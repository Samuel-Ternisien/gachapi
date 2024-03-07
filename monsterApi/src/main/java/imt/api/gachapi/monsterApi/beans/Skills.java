package imt.api.gachapi.monsterApi.beans;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Skills {

    private int num;
    private int dmg;
    private Ratio ratio;
    private int cooldown;
    private int lvlMax;
    private int currentLevel;

    @Override
    public String toString() {
        return "Skills{" +
                "num=" + num +
                ", dmg=" + dmg +
                ", ratio=" + ratio +
                ", cooldown=" + cooldown +
                ", lvlMax=" + lvlMax +
                ", currentLevel=" + currentLevel +
                '}';
    }
}
