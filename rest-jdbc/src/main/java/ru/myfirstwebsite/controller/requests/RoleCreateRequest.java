package ru.myfirstwebsite.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;
import ru.myfirstwebsite.domain.User;

import java.util.Objects;

public class RoleCreateRequest {

    private User user;
    private String roleName;

    public RoleCreateRequest() {
    }

    public RoleCreateRequest(User user, String roleName) {
        this.user = user;
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
