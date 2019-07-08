package ru.myfirstwebsite.repository.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.myfirstwebsite.domain.HibernateApplication;
import ru.myfirstwebsite.repository.hibernate.HibernateApplicationDao;

import java.util.Date;
import java.util.List;

@Repository
@Qualifier("hibernateApplicationDaoImpl")
public class HibernateApplicationDaoImpl implements HibernateApplicationDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<HibernateApplication> findByUserId(Integer userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<HibernateApplication> query = session.createQuery("select tu from HibernateApplication tu where tu.userId = :user_iduser", HibernateApplication.class);
            query.setParameter("user_iduser", userId);
            return query.getResultList();
        }
    }

    @Override
    public List<HibernateApplication> findByDateFrom(Date date) {
        try (Session session = sessionFactory.openSession()) {
            Query<HibernateApplication> query = session.createQuery("select tu from HibernateApplication tu where tu.dateFrom = :datefrom", HibernateApplication.class);
            query.setParameter("datefrom", date);
            return query.getResultList();
        }
    }

    @Override
    public List<HibernateApplication> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateApplication tu", HibernateApplication.class).getResultList();
        }
    }

    @Override
    public HibernateApplication findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateApplication.class, id);
        }
    }

    @Override
    public void delete(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(findById(id));
            session.getTransaction().commit();
            session.close();
        }

    }

    @Override
    public HibernateApplication save(HibernateApplication entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Integer newApplicationID = (Integer) session.save(entity);
            transaction.commit();
            return session.find(HibernateApplication.class, newApplicationID);
        }
    }

    @Override
    public HibernateApplication update(HibernateApplication entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);

            HibernateApplication application = session.get(HibernateApplication.class, entity.getApplicationId());
            application.setDateFrom(entity.getDateFrom());
            application.setDateTo(entity.getDateTo());
            application.setRoomClass(entity.getRoomClass());
            application.setRoomSize(entity.getRoomSize());
            application.setUserId(entity.getUserId());

            session.saveOrUpdate(application);

            transaction.commit();
            return session.find(HibernateApplication.class, entity.getApplicationId());
        }
    }
}
