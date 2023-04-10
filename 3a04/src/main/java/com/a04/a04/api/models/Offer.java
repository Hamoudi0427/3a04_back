package com.a04.a04.api.models;

public class Offer {
    public int offerID, offeringTaxi, openSeats;
    public String offerer, offerTime, offerDestination;
    public float offererRating;

    public Offer(int offerID, int offeringTaxi, int openSeats, String offerer, float offererRating, String offerTime, String offerDestination) {
        this.offerID = offerID;
        this.offeringTaxi = offeringTaxi;
        this.openSeats = openSeats;
        this.offerer = offerer;
        this.offerTime = offerTime;
        this.offerDestination = offerDestination;
        this.offererRating = offererRating;
    }
}
