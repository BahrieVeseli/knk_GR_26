package models.Dto;

public class UpdatePerdoruesitDto {
    private int perdoruesiID;
    private String emri_perdoruesit;
    private String roli;

    public UpdatePerdoruesitDto(int perdoruesiID, String emri_perdoruesit, String roli) {
        this.perdoruesiID = perdoruesiID;
        this.emri_perdoruesit = emri_perdoruesit;
        this.roli = roli;
    }

    public int getPerdoruesiID() {
        return perdoruesiID;
    }

    public void setPerdoruesiID(int perdoruesiID) {
        this.perdoruesiID = perdoruesiID;
    }

    public String getEmri_perdoruesit() {
        return emri_perdoruesit;
    }

    public String getRoli() {
        return roli;
    }
}
