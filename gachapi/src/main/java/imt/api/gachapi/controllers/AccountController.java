package imt.api.gachapi.controllers;

import imt.api.gachapi.beans.Account;
import imt.api.gachapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
