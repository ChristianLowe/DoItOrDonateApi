package com.kalieki.donation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by kalieki on 11/12/16.
 */

@Entity
public class DonationCriteria {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private Charities charityTarget;

    @Column
    private int donationAmt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Charities getCharityTarget() {
        return charityTarget;
    }

    public void setCharityTarget(Charities charityTarget) {
        this.charityTarget = charityTarget;
    }

    public int getDonationAmt() {
        return donationAmt;
    }

    public void setDonationAmt(int donationAmt) {
        this.donationAmt = donationAmt;
    }
}
