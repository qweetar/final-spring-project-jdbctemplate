package ru.myfirstwebsite.controller.request;

import org.apache.commons.lang3.builder.ToStringBuilder;
import ru.myfirstwebsite.domain.HibernateUser;

import java.util.Objects;

public class RoleCreateRequest {

    private HibernateUser user;
    private String roleName;

    public RoleCreateRequest() {
    }

    public RoleCreateRequest(HibernateUser user, String roleName) {
        this.user = user;
        this.roleName = roleName;
    }

    public HibernateUser getUser() {
        return user;
    }

    public void setUser(HibernateUser user) {
        this.user = user;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleCreateRequest that = (RoleCreateRequest) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getRoleName(), that.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getRoleName());
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
