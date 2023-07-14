/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModelDoiTra.HDCTDoiTraCustomModel;
import customModelDoiTra.HoaDonDoiTraCustomModel;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IDoiTraService {

    List<HoaDonDoiTraCustomModel> getHoaDonDoiTra();

    List<HDCTDoiTraCustomModel> getHDCTDoiTra(String id);

    List<HoaDonDoiTraCustomModel> SearchHDDT(String input);

    String doiTra(HoaDonDoiTraCustomModel hd);

    String capNhatSoLuong(HDCTDoiTraCustomModel hdct, String id);

    List<HoaDonDoiTraCustomModel> getHoaDonDaDoiTra();

    List<HDCTDoiTraCustomModel> getHDCTDaDoiTra(String id);

    List<HoaDonDoiTraCustomModel> SearchHDDaDT(String input);
    
    List<HoaDonDoiTraCustomModel> getHoaDonDaDoiTraBetween(String batDau, String ketThuc);
    
    List<HoaDonDoiTraCustomModel> getHoaDonDoiTraBetween(String batDau, String ketThuc);
}
