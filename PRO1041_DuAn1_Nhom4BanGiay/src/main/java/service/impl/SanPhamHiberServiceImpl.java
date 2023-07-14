/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainModel.SanPhamHiber;
import repository.InterfaceHiber;
import repository.impl.SanPhamHiberRepository;
import service.ISanPhamHiberService;

/**
 *
 * @author admin
 */
public class SanPhamHiberServiceImpl implements ISanPhamHiberService{

    private InterfaceHiber<SanPhamHiber> SanPhamHiber = new SanPhamHiberRepository();
    @Override
    public SanPhamHiber getByIndex(int input) {
        return SanPhamHiber.getAll().get(input);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
