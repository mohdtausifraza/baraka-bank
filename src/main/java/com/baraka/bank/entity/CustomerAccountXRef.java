package com.baraka.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * This is an Entity Mapping the CustomerAccountXRef Table
 * This Table will be used to get CustomerDetails with the Account Details.
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerAccountXRef {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CUST_ACC_XREF_ID")
    private UUID id;

    private Long accountNumber;

    private Long customerNumber;

}
