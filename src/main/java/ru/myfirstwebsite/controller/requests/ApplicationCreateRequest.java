package ru.myfirstwebsite.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;
import java.util.Objects;

public class ApplicationCreateRequest {

    private String roomClass;
    private Integer roomSize;
    private Date dateFrom;
    private Date dateTo;
    private Integer userId;
    private Integer roomId;

    public ApplicationCreateRequest() {
    }

    public ApplicationCreateRequest(String roomClass, Integer roomSize, Date dateFrom, Date dateTo, Integer userId, Integer roomId) {
        this.roomClass = roomClass;
        this.roomSize = roomSize;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userId = userId;
        this.roomId = roomId;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public Integer getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Integer roomSize) {
        this.roomSize = roomSize;
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
        ApplicationCreateRequest that = (ApplicationCreateRequest) o;
        return Objects.equals(getRoomClass(), that.getRoomClass()) &&
                Objects.equals(getRoomSize(), that.getRoomSize()) &&
                Objects.equals(getDateFrom(), that.getDateFrom()) &&
                Objects.equals(getDateTo(), that.getDateTo()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getRoomId(), that.getRoomId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomClass(), getRoomSize(), getDateFrom(), getDateTo(), getUserId(), getRoomId());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
