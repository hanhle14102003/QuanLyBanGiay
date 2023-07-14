/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import domainModel.Loginn;
import javax.swing.JOptionPane;
import repository.impl.LoginRepository;
import service.ILoginService;

/**
 *
 * @author ADMIN
 */
public class LoginServiceImpl implements ILoginService {

    private LoginRepository lgr = new LoginRepository();

    @Override
    public Loginn login(String user, String pass) {

        return lgr.login(user, pass);

    }
}
