package pl.FLuffySite.germanapp.servises;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.FLuffySite.germanapp.models.LogModel;
import pl.FLuffySite.germanapp.models.NounModel;

import java.time.LocalDate;
import java.util.List;

@Service
public class LogerService {
    @Autowired
    private LogerI loger;

    public List<LogModel> getAllLogs(){
        return loger.findAll();
    }

    public List<LogModel> getNumberOfLogs(int numberOfLogs){
        return loger.getNumberOfLogs(numberOfLogs);
    }

    public void saveLog(String content, String person){
        loger.save(new LogModel(null, content, person, LocalDate.now()));
        System.out.printf("Log saved. Person: {}, Content: {}", person, content);
    }

}
