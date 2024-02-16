package imt.api.gachapi.repositories;

import imt.api.gachapi.beans.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account,Integer> {
}
