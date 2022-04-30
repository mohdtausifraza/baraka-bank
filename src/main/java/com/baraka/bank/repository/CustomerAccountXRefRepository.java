package com.baraka.bank.repository;

import com.baraka.bank.entity.CustomerAccountXRef;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This is repository to perform operations on CustomerAccountXRef Table
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 */

@Repository
public interface CustomerAccountXRefRepository extends CrudRepository<CustomerAccountXRef, String> {

}
