package Services;
import Repository.AuditLogRepository;
import models.Dto.CreateAuditLogDto;
import models.Dto.UpdateAuditLogDto;
import models.AuditLog;

public class AuditLogService {

        private final AuditLogRepository auditLogRepository;

        public AuditLogService() {
            this.auditLogRepository = new AuditLogRepository();
        }

        public AuditLog create(CreateAuditLogDto dto) throws Exception {
            if (dto == null || dto.getPerdoruesId() <= 0 || dto.getVeprimi() == null || dto.getVeprimi().isBlank()) {
                throw new Exception("Invalid data for creating audit log.");
            }

            AuditLog result = auditLogRepository.create(dto);
            if (result == null) {
                throw new Exception("Failed to create audit log.");
            }

            return result;
        }

        public AuditLog update(UpdateAuditLogDto dto) throws Exception {
            if (dto == null || dto.getLogId() <= 0 || dto.getPerdoruesId() <= 0 || dto.getVeprimi() == null || dto.getVeprimi().isBlank()) {
                throw new Exception("Invalid data for updating audit log.");
            }

            AuditLog result = auditLogRepository.update(dto);
            if (result == null) {
                throw new Exception("Failed to update audit log.");
            }

            return result;
        }
    }
}
