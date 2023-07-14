/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.DongSanPhamCustomModel;
import domainModel.DongSP;
import java.util.ArrayList;
import java.util.List;
import repository.impl.DongSPRespository;
import service.IDongSPService;

/**
 *
 * @author Admin
 */
public class DongSPServiceImpl implements IDongSPService {

    private DongSPRespository dsp = new DongSPRespository();

    @Override
    public List<DongSP> getAll() {
        return dsp.getAll();
    }

    @Override
    public List<DongSanPhamCustomModel> getAllCustom() {
        return dsp.getAllCustom();
    }

    @Override
    public DongSP getOne(String ma) {
        return dsp.getOne(ma);
    }

    @Override
    public String add(DongSP kh) {
        return dsp.add(kh);
    }

    @Override
    public String update(DongSP kh, String ma) {
        return dsp.update(kh, ma);
    }

    @Override
    public String delete(String ma) {
        return dsp.delete(ma);
    }

    @Override
    public List<DongSanPhamCustomModel> Search(String ten) {
        return dsp.search(ten);
    }

    @Override
    public List<DongSanPhamCustomModel> SearchNV(String input) {
        List<DongSanPhamCustomModel> listNV = new ArrayList<>();
        if (input == null) {
            return dsp.getAllCustom();
        }
        for (DongSanPhamCustomModel x : dsp.getAllCustom()) {
            if (x.getMa().contains(input) || x.getTen().contains(input)) {
                listNV.add(x);
            }
        }
        return listNV;
    }

    @Override
    public boolean CheckTrungMa(String input) {
        for (DongSanPhamCustomModel x : dsp.getAllCustom()) {
            if (x.getMa().equals(input)) {
                return true;
            }
        }
        return false;

    }
}
