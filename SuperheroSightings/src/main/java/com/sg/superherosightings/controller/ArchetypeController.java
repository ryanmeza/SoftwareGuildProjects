/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.controller;

import com.sg.superherosightings.dao.SuperHeroSightingsArchetypeDao;
import com.sg.superherosightings.dao.SuperHeroSightingsCharacterTypeDao;
import com.sg.superherosightings.dao.SuperHeroSightingsLocationDao;
import com.sg.superherosightings.dao.SuperHeroSightingsOrganizationDao;
import com.sg.superherosightings.dao.SuperHeroSightingsSightingDao;
import com.sg.superherosightings.dao.SuperHeroSightingsSuperpowerDao;
import com.sg.superherosightings.model.Archetype;
import com.sg.superherosightings.model.CharacterType;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superpower;
import java.util.ArrayList;
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
public class ArchetypeController extends BaseController {

    private SuperHeroSightingsArchetypeDao archetypeDao;
    private SuperHeroSightingsCharacterTypeDao characterTypeDao;
    private SuperHeroSightingsOrganizationDao organizationDao;
    private SuperHeroSightingsSuperpowerDao superpowerDao;
    private SuperHeroSightingsSightingDao sightingDao;
    private SuperHeroSightingsLocationDao locationDao;

    @Inject
    public ArchetypeController(SuperHeroSightingsArchetypeDao archetypeDao,
            SuperHeroSightingsCharacterTypeDao characterTypeDao,
            SuperHeroSightingsOrganizationDao organizationDao,
            SuperHeroSightingsSuperpowerDao superpowerDao,
            SuperHeroSightingsSightingDao sightingDao,
            SuperHeroSightingsLocationDao locationDao) {
        this.archetypeDao = archetypeDao;
        this.characterTypeDao = characterTypeDao;
        this.organizationDao = organizationDao;
        this.superpowerDao = superpowerDao;
        this.sightingDao = sightingDao;
        this.locationDao = locationDao;
    }

    @RequestMapping(value = {"/displayArchetypes"},
            method = RequestMethod.GET)
    public String displayArchetype(Model model) {

        // get list of current character types
        List<CharacterType> characterTypeList = characterTypeDao.getAllCharacterTypes();
        model.addAttribute("characterTypeList", characterTypeList);

        // get list of current superpowers
        List<Superpower> superpowerList = superpowerDao.getAllSuperpowers();
        model.addAttribute("superpowerList", superpowerList);

        // get list of current organizations
        List<Organization> organizationList = organizationDao.getAllOrganizations();
        model.addAttribute("organizationList", organizationList);

        List<Archetype> archetypeList = archetypeDao.getAllArchetypes();
        model.addAttribute("archetypeList", archetypeList);

        return "archetype";
    }

    @RequestMapping(value = "/addArchetype", method = RequestMethod.POST)
    public String addArchetype(HttpServletRequest request) {
        Archetype archetype = new Archetype();
        CharacterType charType;
        List<Superpower> superpowers = new ArrayList();
        List<Organization> organizations = new ArrayList();
        
        String[] superpowerIds = request.getParameterValues("superpowers");
        for (String current: superpowerIds) {
            superpowers.add(superpowerDao.getSuperpowerById(parseInt(current)));
        }
        String[] organizationIds = request.getParameterValues("organizationId");
        for (String current: organizationIds) {
            organizations.add(organizationDao.getOrganizationById(parseInt(current)));
        }

        charType = characterTypeDao.getCharacterTypeById(parseInt(request.getParameter("characterTypeId")));
        archetype.setArchetypeId(parseInt(request.getParameter("archetypeId")));
        archetype.setArchetypeName(request.getParameter("archetypeName"));
        archetype.setArchetypeDescription(request.getParameter("archetypeDescription"));
        archetype.setCharacterType(charType);
        archetype.setArchetypeSuperpowers(superpowers);
        archetype.setArchetypeOrganizations(organizations);

        if (archetype.getArchetypeId() == 0) {
            archetypeDao.addArchetype(archetype);
        } else {
            archetypeDao.updateArchetype(archetype);
        }
       return "redirect:displayArchetypes";
    }

    @RequestMapping(value = "deleteArchetype", method = RequestMethod.GET)
    public String deleteArchetype(HttpServletRequest request) {
        int archetypeId = parseInt(request.getParameter("archetypeId"));
        archetypeDao.deleteArchetype(archetypeId);
        return "redirect:displayArchetypes";
    }
}
