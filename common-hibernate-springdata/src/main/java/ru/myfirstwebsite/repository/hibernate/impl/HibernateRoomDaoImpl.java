package ru.myfirstwebsite.repository.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.myfirstwebsite.domain.HibernateRoom;
import ru.myfirstwebsite.repository.hibernate.HibernateRoomDao;

import java.util.List;

@Repository
@Qualifier("hibernateRoomDao")
public class HibernateRoomDaoImpl implements HibernateRoomDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public List<HibernateRoom> findByRoomClass(String roomClass) {
        try (Session session = sessionFactory.openSession()) {
            Query<HibernateRoom> query = session.createQuery("select tu from HibernateRoom tu where tu.roomClass = :room_class", HibernateRoom.class);
            query.setParameter("room_class", roomClass);
            return query.getResultList();
        }
    }
    @Override
    public List<HibernateRoom> findByPricePerDay(Integer price) {
        try (Session session = sessionFactory.openSession()) {
            Query<HibernateRoom> query = session.createQuery("select tu from HibernateRoom tu where tu.roomPricePerDay = :priceday", HibernateRoom.class);
            query.setParameter("priceday", price);
            return query.getResultList();
        }
    }

    @Override
    public List<HibernateRoom> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateRoom tu", HibernateRoom.class).getResultList();
        }
    }

    @Override
    public HibernateRoom findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateRoom.class, id);
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
    public HibernateRoom save(HibernateRoom entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Integer newRoomID = (Integer) session.save(entity);
            transaction.commit();
            return session.find(HibernateRoom.class, newRoomID);
        }
    }

    @Override
    public HibernateRoom update(HibernateRoom entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);

            HibernateRoom room = session.get(HibernateRoom.class, entity.getRoomId());
            room.setRoomClass(entity.getRoomClass());
            room.setRoomNum(entity.getRoomNum());
            room.setRoomPricePerDay(entity.getRoomPricePerDay());
            room.setRoomSize(entity.getRoomSize());
            room.setRoomStatus(entity.getRoomStatus());

            session.saveOrUpdate(room);

            transaction.commit();
            return session.find(HibernateRoom.class, entity.getRoomId());
        }
    }
}


