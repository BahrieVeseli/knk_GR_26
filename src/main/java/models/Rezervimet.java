package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Rezervimet {
    private int rezervimId;
    private int perdoruesId;
    private int orariId;
    private LocalDate dataUdhetimit;
    private int nrBiletave;
    private LocalDate dataRezervimit;

    public Rezervimet(int rezervimiId, int perdoruesiId, int orariId, LocalDate dataUdhetimit, int nrBiletave, LocalDate dataRezervimit){
        this.rezervimId = rezervimId;
        this.perdoruesId = perdoruesId;
        this.orariId = orariId;
        this.dataUdhetimit = dataUdhetimit;
        this.nrBiletave = nrBiletave;
        this.dataRezervimit = dataRezervimit;
    }
    
    public static Rezervimet getInstance(ResultSet result) throws SQLException {
        int rezervimId = result.getInt("rezervim_id");
        int perdoruesId = result.getInt("perdorues_id");
        int orariId = result.getInt("orari_id");
        LocalDate dataUdhetimit = result.getDate("data_udhetimit").toLocalDate();
        int nrBiletave = result.getInt("nr_biletave");
        LocalDate dataRezervimit = result.getDate("data_rezervimit").toLocalDate();
        return new Rezervimet(rezervimId, perdoruesId, orariId, dataUdhetimit, nrBiletave, dataRezervimit);

    }

    public int getRezervimId() {
        return rezervimId;
    }

    public int getPerdoruesId() {
        return perdoruesId;
    }

    public int getOrariId() {
        return orariId;
    }

    public LocalDate getDataUdhetimit() {
        return dataUdhetimit;
    }

    public int getNrBiletave() {
        return nrBiletave;
    }

    public LocalDate getDataRezervimit() {
        return dataRezervimit;
    }
}
