package ru.myfirstwebsite.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.myfirstwebsite.domain.Role;
import ru.myfirstwebsite.repository.RoleDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

    public static final String ROLE_ID = "idrole";
    public static final String ROLE_NAME = "rolename";
    public static final String ROLE_USER_ID = "user_iduser";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /*Read from Result Set by column name*/
    private Role getRoleRowMapper(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setRoleId(resultSet.getLong(ROLE_ID));
        role.setRoleName(resultSet.getString(ROLE_NAME));
        role.setUserId(resultSet.getLong(ROLE_USER_ID));
        return role;
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        final String getRolesByUserId = "select * from role where iduser = ?";
        return jdbcTemplate.query(getRolesByUserId, new Object[]{userId}, this::getRoleRowMapper);
    }

    @Override
    public List<Role> findAll() {
        final String findAllQuery = "select * from role";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getRoleRowMapper);
    }

    @Override
    public Role findById(Long id) {
        final String findById = "select * from role where idrole = :roleId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getRoleRowMapper);
    }

    @Override
    public void delete(Long id) {
        final String delete = "delete from role where idrole = :roleId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleId", id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public void deleteByUserId(Long id) {
        final String delete = "delete from role where user_iduser = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    public Role save (Role entity) {
        final String createQuery = "INSERT INTO role (rolename, user_iduser) " +
                "VALUES (:roleName, :userId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleName", entity.getRoleName());
        params.addValue("userId", entity.getUserId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

        long createdRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdRoleId);
    }

    @Override
    public Role update(Role entity) {
        final String createQuery = "update role set rolename = :roleName," +
                "iduser = :userId where idrole = :roleId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleName", entity.getRoleName());
        params.addValue("userId", entity.getUserId());

        params.addValue("roleId", entity.getRoleId());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getRoleId());
    }
}
