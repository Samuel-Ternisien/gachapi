package imt.api.player.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import imt.api.player.player.Player;

public interface PlayerRepository extends MongoRepository<Player, String> {
    Player findPlayerById(String id);
}
