package models;
//ndryshimet jane pej konvertimi i dy variablave pej String ne time

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class   OrariLinjave {
    private int orariId;
    private int trenId;
    private int nisjaId;
    private int mbrritjaId;
    private Time kohaNisjes;
    private Time kohaMbrritjes;
    private String dita;

    private OrariLinjave(int orariId, int trenId, int nisjaId, int mbrritjaId, Time kohaNisjes, Time kohaMbrritjes, String dita) {
        this.orariId = orariId;
        this.trenId = trenId;
        this.nisjaId = nisjaId;
        this.mbrritjaId = mbrritjaId;
        this.kohaNisjes = kohaNisjes;
        this.kohaMbrritjes = kohaMbrritjes;
        this.dita = dita;
    }
    public static OrariLinjave getInstance(ResultSet result) throws SQLException {
        int orariId = result.getInt("orari_id");
        int trenId = result.getInt("tren_id");
        int nisjaId = result.getInt("nisja_id");
        int mbrritjaId = result.getInt("mbrritja_id");
        Time kohaNisjes = result.getTime("koha_nisjes");
        Time kohaMbrritjes = result.getTime("koha_mberritjes");
        String dita = result.getString("dita");

        return new OrariLinjave(orariId, trenId, nisjaId, mbrritjaId, kohaNisjes, kohaMbrritjes, dita);
    }


    public static OrariLinjave getInstance(int orariId, int trenId, int nisjaId, int mbrritjaId, Time kohaNisjes, Time kohaMberritjes, String dita) {
        return new OrariLinjave(orariId,trenId,nisjaId,mbrritjaId,kohaNisjes,kohaMberritjes,dita);
    }

    public int getOrariId() {
        return orariId;
    }

    public int getTrenId() {
        return trenId;
    }

    public int getNisjaId() {
        return nisjaId;
    }

    public int getMbrritjaId() {
        return mbrritjaId;
    }

    public Time getKohaNisjes() {
        return kohaNisjes;
    }

    public Time getKohaMbrritjes() {
        return kohaMbrritjes;
    }

    public String getDita() {
        return dita;
    }


}
