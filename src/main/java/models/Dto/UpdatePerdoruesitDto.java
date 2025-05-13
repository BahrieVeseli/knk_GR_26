package models.Dto;

public class UpdatePerdoruesitDto {
    private int perdoruesiID;

    public UpdatePerdoruesitDto(int perdoruesiID) {
        this.perdoruesiID = perdoruesiID;
    }

    public int getPerdoruesiID() {
        return perdoruesiID;
    }

    public void setPerdoruesiID(int perdoruesiID) {
        this.perdoruesiID = perdoruesiID;
    }
}
