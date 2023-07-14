/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModel;

import customModelBanHang.HoaDonViewModel;

/**
 *
 * @author ADMIN
 */
public class HoaDonChiTiet {

    private String idHDCT;
    private HoaDonViewModel hd;
    private ChiTietSanPhamHiber ctsp;
    private int soLuong;
    private Double donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(HoaDonViewModel hd, ChiTietSanPhamHiber ctsp, int soLuong, Double donGia) {
        this.hd = hd;
        this.ctsp = ctsp;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

}
