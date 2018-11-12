/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.Superpower;
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
public class SuperHeroSightingsSuperpowerDaoImpl extends SuperHeroSightingsBaseDao implements SuperHeroSightingsSuperpowerDao {

    // ===================== PREPARED STATEMENTS ===============================
    // =========================================================================
    private static final class SuperpowerSqlQueries {

        private static final String INSERT_SUPERPOWER
                = "INSERT INTO Superpowers (superpower_type) "
                + "VALUES (?)";

        private static final String DELETE_SUPERPOWER
                = "DELETE FROM Superpowers WHERE superpower_id = ?";

        private static final String UPDATE_SUPERPOWER
                = "UPDATE Superpowers SET superpower_type = ? "
                + "WHERE superpower_id = ?";

        private static final String SELECT_SUPERPOWER
                = "SELECT * FROM Superpowers WHERE superpower_id = ?";

        private static final String SELECT_ALL_SUPERPOWERS
                = "SELECT * FROM Superpowers";
    }

    // ===================== SUPERPOWER CRUD METHODS ===========================
    // =========================================================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperpower(Superpower superpower) {
        jdbcTemplate.update(SuperpowerSqlQueries.INSERT_SUPERPOWER,
                superpower.getSuperpowerType());

        int superpowerId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        superpower.setSuperpowerId(superpowerId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperpower(int superpowerId) {
        jdbcTemplate.update(SuperpowerSqlQueries.DELETE_SUPERPOWER, superpowerId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuperpower(Superpower superpower) {

        jdbcTemplate.update(SuperpowerSqlQueries.UPDATE_SUPERPOWER,
                superpower.getSuperpowerType(),
                superpower.getSuperpowerId());
    }

    @Override
    public Superpower getSuperpowerById(int superpowerId) {
        try {
            return jdbcTemplate.queryForObject(SuperpowerSqlQueries.SELECT_SUPERPOWER,
                    new SuperpowerMapper(), superpowerId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Superpower> getAllSuperpowers() {
        try {
            return jdbcTemplate.query(SuperpowerSqlQueries.SELECT_ALL_SUPERPOWERS,
                    new SuperpowerMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // ========================= SUPERPOWER MAPPER =============================
    // =========================================================================
    public static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setSuperpowerId(rs.getInt("superpower_id"));
            superpower.setSuperpowerType(rs.getString("superpower_type"));
            return superpower;
        }

    }

    // ========================== HELPER METHODS ===============================
    // =========================================================================
}
