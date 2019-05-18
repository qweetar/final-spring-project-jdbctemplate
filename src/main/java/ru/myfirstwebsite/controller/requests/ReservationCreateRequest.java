package ru.myfirstwebsite.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.Objects;

public class ReservationCreateRequest {
    private Integer roomId;
    private Date dateFrom;
    private Date dateTo;

    public ReservationCreateRequest() {
    }

    public ReservationCreateRequest(Integer roomId, Date dateFrom, Date dateTo) {
        this.roomId = roomId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
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
        ReservationCreateRequest that = (ReservationCreateRequest) o;
        return Objects.equals(getRoomId(), that.getRoomId()) &&
                Objects.equals(getDateFrom(), that.getDateFrom()) &&
                Objects.equals(getDateTo(), that.getDateTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomId(), getDateFrom(), getDateTo());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
