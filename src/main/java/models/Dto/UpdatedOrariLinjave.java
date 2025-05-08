package models.Dto;

public class UpdatedOrariLinjave {
    private int orariId;

    public UpdateOrariLinjaveDto(int orariId) {
        this.orariId = orariId;
    }

    public int getOrariId() {
        return orariId;
    }

    public void setOrariId(int orariId) {
        this.orariId = orariId;
    }
}
