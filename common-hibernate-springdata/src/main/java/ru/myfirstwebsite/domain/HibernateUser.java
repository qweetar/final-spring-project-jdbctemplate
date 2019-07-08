package ru.myfirstwebsite.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import ru.myfirstwebsite.domain.enums.Sex;

import javax.persistence.*;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class HibernateUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser")
    private Long userId;

    @Column(name = "name")
    private String userName;

    @Column(name = "surname")
    private String surName;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex")
    private Sex sex;

    @Column(name = "phone")
    private String mobilePhone;

    @Column(name = "email")
    private String email;

    @org.hibernate.annotations.Type(type = "yes_no")
    @Column(name = "blocked")
    private Boolean blocked;

//    @Column(name = "role")
//    private String role;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<HibernateRole> role = Collections.emptySet();


    public HibernateUser() {
    }

    public HibernateUser(String userName, String surName, String login, String password, Sex sex, String mobilePhone, String email, Boolean blocked) {
        this.userName = userName;
        this.surName = surName;
        this.login = login;
        this.password = password;
        this.sex = sex;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.blocked = blocked;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surname) {
        this.surName = surname;
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
        HibernateUser hibernateUser = (HibernateUser) o;
        return Objects.equals(getUserId(), hibernateUser.getUserId()) &&
                Objects.equals(getUserName(), hibernateUser.getUserName()) &&
                Objects.equals(getSurName(), hibernateUser.getSurName()) &&
                Objects.equals(getLogin(), hibernateUser.getLogin()) &&
                Objects.equals(getPassword(), hibernateUser.getPassword()) &&
                getSex() == hibernateUser.getSex() &&
                Objects.equals(getMobilePhone(), hibernateUser.getMobilePhone()) &&
                Objects.equals(getEmail(), hibernateUser.getEmail()) &&
                Objects.equals(getBlocked(), hibernateUser.getBlocked());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUserName(), getSurName(), getLogin(), getPassword(), getSex(), getMobilePhone(), getEmail(), getBlocked());
    }
}
