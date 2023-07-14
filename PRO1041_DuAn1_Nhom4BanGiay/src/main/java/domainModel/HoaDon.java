/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModel;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
public class HoaDon {

    private String id;
    private KhachHang kh;
    private NhanVien nv;
    private String ma;
    private Date ngayTao;
    private Date ngayThanhToan;
    private Double tongTien;
    private int tongSanPham;
    private int trangThai;

    public HoaDon() {
    }

    public HoaDon(String id, KhachHang kh, NhanVien nv, String ma, Date ngayTao, Date ngayThanhToan, Double tongTien, int tongSanPham, int trangThai) {
        this.id = id;
        this.kh = kh;
        this.nv = nv;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tongTien = tongTien;
        this.tongSanPham = tongSanPham;
        this.trangThai = trangThai;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public int getTongSanPham() {
        return tongSanPham;
    }

    public void setTongSanPham(int tongSanPham) {
        this.tongSanPham = tongSanPham;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String trangThai() {
        if (getTrangThai() == 0) {
            return "Đã hủy";
        } else if (getTrangThai() == 1) {
            return "Chờ thanh toán";
        } else {
            return "Đã thanh toán";
        }
    }

    public Object[] toRowDataHD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new Object[]{ma, dateFormat.format(ngayTao), nv.getHoTen(), kh.getHoTen(), trangThai()};
    }

}
