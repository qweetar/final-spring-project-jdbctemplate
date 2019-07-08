package ru.myfirstwebsite.controller.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class RoomCreateRequest {

    private String roomClass;
    private Integer roomPricePerDay;
    private Integer roomNum;
    private Integer roomSize;
    private String roomStatus;

    public RoomCreateRequest() {
    }

    public RoomCreateRequest(String roomClass, Integer roomPricePerDay, Integer roomNum, Integer roomSize, String roomStatus) {
        this.roomClass = roomClass;
        this.roomPricePerDay = roomPricePerDay;
        this.roomNum = roomNum;
        this.roomSize = roomSize;
        this.roomStatus = roomStatus;
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
        ru.myfirstwebsite.controller.request.RoomCreateRequest that = (ru.myfirstwebsite.controller.request.RoomCreateRequest) o;
        return Objects.equals(getRoomClass(), that.getRoomClass()) &&
                Objects.equals(getRoomPricePerDay(), that.getRoomPricePerDay()) &&
                Objects.equals(getRoomNum(), that.getRoomNum()) &&
                Objects.equals(getRoomSize(), that.getRoomSize()) &&
                Objects.equals(getRoomStatus(), that.getRoomStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomClass(), getRoomPricePerDay(), getRoomNum(), getRoomSize(), getRoomStatus());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
