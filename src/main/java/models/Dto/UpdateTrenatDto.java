package models.Dto;

public class UpdateTrenatDto {
    private int treniId;

    public UpdateTrenatDto(int treniId) {
        this.treniId = treniId;
    }

    public int getTreniId() {
        return treniId;
    }

    public void setTreniId(int treniId) {
        this.treniId = treniId;
    }
}
