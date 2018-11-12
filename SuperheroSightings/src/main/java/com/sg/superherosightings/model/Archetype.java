/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Ryan Meza
 */


public class Archetype {
    
    private int archetypeId;
    private String archetypeName;
    private String archetypeDescription;
    private CharacterType characterType;
    private List<Organization> archetypeOrganizations;
    private List<Superpower> archetypeSuperpowers;
    private List<Sighting> archetypeSightings;

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

    public String getArchetypeDescription() {
        return archetypeDescription;
    }

    public void setArchetypeDescription(String archetypeDescription) {
        this.archetypeDescription = archetypeDescription;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public void setCharacterType(CharacterType characterType) {
        this.characterType = characterType;
    }

    public List<Organization> getArchetypeOrganizations() {
        return archetypeOrganizations;
    }

    public void setArchetypeOrganizations(List<Organization> archetypeOrganizations) {
        this.archetypeOrganizations = archetypeOrganizations;
    }

    public List<Superpower> getArchetypeSuperpowers() {
        return archetypeSuperpowers;
    }

    public void setArchetypeSuperpowers(List<Superpower> archetypeSuperpowers) {
        this.archetypeSuperpowers = archetypeSuperpowers;
    }

    public List<Sighting> getArchetypeSightings() {
        return archetypeSightings;
    }

    public void setArchetypeSightings(List<Sighting> archetypeSightings) {
        this.archetypeSightings = archetypeSightings;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.archetypeId;
        hash = 53 * hash + Objects.hashCode(this.archetypeName);
        hash = 53 * hash + Objects.hashCode(this.archetypeDescription);
        hash = 53 * hash + Objects.hashCode(this.characterType);
        hash = 53 * hash + Objects.hashCode(this.archetypeOrganizations);
        hash = 53 * hash + Objects.hashCode(this.archetypeSuperpowers);
        hash = 53 * hash + Objects.hashCode(this.archetypeSightings);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Archetype other = (Archetype) obj;
        if (this.archetypeId != other.archetypeId) {
            return false;
        }
        if (!Objects.equals(this.archetypeName, other.archetypeName)) {
            return false;
        }
        if (!Objects.equals(this.archetypeDescription, other.archetypeDescription)) {
            return false;
        }
        if (!Objects.equals(this.characterType, other.characterType)) {
            return false;
        }
        if (!Objects.equals(this.archetypeOrganizations, other.archetypeOrganizations)) {
            return false;
        }
        if (!Objects.equals(this.archetypeSuperpowers, other.archetypeSuperpowers)) {
            return false;
        }
        if (!Objects.equals(this.archetypeSightings, other.archetypeSightings)) {
            return false;
        }
        return true;
    }
    
    
}
