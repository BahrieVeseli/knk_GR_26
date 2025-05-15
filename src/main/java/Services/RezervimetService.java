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
            throw new Exception("Krijimi i rezervimit deshtoi!");
        }

        return created;
    }


    public Rezervimet updateRezervim(UpdateRezervimetDto dto) throws Exception {
        validateUpdateDto(dto);

        Rezervimet updated = rezervimetRepository.update(dto);
        if (updated == null) {
            throw new Exception("Perditesimi i rezervimit deshtoi!");
        }

        return updated;
    }

    public Rezervimet getRezervimById(int id) throws Exception {
        Rezervimet rezervimi = rezervimetRepository.getById(id);
        if (rezervimi == null) {
            throw new Exception("Rezervimi me ID-ne e dhene nuk ekziston!");
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
            throw new Exception("Fshirja e rezervimit deshtoi!");
        }
        return true;
    }



    private void validateCreateDto(CreateRezervimetDto dto) throws Exception {
        if (dto.getPerdoruesId() <= 0 || dto.getOrariId() <= 0) {
            throw new Exception("Te dhenat e perdoruesit ose orarit jane te pavlefshme.");
        }
        if (dto.getNrBiletave() <= 0) {
            throw new Exception("Numri i biletave duhet te jete me i madh se zero.");
        }
        if (dto.getDataUdhetimit() == null || dto.getDataUdhetimit().isBefore(LocalDate.now())) {
            throw new Exception("Data e udhetimit eshte e pavlefshme.");
        }
    }

    private void validateUpdateDto(UpdateRezervimetDto dto) throws Exception {
        if (dto.getRezervimId() <= 0) {
            throw new Exception("ID e rezervimit eshte e pavlefshme.");
        }
        if (dto.getNrBiletave() <= 0) {
            throw new Exception("Numri i biletave duhet te jete me i madh se zero.");
        }
        if (dto.getDataUdhetimit() == null || dto.getDataUdhetimit().isBefore(LocalDate.now())) {
            throw new Exception("Data e udhetimit eshte e pavlefshme.");
        }
    }
}
