/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.SanPhamCustomModel;
import domainModel.SanPham;
import java.util.ArrayList;
import java.util.List;
import repository.impl.SanPhamRepository;
import service.ISanPhamService;

public class SanPhamServiceImpl implements ISanPhamService {

    private SanPhamRepository msr = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return msr.getAll();
    }

    @Override
    public List<SanPhamCustomModel> getAllCustom() {
        return msr.getAllCustom();
    }

    @Override
    public SanPham getOne(String ma) {
        return msr.getOne(ma);
    }

    @Override
    public String add(SanPham ms) {
        return msr.add(ms);
    }

    @Override
    public String update(SanPham ms, String ma) {
        return msr.update(ms, ma);
    }

    @Override
    public String delete(String ma) {
        return msr.delete(ma);
    }

    @Override
    public List<SanPhamCustomModel> SearchNV(String input) {
        List<SanPhamCustomModel> listNV = new ArrayList<>();
        if (input == null) {
            return msr.getAllCustom();
        }
        for (SanPhamCustomModel x : msr.getAllCustom()) {
            if (x.getMa().contains(input) || x.getTen().contains(input)) {
                listNV.add(x);
            }
        }
        return listNV;
    }

    @Override
    public boolean CheckTrungMa(String input) {
        for (SanPhamCustomModel x : msr.getAllCustom()) {
            if (x.getMa().equals(input)) {
                return true;
            }
        }
        return false;

    }

}
