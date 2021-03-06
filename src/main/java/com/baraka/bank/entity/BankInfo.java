package com.baraka.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * This is an Entity Mapping the BankInfo Table
 * This Table will be used to Store Transaction Details
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class BankInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BANK_ID")
    private UUID id;

    private String branchName;

    private Integer branchCode;

    @OneToOne(cascade = CascadeType.ALL)
    private Address branchAddress;

    private Integer routingNumber;

}
