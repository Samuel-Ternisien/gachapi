package imt.api.SummonsApi.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "summonings")
@Getter
@Setter
public class Summoning {

    @Id
    private String id;
    private String playerId;
    private String monsterId;
    private String nameMonster;
    private String element;
    private int hp;
    private int atk;
    private int def;
    private int vit;
    private int stars;

    // Getters and Setters
}