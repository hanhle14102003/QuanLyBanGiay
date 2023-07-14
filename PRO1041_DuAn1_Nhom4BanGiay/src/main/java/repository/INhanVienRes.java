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
public interface INhanVienRes<T> {

    List<T> getAll();

    T getOne(String ma);

    boolean add(T t);

    boolean update(T t, String ma);

    boolean delete(String ma);
}
