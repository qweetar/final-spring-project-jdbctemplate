package ru.myfirstwebsite.controller.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public class BillCreateRequest {

    private Integer price;
    private Integer applicationId;
    private Integer userId;

    public BillCreateRequest() {
    }

    public BillCreateRequest(Integer price, Integer applicationId, Integer userId) {
        this.price = price;
        this.applicationId = applicationId;
        this.userId = userId;
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
        ru.myfirstwebsite.controller.request.BillCreateRequest that = (ru.myfirstwebsite.controller.request.BillCreateRequest) o;
        return Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getApplicationId(), that.getApplicationId()) &&
                Objects.equals(getUserId(), that.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getApplicationId(), getUserId());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
