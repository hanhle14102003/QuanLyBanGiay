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
public class SanPhamViewModel {

    private String id;
    private String maSP;
    private String tenSP;
    private String dongSP;
    private String deGiay;
    private String mauSac;
    private Double donGia;
    private int soLuong;
    private String xuatXu;
    private String kichCo;

    public SanPhamViewModel() {
    }

    public SanPhamViewModel(String id, String maSP, String tenSP, String dongSP, String deGiay, String mauSac, Double donGia, int soLuong, String xuatXu, String kichCo) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.dongSP = dongSP;
        this.deGiay = deGiay;
        this.mauSac = mauSac;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.xuatXu = xuatXu;
        this.kichCo = kichCo;
    }

    public SanPhamViewModel(String maSP, String tenSP, Double donGia, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    public SanPhamViewModel(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    @Override
    public String toString() {
        return "SanPhamViewModel{" + "id=" + id + ", maSP=" + maSP + ", tenSP=" + tenSP + ", dongSP=" + dongSP + ", deGiay=" + deGiay + ", mauSac=" + mauSac + ", donGia=" + donGia + ", soLuong=" + soLuong + ", xuatXu=" + xuatXu + ", kichCo=" + kichCo + '}';
    }

    public Object[] todataRowSanPham() {
        return new Object[]{maSP, tenSP, dongSP, deGiay, mauSac, donGia, soLuong, xuatXu, kichCo};
    }

}
