package com.a04.a04.api.controllers;

import com.a04.a04.api.models.Account;
import com.a04.a04.service.AccountDB;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
public class EncryptionController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        Account account = new Account(email, password);
        AccountDB accountDB = new AccountDB();
        boolean success = accountDB.login(account);
        accountDB.close();
        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body("Success.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed.");
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(@RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("age") int age, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Account account = new Account(email, password, age, firstName, lastName);
        AccountDB accountDB = new AccountDB();
        boolean doesUserExist = accountDB.doesUserExist(email);
        if (age < 18) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Must be 18 or older to create an account.");
        } else if (doesUserExist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already attached to other user.");
        }

        boolean success = accountDB.signUp(account);
        accountDB.close();
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Success.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account creation failed.");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteAccount(@RequestParam("email") String email) {
        AccountDB accountDB = new AccountDB();
        boolean success = accountDB.deleteAccount(email);
        if (success) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted account.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deletion failed.");
        }
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "error page";
    }
}

// change schema to only have email, first, last, age, password, primary key is email