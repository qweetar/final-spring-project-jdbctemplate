package ru.myfirstwebsite.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "application")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "applicationId")
public class HibernateApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idapplication")
    private Integer applicationId;

    @Column(name = "room_class")
    private String roomClass;

    @Column(name = "roomsize")
    private Integer roomSize;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "datefrom")
    private Date dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dateto")
    private Date dateTo;

    @Column(name = "user_iduser")
    private Integer userId;


    public HibernateApplication() {
    }

    public HibernateApplication(String roomClass, Integer roomSize, Date dateFrom, Date dateTo, Integer userId) {
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
        HibernateApplication that = (HibernateApplication) o;
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
}
