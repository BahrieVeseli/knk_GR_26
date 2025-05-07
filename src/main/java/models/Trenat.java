package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Trenat {
    private int trenId;
    private String emriTrenit;
    private String tipiTrenit;
    private int kapaciteti;

    private Trenat(int trenId, String emriTrenit, String tipiTrenit, int kapaciteti) {
        this.trenId = trenId;
        this.emriTrenit = emriTrenit;
        this.tipiTrenit = tipiTrenit;
        this.kapaciteti = kapaciteti;
    }
    public static Trenat getInstance(ResultSet result) throws SQLException {
        int trenId=result.getInt("tren_id");
        String emriTrenit=result.getString("emri_trenit");
        String tipiTrenit=result.getString("tipi_trenit");
        int kapaciteti=result.getInt("kapaciteti");
        return new Trenat(trenId, emriTrenit, tipiTrenit,kapaciteti);
    }

    public int getTrenId() {
        return trenId;
    }

    public String getEmriTrenit() {
        return emriTrenit;
    }

    public String getTipiTrenit() {
        return tipiTrenit;
    }

    public int getKapaciteti() {
        return kapaciteti;
    }
}
