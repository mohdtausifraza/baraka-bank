package com.baraka.bank.request;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TransactionDetails {

    private Long accountNumber;

    private Date txDateTime;

    private String txType;

    private Double txAmount;
}
