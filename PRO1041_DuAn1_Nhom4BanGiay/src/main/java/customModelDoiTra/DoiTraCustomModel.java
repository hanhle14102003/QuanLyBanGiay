/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModelDoiTra;

/**
 *
 * @author ADMIN
 */
public class DoiTraCustomModel {

    private String maHD;
    private String tenKH;
    private String sanPhamDoi;
    private int soLuongDoi;

    public DoiTraCustomModel() {
    }

    public DoiTraCustomModel(String maHD, String tenKH, String sanPhamDoi, int soLuongDoi) {
        this.maHD = maHD;
        this.tenKH = tenKH;
        this.sanPhamDoi = sanPhamDoi;
        this.soLuongDoi = soLuongDoi;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSanPhamDoi() {
        return sanPhamDoi;
    }

    public void setSanPhamDoi(String sanPhamDoi) {
        this.sanPhamDoi = sanPhamDoi;
    }

    public int getSoLuongDoi() {
        return soLuongDoi;
    }

    public void setSoLuongDoi(int soLuongDoi) {
        this.soLuongDoi = soLuongDoi;
    }

    public Object[] toRowData() {
        return new Object[]{maHD, tenKH, sanPhamDoi, soLuongDoi};
    }

}
