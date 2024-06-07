package org.example.database.dao;

import jakarta.persistence.TypedQuery;
import org.example.database.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDAO {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void insert(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(employee);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Employee findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT e FROM Employee e WHERE e.id = :id";
            TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Employee> findByFirstName(String firstName) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT e FROM Employee e WHERE e.firstname = :firstname";
            TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("firstname", firstName);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Employee> findByLastName(String lastName) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT e FROM Employee e WHERE e.lastname = :lastname";
            TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("lastname", lastName);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
