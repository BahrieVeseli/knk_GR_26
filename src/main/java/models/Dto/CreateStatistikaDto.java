package models.Dto;

public class CreateStatistikaDto {
    private int trenId;
    private String data;
    private int pasagjereTotal;

    public CreateStatistikaDto(int trenId, String data, int pasagjereTotal) {
        this.trenId = trenId;
        this.data = data;
        this.pasagjereTotal = pasagjereTotal;
    }

    public int getTrenId() {
        return trenId;
    }

    public void setTrenId(int trenId) {
        this.trenId = trenId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getPasagjereTotal() {
        return pasagjereTotal;
    }

    public void setPasagjereTotal(int pasagjereTotal) {
        this.pasagjereTotal = pasagjereTotal;
    }
}
