package ru.myfirstwebsite.controller.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import ru.myfirstwebsite.domain.enums.Sex;

import java.util.Objects;

public class UserCreateRequest {

    private String userName;

    private String userSurname;

    private String login;

    private String password;

    private Sex sex;

    private String mobilePhone;

    private String email;

    private Boolean blocked;

//    private String role;


    public UserCreateRequest() {
    }

    public UserCreateRequest(String userName, String userSurname, String login, String password, Sex sex, String mobilePhone, String email, Boolean blocked) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.login = login;
        this.password = password;
        this.sex = sex;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.blocked = blocked;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ru.myfirstwebsite.controller.request.UserCreateRequest that = (ru.myfirstwebsite.controller.request.UserCreateRequest) o;
        return Objects.equals(getUserName(), that.getUserName()) &&
                Objects.equals(getUserSurname(), that.getUserSurname()) &&
                Objects.equals(getLogin(), that.getLogin()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                getSex() == that.getSex() &&
                Objects.equals(getMobilePhone(), that.getMobilePhone()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getBlocked(), that.getBlocked());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getUserSurname(), getLogin(), getPassword(), getSex(), getMobilePhone(), getEmail(), getBlocked());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
