package org.example.database.dao;

import jakarta.persistence.TypedQuery;
import org.example.database.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserRoleDAO {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void insert(UserRole userRole) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(userRole);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(UserRole userRole) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(userRole);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(UserRole userRole) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(userRole);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserRole findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT ur FROM UserRole ur WHERE ur.id = :id";
            TypedQuery<UserRole> query = session.createQuery(hql, UserRole.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<UserRole> findByUserId(Integer userId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT ur FROM UserRole ur WHERE ur.user.id = :userId";
            TypedQuery<UserRole> query = session.createQuery(hql, UserRole.class);
            query.setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

