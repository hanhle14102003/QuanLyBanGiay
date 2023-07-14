/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.SanPhamDoiTraThongKe;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Result;
import org.bridj.cpp.std.list;
import utilities.DBContext;
import repository.IDoiTraThongKeRes;

/**
 *
 * @author admin
 */
public class DoiTraThongKeRepository implements IDoiTraThongKeRes{

    @Override
    public List<SanPhamDoiTraThongKe> getAllBetWeen(String batDau, String ketThuc) {
        List<SanPhamDoiTraThongKe> list = new ArrayList<>();
        try {
             Connection conn = DBContext.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from doitra where dbo.doitra.ngayDoi betWeen"+batDau+"and"+ketThuc);
            while (rs.next()) {                
                list.add(new SanPhamDoiTraThongKe(rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            
            rs.close();
            st.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("Lỗi tại getAll");
        }
        return list;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public List<SanPhamDoiTraThongKe> getAllWhere( String input) {
        List<SanPhamDoiTraThongKe> list = new ArrayList<>();
        try {
             Connection conn = DBContext.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from doitra where dbo.doitra.ngayDoi like"+input);
            while (rs.next()) {                
                list.add(new SanPhamDoiTraThongKe(rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            
            rs.close();
            st.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("Lỗi tại getAll");
        }
        return list;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public static void main(String[] args) {
        DoiTraThongKeRepository a = new DoiTraThongKeRepository();
        System.out.println(a.getAllWhere("'2022-11-01'"));
    }

    @Override
    public List<SanPhamDoiTraThongKe> getAll() {
        List<SanPhamDoiTraThongKe> list = new ArrayList<>();
        try {
             Connection conn = DBContext.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from doitra ");
            while (rs.next()) {                
                list.add(new SanPhamDoiTraThongKe(rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            
            rs.close();
            st.close();
            conn.close();
            
        } catch (Exception e) {
            System.out.println("Lỗi tại getAll");
        }
        return list;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
