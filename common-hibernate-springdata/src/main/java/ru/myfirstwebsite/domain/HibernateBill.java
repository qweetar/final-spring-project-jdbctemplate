package ru.myfirstwebsite.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bill")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "billId")
public class HibernateBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbill")
    private Integer billId;

    @Column(name = "price")
    private Integer price;

    @Column(name = "idapplication")
    private Integer applicationId;

    @Column(name = "iduser")
    private Integer userId;

    public HibernateBill() {
    }

    public HibernateBill(Integer price, Integer applicationId, Integer userId) {
        this.price = price;
        this.applicationId = applicationId;
        this.userId = userId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
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
        HibernateBill hibernateBill = (HibernateBill) o;
        return Objects.equals(getBillId(), hibernateBill.getBillId()) &&
                Objects.equals(getPrice(), hibernateBill.getPrice()) &&
                Objects.equals(getApplicationId(), hibernateBill.getApplicationId()) &&
                Objects.equals(getUserId(), hibernateBill.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBillId(), getPrice(), getApplicationId(), getUserId());
    }
}
