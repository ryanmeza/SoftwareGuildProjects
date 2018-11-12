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
public class SuperHeroSightingsLocationDaoTest {

    SuperHeroSightingsLocationDao locationDao;

    SuperHeroSightingsSightingDao sightingDao;

    public SuperHeroSightingsLocationDaoTest() {
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

        locationDao = ctx.getBean("locationDao", SuperHeroSightingsLocationDao.class);
        
        sightingDao = ctx.getBean("sightingDao", SuperHeroSightingsSightingDao.class);

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
     * Test of addLocation method, of class SuperHeroSightingsLocationDao.
     */
    @Test
    public void testAddLocation() {
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

        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(location, fromDao);
    }

    /**
     * Test of deleteLocation method, of class SuperHeroSightingsLocationDao.
     */
    @Test
    public void testDeleteLocation() {
        Location park = new Location();

        park.setLocationName("Park Bench");
        park.setLocationDescription("Some random bench.");
        park.setLocationStreet("456 Some Pl");
        park.setLocationCity("Central Park");
        park.setLocationState("New York");
        park.setLocationZip("12345");
        park.setLocationCountry("Merica");
        park.setLatitude(new BigDecimal(11.568).setScale(8, RoundingMode.HALF_UP));
        park.setLongitude(new BigDecimal(42.69).setScale(8, RoundingMode.HALF_UP));

        locationDao.addLocation(park);
        locationDao.deleteLocation(park.getLocationId());
        Location fromDao = locationDao.getLocationById(park.getLocationId());

        assertEquals(null, fromDao);
    }

    /**
     * Test of updateLocation method, of class SuperHeroSightingsLocationDao.
     */
    @Test
    public void testUpdateLocation() {
        Location park = new Location();
        park.setLocationName("Park Bench");
        park.setLocationDescription("Some random bench.");
        park.setLocationStreet("456 Some Pl");
        park.setLocationCity("Central Park");
        park.setLocationState("New York");
        park.setLocationZip("12345");
        park.setLocationCountry("Merica");
        park.setLatitude(new BigDecimal(11.568).setScale(8, RoundingMode.HALF_UP));
        park.setLongitude(new BigDecimal(42.69).setScale(8, RoundingMode.HALF_UP));

        locationDao.addLocation(park);
        park.setLocationName("BirdBath");
        locationDao.updateLocation(park);
        Location fromDao = locationDao.getLocationById(park.getLocationId());

        assertEquals("BirdBath", fromDao.getLocationName());
    }

    /**
     * Test of getLocationById method, of class SuperHeroSightingsLocationDao.
     */
    @Test
    public void testGetLocationById() {
        Location park = new Location();
        Location birdbath = new Location();
        int expectedLocationId;

        birdbath.setLocationName("BirdBath");
        birdbath.setLocationDescription("Birds bathe here.");
        birdbath.setLocationStreet("555 Some St");
        birdbath.setLocationCity("Central Park");
        birdbath.setLocationState("New York");
        birdbath.setLocationZip("12345");
        birdbath.setLocationCountry("USA");
        birdbath.setLatitude(new BigDecimal(54.568).setScale(8, RoundingMode.HALF_UP));
        birdbath.setLongitude(new BigDecimal(11.69).setScale(8, RoundingMode.HALF_UP));

        park.setLocationName("Park Bench");
        park.setLocationDescription("Some random bench.");
        park.setLocationStreet("456 Some Pl");
        park.setLocationCity("Parky Park");
        park.setLocationState("Kansas");
        park.setLocationZip("45689");
        park.setLocationCountry("Merica");
        park.setLatitude(new BigDecimal(11.568).setScale(8, RoundingMode.HALF_UP));
        park.setLongitude(new BigDecimal(42.69).setScale(8, RoundingMode.HALF_UP));

        locationDao.addLocation(park);
        expectedLocationId = park.getLocationId();
        locationDao.addLocation(birdbath);

        Location fromDao = locationDao.getLocationById(expectedLocationId);
        assertEquals(park, fromDao);
    }

    /**
     * Test of getAllLocations method, of class SuperHeroSightingsLocationDao.
     */
    @Test
    public void testGetAllLocations() {
        Location birdbath = new Location();
        Location park = new Location();

        birdbath.setLocationName("BirdBath");
        birdbath.setLocationDescription("Birds bathe here.");
        birdbath.setLocationStreet("555 Some St");
        birdbath.setLocationCity("Central Park");
        birdbath.setLocationState("New York");
        birdbath.setLocationZip("12345");
        birdbath.setLocationCountry("USA");
        birdbath.setLatitude(new BigDecimal(54.568).setScale(8, RoundingMode.HALF_UP));
        birdbath.setLongitude(new BigDecimal(11.69).setScale(8, RoundingMode.HALF_UP));

        park.setLocationName("Park Bench");
        park.setLocationDescription("Some random bench.");
        park.setLocationStreet("456 Some Pl");
        park.setLocationCity("Parky Park");
        park.setLocationState("Kansas");
        park.setLocationZip("45689");
        park.setLocationCountry("Merica");
        park.setLatitude(new BigDecimal(11.568).setScale(8, RoundingMode.HALF_UP));
        park.setLongitude(new BigDecimal(42.69).setScale(8, RoundingMode.HALF_UP));

        locationDao.addLocation(birdbath);
        locationDao.addLocation(park);
        List<Location> fromDao = locationDao.getAllLocations();

        assertEquals(2, fromDao.size());
    }

}
