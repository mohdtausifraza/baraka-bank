package com.baraka.bank.request;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class AccountInformation {

    private Long accountNumber;

    private BankInformation bankInformation;

    private CustomerDetails customerDetails;

    private String accountStatus;

    private String accountType;

    private Double accountBalance;

    private Date accountCreated;
}
