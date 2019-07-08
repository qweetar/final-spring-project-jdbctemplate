package ru.myfirstwebsite.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reservation")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "reservationId")
public class HibernateReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreservation")
    private Integer reservationId;

    @Column(name = "datefrom")
    private Date dateFrom;

    @Column(name = "dateto")
    private Date dateTo;

    @Column(name = "user_iduser")
    private Integer userId;

    @Column(name = "bill_idbill")
    private Integer billId;

    @Column(name = "application_idapplication")
    private Integer applicationId;

    @Column(name = "room_idroom")
    private Integer roomId;


    public HibernateReservation() {
    }

    public HibernateReservation(Date dateFrom, Date dateTo, Integer userId, Integer billId, Integer applicationId, Integer roomId) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userId = userId;
        this.billId = billId;
        this.applicationId = applicationId;
        this.roomId = roomId;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HibernateReservation that = (HibernateReservation) o;
        return Objects.equals(getReservationId(), that.getReservationId()) &&
                Objects.equals(getDateFrom(), that.getDateFrom()) &&
                Objects.equals(getDateTo(), that.getDateTo()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getBillId(), that.getBillId()) &&
                Objects.equals(getApplicationId(), that.getApplicationId()) &&
                Objects.equals(getRoomId(), that.getRoomId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReservationId(), getDateFrom(), getDateTo(), getUserId(), getBillId(), getApplicationId(), getRoomId());
    }
}
