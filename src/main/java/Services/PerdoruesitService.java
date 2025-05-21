package Services;

import Repository.PerdoruesitRepository;
import models.Perdoruesit;
import models.Dto.CreatePerdoruesitDto;
import models.Dto.UpdatePerdoruesitDto;

import java.util.List;

public class PerdoruesitService {

    private PerdoruesitRepository perdoruesitRepository;

    public PerdoruesitService() {
        this.perdoruesitRepository = new PerdoruesitRepository();
    }

    public Perdoruesit createUser(CreatePerdoruesitDto dto) throws Exception {
        validateCreateDto(dto);

        Perdoruesit created = this.perdoruesitRepository.create(dto);
        if (created == null) {
            throw new Exception("Create user failed!");
        }

        return created;
    }


    public Perdoruesit updateUser(UpdatePerdoruesitDto dto) throws Exception {
        if (dto.getEmri_perdoruesit() == null || dto.getEmri_perdoruesit().trim().isEmpty()) {
            throw new Exception("Username cannot be empty");
        }

        Perdoruesit updated = this.perdoruesitRepository.update(dto);
        if (updated == null) {
            throw new Exception("Updating user failed!");
        }

        return updated;
    }


    public Perdoruesit getUserById(int id) throws Exception {
        Perdoruesit user = this.perdoruesitRepository.getById(id);
        if (user == null) {
            throw new Exception("User with provided ID does not exist!");
        }
        return user;
    }


    public List<Perdoruesit> getAllUsers() {
        return this.perdoruesitRepository.getAll();
    }

    public boolean deleteUser(int id) throws Exception {
        boolean deleted = this.perdoruesitRepository.delete(id);
        if (!deleted) {
            throw new Exception("Fshirja e përdoruesit deshtoi!");
        }
        return true;
    }


    private static void validateCreateDto(CreatePerdoruesitDto dto) throws Exception {
        if (dto.getEmriPerdoruesit() == null || dto.getEmriPerdoruesit().trim().isEmpty()) {
            throw new Exception("Emri i perdoruesit eshte i detyrueshem.");
        }
        if (dto.getFjalekalimi() == null || dto.getFjalekalimi().length() < 4) {
            throw new Exception("Fjalekalimi duhet te kete te pakten 4 karaktere.");
        }
        if (dto.getRoli() == null || dto.getRoli().trim().isEmpty()) {
            throw new Exception("Roli i perdoruesit eshte i detyrueshem.");
        }
    }

    public boolean existsByUsername(String usernameInput) {
        return perdoruesitRepository.findByUsername(usernameInput) != null;
    }

}

