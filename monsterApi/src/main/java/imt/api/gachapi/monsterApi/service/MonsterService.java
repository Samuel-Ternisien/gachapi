package imt.api.gachapi.monsterApi.service;

import imt.api.gachapi.monsterApi.beans.Monster;
import imt.api.gachapi.monsterApi.beans.Skills;
import imt.api.gachapi.monsterApi.repository.MonsterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonsterService {

    private final MonsterRepository monsterRepository;

    Logger logger = LoggerFactory.getLogger(MonsterService.class);

    @Autowired
    public MonsterService(MonsterRepository monsterRepository) {
        this.monsterRepository = monsterRepository;
    }

    public List<Monster> findAllMonsterByPlayerId(int playerId){
        return monsterRepository.findByPlayerId(playerId);
    }

    public List<Monster> findAllMonster(){
        return monsterRepository.findAll();
    }

    public Monster addXpToMonster(int id, double experience){
        Monster monster = monsterRepository.findMonsterById(id);
        double currentXp = monster.getXp();
        monster.setXp(currentXp + experience);
        if (monster.getXp()>100.0){
            monster.setLevel(monster.getLevel()+1);
            monster.setXp(monster.getXp()-100);
            monster.setCompetencePoint(monster.getCompetencePoint()+1);
            monster.setHp(monster.getHp()+10);
            monster.setAtk(monster.getAtk()+10);
            monster.setDef(monster.getDef()+10);
            monster.setVit(monster.getVit()+10);
        }
        return monsterRepository.save(monster);
    }

    public Monster upgradeCompetence(int id, int skillNum){
        Monster monster = monsterRepository.findMonsterById(id);
        Skills skill = findSkillById(id, skillNum);
        monster.getSkills().remove(skillNum-1);
        if(monster.getCompetencePoint()>0){
            if(skill.getCurrentLevel()<skill.getLvlMax()){
                skill.setCurrentLevel(skill.getCurrentLevel()+1);
                skill.setDmg(skill.getDmg()+10);
                skill.getRatio().setPercent(skill.getRatio().getPercent()+10.0);
                monster.setCompetencePoint(monster.getCompetencePoint()-1);
                logger.info("infoooooooooooooo" + skill);
            }
        }
        monster.getSkills().add(skill);
        return monsterRepository.save(monster);
    }

    public Skills findSkillById(int monsterId, int skillNum){
        Monster monster = monsterRepository.findMonsterById(monsterId);
        return monster.getSkills().get(skillNum-1);
    }
}
