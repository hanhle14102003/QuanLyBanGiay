/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.DeGiayCustomModel;
import domainModel.DeGiayHiber;
import java.util.ArrayList;
import java.util.List;
import repository.IDeGiayRes;
import repository.impl.DeGiayRepository;
import service.IDeGiayService;

/**
 *
 * @author admin
 */
public class DeGiayServiecImpl implements IDeGiayService{
    private IDeGiayRes DeGiayRepository = new DeGiayRepository();

    @Override
    public List<DeGiayCustomModel> getAll(String input) {
        List<DeGiayCustomModel> list_deGiay = new ArrayList<>();
        if (input == null) {
            return DeGiayRepository.getAllCustoms();
        }
        for (DeGiayCustomModel x : DeGiayRepository.getAllCustoms()) {
            if (x.getMa().contains(input) || x.getTen().contains(input)) {
                list_deGiay.add(x);
            }
        }
        return list_deGiay;
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   
    
    @Override
    public String getIdbyIndex(int input) {
        return DeGiayRepository.getAll().get(input).getId();
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String add(DeGiayHiber obj) {
        if (DeGiayRepository.add(obj)) {
            return "Thêm thành công";
        }
        return "Thêm thất bại";
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String update(DeGiayHiber obj ){
        if (DeGiayRepository.update(obj)) {
            return "Sửa thành công";
        }
        return "Sửa thất bại";
    }
    
    @Override
    public String delete(DeGiayHiber obj ){
        if (DeGiayRepository.delete(obj)) {
            return "Xóa thành công";
        }
        return "Xóa thất bại, đế giầy đã được thêm vào sản phẩm chi tiết không thể xóa";
    }

    @Override
    public DeGiayHiber getDeGiayHiberbyIndex(int input) {
        return DeGiayRepository.getAll().get(input);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean CheckTrungMa(String input) {
        for (DeGiayHiber x : DeGiayRepository.getAll()) {
            if (x.getMa().equals(input)) {
                return true;
            }
        }
        return false;
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }



    
    
    
}
