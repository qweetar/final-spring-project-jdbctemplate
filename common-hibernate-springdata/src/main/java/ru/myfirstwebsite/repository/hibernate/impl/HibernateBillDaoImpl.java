package ru.myfirstwebsite.repository.hibernate.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.myfirstwebsite.domain.HibernateBill;
import ru.myfirstwebsite.repository.hibernate.HibernateBillDao;

import java.util.List;

@Repository
@Qualifier("hibernateBillDaoImpl")
public class HibernateBillDaoImpl implements HibernateBillDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<HibernateBill> findByUserId(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            Query<HibernateBill> query = session.createQuery("select tu from HibernateBill tu where tu.userId = :iduser", HibernateBill.class);
            query.setParameter("iduser", id);
            return query.getResultList();
        }
    }

    @Override
    public List<HibernateBill> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HibernateBill tu", HibernateBill.class).getResultList();
        }
    }

    @Override
    public HibernateBill findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HibernateBill.class, id);
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
    public HibernateBill save(HibernateBill entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Integer newBillID = (Integer) session.save(entity);
            transaction.commit();
            return session.find(HibernateBill.class, newBillID);
        }
    }

    @Override
    public HibernateBill update(HibernateBill entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);

            HibernateBill bill = session.get(HibernateBill.class, entity.getBillId());
            bill.setApplicationId(entity.getApplicationId());
            bill.setPrice(entity.getPrice());
            bill.setUserId(entity.getUserId());

            session.saveOrUpdate(bill);

            transaction.commit();
            return session.find(HibernateBill.class, entity.getBillId());
        }
    }
}
