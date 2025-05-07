package models.Dto;

public class CreateTrenatDto {
    private String emriTrenti;
    private String tipiTrenit;
    private int kapacitei;

    public CreateTrenatDto(String emriTrenti, String tipiTrenit, int kapacitei) {
        this.emriTrenti = emriTrenti;
        this.tipiTrenit = tipiTrenit;
        this.kapacitei = kapacitei;
    }

    public String getEmriTrenti() {
        return emriTrenti;
    }

    public void setEmriTrenti(String emriTrenti) {
        this.emriTrenti = emriTrenti;
    }

    public String getTipiTrenit() {
        return tipiTrenit;
    }

    public void setTipiTrenit(String tipiTrenit) {
        this.tipiTrenit = tipiTrenit;
    }

    public int getKapacitei() {
        return kapacitei;
    }

    public void setKapacitei(int kapacitei) {
        this.kapacitei = kapacitei;
    }
}
