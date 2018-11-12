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
public class VendingMachineInteractions {

    private BigDecimal amountInput;
    private String selectedItem;
    private String message;
    private String change;

    public VendingMachineInteractions() {
        this.amountInput = BigDecimal.ZERO;
        this.selectedItem = "";
        this.message = "";
        this.change = "";
    }

    public BigDecimal getAmountInput() {
        return amountInput;
    }

    public void setAmountInput(BigDecimal amountInput) {
        this.amountInput = amountInput;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.amountInput);
        hash = 41 * hash + Objects.hashCode(this.selectedItem);
        hash = 41 * hash + Objects.hashCode(this.message);
        hash = 41 * hash + Objects.hashCode(this.change);
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
        final VendingMachineInteractions other = (VendingMachineInteractions) obj;
        if (!Objects.equals(this.selectedItem, other.selectedItem)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.change, other.change)) {
            return false;
        }
        if (!Objects.equals(this.amountInput, other.amountInput)) {
            return false;
        }
        return true;
    }

}
