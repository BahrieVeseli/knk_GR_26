package models.Dto;

public class UpdateStatistikaDto {
    private int statistikaId;

    public UpdateStatistikaDto(int statistikaId) {
        this.statistikaId = statistikaId;
    }

    public int getStatistikaId() {
        return statistikaId;
    }

    public void setStatistikaId(int statistikaId) {
        this.statistikaId = statistikaId;
    }
}
