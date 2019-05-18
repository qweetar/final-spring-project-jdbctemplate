package ru.myfirstwebsite.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import ru.myfirstwebsite.domain.enums.Sex;

import java.util.Objects;

public class UserCreateRequest {

    private String userName;
    private String userSurname;
    private Sex sex;
    private String mobilePhone;
    private String email;
    private Boolean blocked;
    private String role;

    public UserCreateRequest() {
    }

    public UserCreateRequest(String userName, String userSurname, Sex sex, String mobilePhone, String email, Boolean blocked, String role) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.sex = sex;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.blocked = blocked;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateRequest that = (UserCreateRequest) o;
        return Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getUserSurname(), that.getUserSurname()) &&
                getSex() == that.getSex() &&
                Objects.equals(getMobilePhone(), that.getMobilePhone()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getBlocked(), that.getBlocked()) &&
                Objects.equals(getRole(), that.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getUserSurname(), getSex(), getMobilePhone(), getEmail(), getBlocked(), getRole());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
