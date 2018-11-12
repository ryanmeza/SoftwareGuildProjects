/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Ryan
 */
abstract class BaseController {

    protected Integer parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException | NullPointerException e) {
            return 0;
        }
    }

    protected LocalDate parseDate(String str) {
        try {
            return LocalDate.parse(str);
        } catch (DateTimeParseException ex) {
            return null;
        }
    }
}
