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
public class DepositMoneyResponse {

	private Long accountNumber;
	
	private Double previousAccountBalance;

	private Double updatedAccountBalance;

}
