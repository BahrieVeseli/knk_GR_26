package Services;

import models.Dto.CreateRezervimetDto;
import models.Dto.UpdateRezervimetDto;
import models.Rezervimet;
import Repository.RezervimetRepository;

import java.time.LocalDate;
import java.util.List;

public class RezervimetService {

    private RezervimetRepository rezervimetRepository;

    public RezervimetService() {
        this.rezervimetRepository = new RezervimetRepository();
    }


    public Rezervimet createRezervim(CreateRezervimetDto dto) throws Exception {
        validateCreateDto(dto);

        Rezervimet created = rezervimetRepository.create(dto);
        if (created == null) {
            throw new Exception("Create Reservation failed!");
        }

        return created;
    }


    public Rezervimet updateRezervim(UpdateRezervimetDto dto) throws Exception {
        validateUpdateDto(dto);

        Rezervimet updated = rezervimetRepository.update(dto);
        if (updated == null) {
            throw new Exception("Updating Reservation failed!");
        }

        return updated;
    }

    public Rezervimet getRezervimById(int id) throws Exception {
        Rezervimet rezervimi = rezervimetRepository.getById(id);
        if (rezervimi == null) {
            throw new Exception("Reservation with provided ID does not exist!");
        }
        return rezervimi;
    }

    // Get all reservations
    public List<Rezervimet> getAllRezervimet() {
        return rezervimetRepository.getAll();
    }


    public boolean deleteRezervim(int id) throws Exception {
        boolean deleted = rezervimetRepository.delete(id);
        if (!deleted) {
            throw new Exception("Deleting Reservation failed!");
        }
        return true;
    }



    private void validateCreateDto(CreateRezervimetDto dto) throws Exception {
        if (dto.getPerdoruesId() <= 0 || dto.getOrariId() <= 0) {
            throw new Exception("User or schedule data is invalid!");
        }
        if (dto.getNrBiletave() <= 0) {
            throw new Exception("Ticket number must be bigger than 0");
        }
        if (dto.getDataUdhetimit() == null || dto.getDataUdhetimit().isBefore(LocalDate.now())) {
            throw new Exception("Travel data is invalid!");
        }
    }

    private void validateUpdateDto(UpdateRezervimetDto dto) throws Exception {
        if (dto.getRezervimId() <= 0) {
            throw new Exception("Reservation ID invalid");
        }
        if (dto.getNrBiletave() <= 0) {
            throw new Exception("Ticket number must be bigger than 0.");
        }
        if (dto.getDataUdhetimit() == null || dto.getDataUdhetimit().isBefore(LocalDate.now())) {
            throw new Exception("Data e udhetimit eshte e pavlefshme.");
        }
    }
}