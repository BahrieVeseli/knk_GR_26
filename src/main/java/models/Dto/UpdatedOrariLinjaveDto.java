package models.Dto;

public class UpdatedOrariLinjaveDto {
    private int orariId;
    private String dita;

    public void UpdatedOrariLinjaveDto(int orariId, String dita ) {
        this.orariId = orariId;
        this.dita = dita;
    }

    public int getOrariId() {

        return orariId;
    }
    public String getDita() {
        return dita;
    }
    public void setOrariId(int orariId) {
        this.orariId = orariId;
    }
}

