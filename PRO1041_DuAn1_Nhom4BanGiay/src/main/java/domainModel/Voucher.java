/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModel;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class Voucher {

    private String id;
    private String ma;
    private String ten;
    private Double giamGia;
    private Date ngayBatDau;
    private Date ngayHetHan;
    private String ghiChu;
    private int trangThai;

    public Voucher() {
    }

    public Voucher(String id, String ma, String ten, Double giamGia, Date ngayBatDau, Date ngayHetHan, String ghiChu, int trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.giamGia = giamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public Voucher(String ma, String ten, Double giamGia, Date ngayBatDau, Date ngayHetHan, String ghiChu, int trangThai) {
        this.ma = ma;
        this.ten = ten;
        this.giamGia = giamGia;
        this.ngayBatDau = ngayBatDau;
        this.ngayHetHan = ngayHetHan;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
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

    @Override
    public String toString() {
        return "Voucher{" + "ma=" + ma + ", ten=" + ten + ", giamGia=" + giamGia + ", ngayBatDau=" + ngayBatDau + ", ngayHetHan=" + ngayHetHan + ", ghiChu=" + ghiChu + ", trangThai=" + trangThai + '}';
    }

    public Object[] toRowData() {
        return new Object[]{ma, ten, giamGia, ngayBatDau, ngayHetHan, ghiChu, trangThai};
    }

}
