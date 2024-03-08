package imt.api.account.repositories;

import imt.api.account.beans.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account,Integer> {
    Account findAccountByUsername(String username);
}
