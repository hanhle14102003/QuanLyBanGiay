/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customModel;

/**
 *
 * @author ADMIN
 */
public class HoaDonCustomModel {

    private String maHD;
    private String idKH;
    private String idNV;

    public HoaDonCustomModel() {
    }

    public HoaDonCustomModel(String idKH, String idNV) {
        this.idKH = idKH;
        this.idNV = idNV;
    }

    public HoaDonCustomModel(String maHD) {
        this.maHD = maHD;
    }

    public String getIdKH() {
        return idKH;
    }

    public void setIdKH(String idKH) {
        this.idKH = idKH;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

}
