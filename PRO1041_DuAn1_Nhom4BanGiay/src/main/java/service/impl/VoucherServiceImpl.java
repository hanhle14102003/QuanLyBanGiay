/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.KhachHangCustomModel;
import customModel.VoucherCustomModel;
import domainModel.Voucher;
import java.util.ArrayList;
import java.util.List;
import repository.impl.VoucherRepository;
import service.IVoucherSevice;

/**
 *
 * @author ADMIN
 */
public class VoucherServiceImpl implements IVoucherSevice {

    private VoucherRepository vcr = new VoucherRepository();

    @Override
    public List<Voucher> getAll() {
        return vcr.getAll();
    }

    @Override
    public List<VoucherCustomModel> getAllCustom() {
        return vcr.getAllCustom();
    }

    @Override
    public Voucher getOne(String ma) {
        return vcr.getOne(ma);
    }

    @Override
    public String add(Voucher v) {
        return vcr.add(v);
    }

    @Override
    public String update(Voucher v, String ma) {
        return vcr.update(v, ma);
    }

    @Override
    public String delete(String ma) {
        return vcr.delete(ma);
    }

    @Override
    public List<VoucherCustomModel> Search(String input) {
        List<VoucherCustomModel> listKH = new ArrayList<>();
        if (input == null) {
            return vcr.getAllCustom();
        }
        for (VoucherCustomModel x : vcr.getAllCustom()) {
            if (x.getMa().contains(input) || x.getTen().contains(input) || String.valueOf(x.getGiamGia()).contains(input)
                    || String.valueOf(x.getNgayBatDau()).contains(input) || String.valueOf(x.getNgayHetHan()).contains(input)
                    || x.getGhiChu().contains(input) || x.getGhiChu().contains(input)) {
                listKH.add(x);
            }
        }
        return listKH;
    }

    @Override
    public List<VoucherCustomModel> getVoucherTrangThai() {
        return vcr.getVoucherTrangThai();
    }

    @Override
    public boolean CheckTrungMa(String input) {
        for (VoucherCustomModel x : vcr.getAllCustom()) {
            if (x.getMa().equals(input)) {
                return true;
            }
        }
        return false;
    }

}
