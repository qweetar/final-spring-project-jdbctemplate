package ru.myfirstwebsite.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.myfirstwebsite.domain.HibernateReservation;

import java.util.Date;
import java.util.List;

public interface SpringdataReservationRepository extends
        CrudRepository<HibernateReservation, Integer>,
        JpaRepository<HibernateReservation, Integer>,
        PagingAndSortingRepository<HibernateReservation, Integer> {

    List<HibernateReservation> findByRoomId(Integer id);

    List<HibernateReservation> findByDateFrom(Date dateFrome);

    HibernateReservation findByReservationId(Integer id);
}
