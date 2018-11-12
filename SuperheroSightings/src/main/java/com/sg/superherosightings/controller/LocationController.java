/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroSightingsLocationDao;
import com.sg.superherosightings.dao.SuperHeroSightingsOrganizationDao;
import com.sg.superherosightings.dao.SuperHeroSightingsSightingDao;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class LocationController {

    private SuperHeroSightingsLocationDao locationDao;
    private SuperHeroSightingsSightingDao sightingDao;
    private SuperHeroSightingsOrganizationDao organizationDao;

    @Inject
    public LocationController(SuperHeroSightingsLocationDao locationDao,
            SuperHeroSightingsSightingDao sightingDao,
            SuperHeroSightingsOrganizationDao organizationDao) {
        this.locationDao = locationDao;
        this.sightingDao = sightingDao;
        this.organizationDao = organizationDao;
    }

    @RequestMapping(value = {"/displayLocations"},
            method = RequestMethod.GET)
    public String displayLocation(Model model) {

        List<Location> locationList = locationDao.getAllLocations();
        model.addAttribute("locationList", locationList);
        return "location";
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest request) {
        Location location = new Location();
        try {

            location.setLocationId(Integer.parseInt(request.getParameter("locationId")));

        } catch (NumberFormatException ex) {
            location.setLocationId(0);
        }
        try {
            location.setLatitude(new BigDecimal(request.getParameter("latitude")).setScale(8, RoundingMode.HALF_UP));
            location.setLongitude(new BigDecimal(request.getParameter("longitude")).setScale(8, RoundingMode.HALF_UP));
        } catch (NumberFormatException e) {
            location.setLatitude(null);
            location.setLongitude(null);
        }
        location.setLocationName(request.getParameter("locationName"));
        location.setLocationDescription(request.getParameter("locationDescription"));
        location.setLocationStreet(request.getParameter("locationStreet"));
        location.setLocationCity(request.getParameter("locationCity"));
        location.setLocationState(request.getParameter("locationState"));
        location.setLocationZip(request.getParameter("locationZip"));
        location.setLocationCountry(request.getParameter("locationCountry"));

        if (location.getLocationId() == 0) {
            locationDao.addLocation(location);
        } else {
            locationDao.updateLocation(location);
        }
        return "redirect:displayLocations";
    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        List<Sighting> sightingsToBeDeleted = sightingDao.getSightingsByLocationId(locationId);
        List<Organization> organizationsToBeDeleted = organizationDao.getOrganizationsByLocationId(locationId);
        for (Sighting sightingId : sightingsToBeDeleted) {
            sightingDao.deleteSighting(sightingId.getSightingId());
        }
        for (Organization organizationId : organizationsToBeDeleted) {
            organizationDao.deleteOrganization(organizationId.getOrganizationId());
        }
        locationDao.deleteLocation(locationId);
        return "redirect:displayLocations";
    }
}
