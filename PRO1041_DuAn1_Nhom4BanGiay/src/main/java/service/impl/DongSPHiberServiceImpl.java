/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainModel.DongSPHiber;
import domainModel.SanPhamHiber;
import repository.InterfaceHiber;
import repository.impl.DongSPHiberRepository;
import service.IDongSPHiberService;
import service.ISanPhamHiberService;

/**
 *
 * @author admin
 */
public class DongSPHiberServiceImpl implements IDongSPHiberService{

    private InterfaceHiber<DongSPHiber> DongSPHiber = new DongSPHiberRepository();
    @Override
    public DongSPHiber getByIndex(int input) {
        return DongSPHiber.getAll().get(input);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    



    
    
}
