/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.ArchetypeSighting;
import com.sg.superherosightings.model.Sighting;
import java.util.List;

/**
 *
 * @author Ryan Meza
 */
public interface SuperHeroSightingsSightingDao {

    public void addSighting(Sighting sighting);

    public void deleteSighting(int sightingId);

    public void deleteArchetypeSighting(int sightingId);

    public void updateSighting(Sighting sighting);

    public Sighting getSightingById(int sightingId);

    public List<Sighting> getSightingsByLocationId(int locationId);

    public List<Sighting> getAllSightings();
    
    public List<ArchetypeSighting> getLatestSightings();

    public Integer getLastSightingId();
}
