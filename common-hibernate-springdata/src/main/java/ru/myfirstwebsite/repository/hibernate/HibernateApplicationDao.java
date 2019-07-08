package ru.myfirstwebsite.repository.hibernate;




import ru.myfirstwebsite.domain.HibernateApplication;
import ru.myfirstwebsite.repository.GenericDao;

import java.util.Date;
import java.util.List;


public interface HibernateApplicationDao extends GenericDao<HibernateApplication, Integer> {

    List<HibernateApplication> findByUserId(Integer id);

    List<HibernateApplication> findByDateFrom(Date date);
}

