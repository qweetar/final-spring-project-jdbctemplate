package ru.myfirstwebsite.repository;


import ru.myfirstwebsite.domain.to.Room;

public interface RoomDao extends GenericDao<Room, Integer> {

    Room roomNode(Integer roomNum);

    boolean checkRoom(Integer roomNum);
}
