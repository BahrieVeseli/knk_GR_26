package Services;

import Repository.DestinacionetRepository;
import models.Destinacionet;
import models.Dto.CreateDestinacionetDto;
import models.Dto.UpdateDestinacionetDto;
import models.Dto.CreateAuditLogDto;
import Repository.AuditLogRepository;
import Services.AuditLogService;

import java.sql.Timestamp;

public class DestinacionetService {

    private DestinacionetRepository destinacionetRepository;
    private AuditLogService auditLogServices;  // krijon instance te auditlog

    public DestinacionetService() {
        this.destinacionetRepository = new DestinacionetRepository();
        this.auditLogServices = new AuditLogService();  // inicializim i auditlog
    }

    // krijohet destinacioni i ri
    public Destinacionet createDestination(CreateDestinacionetDto createDto) {
        if (createDto.getEmriStacionit() == null || createDto.getEmriStacionit().isEmpty()) {
            throw new IllegalArgumentException("Emri i stacionit nuk mund te jete bosh!");
        }

        Destinacionet newDestinacion = destinacionetRepository.create(createDto);

        // log
        if (newDestinacion != null) {
            // krijohet audit log entry duke perdorur CreateAuditLogDto
            // vetem si shembull e marrum userin me id 1
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis()); // Get the current time
            CreateAuditLogDto auditLogDto = new CreateAuditLogDto(1, "Created a new station: " + newDestinacion.getEmriStacionit(), currentTimestamp);
            try {
                // log
                auditLogServices.create(auditLogDto);
            } catch (Exception e) {
                e.printStackTrace();  // exeption handling
            }
        }

        return newDestinacion;
    }

    // Update ni destinacion
    public Destinacionet updateDestination(UpdateDestinacionetDto updateDto) {
        if (updateDto.getEmri_stacionit() == null || updateDto.getEmri_stacionit().isEmpty()) {
            throw new IllegalArgumentException("Emri i stacionit nuk mund te jete bosh!");
        }

        Destinacionet updatedDestinacion = destinacionetRepository.update(updateDto);

        // Log
        if (updatedDestinacion != null) {
            // krijohet nje audit log entry permes CreateAuditLogDto
            // useri 1 vetem si shembull
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis()); // Get the current time
            CreateAuditLogDto auditLogDto = new CreateAuditLogDto(1, "Stacioni i perditesuar: " + updatedDestinacion.getEmriStacionit(), currentTimestamp);
            try {
                // Log
                auditLogServices.create(auditLogDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return updatedDestinacion;
}
