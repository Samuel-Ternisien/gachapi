package imt.api.gachapi.monsterApi.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "monstreJoueur")
public class Monster {

    @Id
    private String id;
    private String playerId;
    private String nameMonster;
    private int level;
    private double xp;
    private String element;
    private int hp;
    private int atk;
    private int def;
    private int vit;
    private List<Skills> skills;
    private int competencePoint;
}
