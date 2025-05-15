package models.Dto;

public class UpdateDestinacionetDto {
    private int  destinacionId;
    private String emri_stacionit;

    public UpdateDestinacionetDto(int destinacionId) {
        this.destinacionId = destinacionId;
    }

    public int getDestinacionId() {
        return destinacionId;
    }

    public void setDestinacionId(int destinacionId) {
        this.destinacionId = destinacionId;
    }
    public String getEmri_stacionit() {
        return emri_stacionit;
    }
}
