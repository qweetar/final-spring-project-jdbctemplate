package ru.myfirstwebsite.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.myfirstwebsite.domain.HibernateRoom;

import java.util.List;

public interface SpringdataRoomRepository extends CrudRepository<HibernateRoom, Integer>,
        JpaRepository<HibernateRoom, Integer>,
        PagingAndSortingRepository<HibernateRoom, Integer> {

    List<HibernateRoom> findByRoomClass(String roomClass);

    List<HibernateRoom> findByRoomPricePerDay(Integer price);

    HibernateRoom findByRoomId(Integer id);
}
