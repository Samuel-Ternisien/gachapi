package imt.api.account.services;

import imt.api.account.beans.Account;
import imt.api.account.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public Account getByUsername(String username)  {
        return this.accountRepository.findAccountByUsername(username);
    }
    
    public Account getByToken(String token){
        return this.accountRepository.findAccountByToken(token);
    } 

    public ResponseEntity<?> auth(String username, String password) throws NoSuchAlgorithmException {
        Account account=getByUsername(username);
        if(account.checkCredential(password)){
            String token = account.generateToken();
            account.setToken(token);
            account.setTokenExpiry(LocalDateTime.now().plusHours(1));
            this.accountRepository.save(account);
            return ResponseEntity.status(200).body(token);
        }
        return ResponseEntity.status(401).body("Invalid password or user");
    }

    public ResponseEntity<?> validate(String token){
        Account account = getByToken(token);
        if (account != null){
            return ResponseEntity.status(200).body(account.getUsername());
        }
        return ResponseEntity.status(401).body("Invalid Token");
    }
}
