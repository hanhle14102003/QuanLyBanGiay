/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModelBanHang;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class GioHangViewModel {

    private String id;
    private String idCtsp;
    private String maSP;
    private String tenSP;
    private int soLuong;
    private Double donGia;

    public GioHangViewModel() {
    }

    public GioHangViewModel(String id, String idCtsp, String maSP, String tenSP, int soLuong, Double donGia) {
        this.id = id;
        this.idCtsp = idCtsp;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public GioHangViewModel(String idCtsp, String maSP, String tenSP, int soLuong, Double donGia) {
        this.idCtsp = idCtsp;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public GioHangViewModel(String maSP, String tenSP, int soLuong, Double donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public String getIdCtsp() {
        return idCtsp;
    }

    public void setIdCtsp(String idCtsp) {
        this.idCtsp = idCtsp;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public BigDecimal tongTien(int soLuong, BigDecimal donGia) {
//        BigDecimal tongTien1 = BigDecimal.ZERO;
//        BigDecimal tongTien2 = BigDecimal.ZERO;
//        tongTien1 = donGia.multiply(BigDecimal.valueOf(soLuong));
//        tongTien2 = tongTien2.add(tongTien1);
//        return tongTien2;
//    }
    @Override
    public String toString() {
        return "GioHangModel{" + "idCtsp=" + idCtsp + ", maSP=" + maSP + ", tenSP=" + tenSP + ", soLuong=" + soLuong + ", donGia=" + donGia + '}';
    }

//    public Object[] todataRow(int i) {
//        return new Object[]{i, maSP, tenSP, soLuong, donGia, tongTien(soLuong, donGia)};
//    }
    public Object[] todataRow() {
        return new Object[]{maSP, tenSP, soLuong, String.format("%.2f", donGia)};
    }
}
