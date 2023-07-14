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




/**
 *
 * @author admin
 */
  @Entity
 @Table(name = "DeGiay")
 @Getter
 @Setter
public class DeGiayHiber implements Serializable{
   @Id
   @GenericGenerator(name = "generator", strategy = "guid", parameters = {})
   @GeneratedValue(generator = "generator")
   @Column(name = "Id", nullable = true)
   private String id;
   
   @Column(name = "Ma", length = 20, nullable = false, unique = true)
   private String ma;
   
   @Column(name = "Ten", length = 30, nullable = false)
   private String ten;
   
   @OneToMany(mappedBy = "idDeGiay", fetch = FetchType.LAZY)
   List<ChiTietSanPhamHiber> ChiTietSP;
   
    public DeGiayHiber() {
    }

    public DeGiayHiber(String id, String ma, String ten) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
    }

    
    public DeGiayHiber(String id, String ma, String ten, List<ChiTietSanPhamHiber> ChiTietSP) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.ChiTietSP = ChiTietSP;
    }
   
   
}
