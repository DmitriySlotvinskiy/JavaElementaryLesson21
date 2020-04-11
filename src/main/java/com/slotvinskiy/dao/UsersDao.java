package com.slotvinskiy.dao;

import com.slotvinskiy.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class UsersDao {

    private SessionFactory sessionFactory;

    public UsersDao() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void close() {
        sessionFactory.close();
    }

    public void removeAll() {
        System.out.println(clear() + " users were deleted");
    }

    private int clear() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = String.format("DELETE FROM %s", User.class.getSimpleName());
            Query query = session.createQuery(hql);
            int count = query.executeUpdate();
            transaction.commit();
            return count;
        }
    }

    public void addUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }

    public void removeUser(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public void removeUserByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = getUserByName(name);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    public User getUser(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM User WHERE id = :id ", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }

    public User getUserByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM User WHERE name = :name ", User.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return null;
    }

    public List<User> getByAge(int from, int to) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM User WHERE age BETWEEN :from AND :to ORDER BY age", User.class)
                    .setParameter("from", from)
                    .setParameter("to", to)
                    .list();
        }
    }

}
