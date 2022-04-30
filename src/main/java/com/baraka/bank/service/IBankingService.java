package com.baraka.bank.service;

import com.baraka.bank.request.*;
import com.baraka.bank.response.CreateAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IBankingService {

    ResponseEntity<Object> depositMoney(DepositDetails depositDetails);

    ResponseEntity<Object> withdrawMoney(WithdrawalDetails withdrawalDetails);

    ResponseEntity<Object> transferMoney(TransferDetails transferDetails);

    ResponseEntity<Object> transferMoneyToInternationalAccount(InternationalTransferDetails transferDetails);

    ResponseEntity<CreateAccountResponse> createAccount(AccountInformation accountInformation);

    ResponseEntity<Object> getBalance(Long accountNumber);

    ResponseEntity<Object> deleteAccount(@PathVariable Long accountNumber);

    List<AccountInformation> findAll();


//    public CustomerDetails findByCustomerNumber(Long customerNumber);
//
//    public ResponseEntity<Object> updateCustomer(CustomerDetails customerDetails, Long customerNumber);
//
//    public ResponseEntity<Object> deleteCustomer(Long customerNumber) ;
//
//    public ResponseEntity<Object> findByAccountNumber(Long accountNumber);
//
//    public ResponseEntity<Object> addNewAccount(AccountInformation accountInformation, Long customerNumber);
//
////    public ResponseEntity<Object> transferDetails(TransferDetails transferDetails, Long customerNumber);
//
//    public List<TransactionDetails> findTransactionsByAccountNumber(Long accountNumber);

}
