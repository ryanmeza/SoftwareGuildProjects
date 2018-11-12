/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

/**
 *
 * @author Ryan
 */
public class ArchetypeSighting {

    private int archetypeId;
    private String archetypeName;
    private Sighting sighting;

    public ArchetypeSighting(int archetypeId, String archetypeName, Sighting sighting) {
        this.archetypeId = archetypeId;
        this.archetypeName = archetypeName;
        this.sighting = sighting;
    }

    public int getArchetypeId() {
        return archetypeId;
    }

    public void setArchetypeId(int archetypeId) {
        this.archetypeId = archetypeId;
    }

    public String getArchetypeName() {
        return archetypeName;
    }

    public void setArchetypeName(String archetypeName) {
        this.archetypeName = archetypeName;
    }

    public Sighting getSighting() {
        return sighting;
    }

    public void setSighting(Sighting sighting) {
        this.sighting = sighting;
    }

}
