/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.SanPhamCustomModel;
import domainModel.SanPham;
import java.util.List;

public interface ISanPhamService {

    List<SanPham> getAll();

    List<SanPhamCustomModel> getAllCustom();

    SanPham getOne(String ma);

    String add(SanPham ms);

    String update(SanPham ms, String ma);

    String delete(String ma);

    List<SanPhamCustomModel> SearchNV(String input);

    boolean CheckTrungMa(String input);
}
