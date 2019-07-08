package ru.myfirstwebsite.repository.hibernate;


import ru.myfirstwebsite.domain.HibernateRoom;
import ru.myfirstwebsite.repository.GenericDao;

import java.util.List;

public interface HibernateRoomDao extends GenericDao<HibernateRoom, Integer> {
    List<HibernateRoom> findByRoomClass(String roomClass);
    List<HibernateRoom> findByPricePerDay(Integer price);


}
