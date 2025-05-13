package models.Dto;


import java.time.LocalDate;

public class UpdateUdhetimeDto {
    private int udhetimId;
    private int orariId;
    private LocalDate dataUdhetimit;
    private int pasagjeret;
    private String statusi;

    public UpdateUdhetimeDto(int udhetimId, int orariId, LocalDate dataUdhetimit, int pasagjeret, String statusi) {
        this.udhetimId = udhetimId;
        this.orariId = orariId;
        this.dataUdhetimit = dataUdhetimit;
        this.pasagjeret = pasagjeret;
        this.statusi = statusi;
    }

    public int getUdhetimId() {
        return udhetimId;
    }

    public int getOrariId() {
        return orariId;
    }

    public LocalDate getDataUdhetimit() {
        return dataUdhetimit;
    }

    public int getPasagjeret() {
        return pasagjeret;
    }

    public String getStatusi() {
        return statusi;
    }
}

