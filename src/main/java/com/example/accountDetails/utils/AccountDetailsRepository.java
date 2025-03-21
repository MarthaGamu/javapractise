package com.example.accountDetails.utils;


import com.example.accountDetails.pojo.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Long> {

    // Find Account Details by first name and last name
    Optional<AccountDetails> findByFirstNameAndLastName(String firstName, String lastName);

    // You don't need custom save, delete, and findAll methods
    // as JpaRepository already provides these operations.
}
