/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.HDCTCustoModelHD;
import customModel.HoaDonCustomModelHD;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IHoaDonHDService {

    List<HDCTCustoModelHD> getHDCT(String id);

    List<HoaDonCustomModelHD> getHoaDon();

    List<HoaDonCustomModelHD> SearchHD(String input);

    List<HoaDonCustomModelHD> SearchCBB(String input);

    List<HoaDonCustomModelHD> getHoaDonBetween(String batDau, String ketThuc);
}
