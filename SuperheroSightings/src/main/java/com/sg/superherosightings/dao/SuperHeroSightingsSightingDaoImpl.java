/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.SuperHeroSightingsLocationDaoImpl.LocationMapper;
import com.sg.superherosightings.model.ArchetypeSighting;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ryan Meza
 */
public class SuperHeroSightingsSightingDaoImpl extends SuperHeroSightingsBaseDao implements SuperHeroSightingsSightingDao {
    
    @Override
    public List<ArchetypeSighting> getLatestSightings() {
        try {
            List<ArchetypeSighting> sighting = jdbcTemplate.query(SightingSqlQueries.SELECT_LAST_TEN_SIGHTINGS,
                    new ArchetypeSightingMapper());
            return sighting;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // ===================== PREPARED STATEMENTS ===============================
    // =========================================================================
    private static final class SightingSqlQueries {
        
        private static final String INSERT_SIGHTING
                = "INSERT INTO Sightings (sighting_date, location_id) "
                + "VALUES (?, ?)";
        
        private static final String DELETE_SIGHTING
                = "DELETE FROM Sightings WHERE sighting_id = ?";
        
        private static final String DELETE_ARCHETYPE_SIGHTING
                = "DELETE FROM Archetypes_Sightings WHERE sighting_id = ?";
        
        private static final String UPDATE_SIGHTING
                = "UPDATE Sightings SET "
                + "sighting_date = ?, location_id = ? "
                + "WHERE sighting_id = ?";
        
        private static final String SELECT_SIGHTING
                = "SELECT * FROM Sightings AS s "
                + "INNER JOIN Locations AS l "
                + "ON s.location_id = l.location_id WHERE sighting_id = ?";
        
        private static final String SELECT_ALL_SIGHTINGS
                = "SELECT * FROM Sightings AS s "
                + "INNER JOIN Locations AS l "
                + "ON s.location_id = l.location_id";
        
        private static final String SELECT_LOCATION_BY_SIGHTING_ID
                = "SELECT l.* FROM Locations l "
                + "INNER JOIN Sightings AS s "
                + "ON l.location_id = s.location_id "
                + "WHERE s.sighting_id = ?";
        
        private static final String SELECT_SIGHTINGS_BY_LOCATION_ID
                = "SELECT * FROM Sightings WHERE location_id = ?";
        
        private static final String SELECT_LAST_ID = "SELECT MAX(sighting_id) as ID FROM Sightings";
        
        private static final String SELECT_LAST_TEN_SIGHTINGS
                = "SELECT s.sighting_id, s.sighting_date, location_id, archetype_name "
                + "FROM Sightings AS s "
                + "INNER JOIN Archetypes_Sightings AS ast  "
                + "ON s.sighting_id = ast.sighting_id "
                + "INNER JOIN Archetypes AS a "
                + "ON ast.archetype_id = a.archetype_id "
                + "ORDER BY sighting_date DESC LIMIT 0,10";
    }

    // ===================== SIGHTINGS CRUD METHODS ============================
    // =========================================================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SightingSqlQueries.INSERT_SIGHTING,
                sighting.getSightingDate().toString(),
                sighting.getLocation().getLocationId());
        
        int sightingId
                = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        
        sighting.setSightingId(sightingId);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SightingSqlQueries.DELETE_SIGHTING, sightingId);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteArchetypeSighting(int sightingId) {
        jdbcTemplate.update(SightingSqlQueries.DELETE_ARCHETYPE_SIGHTING, sightingId);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SightingSqlQueries.UPDATE_SIGHTING,
                sighting.getSightingDate().toString(),
                sighting.getLocation().getLocationId(),
                sighting.getSightingId());
    }
    
    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SightingSqlQueries.SELECT_SIGHTING,
                    new SightingMapper(), sightingId);
            sighting.setLocation(findLocationForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        //get the sightings by locationids
        List<Sighting> sightingList = jdbcTemplate.query(SightingSqlQueries.SELECT_SIGHTINGS_BY_LOCATION_ID,
                new SightingMapper(), locationId);
        
        return associateLocationWithSighting(sightingList);
    }
    
    @Override
    public List<Sighting> getAllSightings() {
        try {
            return jdbcTemplate.query(SightingSqlQueries.SELECT_ALL_SIGHTINGS,
                    new SightingMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public Integer getLastSightingId() {
        try {
            return jdbcTemplate.queryForObject(SightingSqlQueries.SELECT_LAST_ID,
                    new LatestIdMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // =========================== SIGHTING MAPPER =============================
    // =========================================================================
    public static final class SightingMapper implements RowMapper<Sighting> {
        
        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sighting_id"));
            sighting.setSightingDate(rs.getTimestamp("sighting_date").
                    toLocalDateTime().toLocalDate());
            return sighting;
        }
    }
    
    public static final class LatestIdMapper implements RowMapper<Integer> {
        
        @Override
        public Integer mapRow(ResultSet rs, int i) throws SQLException {
            Integer id = rs.getInt("ID");
            return id;
        }
        
    }
    // ========================== HELPER METHODS ===============================
    // =========================================================================

    private Location findLocationForSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SightingSqlQueries.SELECT_LOCATION_BY_SIGHTING_ID,
                new LocationMapper(), sighting.getSightingId());
    }
    
    private List<Sighting> associateLocationWithSighting(List<Sighting> sightingList) {
        for (Sighting currentSighting : sightingList) {
            currentSighting.setLocation(findLocationForSighting(currentSighting));
        }
        return sightingList;
    }
    
    public static final class ArchetypeSightingMapper implements RowMapper<ArchetypeSighting> {
        
        @Override
        public ArchetypeSighting mapRow(ResultSet rs, int i) throws SQLException {
            
            Location location = new Location();
            location.setLocationId(rs.getInt("location_id"));

            Sighting sighting = new Sighting();
            sighting.setLocation(location);
            sighting.setSightingId(rs.getInt("sighting_id"));
            sighting.setSightingDate(rs.getTimestamp("sighting_date").
                    toLocalDateTime().toLocalDate());

            return new ArchetypeSighting(0, rs.getString("archetype_name"), sighting);
        }
        
    }
}
