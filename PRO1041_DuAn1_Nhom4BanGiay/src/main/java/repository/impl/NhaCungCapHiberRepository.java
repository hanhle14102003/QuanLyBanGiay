/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.impl;

import domainModel.NhaCungCapHiber;
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
public class NhaCungCapHiberRepository implements InterfaceHiber<NhaCungCapHiber>{
    private static final Session session = HibernateUtil.getFACTORY().openSession();
    private String fromTable = "FROM NhaCungCapHiber";

    @Override
    public List<NhaCungCapHiber> getAll() {
        Query query = session.createQuery(fromTable, NhaCungCapHiber.class);
        List<NhaCungCapHiber> list = query.getResultList();
        return list;
        
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args) {
        InterfaceHiber a = new NhaCungCapHiberRepository();
        System.out.println(a.getAll());
    }
    
}
