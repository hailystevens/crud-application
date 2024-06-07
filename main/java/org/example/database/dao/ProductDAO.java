package org.example.database.dao;

import jakarta.persistence.TypedQuery;
import org.example.database.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {
    private static SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public List<Product> findLikeName(String name) {
        try (Session session = factory.openSession()) {
            String hql = "SELECT p FROM Product p WHERE lower(p.productName) LIKE lower(CONCAT('%', :productName, '%'))";
            TypedQuery<Product> query = session.createQuery(hql, Product.class);
            query.setParameter("productName", name);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product findById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(Product product) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

