package com.baraka.bank.repository;

import java.util.Optional;

import com.baraka.bank.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * This is repository to perform operations on Account Table
 *
 * @author Mohd Tausif Raza
 * @date April 29 2022
 *
 */

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

	Optional<Account> findByAccountNumber(Long accountNumber);
}
