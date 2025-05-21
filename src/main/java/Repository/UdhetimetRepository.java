package Repository;

import Database.DBConnector;
import models.Udhetime;
import models.Dto.CreateUdhetimeDto;
import models.Dto.UpdateUdhetimeDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UdhetimetRepository extends BaseRepository<Udhetime, CreateUdhetimeDto, UpdateUdhetimeDto> {

    public UdhetimetRepository() {
        super("udhetimet");
    }

    public static int countAllTrips() {
        String query = "SELECT COUNT(*) AS total FROM udhetimet";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int countByStatus(String status) {
        String query = "SELECT COUNT(*) FROM udhetimet WHERE statusi = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Udhetime getById(int id) {
        String query = "SELECT * FROM udhetimet WHERE udhetim_id = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, id);
            ResultSet res = pstm.executeQuery();
            if (res.next()) {
                return fromResultSet(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Udhetime fromResultSet(ResultSet res) throws SQLException {
        return Udhetime.getInstance(res);
    }

    @Override
    public Udhetime create(CreateUdhetimeDto createDto) {
        String query = """
            INSERT INTO udhetimet (orari_id, data_udhetimit, pasagjere, statusi)
            VALUES (?, ?, ?, ?)
        """;

        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, createDto.getOrariId());
            pstm.setDate(2, Date.valueOf(createDto.getDataUdhetimit()));
            pstm.setInt(3, createDto.getPasagjeret());
            pstm.setString(4, createDto.getStatusi());

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Krijimi i udhëtimit dështoi.");
            }

            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                return Udhetime.of(newId, createDto.getOrariId(), createDto.getDataUdhetimit(), createDto.getPasagjeret(), createDto.getStatusi());
            } else {
                throw new SQLException("Nuk u gjenerua ID për udhëtimin.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Udhetime update(UpdateUdhetimeDto updateDto) {
        String query = """
            UPDATE udhetimet
            SET orari_id = ?, data_udhetimit = ?, pasagjere = ?, statusi = ?
            WHERE udhetim_id = ?
        """;

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
    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM udhetimet WHERE udhetim_id = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setInt(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}