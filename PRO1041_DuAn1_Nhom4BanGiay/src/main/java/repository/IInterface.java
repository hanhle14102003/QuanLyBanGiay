/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface IInterface<T> {
    List<T> getAll();
    
    T getOne(String ma);

    String add(T t);

    String update(T t,String ma);

    String delete(String ma);
    
//    List<T> search(String ten);
}

