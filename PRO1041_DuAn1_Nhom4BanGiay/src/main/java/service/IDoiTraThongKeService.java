/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.SanPhamDoiTraThongKe;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IDoiTraThongKeService {
    List<SanPhamDoiTraThongKe> getAll();
    
    List<SanPhamDoiTraThongKe> getAllBetWeen(String batDau, String ketThuc);
    
    List<SanPhamDoiTraThongKe> getAllWhere( String input);
}
