package imt.api.gachapi.services;

import imt.api.gachapi.beans.Account;
import imt.api.gachapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

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

    public Account getByUsername(String username){
        return this.accountRepository.findAccountByUsername(username);
    }

    public String auth(String username, String password) throws NoSuchAlgorithmException {
        Account account=getByUsername(username);
        if(account.checkCredential(password)){
            String token = account.generateToken();
            account.setToken(token);
            account.setTokenExpiry(LocalDateTime.now().plusHours(1));
            this.accountRepository.save(account);
            return token;
        }
        return "Invalid password";
    }
}
