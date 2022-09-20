package controller;

import entities.Radnik;
import entities.Status;
import java.util.ArrayList;
import java.util.List;
import main.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Andrijana Jovanovic
 */
public class CrudRadnik {

    private static Session session;

    public static void create(Radnik r) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(r);
            transaction.commit();
            messages.MessageAlerts.showInfoMessage("Uspesno ste dodali radnika!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Radnik r) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(r);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Radnik r) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(r);
            transaction.commit();
            messages.MessageAlerts.showInfoMessage("Uspesno ste obrisali radnika!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Radnik> readAllCriteria() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Radnik> listaRadnika = new ArrayList<>();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            listaRadnika = session.createCriteria(Radnik.class).list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return listaRadnika;
    }

    public static List<Radnik> readAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Radnik> listaRadnika = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            listaRadnika = session.createQuery("from radnik").list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
         return listaRadnika;
    }
    
     public static Radnik readById(Integer id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Radnik radnik = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            radnik = (Radnik)session.createCriteria(Radnik.class).add(Restrictions.eq("id", id)).uniqueResult();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return radnik;
    }
}

