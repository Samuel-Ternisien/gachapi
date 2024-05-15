package imt.api.gachapi.monsterApi.repository;

import imt.api.gachapi.monsterApi.beans.Monster;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MonsterRepository extends MongoRepository<Monster, String> {
    List<Monster> findByPlayerId(String playerId);
    Monster findMonsterById(String monsterId);
}
