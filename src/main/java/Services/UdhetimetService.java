package Services;

import Repository.UdhetimetRepository;
import models.Dto.CreateUdhetimeDto;
import models.Dto.UpdateUdhetimeDto;
import models.Udhetime;

import java.time.LocalDate;
import java.util.List;

public class UdhetimetService {

    private UdhetimetRepository udhetimetRepository;

    public UdhetimetService() {
        this.udhetimetRepository = new UdhetimetRepository();
    }


    public Udhetime createUdhetim(CreateUdhetimeDto dto) throws Exception {
        validateCreateDto(dto);

        Udhetime created = this.udhetimetRepository.create(dto);
        if (created == null) {
            throw new Exception("Krijimi i udhetimit deshtoi!");
        }
        return created;
    }


    public Udhetime updateUdhetim(UpdateUdhetimeDto dto) throws Exception {
        validateUpdateDto(dto);

        Udhetime updated = this.udhetimetRepository.update(dto);
        if (updated == null) {
            throw new Exception("Perditesimi i udhetimit deshtoi!");
        }
        return updated;
    }


    public Udhetime getUdhetimById(int id) throws Exception {
        Udhetime udhetimi = this.udhetimetRepository.getById(id);
        if (udhetimi == null) {
            throw new Exception("Udhetimi me ID-ne e dhene nuk ekziston!");
        }
        return udhetimi;
    }

    public List<Udhetime> getAllUdhetimet() {
        return this.udhetimetRepository.getAll();
    }


    public boolean deleteUdhetim(int id) throws Exception {
        boolean deleted = this.udhetimetRepository.delete(id);
        if (!deleted) {
            throw new Exception("Fshirja e udhetimit deshtoi!");
        }
        return true;
    }

    private void validateCreateDto(CreateUdhetimeDto dto) throws Exception {
        if (dto.getOrariId() <= 0) {
            throw new Exception("ID e orarit eshte e pavlefshme.");
        }
        if (dto.getDataUdhetimit() == null || dto.getDataUdhetimit().isBefore(LocalDate.now())) {
            throw new Exception("Data e udhetimit eshte e pavlefshme.");
        }
        if (dto.getPasagjeret() < 0) {
            throw new Exception("Numri i pasagjereve nuk mund te jete negativ.");
        }
        if (dto.getStatusi() == null || dto.getStatusi().trim().isEmpty()) {
            throw new Exception("Statusi eshte i detyrueshem.");
        }
    }

    private void validateUpdateDto(UpdateUdhetimeDto dto) throws Exception {
        if (dto.getUdhetimId() <= 0) {
            throw new Exception("ID e udhetimit eshte e pavlefshme.");
        }
        validateCreateDto(new CreateUdhetimeDto(
                dto.getOrariId(),
                dto.getDataUdhetimit(),
                dto.getPasagjeret(),
                dto.getStatusi()
        ));
    }
}
