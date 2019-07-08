package ru.myfirstwebsite.repository;


import ru.myfirstwebsite.domain.Reservation;

public interface ReservationDao extends GenericDao<Reservation, Integer> {
    boolean checkReservation(Integer roomId);
}
