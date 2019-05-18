package ru.myfirstwebsite.domain.to;

import java.util.Objects;

public class Bill {
    private Integer billId;
    private Integer price;
    private Integer applicationId;
    private Integer userId;

    public Bill() {
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
        Bill bill = (Bill) o;
        return Objects.equals(getBillId(), bill.getBillId()) &&
                Objects.equals(getPrice(), bill.getPrice()) &&
                Objects.equals(getApplicationId(), bill.getApplicationId()) &&
                Objects.equals(getUserId(), bill.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBillId(), getPrice(), getApplicationId(), getUserId());
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", price=" + price +
                ", applicationId=" + applicationId +
                ", userId=" + userId +
                '}';
    }
}
