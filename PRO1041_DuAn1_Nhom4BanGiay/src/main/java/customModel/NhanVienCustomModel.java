/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModel;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class NhanVienCustomModel {

    private String id;
    private String tenCV;
    private String ma;
    private String hoTen;
    private String taiKhoan;
    private String matKhau;
    private String sdt;
    private String email;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;

    public NhanVienCustomModel(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public NhanVienCustomModel() {
    }

    public NhanVienCustomModel(String id, String tenCV, String ma, String hoTen, String taiKhoan, String matKhau, String sdt, String email, String gioiTinh, Date ngaySinh, String diaChi) {
        this.id = id;
        this.tenCV = tenCV;
        this.ma = ma;
        this.hoTen = hoTen;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public Object[] toRowData(){
        return new Object[]{tenCV, ma, hoTen, taiKhoan, matKhau, sdt, email, gioiTinh, ngaySinh, diaChi};
    }
}
