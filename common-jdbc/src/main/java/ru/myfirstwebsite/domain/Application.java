package ru.myfirstwebsite.domain;

import java.util.Date;
import java.util.Objects;

public class Application {
    private Integer applicationId;
    private String roomClass;
    private Integer roomSize;
    private Date dateFrom;
    private Date dateTo;
    private Integer userId;

    public Application() {
    }

    public Application(Integer applicationId, String roomClass, Integer roomSize, Date dateFrom, Date dateTo, Integer userId) {
        this.applicationId = applicationId;
        this.roomClass = roomClass;
        this.roomSize = roomSize;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.userId = userId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(getApplicationId(), that.getApplicationId()) &&
                Objects.equals(getRoomClass(), that.getRoomClass()) &&
                Objects.equals(getRoomSize(), that.getRoomSize()) &&
                Objects.equals(getDateFrom(), that.getDateFrom()) &&
                Objects.equals(getDateTo(), that.getDateTo()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getApplicationId(), getRoomClass(), getRoomSize(), getDateFrom(), getDateTo(), getUserId());
    }

    @Override
    public String toString() {
        return "HibernateApplication{" +
                "applicationId=" + applicationId +
                ", roomClass='" + roomClass + '\'' +
                ", roomSize=" + roomSize +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", userId=" + userId +
                '}';
    }
}