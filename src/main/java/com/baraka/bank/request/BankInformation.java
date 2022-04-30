package com.baraka.bank.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BankInformation {

	private String branchName;
	
	private Integer branchCode;
	
	private AddressDetails branchAddress;
	
	private Integer routingNumber;
}
