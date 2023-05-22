package pl.FLuffySite.germanapp.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.FLuffySite.germanapp.models.NounModel;


import java.time.LocalDate;
import java.util.List;


@Service
public class NounService {

    @Autowired
    NounI nounRepo;

    public List<NounModel> getAllNouns(){
        return nounRepo.findAll();
    }

    public NounModel getNounById(String id){ return nounRepo.findNounModelById(id);}

    public NounModel getNounByNoun(String noun){ return nounRepo.findNounModelByNoun(noun);}

    public List<NounModel> getSomeNouns(int numberOfNouns){
        return nounRepo.findSomeNouns(numberOfNouns);
    }


    public boolean checkIfNounExistsByNoun(String nounName){
        if(nounRepo.findNounModelByNoun(nounName) != null){
            return false;
        }
        return true;
    }
    public boolean checkIfNounExistsById(String id){
        if(nounRepo.findById(id).isEmpty()){
            return false;
        }
        return true;
    }
    public void saveNoun(NounModel noun){
        noun.setDate(LocalDate.now());
        nounRepo.save(noun);
    }

    public void deleteNoun(String id){
        nounRepo.deleteById(id);
    }



}
