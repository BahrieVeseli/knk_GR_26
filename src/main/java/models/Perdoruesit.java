package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Perdoruesit {
    private int perdoruesiID;
    private String emriPerdoruesit;
    private String fjalekalimi;
    private String roli;

    private Perdoruesit(int perdoruesiID, String emriPerdoruesit, String fjalekalimi, String roli) {
        this.perdoruesiID = perdoruesiID;
        this.emriPerdoruesit = emriPerdoruesit;
        this.fjalekalimi = fjalekalimi;
        this.roli = roli;
    }
    public static Perdoruesit of(int perdoruesiID, String emriPerdoruesit, String fjalekalimi, String roli) {
        return new Perdoruesit(perdoruesiID, emriPerdoruesit, fjalekalimi, roli);
    }

    public static Perdoruesit getInstance(ResultSet result) throws SQLException {
        int perdoruesiId=result.getInt("perdorues_id");
        String emriPerdoruesit=result.getString("emri_perdoruesit");
        String fjalekalimi=result.getString("fjalekalimi");
        String roli=result.getString("roli");
        return new Perdoruesit(perdoruesiId,emriPerdoruesit,fjalekalimi, roli);
    }

    public int getPerdoruesiID() {
        return perdoruesiID;
    }

    public String getEmriPerdoruesit() {
        return emriPerdoruesit;
    }

    public String getFjalekalimi() {
        return fjalekalimi;
    }

    public String getRoli() {
        return roli;
    }
}