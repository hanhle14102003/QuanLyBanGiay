/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.KhachHangCustomModel;
import customModel.MauSacCustomModel;
import domainModel.MauSac;
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
public class MauSacRepository implements IInterface<MauSac> {

    @Override
    public List<MauSac> getAll() {
        String query = "Select * from MauSac";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<MauSac> listMS = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listMS.add(new MauSac(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<MauSacCustomModel> getAllCustom() {
        String query = "Select * from MauSac";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<MauSacCustomModel> listMS = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listMS.add(new MauSacCustomModel(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MauSac getOne(String ma) {
        String query = "Select * from MauSac Where Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            List<MauSac> listMS = new ArrayList<>();
            while (rs.next()) {
                listMS.add(new MauSac(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return (MauSac) listMS;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String add(MauSac ms) {
        String query = "INSERT INTO [dbo].[MauSac]\n"
                + "           (\n"
                + "            [Ma]\n"
                + "           ,[Ten]\n"
                + "           )\n"
                + "     VALUES\n"
                + "           (?,?)";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, ms.getMa());
            ps.setObject(2, ms.getTen());

            if (ps.executeUpdate() > 0) {
                return "Thêm thành công";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(MauSac ms, String ma) {
        String query = "UPDATE [dbo].[MauSac]\n"
                + "   SET \n"
                + "      [Ten] = ?\n"
                + "      \n"
                + " WHERE Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(2, ma);
            ps.setObject(1, ms.getTen());

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
        String query = "Delete from MauSac where ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);

            if (ps.executeUpdate() > 0) {
                return "Xóa thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Xóa thất bại, màu sắc đã được thêm vào sản phẩm chi tiết không thể xóa";
    }

    public static void main(String[] args) {
        new MauSacRepository().getAll().forEach((t) -> {
            System.out.println(t.toString());
        });
    }

}
