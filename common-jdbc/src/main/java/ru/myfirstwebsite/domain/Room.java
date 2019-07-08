package ru.myfirstwebsite.domain;

import java.util.Objects;

public class Room {
    private Integer roomId;
    private String roomClass;
    private Integer roomPricePerDay;
    private Integer roomNum;
    private Integer roomSize;
    private String roomStatus;

    public Room() {
    }

    public Room(Integer roomId, String roomClass, Integer roomPricePerDay, Integer roomNum, Integer roomSize, String roomStatus) {
        this.roomId = roomId;
        this.roomClass = roomClass;
        this.roomPricePerDay = roomPricePerDay;
        this.roomNum = roomNum;
        this.roomSize = roomSize;
        this.roomStatus = roomStatus;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public Integer getRoomPricePerDay() {
        return roomPricePerDay;
    }

    public void setRoomPricePerDay(Integer roomPricePerDay) {
        this.roomPricePerDay = roomPricePerDay;
    }

    public Integer getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Integer roomNum) {
        this.roomNum = roomNum;
    }

    public Integer getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Integer roomSize) {
        this.roomSize = roomSize;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(getRoomId(), room.getRoomId()) &&
                Objects.equals(getRoomClass(), room.getRoomClass()) &&
                Objects.equals(getRoomPricePerDay(), room.getRoomPricePerDay()) &&
                Objects.equals(getRoomNum(), room.getRoomNum()) &&
                Objects.equals(getRoomSize(), room.getRoomSize()) &&
                Objects.equals(getRoomStatus(), room.getRoomStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomId(), getRoomClass(), getRoomPricePerDay(), getRoomNum(), getRoomSize(), getRoomStatus());
    }

    @Override
    public String toString() {
        return "HibernateRoom{" +
                "roomId=" + roomId +
                ", roomClass='" + roomClass + '\'' +
                ", roomPricePerDay=" + roomPricePerDay +
                ", roomNum=" + roomNum +
                ", roomSize=" + roomSize +
                ", roomStatus='" + roomStatus + '\'' +
                '}';
    }
}
