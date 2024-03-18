package imt.api.player.services;

import imt.api.player.controllers.PlayerController;
import imt.api.player.repositories.PlayerRepository;
import imt.api.player.player.Monster;
import imt.api.player.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayerInfo(int id) {
        return playerRepository.findPlayerById(id);
    }

    public List<Monster> getMonstersOfPlayer(int id) {
        Player player = playerRepository.findPlayerById(id);
        return player.getMonsters();
    }

    public int getPlayerLevel(int id) {
        Player player = playerRepository.findPlayerById(id);
        return player.getLevel();
    }

    public Player gainExperience(int id, double experience) {
        Player player = playerRepository.findPlayerById(id);
        double currentExperience = player.getXp();
        player.setXp(currentExperience + experience);
        return playerRepository.save(player);
    }

    public Player gainLevel(int id) {
        Player player = playerRepository.findPlayerById(id);
        int currentLevel = player.getLevel();
        player.setLevel(currentLevel + 1);
        return playerRepository.save(player);
    }

    public Player addNewMonster(int id, Monster monster) {
        Player player = playerRepository.findPlayerById(id);
        player.getMonsters().add(monster);
        return playerRepository.save(player);
    }


    public Player deleteMonster(int id, int monsterId) {
        Player player = playerRepository.findPlayerById(id);
        player.getMonsters().removeIf(monster -> monster.getId() == monsterId);
        return playerRepository.save(player);
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

}
