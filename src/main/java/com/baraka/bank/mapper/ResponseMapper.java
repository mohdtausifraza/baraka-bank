package com.baraka.bank.mapper;

import com.baraka.bank.entity.Account;
import com.baraka.bank.response.CreateAccountResponse;
import com.baraka.bank.response.DeleteAccountResponse;
import com.baraka.bank.service.helper.BankingServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    @Autowired
    public BankingServiceHelper bankingServiceHelper;

    public CreateAccountResponse getAccountDetails(Account account){
        return CreateAccountResponse.builder()
                .accountBalance(account.getAccountBalance())
                .accountCreated(account.getCreateDateTime())
                .accountNumber(account.getAccountNumber())
                .accountStatus(account.getAccountStatus())
                .accountType(account.getAccountType())
                .bankInformation(bankingServiceHelper.convertToBankInfoDomain(account.getBankInformation()))
                .customerDetails(bankingServiceHelper.convertToCustomerDomain(account.getCustomerInformation()))
                .build();
    }

    public DeleteAccountResponse getDeleteAccountResponse(Account account){
        return DeleteAccountResponse.builder()
                .accountBalance(account.getAccountBalance())
                .accountCreated(account.getCreateDateTime())
                .accountNumber(account.getAccountNumber())
                .accountStatus(account.getAccountStatus())
                .accountType(account.getAccountType())
                .bankInformation(bankingServiceHelper.convertToBankInfoDomain(account.getBankInformation()))
                .customerDetails(bankingServiceHelper.convertToCustomerDomain(account.getCustomerInformation()))
                .build();
    }
}
