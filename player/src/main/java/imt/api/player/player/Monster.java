package imt.api.player.player;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Monster {
    private int id;
    private int level;

    public Monster(int id, int level) {
        this.id = id;
        this.level = level;
    }
}
