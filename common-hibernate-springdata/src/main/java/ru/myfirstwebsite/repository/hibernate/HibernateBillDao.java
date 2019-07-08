package ru.myfirstwebsite.repository.hibernate;

import ru.myfirstwebsite.domain.HibernateBill;
import ru.myfirstwebsite.repository.GenericDao;

import java.util.List;

public interface HibernateBillDao extends GenericDao<HibernateBill, Integer> {

    List<HibernateBill> findByUserId(Integer id);
}
