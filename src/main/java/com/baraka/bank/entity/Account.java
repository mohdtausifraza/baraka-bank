package com.baraka.bank.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * This is an Entity Mapping the Account Table
 * This Table will be used to Store Account Details.
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 *
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACCOUNT_NO")
	private Long accountNumber;

	@OneToOne(cascade=CascadeType.ALL)
	private Customer customerInformation;
	
	@OneToOne(cascade=CascadeType.ALL)
	private BankInfo bankInformation;
	
	private String accountStatus;
	
	private String accountType;
	
	private Double accountBalance;
    
    @Temporal(TemporalType.TIME)
	private Date createDateTime;
	
    @Temporal(TemporalType.TIME)
	private Date updateDateTime;
}
