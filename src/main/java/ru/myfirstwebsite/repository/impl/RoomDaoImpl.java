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
import ru.myfirstwebsite.domain.to.Room;
import ru.myfirstwebsite.repository.RoomDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class RoomDaoImpl implements RoomDao {

    public static final String ROOM_ID = "idroom";
    public static final String ROOM_NUM = "room#";
    public static final String ROOM_SIZE = "roomsize";
    public static final String ROOM_CLASS = "class";
    public static final String ROOM_PRICE_DAY = "priceday";
    public static final String ROOM_STATUS = "roomstatus";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /*Read from Result Set by column name*/
    private Room getRoomRowMapper(ResultSet resultSet, int i) throws SQLException {
        Room room = new Room();
        room.setRoomId(resultSet.getInt(ROOM_ID));
        room.setRoomNum(resultSet.getInt(ROOM_NUM));
        room.setRoomSize(resultSet.getInt(ROOM_SIZE));
        room.setRoomClass(resultSet.getString(ROOM_CLASS));
        room.setRoomPricePerDay(resultSet.getInt(ROOM_PRICE_DAY));
        room.setRoomStatus(resultSet.getString(ROOM_STATUS));
        return room;
    }

    @Override
    public Room roomNode(Integer roomNum) {
        return null;
    }

    @Override
    public boolean checkRoom(Integer roomNum) {
        return false;
    }

    @Override
    public List<Room> findAll() {
        final String findAllQuery = "select * from room";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getRoomRowMapper);
    }

    @Override
    public Room findById(Integer id) {
        final String findById = "select * from room where idroom = :roomId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roomId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getRoomRowMapper);
    }

    @Override
    public void delete(Integer id) {
        final String delete = "delete from room where idroom = :roomId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roomId", id);

        namedParameterJdbcTemplate.update(delete, params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Room save(Room entity) {
        final String createQuery = "insert into room (room#, roomsize, class, priceday, roomstatus) " +
                "values (:roomNum, roomSize, :roomClass, :roomPricePerDay, roomStatus);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roomNum", entity.getRoomNum());
        params.addValue("roomSize", entity.getRoomSize());
        params.addValue("roomClass", entity.getRoomClass());
        params.addValue("roomPricePerDay", entity.getRoomPricePerDay());
        params.addValue("roomStatus", entity.getRoomStatus());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

        int createdRoomId = Objects.requireNonNull(keyHolder.getKey().intValue());

        return findById(createdRoomId);
    }

    @Override
    public Room update(Room entity) {
        final String createQuery = "update room set room# = :roomNum, roomsize = :roomSize, " +
                "class = :roomClass, priceday = :roomPricePerDay, roomStatus = :roomStatus " +
                "where idroom = :roomId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roomNum", entity.getRoomNum());
        params.addValue("roomSize", entity.getRoomSize());
        params.addValue("roomClass", entity.getRoomClass());
        params.addValue("roomPricePerDay", entity.getRoomPricePerDay());
        params.addValue("roomStatus", entity.getRoomStatus());

        params.addValue("roomId", entity.getRoomId());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getRoomId());
    }
}
