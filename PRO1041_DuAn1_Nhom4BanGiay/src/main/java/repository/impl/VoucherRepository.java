/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.VoucherCustomModel;
import domainModel.Voucher;
import domainModel.Voucher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import repository.IInterface;
import utilities.DBContext;

/**
 *
 * @author ADMIN
 */
public class VoucherRepository implements IInterface<Voucher> {
    
    @Override
    public List<Voucher> getAll() {
        String query = "Select * from Voucher";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<Voucher> listKH = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listKH.add(new Voucher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getInt(8)));
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<VoucherCustomModel> getAllCustom() {
        String query = "Select * from Voucher Order by Ma desc";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<VoucherCustomModel> listKH = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listKH.add(new VoucherCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getInt(8)));
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<VoucherCustomModel> getVoucherTrangThai() {
        String query = "Select * from Voucher Where TrangThai = 0";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<VoucherCustomModel> listKH = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listKH.add(new VoucherCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getInt(8)));
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Voucher getOne(String ma) {
        String query = "Select * from Voucher Where Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            List<Voucher> listKH = new ArrayList<>();
            while (rs.next()) {
                listKH.add(new Voucher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDate(5), rs.getDate(6), rs.getString(7), rs.getInt(8)));
                
            }
            return (Voucher) listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public String add(Voucher v) {
        String query = "INSERT INTO [dbo].[Voucher]\n"
                + "           (\n"
                + "			 [Ma]\n"
                + "           ,[Ten]\n"
                + "           ,[GiamGia]\n"
                + "           ,[NgayBatDau]\n"
                + "           ,[NgayHetHan]\n"
                + "           ,[GhiChu]\n"
                + "           ,[TrangThai])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setObject(1, v.getMa());
            ps.setObject(2, v.getTen());
            ps.setObject(3, v.getGiamGia());
            ps.setObject(4, v.getNgayBatDau());
            ps.setObject(5, v.getNgayHetHan());
            ps.setObject(6, v.getGhiChu());
            ps.setObject(7, v.getTrangThai());
            
            if (ps.executeUpdate() > 0) {
                return "Thêm thành công";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thất bại";
    }
    
    @Override
    public String update(Voucher v, String ma) {
        String query = "UPDATE [dbo].[Voucher]\n"
                + "   SET\n"
                + "      [Ten] = ?	\n"
                + "      ,[GiamGia] = ?\n"
                + "      ,[NgayBatDau] = ?\n"
                + "      ,[NgayHetHan] = ?\n"
                + "      ,[GhiChu] = ?\n"
                + "      ,[TrangThai] = ?\n"
                + " WHERE Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setObject(1, v.getTen());
            ps.setObject(2, v.getGiamGia());
            ps.setObject(3, v.getNgayBatDau());
            ps.setObject(4, v.getNgayHetHan());
            ps.setObject(5, v.getGhiChu());
            ps.setObject(6, v.getTrangThai());
            ps.setObject(7, ma);
            
            if (ps.executeUpdate() > 0) {
                return "sửa thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Sửa thất bại";
    }
    
    @Override
    public String delete(String ma) {
        String query = "Delete from Voucher where Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);
            
            if (ps.executeUpdate() > 0) {
                return "Xóa thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Xóa thất bại";
    }
    
    public static void main(String[] args) {
        new VoucherRepository().getAll().forEach((t) -> {
            System.out.println(t.toString());
        });
    }
    
}
