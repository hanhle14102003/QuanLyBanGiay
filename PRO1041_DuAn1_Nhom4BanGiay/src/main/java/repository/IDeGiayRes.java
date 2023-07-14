/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import customModel.DeGiayCustomModel;
import domainModel.DeGiayHiber;
import java.util.List;

/**
 *
 * @author admin
 * 
 */
public interface IDeGiayRes {
    List<DeGiayHiber> getAll();
    
    List<DeGiayCustomModel> getAllCustoms();
    
    boolean add(DeGiayHiber obj);
    
    boolean update(DeGiayHiber obj);
    
    boolean delete(DeGiayHiber obj);
            
}
