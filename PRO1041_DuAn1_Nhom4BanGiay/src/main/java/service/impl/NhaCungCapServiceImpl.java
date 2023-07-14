/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import customModel.NhaCungCapCM;
import domainModel.NhaCungCap;
import java.util.List;
import repository.impl.NhaCungCapRepository;
import service.INhaCungCapService;

/**
 *
 * @author khuong duc
 */
public class NhaCungCapServiceImpl implements INhaCungCapService{
     private NhaCungCapRepository nccr = new NhaCungCapRepository();

    @Override
    public List<NhaCungCap> getAll() {
        return nccr.getAll();
    }

    @Override
    public List<NhaCungCapCM> getAllCustom() {
        return nccr.getAllCustom();
    }

    @Override
    public NhaCungCap getOne(String ma) {
        return nccr.getOne(ma);
    }

    @Override
    public String add(NhaCungCap ncc) {
        return nccr.add(ncc);
    }

    @Override
    public String update(NhaCungCap ncc, String ma) {
        return nccr.update(ncc, ma);
    }

    @Override
    public String delete(String ma) {
        return nccr.delete(ma);
    }

    @Override
    public List<NhaCungCapCM> Search(String ten) {
        return nccr.search(ten);
    }
}
