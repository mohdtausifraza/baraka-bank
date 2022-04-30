package com.baraka.bank.request;

import java.util.Date;

import lombok.*;

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
