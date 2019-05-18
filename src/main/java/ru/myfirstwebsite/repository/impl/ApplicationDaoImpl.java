package ru.myfirstwebsite.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.repository.ApplicationDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final String APPLICATION_ID = "idapplication";
    public static final String ROOM_SIZE = "roomsize";
    public static final String ROOM_CLASS = "class";
    public static final String DATE_FROM = "datefrom";
    public static final String DATE_TO = "dateto";
    public static final String USER_ID = "iduser";
    public static final String ROOM_ID = "idroom";

    private Application getApplicationRowMapper(ResultSet resultSet, int i) throws SQLException {
        Application application = new Application();
        application.setApplicationId(resultSet.getInt(APPLICATION_ID));
        application.setRoomSize(resultSet.getInt(ROOM_SIZE));
        application.setRoomClass(resultSet.getString(ROOM_CLASS));
        application.setDateFrom(resultSet.getDate(DATE_FROM));
        application.setDateTo(resultSet.getDate(DATE_TO));
        application.setUserId(resultSet.getInt(USER_ID));
        application.setRoomId(resultSet.getInt(ROOM_ID));
        return application;
    }


    @Override
    public List<Application> getUserApplication(Integer userId) {
        final String getUserApplication = "select * from application where iduser = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "userId", userId);
        return namedParameterJdbcTemplate.query(getUserApplication, params, this::getApplicationRowMapper);
    }

    @Override
    public List<Application> findAll() {
        final String findAllQuery = "select * from application";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getApplicationRowMapper);
    }

    @Override
    public Application findById(Integer id) {
        final String findById = "select * from application where idapplication = :applicationId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("applicationId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getApplicationRowMapper);
    }

    @Override
    public void delete(Integer id) {
        final String delete = "delete from application where idapplication = :applicationId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("applicationId", id);

        namedParameterJdbcTemplate.update(delete, params);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Application save(Application entity) {
       final String creatQuery = "insert into application (roomSize, class, datefrom, dateto, iduser, idroom) " +
               "VALUES (:roomSize, :roomClass, : dateFrom, :dateTo, userId, roomId);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roomSize", entity.getRoomSize());
        params.addValue("roomClass", entity.getRoomClass());
        params.addValue("dateFrom", entity.getDateFrom());
        params.addValue("dateTo", entity.getDateTo());
        params.addValue("userId", entity.getUserId());
        params.addValue("roomId", entity.getRoomId());

        namedParameterJdbcTemplate.update(creatQuery, params, keyHolder);

        int createApplicationId = Objects.requireNonNull(keyHolder.getKey().intValue());

        return findById(createApplicationId);
    }

    @Override
    public Application update(Application entity) {
        final String createQuery = "update application set roomsize = :roomSize, " +
                "class = :roomClass, datefrom = :dateFrom, dateto = :dateTo, " +
                "iduser = :userId, idroom = :roomId where idapplication = :applicationId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roomSize", entity.getRoomSize());
        params.addValue("roomClass", entity.getRoomClass());
        params.addValue("dateFrom", entity.getDateFrom());
        params.addValue("dateTo", entity.getDateTo());
        params.addValue("userId", entity.getUserId());
        params.addValue("roomId", entity.getRoomId());

        params.addValue("applicationId", entity.getApplicationId());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getApplicationId());
    }
}
