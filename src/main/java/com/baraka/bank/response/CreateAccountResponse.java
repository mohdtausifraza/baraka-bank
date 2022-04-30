package com.baraka.bank.response;

import com.baraka.bank.request.BankInformation;
import com.baraka.bank.request.CustomerDetails;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CreateAccountResponse {

	private Long accountNumber;
	
	private BankInformation bankInformation;

	private CustomerDetails customerDetails;
	
	private String accountStatus;
	
	private String accountType;
	
	private Double accountBalance;
	
	private Date accountCreated;
}
