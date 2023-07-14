/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.HDCTCustoModelHD;
import customModel.HDCTCustoModelHDThongKe;
import customModel.HoaDonCustomModelHD;
import customModel.HoaDonCustomModelHDThongKe;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IHoaDonHDServiceThongKe {

    List<HDCTCustoModelHDThongKe> getHDCT(String id);
    
    List<HDCTCustoModelHDThongKe> getAllHDCT();

    List<HoaDonCustomModelHDThongKe> getAllHoaDon();
    
    List<HoaDonCustomModelHDThongKe> getHoaDonByNgay(String input);
    
    List<HoaDonCustomModelHDThongKe> getHoaDonBetWeen(String batDau,String ketThuc);

    List<HoaDonCustomModelHDThongKe> SearchHD(String input);

    List<HoaDonCustomModelHDThongKe> SearchCBB(String input);
}
