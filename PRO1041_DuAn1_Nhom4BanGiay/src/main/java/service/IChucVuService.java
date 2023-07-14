/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.ChucVuCustomModel;
import domainModel.ChucVu;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IChucVuService {

    List<ChucVu> getAll();

    ChucVu getOne(String ma);

    String add(ChucVu t);

    String update(ChucVu t, String id);

    String delete(String ma);

    List<ChucVuCustomModel> getAllCustom();

    List<ChucVuCustomModel> Search(String input);

    boolean CheckTrungMa(String input);

}
