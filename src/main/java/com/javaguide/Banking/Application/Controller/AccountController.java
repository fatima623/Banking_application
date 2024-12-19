package com.javaguide.Banking.Application.Controller;

import com.javaguide.Banking.Application.Dto.AccountDto;
import com.javaguide.Banking.Application.Dto.TransferFundDto;
import com.javaguide.Banking.Application.Service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.createaccount(accountDto), HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("id") Long id) {
        return new ResponseEntity<>(accountService.getAccount(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/deposit")
    public ResponseEntity<String> depositAmount(@PathVariable("id") Long id, @RequestBody AccountDto accountDto) {

        return ResponseEntity.ok(accountService.depositAccount(accountDto, id));
    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<String> withdrawAmount(@PathVariable("id") Long id, @RequestBody AccountDto accountDto) {

        return ResponseEntity.ok(accountService.withdrawAccount(accountDto, id));
    }
    @PostMapping("/transfer")
    public ResponseEntity<String> transcation(@RequestBody TransferFundDto transferFundDto) {
        accountService.transferFund(transferFundDto);
        return new ResponseEntity("transfer successful", HttpStatus.OK);
    }
}

