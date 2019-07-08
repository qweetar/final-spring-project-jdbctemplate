package ru.myfirstwebsite.repository.hibernate;


import ru.myfirstwebsite.domain.HibernateReservation;
import ru.myfirstwebsite.repository.GenericDao;

import java.util.Date;
import java.util.List;

public interface HibernateReservationDao extends GenericDao<HibernateReservation, Integer> {

    List<HibernateReservation> findByRoomId(Integer roomId);

    List<HibernateReservation> findByDateFrom(Date dateFrom);

}
