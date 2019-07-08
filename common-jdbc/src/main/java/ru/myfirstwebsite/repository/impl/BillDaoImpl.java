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
import ru.myfirstwebsite.domain.Bill;
import ru.myfirstwebsite.repository.BillDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class BillDaoImpl implements BillDao {

    public static final String BILL_ID = "idbill";
    public static final String PRICE = "price";
    public static final String APPLICATION_ID = "idapplication";
    public static final String USER_ID = "iduser";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Bill getBillRowMapper(ResultSet resultSet, int i) throws SQLException {
        Bill bill = new Bill();
        bill.setBillId(resultSet.getInt(BILL_ID));
        bill.setPrice(resultSet.getInt(PRICE));
        bill.setApplicationId(resultSet.getInt(APPLICATION_ID));
        bill.setUserId(resultSet.getInt(USER_ID));
        return bill;
    }

    @Override
    public List<Bill> getBill(Integer roomId) {
        return null;
    }

    @Override
    public List<Bill> findAll() {
        final String findAllQuery = "select * from bill";
        return namedParameterJdbcTemplate.query(findAllQuery, this::getBillRowMapper);
    }

    @Override
    public Bill findById(Integer id) {
        final String findById = "select * from bill where idbill = :billId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("billId", id);

        return namedParameterJdbcTemplate.queryForObject(findById, params, this::getBillRowMapper);
    }

    @Override
    public void delete(Integer id) {
        final String delete = "delete from bill where idbill = :billId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("billId", id);

        namedParameterJdbcTemplate.update(delete, params);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
    public Bill save(Bill entity) {
        final String createQuery = "insert into bill (price, idapplication, idapplication) " +
                "values (:price, :applicationId, :userId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("price", entity.getPrice());
        params.addValue("applicationId", entity.getApplicationId());
        params.addValue("userId", entity.getUserId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder);

        int createdBillId = Objects.requireNonNull(keyHolder.getKey().intValue());

        return findById(createdBillId);
    }

    @Override
    public Bill update(Bill entity) {
        final String createQuery = "update bill set price = :price, idapplication = :applicationId, " +
                "idapplication = :userId where idbill = :billId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("price", entity.getPrice());
        params.addValue("idapplication", entity.getApplicationId());
        params.addValue("iduser", entity.getUserId());

        params.addValue("idbill", entity.getBillId());

        namedParameterJdbcTemplate.update(createQuery, params);
        return findById(entity.getBillId());
    }
}
