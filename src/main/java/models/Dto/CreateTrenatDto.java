package models.Dto;

public class CreateTrenatDto {
    private String emriTreni;
    private String tipiTrenit;
    private int kapaciteti;

    public CreateTrenatDto(String emriTreni, String tipiTrenit, int kapaciteti) {
        this.emriTreni = emriTreni;
        this.tipiTrenit = tipiTrenit;
        this.kapaciteti = kapaciteti;
    }

    public String getEmriTrenti() {
        return emriTreni;
    }

    public void setEmriTrenti(String emriTreni) {
        this.emriTreni = emriTreni;
    }

    public String getTipiTrenit() {
        return tipiTrenit;
    }

    public void setTipiTrenit(String tipiTrenit) {
        this.tipiTrenit = tipiTrenit;
    }

    public int getKapacitei() {
        return kapaciteti;
    }

    public void setKapacitei(int kapacitei) {
        this.kapaciteti = kapacitei;
    }
}
