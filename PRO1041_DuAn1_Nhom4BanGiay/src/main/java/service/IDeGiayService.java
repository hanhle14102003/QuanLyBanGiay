/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import customModel.DeGiayCustomModel;
import domainModel.DeGiayHiber;
import java.util.List;

/**
 *
 * @author admin
 */
public interface IDeGiayService {
    List<DeGiayCustomModel> getAll(String input);
    
    String getIdbyIndex (int input);
    
    boolean CheckTrungMa(String input);
    
    DeGiayHiber getDeGiayHiberbyIndex (int input);
    
    String add(DeGiayHiber obj);
    
    String update(DeGiayHiber obj);
    
    String delete(DeGiayHiber obj);
}
