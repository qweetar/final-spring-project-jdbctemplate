package ru.myfirstwebsite.controller.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.RoomCreateRequest;
import ru.myfirstwebsite.domain.HibernateRoom;
import ru.myfirstwebsite.repository.hibernate.HibernateRoomDao;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/rooms")
public class HibernateRoomController {

    @Autowired
    private HibernateRoomDao hibernateRoomDao;

    @GetMapping("/hibernate/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateRoom>> getRoomsHibernate() {
        return new ResponseEntity<>(hibernateRoomDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_room_class")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateRoom>> getRoomByRoomClassHibernate(String roomClass) {
        return new ResponseEntity<>(hibernateRoomDao.findByRoomClass(roomClass), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_price")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateRoom>> getRoomByPriceHibernate(Integer price) {
        return new ResponseEntity<>(hibernateRoomDao.findByPricePerDay(price), HttpStatus.OK);
    }

    @GetMapping("/hibernate/by_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateRoom> getRoomByIdHibernate(Integer id) {
        return new ResponseEntity<>(hibernateRoomDao.findById(id), HttpStatus.OK);
    }

    @PostMapping("/hibernate/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateRoom> createRoomHibernate(@RequestBody RoomCreateRequest request) {
        HibernateRoom room = new HibernateRoom();
        room.setRoomStatus(request.getRoomStatus());
        room.setRoomSize(request.getRoomSize());
        room.setRoomPricePerDay(request.getRoomPricePerDay());
        room.setRoomNum(request.getRoomNum());
        room.setRoomClass(request.getRoomClass());

        return new ResponseEntity<>(hibernateRoomDao.save(room), HttpStatus.OK);
    }

    @RequestMapping(value = "/hibernate/update/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateRoom> updateRoomHibernate(@PathVariable("id") Integer id,
                                                             @RequestBody RoomCreateRequest request) {
        HibernateRoom room = hibernateRoomDao.findById(id);
        room.setRoomStatus(request.getRoomStatus());
        room.setRoomSize(request.getRoomSize());
        room.setRoomPricePerDay(request.getRoomPricePerDay());
        room.setRoomNum(request.getRoomNum());
        room.setRoomClass(request.getRoomClass());
        room.setRoomId(id);

        return new ResponseEntity<>(hibernateRoomDao.update(room), HttpStatus.OK);
    }

    @DeleteMapping("/hibernate/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteRoomHibernate(@PathVariable("id") Integer id) {
        hibernateRoomDao.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
