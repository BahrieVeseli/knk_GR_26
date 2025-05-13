package Services;

import Repository.TrenatRepository;
import models.Dto.CreateTrenatDto;
import models.Trenat;

public class TrenatServices {
    private TrenatRepository trenatRepository;
    public TrenatServices(){
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
        //...
    }

}
