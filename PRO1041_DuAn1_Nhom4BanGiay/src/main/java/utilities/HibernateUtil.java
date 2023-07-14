/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import domainModel.ChiTietSanPhamHiber;
import domainModel.ChucVu;
import domainModel.DeGiayHiber;
import domainModel.DongSPHiber;
import domainModel.KhachHang;
import domainModel.MauSacHiber;
import domainModel.NhaCungCapHiber;
import domainModel.NhanVien;
import domainModel.SanPhamHiber;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author admin
 */
public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=QuanLyBanGiay_DA1_Nhom4");

        properties.put(Environment.USER, "sa");

        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");
        //gen DB tự động
        //properties.put(Environment.HBM2DDL_AUTO, "create");

        conf.setProperties(properties);
        conf.addAnnotatedClass(DeGiayHiber.class);
        conf.addAnnotatedClass(ChiTietSanPhamHiber.class);
        conf.addAnnotatedClass(SanPhamHiber.class);
        conf.addAnnotatedClass(DongSPHiber.class);
        conf.addAnnotatedClass(MauSacHiber.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(KhachHang.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }
}
