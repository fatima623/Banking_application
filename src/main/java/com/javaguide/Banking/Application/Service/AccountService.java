package com.javaguide.Banking.Application.Service;

import com.javaguide.Banking.Application.Dto.AccountDto;
import com.javaguide.Banking.Application.Dto.TransferFundDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AccountService
{
    AccountDto createaccount(AccountDto accountDto);
    AccountDto getAccount(Long id);
    List<AccountDto> getAllAccounts();
    void deleteAccount(Long id);
String depositAccount(AccountDto accountDto,Long id);
    String withdrawAccount(AccountDto accountDto,Long id);
    String transferFund(TransferFundDto transferFundDto);
}
