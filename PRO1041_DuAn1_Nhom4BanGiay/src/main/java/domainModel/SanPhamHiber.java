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
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "SanPham")
@Getter
@Setter
public class SanPhamHiber implements Serializable{
    @Id
    @GenericGenerator(name = "Gennerator",strategy = "guid",parameters = {})
    @GeneratedValue(generator = "Gennerator")
    
    @Column(name = "Id", nullable = true)
    private String id;
    
    @Column(name = "Ma", nullable = true)
    private String ma;
    
    @Column(name = "Ten", nullable = true)
    private String ten;
    
    @OneToMany(mappedBy = "idSP", fetch = FetchType.LAZY)
    private List<ChiTietSanPhamHiber> chiTietSP;

    public SanPhamHiber() {
    }

    public SanPhamHiber(String id, String ma, String ten, List<ChiTietSanPhamHiber> chiTietSP) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.chiTietSP = chiTietSP;
    }


    
    
    
    
}
