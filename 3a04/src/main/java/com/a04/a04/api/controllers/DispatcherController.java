package com.a04.a04.api.controllers;

import com.a04.a04.api.models.Account;
import com.a04.a04.api.models.Offer;
import com.a04.a04.service.AccountDB;
import com.a04.a04.service.OfferDB;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@RestController()
public class DispatcherController {
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
        accountDB.close();
        if (success) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Deleted account.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deletion failed.");
        }
    }

    @RequestMapping(value = "/offer", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Offer>> getOffers() {
        OfferDB offerDB = new OfferDB();
        ArrayList<Offer> offers = offerDB.getAllOffers();
        offerDB.close();
        if (offers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(offers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/offer/{username}", method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Offer>> getOffersByUsername(@PathVariable("username") String username) {
        OfferDB offerDB = new OfferDB();
        ArrayList<Offer> offers = offerDB.getOffersByUsername(username);
        offerDB.close();
        if (offers != null) {
            return ResponseEntity.status(HttpStatus.OK).body(offers);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @RequestMapping(value = "/offer", method = RequestMethod.POST)
    public ResponseEntity<Offer> createOffer(@RequestParam("offeringTaxi") int offeringTaxi, @RequestParam("openSeats") int openSeats, @RequestParam("offerer") String offerer,
                                             @RequestParam("offererRating") float offererRating, @RequestParam("offerTime") String offerTime, @RequestParam("offerDestination") String offerDestination,
                                             @RequestParam("startLocation") String startLocation) {
        OfferDB offerDB = new OfferDB();
        int offerId = offerDB.getMaxOfferId() + 1;
        Offer newOffer = new Offer(offerId, offeringTaxi, openSeats, offerer, offererRating, offerTime, offerDestination, startLocation);
        boolean success = offerDB.createOffer(newOffer);
        offerDB.close();
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newOffer);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/offer", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteOffer(@RequestParam("offerId") int offerId) {
        OfferDB offerDB = new OfferDB();
        boolean success = offerDB.deleteOffer(offerId);
        offerDB.close();
        if (success) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Successfully deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "error page";
    }
}