package com.example.accountDetails.services;




import com.example.accountDetails.pojo.AccountDetails;
import com.example.accountDetails.utils.AccountDetailsRepository;
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
        return accountDetailsRepository.save(accountDetails);
    }

    // Edit account details by ID
    public Optional<AccountDetails> editAccountDetails(Long id, AccountDetails updatedAccountDetails) {
        Optional<AccountDetails> existingAccountDetailsOpt = accountDetailsRepository.findById(id);
        if (existingAccountDetailsOpt.isPresent()) {
            AccountDetails existingAccountDetails = existingAccountDetailsOpt.get();
            existingAccountDetails.setTitle(updatedAccountDetails.getTitle());
            existingAccountDetails.setFirstName(updatedAccountDetails.getFirstName());
            existingAccountDetails.setLastName(updatedAccountDetails.getLastName());
            existingAccountDetails.setDob(updatedAccountDetails.getDob());
            existingAccountDetails.setEditMode(updatedAccountDetails.isEditMode());

            // Save and return the updated account
            return Optional.of(accountDetailsRepository.save(existingAccountDetails));
        }
        return Optional.empty(); // Return an empty Optional if the account doesn't exist
    }

    // Delete account details by ID
    public boolean deleteAccountDetails(Long id) {
        if (accountDetailsRepository.existsById(id)) {
            accountDetailsRepository.deleteById(id);
            return true; // Return true if account was deleted
        }
        return false; // Return false if account was not found
    }

    // Get account details by first name and last name
    public Optional<AccountDetails> getAccountDetailsByName(String firstName, String lastName) {
        return accountDetailsRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
