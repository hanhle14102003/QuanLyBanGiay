/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.KhachHangCustomModel;
import customModel.NhaCungCapCM;
import domainModel.NhaCungCap;
import java.util.List;

/**
 *
 * @author khuong duc
 */
public interface INhaCungCapService {
        
    List<NhaCungCap> getAll();

    List<NhaCungCapCM> getAllCustom();

    NhaCungCap getOne(String ma);

    String add(NhaCungCap ncc);

    String update(NhaCungCap ncc, String ma);

    String delete(String ma);

    List<NhaCungCapCM> Search(String ten);
}
