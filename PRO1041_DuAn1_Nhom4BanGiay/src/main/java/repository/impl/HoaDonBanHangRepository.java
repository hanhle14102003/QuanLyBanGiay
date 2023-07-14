/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.HDCTCustoModelHD;
import customModel.HoaDonCustomModel;
import customModel.HoaDonCustomModelHD;
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
public class HoaDonBanHangRepository {

    public List<HDCTCustoModelHD> getHDCT(String id) {
        String sql = "				  SELECT dbo.SanPham.Ma, dbo.SanPham.Ten, dbo.HoaDonChiTiet.SoLuong, dbo.HoaDonChiTiet.DonGia, dbo.HoaDonChiTiet.SoLuong * dbo.HoaDonChiTiet.DonGia as thanhTien\n"
                + "FROM     dbo.ChiTietSP INNER JOIN\n"
                + "                  dbo.HoaDonChiTiet ON dbo.ChiTietSP.Id = dbo.HoaDonChiTiet.IdChiTietSP INNER JOIN\n"
                + "                  dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.Id where dbo.HoaDonChiTiet.IdHoaDon like ?";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            List<HDCTCustoModelHD> listHDCT = new ArrayList<>();
            while (rs.next()) {
                listHDCT.add(new HDCTCustoModelHD(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getDouble(5)));
            }
            return listHDCT;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<HoaDonCustomModelHD> getHoaDon() {
        String sql = "SELECT dbo.HoaDon.Id, dbo.HoaDon.Ma, dbo.NhanVien.Ma AS Expr1, dbo.NhanVien.HoTen, dbo.KhachHang.Ma AS Expr2, dbo.KhachHang.HoTen AS Expr3, dbo.HoaDon.TongTien, dbo.HoaDon.TongSanPham,\n"
                + "                  dbo.HoaDon.TrangThai\n"
                + "FROM     dbo.HoaDon INNER JOIN\n"
                + "                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonCustomModelHD> listHD = new ArrayList<>();
            while (rs.next()) {
                listHD.add(new HoaDonCustomModelHD(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getInt(9)));
            }
            return listHD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<HoaDonCustomModelHD> getHoaDonBetween(String batDau, String ketThuc) {
        String sql = "SELECT dbo.HoaDon.Id, dbo.HoaDon.Ma, dbo.NhanVien.Ma AS Expr1, dbo.NhanVien.HoTen, dbo.KhachHang.Ma AS Expr2, dbo.KhachHang.HoTen AS Expr3, dbo.HoaDon.TongTien, dbo.HoaDon.TongSanPham,\n"
                + "                                  dbo.HoaDon.TrangThai\n"
                + "                FROM     dbo.HoaDon INNER JOIN\n"
                + "                                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id Where NgayThanhToan Between ? and ?";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, batDau);
            ps.setObject(2, ketThuc);
            ResultSet rs = ps.executeQuery();
            List<HoaDonCustomModelHD> listHD = new ArrayList<>();
            while (rs.next()) {
                listHD.add(new HoaDonCustomModelHD(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getInt(9)));
            }
            return listHD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

}
