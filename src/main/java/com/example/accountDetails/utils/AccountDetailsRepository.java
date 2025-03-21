package com.example.accountDetails.utils;

import com.example.accountDetails.pojo.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDetailsRepository extends JpaRepository<AccountDetails, Integer> {

    // Custom query to find an account by first name and last name
    Optional<AccountDetails> findByFirstNameAndLastName(String firstName, String lastName);

    // Additional custom queries can be added as needed
}
