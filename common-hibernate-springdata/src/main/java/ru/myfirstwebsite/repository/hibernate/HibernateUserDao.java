package ru.myfirstwebsite.repository.hibernate;


import ru.myfirstwebsite.domain.HibernateUser;
import ru.myfirstwebsite.repository.GenericDao;

import java.util.List;

public interface HibernateUserDao extends GenericDao<HibernateUser, Long> {
    HibernateUser findByLogin(String login);

    List<HibernateUser> search(String query, Integer limit, Integer offset);
}
