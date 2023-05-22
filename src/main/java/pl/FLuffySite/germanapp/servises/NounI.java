package pl.FLuffySite.germanapp.servises;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.FLuffySite.germanapp.models.NounModel;

import java.util.List;

@Repository
public interface NounI extends MongoRepository<NounModel, String> {
    @Aggregation(pipeline = {
            "{'$sort' :  {'date' :  -1}}",
            "{'$limit' : ?0}"
    })
    List<NounModel> findSomeNouns(int numberOfDocuments);

    NounModel findNounModelByNoun(String noun);

    NounModel findNounModelById(String id);

}
