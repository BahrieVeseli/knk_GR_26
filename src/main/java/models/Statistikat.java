package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Statistika {
    private int statistikaId;
    private int trenId;
    private String data;
    private int pasagjereTotal;

    private Statistika(int statistikaId, int trenId, String data, int pasagjereTotal) {
        this.statistikaId = statistikaId;
        this.trenId = trenId;
        this.data = data;
        this.pasagjereTotal = pasagjereTotal;
    }
    public static Statistika getInstance(ResultSet result) throws SQLException {
        int statistikaId=result.getInt("stat_id");
        int trenId=result.getInt("tren_id");
        String data=result.getString("data");
        int pasagjereTotal=result.getInt("pasagjere_totale");
        return new Statistika(statistikaId, trenId, data,pasagjereTotal );
    }

    public int getStatistikaId() {
        return statistikaId;
    }

    public int getTrenId() {
        return trenId;
    }

    public String getData() {
        return data;
    }

    public int getPasagjereTotal() {
        return pasagjereTotal;
    }
}
