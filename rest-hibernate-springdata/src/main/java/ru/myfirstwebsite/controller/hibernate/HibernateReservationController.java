package ru.myfirstwebsite.controller.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.ReservationCreateRequest;
import ru.myfirstwebsite.domain.HibernateReservation;
import ru.myfirstwebsite.repository.hibernate.HibernateReservationDao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/reservations")
public class HibernateReservationController {

    @Autowired
    private HibernateReservationDao hibernateReservationDao;

    @GetMapping("/hibernate/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateReservation>> getReservationsHibernate() {
        return new ResponseEntity<>(hibernateReservationDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/hibernante/by_room_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateReservation>> getReservationByRoomIdHibernate(Integer id) {
        return new ResponseEntity<>(hibernateReservationDao.findByRoomId(id), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_date_from")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateReservation>> getReservationByDateFromHibernate(@RequestParam("dateFrom")
                                                                                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date date) {
        return new ResponseEntity<>(hibernateReservationDao.findByDateFrom(date), HttpStatus.OK);
    }

    @PostMapping("/hibernate/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateReservation> createReservationHibernate(@RequestBody ReservationCreateRequest request) {
        HibernateReservation reservation = new HibernateReservation();
        reservation.setUserId(request.getUserId());
        reservation.setRoomId(request.getRoomId());
        reservation.setDateFrom(new Timestamp(new Date().getTime()));
        reservation.setDateTo(new Timestamp(new Date().getTime()));

        return new ResponseEntity<>(hibernateReservationDao.save(reservation), HttpStatus.OK);
    }

    @RequestMapping(value = "/hibernate/update/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateReservation> updateReservationHibernate(@PathVariable("id") Integer id,
                                                                           @RequestBody ReservationCreateRequest request) {
        HibernateReservation reservation = hibernateReservationDao.findById(id);

        reservation.setUserId(request.getUserId());
        reservation.setRoomId(request.getRoomId());
        reservation.setDateFrom(new Timestamp(new Date().getTime()));
        reservation.setDateTo(new Timestamp(new Date().getTime()));
        reservation.setReservationId(id);

        return new ResponseEntity<>(hibernateReservationDao.update(reservation), HttpStatus.OK);
    }

    @DeleteMapping("/hibernate/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteReservationHibernate(@PathVariable("id") Integer id) {
        hibernateReservationDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
