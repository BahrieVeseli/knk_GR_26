package Repository;

import Database.DBConnector;

import java.sql.*;
import java.util.ArrayList;

public abstract class BaseRepository<Model, CreateModelDto, UpdateModelDto> {
    protected Connection connection;
    private final String tableName;

    public BaseRepository(String tableName) {
        this.connection = DBConnector.getConnection();
        this.tableName = tableName;
    }


    protected abstract Model fromResultSet(ResultSet res) throws SQLException;


    public ArrayList<Model> getAll() {
        ArrayList<Model> models = new ArrayList<>();
        String query = "SELECT * FROM " + this.tableName;
        try (Statement stm = this.connection.createStatement();
             ResultSet res = stm.executeQuery(query)) {
            while (res.next()) {
                models.add(this.fromResultSet(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return models;
    }


    public abstract Model getById(int id);

    public abstract boolean delete(int id);

    public abstract Model create(CreateModelDto dto);

    public abstract Model update(UpdateModelDto dto);
}

