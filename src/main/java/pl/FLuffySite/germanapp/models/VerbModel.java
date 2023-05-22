package pl.FLuffySite.germanapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Document(collection = "verbs")
public class VerbModel extends ModelBase{
    @NotNull
    @JsonProperty("verb")
    private String verb;
    @NotNull
    @JsonProperty("translation")
    private String translation;
    @NotNull
    @JsonProperty("perfect")
    private String perfect;
    @NotNull
    @JsonProperty("imperfect")
    private String imperfect;
    public VerbModel(String id, String verb, String translation, String perfect, String imperfect, String person) {
        super(id, person);
        this.verb = verb;
        this.translation = translation;
        this.perfect = perfect;
        this.imperfect = imperfect;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPerfect() {
        return perfect;
    }

    public void setPerfect(String perfect) {
        this.perfect = perfect;
    }

    public String getImperfect() {
        return imperfect;
    }

    public void setImperfect(String imperfect) {
        this.imperfect = imperfect;
    }
}
