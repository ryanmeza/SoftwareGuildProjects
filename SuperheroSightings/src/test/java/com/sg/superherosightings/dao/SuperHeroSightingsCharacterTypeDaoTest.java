/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.CharacterType;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Ryan Meza
 */
public class SuperHeroSightingsCharacterTypeDaoTest {

    SuperHeroSightingsCharacterTypeDao characterTypeDao;

    public SuperHeroSightingsCharacterTypeDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        characterTypeDao
                = ctx.getBean("characterTypeDao", SuperHeroSightingsCharacterTypeDao.class);

        //delete all Character Types
        List<CharacterType> characterTypes = characterTypeDao.getAllCharacterTypes();
        for (CharacterType currentCharacterType : characterTypes) {
            characterTypeDao.deleteCharacterType(currentCharacterType.getCharacterTypeId());
        }       
       
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addCharacterType method, of class
     * SuperHeroSightingsCharacterTypeDao.
     */
    @Test
    public void testAddCharacterType() {

        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Villain");

        characterTypeDao.addCharacterType(characterType);

        CharacterType fromDao = characterTypeDao.getCharacterTypeById(characterType.getCharacterTypeId());
        assertEquals(characterType, fromDao);
    }

    /**
     * Test of deleteCharacterType method, of class
     * SuperHeroSightingsCharacterTypeDao.
     */
    @Test
    public void testDeleteCharacterType() {
        CharacterType hero = new CharacterType();

        hero.setCharacterTypeName("Hero");

        characterTypeDao.addCharacterType(hero);
        characterTypeDao.deleteCharacterType(hero.getCharacterTypeId());
        CharacterType fromDao = characterTypeDao.getCharacterTypeById(hero.getCharacterTypeId());

        assertEquals(null, fromDao);
    }

    /**
     * Test of updateCharacterType method, of class
     * SuperHeroSightingsCharacterTypeDao.
     */
    @Test
    public void testUpdateCharacterType() {
        CharacterType hero = new CharacterType();
        hero.setCharacterTypeName("Hero");

        characterTypeDao.addCharacterType(hero);
        hero.setCharacterTypeName("Villian");
        characterTypeDao.updateCharacterType(hero);
        CharacterType fromDao = characterTypeDao.getCharacterTypeById(hero.getCharacterTypeId());

        assertEquals("Villian", fromDao.getCharacterTypeName());
    }

    /**
     * Test of getCharacterTypeById method, of class
     * SuperHeroSightingsCharacterTypeDao.
     */
    @Test
    public void testGetCharacterTypeById() {
        CharacterType hero = new CharacterType();
        CharacterType villian = new CharacterType();
        int expectedCharacterTypeId;
        villian.setCharacterTypeName("Villian");
        hero.setCharacterTypeName("Hero");

        characterTypeDao.addCharacterType(hero);
        expectedCharacterTypeId = hero.getCharacterTypeId();
        characterTypeDao.addCharacterType(villian);

        CharacterType fromDao = characterTypeDao.getCharacterTypeById(expectedCharacterTypeId);
        assertEquals(hero, fromDao);
    }

    /**
     * Test of getAllCharacterTypes method, of class
     * SuperHeroSightingsCharacterTypeDao.
     */
    @Test
    public void testGetAllCharacterTypes() {
        CharacterType hero = new CharacterType();
        CharacterType villian = new CharacterType();
        CharacterType normalPerson = new CharacterType();
        hero.setCharacterTypeName("Hero");
        villian.setCharacterTypeName("Villain");
        normalPerson.setCharacterTypeName("NormalPerson");

        characterTypeDao.addCharacterType(hero);
        characterTypeDao.addCharacterType(villian);
        characterTypeDao.addCharacterType(normalPerson);
        List<CharacterType> fromDao = characterTypeDao.getAllCharacterTypes();

        assertEquals(3, fromDao.size());
    }

}
