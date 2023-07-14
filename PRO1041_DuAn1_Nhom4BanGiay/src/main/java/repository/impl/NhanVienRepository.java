/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.ChucVuCustomModel;
import customModel.NhanVienCustomModel;
import domainModel.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.IInterface;
import repository.INhanVienRes;
import utilities.DBContext;
import utilities.HibernateUtil;

/**
 *
 * @author ADMIN
 */
public class NhanVienRepository implements INhanVienRes<NhanVien> {

    private static final Session session = HibernateUtil.getFACTORY().openSession();
    private String fromTable = "FROM NhanVien";

    @Override
    public List<NhanVien> getAll() {
        Query query = session.createQuery(fromTable, NhanVien.class);
        List<NhanVien> listNhanViens = query.getResultList();
        return listNhanViens;
    }

    public List<NhanVienCustomModel> getAllCustomModel() {
        String hql = "SELECT new customModel.NhanVienCustomModel(B.id,A.ten, B.ma,B.hoTen, B.taiKhoan, B.matKhau, B.sdt, B.email, B.gioiTinh, B.ngaySinh, B.diaChi)"
                + "FROM ChucVu A INNER JOIN NhanVien B ON A.id = B.cv ";
        Query query = session.createQuery(hql);
        List<NhanVienCustomModel> listNhanViens = query.getResultList();
        return listNhanViens;
    }

    public List<NhanVienCustomModel> getAllCustomByMaNV() {
        String query = "SELECT dbo.NhanVien.Id, dbo.ChucVu.Ten, dbo.NhanVien.Ma, dbo.NhanVien.HoTen, dbo.NhanVien.TaiKhoan, dbo.NhanVien.MatKhau, dbo.NhanVien.Sdt, dbo.NhanVien.Email, dbo.NhanVien.GioiTinh, dbo.NhanVien.NgaySinh, \n"
                + "                  dbo.NhanVien.DiaChi\n"
                + "FROM     dbo.ChucVu INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.ChucVu.Id = dbo.NhanVien.IdCV\n"
                + "Where dbo.NhanVien.Ma like '%NV%' Order by Ma desc";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<NhanVienCustomModel> listNV = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                listNV.add(new NhanVienCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getDate(10), rs.getString(11)));
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NhanVien getOne(String ma) {
        NhanVien nv;
        String hql = fromTable + " Where Ma = :ma";
        Query query = session.createQuery(hql, NhanVien.class);
        query.setParameter("ma", ma);
        nv = (NhanVien) query.getSingleResult();
        return nv;
    }

    @Override
    public boolean add(NhanVien t) {
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
    public boolean update(NhanVien t, String ma) {
        int check = 0;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                Query query = session.createQuery("UPDATE NhanVien SET IdCV =:idCVUpdate, Ma = :maUpdate, HoTen = :hoTenUpdate,"
                        + "TaiKhoan = : tkUpdate, MatKhau = : mkUpdate, Sdt = : sdtUpdate, Email =: emailUpdate, GioiTinh =: gioiTinhUpdate,"
                        + " NgaySinh = : ngaySinhUpdate, DiaChi =:diaChiUpdate "
                        + " where Id = :idUpdate");
                query.setParameter("idCVUpdate", t.getCv().getId());
                query.setParameter("maUpdate", t.getMa());
                query.setParameter("hoTenUpdate", t.getHoTen());
                query.setParameter("tkUpdate", t.getTaiKhoan());
                query.setParameter("mkUpdate", t.getMatKhau());
                query.setParameter("sdtUpdate", t.getSdt());
                query.setParameter("emailUpdate", t.getEmail());
                query.setParameter("gioiTinhUpdate", t.getGioiTinh());
                query.setParameter("ngaySinhUpdate", t.getNgaySinh());
                query.setParameter("diaChiUpdate", t.getDiaChi());
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
                Query query = session.createQuery("DELETE FROM NhanVien where Id=:id ");
                query.setParameter("id", ma);
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

    public List<NhanVienCustomModel> search(String ten) {
        String sql = "SELECT dbo.NhanVien.Id, dbo.ChucVu.Ten, dbo.NhanVien.Ma, dbo.NhanVien.HoTen, dbo.NhanVien.TaiKhoan, dbo.NhanVien.MatKhau, dbo.NhanVien.Sdt, dbo.NhanVien.Email, dbo.NhanVien.GioiTinh, dbo.NhanVien.NgaySinh\n"
                + "FROM     dbo.ChucVu INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.ChucVu.Id = dbo.NhanVien.IdCV"
                + "where HoTen like ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            List<NhanVienCustomModel> listNV = new ArrayList<>();
            while (rs.next()) {
                listNV.add(new NhanVienCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9), rs.getDate(10), rs.getString(11)));
            }
            return listNV;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static void main(String[] args) {
        new NhanVienRepository().getAllCustomByMaNV().forEach((t) -> {
            System.out.println(t.toString());
        });
    }
}
