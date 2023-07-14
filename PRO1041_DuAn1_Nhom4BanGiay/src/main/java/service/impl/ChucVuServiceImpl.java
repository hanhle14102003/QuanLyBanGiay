/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import com.itextpdf.text.pdf.PdfStructTreeController;
import customModel.ChucVuCustomModel;
import domainModel.ChucVu;
import java.util.ArrayList;
import java.util.List;
import repository.impl.ChucVuRepository;
import service.IChucVuService;

/**
 *
 * @author ADMIN
 */
public class ChucVuServiceImpl implements IChucVuService {

    private ChucVuRepository ChucVuRes = new ChucVuRepository();

    @Override
    public List<ChucVu> getAll() {

        return ChucVuRes.getAll();
    }

    @Override
    public ChucVu getOne(String ma) {
        return ChucVuRes.getOne(ma);

    }

    @Override
    public String add(ChucVu t) {
        if (ChucVuRes.add(t)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(ChucVu t, String id) {
        if (ChucVuRes.update(t, id)) {
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    @Override
    public String delete(String ma) {
        if (ChucVuRes.delete(ma)) {
            return "Xóa thành công";
        } else {
            return "Xóa thất bại, chức vụ đã được thêm vào nhân viên không thể xóa";
        }
    }

    @Override
    public List<ChucVuCustomModel> getAllCustom() {
        return ChucVuRes.getAllCustom();

    }

    @Override
    public List<ChucVuCustomModel> Search(String input) {
        List<ChucVuCustomModel> listCV = new ArrayList<>();
        if (input == null) {
            return ChucVuRes.getAllCustom();
        }
        for (ChucVuCustomModel x : ChucVuRes.getAllCustom()) {
            if (x.getMa().contains(input) || x.getTen().contains(input)) {
                listCV.add(x);
            }
        }
        return listCV;
    }

    @Override
    public boolean CheckTrungMa(String input) {
        for (ChucVuCustomModel x : ChucVuRes.getAllCustom()) {
            if (x.getMa().equals(input)) {
                return true;
            }
        }
        return false;
    }

}
