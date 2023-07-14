/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModel;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class VoucherCustomModel {

    private String id;
    private String ma;
    private String ten;
    private Double giamGia;
    private Date ngayBatDau;
    private Date ngayHetHan;
    private String ghiChu;
    private int trangThai;

    public VoucherCustomModel() {
    }

    public VoucherCustomModel(String id, String ma, String ten, Double giamGia, Date ngayBatDau, Date ngayHetHan, String ghiChu, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.giamGia = giamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public VoucherCustomModel(String ma, String ten, Double giamGia, Date ngayBatDau, Date ngayHetHan, String ghiChu, int trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.giamGia = giamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public VoucherCustomModel(Double giamGia) {
        this.giamGia = giamGia;
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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Double giamGia) {
        this.giamGia = giamGia;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String trangThaiVC() {
        if (getTrangThai() == 0) {
            return "Đang áp dụng";
        } else {
            return "Đã hết hạn";
        }
    }

    public String giamGiaTT() {
        if (getGiamGia() == 0.95) {
            return "5%";
        } else if (getGiamGia() == 0.9) {
            return "10%";
        } else if (getGiamGia() == 0.85) {
            return "15%";
        } else if (getGiamGia() == 0.8) {
            return "20%";
        } else if (getGiamGia() == 0.75) {
            return "25%";
        } else if (getGiamGia() == 0.7) {
            return "30%";
        } else if (getGiamGia() == 0.65) {
            return "35%";
        } else if (getGiamGia() == 0.6) {
            return "40%";
        } else if (getGiamGia() == 0.55) {
            return "45%";
        } else if (getGiamGia() == 0.5) {
            return "50%";
        } else {
            return "0%";
        }
    }

    @Override
    public String toString() {
        return "VoucherCustomModel{" + "ma=" + ma + ", ten=" + ten + ", giamGia=" + giamGia + ", ngayBatDau=" + ngayBatDau + ", ngayHetHan=" + ngayHetHan + ", ghiChu=" + ghiChu + ", trangThai=" + trangThai + '}';
    }

    public Object[] toRowData() {
        return new Object[]{ma, ten, giamGiaTT(), ngayBatDau, ngayHetHan, ghiChu, trangThaiVC()};
    }

    public Object[] toRowDataTrangThai() {
        return new Object[]{giamGiaTT()};
    }
}
