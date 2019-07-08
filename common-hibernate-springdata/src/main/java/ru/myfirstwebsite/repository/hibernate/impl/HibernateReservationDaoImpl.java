package ru.myfirstwebsite.repository.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.myfirstwebsite.domain.HibernateReservation;
import ru.myfirstwebsite.repository.hibernate.HibernateReservationDao;

import java.util.Date;
import java.util.List;

@Repository
@Qualifier("hibernateReservationDaoImpl")
public class HibernateReservationDaoImpl implements HibernateReservationDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<HibernateReservation> findByRoomId(Integer roomId) {
        try (Session session = sessionFactory.openSession()) {
            Query<HibernateReservation> query = session.createQuery("select tu from HibernateReservation tu where tu.roomId = :roomId", HibernateReservation.class);
            query.setParameter("roomId", roomId);
            return query.getResultList();
        }
    }

    @Override
    public List<HibernateReservation> findByDateFrom(Date dateFrom) {
        try (Session session = sessionFactory.openSession()) {
            Query<HibernateReservation> query = session.createQuery("select tu from HibernateReservation tu where tu.dateFrom = :dateFrom", HibernateReservation.class);
            query.setParameter("dateFrom", dateFrom);
            return query.getResultList();
        }
    }

    @Override
    public HibernateReservation findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateReservation.class, id);
        }
    }

    @Override
    public List<HibernateReservation> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateReservation tu", HibernateReservation.class).getResultList();
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
    public HibernateReservation save(HibernateReservation entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Integer newReservationID = (Integer) session.save(entity);
            transaction.commit();
            return session.find(HibernateReservation.class, newReservationID);
        }
    }

    @Override
    public HibernateReservation update(HibernateReservation entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);

            HibernateReservation reservation = session.get(HibernateReservation.class, entity.getReservationId());
            reservation.setDateFrom(entity.getDateFrom());
            reservation.setDateTo(entity.getDateTo());
            reservation.setRoomId(entity.getRoomId());
            reservation.setUserId(entity.getUserId());

            session.saveOrUpdate(reservation);

            transaction.commit();
            return session.find(HibernateReservation.class, entity.getRoomId());
        }
    }
}
