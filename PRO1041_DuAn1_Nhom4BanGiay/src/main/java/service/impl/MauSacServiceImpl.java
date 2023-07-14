/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.MauSacCustomModel;
import domainModel.MauSac;
import java.util.ArrayList;
import java.util.List;
import repository.impl.MauSacRepository;
import service.IMauSacService;

/**
 *
 * @author ADMIN
 */
public class MauSacServiceImpl implements IMauSacService {

    private MauSacRepository msr = new MauSacRepository();

    @Override
    public List<MauSac> getAll() {
        return msr.getAll();
    }

    @Override
    public List<MauSacCustomModel> getAllCustom() {
        return msr.getAllCustom();
    }

    @Override
    public MauSac getOne(String ma) {
        return msr.getOne(ma);
    }

    @Override
    public String add(MauSac ms) {
        return msr.add(ms);
    }

    @Override
    public String update(MauSac ms, String ma) {
        return msr.update(ms, ma);
    }

    @Override
    public String delete(String ma) {
        return msr.delete(ma);
    }

    @Override
    public List<MauSacCustomModel> SearchNV(String input) {
        List<MauSacCustomModel> listNV = new ArrayList<>();
        if (input == null) {
            return msr.getAllCustom();
        }
        for (MauSacCustomModel x : msr.getAllCustom()) {
            if (x.getMa().contains(input) || x.getTen().contains(input)) {
                listNV.add(x);
            }
        }
        return listNV;
    }

    @Override
    public boolean CheckTrungMa(String input) {
        for (MauSacCustomModel x : msr.getAllCustom()) {
            if (x.getMa().equals(input)) {
                return true;
            }
        }
        return false;

    }

}
