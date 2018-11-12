/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroSightingsLocationDao;
import com.sg.superherosightings.dao.SuperHeroSightingsOrganizationDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
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
public class OrganizationController {

    private SuperHeroSightingsOrganizationDao organizationDao;
    private SuperHeroSightingsLocationDao locationDao;

    @Inject
    public OrganizationController(SuperHeroSightingsOrganizationDao organizationDao,
            SuperHeroSightingsLocationDao locationDao) {
        this.organizationDao = organizationDao;
        this.locationDao = locationDao;
    }

    @RequestMapping(value = {"/displayOrganizations"},
            method = RequestMethod.GET)
    public String displayOrganization(Model model) {

        // get list of current locations
        List<Location> locationList = locationDao.getAllLocations();
        model.addAttribute("locationList", locationList);

        List<Organization> organizationList = organizationDao.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);
        return "organization";
    }

    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request) {
        Organization organization = new Organization();
        try {
            organization.setOrganizationId(Integer.parseInt(request.getParameter("organizationId")));
        } catch (NumberFormatException ex) {
            organization.setOrganizationId(0);
        }
        organization.setOrganizationName(request.getParameter("organizationName"));
        organization.setOrganizationDescription(request.getParameter("organizationDescription"));
        organization.setOrganizationPhone(request.getParameter("organizationPhone"));
        organization.setOrganizationEmail(request.getParameter("organizationEmail"));

        Location location;
        try {
            location = locationDao.getLocationById(Integer.parseInt(request.getParameter("locationId")));

        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("ERROR MESSAGE");
            //add error message to show that org was not added !!!!!!!!!!!!!!!!!!!!!!
            //or allow null locations !!!!!!
            return "redirect:displayOrganizations";
        }
        organization.setLocation(location);

        if (organization.getOrganizationId() == 0) {
            organizationDao.addOrganization(organization);
        } else {
            organizationDao.updateOrganization(organization);
        }
        return "redirect:displayOrganizations";
    }

    @RequestMapping(value = "deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        organizationDao.deleteOrganization(organizationId);
        return "redirect:displayOrganizations";
    }
}
