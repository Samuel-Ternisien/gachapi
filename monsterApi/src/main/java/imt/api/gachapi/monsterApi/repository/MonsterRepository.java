package imt.api.gachapi.monsterApi.repository;

import imt.api.gachapi.monsterApi.beans.Monster;
import imt.api.gachapi.monsterApi.beans.Skills;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MonsterRepository extends MongoRepository<Monster, Integer> {
    List<Monster> findByPlayerId(int playerId);
    Monster findMonsterById(int monsterId);
}
