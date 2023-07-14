/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.KhachHangCustomModel;
import domainModel.KhachHang;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IKhachHangService {

    List<KhachHang> getAll();

    List<KhachHangCustomModel> getAllCustom();

    KhachHang getOne(String ma);

    String add(KhachHang kh);

    String update(KhachHang kh, String ma);

    String delete(String ma);

    boolean CheckTrungMa(String input);

}
