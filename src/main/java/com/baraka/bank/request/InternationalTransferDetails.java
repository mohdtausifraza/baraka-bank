package com.baraka.bank.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InternationalTransferDetails {

	private Long fromAccountNumber;
	
	private InternationalToAccountDetails toAccountNumber;
	
	private Double transferAmount;
}
