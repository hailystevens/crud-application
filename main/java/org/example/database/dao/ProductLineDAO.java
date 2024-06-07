package org.example.database.dao;

import jakarta.persistence.TypedQuery;
import org.example.database.entity.ProductLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductLineDAO {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void insert(ProductLine productLine) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(productLine);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(ProductLine productLine) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(productLine);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(ProductLine productLine) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(productLine);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ProductLine findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT pl FROM ProductLine pl WHERE pl.id = :id";
            TypedQuery<ProductLine> query = session.createQuery(hql, ProductLine.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProductLine> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT pl FROM ProductLine pl";
            TypedQuery<ProductLine> query = session.createQuery(hql, ProductLine.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

