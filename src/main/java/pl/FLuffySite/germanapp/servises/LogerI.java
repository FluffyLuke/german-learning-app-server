package pl.FLuffySite.germanapp.servises;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.FLuffySite.germanapp.models.LogModel;


import java.util.List;

@Repository
public interface LogerI extends MongoRepository<LogModel, ObjectId> {
    @Aggregation(pipeline = {
            "{'$sort' :  {'date' :  -1}}",
            "{'$limit' : ?0}"
    })
    List<LogModel> getNumberOfLogs(int numberOfLogs);

}
