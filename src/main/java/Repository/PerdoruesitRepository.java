package Repository;

import Database.DBConnector;
import models.Perdoruesit;
import models.Dto.CreatePerdoruesitDto;
import models.Dto.UpdatePerdoruesitDto;

import java.sql.*;

public class PerdoruesitRepository extends BaseRepository<Perdoruesit, CreatePerdoruesitDto, UpdatePerdoruesitDto> {

    public PerdoruesitRepository() {
        super("perdoruesit");
    }

    @Override
    protected Perdoruesit fromResultSet(ResultSet res) throws SQLException {
        return Perdoruesit.getInstance(res);
    }

    @Override
    public Perdoruesit create(CreatePerdoruesitDto createDto) {
        String query = "INSERT INTO perdoruesit (emri_perdoruesit, fjalekalimi, roli) VALUES (?, ?, ?)";
        try (PreparedStatement pstm = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Perdoruesit update(UpdatePerdoruesitDto updateDto) {
        String query = "UPDATE perdoruesit SET emri_perdoruesit = ?, roli = ? WHERE perdorues_id = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setString(1, updateDto.getEmri_perdoruesit());
            pstm.setString(2, updateDto.getRoli());
            pstm.setInt(3, updateDto.getPerdoruesiID());

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 1) {
                return Perdoruesit.of(updateDto.getPerdoruesiID(), updateDto.getEmri_perdoruesit(), "hidden", updateDto.getRoli());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Perdoruesit getById(int id) {
        String query = "SELECT * FROM perdoruesit WHERE perdorues_id = ?";
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
        String query = "DELETE FROM perdoruesit WHERE perdorues_id = ?";
        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setInt(1, id);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insert(CreatePerdoruesitDto newUserDto) {
        String query = "INSERT INTO perdoruesit (emri_perdoruesit, fjalekalimi, roli) VALUES (?, ?, ?)";

        try (PreparedStatement pstm = this.connection.prepareStatement(query)) {
            pstm.setString(1, newUserDto.getEmriPerdoruesit());
            pstm.setString(2, newUserDto.getFjalekalimi());
            pstm.setString(3, newUserDto.getRoli());

            int rowsAffected = pstm.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("User not added");
            } else {
                System.out.println("User added successfully");
            }

        } catch (SQLException e) {
            System.err.println("Gabim gjatë shtimit të përdoruesit: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Perdoruesit findByUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM Perdoruesit WHERE emri_perdoruesit = ? AND fjalekalimi = ?";
        try (PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setString(1, username);
            pstm.setString(2, password);
            ResultSet res = pstm.executeQuery();
            if (res.next()) {
                return Perdoruesit.getInstance(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Perdoruesit findByUsername(String username) {
        String query = "SELECT * FROM Perdoruesit WHERE emri_perdoruesit = ?";
        try (PreparedStatement pstm = connection.prepareStatement(query)) {
            pstm.setString(1, username);
            ResultSet res = pstm.executeQuery();
            if (res.next()) {
                return Perdoruesit.getInstance(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}

