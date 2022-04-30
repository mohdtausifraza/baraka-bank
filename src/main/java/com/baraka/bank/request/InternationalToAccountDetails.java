package com.baraka.bank.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class InternationalToAccountDetails {

    private Long fromAccountNumber;

    private String bankName;

    private String bankCode;

    private String IfscCode;

    private String bankBranch;

    private Double transferAmount;
}
