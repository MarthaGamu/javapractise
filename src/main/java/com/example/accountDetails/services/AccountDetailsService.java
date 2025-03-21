package com.example.accountDetails.services;

import com.example.accountDetails.pojo.AccountDetails;
import com.example.accountDetails.utils.AccountDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountDetailsService {

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    // Get all account details
    public List<AccountDetails> getAllAccountDetails() {
        return accountDetailsRepository.findAll();
    }

    // Add a new account holder
    public AccountDetails addAccountDetails(AccountDetails accountDetails) {
        // Save the account and return the saved entity
        return accountDetailsRepository.save(accountDetails);
    }

    // Edit account details by ID
    public Optional<AccountDetails> editAccountDetails(Integer id, AccountDetails updatedAccountDetails) {
        // Retrieve the existing account
        Optional<AccountDetails> existingAccountDetailsOpt = accountDetailsRepository.findById(id);

        if (existingAccountDetailsOpt.isPresent()) {
            AccountDetails existingAccountDetails = existingAccountDetailsOpt.get();

            // Update fields
            existingAccountDetails.setTitle(updatedAccountDetails.getTitle());
            existingAccountDetails.setFirstName(updatedAccountDetails.getFirstName());
            existingAccountDetails.setLastName(updatedAccountDetails.getLastName());
            existingAccountDetails.setDob(updatedAccountDetails.getDob());
            existingAccountDetails.setEditMode(updatedAccountDetails.isEditMode());

            // Save and return the updated entity
            return Optional.of(accountDetailsRepository.save(existingAccountDetails));
        } else {
            throw new EntityNotFoundException("AccountDetails not found with ID: " + id);
        }
    }

    // Delete account details by ID
    public boolean deleteAccountDetails(Integer id) {
        if (accountDetailsRepository.existsById(id)) {
            accountDetailsRepository.deleteById(id);
            return true; // Return true if successfully deleted
        }
        return false; // Return false if the ID was not found
    }

    // Get account details by first name and last name
    public Optional<AccountDetails> getAccountDetailsByName(String firstName, String lastName) {
        return accountDetailsRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
