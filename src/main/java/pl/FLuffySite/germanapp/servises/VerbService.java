package pl.FLuffySite.germanapp.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.FLuffySite.germanapp.models.VerbModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VerbService {
    @Autowired
    VerbI verbRepo;

    public List<VerbModel> getAllVerbs(){
        return verbRepo.findAll();
    }
    public VerbModel getVerbById(String id){ return verbRepo.findVerbModelById(id);}
    public VerbModel getVerbByVerb(String verb){
        return verbRepo.findVerbModelByVerb(verb);
    }
    public List<VerbModel> getSomeVerbs(int numberOfVerbs){
        return verbRepo.findSomeVerbs(numberOfVerbs);
    }

    public boolean checkIfVerbExistsByVerb(String verb){
        if(verbRepo.findVerbModelByVerb(verb) != null){
            return false;
        }
        return true;
    }
    public boolean checkIfVerbExistsById(String id){
        if(verbRepo.findById(id).isEmpty()){
            return false;
        }
        return true;
    }
    public void saveVerb(VerbModel verb){
        verb.setDate(LocalDate.now());
        verbRepo.save(verb);
    }
    public void deleteVerb(String id){
        verbRepo.deleteById(id);
    }
}
