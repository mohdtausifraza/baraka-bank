package com.baraka.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

/**
 * This is an Entity Mapping the Contact Table
 * This Table will be used to Store Contact Details of the Account Holders
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTACT_ID")
    private UUID id;

    private String emailId;

    private String homePhone;

    private String workPhone;

}
