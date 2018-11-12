/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Snack;
import com.sg.vendingmachinespringmvc.model.VendingMachineInteractions;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ryan
 */
public class VendingMachineDaoImpl implements VendingMachineDao {
    
    private Map<String, Snack> snacks = new HashMap();
    private VendingMachineInteractions interaction = new VendingMachineInteractions();
    
    public VendingMachineDaoImpl() {
        snacks.put("1", new Snack("1", "Snickers", new BigDecimal(1.85).setScale(2, RoundingMode.HALF_UP), 9));
        snacks.put("2", new Snack("2", "M&Ms", new BigDecimal(1.50).setScale(2, RoundingMode.HALF_UP), 2));
        snacks.put("3", new Snack("3", "Pringles", new BigDecimal(2.10).setScale(2, RoundingMode.HALF_UP), 5));
        snacks.put("4", new Snack("4", "Reese's", new BigDecimal(1.85).setScale(2, RoundingMode.HALF_UP), 4));
        snacks.put("5", new Snack("5", "Pretzels", new BigDecimal(1.25).setScale(2, RoundingMode.HALF_UP), 9));
        snacks.put("6", new Snack("6", "Twinkies", new BigDecimal(1.95).setScale(2, RoundingMode.HALF_UP), 3));
        snacks.put("7", new Snack("7", "Doritos", new BigDecimal(1.75).setScale(2, RoundingMode.HALF_UP), 11));
        snacks.put("8", new Snack("8", "Almond Joy", new BigDecimal(1.85).setScale(2, RoundingMode.HALF_UP), 0));
        snacks.put("9", new Snack("9", "Trident", new BigDecimal(1.95).setScale(2, RoundingMode.HALF_UP), 6));
    }

    /**
     * Method updates the item inventory/quantity after a purchase is made
     * but decrementing it by one. The snackAmount property will decrease by
     * one.
     *
     * @param slotId
     */
    @Override
    public void updateInventory(String slotId) {
        
        Snack snack = snacks.get(slotId);
        snack.setSnackAmount(snack.getSnackAmount() - 1);
        
    }

    /**
     * Method pulls the snack inventory from in-memory 
     * (stored in DAOImpl constructor) and allows objects 
     * to be used within the program.
     *
     * @return ArrayList of Snack objects with their properties.
     */
    @Override
    public List<Snack> getSnackInventory() {
        
        return new ArrayList<>(snacks.values());
    }

    /**
     * Method gets the Snack object associated to the key(slotId) provided by
     * the user.
     *
     * @param key
     * @return
     */
    @Override
    public Snack getSnack(String key) {
        
        return snacks.get(key);
    }
    
    @Override
    public Snack addSnack(String slotId, Snack snack) {
        Snack newSnack = snacks.put(slotId, snack);
        
        return newSnack;
    }
    
    @Override
    public Snack removeSnack(String slotId) {
        Snack removedSnack = snacks.remove(slotId);
        
        return removedSnack;
    }
    
    @Override
    public VendingMachineInteractions getInteractions() {
        return this.interaction;
    }
    
    @Override
    public void updateAmountInputInteraction(BigDecimal amountInput) {
        this.interaction.setAmountInput(amountInput);
    }
    
    @Override
    public void updateSelectedItemInteraction(String selectedItem) {
        this.interaction.setSelectedItem(selectedItem);
    }
    
    @Override
    public void updateMessageInteraction(String message) {
        this.interaction.setMessage(message);
    }
    
    @Override
    public void updateChangeInteraction(String change) {
        this.interaction.setChange(change);
    }
}
