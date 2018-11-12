/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
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
public class SuperHeroSightingsOrganizationDaoTest {

    SuperHeroSightingsOrganizationDao organizationDao;
    SuperHeroSightingsLocationDao locationDao;

    public SuperHeroSightingsOrganizationDaoTest() {
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

        organizationDao = ctx.getBean("organizationDao", SuperHeroSightingsOrganizationDao.class);

        locationDao = ctx.getBean("locationDao", SuperHeroSightingsLocationDao.class);

        //delete all organizations
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            organizationDao.deleteOrganization(currentOrganization.getOrganizationId());
        }

        //delete all locations
        List<Location> locations = locationDao.getAllLocations();
        for (Location currentLocation : locations) {
            locationDao.deleteLocation(currentLocation.getLocationId());
        }
    }

    @After
    public void tearDown() {

        //delete all organizations
        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization currentOrganization : organizations) {
            organizationDao.deleteOrganization(currentOrganization.getOrganizationId());
        }

        //delete all locations
        List<Location> locations = locationDao.getAllLocations();
        for (Location currentLocation : locations) {
            locationDao.deleteLocation(currentLocation.getLocationId());
        }
    }

    /**
     * Test of addOrganization method, of class
     * SuperHeroSightingsOrganizationDao.
     */
    @Test
    public void testAddGetOrganization() {
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

        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);

        organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getOrganizationId());
        assertEquals(organization, fromDao);
    }

    /**
     * Test of deleteOrganization method, of class
     * SuperHeroSightingsOrganizationDao.
     */
    @Test
    public void testDeleteOrganization() {
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

        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);

        organizationDao.addOrganization(organization);

        organizationDao.deleteOrganization(organization.getOrganizationId());

        Organization fromDao = organizationDao.getOrganizationById(organization.getOrganizationId());
        assertEquals(null, fromDao);
    }

    /**
     * Test of updateOrganization method, of class
     * SuperHeroSightingsOrganizationDao.
     */
    @Test
    public void testUpdateOrganization() {
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

        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);

        organizationDao.addOrganization(organization);

        organization.setOrganizationName("Sears Tower");
        organizationDao.updateOrganization(organization);

        Organization fromDao = organizationDao.getOrganizationById(organization.getOrganizationId());
        assertEquals("Sears Tower", fromDao.getOrganizationName());
    }

    /**
     * Test of getOrganizationsByLocationId method, of class
     * SuperHeroSightingsOrganizationDao.
     */
    @Test
    public void testGetOrganizationsByLocationId() {
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

        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);

        organizationDao.addOrganization(organization);

        Organization organization2 = new Organization();
        organization2.setOrganizationName("Suicide Squad");
        organization2.setOrganizationDescription("Bad guys forced to be good.");
        organization2.setOrganizationPhone("888-812-8080");
        organization2.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization2.setLocation(location);

        organizationDao.addOrganization(organization2);

        List<Organization> fromDao = organizationDao.getOrganizationsByLocationId(location.getLocationId());
        assertEquals(2, fromDao.size());
    }

    /**
     * Test of getAllOrganizations method, of class
     * SuperHeroSightingsOrganizationDao.
     */
    @Test
    public void testGetAllOrganizations() {

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

        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);

        organizationDao.addOrganization(organization);

        Organization organization2 = new Organization();
        organization2.setOrganizationName("Suicide Squad");
        organization2.setOrganizationDescription("Bad guys forced to be good.");
        organization2.setOrganizationPhone("888-812-8080");
        organization2.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization2.setLocation(location);

        organizationDao.addOrganization(organization2);

        List<Organization> fromDao = organizationDao.getAllOrganizations();
        assertEquals(2, fromDao.size());
    }

}
