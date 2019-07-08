package ru.myfirstwebsite.controller.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.ReservationCreateRequest;
import ru.myfirstwebsite.domain.HibernateReservation;
import ru.myfirstwebsite.repository.springdata.SpringdataReservationRepository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/reservations")
public class SpringdataReservationController {

    @Autowired
    private SpringdataReservationRepository springdataReservationRepository;

    @GetMapping("/springdata/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateReservation>> getReservationsSpringdata() {
        return new ResponseEntity<>(springdataReservationRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_room_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateReservation>> getReservationByRoomIdSpringdata(Integer id) {
        return new ResponseEntity<>(springdataReservationRepository.findByRoomId(id), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_date_from")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateReservation>> getReservationByDateFromSpringdata(Date dateFrom) {
        return new ResponseEntity<>(springdataReservationRepository.findByDateFrom(dateFrom), HttpStatus.OK);
    }

    @PostMapping("/springdata/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateReservation> createReservationSpringdata(@RequestBody ReservationCreateRequest request) {
        HibernateReservation reservation = new HibernateReservation();
        reservation.setUserId(request.getUserId());
        reservation.setRoomId(request.getRoomId());
        reservation.setDateFrom(new Timestamp(new Date().getTime()));
        reservation.setDateTo(new Timestamp(new Date().getTime()));

        return new ResponseEntity<>(springdataReservationRepository.save(reservation), HttpStatus.OK);
    }

    @RequestMapping(value = "/springdata/update/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateReservation> updateReservationSpringdata(@PathVariable("id") Integer id,
                                                                            @RequestBody ReservationCreateRequest request) {
        HibernateReservation reservation = springdataReservationRepository.findByReservationId(id);
        reservation.setUserId(request.getUserId());
        reservation.setRoomId(request.getRoomId());
        reservation.setDateFrom(new Timestamp(new Date().getTime()));
        reservation.setDateTo(new Timestamp(new Date().getTime()));
        reservation.setReservationId(id);

        return new ResponseEntity<>(springdataReservationRepository.save(reservation), HttpStatus.OK);
    }

    @DeleteMapping("/springdata/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteReservationSpringdata(@PathVariable("id") Integer id) {
        HibernateReservation reservation = springdataReservationRepository.findByReservationId(id);
        springdataReservationRepository.delete(reservation);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
