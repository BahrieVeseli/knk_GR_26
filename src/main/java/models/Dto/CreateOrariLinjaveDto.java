package models.Dto;

public class CreateOrariLinjaveDto {
    private int trenId;
    private int nisjaId;
    private int mbrritjaId;
    private String kohaNisjes;
    private String kohaMbrritjes;
    private String dita;

    public CreateOrariLinjaveDto(int trenId, int nisjaId, int mbrritjaId, String kohaNisjes, String kohaMbrritjes, String dita) {
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

    public String getKohaNisjes() {
        return kohaNisjes;
    }

    public void setKohaNisjes(String kohaNisjes) {
        this.kohaNisjes = kohaNisjes;
    }

    public String getKohaMbrritjes() {
        return kohaMbrritjes;
    }

    public void setKohaMbrritjes(String kohaMbrritjes) {
        this.kohaMbrritjes = kohaMbrritjes;
    }

    public String getDita() {
        return dita;
    }

    public void setDita(String dita) {
        this.dita = dita;
    }
}