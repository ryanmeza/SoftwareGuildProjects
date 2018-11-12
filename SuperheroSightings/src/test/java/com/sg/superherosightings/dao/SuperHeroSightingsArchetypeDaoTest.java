/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Archetype;
import com.sg.superherosightings.model.CharacterType;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superpower;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
 * @author Ryan
 */
public class SuperHeroSightingsArchetypeDaoTest {

    SuperHeroSightingsArchetypeDao archetypeDao;
    SuperHeroSightingsCharacterTypeDao characterTypeDao;
    SuperHeroSightingsOrganizationDao organizationDao;
    SuperHeroSightingsSuperpowerDao superpowerDao;
    SuperHeroSightingsSightingDao sightingDao;
    SuperHeroSightingsLocationDao locationDao;

    public SuperHeroSightingsArchetypeDaoTest() {
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

        archetypeDao
                = ctx.getBean("archetypeDao", SuperHeroSightingsArchetypeDao.class);

        characterTypeDao
                = ctx.getBean("characterTypeDao", SuperHeroSightingsCharacterTypeDao.class);

        organizationDao
                = ctx.getBean("organizationDao", SuperHeroSightingsOrganizationDao.class);

        locationDao
                = ctx.getBean("locationDao", SuperHeroSightingsLocationDao.class);

        superpowerDao
                = ctx.getBean("superpowerDao", SuperHeroSightingsSuperpowerDao.class);

        sightingDao
                = ctx.getBean("sightingDao", SuperHeroSightingsSightingDao.class);

        //delete all Archetypes
        List<Archetype> archetypes = archetypeDao.getAllArchetypes();
        for (Archetype currentArchetype : archetypes) {
            archetypeDao.deleteArchetype(currentArchetype.getArchetypeId());
        }
        //delete all Superpowers
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for (Superpower currentSuperpower : superpowers) {
            superpowerDao.deleteSuperpower(currentSuperpower.getSuperpowerId());
        }

        //delete all sightings
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingDao.deleteSighting(currentSighting.getSightingId());
        }
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

