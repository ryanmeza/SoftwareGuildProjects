/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.SuperHeroSightingsCharacterTypeDaoImpl.CharacterTypeMapper;
import com.sg.superherosightings.dao.SuperHeroSightingsSuperpowerDaoImpl.SuperpowerMapper;
import com.sg.superherosightings.model.Archetype;
import com.sg.superherosightings.model.CharacterType;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import com.sg.superherosightings.model.Sighting;
import com.sg.superherosightings.model.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ryan
 */
public class SuperHeroSightingsArchetypeDaoImpl extends SuperHeroSightingsBaseDao implements SuperHeroSightingsArchetypeDao {
    // =========================================================================
    // ===================== PREPARED STATEMENTS ===============================
    // =========================================================================

    private static final class ArchetypeSqlQueries {
        // =====================================================================
        // INSERT Prepared Statements
        // =====================================================================

        private static final String INSERT_ARCHETYPE
                = "INSERT INTO Archetypes (archetype_name, archetype_description, "
                + "character_type_id) "
                + "VALUES (?, ?, ?)";

        private static final String INSERT_ARCHETYPES_ORGANIZATIONS
                = "INSERT INTO Archetypes_Organizations (archetype_id, organization_id) "
                + "VALUES (?, ?)";

        private static final String INSERT_ARCHETYPES_SUPERPOWERS
                = "INSERT INTO Archetypes_Superpowers (archetype_id, superpower_id) "
                + "VALUES (?, ?)";

        private static final String INSERT_ARCHETYPES_SIGHTINGS
                = "INSERT INTO Archetypes_Sightings (archetype_id, sighting_id) "
                + "VALUES (?, ?)";

        // =====================================================================
        // DELETE Prepared Statements
        // =====================================================================
        private static final String DELETE_ARCHETYPE
                = "DELETE FROM Archetypes WHERE archetype_id = ?";

        private static final String DELETE_ARCHETYPES_ORGANIZATIONS
                = "DELETE FROM Archetypes_Organizations WHERE archetype_id = ?";

        private static final String DELETE_ARCHETYPES_SUPERPOWERS
                = "DELETE FROM Archetypes_Superpowers WHERE archetype_id = ?";

        private static final String DELETE_ARCHETYPES_SIGHTINGS
                = "DELETE FROM Archetypes_Sightings WHERE archetype_id = ?";

        // =====================================================================
        // UPDATE Prepared Statements
        // =====================================================================
        private static final String UPDATE_ARCHETYPE
                = "UPDATE Archetypes SET archetype_name = ?, "
                + "archetype_description = ?, character_type_id = ? "
                + "WHERE archetype_id = ?";

        // =====================================================================
        // SELECT Prepared Statements
        // =====================================================================
        private static final String SELECT_ARCHETYPE
                = "SELECT * FROM Archetypes AS a "
                + "INNER JOIN Character_Types AS c "
                + "ON a.character_type_id = c.character_type_id "
                + "WHERE archetype_id = ?";

        private static final String SELECT_ARCHETYPES_BY_ORGANIZATION_ID
                = "SELECT a.archetype_id, a.archetype_name, a.archetype_description, "
                + "c.character_type_id, c.character_type_name FROM Archetypes AS a "
                + "INNER JOIN Character_Types AS c "
                + "ON a.character_type_id = c.character_type_id "
                + "INNER JOIN Archetypes_Organizations AS ao "
                + "ON a.archetype_id = ao.archetype_id "
                + "WHERE a.archetype_id = ao.archetype_id "
                + "AND ao.organization_id = ?";

        private static final String SELECT_ARCHETYPES_BY_SUPERPOWER_ID
                = "SELECT a.archetype_id, a.archetype_name, a.archetype_description, "
                + "c.character_type_id, c.character_type_name FROM Archetypes AS a "
                + "INNER JOIN Character_Types AS c "
                + "ON a.character_type_id = c.character_type_id "
                + "INNER JOIN Archetypes_Superpowers AS asp "
                + "ON a.archetype_id = asp.archetype_id "
                + "WHERE a.archetype_id = asp.archetype_id "
                + "AND asp.superpower_id = ?";

