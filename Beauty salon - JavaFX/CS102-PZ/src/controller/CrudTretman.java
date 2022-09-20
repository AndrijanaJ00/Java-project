package controller;

import entities.Status;
import entities.Tretman;
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
public class CrudTretman {

    private static Session session;

    public static void create(Tretman t) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(t);
            transaction.commit();
            messages.MessageAlerts.showInfoMessage("Uspesno ste dodali tretman!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(Tretman t) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Tretman t) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            messages.MessageAlerts.showInfoMessage("Uspesno ste obrisali tretman!");
        } catch (HibernateException ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Tretman> readAllCriteria() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Tretman> listaTretmana = new ArrayList<>();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            listaTretmana = session.createCriteria(Tretman.class).list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return listaTretmana;
    }

    public static List<Tretman> readAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Tretman> listaRadnika = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            listaRadnika = session.createQuery("from tretman").list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return listaRadnika;
    }

    public static List<Tretman> readByStatus(Status status) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Tretman> tretman = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            tretman = session.createCriteria(Tretman.class).add(Restrictions.eq("idStatusa", status)).list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return tretman;
    }
}
