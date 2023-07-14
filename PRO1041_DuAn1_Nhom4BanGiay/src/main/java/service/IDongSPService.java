/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.DongSanPhamCustomModel;
import customModel.KhachHangCustomModel;
import domainModel.DongSP;
import domainModel.KhachHang;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IDongSPService {

    List<DongSP> getAll();

    List<DongSanPhamCustomModel> getAllCustom();

    DongSP getOne(String ma);

    String add(DongSP kh);

    String update(DongSP kh, String ma);

    String delete(String ma);

    List<DongSanPhamCustomModel> Search(String ten);

    List<DongSanPhamCustomModel> SearchNV(String input);

    boolean CheckTrungMa(String input);

}
