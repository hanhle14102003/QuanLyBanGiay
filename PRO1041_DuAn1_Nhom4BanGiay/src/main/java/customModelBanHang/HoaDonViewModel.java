/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModelBanHang;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author ADMIN
 */
public class HoaDonViewModel {

    private String id;
    private String ma;
    private Date ngayTao;
    private String nv;
    private String kh;
    private int trangThai;
    private Date ngayThanhToan;
    private Double tongTien;
    private int tongSanPham;
    private String idVoucher;
    private String sdt;

    public HoaDonViewModel() {
    }

    public HoaDonViewModel(String id, String ma, Date ngayTao, String nv, String kh, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.nv = nv;
        this.kh = kh;
        this.trangThai = trangThai;
    }

    public HoaDonViewModel(String id, String ma, Date ngayTao, String nv, String kh, int trangThai, String sdt) {
        this.id = id;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.nv = nv;
        this.kh = kh;
        this.trangThai = trangThai;
        this.sdt = sdt;
    }

    public HoaDonViewModel(String id, String ma, Date ngayTao, String nv, String kh, int trangThai, Date ngayThanhToan, Double tongTien, int tongSanPham) {
        this.id = id;
        this.ma = ma;
        this.ngayTao = ngayTao;
        this.nv = nv;
        this.kh = kh;
        this.trangThai = trangThai;
        this.ngayThanhToan = ngayThanhToan;
        this.tongTien = tongTien;
        this.tongSanPham = tongSanPham;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNv() {
        return nv;
    }

    public void setNv(String nv) {
        this.nv = nv;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(String idVoucher) {
        this.idVoucher = idVoucher;
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

    @Override
    public String toString() {
        return "HoaDonViewModel{" + "id=" + id + ", ma=" + ma + ", ngayTao=" + ngayTao + ", nv=" + nv + ", kh=" + kh + ", trangThai=" + trangThai + '}';
    }

    public Object[] toRowDataHD() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new Object[]{ma, dateFormat.format(ngayTao), nv, kh, trangThai()};
    }

}
