package com.javaguide.Banking.Application.Service.Impl;

import com.javaguide.Banking.Application.Dto.AccountDto;
import com.javaguide.Banking.Application.Dto.TransferFundDto;
import com.javaguide.Banking.Application.Entity.Account;
import com.javaguide.Banking.Application.Exception.AccountException;
import com.javaguide.Banking.Application.Repository.AccountRepo;
import com.javaguide.Banking.Application.Service.AccountService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountImpl implements AccountService {
    private AccountRepo accountRepo;
    private ModelMapper modelMapper;

    @Override
    public AccountDto createaccount(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        Account saveAccount = accountRepo.save(account);
        AccountDto savedAccountDto = modelMapper.map(saveAccount, AccountDto.class);
        return savedAccountDto;

    }

    @Override
    public AccountDto getAccount(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new AccountException("ID DOES NOT EXIST"));
        return modelMapper.map(account, AccountDto.class);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepo.findAll();
        return accounts.stream().map((account) -> (modelMapper.map(account, AccountDto.class))).collect(Collectors.toUnmodifiableList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new AccountException("ID DOES NOT EXIST"));
        accountRepo.deleteById(id);
    }

    @Override
    public String depositAccount(AccountDto accountDto, Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new AccountException("ID DOES NOT EXIST"));

        account.setBalance(accountDto.getBalance() + account.getBalance());
        return "you have successfully deposit " + accountDto.getBalance();
    }

    @Override
    public String withdrawAccount(AccountDto accountDto, Long id) {
        Account account = accountRepo.findById(id).orElseThrow(() -> new AccountException("ID DOES NOT EXIST"));
        account.setBalance(accountDto.getBalance() - account.getBalance());
        return "THANKU";
    }

    @Override
    public String transferFund(TransferFundDto transferFundDto) {
        Account fromAccount = accountRepo.findById(transferFundDto.fromAccountId())
                .orElseThrow(() -> new AccountException("ID DOES NOT EXIST"));
        Account toAccount = accountRepo.findById(transferFundDto.toAccountId())
                .orElseThrow(() -> new AccountException("ID DOES NOT EXIST"));
        fromAccount.setBalance(fromAccount.getBalance() - transferFundDto.amount());
        toAccount.setBalance(toAccount.getBalance() + transferFundDto.amount());
        accountRepo.save(fromAccount);
        accountRepo.save(toAccount);
        return "FUND TRANSFERED SUCCESSFULLY";
    }
}
