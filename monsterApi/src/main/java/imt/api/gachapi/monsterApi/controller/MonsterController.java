package imt.api.gachapi.monsterApi.controller;


import imt.api.gachapi.monsterApi.beans.Monster;
import imt.api.gachapi.monsterApi.beans.Skills;
import imt.api.gachapi.monsterApi.service.MonsterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monster")
public class MonsterController {


    private final MonsterService monsterService;

    Logger logger = LoggerFactory.getLogger(MonsterController.class);

    @Autowired
    public MonsterController(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @GetMapping("/player/{playerId}")
    public List<Monster> findAllMonsterById(@PathVariable int playerId){
        return monsterService.findAllMonsterByPlayerId(playerId);
    }

    @GetMapping
    public List<Monster> findAllMonster(){
        return monsterService.findAllMonster();
    }

    @PutMapping("/{id}/xp")
    public Monster addXpToMonster(@PathVariable int id,@RequestParam double xp ){
        return monsterService.addXpToMonster(id,xp);
    }

    @PutMapping("/{id}/comp")
    public Monster upgradeMonsterCompetence(@PathVariable int id, @RequestParam int skillNum){
        return monsterService.upgradeCompetence(id,skillNum);
    }

    @GetMapping("/{id}/skill")
    public Skills findSkillById(@PathVariable int id, @RequestParam int skillNum){
        return monsterService.findSkillById(id, skillNum);
    }
}
