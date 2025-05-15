package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AuditLog {
    private int logId;
    private int perdoruesId;
    private String veprimi;
    private Timestamp date;

    private AuditLog(int logId, int perdoruesId, String veprimi, Timestamp date) {
        this.logId = logId;
        this.perdoruesId = perdoruesId;
        this.veprimi = veprimi;
        this.date = date;
    }
    public static AuditLog of(int logId, int perdoruesId, String veprimi, Timestamp date) {
        return new AuditLog(logId, perdoruesId, veprimi, date);
    }

    public static AuditLog getInstance(ResultSet result) throws SQLException {
        int logId=result.getInt("log_id");
        int perdoruesId=result.getInt("perdorues_id");
        String veprimi=result.getString("veprimi");
        Timestamp date=result.getTimestamp("data");
        return new AuditLog(logId, perdoruesId, veprimi,date);
    }

    public int getLogId() {
        return logId;
    }

    public int getPerdoruesId() {
        return perdoruesId;
    }

    public String getVeprimi() {
        return veprimi;
    }

    public Timestamp getDate() {
        return date;
    }
}
