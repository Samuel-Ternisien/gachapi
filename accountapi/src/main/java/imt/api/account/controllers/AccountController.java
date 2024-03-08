package imt.api.account.controllers;

import imt.api.account.beans.Account;
import imt.api.account.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Account postAccount(@RequestBody Account account){
        return this.accountService.save(account);
    }

    @PostMapping("/login")
    public String auth(@RequestBody Account account) throws NoSuchAlgorithmException {
        return this.accountService.auth(account.getUsername(), account.getPassword());
    }
}
