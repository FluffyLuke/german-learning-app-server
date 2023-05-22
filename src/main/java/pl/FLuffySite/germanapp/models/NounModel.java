package pl.FLuffySite.germanapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Document(collection = "nouns")
public class NounModel extends ModelBase {

    @NotNull
    @JsonProperty("noun")
    private String noun;
    @NotNull
    @JsonProperty("article")
    private Articles article;
    @NotNull
    @JsonProperty("translation")
    private String translation;

    public NounModel(String id, String noun, Articles article, String translation, String person) {
        super(id, person);
        this.noun = noun;
        this.article = article;
        this.translation = translation;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public Articles getArticle() {
        return article;
    }

    public void setArticle(Articles article) {
        this.article = article;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

}
