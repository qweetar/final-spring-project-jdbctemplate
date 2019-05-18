package ru.myfirstwebsite.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.myfirstwebsite.controller.requests.RoomCreateRequest;
import ru.myfirstwebsite.domain.to.Room;
import ru.myfirstwebsite.repository.RoomDao;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/rooms")
public class RoomController {

    @Autowired
    private RoomDao roomDao;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Room>> getRooms() {
        return new ResponseEntity<>(roomDao.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Room> getRoomById(@PathVariable Integer id) {
        Room room = roomDao.findById(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Room> createRoom(@RequestBody RoomCreateRequest request) {
        Room room = new Room();
        room.setRoomClass(request.getRoomClass());
        room.setRoomPricePerDay(request.getRoomPricePerDay());
        room.setRoomNum(request.getRoomNum());
        room.setRoomSize(request.getRoomSize());
        room.setRoomStatus(request.getRoomStatus());

        Room savedRoom = roomDao.save(room);
        return new ResponseEntity<>(savedRoom, HttpStatus.OK);
    }

    @ApiOperation(value = "Update room by roomId")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful room update"),
            @ApiResponse(code = 400, message = "Invalid Room ID supplied"),
            @ApiResponse(code = 404, message = "Room was not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-Auth-Token", value = "token", required = true, dataType = "string", paramType = "header")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Room> updateRoom(@PathVariable("id") Integer roomId,
                                           @RequestBody RoomCreateRequest request) {
        Room room = roomDao.findById(roomId);
        room.setRoomClass(request.getRoomClass());
        room.setRoomPricePerDay(request.getRoomPricePerDay());
        room.setRoomNum(request.getRoomNum());
        room.setRoomSize(request.getRoomSize());
        room.setRoomStatus(request.getRoomStatus());

        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> deleteRoom(@PathVariable("id")Integer roomId) {
        roomDao.delete(roomId);
        return new ResponseEntity<>(roomId, HttpStatus.OK);
    }





}
