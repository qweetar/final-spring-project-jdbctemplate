package ru.myfirstwebsite.repository.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.myfirstwebsite.domain.HibernateRole;
import ru.myfirstwebsite.repository.hibernate.HibernateRoleDao;

import java.util.List;

@Repository
@Qualifier("hibernateRoleDaoImpl")
public class HibernateRoleDaoImpl implements HibernateRoleDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<HibernateRole> findByRoleName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            Query<HibernateRole> query = session.createQuery("select tu from HibernateRole tu where tu.roleName = :rolename", HibernateRole.class);
            query.setParameter("rolename", roleName);
            return query.getResultList();
        }
    }

//    @Override
//    public HibernateRole getRoleByUserId(Long id) {
//        try(Session session = sessionFactory.openSession()) {
//            Query<HibernateRole> query = session.createQuery("select tu from HibernateRole tu where tu.user_iduser = :user_iduser", HibernateRole.class);
//            query.setParameter("user_iduser", user_iduser);
//            return query.getResultList();
//        }
//    }

    @Override
    public List<HibernateRole> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateRole tu", HibernateRole.class).getResultList();
        }
    }

    @Override
    public HibernateRole findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateRole.class, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(findById(id));
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public HibernateRole save(HibernateRole entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newRoleID = (Long) session.save(entity);
            transaction.commit();
            return session.find(HibernateRole.class, newRoleID);
        }
    }

    @Override
    public HibernateRole update(HibernateRole entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);

            HibernateRole role = session.get(HibernateRole.class, entity.getRoleId());
            role.setRoleName(entity.getRoleName());
            role.setUser(entity.getUser());

            session.saveOrUpdate(role);

            transaction.commit();
            return session.find(HibernateRole.class, entity.getRoleId());
        }
    }
}
