/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroSightingsArchetypeDao;
import com.sg.superherosightings.dao.SuperHeroSightingsLocationDao;
import com.sg.superherosightings.dao.SuperHeroSightingsSightingDao;
import com.sg.superherosightings.model.ArchetypeSighting;
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
public class HomeController {

    private SuperHeroSightingsSightingDao sightingDao;

    @Inject
    public HomeController(SuperHeroSightingsSightingDao sightingDao) {
        this.sightingDao = sightingDao;
    }

    @RequestMapping(value = {"/home", "/index.html"},
            method = RequestMethod.GET)
    public String displayHome(HttpServletRequest request, Model model) {
        List<ArchetypeSighting> sightings = sightingDao.getLatestSightings();
        model.addAttribute("tableList",sightings);
        return "home";
    }

}
