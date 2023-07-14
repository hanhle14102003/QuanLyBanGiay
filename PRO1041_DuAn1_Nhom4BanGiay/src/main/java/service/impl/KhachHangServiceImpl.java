/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.ChiTietSPCustomModel;
import customModel.KhachHangCustomModel;
import domainModel.KhachHang;
import java.util.ArrayList;
import java.util.List;
import repository.impl.KhachHangRepository;
import service.IKhachHangService;

/**
 *
 * @author ADMIN
 */
public class KhachHangServiceImpl implements IKhachHangService {

    private KhachHangRepository khr = new KhachHangRepository();

    @Override
    public List<KhachHang> getAll() {
        return khr.getAll();
    }

    @Override
    public List<KhachHangCustomModel> getAllCustom() {
        return khr.getAllCustom();
    }

    @Override
    public KhachHang getOne(String ma) {
        return khr.getOne(ma);
    }

    @Override
    public String add(KhachHang kh) {
        return khr.add(kh);
    }

    @Override
    public String update(KhachHang kh, String ma) {
        return khr.update(kh, ma);
    }

    @Override
    public String delete(String ma) {
        return khr.delete(ma);
    }

    public List<KhachHangCustomModel> SearchKH(String input) {
        List<KhachHangCustomModel> listKH = new ArrayList<>();
        if (input == null) {
            return khr.getAllCustom();
        }
        for (KhachHangCustomModel x : khr.getAllCustom()) {
            if (x.getMa().contains(input) || x.getHoTen().contains(input) || x.getSdt().contains(input)) {
                listKH.add(x);
            }
        }
        return listKH;
    }

    @Override
    public boolean CheckTrungMa(String input) {
        for (KhachHangCustomModel x : khr.getAllCustom()) {
            if (x.getMa().equals(input)) {
                return true;
            }
        }
        return false;
    }

}
