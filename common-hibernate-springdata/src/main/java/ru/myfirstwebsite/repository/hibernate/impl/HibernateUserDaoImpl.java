package ru.myfirstwebsite.repository.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.myfirstwebsite.domain.HibernateRole;
import ru.myfirstwebsite.domain.HibernateUser;
import ru.myfirstwebsite.repository.hibernate.HibernateUserDao;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Qualifier("hibernateUserDaoImpl")
public class HibernateUserDaoImpl implements HibernateUserDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public HibernateUser findByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            Query<HibernateUser> query = session.createQuery("select tu from HibernateUser tu where tu.login = :login", HibernateUser.class);
            query.setParameter("login", login);
            return query.getSingleResult();
        }
    }

    @Override
    public List<HibernateUser> search(String query, Integer limit, Integer offset) {
        return null;
    }


    @Override
    public List<HibernateUser> findAll() {
        try (Session session = sessionFactory.openSession()){
            return session.createQuery("select tu from HibernateUser tu", HibernateUser.class).getResultList();
        }
    }

    @Override
    public HibernateUser findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateUser.class, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public HibernateUser save(HibernateUser entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newUserID = (Long) session.save(entity);
            transaction.commit();
            return session.find(HibernateUser.class, newUserID);
        }
    }


    @Override
    public HibernateUser update(HibernateUser entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);

            HibernateUser user = session.get(HibernateUser.class, entity.getUserId());
//            user.setRole(entity.getRole());
            user.setEmail(entity.getEmail());
            user.setMobilePhone(entity.getMobilePhone());
            user.setSex(entity.getSex());
            user.setPassword(entity.getPassword());
            user.setLogin(entity.getLogin());
            user.setSurName(entity.getSurName());
            user.setUserName(entity.getUserName());
            user.setBlocked(entity.getBlocked());

            session.saveOrUpdate(user);

            transaction.commit();
            return session.find(HibernateUser.class, entity.getUserId());
        }
    }
//
//    @Override
//    public List<HibernateUser> search(String searchQuery, Integer limit, Integer offset) {
//        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
//
//        CriteriaQuery<HibernateUser> query = cb.createQuery(HibernateUser.class);
//        Root<HibernateUser> root = query.from(HibernateUser.class);
//
//        CriteriaQuery<HibernateRole> roleQuery = cb.createQuery(HibernateRole.class);
//        Root<HibernateRole> roleRoot = roleQuery.from(HibernateRole.class);
//
//        ParameterExpression<String> param = cb.parameter(String.class);
//        Expression<Long> id = root.get(HibernateUser_.userId);
//
//        query.select(root)
//                .distinct(true)
//                .where(
//                        cb.or(
//                                cb.like(root.get("userName"), param),
//                                cb.like(root.get("userSurname"), param)
//                        ),
//                        cb.and(
//                                cb.gt(root.get(HibernateUser_.userId), 0L),
//                                id.in(1L)
//                        ),
//                        cb.between(
//                                root.get(HibernateUser_.mobilePhone),
//                                new String(),
//                                new String()
//                        )
//                ).orderBy(cb.asc(root.get("userName")));
//        try (Session session = sessionFactory.openSession()) {
//            TypedQuery<HibernateUser> resultQuery = session.createQuery(query);
//            resultQuery.setParameter(param, searchQuery);
//            resultQuery.setMaxResults(limit);
//            resultQuery.setFirstResult(offset);
//            return resultQuery.getResultList();
//        }
//    }
}
