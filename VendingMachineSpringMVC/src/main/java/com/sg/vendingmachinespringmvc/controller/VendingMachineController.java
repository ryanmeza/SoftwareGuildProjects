/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Snack;
import com.sg.vendingmachinespringmvc.model.VendingMachineInteractions;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Ryan
 */
@Controller
public class VendingMachineController {

    VendingMachineDao dao;

    //  Create access to the information stored in the DAO.
    @Inject
    public VendingMachineController(VendingMachineDao dao) {
        this.dao = dao;
    }

    //  Pulls data from DAO to display vending machine options.
    @RequestMapping(value = {"/displayVendingMachine", "/index.html"}, method = RequestMethod.GET)
    public String displayVendingMachineInventory(Model model) {
        VendingMachineInteractions interaction = dao.getInteractions();
        List<Snack> snackList = dao.getSnackInventory();
        model.addAttribute("snackList", snackList);
        model.addAttribute("interaction", interaction);
        return "vendingMachine";
    }
    //  Action allow for Total $ Input to increase based on which buttons 
    //  (dollar, quarter, dime, nickel) are pressed

    @RequestMapping(value = "/addMoney", method = RequestMethod.POST)
    public String addMoney(HttpServletRequest request, Model model) {
        VendingMachineInteractions interaction = dao.getInteractions();
        BigDecimal currentAmount;

        try {
            currentAmount = new BigDecimal(request.getParameter("value"));
        } catch (NumberFormatException | NullPointerException e) {
            currentAmount = BigDecimal.ZERO;
        }

        dao.updateAmountInputInteraction(interaction.getAmountInput().add(currentAmount));

        return "redirect:displayVendingMachine";
    }

    //  When user selects/clicks an Item button, the item Number will
    //  display in the Item Selected input.
    @RequestMapping(value = "/itemSelect", method = RequestMethod.POST)
    public String selectItem(HttpServletRequest request, Model model) {
        String selectedItem = request.getParameter("itemSelected");
        dao.updateSelectedItemInteraction(selectedItem);

        return "redirect:displayVendingMachine";
    }

    //  When user has entered money but wants to cancel interaction, 
    //  this action will return their money back to them in coins. 
    @RequestMapping(value = "/changeReturn", method = RequestMethod.POST)
    public String changeReturn(HttpServletRequest request, Model model) {
        VendingMachineInteractions interactions = dao.getInteractions();
        Change change = new Change(interactions.getAmountInput());
        dao.updateChangeInteraction(change.toString());
        dao.updateAmountInputInteraction(BigDecimal.ZERO);
        dao.updateMessageInteraction("");
        dao.updateSelectedItemInteraction("");
        return "redirect:displayVendingMachine";
    }

    //  This method controls the purchasing process once an item is 
    //  selected, inventory is available, sufficient funds are entered, 
    //  and then will properly return change if more than needed was
    //  submitted.
    @RequestMapping(value = "/purchaseItem", method = RequestMethod.POST)
    public String purchaseItem(HttpServletRequest request, Model model) {
        String selectedItem = request.getParameter("itemSelected");
        BigDecimal totalInput = dao.getInteractions().getAmountInput();


            if (selectedItem == null || selectedItem.isEmpty()) {
                dao.updateMessageInteraction("Please Select Item.");

            } else if (dao.getSnack(selectedItem) == null) {
                dao.updateMessageInteraction("Not Valid Item.");
            } else if (dao.getSnack(selectedItem).getSnackAmount() == 0) {
                dao.updateMessageInteraction("SOLD OUT!!!");
            } else {
                Snack selectedSnack = dao.getSnack(selectedItem);
                if (selectedSnack.getSnackPrice().compareTo(totalInput) > 0) {
                    String difference = (selectedSnack.getSnackPrice()
                            .subtract(totalInput)).toString();

                    dao.updateMessageInteraction("Please deposit $" + difference);

                } else {
                    BigDecimal totalInputMinusSnackPrice
                            = totalInput.subtract(selectedSnack.getSnackPrice());

                    Change changeBack = new Change(totalInputMinusSnackPrice);
                    dao.updateChangeInteraction(changeBack.toString());
                    dao.updateAmountInputInteraction(BigDecimal.ZERO);
                    dao.updateSelectedItemInteraction(selectedItem);
                    dao.updateInventory(selectedItem);
                    dao.updateMessageInteraction("THANK YOU!!!");
                }

            }


//TODO: 
//DONE      quantity = 0 check
//DONE      insufficientFundsCheck
//DONE      badData/NoSelection Check
//      Potential change return from purchase
//DONE      Clear all inputs when Change Return button clicked
        return "redirect:displayVendingMachine";
    }
}
