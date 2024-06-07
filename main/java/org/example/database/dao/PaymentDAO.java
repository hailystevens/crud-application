package org.example.database.dao;

import jakarta.persistence.TypedQuery;
import org.example.database.entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class PaymentDAO {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void insert(Payment payment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(payment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Payment payment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(payment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Payment payment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(payment);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Payment findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT p FROM Payment p WHERE p.id = :id";
            TypedQuery<Payment> query = session.createQuery(hql, Payment.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Payment> findByCustomerId(Integer customerId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT p FROM Payment p WHERE p.customer.id = :customerId";
            TypedQuery<Payment> query = session.createQuery(hql, Payment.class);
            query.setParameter("customerId", customerId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

