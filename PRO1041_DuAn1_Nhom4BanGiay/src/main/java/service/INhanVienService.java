/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.NhanVienCustomModel;
import domainModel.NhanVien;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface INhanVienService {

    List<NhanVien> getAll();

    NhanVien getOne(String ma);

    String add(NhanVien t);

    String update(NhanVien t, String id);

    String delete(String id);

    List<NhanVienCustomModel> getAllCustom();

    List<NhanVienCustomModel> Search(String ten);

    List<NhanVienCustomModel> SearchNV(String input);

    List<NhanVienCustomModel> getAllCustomByMaNV();

    boolean CheckTrungMa(String input);
}
