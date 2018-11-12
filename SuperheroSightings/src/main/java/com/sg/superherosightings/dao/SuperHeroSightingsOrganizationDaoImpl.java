/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.dao.SuperHeroSightingsLocationDaoImpl.LocationMapper;
import com.sg.superherosightings.model.Location;
import com.sg.superherosightings.model.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ryan
 */
public class SuperHeroSightingsOrganizationDaoImpl extends SuperHeroSightingsBaseDao implements SuperHeroSightingsOrganizationDao {

    // ===================== PREPARED STATEMENTS ===============================
    // =========================================================================
    private static final class OrganizationSqlQueries {

        private static final String INSERT_ORGANIZATION
                = "INSERT INTO Organizations (organization_name, "
                + "organization_description, organization_phone, "
                + "organization_email, location_id) "
                + "VALUES (?, ?, ?, ?, ?)";

        private static final String DELETE_ORGANIZATION
                = "DELETE FROM Organizations WHERE organization_id = ?";

        private static final String UPDATE_ORGANIZATION
                = "UPDATE Organizations SET "
                + "organization_name = ?, organization_description = ?, "
                + "organization_phone = ?, organization_email = ?, "
                + "location_id = ? WHERE organization_id = ?";

        private static final String SELECT_ORGANIZATION
                = "SELECT * FROM Organizations AS o "
                + "INNER JOIN Locations AS l "
                + "ON o.location_id = l.location_id "
                + "WHERE organization_id = ?";

        private static final String SELECT_ALL_ORGANIZATIONS
                = "SELECT * FROM Organizations AS o "
                + "INNER JOIN Locations AS l "
                + "ON o.location_id = l.location_id";

        private static final String SELECT_LOCATION_BY_ORGANIZATION_ID
                = "SELECT l.* FROM Locations AS l "
                + "INNER JOIN Organizations AS o "
                + "ON l.location_id = o.location_id "
                + "WHERE o.organization_id = ?";

        private static final String SELECT_ORGANIZATIONS_BY_LOCATION_ID
                = "SELECT * FROM Organizations WHERE location_id = ?";
    }

    // ======================= ORGANIZATIONS CRUD METHODS ======================
    // =========================================================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(OrganizationSqlQueries.INSERT_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationPhone(),
                organization.getOrganizationEmail(),
                organization.getLocation().getLocationId());

        int organizationId
                = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        organization.setOrganizationId(organizationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int organizationId) {
        jdbcTemplate.update(OrganizationSqlQueries.DELETE_ORGANIZATION, organizationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(OrganizationSqlQueries.UPDATE_ORGANIZATION,
                organization.getOrganizationName(),
                organization.getOrganizationDescription(),
                organization.getOrganizationPhone(),
                organization.getOrganizationEmail(),
                organization.getLocation().getLocationId(),
                organization.getOrganizationId());
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        try {
            Organization organization = jdbcTemplate.queryForObject(OrganizationSqlQueries.SELECT_ORGANIZATION,
                    new OrganizationMapper(), organizationId);
            organization.setLocation(findLocationForOrganization(organization));
            return organization;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Organization> getOrganizationsByLocationId(int locationId) {
        //get the organizations by locationids
        List<Organization> organizationList = jdbcTemplate.query(OrganizationSqlQueries.SELECT_ORGANIZATIONS_BY_LOCATION_ID,
                new OrganizationMapper(), locationId);

        return associateLocationWithOrganization(organizationList);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        try {
            List<Organization> organizationList
                    = jdbcTemplate.query(OrganizationSqlQueries.SELECT_ALL_ORGANIZATIONS,
                            new OrganizationMapper());

            return associateLocationWithOrganization(organizationList);

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // =========================== ORGANIZATIONS MAPPER ========================
    // =========================================================================
    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationId(rs.getInt("organization_id"));
            organization.setOrganizationName(rs.getString("organization_name"));
            organization.setOrganizationDescription(rs.getString("organization_description"));
            organization.setOrganizationPhone(rs.getString("organization_phone"));
            organization.setOrganizationEmail(rs.getString("organization_email"));
//            Location location = new Location();
//            location.setLocationId(rs.getInt("location_id"));
//            location.setLocationName(rs.getString("location_name"));
//            location.setLocationDescription(rs.getString("location_description"));
//            location.setLocationStreet(rs.getString("location_street"));
//            location.setLocationCity(rs.getString("location_city"));
//            location.setLocationState(rs.getString("location_state"));
//            location.setLocationZip(rs.getString("location_zip"));
//            location.setLocationCountry(rs.getString("location_country"));
//            location.setLatitude(rs.getBigDecimal("location_latitude"));
//            location.setLongitude(rs.getBigDecimal("location_longitude"));
            return organization;
        }

    }

    // ========================== HELPER METHODS ===============================
    // =========================================================================
    private Location findLocationForOrganization(Organization organization) {
        return jdbcTemplate.queryForObject(OrganizationSqlQueries.SELECT_LOCATION_BY_ORGANIZATION_ID,
                new LocationMapper(), organization.getOrganizationId());
    }

    protected List<Organization> associateLocationWithOrganization(List<Organization> organizationList) {
        for (Organization currentOrganization : organizationList) {
            currentOrganization.setLocation(findLocationForOrganization(currentOrganization));
        }
        return organizationList;
    }

}
