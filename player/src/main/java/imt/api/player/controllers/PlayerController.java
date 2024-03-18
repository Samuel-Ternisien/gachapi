package imt.api.player.controllers;

import imt.api.player.player.Monster;
import imt.api.player.player.Player;
import imt.api.player.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    public Player getPlayerInfo(@PathVariable int id) {
        return playerService.getPlayerInfo(id);
    }

    @GetMapping("/{id}/monstres")
    public List<Monster> getMonstersOfPlayer(@PathVariable int id) {
        return playerService.getMonstersOfPlayer(id);
    }

    @GetMapping("/{id}/level")
    public int getPlayerLevel(@PathVariable int id) {
        return playerService.getPlayerLevel(id);
    }

    @PostMapping("/{id}/gain-experience")
    public Player gainExperience(@PathVariable int id, @RequestParam double experience) {
        return playerService.gainExperience(id, experience);
    }

    @PostMapping("/{id}/gain-level")
    public Player gainLevel(@PathVariable int id) {
        return playerService.gainLevel(id);
    }

    @PostMapping("/{id}/new-monster")
    public Player addNewMonster(@PathVariable int id, @RequestBody Monster monster) {
        return playerService.addNewMonster(id, monster);
    }


    @DeleteMapping("/{id}/monster/{monsterId}")
    public Player deleteMonster(@PathVariable int id, @PathVariable int monsterId) {
        return playerService.deleteMonster(id, monsterId);
    }

    @PostMapping("/add")
    public Player addPlayer(@RequestBody Player player) {
        return playerService.addPlayer(player);
    }

}
