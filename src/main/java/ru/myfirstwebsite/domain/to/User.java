package ru.myfirstwebsite.domain.to;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import ru.myfirstwebsite.domain.enums.Sex;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private Long userId;
    private String userName;
    private String surname;
    private String login;
    private String password;
    private Sex sex;

    private String mobilePhone;
    private String email;
    private Boolean blocked;
    private String role;

    public User() {    }

    public User(Long userId, String userName, String surname) {
        this.userId = userId;
        this.userName = userName;
        this.surname = surname;
    }

    public User(Long userId, String userName, String surname, String login, String password, Sex sex, String mobilePhone, String email, Boolean blocked, String role) {
        this.userId = userId;
        this.userName = userName;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.sex = sex;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.blocked = blocked;
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        User user = (User) o;
        return Objects.equals(getUserId(), user.getUserId()) &&
                Objects.equals(getUserName(), user.getUserName()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                getSex() == user.getSex() &&
                Objects.equals(getMobilePhone(), user.getMobilePhone()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getBlocked(), user.getBlocked()) &&
                Objects.equals(getRole(), user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserName(), getSurname(), getLogin(), getPassword(), getSex(), getMobilePhone(), getEmail(), getBlocked(), getRole());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}