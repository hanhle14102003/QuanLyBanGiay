/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.KhachHangCustomModel;
import domainModel.KhachHang;
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
public class KhachHangRepository implements IInterface<KhachHang> {

    @Override
    public List<KhachHang> getAll() {
        String query = "Select * from KhachHang";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<KhachHang> listKH = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listKH.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<KhachHangCustomModel> getAllCustom() {
        String query = "Select * from KhachHang Order by Ma desc";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<KhachHangCustomModel> listKH = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listKH.add(new KhachHangCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
            }
            return listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public KhachHang getOne(String ma) {
        String query = "Select * from KhachHang Where Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            List<KhachHang> listKH = new ArrayList<>();
            while (rs.next()) {
                listKH.add(new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5)));
            }
            return (KhachHang) listKH;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String add(KhachHang kh) {
        String query = "INSERT INTO [dbo].[KhachHang]\n"
                + "           (\n"
                + "            [Ma]\n"
                + "           ,[HoTen]\n"
                + "           ,[Sdt]\n"
                + "           ,[NgaySinh]\n"
                + "           )\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, kh.getMa());
            ps.setObject(2, kh.getHoTen());
            ps.setObject(3, kh.getSdt());
            ps.setObject(4, kh.getNgaySinh());

            if (ps.executeUpdate() > 0) {
                return "Thêm thành công";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(KhachHang kh, String ma) {
        String query = "UPDATE [dbo].[KhachHang]\n"
                + "   SET \n"
                + "      [HoTen] = ?\n"
                + "      ,[Sdt] = ?\n"
                + "      ,[NgaySinh] = ?\n"
                + "      \n"
                + " WHERE Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(4, ma);
            ps.setObject(1, kh.getHoTen());
            ps.setObject(2, kh.getSdt());
            ps.setObject(3, kh.getNgaySinh());

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
        String query = "Delete from KhachHang where ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);

            if (ps.executeUpdate() > 0) {
                return "Xóa thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Xóa thất bại, khách hàng đã được thêm vào hóa đơn không thể xóa";
    }

    public static void main(String[] args) {
        new KhachHangRepository().getAll().forEach((t) -> {
            System.out.println(t.toString());
        });
    }
}
