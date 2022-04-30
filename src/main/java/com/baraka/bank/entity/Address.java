package com.baraka.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * This is an Entity Mapping the Address Table
 * This Table will be used to Store Addresses.
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADDRESS_ID")
    private UUID id;

    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;

}
