/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domainModel;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ChucVu")
@Getter
@Setter

public class ChucVu implements Serializable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
    @GeneratedValue(generator = "generator")
    @Column(name = "Id", nullable = true)
    private String id;

    @Column(name = "Ma", length = 20)
    private String ma;

    @Column(name = "Ten", length = 50)
    private String ten;

    @OneToMany(mappedBy = "cv", fetch = FetchType.LAZY)
    List<NhanVien> nhanVien;

    public ChucVu() {
    }

    public ChucVu(String id, String ma, String ten, List<NhanVien> nhanVien) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.nhanVien = nhanVien;
    }

    public ChucVu(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "ChucVu{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + '}';
    }

}
