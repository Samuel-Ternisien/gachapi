package imt.api.SummonsApi.repository;

import imt.api.SummonsApi.beans.Summoning;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SummoningRepository extends MongoRepository<Summoning, String> {
}
