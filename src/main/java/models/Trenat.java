package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Trenat {
    private int tren_id;
    private String emri_trenit;
    private String tipi_trenit;
    private int kapaciteti;

    private Trenat(int tren_id, String emri_trenit, String tipi_trenit, int kapaciteti) {
        this.tren_id = tren_id;
        this.emri_trenit = emri_trenit;
        this.tipi_trenit = tipi_trenit;
        this.kapaciteti = kapaciteti;
    }
    public static Trenat getInstance(ResultSet result) throws SQLException {
        int trenId=result.getInt("tren_id");
        String emriTrenit=result.getString("emri_trenit");
        String tipiTrenit=result.getString("tipi_trenit");
        int kapaciteti=result.getInt("kapaciteti");
        return new Trenat(trenId, emriTrenit, tipiTrenit,kapaciteti);
    }

    public int getTren_id() {
        return tren_id;
    }

    public String getEmri_trenit() {
        return emri_trenit;
    }

    public String getTipi_trenit() {
        return tipi_trenit;
    }

    public int getKapaciteti() {
        return kapaciteti;
    }
}
