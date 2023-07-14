/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModelDoiTra;

/**
 *
 * @author ADMIN
 */
public class HDCTDoiTraCustomModel {

    private String idCTSP;
    private String maSP;
    private String tenSP;
    private String dongSP;
    private String deGiay;
    private String mauSac;
    private String size;
    private int soLuong;

    public HDCTDoiTraCustomModel() {
    }

    public HDCTDoiTraCustomModel(int soLuong) {
        this.soLuong = soLuong;
    }

    public HDCTDoiTraCustomModel(String idCTSP, String maSP, String tenSP, String dongSP, String deGiay, String mauSac, String size, int soLuong) {
        this.idCTSP = idCTSP;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.dongSP = dongSP;
        this.deGiay = deGiay;
        this.mauSac = mauSac;
        this.size = size;
        this.soLuong = soLuong;
    }

    public HDCTDoiTraCustomModel(String idCTSP, String maSP, String tenSP, String dongSP, String deGiay, String mauSac, String size) {
        this.idCTSP = idCTSP;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.dongSP = dongSP;
        this.deGiay = deGiay;
        this.mauSac = mauSac;
        this.size = size;

    }

    public String getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(String idCTSP) {
        this.idCTSP = idCTSP;
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

    public String getDongSP() {
        return dongSP;
    }

    public void setDongSP(String dongSP) {
        this.dongSP = dongSP;
    }

    public String getDeGiay() {
        return deGiay;
    }

    public void setDeGiay(String deGiay) {
        this.deGiay = deGiay;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Object[] toRowData() {
        return new Object[]{maSP, tenSP, dongSP, deGiay, mauSac, size, soLuong};
    }

    public Object[] toRowDataDa() {
        return new Object[]{maSP, tenSP, dongSP, deGiay, mauSac, size};
    }

}
