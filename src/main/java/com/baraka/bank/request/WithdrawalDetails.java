package com.baraka.bank.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WithdrawalDetails {

	private Long fromAccountNumber;
	
	private Double withdrawalAmount;
}
