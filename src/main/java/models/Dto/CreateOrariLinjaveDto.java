package models.Dto;


import java.sql.Time;

public class CreateOrariLinjaveDto {
    private int trenId;
    private int nisjaId;
    private int mbrritjaId;
    private Time kohaNisjes;
    private Time kohaMbrritjes;
    private String dita;

    public CreateOrariLinjaveDto(int trenId, int nisjaId, int mbrritjaId, Time kohaNisjes, Time kohaMbrritjes, String dita) {
        this.trenId = trenId;
        this.nisjaId = nisjaId;
        this.mbrritjaId = mbrritjaId;
        this.kohaNisjes = kohaNisjes;
        this.kohaMbrritjes = kohaMbrritjes;
        this.dita = dita;
    }

    public int getTrenId() {
        return trenId;
    }

    public void setTrenId(int trenId) {
        this.trenId = trenId;
    }

    public int getNisjaId() {
        return nisjaId;
    }

    public void setNisjaId(int nisjaId) {
        this.nisjaId = nisjaId;
    }

    public int getMbrritjaId() {
        return mbrritjaId;
    }

    public void setMbrritjaId(int mbrritjaId) {
        this.mbrritjaId = mbrritjaId;
    }

    public Time getKohaNisjes() {
        return kohaNisjes;
    }

    public void setKohaNisjes(Time kohaNisjes) {
        this.kohaNisjes = kohaNisjes;
    }

    public Time getKohaMbrritjes() {
        return kohaMbrritjes;
    }

    public void setKohaMbrritjes(Time kohaMbrritjes) {
        this.kohaMbrritjes = kohaMbrritjes;
    }

    public String getDita() {
        return dita;
    }

    public void setDita(String dita) {
        this.dita = dita;
    }
}