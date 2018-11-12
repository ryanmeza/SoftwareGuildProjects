/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroSightingsSuperpowerDao;
import com.sg.superherosightings.model.Superpower;
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
public class SuperpowerController {

    private SuperHeroSightingsSuperpowerDao superpowerDao;

    @Inject
    public SuperpowerController(SuperHeroSightingsSuperpowerDao superpowerDao) {
        this.superpowerDao = superpowerDao;
    }

    @RequestMapping(value = "/displaySuperpowers",
            method = RequestMethod.GET)
    public String displaySuperpowers(Model model) {

        List<Superpower> superpowerList = superpowerDao.getAllSuperpowers();
        model.addAttribute("superpowerList", superpowerList);
        return "superpower";
    }

    @RequestMapping(value = "/addSuperpower", method = RequestMethod.POST)
    public String addSuperpower(HttpServletRequest request) {
        Superpower superpower = new Superpower();
        try {
            superpower.setSuperpowerId(Integer.parseInt(request.getParameter("superpowerId")));
        } catch (NumberFormatException ex) {
            superpower.setSuperpowerId(0);
        }
        superpower.setSuperpowerType(request.getParameter("superpowerType"));
        if (superpower.getSuperpowerId() == 0) {
            superpowerDao.addSuperpower(superpower);
        } else {
            superpowerDao.updateSuperpower(superpower);
        }
        return "redirect:displaySuperpowers";
    }

    @RequestMapping(value = "deleteSuperpower", method = RequestMethod.GET)
    public String deleteSuperpower(HttpServletRequest request) {
        String superpowerIdParameter = request.getParameter("superpowerId");
        int superpowerId = Integer.parseInt(superpowerIdParameter);
        superpowerDao.deleteSuperpower(superpowerId);
        return "redirect:displaySuperpowers";
    }
}
