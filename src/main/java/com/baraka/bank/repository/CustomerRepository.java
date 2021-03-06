package com.baraka.bank.repository;

import com.baraka.bank.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This is repository to perform operations on Customer Table
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 */

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

    public Optional<Customer> findByCustomerNumber(Long customerNumber);

}
