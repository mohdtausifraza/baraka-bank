package com.baraka.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * This is an Entity Mapping the Transaction Table
 * This Table will be used to Store Transaction Details
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 */

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TRANSACTION_ID")
    private UUID id;

    private Long accountNumber;

    @Temporal(TemporalType.TIME)
    private Date txDateTime;

    private String txType;

    private Double txAmount;
}
