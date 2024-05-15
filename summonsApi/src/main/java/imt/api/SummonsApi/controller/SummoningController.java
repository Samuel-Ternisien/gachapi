package imt.api.SummonsApi.controller;
import imt.api.SummonsApi.beans.Summoning;
import imt.api.SummonsApi.repository.SummoningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/summons")
public class SummoningController {

    private String monsterAppUrl;

    private String playerAppUrl;
    private String authUrl;

    @Autowired
    private final Environment env;

    private final RestTemplate restTemplate;
    private final SummoningRepository summoningRepository;

    @Autowired
    public SummoningController(SummoningRepository summoningRepository, Environment env) {
        this.restTemplate = new RestTemplate();
        this.env = env;
        playerAppUrl = env.getProperty("urls.player");
        monsterAppUrl = env.getProperty("urls.monster");
        authUrl = env.getProperty("urls.authentification");
        this.summoningRepository = summoningRepository;
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

    @PostMapping("/{playerId}")
    public ResponseEntity<Map> summonMonster(@RequestHeader String token, @PathVariable String playerId) {
        validateToken(token);
        Random random = new Random();

        // Characteristics
        String nameMonster = "Monster_" + random.nextInt(1000);
        String[] elements = {"Fire", "Water", "Earth", "Air", "Lightning"};
        String element = elements[random.nextInt(elements.length)];
        int baseHp = 100;
        int baseAtk = 50;
        int baseDef = 30;
        int baseVit = 20;

        // Weighted random selection for stars
        int[] starWeights = {50, 30, 15, 4, 1}; // Example weights: 1-star is most common, 5-star is rarest
        int stars = WeightedRandom.getWeightedRandom(starWeights);

        // Calculated stats
        int hp = baseHp * stars;
        int atk = baseAtk * stars;
        int def = baseDef * stars;
        int vit = baseVit * stars;

        // Monster creation payload
        Map<String, Object> monsterPayload = new HashMap<>();
        monsterPayload.put("nameMonster", nameMonster);
        monsterPayload.put("element", element);
        monsterPayload.put("hp", hp);
        monsterPayload.put("atk", atk);
        monsterPayload.put("def", def);
        monsterPayload.put("vit", vit);
        monsterPayload.put("stars", stars);

        // Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Map<String, Object>> monsterRequest = new HttpEntity<>(monsterPayload, headers);

        // Create monster
        ResponseEntity<Map> monsterResponse = restTemplate.exchange(
                monsterAppUrl,
                HttpMethod.PUT,
                monsterRequest,
                Map.class
        );

        Map<String, Object> monsterResponseBody = monsterResponse.getBody();
        String monsterId = (String) monsterResponseBody.get("id");

        // Add monster to player
        String playerMonsterUrl = playerAppUrl + "/" + playerId + "/new-monster/" + monsterId;
        HttpEntity<Void> playerRequest = new HttpEntity<>(headers);
        ResponseEntity<String> playerResponse = restTemplate.exchange(playerMonsterUrl, HttpMethod.PUT, playerRequest, String.class);


        // Save summoning details to MongoDB
        Summoning summoning = new Summoning();
        summoning.setPlayerId(playerId);
        summoning.setMonsterId(monsterId);
        summoning.setNameMonster(nameMonster);
        summoning.setElement(element);
        summoning.setHp(hp);
        summoning.setAtk(atk);
        summoning.setDef(def);
        summoning.setVit(vit);
        summoning.setStars(stars);

        summoningRepository.save(summoning);

        return monsterResponse;
    }
}