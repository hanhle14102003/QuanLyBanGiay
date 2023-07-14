/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.ChiTietSPCustomModel;
import domainModel.ChiTietSanPhamHiber;
import java.util.ArrayList;
import java.util.List;
import repository.IChiTietSanPhamRes;
import repository.impl.ChiTietSPRepositoryImpl;

import service.IChiTietSanPhamService;

/**
 *
 * @author admin
 */
public class ChiTietSanPhamServiceImpl implements IChiTietSanPhamService {

    private IChiTietSanPhamRes ChiTietSanPham = new ChiTietSPRepositoryImpl();

    @Override
    public List<ChiTietSanPhamHiber> getAll() {
        List<ChiTietSanPhamHiber> list_chiChiTietSanPham = new ArrayList<>();
        for (var x : ChiTietSanPham.getAll()) {
            list_chiChiTietSanPham.add(x);
        }
        return list_chiChiTietSanPham;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietSPCustomModel> getAllCustomModel(String input) {
        List<ChiTietSPCustomModel> list_chiCustomModels = new ArrayList<>();
        if (input == null) {
            return ChiTietSanPham.getAllCustomModels();
        }
        for (ChiTietSPCustomModel x : ChiTietSanPham.getAllCustomModels()) {
            if (x.getMaSP().contains(input) || x.getTenSP().contains(input) || x.getTenDongSP().contains(input) || x.getTenDeGiay().contains(input) || x.getTenMauSac().contains(input) || x.getXuatXu().contains(input)) {
                list_chiCustomModels.add(x);
            }
        }
        return list_chiCustomModels;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietSPCustomModel> getAllBetWeen(String batDau, String ketThuc) {
        List<ChiTietSPCustomModel> list_between = new ArrayList<>();
        for (var x : ChiTietSanPham.getAllBetWeen(batDau, ketThuc)) {
            list_between.add(x);
        }
        return list_between;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String add(ChiTietSanPhamHiber obj) {
        if (ChiTietSanPham.add(obj)) {
            return "Thêm thành công";
        }
        return "Thêm không thành công";
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String update(ChiTietSanPhamHiber obj) {
        if (ChiTietSanPham.update(obj)) {
            return "Cập nhật thành công";
        }
        return "Cập nhật không thành công";
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String delete(ChiTietSanPhamHiber obj) {
        if (ChiTietSanPham.delete(obj)) {
            return "Xóa thành công";
        }
        return "Xóa không thành công, sản phẩm chi tiết đã được thêm vào hóa đơn không thể xóa";
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietSPCustomModel getById(String input) {
        for (ChiTietSPCustomModel x : ChiTietSanPham.getAllCustomModels()) {
            if (input.equals(x.getId())) {
                return x;
            }
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getIdByIndex(int input) {
        return ChiTietSanPham.getAllCustomModels().get(input).getId();
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
