/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainModel.MauSacHiber;
import domainModel.SanPhamHiber;
import repository.InterfaceHiber;
import repository.impl.MauSacHiberRepository;
import repository.impl.SanPhamHiberRepository;
import service.IMauSacHiberService;
import service.ISanPhamHiberService;

/**
 *
 * @author admin
 */
public class MauSacHiberServiecImpl implements IMauSacHiberService{

    private InterfaceHiber<MauSacHiber> mauSacHiber = new MauSacHiberRepository();

    @Override
    public MauSacHiber getByIndex(int input) {
        return mauSacHiber.getAll().get(input);
       // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
