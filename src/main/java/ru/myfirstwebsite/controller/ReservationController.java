package ru.myfirstwebsite.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.requests.ReservationCreateRequest;
import ru.myfirstwebsite.domain.to.Reservation;
import ru.myfirstwebsite.repository.ReservationDao;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/reservations")
public class ReservationController {

    @Autowired
    private ReservationDao reservationDao;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Reservation>> getReservations() {
        return new ResponseEntity<>(reservationDao.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Reservation> getReservationById(@PathVariable Integer id) {
        Reservation reservation = reservationDao.findById(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationCreateRequest request) {
        Reservation reservation = new Reservation();
        reservation.setRoomId(request.getRoomId());
        reservation.setDateFrom(request.getDateFrom());
        reservation.setDateTo(request.getDateTo());

        Reservation savedReservation = reservationDao.save(reservation);
        return new ResponseEntity<>(savedReservation, HttpStatus.OK);
    }

    @ApiOperation(value = "Update reservation by roomId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful reservation update"),
            @ApiResponse(code = 400, message = "Invalid reservation ID supplied"),
            @ApiResponse(code = 404, message = "Reservation was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Integer reservationId,
                                                         @RequestBody ReservationCreateRequest request) {
        Reservation reservation = reservationDao.findById(reservationId);
        reservation.setRoomId(request.getRoomId());
        reservation.setDateFrom(request.getDateFrom());
        reservation.setDateTo(request.getDateTo());

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteRoom(@PathVariable("id") Integer reservationId) {
        reservationDao.delete(reservationId);
        return new ResponseEntity<>(reservationId, HttpStatus.OK);
    }
}
