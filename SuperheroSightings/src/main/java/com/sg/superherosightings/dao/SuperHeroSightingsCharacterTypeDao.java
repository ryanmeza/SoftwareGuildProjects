/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.CharacterType;
import java.util.List;

/**
 *
 * @author Ryan Meza
 */
public interface SuperHeroSightingsCharacterTypeDao {

    public void addCharacterType(CharacterType characterType);

    public void deleteCharacterType(int characterTypeId);

    public void updateCharacterType(CharacterType characterType);

    public CharacterType getCharacterTypeById(int characterTypeId);

    public List<CharacterType> getAllCharacterTypes();

}
