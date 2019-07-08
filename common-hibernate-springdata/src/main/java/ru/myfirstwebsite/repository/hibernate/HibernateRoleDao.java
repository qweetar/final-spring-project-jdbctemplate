package ru.myfirstwebsite.repository.hibernate;


import ru.myfirstwebsite.domain.HibernateRole;
import ru.myfirstwebsite.repository.GenericDao;

import java.util.List;

public interface HibernateRoleDao extends GenericDao<HibernateRole, Long> {
    List<HibernateRole> findByRoleName(String roleName);

//    HibernateRole getRoleByUserId(Long id);
}
