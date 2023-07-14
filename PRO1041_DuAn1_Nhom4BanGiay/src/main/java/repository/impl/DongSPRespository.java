/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.DongSanPhamCustomModel;

import domainModel.DongSP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import repository.IDongSPRes;
import utilities.DBContext;

public class DongSPRespository implements IDongSPRes<DongSP> {

    @Override
    public List<DongSP> getAll() {
        String query = "select * from DongSP";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<DongSP> listDs = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listDs.add(new DongSP(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listDs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<DongSanPhamCustomModel> getAllCustom() {
        String query = "select * from DongSP";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<DongSanPhamCustomModel> listDs = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listDs.add(new DongSanPhamCustomModel(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listDs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DongSP getOne(String ma) {
        String query = "Select * from DongSP Where Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            List<DongSP> listDs = new ArrayList<>();
            while (rs.next()) {
                listDs.add(new DongSP(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return (DongSP) listDs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String add(DongSP ds) {
        String query = "INSERT INTO [dbo].[DongSP]\n"
                + "           (\n"
                + "            [Ma]\n"
                + "           ,[Ten]\n"
                + "           )\n"
                + "     VALUES\n"
                + "           (?,?)";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, ds.getMa());
            ps.setObject(2, ds.getTen());

            if (ps.executeUpdate() > 0) {
                return "Thêm thành công";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(DongSP ds, String ma) {
        String query = "UPDATE [dbo].[DongSP]\n"
                + "   SET \n"
                + "      [Ten] = ?\n"
                + "      \n"
                + " WHERE Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(2, ma);
            ps.setObject(1, ds.getTen());

            if (ps.executeUpdate() > 0) {
                return "Sửa thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Sửa thất bại";
    }

    @Override
    public String delete(String ma) {
        String query = "Delete from DongSP where ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);

            if (ps.executeUpdate() > 0) {
                return "Xóa thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Xóa thất bại, dòng sản phẩm đã được thêm vào sản phẩm chi tiết không thể xóa";
    }

    public List<DongSanPhamCustomModel> search(String ma) {
        String sql = "SELECT Id, Ma, Ten\n"
                + "FROM     DongSP\n"
                + "where Ma like ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + ma + "%");
            ResultSet rs = ps.executeQuery();
            List<DongSanPhamCustomModel> listMS = new ArrayList<>();
            while (rs.next()) {
                listMS.add(new DongSanPhamCustomModel(rs.getString(1), rs.getString(2)));
            }
            return listMS;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static void main(String[] args) {
        new DongSPRespository().getAll().forEach((t) -> {
            System.out.println(t.toString());
        });
    }

}
