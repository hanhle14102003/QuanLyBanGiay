/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.HDCTCustoModelHD;
import customModel.HDCTCustoModelHDThongKe;
import customModel.HoaDonCustomModel;
import customModel.HoaDonCustomModelHD;
import customModel.HoaDonCustomModelHDThongKe;
import customModelBanHang.GioHangViewModel;
import customModelBanHang.HoaDonViewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utilities.DBContext;

/**
 *
 * @author ADMIN
 */
public class HoaDonBanHangRepositoryThongKe {

    public List<HDCTCustoModelHDThongKe> getHDCT(String id) {
        String sql = "				  SELECT dbo.SanPham.Ma, dbo.SanPham.Ten, dbo.HoaDonChiTiet.SoLuong, dbo.HoaDonChiTiet.DonGia, dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia as thanhTien\n"
                + "FROM     dbo.ChiTietSP INNER JOIN\n"
                + "                  dbo.HoaDonChiTiet ON dbo.ChiTietSP.Id = dbo.HoaDonChiTiet.IdChiTietSP INNER JOIN\n"
                + "                  dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.Id where dbo.HoaDonChiTiet.IdHoaDon like ?";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            List<HDCTCustoModelHDThongKe> listHDCT = new ArrayList<>();
            while (rs.next()) {
                listHDCT.add(new HDCTCustoModelHDThongKe(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5)));
            }
            return listHDCT;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    
    public List<HDCTCustoModelHDThongKe> getALLHDCT() {
        String sql = "				  SELECT dbo.SanPham.Ma, dbo.SanPham.Ten, dbo.HoaDonChiTiet.SoLuong, dbo.HoaDonChiTiet.DonGia, dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia as thanhTien\n"
                + "FROM     dbo.ChiTietSP INNER JOIN\n"
                + "                  dbo.HoaDonChiTiet ON dbo.ChiTietSP.Id = dbo.HoaDonChiTiet.IdChiTietSP INNER JOIN\n"
                + "                  dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.Id ";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HDCTCustoModelHDThongKe> listHDCT = new ArrayList<>();
            while (rs.next()) {
                listHDCT.add(new HDCTCustoModelHDThongKe(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5)));
            }
            return listHDCT;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    


    public List<HoaDonCustomModelHDThongKe> getAllHoaDon() {
        String sql = "SELECT dbo.HoaDon.Id, dbo.HoaDon.Ma, dbo.NhanVien.Ma AS Expr1, dbo.NhanVien.HoTen, dbo.KhachHang.Ma AS Expr2, dbo.KhachHang.HoTen AS Expr3, dbo.HoaDon.NgayThanhToan, dbo.HoaDon.TongTien, dbo.HoaDon.TongSanPham,CONVERT(int,ROUND(DATEDIFF(hour,dbo.KhachHang.NgaySinh,GETDATE())/8766.0,0)) AS tuoi,\n"
                + "                  dbo.HoaDon.TrangThai\n"
                + "FROM     dbo.HoaDon INNER JOIN\n"
                + "                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonCustomModelHDThongKe> listHD = new ArrayList<>();
            while (rs.next()) {
                listHD.add(new HoaDonCustomModelHDThongKe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getDouble(8), rs.getInt(9), rs.getInt(10),rs.getInt(11)));
            }
            return listHD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    
    public List<HoaDonCustomModelHDThongKe> getHoaDonBetWeen(String batDau, String ketThuc) {
         String sql = "SELECT dbo.HoaDon.Id, dbo.HoaDon.Ma, dbo.NhanVien.Ma AS Expr1, dbo.NhanVien.HoTen, dbo.KhachHang.Ma AS Expr2, dbo.KhachHang.HoTen AS Expr3, dbo.HoaDon.NgayThanhToan, dbo.HoaDon.TongTien, dbo.HoaDon.TongSanPham,CONVERT(int,ROUND(DATEDIFF(hour, dbo.KhachHang.NgaySinh,GETDATE())/8766.0,0)) AS tuoi,\n"
                + "                  dbo.HoaDon.TrangThai\n"
                + "FROM     dbo.HoaDon INNER JOIN\n"
                + "                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id"
                + "                  where dbo.HoaDon.NgayThanhToan BetWeen"+batDau+"and"+ketThuc+" order by NgayThanhToan asc";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonCustomModelHDThongKe> listHD = new ArrayList<>();
            while (rs.next()) {
                listHD.add(new HoaDonCustomModelHDThongKe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getInt(9), rs.getInt(10),rs.getInt(11)));
            }
            return listHD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    
    public List<HoaDonCustomModelHDThongKe> getHoaDonByNgay(String input) {
         String sql = "SELECT dbo.HoaDon.Id, dbo.HoaDon.Ma, dbo.NhanVien.Ma AS Expr1, dbo.NhanVien.HoTen, dbo.KhachHang.Ma AS Expr2, dbo.KhachHang.HoTen AS Expr3, dbo.HoaDon.NgayThanhToan, dbo.HoaDon.TongTien, dbo.HoaDon.TongSanPham,CONVERT(int,ROUND(DATEDIFF(hour, dbo.KhachHang.NgaySinh,GETDATE())/8766.0,0)) AS tuoi,\n"
                + "                  dbo.HoaDon.TrangThai\n"
                + "FROM     dbo.HoaDon INNER JOIN\n"
                + "                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id"
                + "                  where dbo.HoaDon.NgayThanhToan like"+input;

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonCustomModelHDThongKe> listHD = new ArrayList<>();
            while (rs.next()) {
                listHD.add(new HoaDonCustomModelHDThongKe(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getInt(9), rs.getInt(10),rs.getInt(11)));
            }
            return listHD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
    
//        public static void main(String[] args) {
//        HoaDonBanHangRepositoryThongKe a = new HoaDonBanHangRepositoryThongKe();
//        System.out.println(a.getAllHoaDon().get(0).getTuoiKH());
//    }

}
