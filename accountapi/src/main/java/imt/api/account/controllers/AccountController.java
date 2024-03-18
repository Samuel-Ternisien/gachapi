package imt.api.account.controllers;

import imt.api.account.beans.Account;
import imt.api.account.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public Account postAccount(@RequestBody Account account) {
        return this.accountService.save(account);
    }

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody Account account) throws NoSuchAlgorithmException {
        return this.accountService.auth(account.getUsername(), account.getPassword());
    }

    @PostMapping("/validate/{token}")
    public ResponseEntity<?> validate(@PathVariable String token){
        return this.accountService.validate(token);
    }  
}
