package imt.api.player.player;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Player {
    private int id;
    private int level;
    private double xp;
    private List<Monster> monsters;


    public Player(int id, int level, int xp) {
        this.id = id;
        this.level = level;
        this.xp = xp;
        this.monsters = new ArrayList<>();
    }
}