        //delete all character types
        List<CharacterType> characterTypes = characterTypeDao.getAllCharacterTypes();
        for (CharacterType currentType : characterTypes) {
            characterTypeDao.deleteCharacterType(currentType.getCharacterTypeId());
        }
    }

    @After
    public void tearDown() {
        //delete all Archetypes
        List<Archetype> archetypes = archetypeDao.getAllArchetypes();
        for (Archetype currentArchetype : archetypes) {
            archetypeDao.deleteArchetype(currentArchetype.getArchetypeId());
        }
        //delete all Superpowers
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for (Superpower currentSuperpower : superpowers) {
            superpowerDao.deleteSuperpower(currentSuperpower.getSuperpowerId());
        }

        //delete all sightings
        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            sightingDao.deleteSighting(currentSighting.getSightingId());
        }
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
     * Test of addArchetype method, of class SuperHeroSightingsArchetypeDao.
     */
    @Test
    public void testAddGetArchetype() {
        // id, name, description, character-type object, list org, list superpower, list sighting

        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        
        archetypeDao.addArchetype(archetype);
        
        Archetype fromDao = archetypeDao.getArchetypeById(archetype.getArchetypeId());
        
        assertEquals(fromDao, archetype);

    }

    /**
     * Test of deleteArchetype method, of class SuperHeroSightingsArchetypeDao.
     */
    @Test
    public void testDeleteArchetype() {
        
        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        
        archetypeDao.addArchetype(archetype);
        archetypeDao.deleteArchetype(archetype.getArchetypeId());
        Archetype fromDao = archetypeDao.getArchetypeById(archetype.getArchetypeId());
        
        assertEquals(null, fromDao);
    }
    

    /**
     * Test of updateArchetype method, of class SuperHeroSightingsArchetypeDao.
     */
    @Test
    public void testUpdateArchetype() {
        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        archetypeDao.addArchetype(archetype);
        
        archetype.setArchetypeName("Harley Quinn");
        archetypeDao.updateArchetype(archetype);
        
        Archetype fromDao = archetypeDao.getArchetypeById(archetype.getArchetypeId());
        
        assertEquals("Harley Quinn", fromDao.getArchetypeName());
    }


    /**
     * Test of getAllArchetypes method, of class SuperHeroSightingsArchetypeDao.
     */
    @Test
    public void testGetAllArchetypes() {
        
        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        
        archetypeDao.addArchetype(archetype);
        
        // SECOND ARCHETYPE --------------
        
        //Character Type
        CharacterType characterType2 = new CharacterType();
        characterType2.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType2);

        //Superpower
        Superpower superpower2 = new Superpower();
        superpower2.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower2);

        //Location 
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

        //Organization
        Organization organization2 = new Organization();
        organization2.setOrganizationName("Suicide Squad");
        organization2.setOrganizationDescription("Bad guys forced to be good.");
        organization2.setOrganizationPhone("888-812-8080");
        organization2.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization2.setLocation(location2);
        organizationDao.addOrganization(organization2);

        //Sighting
        Sighting sighting2 = new Sighting();
        sighting2.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting2.setLocation(location2);
        sightingDao.addSighting(sighting2);

        //Archetype
        Archetype archetype2 = new Archetype();
        archetype2.setArchetypeName("Spider-Man");
        archetype2.setArchetypeDescription("Red and blue, spider webs.");
        archetype2.setCharacterType(characterType2);

        List<Organization> organizations2 = new ArrayList<>();
        organizations2.add(organization2);
        archetype2.setArchetypeOrganizations(organizations2);

        List<Superpower> superpowers2 = new ArrayList<>();
        superpowers2.add(superpower2);
        archetype2.setArchetypeSuperpowers(superpowers2);

        List<Sighting> sightings2 = new ArrayList<>();
        sightings2.add(sighting2);
        archetype2.setArchetypeSightings(sightings2);
        
        archetypeDao.addArchetype(archetype2);
        
        List<Archetype> fromDao = archetypeDao.getAllArchetypes();
        assertEquals(2, fromDao.size());
        

    }

    /**
     * Test of getArchetypeByOrganizationId method, of class
     * SuperHeroSightingsArchetypeDao.
     */
    @Test
    public void testGetArchetypeByOrganizationId() {
        
        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        
        archetypeDao.addArchetype(archetype);
        
        List<Archetype> fromDao = archetypeDao.getArchetypeByOrganizationId(organization.getOrganizationId());
        assertEquals(1, fromDao.size());
    }

    /**
     * Test of getArchetypeBySuperpowerId method, of class
     * SuperHeroSightingsArchetypeDao.
     */
    @Test
    public void testGetArchetypeBySuperpowerId() {
        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        
        archetypeDao.addArchetype(archetype);
        
        List<Archetype> fromDao = archetypeDao.getArchetypeBySuperpowerId(superpower.getSuperpowerId());
        assertEquals(1, fromDao.size());
    }

    /**
     * Test of getArchetypeBySightingId method, of class
     * SuperHeroSightingsArchetypeDao.
     */
    @Test
    public void testGetArchetypeBySightingId() {
        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        
        archetypeDao.addArchetype(archetype);
        
        List<Archetype> fromDao = archetypeDao.getArchetypeBySightingId(sighting.getSightingId());
        assertEquals(1, fromDao.size());
    }

    /**
     * Test of getArchetypeByCharacterTypeId method, of class
     * SuperHeroSightingsArchetypeDao.
     */
    @Test
    public void testGetArchetypeByCharacterTypeId() {
        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        
        archetypeDao.addArchetype(archetype);
        
        List<Archetype> fromDao = archetypeDao.getArchetypeByCharacterTypeId(characterType.getCharacterTypeId());
        assertEquals(1, fromDao.size());
    }

    @Test
    public void testGetSightingsWithArchetypesByDate() {
        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        archetypeDao.addArchetype(archetype);
        
        List<Archetype> fromDao = 
                archetypeDao.getSightingsWithArchetypesByDate(sighting.getSightingDate());
        
        assertEquals(1, fromDao.size());
    }
    
    @Test
    public void testGetArchetypesByLocationId() {
        
    //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        archetypeDao.addArchetype(archetype);
        
        List<Archetype> fromDao = 
                archetypeDao.getArchetypesByLocationId(location.getLocationId());
        assertEquals(1, fromDao.size());
    }
    
    @Test
    public void testGetSightingLocationsByArchetypeId() {
        
        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        archetypeDao.addArchetype(archetype);
        
        List<Archetype> fromDao = 
                archetypeDao.getSightingLocationsByArchetypeId(archetype.getArchetypeId());
        assertEquals(1, fromDao.size());
    }
    
    @Test
    public void testGetOrganizationsByArchetypeId() {
        //Character Type
        CharacterType characterType = new CharacterType();
        characterType.setCharacterTypeName("Hero");
        characterTypeDao.addCharacterType(characterType);

        //Superpower
        Superpower superpower = new Superpower();
        superpower.setSuperpowerType("Telekinetic");
        superpowerDao.addSuperpower(superpower);

        //Location 
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

        //Organization
        Organization organization = new Organization();
        organization.setOrganizationName("Suicide Squad");
        organization.setOrganizationDescription("Bad guys forced to be good.");
        organization.setOrganizationPhone("888-812-8080");
        organization.setOrganizationEmail("suicidesquadfanmail@gmail.com");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        //Sighting
        Sighting sighting = new Sighting();
        sighting.setSightingDate(LocalDate.parse("2019-05-19", DateTimeFormatter.ISO_DATE));
        sighting.setLocation(location);
        sightingDao.addSighting(sighting);

        //Archetype
        Archetype archetype = new Archetype();
        archetype.setArchetypeName("Spider-Man");
        archetype.setArchetypeDescription("Red and blue, spider webs.");
        archetype.setCharacterType(characterType);

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        archetype.setArchetypeOrganizations(organizations);

        List<Superpower> superpowers = new ArrayList<>();
        superpowers.add(superpower);
        archetype.setArchetypeSuperpowers(superpowers);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);
        archetype.setArchetypeSightings(sightings);
        archetypeDao.addArchetype(archetype);
        
    }
}
