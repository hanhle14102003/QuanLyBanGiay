/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.MauSacCustomModel;
import domainModel.MauSac;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IMauSacService {

    List<MauSac> getAll();

    List<MauSacCustomModel> getAllCustom();

    MauSac getOne(String ma);

    String add(MauSac ms);

    String update(MauSac ms, String ma);

    String delete(String ma);

    List<MauSacCustomModel> SearchNV(String input);

    boolean CheckTrungMa(String input);
}
