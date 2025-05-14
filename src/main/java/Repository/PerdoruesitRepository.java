package Repository;

import models.Perdoruesit;
import models.Dto.CreatePerdoruesitDto;
import models.Dto.UpdatePerdoruesitDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class PerdoruesitRepository extends BaseRepository<Perdoruesit, CreatePerdoruesitDto, UpdatePerdoruesitDto> {

    public PerdoruesitRepository() {
        super("Perdoruesit");
    }

    @Override
    protected Perdoruesit fromResultSet(ResultSet res) throws SQLException {
        return Perdoruesit.getInstance(res);
    }

    @Override
    public Perdoruesit create(CreatePerdoruesitDto createDto) {
        String query = "INSERT INTO perdoruesit (emri_perdoruesit, fjalekalimi, roli) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, createDto.getEmriPerdoruesit());
            pstm.setString(2, createDto.getFjalekalimi());
            pstm.setString(3, createDto.getRoli());

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                int newId = generatedKeys.getInt(1);
                return Perdoruesit.of(newId, createDto.getEmriPerdoruesit(), createDto.getFjalekalimi(), createDto.getRoli());
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Perdoruesit update(UpdatePerdoruesitDto updateDto) {
        String query = "UPDATE Perdoruesit SET emri_perdoruesit = ?, roli = ? WHERE perdorues_id = ?";
        try {
            String updatedName = updateDto.getEmri_perdoruesit();
            String updatedRole = updateDto.getRoli();


            PreparedStatement pstm = this.connection.prepareStatement(query);
            pstm.setString(1, updatedName);
            pstm.setString(2, updatedRole);
            pstm.setInt(3, updateDto.getPerdoruesiID());

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 1) {
                return Perdoruesit.of(updateDto.getPerdoruesiID(), updatedName, "hidden", updatedRole);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
