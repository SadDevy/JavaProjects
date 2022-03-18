package DAO;

import Entities.StudentTest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.text.MessageFormat;

public class StudentTestDAO {
//    private final SessionFactory factory = new Configuration().configure()
//            .addAnnotatedClass(Test.class).buildSessionFactory();

    private SessionFactory factory;

    public StudentTestDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public void add(StudentTest test) {
        try (Session session = factory.openSession()) {
            add(session, test);
        }
    }

    private void add(Session session, StudentTest test) {
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

            StudentTest test = session.get(StudentTest.class, id);
            session.remove(test);

            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null)
                transaction.rollback();

            ex.printStackTrace();
        }
    }

    public StudentTest get(int id)
            throws IllegalStateException {
        try (Session session = factory.openSession()) {
            return get(session, id);
        }
    }

    private StudentTest get(Session session, int id)
            throws IllegalStateException {
        Transaction transaction = null;
        StudentTest test = null;
        try {
            transaction = session.beginTransaction();
            test = session.get(StudentTest.class, id);

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
