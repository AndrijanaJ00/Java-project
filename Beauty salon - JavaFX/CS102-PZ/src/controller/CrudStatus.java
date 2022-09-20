package controller;

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
public class CrudStatus {

    private static Session session;

    public static List<Status> readAllCriteria() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Status> listaStatusa = new ArrayList<>();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            listaStatusa = session.createCriteria(Status.class).list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return listaStatusa;
    }

    public static List<Status> readAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Status> listaStatusa = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            listaStatusa = session.createQuery("from usluga").list();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return listaStatusa;
    }
    
    public static Status readOne(Integer id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Status status = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            status = (Status)session.createCriteria(Status.class).add(Restrictions.eq("id", id)).uniqueResult();
            transaction.commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }
}
