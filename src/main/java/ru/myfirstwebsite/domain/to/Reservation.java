package ru.myfirstwebsite.domain.to;

import java.util.Date;
import java.util.Objects;

public class Reservation {

    private Integer reservationId;
    private Integer roomId;
    private Date dateFrom;
    private Date dateTo;

    public Reservation() {
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return getReservationId() == that.getReservationId() &&
                getRoomId() == that.getRoomId() &&
                Objects.equals(getDateFrom(), that.getDateFrom()) &&
                Objects.equals(getDateTo(), that.getDateTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReservationId(), getRoomId(), getDateFrom(), getDateTo());
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", roomId=" + roomId +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
