package imt.api.player.controllers;

import imt.api.player.player.Player;
import imt.api.player.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;
    @Autowired
    private final Environment env;

    private final RestTemplate restTemplate;

    @Autowired
    public PlayerController(PlayerService playerService, Environment env) {
        this.playerService = playerService;
        this.env = env;
        restTemplate = new RestTemplate();
    }

    public String validateToken(String token) {
        // Define url
        String url = String.format("%s/validate/%s", env.getProperty("urls.authentification"), token);

        // Make a POST request and retrieve the response
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            }
        } catch (Exception e) {
            return "Invalid credentials";
        }
        return "Invalid credentials";
    }

    @GetMapping("/{id}")
    public Player getPlayerInfo(@RequestHeader String token, @PathVariable String id) {
        String res = validateToken(token);
        if (res.equals("Invalid credentials")) return null;
        return playerService.getPlayerInfo(id);
    }

    @GetMapping("/{id}/monstres")
    public List<String> getMonstersOfPlayer(@RequestHeader String token, @PathVariable String id) {
        String res = validateToken(token);
        if (res.equals("Invalid credentials")) return null;
        return playerService.getMonstersOfPlayer(id);
    }

    @GetMapping("/{id}/level")
    public int getPlayerLevel(@PathVariable String id) {
        return playerService.getPlayerLevel(id);
    }

    @PostMapping("/{id}/gain-experience")
    public Player gainExperience(@PathVariable String id, @RequestParam double experience) {
        return playerService.gainExperience(id, experience);
    }

    @PostMapping("/{id}/gain-level")
    public Player gainLevel(@PathVariable String id) {
        return playerService.gainLevel(id);
    }

    @PutMapping("/{id}/new-monster/{monsterId}")
    public Player addNewMonster(@PathVariable String id, @PathVariable String monsterId) {
        // Define url
        String url = String.format("%s/%s/addPlayer/%s", env.getProperty("urls.monster"), monsterId, id);

        // Make a PUT request and retrieve the response
        ResponseEntity<Void> response = restTemplate.exchange(url, HttpMethod.PUT, null, Void.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Player added successfully");
        }
        else{
            System.out.println("Failed to add player");
        }
        return playerService.addNewMonster(id, monsterId);
    }


    @DeleteMapping("/{id}/monster/{monsterId}")
    public Player deleteMonster(@PathVariable String id, @PathVariable String monsterId) {
        return playerService.deleteMonster(id, monsterId);
    }

    @PostMapping("/add")
    public Player addPlayer(@RequestBody Player player) {
        return playerService.addPlayer(player);
    }

}
