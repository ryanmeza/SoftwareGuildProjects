/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroSightingsArchetypeDao;
import com.sg.superherosightings.dao.SuperHeroSightingsLocationDao;
import com.sg.superherosightings.dao.SuperHeroSightingsSightingDao;
import com.sg.superherosightings.model.Archetype;
import com.sg.superherosightings.model.ArchetypeSighting;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class SightingController extends BaseController {

    private SuperHeroSightingsSightingDao sightingDao;
    private SuperHeroSightingsLocationDao locationDao;
    private SuperHeroSightingsArchetypeDao archetypeDao;

    @Inject
    public SightingController(SuperHeroSightingsSightingDao sightingDao,
            SuperHeroSightingsLocationDao locationDao,
            SuperHeroSightingsArchetypeDao archetypeDao) {
        this.sightingDao = sightingDao;
        this.locationDao = locationDao;
        this.archetypeDao = archetypeDao;
    }

    @RequestMapping(value = {"/displaySightings"},
            method = RequestMethod.GET)
    public String displaySighting(Model model) {

        // get list of current locations
        List<Location> locationList = locationDao.getAllLocations();
        List<Archetype> archetypeList = archetypeDao.getAllArchetypes();
        List<ArchetypeSighting> tableList = new ArrayList<>();

        for (Archetype arch : archetypeList) {
            for (Sighting sighting : arch.getArchetypeSightings()) {
                tableList.add(new ArchetypeSighting(arch.getArchetypeId(), arch.getArchetypeName(), sighting));
            }
        }

        model.addAttribute("locationList", locationList);
        model.addAttribute("archetypeList", archetypeList);
        model.addAttribute("tableList", tableList);

        return "sighting";
    }

    @RequestMapping(value = "/addSighting", method = RequestMethod.POST)
    public String addSighting(HttpServletRequest request) {
        Sighting sighting = new Sighting();
        Location location = new Location();
        Archetype arch = new Archetype();
        List<Sighting> newSightings;

        sighting.setSightingId(parseInt(request.getParameter("sightingId")));
        sighting.setSightingDate(parseDate(request.getParameter("sightingDate")));
        location = locationDao.getLocationById(parseInt(request.getParameter("locationId")));
        arch = archetypeDao.getArchetypeById(parseInt(request.getParameter("archetypeName")));

        sighting.setLocation(location);
        newSightings = arch.getArchetypeSightings();

        if (sighting.getSightingId() == 0) {
            sightingDao.addSighting(sighting);
            sighting.setSightingId(sightingDao.getLastSightingId());
        } else {
            sightingDao.updateSighting(sighting);
            newSightings = newSightings.stream().filter(i -> i.getSightingId() != sighting.getSightingId()).collect(Collectors.toList());
        }
        
        newSightings.add(sighting);
        arch.setArchetypeSightings(newSightings);
        archetypeDao.updateArchetype(arch);

        return "redirect:displaySightings";
    }

    @RequestMapping(value = "deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = parseInt(sightingIdParameter);
        sightingDao.deleteSighting(sightingId);
        sightingDao.deleteArchetypeSighting(sightingId);
        return "redirect:displaySightings";
    }

}
