package models.Dto;

public class UpdateTrenatDto {
    private int treniId;
    private int kapaciteti;

    public UpdateTrenatDto(int treniId, int kapaciteti) {

        this.treniId = treniId;
        this.kapaciteti=kapaciteti;
    }

    public int getTreniId() {

        return treniId;
    }

    public void setTreniId(int treniId) {

        this.treniId = treniId;
    }
    public int getKapaciteti() {
        return kapaciteti;
    }
    public int setKapaciteti(int kapaciteti){
        return kapaciteti;
    }
}
