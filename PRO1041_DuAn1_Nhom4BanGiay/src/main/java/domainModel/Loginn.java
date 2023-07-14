/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModel;

import customModel.*;

/**
 *
 * @author ADMIN
 */
public class Loginn {
    private String username;
    private String password;
    private String ma;
    private String hoTen;

    public Loginn(String username, String password, String ma, String hoTen) {
        this.username = username;
        this.password = password;
        this.ma = ma;
        this.hoTen = hoTen;
    }

    public Loginn(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    @Override
    public String toString() {
        return "Login{" + "username=" + username + ", password=" + password + ", ma=" + ma + '}';
    }

}
