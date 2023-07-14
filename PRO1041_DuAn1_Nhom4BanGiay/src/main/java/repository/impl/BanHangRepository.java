/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import customModel.HoaDonChiTietCustomModel;
import customModel.HoaDonCustomModel;
import customModel.VoucherCustomModel;
import customModelBanHang.GioHangViewModel;
import customModelBanHang.HoaDonViewModel;
import customModelBanHang.SanPhamViewModel;
import domainModel.ChiTietSanPhamHiber;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utilities.DBContext;

/**
 *
 * @author ADMIN
 */
public class BanHangRepository {

    public List<SanPhamViewModel> getSanPhamVM() {
        String sql = "SELECT dbo.ChiTietSP.Id, dbo.SanPham.Ma, dbo.SanPham.Ten, dbo.DongSP.Ten AS DSP, dbo.DeGiay.Ten AS DG, dbo.MauSac.Ten AS MS, dbo.ChiTietSP.DonGia, dbo.ChiTietSP.SoLuong, dbo.ChiTietSP.XuatXu, dbo.ChiTietSP.KichCo\n"
                + "FROM     dbo.ChiTietSP INNER JOIN\n"
                + "                  dbo.DeGiay ON dbo.ChiTietSP.IdDeGiay = dbo.DeGiay.Id INNER JOIN\n"
                + "                  dbo.DongSP ON dbo.ChiTietSP.IdDongSP = dbo.DongSP.Id INNER JOIN\n"
                + "                  dbo.MauSac ON dbo.ChiTietSP.IdMauSac = dbo.MauSac.Id INNER JOIN\n"
                + "                  dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.Id";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<SanPhamViewModel> listSPVM = new ArrayList<>();
            while (rs.next()) {
                listSPVM.add(new SanPhamViewModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getInt(8), rs.getString(9), rs.getString(10)));
            }
            return listSPVM;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<GioHangViewModel> getGioHang(String id) {
        String sql = " SELECT dbo.HoaDonChiTiet.Id, dbo.ChiTietSP.Id, dbo.SanPham.Ma, dbo.SanPham.Ten,dbo.HoaDonChiTiet.SoLuong, dbo.ChiTietSP.DonGia\n"
                + "                FROM     dbo.ChiTietSP INNER JOIN\n"
                + "                                dbo.HoaDonChiTiet ON dbo.ChiTietSP.Id = dbo.HoaDonChiTiet.IdChiTietSP inner join           \n"
                + "                                 dbo.SanPham ON dbo.ChiTietSP.IdSP = dbo.SanPham.Id\n"
                + "								  where IdHoaDon like ?";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            List<GioHangViewModel> listGHMD = new ArrayList<>();
            while (rs.next()) {
                listGHMD.add(new GioHangViewModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDouble(6)));
            }
            return listGHMD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<HoaDonViewModel> getHoaDon() {
        String sql = "SELECT dbo.HoaDon.Id, dbo.HoaDon.Ma, dbo.HoaDon.NgayTao, dbo.NhanVien.HoTen, dbo.KhachHang.HoTen AS Expr1, dbo.HoaDon.TrangThai, dbo.KhachHang.Sdt\n"
                + "FROM     dbo.HoaDon INNER JOIN\n"
                + "                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id Where dbo.HoaDon.TrangThai = 1 order by dbo.HoaDon.Ma desc";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonViewModel> listHD = new ArrayList<>();
            while (rs.next()) {
                listHD.add(new HoaDonViewModel(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
            }
            return listHD;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public List<HoaDonCustomModel> getHoaDonFull() {
        String sql = "SELECT Ma\n"
                + "FROM HoaDon ";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            List<HoaDonCustomModel> listHDFull = new ArrayList<>();
            while (rs.next()) {
                listHDFull.add(new HoaDonCustomModel(rs.getString(1)));
            }
            return listHDFull;
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    public String saveHoaDon(HoaDonViewModel hd) {
        String query = "Insert into HoaDon(IdKH, IdNV, Ma, NgayTao, TrangThai) values (?,?,?,?,?);";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, hd.getKh());
            ps.setObject(2, hd.getNv());
            ps.setObject(3, hd.getMa());
            ps.setObject(4, hd.getNgayTao());
            ps.setObject(5, hd.getTrangThai());

            if (ps.executeUpdate() > 0) {
                return "Thêm hóa đơn thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm hóa đơn thất bại";
    }

    public HoaDonViewModel getOneMaHD(String ma) {
        String query = "SELECT dbo.HoaDon.Id, dbo.HoaDon.Ma, dbo.HoaDon.NgayTao, dbo.NhanVien.HoTen, dbo.KhachHang.HoTen AS Expr1, dbo.HoaDon.TrangThai\n"
                + "FROM     dbo.HoaDon INNER JOIN\n"
                + "                  dbo.KhachHang ON dbo.HoaDon.IdKH = dbo.KhachHang.Id INNER JOIN\n"
                + "                  dbo.NhanVien ON dbo.HoaDon.IdNV = dbo.NhanVien.Id Where dbo.HoaDon.Ma = ? order by dbo.HoaDon.Ma asc";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            HoaDonViewModel hd = null;
            ps.setObject(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                hd = new HoaDonViewModel(rs.getString(1), rs.getString(1), rs.getDate(3), rs.getString(1), rs.getString(5), rs.getInt(6));
            }
            return hd;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public String addHDCT(HoaDonChiTietCustomModel hdct) {
        String query = "INSERT INTO [dbo].[HoaDonChiTiet]\n"
                + "           ([IdHoaDon]\n"
                + "           ,[IdChiTietSP]\n"
                + "           ,[SoLuong]\n"
                + "           ,[DonGia])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {

            ps.setObject(1, hdct.getIdHoaDon());
            ps.setObject(2, hdct.getIdCTSanPham());
            ps.setObject(3, hdct.getSoLuong());
            ps.setObject(4, hdct.getDonGia());
            if (ps.executeUpdate() > 0) {
                return "Thêm sản phẩm thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thêm sản phẩm thất bại";

    }

    public String updateNVKH(HoaDonCustomModel hdUpdate, String ma) {
        String query = "UPDATE [dbo].[HoaDon]\n"
                + "   SET \n"
                + "      [IdKH] = ?\n"
                + "      ,[IdNV] = ?\n"
                + "      \n"
                + " WHERE Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(3, ma);
            ps.setObject(1, hdUpdate.getIdKH());
            ps.setObject(2, hdUpdate.getIdNV());

            if (ps.executeUpdate() > 0) {
                return "Thay đổi thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thay đổi thất bại";
    }

    public String updateTrangThai(HoaDonViewModel hd, String ma) {
        String query = "UPDATE [dbo].[HoaDon]\n"
                + "   SET \n"
                + "      [IdVoucher] = ?\n"
                + "      ,[NgayThanhToan] = ?\n"
                + "      ,[TongTien] = ?\n"
                + "      ,[TongSanPham] = ?\n"
                + "      ,[TrangThai] = ?\n"
                + " WHERE Ma = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, hd.getIdVoucher());
            ps.setObject(2, hd.getNgayThanhToan());
            ps.setObject(3, hd.getTongTien());
            ps.setObject(4, hd.getTongSanPham());
            ps.setObject(5, hd.getTrangThai());
            ps.setObject(6, ma);

            if (ps.executeUpdate() > 0) {
                return "Thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Thất bại";
    }

    public String updateSoLuong(ChiTietSanPhamHiber ctsp, String id) {
        String query = "UPDATE [dbo].[ChiTietSP]\n"
                + "   SET \n"
                + "      [SoLuong] = ?\n"
                + " WHERE ID = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ctsp.getSoLuong());
            ps.setObject(2, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public String deleteGioHang(String id) {
        String query = "Delete from HoaDonChiTiet where Id = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, id);

            if (ps.executeUpdate() > 0) {
                return "Xóa sản phẩm thành công";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Để xác nhận vui lòng chọn lại sản phẩm cần xóa";
    }

    public String capNhatSoLuong(ChiTietSanPhamHiber ctsp, String id) {
        String query = "Update ChiTietSP Set SoLuong = SoLuong + ? where Id = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ctsp.getSoLuong());
            ps.setObject(2, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public String capNhatSoLuong2(ChiTietSanPhamHiber ctsp, String id) {
        String query = "Update ChiTietSP Set SoLuong = SoLuong - ? where Id = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ctsp.getSoLuong());
            ps.setObject(2, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public String updateSoLuongHDCT(GioHangViewModel gh, String id) {
        String query = "UPDATE [dbo].[HoaDOnChiTiet]\n"
                + "   SET \n"
                + "      [SoLuong] = ?\n"
                + " WHERE Id = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, gh.getSoLuong());
            ps.setObject(2, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public String deleteHDCT(String idHD) {
        String query = "Delete from HoaDonChiTiet where IdHoaDon = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, idHD);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Ôi hỏng";
    }

    public String deleteHD(String idHD) {
        String query = "Delete from HoaDon where Id = ?";
        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, idHD);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Ôi hỏng";
    }

    public int laySoLuong(String idSL) {
        String sql = "  select SoLuong from ChiTietSP where id like ?";

        try ( Connection con = DBContext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSL);
            int sl = 0;
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sl = rs.getInt(1);
            }
            return sl;
        } catch (Exception e) {
            e.getMessage();
        }
        return 0;

    }

    public static void main(String[] args) {
        new BanHangRepository().getHoaDonFull().forEach((t) -> {
            System.out.println(t.toString());
        });
    }
}
