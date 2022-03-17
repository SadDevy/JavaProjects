package DAO;

import Entities.Test;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.MessageFormat;

public class TestDAO {
    private final SessionFactory factory = new Configuration().configure()
            .addAnnotatedClass(Test.class).buildSessionFactory();

    public void add(Test test) {
        try (Session session = factory.openSession()) {
            add(session, test);
        }
    }

    private void add(Session session, Test test) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(test);

            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();

            ex.printStackTrace();
        }
    }

    public void remove(int id) {
        try (Session session = factory.openSession()) {
            remove(session, id);
        }
    }

    private void remove(Session session, int id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Test test = session.get(Test.class, id);
            session.remove(test);

            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();

            ex.printStackTrace();
        }
    }

    public Test get(int id)
            throws IllegalStateException {
        try (Session session = factory.openSession()) {
            return get(session, id);
        }
    }

    private Test get(Session session, int id)
            throws IllegalStateException {
        Transaction transaction = null;
        Test test = null;
        try {
            transaction = session.beginTransaction();
            test = session.get(Test.class, id);

            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();

            ex.printStackTrace();
        }

        if (test == null) {
            String failureMessage = MessageFormat.format("Теста с id = {0} не существует в БД.", id);
            throw new IllegalStateException(failureMessage);
        }

        return test;
    }
}
