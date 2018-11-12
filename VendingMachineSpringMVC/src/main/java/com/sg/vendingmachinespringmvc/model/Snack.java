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
public class Snack {

    private String slotId;
    private String snackName;
    private BigDecimal snackPrice;
    private int snackAmount;

    public Snack(String slotId) {
        this.slotId = slotId;
    }

    public Snack(String slotId, String snackName, BigDecimal snackPrice, int snackAmount) {
        this.slotId = slotId;
        this.snackName = snackName;
        this.snackPrice = snackPrice;
        this.snackAmount = snackAmount;
    }

    public int getSnackAmount() {
        return snackAmount;
    }

    public void setSnackAmount(int snackAmount) {
        this.snackAmount = snackAmount;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getSnackName() {
        return snackName;
    }

    public void setSnackName(String snackName) {
        this.snackName = snackName;
    }

    public BigDecimal getSnackPrice() {
        return snackPrice;
    }

    public void setSnackPrice(BigDecimal snackPrice) {
        this.snackPrice = snackPrice;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.slotId);
        hash = 73 * hash + Objects.hashCode(this.snackName);
        hash = 73 * hash + Objects.hashCode(this.snackPrice);
        hash = 73 * hash + this.snackAmount;
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
        final Snack other = (Snack) obj;
        if (this.snackAmount != other.snackAmount) {
            return false;
        }
        if (!Objects.equals(this.slotId, other.slotId)) {
            return false;
        }
        if (!Objects.equals(this.snackName, other.snackName)) {
            return false;
        }
        if (!Objects.equals(this.snackPrice, other.snackPrice)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Slot ID: " + slotId + " |Snack Item: " + snackName
                + " |Snack Price: " + snackPrice + " |Snack Inventory: " + snackAmount;
    }
}
