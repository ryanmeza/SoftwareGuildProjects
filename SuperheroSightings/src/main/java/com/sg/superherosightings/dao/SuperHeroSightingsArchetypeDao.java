/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Archetype;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Ryan
 */
public interface SuperHeroSightingsArchetypeDao {

    public void addArchetype(Archetype archetype);

    public void deleteArchetype(int archetypeId);

    public void updateArchetype(Archetype archetype);

    public Archetype getArchetypeById(int archetypeId);

    public List<Archetype> getAllArchetypes();

    public List<Archetype> getArchetypeByOrganizationId(int organizationId);

    public List<Archetype> getArchetypeBySuperpowerId(int superpowerId);

    public List<Archetype> getArchetypeBySightingId(int sightingId);

    public List<Archetype> getArchetypeByCharacterTypeId(int characterTypeId);

    public List<Archetype> getSightingsWithArchetypesByDate(LocalDate sightingdate);
    
    public List<Archetype> getArchetypesByLocationId(int locationId);
    
    public List<Archetype> getSightingLocationsByArchetypeId(int archetypeId);
    
    public List<Archetype> getOrganizationsByArchetypeId(List<Archetype> archetypeList);
}
