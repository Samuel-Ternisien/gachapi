package imt.api.player.player;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document
public class Player {
    @Id
    private String id;
    private String name;
    private int level;
    private double xp;
    private List<String> monsters;
    public Player(String id, String name, int level, double xp) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.xp = xp;
        this.monsters = new ArrayList<>();
    }
}
