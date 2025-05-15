package Services;

import models.OrariLinjave;
import models.Dto.CreateOrariLinjaveDto;
import models.Dto.UpdatedOrariLinjaveDto;
import Repository.OrariLinjaveRepository;

import java.util.List;
public class OrariLinjaveService {
    private OrariLinjaveRepository orariRepository;

    // konstruktori
    public OrariLinjaveService() {
        this.orariRepository = new OrariLinjaveRepository();
    }

    // krijimi i nje orari te ri
    public OrariLinjave createOrar(CreateOrariLinjaveDto dto) throws Exception {
        validateCreateDto(dto);
        OrariLinjave created = this.orariRepository.create(dto);
        if (created == null) {
            throw new Exception("Krijimi i orarit deshtoi!");
        }
        return created;
    }

    // Update vetem diten e nje orari qe ekziston
    public OrariLinjave updateDita(UpdatedOrariLinjaveDto dto) throws Exception {
        if (dto.getDita() == null || dto.getDita().trim().isEmpty()) {
            throw new Exception("Dita nuk mund te jete bosh!");
        }
        OrariLinjave updated = this.orariRepository.update(dto);
        if (updated == null) {
            throw new Exception("Perditesimi i dites deshtoi!");
        }
        return updated;
    }

    // get orar by id
    public OrariLinjave getOrarById(int id) throws Exception {
        OrariLinjave result = this.orariRepository.getById(id);
        if (result == null) {
            throw new Exception("Orari me ID-ne e dhene nuk ekziston!");
        }
        return result;
    }

    // get all oraret
    public List<OrariLinjave> getAllOraret() {
        return this.orariRepository.getAll();
    }

    // fshij orarin
    public boolean deleteOrar(int id) throws Exception {
        boolean deleted = this.orariRepository.delete(id);
        if (!deleted) {
            throw new Exception("Fshirja e orarit deshtoi!");
        }
        return true;
    }

    // metode per validim
    private void validateCreateDto(CreateOrariLinjaveDto dto) throws Exception {
        if (dto.getTrenId() <= 0 || dto.getNisjaId() <= 0 || dto.getMbrritjaId() <= 0) {
            throw new Exception("Te dhenat e trenit ose stacioneve jane te pavlefshme!");
        }
        if (dto.getKohaNisjes() == null || dto.getKohaMbrritjes() == null || dto.getDita() == null) {
            throw new Exception("Koha e nisjes, mberritjes dhe dita nuk mund te jene bosh!");
        }
    }
}
