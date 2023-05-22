package pl.FLuffySite.germanapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class ModelBase {

    @Id
    private String id;

    @JsonProperty("person")
    @NotNull
    private String person;
    @JsonProperty("date")
    @NotNull
    private LocalDate date;

    public ModelBase(String id) {
        this.id = id;
    }

    public ModelBase(String id, String person) {
        this.id = id;
        this.person = person;
    }
    public ModelBase(String id, String person, LocalDate date) {
        this.id = id;
        this.person = person;
        this.date = date;
    }

    public ModelBase(String person, LocalDate date) {
        this.person = person;
        this.date = date;
    }

    public ModelBase(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
