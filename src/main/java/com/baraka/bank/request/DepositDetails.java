package com.baraka.bank.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DepositDetails {
	
	private Long toAccountNumber;
	
	private Double depositAmount;
}
