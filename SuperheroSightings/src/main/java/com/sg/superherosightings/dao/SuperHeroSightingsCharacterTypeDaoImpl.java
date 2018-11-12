/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.dao;

import com.sg.superherosightings.model.CharacterType;
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
public class SuperHeroSightingsCharacterTypeDaoImpl extends SuperHeroSightingsBaseDao implements SuperHeroSightingsCharacterTypeDao {

    // ===================== CHARACTER TYPES STATEMENTS ========================
    // =========================================================================
    private static final class CharacterTypeSqlQueries {

        private static final String INSERT_CHARACTER_TYPE
                = "INSERT INTO Character_Types (character_type_name) "
                + "VALUES (?)";

        private static final String DELETE_CHARACTER_TYPE
                = "DELETE FROM Character_Types WHERE character_type_id  = ?";

        private static final String UPDATE_CHARACTER_TYPE
                = "UPDATE Character_Types SET character_type_name  = ? "
                + "WHERE character_type_id = ?";

        private static final String SELECT_CHARACTER_TYPE
                = "SELECT * FROM Character_Types WHERE character_type_id = ?";

        private static final String SELECT_ALL_CHARACTER_TYPES
                = "SELECT * FROM Character_Types";
    }

    // ===================== CHARACTER TYPES CRUD METHODS ======================
    // =========================================================================
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addCharacterType(CharacterType characterType) {
        jdbcTemplate.update(CharacterTypeSqlQueries.INSERT_CHARACTER_TYPE,
                characterType.getCharacterTypeName());

        int characterTypeId
                = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        characterType.setCharacterTypeId(characterTypeId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteCharacterType(int characterTypeId) {
        jdbcTemplate.update(CharacterTypeSqlQueries.DELETE_CHARACTER_TYPE, characterTypeId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateCharacterType(CharacterType characterType) {
        jdbcTemplate.update(CharacterTypeSqlQueries.UPDATE_CHARACTER_TYPE,
                characterType.getCharacterTypeName(),
                characterType.getCharacterTypeId());
    }

    @Override
    public CharacterType getCharacterTypeById(int characterTypeId) {
        try {
            return jdbcTemplate.queryForObject(CharacterTypeSqlQueries.SELECT_CHARACTER_TYPE,
                    new CharacterTypeMapper(), characterTypeId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<CharacterType> getAllCharacterTypes() {
        try {
            return jdbcTemplate.query(CharacterTypeSqlQueries.SELECT_ALL_CHARACTER_TYPES,
                    new CharacterTypeMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    // ====================== CHARACTER TYPES MAPPER ===========================
    // =========================================================================

    public static final class CharacterTypeMapper implements RowMapper<CharacterType> {

        @Override
        public CharacterType mapRow(ResultSet rs, int i) throws SQLException {
            CharacterType characterType = new CharacterType();
            characterType.setCharacterTypeId(rs.getInt("character_type_id"));
            characterType.setCharacterTypeName(rs.getString("character_type_name"));
            return characterType;
        }

    }
    // ========================== HELPER METHODS ===============================
    // =========================================================================
}