        private static final String SELECT_ARCHETYPES_BY_SIGHTING_ID
                = "SELECT a.archetype_id, a.archetype_name, a.archetype_description, "
                + "c.character_type_id, c.character_type_name FROM Archetypes AS a "
                + "INNER JOIN Character_Types AS c "
                + "ON a.character_type_id = c.character_type_id "
                + "INNER JOIN Archetypes_Sightings AS ast "
                + "ON a.archetype_id = ast.archetype_id "
                + "INNER JOIN Sightings AS s "
                + "ON ast.sighting_id = s.sighting_id "
                + "WHERE a.archetype_id = ast.archetype_id "
                + "AND ast.sighting_id = ?";

        private static final String SELECT_ALL_ARCHETYPES
                = "SELECT * FROM Archetypes AS a "
                + "INNER JOIN Character_Types AS c "
                + "ON a.character_type_id = c.character_type_id ";

        private static final String SELECT_ORGANIZATIONS_BY_ARCHETYPE_ID
                = "SELECT * FROM Organizations AS o "
                + "INNER JOIN Locations AS l "
                + "ON o.location_id = l.location_id "
                + "INNER JOIN Archetypes_Organizations AS ao "
                + "ON o.organization_id = ao.organization_id "
                + "INNER JOIN Archetypes AS a "
                + "ON ao.archetype_id = a.archetype_id "
                + "WHERE a.archetype_id = ?";

        private static final String SELECT_SUPERPOWERS_BY_ARCHETYPE_ID
                = "SELECT sup.* FROM Superpowers AS sup "
                + "INNER JOIN Archetypes_Superpowers AS asup "
                + "ON sup.superpower_id = asup.superpower_id "
                + "WHERE asup.archetype_id = ?";

        private static final String SELECT_SIGHTINGS_BY_ARCHETYPE_ID
                = "SELECT * FROM Sightings AS s "
                + "INNER JOIN Locations AS l "
                + "ON s.location_id = l.location_id "
                + "INNER JOIN Archetypes_Sightings AS ast "
                + "ON s.sighting_id = ast.sighting_id "
                + "WHERE ast.archetype_id = ?";

        // =====================================================================
        // Specific SELECT Type Prepared Statements
        // =====================================================================
        private static final String SELECT_SIGHTINGS_BY_SIGHTING_DATE
                = "SELECT * FROM Sightings AS s "
                + "INNER JOIN Locations AS l "
                + "ON s.location_Id = l.location_id "
                + "INNER JOIN Archetypes_Sightings AS ast "
                + "ON s.sighting_id = ast.sighting_id "
                + "INNER JOIN Archetypes AS a "
                + "ON ast.archetype_id = a.archetype_id "
                + "WHERE s.sighting_date = ?";

        private static final String SELECT_ARCHETYPES_BY_LOCATION_ID
                = "SELECT * FROM Locations AS l "
                + "INNER JOIN Sightings AS s "
                + "ON l.location_id = s.location_id "
                + "INNER JOIN Archetypes_Sightings AS ast "
                + "ON s.sighting_id = ast.sighting_id "
                + "INNER JOIN Archetypes AS a "
                + "ON ast.archetype_id = a.archetype_id "
                + "WHERE l.location_id = ?";

        private static final String SELECT_SIGHTING_LOCATIONS_BY_ARCHETYPE_ID
                = "SELECT * FROM Archetypes AS a "
                + "INNER JOIN Character_Types AS c "
                + "ON a.character_type_id = c.character_type_id "
                + "INNER JOIN Archetypes_Sightings AS ast "
                + "ON a.archetype_id = ast.archetype_id "
                + "INNER JOIN Sightings AS s "
                + "ON ast.sighting_id = s.sighting_id "
                + "INNER JOIN Locations AS l "
                + "ON s.location_id = l.location_id "
                + "WHERE a.archetype_id = ?";

        // =====================================================================
        // Archetype-Character Type Prepared Statements
        // =====================================================================
        private static final String SELECT_CHARACTER_TYPE_BY_ARCHETYPE_ID
                = "SELECT c.* FROM Character_Types AS c "
                + "INNER JOIN Archetypes AS a "
                + "ON c.character_type_id = a.character_type_id "
                + "WHERE a.archetype_id = ?";

        private static final String SELECT_ARCHETYPES_BY_CHARACTER_TYPE_ID
                = "SELECT * FROM Archetypes WHERE character_type_id = ?";

    }

