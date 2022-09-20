package controller;

import entities.Usluga;
import java.util.ArrayList;
import java.util.List;
import main.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Andrijana Jovanovic
 */
public class CrudUsluga {

    private static Session session;

    public static void create(Usluga u) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(u);
            transaction.commit();
            messages.MessageAlerts.showInfoMessage("Uspesno ste dodali uslugu!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public static void createFromWeb(Usluga u) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(u);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Usluga u) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(u);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Usluga u) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(u);
            transaction.commit();
            messages.MessageAlerts.showInfoMessage("Uspesno ste obrisali uslugu!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Usluga> readAllCriteria() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Usluga> listaUsluga = new ArrayList<>();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            listaUsluga = session.createCriteria(Usluga.class).list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return listaUsluga;
    }

    public static List<Usluga> readAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Usluga> klijenti = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            klijenti = session.createQuery("from usluga").list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return klijenti;
    }
}
