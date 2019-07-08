package ru.myfirstwebsite.repository;


import ru.myfirstwebsite.domain.Role;

import java.util.List;

public interface RoleDao extends GenericDao<Role, Long> {

    List<Role> getRolesByUserId(Long userId);

    void deleteByUserId(Long id);
}
