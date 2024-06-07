package org.example.database.dao;

import jakarta.persistence.TypedQuery;
import org.example.database.entity.OrderDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class OrderDetailsDAO {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void insert(OrderDetails orderDetails) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(orderDetails);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(OrderDetails orderDetails) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(orderDetails);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(OrderDetails orderDetails) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(orderDetails);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OrderDetails findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT od FROM OrderDetails od WHERE od.id = :id";
            TypedQuery<OrderDetails> query = session.createQuery(hql, OrderDetails.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderDetails> findByOrderId(Integer orderId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT od FROM OrderDetails od WHERE od.order.id = :orderId";
            TypedQuery<OrderDetails> query = session.createQuery(hql, OrderDetails.class);
            query.setParameter("orderId", orderId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