    // =========================================================================
    // ====================== ARCHETYPE CRUD METHODS ===========================
    // =========================================================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addArchetype(Archetype archetype) {
        jdbcTemplate.update(ArchetypeSqlQueries.INSERT_ARCHETYPE,
                archetype.getArchetypeName(),
                archetype.getArchetypeDescription(),
                archetype.getCharacterType().getCharacterTypeId());
        archetype.setArchetypeId(jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class));

        // update archetypes_organizations table
        insertArchetypeOrganizations(archetype);
        // update archetypes_superpowers table
        insertArchetypeSuperpowers(archetype);
        // update archetypes_sightings table
        insertArchetypeSightings(archetype);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteArchetype(int archetypeId) {

        // delete archetype_organization relationship for this archetype
        jdbcTemplate.update(ArchetypeSqlQueries.DELETE_ARCHETYPES_ORGANIZATIONS, archetypeId);

        // delete archetype_superpower relationship for this archetype
        jdbcTemplate.update(ArchetypeSqlQueries.DELETE_ARCHETYPES_SUPERPOWERS, archetypeId);

        // delete archetype_sighting relationship for this archetype
        jdbcTemplate.update(ArchetypeSqlQueries.DELETE_ARCHETYPES_SIGHTINGS, archetypeId);

        // delete archetype
        jdbcTemplate.update(ArchetypeSqlQueries.DELETE_ARCHETYPE, archetypeId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateArchetype(Archetype archetype) {

        jdbcTemplate.update(ArchetypeSqlQueries.UPDATE_ARCHETYPE,
                archetype.getArchetypeName(),
                archetype.getArchetypeDescription(),
                archetype.getCharacterType().getCharacterTypeId(),
                archetype.getArchetypeId());

        //DELETE THEN RESET RELATIONSHIPS
        // archetype_organization relationship 
        jdbcTemplate.update(ArchetypeSqlQueries.DELETE_ARCHETYPES_ORGANIZATIONS, archetype.getArchetypeId());
        insertArchetypeOrganizations(archetype);

        // archetype_superpower relationship
        jdbcTemplate.update(ArchetypeSqlQueries.DELETE_ARCHETYPES_SUPERPOWERS, archetype.getArchetypeId());
        insertArchetypeSuperpowers(archetype);

        // archetype_sighting relationship
        jdbcTemplate.update(ArchetypeSqlQueries.DELETE_ARCHETYPES_SIGHTINGS, archetype.getArchetypeId());
        insertArchetypeSightings(archetype);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Archetype getArchetypeById(int archetypeId) {
        try {
            Archetype archetype
                    = jdbcTemplate.queryForObject(ArchetypeSqlQueries.SELECT_ARCHETYPE,
                            new ArchetypeMapper(), archetypeId);

            // get the organizations and set list
            archetype.setArchetypeOrganizations(findOrganizationsForArchetype(archetype));

            // get the superpowers and set list
            archetype.setArchetypeSuperpowers(findSuperpowersForArchetype(archetype));

            // get the sightings and set list
            archetype.setArchetypeSightings(findSightingsForArchetype(archetype));

            // get the character type 
            archetype.setCharacterType(findCharacterTypeForArchetype(archetype));

            return archetype;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Archetype> getAllArchetypes() {

        List<Archetype> archetypeList = jdbcTemplate.query(ArchetypeSqlQueries.SELECT_ALL_ARCHETYPES,
                new ArchetypeMapper());

        associateOrganizationWithArchetype(archetypeList);
        associateSuperpowerWithArchetype(archetypeList);
        associateSightingWithArchetype(archetypeList);
        associateCharacterTypeWithArchetype(archetypeList);
        return archetypeList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Archetype> getArchetypeByOrganizationId(int organizationId) {
        List<Archetype> archetypeList
                = jdbcTemplate.query(ArchetypeSqlQueries.SELECT_ARCHETYPES_BY_ORGANIZATION_ID,
                        new ArchetypeMapper(), organizationId);
        return associateOrganizationWithArchetype(archetypeList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Archetype> getArchetypeBySuperpowerId(int superpowerId) {
        List<Archetype> archetypeList
                = jdbcTemplate.query(ArchetypeSqlQueries.SELECT_ARCHETYPES_BY_SUPERPOWER_ID,
                        new ArchetypeMapper(), superpowerId);
        return associateSuperpowerWithArchetype(archetypeList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Archetype> getArchetypeBySightingId(int sightingId) {
        List<Archetype> archetypeList
                = jdbcTemplate.query(ArchetypeSqlQueries.SELECT_ARCHETYPES_BY_SIGHTING_ID,
                        new ArchetypeMapper(), sightingId);
        return associateSightingWithArchetype(archetypeList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Archetype> getArchetypeByCharacterTypeId(int characterTypeId) {
        //get the archetypes by characterTypes ids
        List<Archetype> archetypeList
                = jdbcTemplate.query(ArchetypeSqlQueries.SELECT_ARCHETYPES_BY_CHARACTER_TYPE_ID,
                        new ArchetypeMapper(), characterTypeId);

        return associateCharacterTypeWithArchetype(archetypeList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Archetype> getSightingsWithArchetypesByDate(LocalDate sightingdate) {
        List<Archetype> archetypeList = jdbcTemplate.query(ArchetypeSqlQueries.SELECT_SIGHTINGS_BY_SIGHTING_DATE,
                new ArchetypeMapper(), sightingdate.toString());
        return associateSightingWithArchetype(archetypeList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Archetype> getArchetypesByLocationId(int locationId) {
        List<Archetype> archetypeList = jdbcTemplate.query(ArchetypeSqlQueries.SELECT_ARCHETYPES_BY_LOCATION_ID,
                new ArchetypeMapper(), locationId);
        return associateSightingWithArchetype(archetypeList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Archetype> getSightingLocationsByArchetypeId(int archetypeId) {
        List<Archetype> archetypeList
                = jdbcTemplate.query(ArchetypeSqlQueries.SELECT_SIGHTING_LOCATIONS_BY_ARCHETYPE_ID, new ArchetypeMapper(), archetypeId);
        return associateSightingWithArchetype(archetypeList);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Archetype> getOrganizationsByArchetypeId(List<Archetype> archetypeList) {
        List<Archetype> archetypes = associateOrganizationWithArchetype(archetypeList);
        return archetypes;
    }

    // =========================================================================
    // ========================= ARCHETYPE MAPPER ==============================
    // =========================================================================
    public static final class ArchetypeMapper implements RowMapper<Archetype> {

        @Override
        public Archetype mapRow(ResultSet rs, int i) throws SQLException {
            Archetype archetype = new Archetype();
            archetype.setArchetypeId(rs.getInt("archetype_id"));
            archetype.setArchetypeName(rs.getString("archetype_name"));
            archetype.setArchetypeDescription(rs.getString("archetype_description"));

            return archetype;
        }

    }

    // =========================================================================
    // ========================== HELPER METHODS ===============================
    // =========================================================================
    // Helpers for Archetypes-Organizations relationships
    private void insertArchetypeOrganizations(Archetype archetype) {

        final int archetypeId = archetype.getArchetypeId();
        final List<Organization> organizations = archetype.getArchetypeOrganizations();

        for (Organization currentOrganization : organizations) {
            jdbcTemplate.update(ArchetypeSqlQueries.INSERT_ARCHETYPES_ORGANIZATIONS,
                    archetypeId, currentOrganization.getOrganizationId());
        }

    }

    private List<Organization> findOrganizationsForArchetype(Archetype archetype) {
        List<Organization> organizations = jdbcTemplate.query(ArchetypeSqlQueries.SELECT_ORGANIZATIONS_BY_ARCHETYPE_ID,
                new OrganizationWithLocationMapper(), archetype.getArchetypeId());

        return organizations;
    }

    private List<Archetype> associateOrganizationWithArchetype(List<Archetype> archetypeList) {
        for (Archetype currentArchetype : archetypeList) {
            currentArchetype.setArchetypeOrganizations(findOrganizationsForArchetype(currentArchetype));
        }
        return archetypeList;
    }

    // ========================================================================= 
    // Helpers for Archetypes-Superpowers relationships
    // ========================================================================= 
    private void insertArchetypeSuperpowers(Archetype archetype) {

        final int archetypeId = archetype.getArchetypeId();
        final List<Superpower> superpowers = archetype.getArchetypeSuperpowers();

        for (Superpower currentSuperpower : superpowers) {
            jdbcTemplate.update(ArchetypeSqlQueries.INSERT_ARCHETYPES_SUPERPOWERS,
                    archetypeId, currentSuperpower.getSuperpowerId());
        }

    }

    private List<Superpower> findSuperpowersForArchetype(Archetype archetype) {
        return jdbcTemplate.query(ArchetypeSqlQueries.SELECT_SUPERPOWERS_BY_ARCHETYPE_ID,
                new SuperpowerMapper(), archetype.getArchetypeId());
    }

    private List<Archetype> associateSuperpowerWithArchetype(List<Archetype> archetypeList) {
        for (Archetype currentArchetype : archetypeList) {
            currentArchetype.setArchetypeSuperpowers(findSuperpowersForArchetype(currentArchetype));
        }
        return archetypeList;
    }

    // ========================================================================= 
    // Helpers for Archetypes-Sightings relationships
    // ========================================================================= 
    private void insertArchetypeSightings(Archetype archetype) {
        if (archetype.getArchetypeSightings() == null) {
            return;
        }
        final int archetypeId = archetype.getArchetypeId();
        final List<Sighting> sightings = archetype.getArchetypeSightings();

        for (Sighting currentSighting : sightings) {
            jdbcTemplate.update(ArchetypeSqlQueries.INSERT_ARCHETYPES_SIGHTINGS,
                    archetypeId, currentSighting.getSightingId());
        }
    }

    private List<Sighting> findSightingsForArchetype(Archetype archetype) {
        return jdbcTemplate.query(ArchetypeSqlQueries.SELECT_SIGHTINGS_BY_ARCHETYPE_ID,
                new SightingWithLocationMapper(), archetype.getArchetypeId());
    }

    private List<Archetype> associateSightingWithArchetype(List<Archetype> archetypeList) {
        for (Archetype currentArchetype : archetypeList) {
            currentArchetype.setArchetypeSightings(findSightingsForArchetype(currentArchetype));
        }
        return archetypeList;
    }

    // ========================================================================= 
    // Helpers for Archetype-Character Type relationships
    private CharacterType findCharacterTypeForArchetype(Archetype archetype) {
        return jdbcTemplate.queryForObject(ArchetypeSqlQueries.SELECT_CHARACTER_TYPE_BY_ARCHETYPE_ID,
                new CharacterTypeMapper(), archetype.getArchetypeId());
    }

    private List<Archetype> associateCharacterTypeWithArchetype(List<Archetype> archetypeList) {
        for (Archetype currentArchetype : archetypeList) {
            currentArchetype.setCharacterType(findCharacterTypeForArchetype(currentArchetype));
        }
        return archetypeList;
    }

    // =========================== ORGANIZATIONS MAPPER ========================
    // =========================================================================
    public static final class OrganizationWithLocationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationId(rs.getInt("organization_id"));
            organization.setOrganizationName(rs.getString("organization_name"));
            organization.setOrganizationDescription(rs.getString("organization_description"));
            organization.setOrganizationPhone(rs.getString("organization_phone"));
            organization.setOrganizationEmail(rs.getString("organization_email"));
            Location location = new Location();
            location.setLocationId(rs.getInt("location_id"));
            location.setLocationName(rs.getString("location_name"));
            location.setLocationDescription(rs.getString("location_description"));
            location.setLocationStreet(rs.getString("location_street"));
            location.setLocationCity(rs.getString("location_city"));
            location.setLocationState(rs.getString("location_state"));
            location.setLocationZip(rs.getString("location_zip"));
            location.setLocationCountry(rs.getString("location_country"));
            location.setLatitude(rs.getBigDecimal("location_latitude"));
            location.setLongitude(rs.getBigDecimal("location_longitude"));
            organization.setLocation(location);
            return organization;
        }

    }

    public static final class SightingWithLocationMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sighting_id"));
            sighting.setSightingDate(rs.getTimestamp("sighting_date").
                    toLocalDateTime().toLocalDate());
            Location location = new Location();
            location.setLocationId(rs.getInt("location_id"));
            location.setLocationName(rs.getString("location_name"));
            location.setLocationDescription(rs.getString("location_description"));
            location.setLocationStreet(rs.getString("location_street"));
            location.setLocationCity(rs.getString("location_city"));
            location.setLocationState(rs.getString("location_state"));
            location.setLocationZip(rs.getString("location_zip"));
            location.setLocationCountry(rs.getString("location_country"));
            location.setLatitude(rs.getBigDecimal("location_latitude"));
            location.setLongitude(rs.getBigDecimal("location_longitude"));
            sighting.setLocation(location);
            return sighting;
        }
    }
}
