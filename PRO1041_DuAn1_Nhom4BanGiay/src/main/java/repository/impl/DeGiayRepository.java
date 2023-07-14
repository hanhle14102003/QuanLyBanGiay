/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.DeGiayCustomModel;
import domainModel.DeGiayHiber;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.IDeGiayRes;
import utilities.HibernateUtil;

/**
 *
 * @author admin
 */
public class DeGiayRepository implements IDeGiayRes {

    private static final Session session = HibernateUtil.getFACTORY().openSession();

     @Override
    public List<DeGiayHiber> getAll() {
        Query query = session.createQuery("FROM DeGiayHiber", DeGiayHiber.class);
        List<DeGiayHiber> list_deGiay = query.getResultList();
        return list_deGiay;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<DeGiayCustomModel> getAllCustoms() {
        String hql = "SELECT new customModel.DeGiayCustomModel(A.id, A.ma, A.ten) "
                + "FROM DeGiayHiber A";
        Query query = session.createQuery(hql);
        List<DeGiayCustomModel> list_deGiay = query.getResultList();
        return list_deGiay;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(DeGiayHiber obj) {
        try (Session se = HibernateUtil.getFACTORY().openSession()){           
            Transaction tran = se.getTransaction();
            tran.begin();
            try {
                se.save(obj);
                tran.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace(System.out);
                tran.rollback();
            }
        } 
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(DeGiayHiber obj) {
        int check = 0;
        try {           
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction tran = session.getTransaction();
            tran.begin();
            try {
                Query query = session.createQuery("update DeGiayHiber set Ma = :ma , Ten = :ten where Id = :id ");
                query.setParameter("ma", obj.getMa());
                query.setParameter("ten", obj.getTen());
                query.setParameter("id", obj.getId());
                check = query.executeUpdate();
                tran.commit();
                        
            } catch (Exception e) {
                e.printStackTrace(System.out);
                tran.rollback();
            }
        } finally {
            return check > 0;
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(DeGiayHiber obj) {
        int check = 0;
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            Transaction tran = session.getTransaction();
            tran.begin();
            try {
                Query query = session.createQuery("delete from DeGiayHiber where Id = :id");
                query.setParameter("id", obj.getId());
                check = query.executeUpdate();
                tran.commit();
                
            } catch (Exception e) {               
                e.printStackTrace(System.out);
                tran.rollback();
            }
        } finally {
            return check > 0;
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    
    
    

}
