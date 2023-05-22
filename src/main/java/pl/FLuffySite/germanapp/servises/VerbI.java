package pl.FLuffySite.germanapp.servises;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.FLuffySite.germanapp.models.VerbModel;

import java.util.List;

@Repository
public interface VerbI extends MongoRepository<VerbModel, String> {
    @Aggregation(pipeline = {
            "{'$sort' :  {'date' :  -1}}",
            "{'$limit' : ?0}"
    })
    List<VerbModel> findSomeVerbs(int numberOfDocuments);

    VerbModel findVerbModelByVerb(String verb);

    VerbModel findVerbModelById(String id);
}
