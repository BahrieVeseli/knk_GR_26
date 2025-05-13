package models.Dto;

public class UpdateDestinacionetDto {
    private int  destinacionId;

    public UpdateDestinacionetDto(int destinacionId) {
        this.destinacionId = destinacionId;
    }

    public int getDestinacionId() {
        return destinacionId;
    }

    public void setDestinacionId(int destinacionId) {
        this.destinacionId = destinacionId;
    }
}
