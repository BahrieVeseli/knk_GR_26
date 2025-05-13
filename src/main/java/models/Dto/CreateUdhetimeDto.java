package models.Dto;

import java.time.LocalDate;

public class CreateUdhetimeDto {
    private int orariId;
    private LocalDate dataUdhetimit;
    private int pasagjeret;
    private String statusi;

    public CreateUdhetimeDto(int orariId, LocalDate dataUdhetimit, int pasagjeret, String statusi) {
        this.orariId = orariId;
        this.dataUdhetimit = dataUdhetimit;
        this.pasagjeret = pasagjeret;
        this.statusi = statusi;
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
