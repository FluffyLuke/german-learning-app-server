package pl.FLuffySite.germanapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Document(collection = "logs")
public class LogModel {

    @Id
    private ObjectId id;
    @NotNull
    @JsonProperty("content")
    private String content;
    @NotNull
    @JsonProperty("person")
    private String person;
    @NotNull
    @JsonProperty("date")
    private LocalDate date;

    public LogModel(){};

    public LogModel(ObjectId id, String content) {
        this.id = id;
        this.content = content;
    }
    public LogModel(ObjectId id, String content, String person, LocalDate date) {
        this.id = id;
        this.content = content;
        this.person = person;
        this.date = date;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

