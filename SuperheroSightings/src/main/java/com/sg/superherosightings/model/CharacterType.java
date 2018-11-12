/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.Objects;

/**
 *
 * @author Ryan Meza
 */

public class CharacterType {

    private int characterTypeId;
    private String characterTypeName;

    public int getCharacterTypeId() {
        return characterTypeId;
    }

    public void setCharacterTypeId(int characterTypeId) {
        this.characterTypeId = characterTypeId;
    }

    public String getCharacterTypeName() {
        return characterTypeName;
    }

    public void setCharacterTypeName(String characterTypeName) {
        this.characterTypeName = characterTypeName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.characterTypeId;
        hash = 43 * hash + Objects.hashCode(this.characterTypeName);
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
        final CharacterType other = (CharacterType) obj;
        if (this.characterTypeId != other.characterTypeId) {
            return false;
        }
        if (!Objects.equals(this.characterTypeName, other.characterTypeName)) {
            return false;
        }
        return true;
    }
    
    
}
