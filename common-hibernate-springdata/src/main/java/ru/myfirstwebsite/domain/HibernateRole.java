package ru.myfirstwebsite.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "role")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "roleId")
public class HibernateRole {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idrole")
  private Long roleId;

  //    @Column(name = "user_iduser")
  //    private Long userId;

  @Column(name = "rolename")
  @Size(max = 50, message = "{roleName.maximum.size}")
  private String roleName;

  @JsonBackReference
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_iduser")
  private HibernateUser user;

  public HibernateRole() {}

  public HibernateRole(HibernateUser user, String roleName) {
    this.user = user;
    this.roleName = roleName;
  }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public HibernateUser getUser() {
        return user;
    }

    public void setUser(HibernateUser user) {
        this.user = user;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HibernateRole role = (HibernateRole) o;
        return Objects.equals(getRoleId(), role.getRoleId()) &&
                Objects.equals(getRoleName(), role.getRoleName()) &&
                Objects.equals(getUser(), role.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleId(), getRoleName(), getUser());
    }
}

