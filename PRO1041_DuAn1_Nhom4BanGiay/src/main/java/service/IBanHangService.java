/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.HoaDonChiTietCustomModel;
import customModel.HoaDonCustomModel;
import customModelBanHang.GioHangViewModel;
import customModelBanHang.HoaDonViewModel;
import customModelBanHang.SanPhamViewModel;
import domainModel.ChiTietSanPhamHiber;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface IBanHangService {

    List<SanPhamViewModel> getSanPhamVM();

    List<GioHangViewModel> getGioHang(String id);

    List<HoaDonViewModel> getHoaDon();

    String saveHoaDon(HoaDonViewModel hd);

    String addHDCT(HoaDonChiTietCustomModel hdct);

    String updateNVKH(HoaDonCustomModel hdUpdate, String ma);

    List<SanPhamViewModel> SearchSPBH(String input);

    String updateTrangThai(HoaDonViewModel hd, String ma);

    String updateSoLuong(ChiTietSanPhamHiber ctsp, String id);

    String deleteGioHang(String id);

    String capNhatSoLuong(ChiTietSanPhamHiber ctsp, String id);

    String capNhatSoLuong2(ChiTietSanPhamHiber ctsp, String id);

    String updateSoLuongHDCT(GioHangViewModel gh, String id);

    List<HoaDonCustomModel> getHoaDonFull();

    String deleteHDCT(String idHD);

    String deleteHD(String idHD);

    int laySoLuong(String idSL);
}
