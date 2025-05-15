package Repository;

import models.Dto.CreateRezervimetDto;
import models.Dto.UpdateRezervimetDto;
import models.Rezervimet;

import java.sql.*;
import java.time.LocalDate;

public class RezervimetRepository extends BaseRepository<Rezervimet, CreateRezervimetDto, UpdateRezervimetDto> {
    public RezervimetRepository() {
        super("Rezervime");
    }

    @Override
    protected Rezervimet fromResultSet(ResultSet res) throws SQLException {
        return Rezervimet.getInstance(res);
    }

    @Override
    public Rezervimet create(CreateRezervimetDto dto) {
        String query = """
            INSERT INTO Rezervime (perdorues_id, orari_id, data_udhetimit, nr_biletave, data_reservimit)
            VALUES (?, ?, ?, ?, ?)
        """;

        try {
            PreparedStatement pstm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, dto.getPerdoruesId());
            pstm.setInt(2, dto.getOrariId());
            pstm.setDate(3, Date.valueOf(dto.getDataUdhetimit()));
            pstm.setInt(4, dto.getNrBiletave());
            pstm.setDate(5, Date.valueOf(LocalDate.now())); // assuming reservation is made "now"
            pstm.execute();

            ResultSet res = pstm.getGeneratedKeys();
            if (res.next()) {
                int id = res.getInt(1);
                return this.getById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Rezervimet update(UpdateRezervimetDto dto) {
        String query = """
            UPDATE Rezervime 
            SET perdorues_id = ?, orari_id = ?, data_udhetimit = ?, nr_biletave = ?
            WHERE rezervim_id = ?
        """;

        try {
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setInt(1, dto.getPerdoruesId());
            pstm.setInt(2, dto.getOrariId());
            pstm.setDate(3, Date.valueOf(dto.getDataUdhetimit()));
            pstm.setInt(4, dto.getNrBiletave());
            pstm.setInt(5, dto.getRezervimId());

            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getRezervimId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}