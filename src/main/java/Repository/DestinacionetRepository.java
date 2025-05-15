package Repository;

import models.Destinacionet;
import models.Dto.CreateDestinacionetDto;
import models.Dto.UpdateDestinacionetDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DestinacionetRepository extends BaseRepository<Destinacionet, CreateDestinacionetDto, UpdateDestinacionetDto>{
    public DestinacionetRepository() {
        super("Destinacionet");
    }

    @Override
    protected Destinacionet fromResultSet(ResultSet res) throws SQLException {
        return Destinacionet.getInstance(res);
    }

    @Override
    public Destinacionet create(CreateDestinacionetDto createDto) {
        String query = "INSERT INTO Destinacionet (emri_stacionit) VALUES (?)";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, createDto.getEmriStacionit());

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating destination failed, no rows affected.");
            }

            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                return Destinacionet.of(newId, createDto.getEmriStacionit());

            } else {
                throw new SQLException("Creating destination failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Destinacionet update(UpdateDestinacionetDto updateDto) {
        String query = "UPDATE Destinacionet SET emri_stacionit = ? WHERE destinacion_id = ?";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query);

            pstm.setString(1, updateDto.getEmri_stacionit());
            pstm.setInt(2, updateDto.getDestinacionId());

            int rows = pstm.executeUpdate();
            if (rows == 1) {
                return Destinacionet.of(updateDto.getDestinacionId(), updateDto.getEmri_stacionit());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
