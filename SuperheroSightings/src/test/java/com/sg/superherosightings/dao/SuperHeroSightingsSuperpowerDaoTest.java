/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Superpower;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Ryan Meza
 */
public class SuperHeroSightingsSuperpowerDaoTest {

    SuperHeroSightingsSuperpowerDao superpowerDao;

    public SuperHeroSightingsSuperpowerDaoTest() {
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

        superpowerDao
                = ctx.getBean("superpowerDao", SuperHeroSightingsSuperpowerDao.class);

        //delete all Superpowers
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for (Superpower currentSuperpower : superpowers) {
            superpowerDao.deleteSuperpower(currentSuperpower.getSuperpowerId());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addSuperpower method, of class SuperHeroSightingsSuperpowerDao.
     */
    @Test
    public void testAddSuperpower() {

        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");

        superpowerDao.addSuperpower(superpower);

        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpowerId());
        assertEquals(superpower, fromDao);

    }

    /**
     * Test of deleteSuperpower method, of class
     * SuperHeroSightingsSuperpowerDao.
     */
    @Test
    public void testDeleteSuperpower() {
        Superpower superpower = new Superpower();

        superpower.setSuperpowerType("Flying");

        superpowerDao.addSuperpower(superpower);
        superpowerDao.deleteSuperpower(superpower.getSuperpowerId());
        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpowerId());

        assertEquals(null, fromDao);
    }

    /**
     * Test of updateSuperpower method, of class
     * SuperHeroSightingsSuperpowerDao.
     */
    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");

        superpowerDao.addSuperpower(superpower);
        superpower.setSuperpowerType("Lasers");
        superpowerDao.updateSuperpower(superpower);
        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpowerId());

        assertEquals("Lasers", fromDao.getSuperpowerType());
    }

    /**
     * Test of getSuperpowerById method, of class
     * SuperHeroSightingsSuperpowerDao.
     */
    @Test
    public void testGetSuperpowerById() {
        Superpower invisible = new Superpower();
        Superpower lasers = new Superpower();
        int expectedSuperpowerId;
        lasers.setSuperpowerType("Lasers");
        invisible.setSuperpowerType("Invisible");

        superpowerDao.addSuperpower(invisible);
        expectedSuperpowerId = invisible.getSuperpowerId();
        superpowerDao.addSuperpower(lasers);

        Superpower fromDao = superpowerDao.getSuperpowerById(expectedSuperpowerId);
        assertEquals(invisible, fromDao);
    }

    /**
     * Test of getAllSuperpowers method, of class
     * SuperHeroSightingsSuperpowerDao.
     */
    @Test
    public void testGetAllSuperpowers() {

        Superpower invisible = new Superpower();
        Superpower lasers = new Superpower();
        Superpower flying = new Superpower();
        invisible.setSuperpowerType("Invisible");
        lasers.setSuperpowerType("Lasers");
        flying.setSuperpowerType("Flying");

        superpowerDao.addSuperpower(invisible);
        superpowerDao.addSuperpower(lasers);
        superpowerDao.addSuperpower(flying);
        List<Superpower> fromDao = superpowerDao.getAllSuperpowers();

        assertEquals(3, fromDao.size());
    }

}
