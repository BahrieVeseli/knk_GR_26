package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Udhetime {
    private int udhetimId;
    private int orariId;
    private LocalDate dataudhetimit;
    private int pasagjeret;
    private String statusi;

    private Udhetime(int udhetimId, int orariId, LocalDate dataudhetimit, int pasagjeret, String statusi) {
        this.udhetimId = udhetimId;
        this.orariId = orariId;
        this.dataudhetimit = dataudhetimit;
        this.pasagjeret = pasagjeret;
        this.statusi = statusi;


    }
    public static Udhetime getInstance(ResultSet result) throws SQLException {
        int udhetimId=result.getInt("udhetim_id");
        int orariId=result.getInt("orari_id");
        LocalDate dataudhetimit= result.getDate("data_udhetimit").toLocalDate();
        int pasagjeret=result.getInt("pasagjere");
        String statusi =result.getString("statusi");
        return new Udhetime(udhetimId ,orariId, dataudhetimit, pasagjeret,statusi);
    }

    public int getUdhetimId() {
        return udhetimId;
    }

    public int getOrariId() {
        return orariId;
    }

    public LocalDate getDataudhetimit() {
        return dataudhetimit;
    }

    public int getPasagjeret() {
        return pasagjeret;
    }

    public String getStatusi() {
        return statusi;
    }
}
