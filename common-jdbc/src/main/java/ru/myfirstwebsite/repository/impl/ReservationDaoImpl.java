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
import ru.myfirstwebsite.domain.Reservation;
import ru.myfirstwebsite.repository.ReservationDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class ReservationDaoImpl implements ReservationDao {

    public static final String DATE_FROM = "datefrom";
    public static final String DATE_TO = "dateto";
    public static final String ROOM_ID = "room_idroom";
    public static final String RESERVATION_ID = "idreservation";
    public static final String USER_ID = "user_iduser";
    public static final String BILL_ID = "bill_idbill";
    public static final String APPLICATION_ID = "application_idapplication";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Reservation getReservationRowMapper(ResultSet resultSet, int i) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setDateFrom(resultSet.getDate(DATE_FROM));
        reservation.setDateTo(resultSet.getDate(DATE_TO));
        reservation.setRoomId(resultSet.getInt(ROOM_ID));
        reservation.setReservationId(resultSet.getInt(RESERVATION_ID));
        reservation.setUserId(resultSet.getInt(USER_ID));
        reservation.setBillId(resultSet.getInt(BILL_ID));
        reservation.setApplicationId(resultSet.getInt(APPLICATION_ID));
        return reservation;
    }

    @Override
    public boolean checkReservation(Integer roomId) {
        return false;
    }

    @Override
    public List<Reservation> findAll() {
        final String findAllQuery = "select * from reservation";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getReservationRowMapper);
    }

    @Override
    public Reservation findById(Integer id) {
        final String findById = "select * from revervation where idreservation = :reservationId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("reservationId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getReservationRowMapper);
    }

    @Override
    public void delete(Integer id) {
        final String delete = "delete from reservation where idreservation = :reservationId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("reservationId", id);

        namedParameterJdbcTemplate.update(delete, params);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Reservation save(Reservation entity) {
        final String createQuery = "insert into reservation (datefrom, dateto, room_idroom," +
                " user_iduser, bill_idbill, application_idapplication) " +
                "values (:dateFrom, :dateTo, :roomId, :userId, :billId, :applicationId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dateFrom", entity.getDateFrom());
        params.addValue("dateTo", entity.getDateTo());
        params.addValue("roomId", entity.getRoomId());
        params.addValue("userId", entity.getUserId());
        params.addValue("billId", entity.getBillId());
        params.addValue("applicationId", entity.getApplicationId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

        int createdReservationId = Objects.requireNonNull(keyHolder.getKey().intValue());

        return findById(createdReservationId);
    }

    @Override
    public Reservation update(Reservation entity) {
        final String createQuery = "update reservation set datefrom = :dateFrom, dateto = :dateTo, " +
                "room_idroom = :roomId, user_iduser = :userId, bill_idbill = :billId, " +
                "application_idapplication = :applicationId where idreservation = :reservationId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("dateFrom", entity.getDateFrom());
        params.addValue("dateTo", entity.getDateTo());
        params.addValue("roomId", entity.getRoomId());
        params.addValue("userId", entity.getUserId());
        params.addValue("billId", entity.getBillId());
        params.addValue("applicationId", entity.getApplicationId());

        params.addValue("reservationId", entity.getReservationId());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getReservationId());
    }
}
