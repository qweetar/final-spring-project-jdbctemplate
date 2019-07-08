package ru.myfirstwebsite.controller.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.request.RoomCreateRequest;
import ru.myfirstwebsite.domain.HibernateRoom;
import ru.myfirstwebsite.repository.springdata.SpringdataRoomRepository;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/rooms")
public class SpringdataRoomController {

    @Autowired
    private SpringdataRoomRepository springdataRoomRepository;

    @GetMapping("/springdata/all")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Page<HibernateRoom>> getRoomsSpringdata(@ApiIgnore Pageable pageable) {
        return new ResponseEntity<>(springdataRoomRepository.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_room_class")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateRoom>> getRoomByRoomClassSpringdata(String roomClass) {
        return new ResponseEntity<>(springdataRoomRepository.findByRoomClass(roomClass), HttpStatus.OK);
    }

    @GetMapping("/springdata/by_price")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<HibernateRoom>> getRoomByPriceSpringdata(Integer price) {
        return new ResponseEntity<>(springdataRoomRepository.findByRoomPricePerDay(price),HttpStatus.OK);
    }

    @GetMapping("/springdata/by_id")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateRoom> getRoomByIdSpringdata(Integer id) {
        return new ResponseEntity<>(springdataRoomRepository.findByRoomId(id), HttpStatus.OK);
    }

    @PostMapping("/springdata/create")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HibernateRoom> createRoomSpringdata(@RequestBody RoomCreateRequest request) {
        HibernateRoom room = new HibernateRoom();
        room.setRoomStatus(request.getRoomStatus());
        room.setRoomSize(request.getRoomSize());
        room.setRoomPricePerDay(request.getRoomPricePerDay());
        room.setRoomNum(request.getRoomNum());
        room.setRoomClass(request.getRoomClass());

        return new ResponseEntity<>(springdataRoomRepository.save(room), HttpStatus.OK);
    }

    @RequestMapping(value = "/springdata/update/{id}", method = RequestMethod.PUT)
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<HibernateRoom> updateRoomSpringdata(@PathVariable("id") Integer id,
                                                              @RequestBody RoomCreateRequest request) {
        HibernateRoom room = springdataRoomRepository.findByRoomId(id);
        room.setRoomStatus(request.getRoomStatus());
        room.setRoomSize(request.getRoomSize());
        room.setRoomPricePerDay(request.getRoomPricePerDay());
        room.setRoomNum(request.getRoomNum());
        room.setRoomClass(request.getRoomClass());
        room.setRoomId(id);

        return new ResponseEntity<>(springdataRoomRepository.save(room), HttpStatus.OK);
    }

    @DeleteMapping("/springdata/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteRoomSpringdata(@PathVariable("id") Integer id) {
        HibernateRoom room = springdataRoomRepository.findByRoomId(id);
        springdataRoomRepository.delete(room);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }





}
