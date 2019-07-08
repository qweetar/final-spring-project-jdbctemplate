package ru.myfirstwebsite.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.myfirstwebsite.domain.enums.Sex;
import ru.myfirstwebsite.domain.User;
import ru.myfirstwebsite.repository.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    public static final String USER_ID = "iduser";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_SEX = "sex";
    public static final String USER_PHONE = "phone";
    public static final String USER_EMAIL = "email";
    public static final String USER_STATUS = "blocked";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /*Read from Result Set by column name*/
    private User getUserRowMapper(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getLong(USER_ID));
        user.setUserName(resultSet.getString(USER_NAME));
        user.setSurname(resultSet.getString(USER_SURNAME));
        user.setSex(Sex.valueOf(resultSet.getString(USER_SEX)));
        user.setMobilePhone(resultSet.getString(USER_PHONE));
        user.setEmail(resultSet.getString(USER_EMAIL));
        user.setBlocked(Boolean.valueOf(resultSet.getString(USER_STATUS)));
        user.setLogin(resultSet.getString(LOGIN));
        user.setPassword(resultSet.getString(PASSWORD));
        return user;
    }

    @Override
    public List<User> findAll() {
        final String findAllQuery = "select * from user";
//        return jdbcTemplate.query(findAllQuery, this::getUserRowMapper);
        return namedParameterJdbcTemplate.query(findAllQuery, this::getUserRowMapper);
    }

    @Override
    public User findById(Long id) {
//        final String findById = "select * from user where user_id = ?";
//        return jdbcTemplate.queryForObject(findById, new Object[]{id}, this::getUserRowMapper);
        final String findById = "select * from user where iduser = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getUserRowMapper);
    }

    @Override
    public void delete(Long id) {
        final String delete = "delete from user where iduser = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id);



        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public User save(User entity) {
        final String createQuery = "INSERT INTO user (login, password, name, surname, sex, phone, email, " +
                "blocked) VALUES (:login, :pass, :userName, :surname, :sex, :mobilePhone, :email, :blocked);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", entity.getLogin());
        params.addValue("pass", entity.getPassword());
        params.addValue("userName", entity.getUserName());
        params.addValue("surname", entity.getSurname());
        params.addValue("sex", String.valueOf(entity.getSex()));
        params.addValue("mobilePhone", entity.getMobilePhone());
        params.addValue("email", entity.getEmail());
        params.addValue("blocked", entity.getBlocked());

//        jdbcTemplate.update(createQuery, params);

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdUserId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User update(User entity) {
        final String createQuery = "UPDATE user set login = :login, password = :pass, name = :userName, " +
                "surname = :surname, sex = :sex, phone = :mobilePhone, email = :email, blocked = :blocked " +
                "where iduser = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", entity.getLogin());
        params.addValue("pass", entity.getPassword());
        params.addValue("userName", entity.getUserName());
        params.addValue("surname", entity.getSurname());
        params.addValue("sex", String.valueOf(entity.getSex()));
        params.addValue("mobilePhone", entity.getMobilePhone());
        params.addValue("email", entity.getEmail());
        params.addValue("blocked", entity.getBlocked());

        params.addValue("userId", entity.getUserId());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getUserId());
    }

    @Override
    public User findByLogin(String login) {
        final String findById = "select * from user where login = :login";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", login);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getUserRowMapper);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Long> batchUpdate(List<User> users) {
        final String createQuery = "UPDATE user set login = :login, password = :password, name = :userName, " +
                "surname = :surname, sex = :sex, phone = :mobilePhone, email = :email, blocked = :blocked " +
                "where iduser = :userId";

        List<SqlParameterSource> batch = new ArrayList<>();
        for (User user : users) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("login", user.getLogin());
            params.addValue("password", user.getPassword());
            params.addValue("userName", user.getUserName());
            params.addValue("surname", user.getSurname());
            params.addValue("sex", user.getSex());
            params.addValue("mobilePhone", user.getMobilePhone());
            params.addValue("email", user.getEmail());
            params.addValue("blocked", user.getBlocked());

            params.addValue("userId", user.getUserId());
            batch.add(params);
        }

        namedParameterJdbcTemplate.batchUpdate(createQuery, batch.toArray(new SqlParameterSource[batch.size()]));
        return users.stream().map(User::getUserId).collect(Collectors.toList());
    }

    @Override
    public List<User> search(String query, Integer limit, Integer offset) {
        final String searchQuery = "select * from user where lower(name) LIKE lower(:query) or " +
                "lower(surname) LIKE lower(:query) limit :lim offset :off";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("query", "%" + query + "%");
        params.addValue("lim", limit);
        params.addValue("off", offset);

        return namedParameterJdbcTemplate.query(searchQuery, params, this::getUserRowMapper);
    }
}
