package models.Dto;

import java.time.LocalDate;

public class CreateRezervimetDto {
    private int perdoruesId;
    private int orariId;
    private LocalDate dataUdhetimit;
    private int nrBiletave;

    public CreateRezervimetDto(int perdoruesId, int orariId, LocalDate dataUdhetimit, int nrBiletave) {
        this.perdoruesId = perdoruesId;
        this.orariId = orariId;
        this.dataUdhetimit = dataUdhetimit;
        this.nrBiletave = nrBiletave;
    }

    public int getPerdoruesId() {
        return perdoruesId;
    }

    public void setPerdoruesId(int perdoruesId) {
        this.perdoruesId = perdoruesId;
    }

    public int getOrariId() {
        return orariId;
    }

    public void setOrariId(int orariId) {
        this.orariId = orariId;
    }

    public LocalDate getDataUdhetimit() {
        return dataUdhetimit;
    }

    public void setDataUdhetimit(LocalDate dataUdhetimit) {
        this.dataUdhetimit = dataUdhetimit;
    }

    public int getNrBiletave() {
        return nrBiletave;
    }

    public void setNrBiletave(int nrBiletave) {
        this.nrBiletave = nrBiletave;
    }
}
