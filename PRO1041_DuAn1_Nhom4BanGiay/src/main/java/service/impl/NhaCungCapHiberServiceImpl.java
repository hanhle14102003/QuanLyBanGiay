/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainModel.NhaCungCapHiber;
import domainModel.SanPhamHiber;
import repository.InterfaceHiber;
import repository.impl.NhaCungCapHiberRepository;
import repository.impl.SanPhamHiberRepository;
import service.INhaCungCapHiberService;
import service.ISanPhamHiberService;

/**
 *
 * @author admin
 */
public class NhaCungCapHiberServiceImpl implements INhaCungCapHiberService{

    private InterfaceHiber<NhaCungCapHiber> nhaCungCapHiber = new NhaCungCapHiberRepository();
    @Override
    public NhaCungCapHiber getByIndex(int input) {
        return nhaCungCapHiber.getAll().get(input);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
