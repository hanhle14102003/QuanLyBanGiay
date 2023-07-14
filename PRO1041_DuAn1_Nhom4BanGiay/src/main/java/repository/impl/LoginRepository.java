/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainModel.Loginn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utilities.DBContext;

/**
 *
 * @author ADMIN
 */
public class LoginRepository {

    public Loginn login(String user, String pass) {
        String query = "SELECT [TaiKhoan]\n"
                + "      ,[MatKhau]\n"
                + "      ,[Ma]\n"
                + "      ,[HoTen]\n"
                + "  FROM [dbo].[NhanVien]\n"
                + "  where TaiKhoan like ? and MatKhau like ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query);) {
            ps.setObject(1, user);
            ps.setObject(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Loginn(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        Loginn tk = new LoginRepository().login("quanly01", "ql01");
        System.out.println(tk);
    }
}
