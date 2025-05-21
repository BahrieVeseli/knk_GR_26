package Repository;

import models.Dto.CreateOrariLinjaveDto;
import models.Dto.UpdatedOrariLinjaveDto;
import models.OrariLinjave;

import java.sql.*;

public class OrariLinjaveRepository extends BaseRepository<OrariLinjave, CreateOrariLinjaveDto, UpdatedOrariLinjaveDto> {

    public OrariLinjaveRepository() {
        super("orari_linjave");
    }

    @Override
    protected OrariLinjave fromResultSet(ResultSet res) throws SQLException {
        return OrariLinjave.getInstance(res);
    }

    @Override
    public OrariLinjave create(CreateOrariLinjaveDto dto) {
        String query = """
                INSERT INTO orari_linjave (tren_id, nisja_id, mbrritja_id, koha_nisjes, koha_mberritjes, dita)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, dto.getTrenId());
            pstm.setInt(2, dto.getNisjaId());
            pstm.setInt(3, dto.getMbrritjaId());
            pstm.setTime(4, dto.getKohaNisjes());
            pstm.setTime(5, dto.getKohaMbrritjes());
            pstm.setString(6, dto.getDita());
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
    public OrariLinjave update(UpdatedOrariLinjaveDto dto) {
        String query = """
                UPDATE orari_linjave
                SET dita = ?
                WHERE orari_id = ?
                """;
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setString(1, dto.getDita());
            pstm.setInt(2, dto.getOrariId());
            int updated = pstm.executeUpdate();
            if (updated == 1) {
                return this.getById(dto.getOrariId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrariLinjave getById(int id) {
        String query = "SELECT * FROM orari_linjave WHERE orari_id = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
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
    public boolean delete(int id) {
        String query = "DELETE FROM orari_linjave WHERE orari_id = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
