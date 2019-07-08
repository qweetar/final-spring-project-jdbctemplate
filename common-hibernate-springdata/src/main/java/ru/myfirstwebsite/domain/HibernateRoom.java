package ru.myfirstwebsite.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "roomId")
public class HibernateRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idroom")
    private Integer roomId;

    @Column(name = "room_class")
    private String roomClass;

    @Column(name = "priceday")
    private Integer roomPricePerDay;

    @Column(name = "roomnum")
    private Integer roomNum;

    @Column(name = "roomsize")
    private Integer roomSize;

    @Column(name = "roomstatus")
    private String roomStatus;

//    @JsonBackReference
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "idapplication")
//    private HibernateApplication hiberApplication;

    public HibernateRoom() {
    }

    public HibernateRoom(String roomClass, Integer roomPricePerDay, Integer roomNum, Integer roomSize, String roomStatus) {
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
        HibernateRoom hibernateRoom = (HibernateRoom) o;
        return Objects.equals(getRoomId(), hibernateRoom.getRoomId()) &&
                Objects.equals(getRoomClass(), hibernateRoom.getRoomClass()) &&
                Objects.equals(getRoomPricePerDay(), hibernateRoom.getRoomPricePerDay()) &&
                Objects.equals(getRoomNum(), hibernateRoom.getRoomNum()) &&
                Objects.equals(getRoomSize(), hibernateRoom.getRoomSize()) &&
                Objects.equals(getRoomStatus(), hibernateRoom.getRoomStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomId(), getRoomClass(), getRoomPricePerDay(), getRoomNum(), getRoomSize(), getRoomStatus());
    }
}
