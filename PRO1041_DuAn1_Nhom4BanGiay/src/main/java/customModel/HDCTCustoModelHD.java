/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModel;

/**
 *
 * @author ADMIN
 */
public class HDCTCustoModelHD {

    private String maSP;
    private String tenSP;
    private int soLuong;
    private Double donGia;
    private Double thanhTien;

    public HDCTCustoModelHD() {
    }

    public HDCTCustoModelHD(String maSP, String tenSP, int soLuong, Double donGia, Double thanhTien) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public Object[] toRowData() {
        return new Object[]{maSP, tenSP, soLuong, donGia, thanhTien};
    }

}
