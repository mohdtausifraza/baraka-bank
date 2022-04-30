package com.baraka.bank.response;

import lombok.*;

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
