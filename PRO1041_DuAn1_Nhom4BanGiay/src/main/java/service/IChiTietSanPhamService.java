/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.ChiTietSPCustomModel;
import domainModel.ChiTietSanPhamHiber;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IChiTietSanPhamService {
    List<ChiTietSanPhamHiber> getAll();
    
    List<ChiTietSPCustomModel> getAllCustomModel(String input);
    
    ChiTietSPCustomModel getById(String input);
    
    List<ChiTietSPCustomModel> getAllBetWeen(String batDau, String ketThuc);
    
    String getIdByIndex(int input);
    
    String add(ChiTietSanPhamHiber obj);
    
    String update(ChiTietSanPhamHiber obj);
    
    String delete(ChiTietSanPhamHiber obj);
}
