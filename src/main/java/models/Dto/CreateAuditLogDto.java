package models.Dto;
import java.sql.Timestamp;
public class CreateAuditLogDto {

        private int perdoruesId;
        private String veprimi;
        private Timestamp data;

        public CreateAuditLogDto(int perdoruesId, String veprimi, Timestamp data) {
            this.perdoruesId = perdoruesId;
            this.veprimi = veprimi;
            this.data = data;
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



