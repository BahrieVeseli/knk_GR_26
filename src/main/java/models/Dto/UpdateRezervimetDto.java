package models.Dto;

import java.time.LocalDate;

public class UpdateRezervimetDto {
    private int rezervimId;
    private int perdoruesId;
    private int orariId;
    private LocalDate dataUdhetimit;
    private int nrBiletave;
    private LocalDate dataRezervimit;

    public UpdateRezervimetDto(int rezervimId, int perdoruesId, int orariId, LocalDate dataUdhetimit, int nrBiletave, LocalDate dataRezervimit) {
        this.rezervimId = rezervimId;
        this.perdoruesId = perdoruesId;
        this.orariId = orariId;
        this.dataUdhetimit = dataUdhetimit;
        this.nrBiletave = nrBiletave;
        this.dataRezervimit = dataRezervimit;
    }

    public int getRezervimId() {
        return rezervimId;
    }

    public void setRezervimId(int rezervimId) {
        this.rezervimId = rezervimId;
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

    public LocalDate getDataRezervimit() {
        return dataRezervimit;
    }

    public void setDataRezervimit(LocalDate dataRezervimit) {
        this.dataRezervimit = dataRezervimit;
    }
}
