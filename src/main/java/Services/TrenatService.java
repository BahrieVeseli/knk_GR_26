package Services;

import Repository.TrenatRepository;
import models.Dto.CreateTrenatDto;
import models.Trenat;

public class TrenatService {
    private TrenatRepository trenatRepository;
    public TrenatService(){
        this.trenatRepository = new TrenatRepository();
    }
    public Trenat getById(int id) throws Exception{
        if(id <= 0){
            throw new Exception("Id does not exist!");
        }
        Trenat trenat = this.trenatRepository.getById(id);
        if(trenat == null){
            throw new Exception("Trenat with Id: " + id + " does not exist!");
        }
        return trenat;
    }

    public Trenat create(CreateTrenatDto createTrenat) throws Exception{
        if (createTrenat == null) {
            throw new Exception("Te dhenat per krijimin e trenit nuk jane te vlefshme!");
        }

        if (createTrenat.getEmriTrenti() == null || createTrenat.getEmriTrenti().isBlank()) {
            throw new Exception("Emri i trenit nuk mund te jete bosh!");
        }

        if (createTrenat.getTipiTrenit() == null || createTrenat.getTipiTrenit().isBlank()) {
            throw new Exception("Tipi i trenit nuk mund te jete bosh!");
        }

        if (createTrenat.getKapacitei() <= 0) {
            throw new Exception("Kapaciteti duhet te jete nje numer pozitiv!");
        }

        Trenat created = this.trenatRepository.create(createTrenat);
        if (created == null) {
            throw new Exception("Krijimi i trenit deshtoi!");
        }

        return created;
    }

}
