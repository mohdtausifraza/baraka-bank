package com.baraka.bank.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * This is an Entity Mapping the Address Table
 * This Table will be used to Store Addresses.
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADDRESS_ID")
	private UUID id;
	
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;

}
