/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModel;

/**
 *
 * @author ADMIN
 */
public class HoaDonChiTietCustomModel {
    
    private String idHoaDon;
    private String idCTSanPham;
    private int soLuong;
    private Double donGia;

    public HoaDonChiTietCustomModel() {
    }

    public HoaDonChiTietCustomModel(String idHoaDon, String idCTSanPham, int soLuong, Double donGia) {
        this.idHoaDon = idHoaDon;
        this.idCTSanPham = idCTSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public HoaDonChiTietCustomModel(String idCTSanPham, int soLuong, Double donGia) {
        this.idCTSanPham = idCTSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public HoaDonChiTietCustomModel(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdCTSanPham() {
        return idCTSanPham;
    }

    public void setIdCTSanPham(String idCTSanPham) {
        this.idCTSanPham = idCTSanPham;
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
}
