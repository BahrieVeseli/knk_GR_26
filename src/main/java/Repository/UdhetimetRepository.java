package Repository;

import models.Udhetime;
import models.Dto.CreateUdhetimeDto;
import models.Dto.UpdateUdhetimeDto;
import java.sql.*;
import java.time.LocalDate;

public class UdhetimetRepository extends BaseRepository<Udhetime, CreateUdhetimeDto, UpdateUdhetimeDto> {

    public UdhetimetRepository() {
        super("Udhetimet");
    }

    @Override
    protected Udhetime fromResultSet(ResultSet res) throws SQLException {
        return Udhetime.getInstance(res);
    }

    @Override
    public Udhetime create(CreateUdhetimeDto createDto) {
        String query = "INSERT INTO Udhetimet (orari_id, data_udhetimit, pasagjere, statusi) " +
                "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, createDto.getOrariId());
            pstm.setDate(2, Date.valueOf(createDto.getDataUdhetimit()));
            pstm.setInt(3, createDto.getPasagjeret());
            pstm.setString(4, createDto.getStatusi());

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating udhetim failed, no rows affected.");
            }

            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                return Udhetime.of(newId, createDto.getOrariId(), createDto.getDataUdhetimit(), createDto.getPasagjeret(), createDto.getStatusi());
            } else {
                throw new SQLException("Creating udhetim failed, no ID obtained.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Udhetime update(UpdateUdhetimeDto updateDto) {
        String query = "UPDATE Udhetimet SET orari_id = ?, data_udhetimit = ?, pasagjere = ?, statusi = ? WHERE udhetim_id = ?";

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, updateDto.getOrariId());
            pstm.setDate(2, Date.valueOf(updateDto.getDataUdhetimit()));
            pstm.setInt(3, updateDto.getPasagjeret());
            pstm.setString(4, updateDto.getStatusi());
            pstm.setInt(5, updateDto.getUdhetimId());

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 1) {
                return Udhetime.of(updateDto.getUdhetimId(), updateDto.getOrariId(), updateDto.getDataUdhetimit(), updateDto.getPasagjeret(), updateDto.getStatusi());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}