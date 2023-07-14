/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModel;

/**
 *
 * @author ADMIN
 */
public class HoaDonCustomModelHDThongKe {

    private String id;
    private String maHD;
    private String maNV;
    private String tenNV;
    private String maKH;
    private String tenKH;
    private String ngayThanhToan;
    private Double tongTien;
    private int tongSP;
     private int tuoiKH;
    private int trangThai;
   

    public HoaDonCustomModelHDThongKe() {
    }

    public HoaDonCustomModelHDThongKe(String id, String maHD, String maNV, String tenNV, String maKH, String tenKH, String ngayThanhToan, Double tongTien, int tongSP, int tuoiKH, int trangThai) {
        this.id = id;
        this.maHD = maHD;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.ngayThanhToan = ngayThanhToan;
        this.tongTien = tongTien;
        this.tongSP = tongSP;
        this.tuoiKH = tuoiKH;
        this.trangThai = trangThai;
    }

    

    public int getTuoiKH() {
        return tuoiKH;
    }

    public void setTuoiKH(int tuoiKH) {
        this.tuoiKH = tuoiKH;
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

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public int getTongSP() {
        return tongSP;
    }

    public void setTongSP(int tongSP) {
        this.tongSP = tongSP;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

   

    

    public String trangThaiHD() {
        if (getTrangThai() == 3) {
            return "Đã thanh toán";
        } else {
            return "Chờ thanh toán";
        }
    }

    @Override
    public String toString() {
        return "HoaDonCustomModelHDThongKe{" + "id=" + id + ", maHD=" + maHD + ", maNV=" + maNV + ", tenNV=" + tenNV + ", maKH=" + maKH + ", tenKH=" + tenKH + ", ngayThanhToan=" + ngayThanhToan + ", tongTien=" + tongTien + ", tongSP=" + tongSP + ", trangThai=" + trangThai + ", tuoiKH=" + tuoiKH + '}';
    }

    



    public Object[] toRowData() {
        return new Object[]{maHD, maNV, tenNV, maKH, tenKH, tongTien, tongSP, trangThaiHD()};
    }
}
