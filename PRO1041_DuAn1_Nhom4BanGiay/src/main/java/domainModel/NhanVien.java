/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModel;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "NhanVien")
@Getter
@Setter

public class NhanVien implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", nullable = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "IdCV")
    private ChucVu cv;

    @Column(name = "Ma", nullable = false, length = 20)
    private String ma;

    @Column(name = "HoTen", length = 100, nullable = false)
    private String hoTen;

    @Column(name = "TaiKhoan", nullable = false)
    private String taiKhoan;

    @Column(name = "MatKhau", nullable = false)
    private String matKhau;

    @Column(name = "Sdt", length = 30, nullable = false)
    private String sdt;

    @Column(name = "Email", length = 100, nullable = false)
    private String email;

    @Column(name = "GioiTinh", length = 10, nullable = false)
    private String gioiTinh;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "DiaChi", length = 100, nullable = false)
    private String diaChi;

    public NhanVien() {
    }

    public NhanVien(String id, ChucVu cv, String ma, String hoTen, String taiKhoan, String matKhau, String sdt, String email, String gioiTinh, Date ngaySinh, String diaChi) {
        this.id = id;
        this.cv = cv;
        this.ma = ma;
        this.hoTen = hoTen;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public NhanVien(String id) {
        this.id = id;
    }

    public NhanVien(ChucVu cv, String ma, String hoTen, String taiKhoan, String matKhau, String sdt, String email, String gioiTinh, Date ngaySinh, String diaChi) {
        this.cv = cv;
        this.ma = ma;
        this.hoTen = hoTen;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "id=" + id + ", cv=" + cv + ", ma=" + ma + ", hoTen=" + hoTen + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + ", sdt=" + sdt + ", email=" + email + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + '}';
    }

}
