package com.example.accountDetails.controller;



import com.example.accountDetails.pojo.AccountDetails;
import com.example.accountDetails.services.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountDetailsController {

    @Autowired
    private AccountDetailsService accountDetailsService;

    // Get all account details
    @GetMapping
    public ResponseEntity<List<AccountDetails>> getAllAccountDetails() {
        List<AccountDetails> accountDetailsList = accountDetailsService.getAllAccountDetails();
        return new ResponseEntity<>(accountDetailsList, HttpStatus.OK);
    }

    // Get account details by first name and last name
    @GetMapping("/search")
    public ResponseEntity<AccountDetails> getAccountDetailsByName(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {

        Optional<AccountDetails> accountDetailsOpt = accountDetailsService.getAccountDetailsByName(firstName, lastName);
        return accountDetailsOpt
                .map(accountDetails -> new ResponseEntity<>(accountDetails, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Add a new account holder
    @PostMapping
    public ResponseEntity<AccountDetails> addAccountDetails(@RequestBody AccountDetails accountDetails) {
        AccountDetails savedAccount = accountDetailsService.addAccountDetails(accountDetails);
        return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
    }

    // Edit account details by ID
    @PutMapping("/{id}")
    public ResponseEntity<AccountDetails> editAccountDetails(
            @PathVariable Long id,
            @RequestBody AccountDetails updatedAccountDetails) {

        Optional<AccountDetails> updatedAccountOpt = accountDetailsService.editAccountDetails(id, updatedAccountDetails);
        return updatedAccountOpt
                .map(accountDetails -> new ResponseEntity<>(accountDetails, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete account details by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountDetails(@PathVariable Long id) {
        boolean isDeleted = accountDetailsService.deleteAccountDetails(id);
        return isDeleted
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT) // Successfully deleted
                : new ResponseEntity<>(HttpStatus.NOT_FOUND); // Not found
    }
}
