/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.SanPhamDoiTraThongKe;
import java.util.List;
import repository.impl.DoiTraThongKeRepository;
import service.IDoiTraThongKeService;
import repository.IDoiTraThongKeRes;

/**
 *
 * @author admin
 */
public class DoiTraThongKeServiceImpl implements IDoiTraThongKeService{
private IDoiTraThongKeRes doiTra = new DoiTraThongKeRepository();
    @Override
    public List<SanPhamDoiTraThongKe> getAllBetWeen(String batDau, String ketThuc) {
        return doiTra.getAllBetWeen(batDau,ketThuc);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPhamDoiTraThongKe> getAllWhere(String input) {
        return doiTra.getAllWhere(input);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SanPhamDoiTraThongKe> getAll() {
        return doiTra.getAll();
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    
}
