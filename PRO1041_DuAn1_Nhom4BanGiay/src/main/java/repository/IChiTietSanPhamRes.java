/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import customModel.ChiTietSPCustomModel;
import domainModel.ChiTietSanPhamHiber;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IChiTietSanPhamRes {
    
    List<ChiTietSanPhamHiber> getAll();
    
    List<ChiTietSPCustomModel> getAllCustomModels();
    
    List<ChiTietSPCustomModel> getAllBetWeen(String batDau, String ketThuc);
    
    boolean add(ChiTietSanPhamHiber obj);
    
    boolean update(ChiTietSanPhamHiber obj);
    
    boolean delete(ChiTietSanPhamHiber obj);
}
