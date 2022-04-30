package com.baraka.bank.controller;

import com.baraka.bank.request.DepositDetails;
import com.baraka.bank.request.InternationalTransferDetails;
import com.baraka.bank.request.TransferDetails;
import com.baraka.bank.request.WithdrawalDetails;
import com.baraka.bank.service.BankingServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This Controller will be used to do Transactional Related Operations.
 * Like Deposit Money from the Given Account Number,
 * Withdraw Money From The Given Account Number,
 * Transfer Money From Given Source AccountNumber To Given destination AccountNumber,
 * Transfer Money to International Account,
 * Get Balance of a Given AccountNumber
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 */

@RestController
@RequestMapping("transactions")
@Api(tags = {"Transactions REST endpoints"})
@Slf4j
public class TransactionController {

    @Autowired
    private BankingServiceImpl bankingService;

    // Money Deposit
    @PutMapping(path = "/deposit/")
    @ApiOperation(value = "Deposit Money", notes = "Deposit Money to accounts.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Object> moneyDeposit(@RequestBody DepositDetails depositDetails) {
        log.info("Money Deposit Request :{}", depositDetails);
        return bankingService.depositMoney(depositDetails);
    }

    // Money Withdrawal
    @PutMapping(path = "/withdrawal/")
    @ApiOperation(value = "Withdraw Money", notes = "Withdraw Money From accounts.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public ResponseEntity<Object> withdrawDeposit(@RequestBody WithdrawalDetails withdrawalDetails) {
        return bankingService.withdrawMoney(withdrawalDetails);
    }

    // Money Transfer
    @PutMapping(path = "/transfer/")
    @ApiOperation(value = "Transfer Money", notes = "Transfer Money between accounts in the same bank")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<Object> transferMoney(@RequestBody TransferDetails transferDetails) {

        return bankingService.transferMoney(transferDetails);
    }

    // International Transfer
    @PutMapping(path = "/international/transfer/")
    @ApiOperation(value = "International Transfer", notes = "Transfer money between accounts of different banks")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<Object> transferInternationMoney(@RequestBody InternationalTransferDetails transferDetails) {

        return bankingService.transferMoneyToInternationalAccount(transferDetails);
    }


    // Getting the Balance
    @GetMapping(path = "/get/balance/{accountNumber}")
    @ApiOperation(value = "Get Balance", notes = "Get the Balance of the Account")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<Object> getBalance(@PathVariable Long accountNumber) {

        return bankingService.getBalance(accountNumber);
    }
}
