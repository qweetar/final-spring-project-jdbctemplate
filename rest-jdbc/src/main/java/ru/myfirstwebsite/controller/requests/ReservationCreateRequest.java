package ru.myfirstwebsite.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.Objects;

public class ReservationCreateRequest {
    private Integer roomId;
    private Date dateFrom;
    private Date dateTo;
    private Integer userId;
    private Integer applicationId;
    private Integer billId;

    public ReservationCreateRequest() {
    }

    public ReservationCreateRequest(Integer roomId, Date dateFrom, Date dateTo, Integer userId, Integer applicationId, Integer billId) {
        this.roomId = roomId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userId = userId;
        this.applicationId = applicationId;
        this.billId = billId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationCreateRequest that = (ReservationCreateRequest) o;
        return Objects.equals(getRoomId(), that.getRoomId()) &&
                Objects.equals(getDateFrom(), that.getDateFrom()) &&
                Objects.equals(getDateTo(), that.getDateTo()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getApplicationId(), that.getApplicationId()) &&
                Objects.equals(getBillId(), that.getBillId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomId(), getDateFrom(), getDateTo(), getUserId(), getApplicationId(), getBillId());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
