/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Snack;
import com.sg.vendingmachinespringmvc.model.VendingMachineInteractions;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Ryan
 */
public interface VendingMachineDao {

    public void updateInventory(String slotId);

    public List<Snack> getSnackInventory();

    public Snack getSnack(String slotId);

    public Snack addSnack(String slotId, Snack snack);

    public Snack removeSnack(String slotId);

    public VendingMachineInteractions getInteractions();

    public void updateAmountInputInteraction(BigDecimal amountInput);

    public void updateSelectedItemInteraction(String selectedItem);

    public void updateMessageInteraction(String message);

    public void updateChangeInteraction(String change);

}
