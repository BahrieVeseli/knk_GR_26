package models;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Destinacionet {
    private int destinacionId;
    private String emriStacionit;

    private Destinacionet(int destinacionId, String emriStacionit) {
        this.destinacionId = destinacionId;
        this.emriStacionit = emriStacionit;
    }
    public static Destinacionet getInstance(ResultSet result) throws SQLException {
        int destinacionId=result.getInt("destinacion_id");
        String emriStacionit=result.getString("emri_stacionit");
        return new Destinacionet(destinacionId,emriStacionit);
    }

    public int getDestinacionId() {
        return destinacionId;
    }

    public String getEmriStacionit() {
        return emriStacionit;
    }
}
