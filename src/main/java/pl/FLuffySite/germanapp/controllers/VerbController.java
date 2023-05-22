package pl.FLuffySite.germanapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.FLuffySite.germanapp.models.VerbModel;
import pl.FLuffySite.germanapp.servises.LogerService;
import pl.FLuffySite.germanapp.servises.VerbService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/verb")
public class VerbController {

    @Autowired
    private VerbService verbService;
    @Autowired
    private LogerService loger;

    @GetMapping(value = "/get-all")
    public ResponseEntity<List<VerbModel>> getVerbs(){
        return new ResponseEntity<>(verbService.getAllVerbs(), HttpStatus.OK);
    }
    @GetMapping(value="/get-some{number}")
    public ResponseEntity<List<VerbModel>> getverbs(@RequestParam int number){
        return new ResponseEntity<>(verbService.getSomeVerbs(number), HttpStatus.OK);
    }

    @GetMapping(value="/get-one{id}")
    public ResponseEntity<VerbModel> getNouns(@RequestParam String id){
        return new ResponseEntity<>(verbService.getVerbById(id), HttpStatus.OK);
    }

    @PostMapping(value="/post-one")
    public ResponseEntity<String> saveverb(@RequestBody VerbModel verbModel){
        if(verbModel.getId() != null){
            verbModel.setId(null);
        }

        if(!verbService.checkIfVerbExistsByVerb(verbModel.getVerb())){
            return new ResponseEntity<>("VERB ALREADY IN DATABASE", HttpStatus.BAD_REQUEST);
        }
        verbService.saveVerb(verbModel);
        loger.saveLog("sent a verb", verbModel.getPerson());
        return new ResponseEntity<>("VERB SAVED", HttpStatus.CREATED);
    }

    @PutMapping(value = "/update-one")
    public ResponseEntity<String> updateVerb(@RequestBody VerbModel verbModel){

        if(verbModel.getId() == null){
            return new ResponseEntity<>("VERB NOT FOUND", HttpStatus.NOT_FOUND);
        }

        if(!verbService.checkIfVerbExistsById(verbModel.getId())){
            return new ResponseEntity<>("VERB ID NOT FOUND", HttpStatus.NOT_FOUND);
        }

        if(!verbService.checkIfVerbExistsByVerb(verbModel.getVerb())){
            if(!verbService.getVerbByVerb(verbModel.getVerb()).getId().equals(verbModel.getId())){
                return new ResponseEntity<>("VERB WITH THE SAME NAME EXIST", HttpStatus.BAD_REQUEST);
            }
        }
        verbService.saveVerb(verbModel);
        loger.saveLog("updated a verb", verbModel.getPerson());
        return new ResponseEntity<>("VERB UPDATED", HttpStatus.OK);
    }

    @PutMapping(value = "/delete-one")
    public ResponseEntity<String> deleteNoun(@RequestParam String id, @RequestParam String person){

        if(id == null || id.equals("")){
            return new ResponseEntity<>("ID CANNOT BE EMPTY", HttpStatus.NOT_FOUND);
        }
        if(person == null || person == ""){
            return new ResponseEntity<>("PERSON CANNOT BE EMPTY", HttpStatus.BAD_REQUEST);
        }
        if(!verbService.checkIfVerbExistsById(id)){
            return new ResponseEntity<>("VERB NOT FOUND", HttpStatus.NOT_FOUND);
        }
        verbService.deleteVerb(id);
        loger.saveLog("deleted a verb", person);
        return new ResponseEntity<>("VERB DELETED", HttpStatus.OK);
    }
}
