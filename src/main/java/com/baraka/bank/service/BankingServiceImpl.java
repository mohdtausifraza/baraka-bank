package com.baraka.bank.service;

import com.baraka.bank.entity.Account;
import com.baraka.bank.entity.Transaction;
import com.baraka.bank.mapper.ResponseMapper;
import com.baraka.bank.repository.AccountRepository;
import com.baraka.bank.repository.CustomerRepository;
import com.baraka.bank.repository.TransactionRepository;
import com.baraka.bank.request.*;
import com.baraka.bank.response.CreateAccountResponse;
import com.baraka.bank.response.DeleteAccountResponse;
import com.baraka.bank.service.helper.BankingServiceHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class BankingServiceImpl implements IBankingService {


    @Autowired
    private ResponseMapper responseMapper;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private BankingServiceHelper bankingServiceHelper;

    public BankingServiceImpl(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    /**
     * CREATE New Account
     *
     * @param accountInformation
     * @return
     */
    public ResponseEntity<CreateAccountResponse> createAccount(AccountInformation accountInformation) {

        log.info("Creating Account Entity from Account Information");
        Account account = bankingServiceHelper.convertToAccountEntity(accountInformation);
        account.setCreateDateTime(new Date());
        try {
            account = accountRepository.save(account);
        } catch (Exception e) {
            log.info("Exception occurred while saving the AccountInformation:{}", accountInformation);
        }
        CreateAccountResponse createAccountResponse = responseMapper.getAccountDetails(account);
        log.info("Account Created Successfully with Response : {}", createAccountResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(createAccountResponse);
    }

    /**
     * Deposit Money to account
     *
     * @param depositDetails
     * @return
     */
    public ResponseEntity<Object> depositMoney(DepositDetails depositDetails) {

        Account toAccountEntity = null;

        // Get ACCOUNT info Info where we need to deposit money
        log.info("Getting the Details of the AccountNumber : {}", depositDetails.getToAccountNumber());
        Optional<Account> toAccountEntityOpt = accountRepository.findByAccountNumber(depositDetails.getToAccountNumber());
        if (toAccountEntityOpt.isPresent()) {
            log.info("AccountNumber : {} is found", depositDetails.getToAccountNumber());
            toAccountEntity = toAccountEntityOpt.get();
        } else {
            // If Account where we want to deposit money not exist, 404 Bad Request
            log.info("AccountNumber : {} is found", depositDetails.getToAccountNumber());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Number:" + depositDetails.getToAccountNumber() + " not found.");
        }

        // Depositing money to the account
        synchronized (this) {
            // update TO ACCOUNT
            toAccountEntity.setAccountBalance(toAccountEntity.getAccountBalance() + depositDetails.getDepositAmount());
            toAccountEntity.setUpdateDateTime(new Date());

            try {
                accountRepository.save(toAccountEntity);
            } catch (Exception e) {
                log.info("Exception occurred while saving the details {}", e.getStackTrace());
            }
            // Create transaction for Deposit to Account
            Transaction toTransaction = bankingServiceHelper.createDepositTransaction(depositDetails, toAccountEntity.getAccountNumber(), "CREDIT");
            transactionRepository.save(toTransaction);
        }
        return ResponseEntity.status(HttpStatus.OK).body("Success: Amount:" + depositDetails.getDepositAmount() + "Deposited to AccountNumber " + toAccountEntity.getAccountNumber());
    }


    /**
     * Withdraw Money from account for a specific customer
     *
     * @param withdrawalDetails
     * @return
     */
    public ResponseEntity<Object> withdrawMoney(WithdrawalDetails withdrawalDetails) {

        Account fromAccountEntity = null;

        // Get ACCOUNT info Info from where we need to withdraw money
        log.info("Getting the Details of the AccountNumber : {}", withdrawalDetails.getFromAccountNumber());
        Optional<Account> toAccountEntityOpt = accountRepository.findByAccountNumber(withdrawalDetails.getFromAccountNumber());
        if (toAccountEntityOpt.isPresent()) {
            log.info("AccountNumber : {} is found", withdrawalDetails.getFromAccountNumber());
            fromAccountEntity = toAccountEntityOpt.get();
        } else {
            // If Account where we want to deposit money not exist, 404 Bad Request
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account Number:" + withdrawalDetails.getFromAccountNumber() + " not found.");
        }

        // Withdrawing money from the account
        // Check if the account has sufficient money for withdrawal, else return 400 Bad Request
        if (fromAccountEntity.getAccountBalance() < withdrawalDetails.getWithdrawalAmount()) {
            log.info("Account is having Insufficient Amount");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Funds.");
        } else {
            synchronized (this) {
                // Update the account for the withdrawal
                fromAccountEntity.setAccountBalance(fromAccountEntity.getAccountBalance() - withdrawalDetails.getWithdrawalAmount());
                fromAccountEntity.setUpdateDateTime(new Date());

                try {
                    accountRepository.save(fromAccountEntity);
                } catch (Exception e) {
                    log.info("Exception occurred while saving the details {}", e.getStackTrace());
                }

                // Create transaction for Withdrawal from Account
                Transaction fromTransaction = bankingServiceHelper.createWithdrawalTransaction(withdrawalDetails, fromAccountEntity.getAccountNumber(), "DEBIT");
                transactionRepository.save(fromTransaction);

            }
            return ResponseEntity.status(HttpStatus.OK).body("Success: Amount withdrawal for AccountNumber:" + fromAccountEntity.getAccountNumber());
        }
    }

    /**
     * Transfer money from one account to another of the Same Bank.
     *
     * @param transferDetails
     * @return
     */
    public ResponseEntity<Object> transferMoney(TransferDetails transferDetails) {

        List<Account> accountEntities = new ArrayList<>();
        Account fromAccountEntity = null;
        Account toAccountEntity = null;


        // get FROM ACCOUNT info
        Optional<Account> fromAccountEntityOpt = accountRepository.findByAccountNumber(transferDetails.getFromAccountNumber());
        if (fromAccountEntityOpt.isPresent()) {
            fromAccountEntity = fromAccountEntityOpt.get();
        } else {
            // if from request does not exist, 404 Bad Request
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("From AccountNumber: " + transferDetails.getFromAccountNumber() + " not found.");
        }


        // get TO ACCOUNT info
        Optional<Account> toAccountEntityOpt = accountRepository.findByAccountNumber(transferDetails.getToAccountNumber());
        if (toAccountEntityOpt.isPresent()) {
            toAccountEntity = toAccountEntityOpt.get();
        } else {
            // if from request does not exist, 404 Bad Request
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("To AccountNumber: " + transferDetails.getToAccountNumber() + " not found.");
        }


        // if not sufficient funds, return 400 Bad Request
        if (fromAccountEntity.getAccountBalance() < transferDetails.getTransferAmount()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Funds.");
        } else {
            synchronized (this) {
                // update FROM ACCOUNT
                fromAccountEntity.setAccountBalance(fromAccountEntity.getAccountBalance() - transferDetails.getTransferAmount());
                fromAccountEntity.setUpdateDateTime(new Date());
                accountEntities.add(fromAccountEntity);

                // update TO ACCOUNT
                toAccountEntity.setAccountBalance(toAccountEntity.getAccountBalance() + transferDetails.getTransferAmount());
                toAccountEntity.setUpdateDateTime(new Date());
                accountEntities.add(toAccountEntity);

                accountRepository.saveAll(accountEntities);

                // Create transaction for FROM Account
                Transaction fromTransaction = bankingServiceHelper.createTransaction(transferDetails, fromAccountEntity.getAccountNumber(), "DEBIT");
                transactionRepository.save(fromTransaction);

                // Create transaction for TO Account
                Transaction toTransaction = bankingServiceHelper.createTransaction(transferDetails, toAccountEntity.getAccountNumber(), "CREDIT");
                transactionRepository.save(toTransaction);
            }

            return ResponseEntity.status(HttpStatus.OK).body("Success: Amount transferred FROM AccountNumber: " + transferDetails.getFromAccountNumber() + " TO AccountNumber:" + transferDetails.getToAccountNumber());
        }

    }


    /**
     * Transfer money from one account to another of Different Bank or International Bank.
     *
     * @param transferDetails
     * @return
     */
    public ResponseEntity<Object> transferMoneyToInternationalAccount(InternationalTransferDetails transferDetails) {

        Account fromAccountEntity;

        // get FROM ACCOUNT info
        Optional<Account> fromAccountEntityOpt = accountRepository.findByAccountNumber(transferDetails.getFromAccountNumber());
        if (fromAccountEntityOpt.isPresent()) {
            fromAccountEntity = fromAccountEntityOpt.get();
        } else {
            // if from request does not exist, 404 Bad Request
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("From AccountNumber: " + transferDetails.getFromAccountNumber() + " not found.");
        }


        // Get The Details of the International Account Using The Details in the InternationalToAccountDetails
        // Here we will be using some Switch of Acquirer Like HEDC, ICICI to get the details of the Foreign Account

        // if From Account is not having sufficient funds, return 400 Bad Request
        if (fromAccountEntity.getAccountBalance() < transferDetails.getTransferAmount()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient Funds.");
        } else {
            synchronized (this) {
                // update FROM ACCOUNT
                fromAccountEntity.setAccountBalance(fromAccountEntity.getAccountBalance() - transferDetails.getTransferAmount());
                fromAccountEntity.setUpdateDateTime(new Date());
                accountRepository.save(fromAccountEntity);

                // update TO ACCOUNT
                // Here we will be using Some Switch or Acquirer Like HEDC, ICICI, who will Actually do the Transfer using NCPI


                // Create transaction for FROM Account
                Transaction fromTransaction = bankingServiceHelper.createInternationalTransaction(transferDetails, fromAccountEntity.getAccountNumber(), "DEBIT");
                transactionRepository.save(fromTransaction);

            }

            return ResponseEntity.status(HttpStatus.OK).body("Success: Amount transferred FROM AccountNumber: " + transferDetails.getFromAccountNumber() + " TO AccountNumber:" + transferDetails.getToAccountNumber());
        }

    }


    /**
     * Get Balance of a Specific Account Number.
     *
     * @param accountNumber
     * @return
     */
    public ResponseEntity<Object> getBalance(Long accountNumber) {

        Account fromAccountEntity;

        // Get ACCOUNT Details
        Optional<Account> fromAccountEntityOpt = accountRepository.findByAccountNumber(accountNumber);
        if (fromAccountEntityOpt.isPresent()) {
            fromAccountEntity = fromAccountEntityOpt.get();
        } else {
            // If Account Does not present Give 404 Bad Request
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AccountNumber: " + accountNumber + " not found.");
        }

        // if From Account is not having sufficient funds, return 400 Bad Request
        return ResponseEntity.status(HttpStatus.OK).body("Account Balance:" + fromAccountEntity.getAccountBalance());

    }

    /**
     * Get Balance of a Specific Account Number.
     *
     * @param accountNumber
     * @return
     */
    public ResponseEntity<Object> deleteAccount(@PathVariable Long accountNumber) {

        log.info("Getting the details of the AccountNumber : {}", accountNumber);
        Optional<Account> managedAccountEntityOpt = accountRepository.findByAccountNumber(accountNumber);

        Account managedAccountEntity;
        if (managedAccountEntityOpt.isPresent()) {
            log.info("Account Details Found");
            managedAccountEntity = managedAccountEntityOpt.get();
            try {
                log.info("Deleting The AccountNumber : {}", accountNumber);
                accountRepository.delete(managedAccountEntity);
            } catch (Exception e) {
                log.info("Exception occurred while deleting the AccountNumber: {}", accountNumber);
            }
            DeleteAccountResponse response = responseMapper.getDeleteAccountResponse(managedAccountEntity);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            log.info("Account Details Not Found for AccountNumber : {}", accountNumber);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account does not exist.");
        }
    }


    /**
     * Get List of all the Accounts.
     *
     * @return
     */
    public List<AccountInformation> findAll() {

        log.info("Get List of Account details Request");
        List<AccountInformation> allAccountInformations = new ArrayList<>();
        Iterable<Account> accountsList = null;
        try {
            log.info("Getting The List of Account Details");
            accountsList = accountRepository.findAll();
        } catch (Exception e) {
            log.info("Exception Occured while getting the list of account details");
        }
        if (accountsList != null) {
            accountsList.forEach(account -> {
                allAccountInformations.add(bankingServiceHelper.convertToAccountDomain(account));
            });
        }
        return allAccountInformations;
    }
}
