/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class SuperHeroSightingsSightingDaoTest {

    SuperHeroSightingsSightingDao sightingDao;
    SuperHeroSightingsLocationDao locationDao;

    public SuperHeroSightingsSightingDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        sightingDao = ctx.getBean("sightingDao", SuperHeroSightingsSightingDao.class);

        locationDao = ctx.getBean("locationDao", SuperHeroSightingsLocationDao.class);

        //delete all sightings
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingDao.deleteSighting(currentSighting.getSightingId());
        }
        
        //delete all locations
        List<Location> locations = locationDao.getAllLocations();
        for (Location currentLocation : locations) {
            locationDao.deleteLocation(currentLocation.getLocationId());
        }

    }

    @After
    public void tearDown() {
       //delete all sightings
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingDao.deleteSighting(currentSighting.getSightingId());
        }
        
        //delete all locations
        List<Location> locations = locationDao.getAllLocations();
        for (Location currentLocation : locations) {
            locationDao.deleteLocation(currentLocation.getLocationId());
        }
    }

    /**
     * Test of addSighting method, of class SuperHeroSightingsSightingDao.
     */
    @Test
    public void testAddGetSighting() {

        Location location = new Location();
        location.setLocationName("Empire State Building");
        location.setLocationDescription("Really tall building.");
        location.setLocationStreet("123 Some St");
        location.setLocationCity("New York");
        location.setLocationState("New York");
        location.setLocationZip("12345");
        location.setLocationCountry("USA");
        location.setLatitude(new BigDecimal(45.12).setScale(8, RoundingMode.HALF_UP));
        location.setLongitude(new BigDecimal(175.14).setScale(8, RoundingMode.HALF_UP));

        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    }

    /**
     * Test of deleteSighting method, of class SuperHeroSightingsSightingDao.
     */
    @Test
    public void testDeleteSighting() {

        Location location = new Location();
        location.setLocationName("Banana Boat");
        location.setLocationDescription("The ocean.");
        location.setLocationStreet("123 Water Way");
        location.setLocationCity("WarmPlace");
        location.setLocationState("South");
        location.setLocationZip("12345");
        location.setLocationCountry("USA");
        location.setLatitude(new BigDecimal(12.12).setScale(8, RoundingMode.HALF_UP));
        location.setLongitude(new BigDecimal(85.14).setScale(8, RoundingMode.HALF_UP));

        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-06-22", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);
        sightingDao.deleteSighting(sighting.getSightingId());
        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());

        assertEquals(null, fromDao);
    }

    /**
     * Test of updateSighting method, of class SuperHeroSightingsSightingDao.
     */
    @Test
    public void testUpdateSighting() {

        Location location = new Location();
        location.setLocationName("Bat Cave");
        location.setLocationDescription("A cave with bats");
        location.setLocationStreet("123 Underground Ct");
        location.setLocationCity("WetDampPlace");
        location.setLocationState("Below");
        location.setLocationZip("12345");
        location.setLocationCountry("USA");
        location.setLatitude(new BigDecimal(12.12).setScale(8, RoundingMode.HALF_UP));
        location.setLongitude(new BigDecimal(85.14).setScale(8, RoundingMode.HALF_UP));

        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2020-10-22", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);

        sighting.setSightingDate(LocalDate.parse("2020-10-10", DateTimeFormatter.ISO_DATE));
        sightingDao.updateSighting(sighting);
        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());

        assertEquals("2020-10-10", fromDao.getSightingDate().toString());
    }

    /**
     * Test of getSightingsByLocationId method, of class
     * SuperHeroSightingsSightingDao.
     */
    @Test
    public void testGetSightingsByLocationId() {
        Location location = new Location();
        location.setLocationName("Empire State Building");
        location.setLocationDescription("Really tall building.");
        location.setLocationStreet("123 Some St");
        location.setLocationCity("New York");
        location.setLocationState("New York");
        location.setLocationZip("12345");
        location.setLocationCountry("USA");
        location.setLatitude(new BigDecimal(45.12).setScale(8, RoundingMode.HALF_UP));
        location.setLongitude(new BigDecimal(175.14).setScale(8, RoundingMode.HALF_UP));

        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingDate(LocalDate.parse("2020-05-19", DateTimeFormatter.ISO_DATE));
        sighting2.setLocation(location);

        sightingDao.addSighting(sighting2);

        List<Sighting> fromDao = sightingDao.getSightingsByLocationId(location.getLocationId());
        assertEquals(2, fromDao.size());
    }

    /**
     * Test of getAllSightings method, of class SuperHeroSightingsSightingDao.
     */
    @Test
    public void testGetAllSightings() {

        Location location = new Location();
        location.setLocationName("Empire State Building");
        location.setLocationDescription("Really tall building.");
        location.setLocationStreet("123 Some St");
        location.setLocationCity("New York");
        location.setLocationState("New York");
        location.setLocationZip("12345");
        location.setLocationCountry("USA");
        location.setLatitude(new BigDecimal(45.12).setScale(8, RoundingMode.HALF_UP));
        location.setLongitude(new BigDecimal(175.14).setScale(8, RoundingMode.HALF_UP));

        locationDao.addLocation(location);

        Location location2 = new Location();
        location2.setLocationName("Empire State Building");
        location2.setLocationDescription("Really tall building.");
        location2.setLocationStreet("123 Some St");
        location2.setLocationCity("New York");
        location2.setLocationState("New York");
        location2.setLocationZip("12345");
        location2.setLocationCountry("USA");
        location2.setLatitude(new BigDecimal(45.12).setScale(8, RoundingMode.HALF_UP));
        location2.setLongitude(new BigDecimal(175.14).setScale(8, RoundingMode.HALF_UP));

        locationDao.addLocation(location2);

        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);

        Sighting sighting2 = new Sighting();
        sighting2.setSightingDate(LocalDate.parse("2020-05-19", DateTimeFormatter.ISO_DATE));
        sighting2.setLocation(location2);

        sightingDao.addSighting(sighting2);

        List<Sighting> fromDao = sightingDao.getAllSightings();
        assertEquals(2, fromDao.size());

    }

}
