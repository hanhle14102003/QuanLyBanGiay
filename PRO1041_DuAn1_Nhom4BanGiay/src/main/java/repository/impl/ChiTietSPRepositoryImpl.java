/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.ChiTietSPCustomModel;
import domainModel.ChiTietSanPhamHiber;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilities.HibernateUtil;
import repository.IChiTietSanPhamRes;

/**
 *
 * @author admin
 */
public class ChiTietSPRepositoryImpl implements IChiTietSanPhamRes {

    private static final Session session = HibernateUtil.getFACTORY().openSession();
    private String fromTable = "FROM ChiTietSanPhamHiber";

    @Override
    public List<ChiTietSanPhamHiber> getAll() {
        Query query = session.createQuery(fromTable, ChiTietSanPhamHiber.class);
        List<ChiTietSanPhamHiber> list_ctsp = query.getResultList();
        return list_ctsp;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietSPCustomModel> getAllCustomModels() {
        String hql = "SELECT new customModel.ChiTietSPCustomModel(A.id, F.ma, F.ten, C.ten, B.ten , D.ten  , A.ngayNhapHang, A.donGia, A.soLuong, A.xuatXu, A.kichCo, A.trangThai)\n"
                + "                      FROM ChiTietSanPhamHiber A INNER JOIN\n"
                + "                      DeGiayHiber B ON A.idDeGiay = B.id INNER JOIN\n"
                + "                      DongSPHiber C ON A.idDongSP = C.id INNER JOIN\n"
                + "                      MauSacHiber D ON A.idMauSac = D.id INNER JOIN\n"
                + "                      SanPhamHiber F ON A.idSP = F.id ";
        Query query = session.createQuery(hql);
        List<ChiTietSPCustomModel> list_ctspCustomModels = query.getResultList();
        return list_ctspCustomModels;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     @Override
    public List<ChiTietSPCustomModel> getAllBetWeen(String batDau, String ketThuc) {
                String hql = "SELECT new customModel.ChiTietSPCustomModel(A.id, F.ma, F.ten, C.ten, B.ten , D.ten  , A.ngayNhapHang, A.donGia, A.soLuong, A.xuatXu, A.kichCo, A.trangThai)\n"
                + "                      FROM ChiTietSanPhamHiber A INNER JOIN\n"
                + "                      DeGiayHiber B ON A.idDeGiay = B.id INNER JOIN\n"
                + "                      DongSPHiber C ON A.idDongSP = C.id INNER JOIN\n"
                + "                      MauSacHiber D ON A.idMauSac = D.id INNER JOIN\n"
                + "                      SanPhamHiber F ON A.idSP = F.id \n"
                + "                      where A.donGia between "+batDau +" and "+ketThuc ;
        Query query = session.createQuery(hql);
        List<ChiTietSPCustomModel> list_ctspCustomModels = query.getResultList();
        return list_ctspCustomModels;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   

    @Override
    public boolean add(ChiTietSanPhamHiber obj) {
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.save(obj);
                trans.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace(System.out);
                trans.rollback();
            }

        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(ChiTietSanPhamHiber obj) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                Query query = session.createQuery("UPDATE ChiTietSanPhamHiber set IdSP = :idSP, IdDongSP = :idDongSP, IdDeGiay = :idDeGiay, IdMauSac = :idMauSac, NgayNhapHang = :ngayNhapHang, DonGia = :giaBan, SoLuong = :soLuong, XuatXu = :xuatXu, KichCo = :kichCo, TrangThai = :trangThai where Id = :id");
                query.setParameter("idSP", obj.getIdSP().getId());
                query.setParameter("idDongSP",obj.getIdDongSP().getId());
                query.setParameter("idDeGiay", obj.getIdDeGiay().getId());
                query.setParameter("idMauSac", obj.getIdMauSac().getId());
                query.setParameter("ngayNhapHang",obj.getNgayNhapHang());
                query.setParameter("giaBan", obj.getDonGia());
                query.setParameter("soLuong", obj.getSoLuong());
                query.setParameter("xuatXu", obj.getXuatXu());
                query.setParameter("kichCo", obj.getKichCo());
                query.setParameter("trangThai", obj.getTrangThai());
                query.setParameter("id", obj.getId());
                query.executeUpdate();
                trans.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace(System.out);
                trans.rollback();
            }
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(ChiTietSanPhamHiber obj) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                Query query = session.createQuery("DELETE FROM ChiTietSanPhamHiber where Id=:id ");
                query.setParameter("id", obj.getId());
                query.executeUpdate();
                trans.commit();
                return true;
            } catch (Exception e) {
                e.printStackTrace(System.out);
                trans.rollback();
            }
        }
        return false;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

}
