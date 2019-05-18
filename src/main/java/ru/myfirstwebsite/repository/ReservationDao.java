package ru.myfirstwebsite.repository;


import ru.myfirstwebsite.domain.to.Reservation;

public interface ReservationDao extends GenericDao<Reservation, Integer> {
    boolean checkReservation(Integer roomId);
}
