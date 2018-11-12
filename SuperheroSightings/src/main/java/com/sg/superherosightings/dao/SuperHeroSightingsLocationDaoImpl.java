/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Location;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ryan Meza
 */
public class SuperHeroSightingsLocationDaoImpl extends SuperHeroSightingsBaseDao implements SuperHeroSightingsLocationDao {

    // ===================== PREPARED STATEMENTS ===============================
    // =========================================================================
    private static final class LocationSqlQueries {

        private static final String INSERT_LOCATION
                = "INSERT INTO Locations (location_name, location_description, "
                + "location_street, location_city, location_state, location_zip, "
                + "location_country, location_latitude, location_longitude) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        private static final String DELETE_LOCATION
                = "DELETE FROM Locations WHERE location_id  = ?";

        private static final String UPDATE_LOCATION
                = "UPDATE Locations SET location_name  = ?, location_description = ?, "
                + "location_street = ?, location_city = ?, location_state = ?, "
                + "location_zip = ?, location_country = ?, location_latitude = ?,"
                + "location_longitude = ? "
                + "WHERE location_id = ?";

        private static final String SELECT_LOCATION
                = "SELECT * FROM Locations WHERE location_id = ?";

        private static final String SELECT_ALL_LOCATIONS
                = "SELECT * FROM Locations";
    }

    // ====================== LOCATIONS CRUD METHODS ===========================
    // =========================================================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(LocationSqlQueries.INSERT_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationStreet(),
                location.getLocationCity(),
                location.getLocationState(),
                location.getLocationZip(),
                location.getLocationCountry(),
                location.getLatitude(),
                location.getLongitude());

        int locationId
                = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        location.setLocationId(locationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(LocationSqlQueries.DELETE_LOCATION, locationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateLocation(Location location) {

        jdbcTemplate.update(LocationSqlQueries.UPDATE_LOCATION,
                location.getLocationName(),
                location.getLocationDescription(),
                location.getLocationStreet(),
                location.getLocationCity(),
                location.getLocationState(),
                location.getLocationZip(),
                location.getLocationCountry(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
    }

    @Override
    public Location getLocationById(int locationId) {
        try {
            return jdbcTemplate.queryForObject(LocationSqlQueries.SELECT_LOCATION,
                    new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        try {
            return jdbcTemplate.query(LocationSqlQueries.SELECT_ALL_LOCATIONS,
                    new LocationMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // ========================== LOCATIONS MAPPER =============================
    // =========================================================================
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("location_id"));
            location.setLocationName(rs.getString("location_name"));
            location.setLocationDescription(rs.getString("location_description"));
            location.setLocationStreet(rs.getString("location_street"));
            location.setLocationCity(rs.getString("location_city"));
            location.setLocationState(rs.getString("location_state"));
            location.setLocationZip(rs.getString("location_zip"));
            location.setLocationCountry(rs.getString("location_country"));
     try {
            location.setLatitude(rs.getBigDecimal("location_latitude").setScale(8, RoundingMode.HALF_UP));
            location.setLongitude(rs.getBigDecimal("location_longitude").setScale(8, RoundingMode.HALF_UP));
     } catch (NullPointerException e) {
         location.setLatitude(null);
         location.setLongitude(null);
     }
            
            return location;
        }

    }

    // ========================== HELPER METHODS ===============================
    // =========================================================================
}
