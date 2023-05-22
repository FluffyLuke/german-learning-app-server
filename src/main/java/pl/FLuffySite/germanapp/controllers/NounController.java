package pl.FLuffySite.germanapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.FLuffySite.germanapp.models.NounModel;
import pl.FLuffySite.germanapp.servises.LogerService;
import pl.FLuffySite.germanapp.servises.NounService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/noun")
public class NounController {

    @Autowired
    private NounService nounService;
    @Autowired
    private LogerService loger;


    @GetMapping(value = "/get-all")
    public ResponseEntity<List<NounModel>> getNouns(){
        return new ResponseEntity<>(nounService.getAllNouns(), HttpStatus.OK);
    }
    @GetMapping(value="/get-some{number}")
    public ResponseEntity<List<NounModel>> getNouns(@RequestParam int number){
        return new ResponseEntity<>(nounService.getSomeNouns(number), HttpStatus.OK);
    }
    @GetMapping(value="/get-one{id}")
    public ResponseEntity<NounModel> getNouns(@RequestParam String id){
        return new ResponseEntity<>(nounService.getNounById(id), HttpStatus.OK);
    }

    @PostMapping(value="/post-one")
    public ResponseEntity<String> saveNoun(@RequestBody NounModel nounModel){
            if(nounModel.getId() != null){
                nounModel.setId(null);
            }
            if(!nounService.checkIfNounExistsByNoun(nounModel.getNoun())){
                return new ResponseEntity<>("NOUN ALREADY IN DATABASE", HttpStatus.BAD_REQUEST);
            }
            nounService.saveNoun(nounModel);
            loger.saveLog("sent a noun", nounModel.getPerson());
            return new ResponseEntity<>("NOUN SAVED", HttpStatus.CREATED);
    }
//    @PostMapping(value = "/post-many")
//    public ResponseEntity<String> saveNoun(@RequestBody List<NounModel> nounModel){
//        for (NounModel x: nounModel) {
//            x.setDate(LocalDate.now());
//            nounService.saveNoun(x);
//        }
//        return new ResponseEntity<String>("NOUNS SAVED", HttpStatus.CREATED);
//    }

    @PutMapping(value = "/update-one")
    public ResponseEntity<String> updateNoun(@RequestBody NounModel nounModel){

        if(nounModel.getId() == null){
            return new ResponseEntity<>("NOUN NOT FOUND", HttpStatus.NOT_FOUND);
        }

        if(!nounService.checkIfNounExistsById(nounModel.getId())){
            return new ResponseEntity<>("NOUN ID NOT FOUND", HttpStatus.NOT_FOUND);
        }

        if(!nounService.checkIfNounExistsByNoun(nounModel.getNoun())){
            if(!nounService.getNounByNoun(nounModel.getNoun()).getId().equals(nounModel.getId())){
                return new ResponseEntity<>("NOUN WITH THE SAME NAME EXIST", HttpStatus.BAD_REQUEST);
            }
        }
        nounService.saveNoun(nounModel);
        loger.saveLog("updated a noun", nounModel.getPerson());
        return new ResponseEntity<>("NOUN UPDATED", HttpStatus.OK);
    }


    @PutMapping(value = "/delete-one")
    public ResponseEntity<String> deleteNoun(@RequestParam String id, @RequestParam String person){

        if(id == null || id == ""){
            return new ResponseEntity<>("ID CANNOT BE EMPTY", HttpStatus.BAD_REQUEST);
        }
        if(person == null || person == ""){
            return new ResponseEntity<>("PERSON CANNOT BE EMPTY", HttpStatus.BAD_REQUEST);
        }
        if(!nounService.checkIfNounExistsById(id)){
            return new ResponseEntity<>("NOUN NOT FOUND", HttpStatus.NOT_FOUND);
        }
        nounService.deleteNoun(id);
        loger.saveLog("updated a noun", person);
        return new ResponseEntity<>("NOUN DELETED", HttpStatus.OK);
    }

}
