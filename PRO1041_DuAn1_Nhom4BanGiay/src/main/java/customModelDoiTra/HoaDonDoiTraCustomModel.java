/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModelDoiTra;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author ADMIN
 */
public class HoaDonDoiTraCustomModel {

    private String id;
    private String maHD;
    private String tenNV;
    private String idKH;
    private String tenKH;
    private String sdt;
    private Date ngayThanhToan;
    private String idCTSP;
    private int soLuong;
    private String liDoDoi;
    private String ghiChu;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public HoaDonDoiTraCustomModel() {
    }

    public HoaDonDoiTraCustomModel(String id, String maHD, String tenNV, String idKH, String tenKH, String sdt, Date ngayThanhToan) {
        this.id = id;
        this.maHD = maHD;
        this.tenNV = tenNV;
        this.idKH = idKH;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.ngayThanhToan = ngayThanhToan;
    }

    public HoaDonDoiTraCustomModel(String idCTSP, String id, String idKH, Date ngayThanhToan, int soLuong, String liDoDoi, String ghiChu) {
        this.idCTSP = idCTSP;
        this.id = id;
        this.idKH = idKH;
        this.ngayThanhToan = ngayThanhToan;
        this.soLuong = soLuong;
        this.liDoDoi = liDoDoi;
        this.ghiChu = ghiChu;
    }

    public HoaDonDoiTraCustomModel(String idCTSP, String maHD, String tenNV, String tenKH, String sdt, Date ngayThanhToan, int soLuong, String liDoDoi, String ghiChu) {
        this.idCTSP = idCTSP;
        this.maHD = maHD;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.ngayThanhToan = ngayThanhToan;
        this.soLuong = soLuong;
        this.liDoDoi = liDoDoi;
        this.ghiChu = ghiChu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(String idCTSP) {
        this.idCTSP = idCTSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String hanDoi() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(getNgayThanhToan());
        cal.add(Calendar.DATE, 3);
        return dateFormat.format(cal.getTime());

    }

    public String getLiDoDoi() {
        return liDoDoi;
    }

    public void setLiDoDoi(String liDoDoi) {
        this.liDoDoi = liDoDoi;
    }

    public Object[] toRowData() {
        return new Object[]{maHD, tenNV, tenKH, sdt, dateFormat.format(ngayThanhToan), hanDoi()};
    }

    public Object[] toRowDataDa() {
        return new Object[]{maHD, tenNV, tenKH, sdt, dateFormat.format(ngayThanhToan), soLuong, liDoDoi, ghiChu};
    }
}
