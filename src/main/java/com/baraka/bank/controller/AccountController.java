package com.baraka.bank.controller;

import com.baraka.bank.request.AccountInformation;
import com.baraka.bank.response.CreateAccountResponse;
import com.baraka.bank.service.IBankingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This Controller will be used to do Account Related Operations.
 * Like Create New Account, Delete Account, Get List of All Accounts.
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 */

@Slf4j
@RestController
@RequestMapping("accounts")
@Api(tags = {"Create, Delete, and List of Accounts REST endpoints"})
public class AccountController {

    @Autowired
    private IBankingService bankingService;

    // Creation of Account
    @PostMapping(path = "/add")
    @ApiOperation(value = "Create Account", notes = "Create an account")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody AccountInformation accountInformation) {
        log.info("Create Account Request Received AccountInformation : {}", accountInformation);
        return bankingService.createAccount(accountInformation);
    }

    // Deletion of Account
    @DeleteMapping(path = "/{accountNumber}")
    @ApiOperation(value = "Delete Account", notes = "Delete account with the given account number.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = Object.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})

    public ResponseEntity<Object> deleteAccount(@PathVariable Long accountNumber) {
        log.info("Delete Account Request Received for AccountNumber : {}", accountNumber);
        return bankingService.deleteAccount(accountNumber);
    }

    // List of All Accounts
    @GetMapping(path = "/findAllAccounts")
    @ApiOperation(value = "Find all Accounts", notes = "Gets details of all the Accounts")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    public List<AccountInformation> getAllAccounts() {
        return bankingService.findAll();
    }
}
