/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.ChucVuCustomModel;
import domainModel.ChucVu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.IChucVuRes;
import repository.IInterface;
import utilities.DBContext;
import utilities.HibernateUtil;

/**
 *
 * @author ADMIN
 */
public class ChucVuRepository implements IChucVuRes<ChucVu> {

    private static final Session session = HibernateUtil.getFACTORY().openSession();
    private String fromTable = "FROM ChucVu";

    @Override
    public List<ChucVu> getAll() {
        Query query = session.createQuery(fromTable, ChucVu.class);
        List<ChucVu> listChucVus = query.getResultList();
        return listChucVus;
    }

    public List<ChucVuCustomModel> getAllCustom() {
        String hql = "SELECT new customModel.ChucVuCustomModel(A.id, A.ma, A.ten)"
                + " FROM ChucVu A";
        Query query = session.createQuery(hql);
        List<ChucVuCustomModel> listChucVus = query.getResultList();
        return listChucVus;

    }

    @Override
    public ChucVu getOne(String ma) {
        ChucVu cv;
        String hql = fromTable + " Where Ma = :ma";
        Query query = session.createQuery(hql, ChucVu.class);
        query.setParameter("ma", ma);
        cv = (ChucVu) query.getSingleResult();
        return cv;
    }

    @Override
    public boolean add(ChucVu t) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(t);
                trans.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace(System.out);
                trans.rollback();
            }
        }
        return false;
    }

    @Override
    public boolean update(ChucVu t, String ma) {
        int check = 0;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                Query query = session.createQuery("UPDATE ChucVu SET Ma = :maUpdate, Ten = :tenUpdate where Id = :idUpdate");
                query.setParameter("maUpdate", t.getMa());
                query.setParameter("tenUpdate", t.getTen());
                query.setParameter("idUpdate", ma);
                check = query.executeUpdate();
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace(System.out);
                trans.rollback();
            }
        } finally {
            return check > 0;
        }
    }

    @Override
    public boolean delete(String ma) {
        int check = 0;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                Query query = session.createQuery("DELETE FROM ChucVu where Ma=:ma ");
                query.setParameter("ma", ma);
                check = query.executeUpdate();
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace(System.out);
                trans.rollback();
            }
        } finally {
            return check > 0;
        }
    }

    public List<ChucVuCustomModel> search(String ten) {
        String sql = "SELECT Id, Ma, Ten\n"
                + "FROM     ChucVu\n"
                + "where Ten like ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            List<ChucVuCustomModel> listKH = new ArrayList<>();
            while (rs.next()) {
                listKH.add(new ChucVuCustomModel(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listKH;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
 
    public static void main(String[] args) {
        new ChucVuRepository().getAll().forEach((t) -> {
            System.out.println(t.toString());
        });
    }
}
