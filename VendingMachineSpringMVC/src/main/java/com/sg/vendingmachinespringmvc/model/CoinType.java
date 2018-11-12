/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

/**
 *
 * @author Ryan
 */
public enum CoinType {
    //The order of these enums are important 
    //and a project, VendingMachine, is utilizing that order.
    QUARTER, DIME, NICKEL, PENNY;
}
