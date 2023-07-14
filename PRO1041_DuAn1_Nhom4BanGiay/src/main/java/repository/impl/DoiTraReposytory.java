/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModelDoiTra.HDCTDoiTraCustomModel;
import customModelDoiTra.HoaDonDoiTraCustomModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import lombok.ToString;
import utilities.DBContext;

/**
 *
 * @author ADMIN
 */
public class DoiTraReposytory {

    public List<HoaDonDoiTraCustomModel> getHoaDonDoiTra() {
        String sql = "SELECT dbo.HoaDon.Id, dbo.HoaDon.Ma, dbo.NhanVien.HoTen, dbo.HoaDon.IdKH, dbo.KhachHang.HoTen AS Expr1, dbo.KhachHang.Sdt, dbo.HoaDon.NgayThanhToan\n"
                + "FROM     dbo.HoaDon INNER JOIN\n"
                + "                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id Where TrangThai = 3 order by dbo.HoaDon.Ma desc";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonDoiTraCustomModel> listHD = new ArrayList<>();
            while (rs.next()) {
                listHD.add(new HoaDonDoiTraCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7)));
            }
            return listHD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<HDCTDoiTraCustomModel> getHDCTDoiTra(String id) {
        String sql = "SELECT dbo.ChiTietSP.Id, dbo.SanPham.Ma, dbo.SanPham.Ten, dbo.DongSP.Ten AS Expr1, dbo.DeGiay.Ten AS Expr2, dbo.MauSac.Ten AS Expr3, dbo.ChiTietSP.KichCo, dbo.HoaDonChiTiet.SoLuong\n"
                + "FROM     dbo.ChiTietSP INNER JOIN\n"
                + "                  dbo.DeGiay ON dbo.ChiTietSP.IdDeGiay = dbo.DeGiay.Id INNER JOIN\n"
                + "                  dbo.DongSP ON dbo.ChiTietSP.IdDongSP = dbo.DongSP.Id INNER JOIN\n"
                + "                  dbo.HoaDonChiTiet ON dbo.ChiTietSP.Id = dbo.HoaDonChiTiet.IdChiTietSP INNER JOIN\n"
                + "                  dbo.MauSac ON dbo.ChiTietSP.IdMauSac = dbo.MauSac.Id INNER JOIN\n"
                + "                  dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.Id where dbo.HoaDonChiTiet.IdHoaDon like ?";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            List<HDCTDoiTraCustomModel> listHDCTDT = new ArrayList<>();
            while (rs.next()) {
                listHDCTDT.add(new HDCTDoiTraCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8)));
            }
            return listHDCTDT;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public String doiTra(HoaDonDoiTraCustomModel hd) {
        String query = "INSERT INTO [dbo].[DoiTra]\n"
                + "           (\n"
                + "           [IdCTSP]\n"
                + "           ,[IdHD]\n"
                + "           ,[IdKH]\n"
                + "           ,[NgayDoi]\n"
                + "           ,[SoLuong]\n"
                + "           ,[LiDoDoi]\n"
                + "           ,[GhiChu])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?);";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, hd.getIdCTSP());
            ps.setObject(2, hd.getId());
            ps.setObject(3, hd.getIdKH());
            ps.setObject(4, hd.getNgayThanhToan());
            ps.setObject(5, hd.getSoLuong());
            ps.setObject(6, hd.getLiDoDoi());
            ps.setObject(7, hd.getGhiChu());

            if (ps.executeUpdate() > 0) {
                return "Đổi thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Đổi thất bại";
    }

    public String capNhatSoLuong(HDCTDoiTraCustomModel hdct, String id) {
        String query = "Update ChiTietSP Set SoLuong = SoLuong - ? where Id = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, hdct.getSoLuong());
            ps.setObject(2, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<HoaDonDoiTraCustomModel> getHoaDonDaDoiTra() {
        String sql = "SELECT  dbo.DoiTra.IdCTSP, dbo.HoaDon.Ma, dbo.NhanVien.HoTen, dbo.KhachHang.HoTen AS Expr1, dbo.KhachHang.Sdt, dbo.DoiTra.NgayDoi, dbo.DoiTra.SoLuong, dbo.DoiTra.LiDoDoi, dbo.DoiTra.GhiChu\n"
                + "FROM     dbo.DoiTra INNER JOIN\n"
                + "                  dbo.HoaDon ON dbo.DoiTra.IdHD = dbo.HoaDon.Id INNER JOIN\n"
                + "                  dbo.KhachHang ON dbo.DoiTra.IdKH = dbo.KhachHang.Id AND dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonDoiTraCustomModel> listHD = new ArrayList<>();
            while (rs.next()) {
                listHD.add(new HoaDonDoiTraCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getString(8), rs.getString(9)));
            }
            return listHD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<HDCTDoiTraCustomModel> getHDCTDaDoiTra(String id) {
        String sql = "SELECT dbo.ChiTietSP.Id, dbo.SanPham.Ma, dbo.SanPham.Ten, dbo.DongSP.Ten AS Expr1, dbo.DeGiay.Ten AS Expr2, dbo.MauSac.Ten AS Expr3, dbo.ChiTietSP.KichCo\n"
                + "FROM     dbo.ChiTietSP INNER JOIN\n"
                + "                  dbo.DeGiay ON dbo.ChiTietSP.IdDeGiay = dbo.DeGiay.Id INNER JOIN\n"
                + "                  dbo.DongSP ON dbo.ChiTietSP.IdDongSP = dbo.DongSP.Id INNER JOIN\n"
                + "                  dbo.MauSac ON dbo.ChiTietSP.IdMauSac = dbo.MauSac.Id INNER JOIN\n"
                + "                  dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.Id where dbo.ChiTietSP.Id like ?";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            List<HDCTDoiTraCustomModel> listHDCTDT = new ArrayList<>();
            while (rs.next()) {
                listHDCTDT.add(new HDCTDoiTraCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            return listHDCTDT;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<HoaDonDoiTraCustomModel> getHoaDonDaDoiTraBetween(String batDau, String ketThuc) {
        String sql = "SELECT  dbo.DoiTra.IdCTSP, dbo.HoaDon.Ma, dbo.NhanVien.HoTen, dbo.KhachHang.HoTen AS Expr1, dbo.KhachHang.Sdt, dbo.DoiTra.NgayDoi, dbo.DoiTra.SoLuong, dbo.DoiTra.LiDoDoi, dbo.DoiTra.GhiChu\n"
                + "                FROM     dbo.DoiTra INNER JOIN\n"
                + "                                  dbo.HoaDon ON dbo.DoiTra.IdHD = dbo.HoaDon.Id INNER JOIN\n"
                + "                                  dbo.KhachHang ON dbo.DoiTra.IdKH = dbo.KhachHang.Id AND dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id  where NgayDoi Between ? and ?";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, batDau);
            ps.setObject(2, ketThuc);
            ResultSet rs = ps.executeQuery();
            List<HoaDonDoiTraCustomModel> listHD = new ArrayList<>();
            while (rs.next()) {
                listHD.add(new HoaDonDoiTraCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getString(8), rs.getString(9)));
            }
            return listHD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<HoaDonDoiTraCustomModel> getHoaDonDoiTraBetween(String batDau, String ketThuc) {
        String sql = "				  SELECT dbo.HoaDon.Id, dbo.HoaDon.Ma, dbo.NhanVien.HoTen, dbo.HoaDon.IdKH, dbo.KhachHang.HoTen AS Expr1, dbo.KhachHang.Sdt, dbo.HoaDon.NgayThanhToan\n"
                + "                FROM     dbo.HoaDon INNER JOIN\n"
                + "                                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id Where ((TrangThai = 3) and (NgayThanhToan Between ? and ?))\n"
                + "								   order by dbo.HoaDon.Ma desc";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, batDau);
            ps.setObject(2, ketThuc);
            ResultSet rs = ps.executeQuery();
            List<HoaDonDoiTraCustomModel> listHD = new ArrayList<>();
            while (rs.next()) {
                listHD.add(new HoaDonDoiTraCustomModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDate(7)));
            }
            return listHD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public static void main(String[] args) {
        new DoiTraReposytory().getHoaDonDaDoiTraBetween("2022/12/05", "2022/12/06").forEach((t) -> {
            System.out.println(t.toString());
        });
    }

}
