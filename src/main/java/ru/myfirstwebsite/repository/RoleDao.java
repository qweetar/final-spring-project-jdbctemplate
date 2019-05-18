package ru.myfirstwebsite.repository;


import ru.myfirstwebsite.domain.to.Role;

import java.util.List;

public interface RoleDao extends GenericDao<Role, Long> {

    List<Role> getRolesByUserId(Long userId);
}
