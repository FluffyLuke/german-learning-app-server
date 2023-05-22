package pl.FLuffySite.germanapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.FLuffySite.germanapp.models.LogModel;
import pl.FLuffySite.germanapp.servises.LogerService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/logs")
public class LogerController {

    @Autowired
    private LogerService loger;

    @GetMapping(value="/get-some{number}")
    public ResponseEntity<List<LogModel>> getSomeLogs(@RequestParam int number){
        return new ResponseEntity<>(loger.getNumberOfLogs(number), HttpStatus.OK);
    }

    @GetMapping(value="/get-all")
    public ResponseEntity<List<LogModel>> getSomeLogs(){
        return new ResponseEntity<>(loger.getAllLogs(), HttpStatus.OK);
    }

}
