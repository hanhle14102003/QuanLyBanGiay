/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainModel.SanPhamHiber;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import repository.InterfaceHiber;
import utilities.HibernateUtil;

/**
 *
 * @author admin
 */
public class SanPhamHiberRepository implements InterfaceHiber<SanPhamHiber>{
    private static final Session session = HibernateUtil.getFACTORY().openSession();
    private String fromTable = "FROM SanPhamHiber";

    @Override
    public List<SanPhamHiber> getAll() {
        Query query = session.createQuery(fromTable, SanPhamHiber.class);
        List<SanPhamHiber> list = query.getResultList();
        return list;
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        InterfaceHiber a = new SanPhamHiberRepository();
        System.out.println(a.getAll());
    }
    
}
