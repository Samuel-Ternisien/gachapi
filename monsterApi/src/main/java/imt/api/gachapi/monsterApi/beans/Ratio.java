package imt.api.gachapi.monsterApi.beans;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Ratio {

    private String stat;
    private double percent;

    @Override
    public String toString() {
        return "Ratio{" +
                "stat='" + stat + '\'' +
                ", percent=" + percent +
                '}';
    }
}
