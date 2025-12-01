package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TaskDAO {

    public void save(Task task) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(task);
            tx.commit();
        }
    }

    public void update(Task task) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(task);
            tx.commit();
        }
    }

    public void delete(Task task) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.remove(task);
            tx.commit();
        }
    }

    public List<Task> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Task", Task.class).list();
        }
    }
}
