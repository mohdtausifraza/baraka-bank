package com.baraka.bank.repository;

import java.util.List;
import java.util.Optional;

import com.baraka.bank.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * This is repository to perform operations on Transaction Table
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 *
 */

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {

    public Optional<List<Transaction>> findByAccountNumber(Long accountNumber);
    
}
