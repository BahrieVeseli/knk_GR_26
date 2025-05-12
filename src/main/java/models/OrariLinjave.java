package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrariLinjave {
    private int orariId;
    private int trenId;
    private int nisjaId;
    private int mbrritjaId;
    private String kohaNisjes;
    private String kohaMbrritjes;
    private String dita;

    private OrariLinjave(int orariId, int trenId, int nisjaId, int mbrritjaId, String kohaNisjes, String kohaMbrritjes, String dita) {
        this.orariId = orariId;
        this.trenId = trenId;
        this.nisjaId = nisjaId;
        this.mbrritjaId = mbrritjaId;
        this.kohaNisjes = kohaNisjes;
        this.kohaMbrritjes = kohaMbrritjes;
        this.dita = dita;
    }
    public static OrariLinjave getInstance(ResultSet result) throws SQLException {
        int orariId=result.getInt("perdorues_id");
        int trenId=result.getInt("perdorues_id");
        int nisjaId=result.getInt("perdorues_id");
        int mbrritjaId=result.getInt("perdorues_id");
        String kohaNisjes=result.getString("emri_perdoruesit");
        String kohaMbrritjes=result.getString("fjalekalimi");
        String dita=result.getString("roli");
        return new OrariLinjave(orariId,trenId,nisjaId, mbrritjaId,kohaNisjes,kohaMbrritjes , dita );
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

    public String getKohaNisjes() {
        return kohaNisjes;
    }

    public String getKohaMbrritjes() {
        return kohaMbrritjes;
    }

    public String getDita() {
        return dita;
    }


}
