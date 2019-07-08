package ru.myfirstwebsite.domain;

import java.io.Serializable;

public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long roleId;
    private Long userId;
    private String roleName;

    public Role(){}

    public Role(Long userId, String roleName) {
        this.userId = userId;
        this.roleName = roleName;
    }

    public Role(Long roleId, Long userId, String roleName) {
        this.roleId = roleId;
        this.userId = userId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Role role = (Role) o;

        if (!roleId.equals(role.roleId)) {
            return false;
        }
        if (!userId.equals(role.userId)) {
            return false;
        }
        return roleName.equals(role.roleName);
    }

    @Override
    public int hashCode() {
        return (int)(7 * this.roleId.hashCode() + 7 * this.userId.hashCode() + 7 * this.roleName.hashCode());
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("\n");
        sb.append("HibernateRole ID: ");
        sb.append(this.roleId);
        sb.append("\n");
        sb.append("HibernateUser ID: ");
        sb.append(this.userId);
        sb.append("\n");
        sb.append("HibernateRole name: ");
        sb.append(this.roleName);
        sb.append("\n");

        return sb.toString();
    }
}
