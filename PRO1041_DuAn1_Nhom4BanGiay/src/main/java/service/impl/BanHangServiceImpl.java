/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.HoaDonChiTietCustomModel;
import customModel.HoaDonCustomModel;
import customModelBanHang.GioHangViewModel;
import customModelBanHang.HoaDonViewModel;
import customModelBanHang.SanPhamViewModel;
import domainModel.ChiTietSanPhamHiber;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import repository.impl.BanHangRepository;
import service.IBanHangService;

/**
 *
 * @author Admin
 */
public class BanHangServiceImpl implements IBanHangService {

    private BanHangRepository banHangRes = new BanHangRepository();

    @Override
    public List<SanPhamViewModel> getSanPhamVM() {
        return banHangRes.getSanPhamVM();
    }

    @Override
    public List<GioHangViewModel> getGioHang(String id) {
        return banHangRes.getGioHang(id);
    }

    @Override
    public List<HoaDonViewModel> getHoaDon() {
        return banHangRes.getHoaDon();
    }

    @Override
    public String saveHoaDon(HoaDonViewModel hd) {
        return banHangRes.saveHoaDon(hd);
    }

    @Override
    public String addHDCT(HoaDonChiTietCustomModel hdct) {
        return banHangRes.addHDCT(hdct);
    }

    @Override
    public String updateNVKH(HoaDonCustomModel hdUpdate, String ma) {
        return banHangRes.updateNVKH(hdUpdate, ma);
    }

    @Override
    public List<SanPhamViewModel> SearchSPBH(String input) {
        List<SanPhamViewModel> listNV = new ArrayList<>();
        if (input == null) {
            return banHangRes.getSanPhamVM();
        }
        for (SanPhamViewModel x : banHangRes.getSanPhamVM()) {
            if (x.getMaSP().contains(input) || x.getTenSP().contains(input) || x.getDongSP().contains(input) || x.getDeGiay().contains(input)
                    || x.getMauSac().contains(input) || String.valueOf(x.getDonGia()).contains(input) || String.valueOf(x.getSoLuong()).contains(input) || x.getXuatXu().contains(input)
                    || x.getKichCo().contains(input)) {
                listNV.add(x);
            }
        }
        return listNV;
    }

    @Override
    public String updateTrangThai(HoaDonViewModel hd, String ma) {
        return banHangRes.updateTrangThai(hd, ma);
    }

    @Override
    public String updateSoLuong(ChiTietSanPhamHiber ctsp, String id) {
        return banHangRes.updateSoLuong(ctsp, id);
    }

    @Override
    public String deleteGioHang(String id) {
        return banHangRes.deleteGioHang(id);
    }

    @Override
    public String capNhatSoLuong(ChiTietSanPhamHiber ctsp, String id) {
        return banHangRes.capNhatSoLuong(ctsp, id);
    }

    @Override
    public String capNhatSoLuong2(ChiTietSanPhamHiber ctsp, String id) {
        return banHangRes.capNhatSoLuong2(ctsp, id);
    }

    @Override
    public String updateSoLuongHDCT(GioHangViewModel gh, String id) {
        return banHangRes.updateSoLuongHDCT(gh, id);
    }

    @Override
    public List<HoaDonCustomModel> getHoaDonFull() {
        return banHangRes.getHoaDonFull();
    }

    @Override
    public String deleteHDCT(String idHD) {
        return banHangRes.deleteHDCT(idHD);
    }

    @Override
    public String deleteHD(String idHD) {
        return banHangRes.deleteHD(idHD);
    }

    @Override
    public int laySoLuong(String idSL) {
        return banHangRes.laySoLuong(idSL);
    }

}
