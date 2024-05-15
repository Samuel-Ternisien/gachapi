package imt.api.gachapi.monsterApi.controller;


import imt.api.gachapi.monsterApi.beans.Monster;
import imt.api.gachapi.monsterApi.beans.Skills;
import imt.api.gachapi.monsterApi.service.MonsterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monster")
public class MonsterController {


    private final MonsterService monsterService;

    @Autowired
    public MonsterController(MonsterService monsterService) {
        this.monsterService = monsterService;
    }

    @GetMapping("/player/{playerId}")
    public List<Monster> findAllMonsterById(@PathVariable String playerId){
        return monsterService.findAllMonsterByPlayerId(playerId);
    }

    @GetMapping("/{monsterId}")
    public Monster findMonster(@PathVariable String monsterId){
        return monsterService.findMonsterById(monsterId);
    }

    @GetMapping
    public List<Monster> findAllMonster(){
        return monsterService.findAllMonster();
    }

    @PutMapping("/{id}/xp")
    public Monster addXpToMonster(@PathVariable String id,@RequestParam double xp ){
        return monsterService.addXpToMonster(id,xp);
    }

    @PutMapping("/{id}/comp")
    public Monster upgradeMonsterCompetence(@PathVariable String id, @RequestParam int skillNum){
        return monsterService.upgradeCompetence(id,skillNum);
    }

    @GetMapping("/{id}/skill")
    public Skills findSkillById(@PathVariable String id, @RequestParam int skillNum){
        return monsterService.findSkillById(id, skillNum);
    }

    @PutMapping("/{id}/addPlayer/{playerId}")
    public Monster addPlayerToMonster(@PathVariable String id, @PathVariable String playerId){
        return monsterService.addPlayerToMonster(id, playerId);
    }

    @PutMapping
    public Monster addMonster(@RequestBody Monster monster){
        return monsterService.save(monster);
    }
}
