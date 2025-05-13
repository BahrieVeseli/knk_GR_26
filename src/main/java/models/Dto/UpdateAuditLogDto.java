package models.Dto;
import java.sql.Timestamp;
public class UpdateAuditLogDto {

        private int logId;
        private int perdoruesId;
        private String veprimi;
        private Timestamp data;

        public UpdateAuditLogDto(int logId, int perdoruesId, String veprimi, Timestamp data) {
            this.logId = logId;
            this.perdoruesId = perdoruesId;
            this.veprimi = veprimi;
            this.data = data;
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

        public Timestamp getData() {
            return data;
        }
    }



