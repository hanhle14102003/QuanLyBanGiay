/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import repository.IInterface;
import customModel.NhaCungCapCM;
import domainModel.NhaCungCap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import repository.IInterface;
import utilities.DBContext;
/**
 *
 * @author khuong duc
 */
public class NhaCungCapRepository implements IInterface<NhaCungCap>{

    @Override
    public List<NhaCungCap> getAll() {
        String query = "Select * from NhaCungCap";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<NhaCungCap> listNCC = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listNCC.add(new NhaCungCap(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listNCC;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NhaCungCapCM> getAllCustom() {
        String query = "Select * from NhaCungCap";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            List<NhaCungCapCM> listNCC = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listNCC.add(new NhaCungCapCM(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listNCC;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NhaCungCap getOne(String ma) {
        String query = "Select * from NhaCungCap Where Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            List<NhaCungCap> listNCC = new ArrayList<>();
            while (rs.next()) {
                listNCC.add(new NhaCungCap(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return (NhaCungCap) listNCC;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String add(NhaCungCap ncc) {
        String query = "INSERT INTO [dbo].[NhaCungCap]\n"
                + "           (\n"
                + "            [Ma]\n"
                + "           ,[Ten]\n"               
                + "           )\n"
                + "     VALUES\n"
                + "           (?,?)";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, ncc.getMa());
            ps.setObject(2, ncc.getHoTen());


            if (ps.executeUpdate() > 0) {
                return "Thêm thành công";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm thất bại";
    }

    @Override
    public String update(NhaCungCap ncc, String ma) {
        String query = "UPDATE [dbo].[NhaCungCap]\n"
                + "   SET \n"
                + "      [Ten] = ?\n"
                + "      \n"
                + " WHERE Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(3, ma);
            ps.setObject(1, ncc.getHoTen());

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
        String query = "Delete from NhaCungCap where ma = ?";
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

    
    public List<NhaCungCapCM> search(String ten) {
        String sql = "SELECT Id, Ma, Ten\n"
                + "FROM     NhaCungCap\n"
                + "where Ten like ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            List<NhaCungCapCM> listNCC = new ArrayList<>();
            while (rs.next()) {
                listNCC.add(new NhaCungCapCM(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listNCC;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    public static void main(String[] args) {
        new KhachHangRepository().getAll().forEach((t) -> {
            System.out.println(t.toString());
        });
    }
    
}
