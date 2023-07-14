/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModel;

/**
 *
 * @author admin
 */
public class SanPhamDoiTraThongKe {
    private String tenSanPham;
    private String maHoaDon;
    private String tenKhachHang;
    private String ngayDoi;
    private String soLuong;
    private String liDoDoi;
    private String ghiChu;

    public SanPhamDoiTraThongKe() {
    }

    public SanPhamDoiTraThongKe(String tenSanPham, String maHoaDon, String tenKhachHang, String ngayDoi, String soLuong, String liDoDoi, String ghiChu) {
        this.tenSanPham = tenSanPham;
        this.maHoaDon = maHoaDon;
        this.tenKhachHang = tenKhachHang;
        this.ngayDoi = ngayDoi;
        this.soLuong = soLuong;
        this.liDoDoi = liDoDoi;
        this.ghiChu = ghiChu;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getNgayDoi() {
        return ngayDoi;
    }

    public void setNgayDoi(String ngayDoi) {
        this.ngayDoi = ngayDoi;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public String getLiDoDoi() {
        return liDoDoi;
    }

    public void setLiDoDoi(String liDoDoi) {
        this.liDoDoi = liDoDoi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
    
}
