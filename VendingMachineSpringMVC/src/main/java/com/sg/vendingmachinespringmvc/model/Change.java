/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author Ryan
 */
public class Change {

    private int quarter;
    private int dime;
    private int nickel;
    private int penny;
    private int changeAmount;
    private final BigDecimal CENTS_PER_DOLLAR = new BigDecimal(100);
    private final int QUARTER_VALUE = 25;
    private final int DIME_VALUE = 10;
    private final int NICKEL_VALUE = 5;
    private final int PENNY_VALUE = 1;

    //Converting BigDecimal changeAmount from Service Layer to 
    // int changeAmount to calculate coins to return to User.
    public Change(BigDecimal changeAmount) {
        changeAmount = changeAmount.multiply(CENTS_PER_DOLLAR);
        this.changeAmount = changeAmount.intValueExact();
        calculateChange();
    }

    //Method to calculate the number of each coin to return to User.
    private void calculateChange() {

        for (CoinType coin : CoinType.values()) {

            switch (coin) {
                case QUARTER:
                    quarter = changeAmount / QUARTER_VALUE;
                    changeAmount = changeAmount % QUARTER_VALUE;
                    break;
                case DIME:
                    dime = changeAmount / DIME_VALUE;
                    changeAmount = changeAmount % DIME_VALUE;
                    break;
                case NICKEL:
                    nickel = changeAmount / NICKEL_VALUE;
                    changeAmount = changeAmount % NICKEL_VALUE;
                    break;
                case PENNY:
                    penny = changeAmount / PENNY_VALUE;
                    changeAmount = changeAmount % PENNY_VALUE;
                    break;
                default:
                    break;
            }
        }
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getDime() {
        return dime;
    }

    public void setDime(int dime) {
        this.dime = dime;
    }

    public int getNickel() {
        return nickel;
    }

    public void setNickel(int nickel) {
        this.nickel = nickel;
    }

    public int getPenny() {
        return penny;
    }

    public void setPenny(int penny) {
        this.penny = penny;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.quarter;
        hash = 97 * hash + this.dime;
        hash = 97 * hash + this.nickel;
        hash = 97 * hash + this.penny;
        hash = 97 * hash + this.changeAmount;
        hash = 97 * hash + Objects.hashCode(this.CENTS_PER_DOLLAR);
        hash = 97 * hash + this.QUARTER_VALUE;
        hash = 97 * hash + this.DIME_VALUE;
        hash = 97 * hash + this.NICKEL_VALUE;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Change other = (Change) obj;
        if (this.quarter != other.quarter) {
            return false;
        }
        if (this.dime != other.dime) {
            return false;
        }
        if (this.nickel != other.nickel) {
            return false;
        }
        if (this.penny != other.penny) {
            return false;
        }
        if (this.changeAmount != other.changeAmount) {
            return false;
        }
        if (this.QUARTER_VALUE != other.QUARTER_VALUE) {
            return false;
        }
        if (this.DIME_VALUE != other.DIME_VALUE) {
            return false;
        }
        if (this.NICKEL_VALUE != other.NICKEL_VALUE) {
            return false;
        }
        if (this.PENNY_VALUE != other.PENNY_VALUE) {
            return false;
        }
        if (!Objects.equals(this.CENTS_PER_DOLLAR, other.CENTS_PER_DOLLAR)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String changeBack = "";

        if (this.quarter > 0) {
            changeBack += quarter + " Quarters ";
        }
        if (this.dime > 0) {
            changeBack += dime + " Dimes ";
        }
        if (this.nickel > 0) {
            changeBack += nickel + " Nickels ";
        }

        return changeBack;
    }
}
