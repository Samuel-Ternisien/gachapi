package imt.api.player.repositories;


import imt.api.player.player.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, Integer> {
    Player findPlayerById(int id);
}
