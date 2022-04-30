package com.baraka.bank.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransferDetails {

    private Long fromAccountNumber;

    private Long toAccountNumber;

    private Double transferAmount;
}
