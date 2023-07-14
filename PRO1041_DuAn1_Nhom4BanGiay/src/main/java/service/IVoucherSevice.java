/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.VoucherCustomModel;
import domainModel.Voucher;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IVoucherSevice {

    List<Voucher> getAll();

    List<VoucherCustomModel> getAllCustom();

    Voucher getOne(String ma);

    String add(Voucher v);

    String update(Voucher v, String ma);

    String delete(String ma);

    List<VoucherCustomModel> Search(String input);

    List<VoucherCustomModel> getVoucherTrangThai();

    boolean CheckTrungMa(String input);
}
