package imt.api.gachapi.services;

import imt.api.gachapi.beans.Account;
import imt.api.gachapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    public Account save(Account account){
        return this.accountRepository.save(account);
    }
}
