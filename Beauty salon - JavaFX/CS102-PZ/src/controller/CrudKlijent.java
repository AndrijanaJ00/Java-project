package controller;

import entities.Klijent;
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
public class CrudKlijent {

    private static Session session;

    public static void create(Klijent k) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(k);
            transaction.commit();
            messages.MessageAlerts.showInfoMessage("Uspesno ste dodali klijenta!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Klijent k) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(k);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Klijent k) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(k);
            transaction.commit();
            messages.MessageAlerts.showInfoMessage("Uspesno ste obrisali klijenta!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Klijent> readAllCriteria() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Klijent> listaKlijenata = new ArrayList<>();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            listaKlijenata = session.createCriteria(Klijent.class).list();
             transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return listaKlijenata;
    }

    public static List<Klijent> readAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Klijent> klijenti = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            klijenti = session.createQuery("from klijent").list();
            transaction.commit();

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
         return klijenti;
    }
}
