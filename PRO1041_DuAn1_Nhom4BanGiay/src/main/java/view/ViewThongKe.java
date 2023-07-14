/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import customModel.HDCTCustoModelHD;
import customModel.HoaDonCustomModelHD;
import customModel.HoaDonCustomModelHDThongKe;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ItemListener;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import service.IDoiTraService;
import service.IHoaDonHDService;
import service.IHoaDonHDServiceThongKe;
import service.impl.DoiTraServiceImpl;
import service.impl.HoaDonHDServiceImpl;
import service.impl.HoaDonHDServiceImplThongKe;
import utilities.Utility;
import repository.IDoiTraThongKeRes;
import service.IDoiTraThongKeService;
import service.impl.DoiTraThongKeServiceImpl;

/**
 *
 * @author ADMIN
 */
public class ViewThongKe extends javax.swing.JFrame {

    private IHoaDonHDServiceThongKe hoaDonHDServiceThongKe = new HoaDonHDServiceImplThongKe();
    private IDoiTraThongKeService doiTraThongKe = new DoiTraThongKeServiceImpl();
    private utilities.Utility utility = new Utility();

    /**
     * Creates new form ViewThongKe
     */
    JTextField batDau;
    JTextField ketThuc;
    JTextField batDauDoiTra;
    JTextField ketThucDoiTra;

    public ViewThongKe() {
        initComponents();

        batDau = ((JTextField) date_batDau.getDateEditor().getUiComponent());
        ketThuc = ((JTextField) date_ketThuc.getDateEditor().getUiComponent());
        batDauDoiTra = ((JTextField) date_doiTraBD.getDateEditor().getUiComponent());
        ketThucDoiTra = ((JTextField) date_doiTraKT.getDateEditor().getUiComponent());
        setDoanhThuTheoNgay(date + "");
        setHoaDonTheoNgay(date + "");
        setSanPhamTheoNgay(date + "");
        setKhachHangTheoNgay(date + "");
        setDoiTraTheoNgay(date + "");

        setBieuDoDTTheoNam(pnl_doanhThu, "2022");
        setBieuDoKHTheoNam(pnl_khachHang, "2022");
        setBieuDoKHTronGetAll(pnl_khachHangTron);

        setDoiTraChatLuongGetAll();
        setDoiTraKichCoGetAll();
        setDoiTraMauSacGetAll();
        setTronDoiTraGetAll(pnl_doiTra);

        date_batDau.setDate(new java.util.Date());
        date_ketThuc.setDate(new java.util.Date());

        date_doiTraBD.setDate(new java.util.Date());
        date_doiTraKT.setDate(new java.util.Date());

        txt_namDT.setText(year + "");
        txt_namKH.setText(year + "");

    }

    // lấy ra ngày + tháng + năm hiện tại
    Calendar instance = Calendar.getInstance();
    int year = instance.get(Calendar.YEAR);
    int month = instance.get(Calendar.MONTH) + 1;
    int day = instance.get(Calendar.DATE);
    String date = (java.time.LocalDate.now() + "");

    public void setDoanhThuTheoNgay(String day) {

        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;
        double sum = 0;
        String ngay = null;
        if (day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5") || day.equals("6") || day.equals("7") || day.equals("8") || day.equals("9")) {
            ngay = "0" + String.valueOf(day);
        } else {
            ngay = day;
        }
        for (HoaDonCustomModelHDThongKe x : hoaDonHDServiceThongKe.getHoaDonByNgay("'" + day + "'")) {
            sum = sum + x.getTongTien();
            tong = instance.format(sum);
        }
        if (sum == 0) {
            lbl_doanhThu.setText(sum + "");
        } else {
            lbl_doanhThu.setText(tong + "");
        }

    }

    public void setHoaDonTheoNgay(String day) {
        int sum = 0;
        String ngay = null;
        if (day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5") || day.equals("6") || day.equals("7") || day.equals("8") || day.equals("9")) {
            ngay = "0" + String.valueOf(day);
        } else {
            ngay = day;
        }
        for (var x : hoaDonHDServiceThongKe.getHoaDonByNgay("'" + day + "'")) {
            sum++;
        }
        lbl_hoaDon.removeAll();
        lbl_hoaDon.setText(sum + "");

    }

    public void setSanPhamTheoNgay(String day) {
        int sum = 0;
        String ngay = null;
        if (day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5") || day.equals("6") || day.equals("7") || day.equals("8") || day.equals("9")) {
            ngay = "0" + String.valueOf(day);
        } else {
            ngay = day;
        }
        for (var x : hoaDonHDServiceThongKe.getHoaDonByNgay("'" + day + "'")) {
            sum += x.getTongSP();
        }
        lbl_sanPhamtk.removeAll();
        lbl_sanPhamtk.setText(sum + "");
    }

    public void setKhachHangTheoNgay(String day) {
        int sum = 0;
        String ngay = null;
        if (day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5") || day.equals("6") || day.equals("7") || day.equals("8") || day.equals("9")) {
            ngay = "0" + String.valueOf(day);
        } else {
            ngay = day;
        }
        for (var x : hoaDonHDServiceThongKe.getHoaDonByNgay("'" + day + "'")) {
            sum++;
        }
        lbl_khachHang.removeAll();
        lbl_khachHang.setText(sum + "");
    }

    public void setDoiTraTheoNgay(String day) {

        int sum = 0;
        String ngay = null;
        if (day.equals("1") || day.equals("2") || day.equals("3") || day.equals("4") || day.equals("5") || day.equals("6") || day.equals("7") || day.equals("8") || day.equals("9")) {
            ngay = "0" + String.valueOf(day);
        } else {
            ngay = day;
        }
        for (var x : doiTraThongKe.getAllWhere("'" + day + "'")) {
            sum++;
        }
        lbl_doiTra.removeAll();
        lbl_doiTra.setText(sum + "");
    }

    public void setDoanhThuTheoNam(String batDau, String ketThuc) {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;

        double sum = 0;
        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            sum = sum + x.getTongTien();
            tong = instance.format(sum);
        }
        lbl_doanhThu.removeAll();

        if (sum == 0) {
            lbl_doanhThu.setText(sum + "");
        } else {
            lbl_doanhThu.setText(tong + "");
        }

    }

    public void setHoaDonTheoNam(String batDau, String ketThuc) {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;
        int sum = 0;
        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            sum++;
            tong = instance.format(sum);
        }
        lbl_hoaDon.removeAll();
        if (sum == 0) {
            lbl_hoaDon.setText(sum + "");
        } else {
            lbl_hoaDon.setText(tong + "");
        }
    }

    public void setSanPhamTheoNam(String batDau, String ketThuc) {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;
        int sum = 0;
        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            sum = sum + x.getTongSP();
            tong = instance.format(sum);
        }
        lbl_sanPhamtk.removeAll();
        if (sum == 0) {
            lbl_sanPhamtk.setText(sum + "");
        } else {
            lbl_sanPhamtk.setText(tong + "");
        }
    }

    public void setKhachHangTheoNam(String batDau, String ketThuc) {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;
        int sum = 0;
        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            sum++;
            tong = instance.format(sum);
        }
        lbl_khachHang.removeAll();

        if (sum == 0) {
            lbl_khachHang.setText(sum + "");

        } else {
            lbl_khachHang.setText(tong + "");
        }
    }

    public void setDoiTraTheoNam(String batDau, String ketThuc) {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;
        int sum = 0;
        for (var x : doiTraThongKe.getAllBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            sum++;
            tong = instance.format(sum);

        }
        lbl_doiTra.removeAll();
        if (sum == 0) {
            lbl_doiTra.setText(sum + "");
        } else {
            lbl_doiTra.setText(tong + "");
        }

    }

    public void setDoiTraChatLuong(String batDau, String ketThuc) {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;
        int sum = 0;
        for (var x : doiTraThongKe.getAllBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            if (x.getLiDoDoi().equals("Chat Luong")) {
                sum++;
                tong = instance.format(sum);
            }
        }
        lbl_doiTraChatLuong.removeAll();
        if (sum == 0) {
            lbl_doiTraChatLuong.setText(sum + "");
        } else {
            lbl_doiTraChatLuong.setText(tong + "");
        }

    }

    public void setDoiTraChatLuongGetAll() {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;
        int sum = 0;
        for (var x : doiTraThongKe.getAll()) {
            if (x.getLiDoDoi().equals("Chat Luong")) {
                sum++;
                tong = instance.format(sum);
            }
        }
        lbl_doiTraChatLuong.removeAll();
        if (sum == 0) {
            lbl_doiTraChatLuong.setText(sum + "");
        } else {
            lbl_doiTraChatLuong.setText(tong + "");
        }
    }

    public void setDoiTraMauSac(String batDau, String ketThuc) {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;
        int sum = 0;
        for (var x : doiTraThongKe.getAllBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            if (x.getLiDoDoi().equals("Mau Sac")) {
                sum++;
                tong = instance.format(sum);
            }
        }
        lbl_doiTraMauSac.removeAll();
        if (sum == 0) {
            lbl_doiTraMauSac.setText(sum + "");
        } else {
            lbl_doiTraMauSac.setText(tong + "");
        }
    }

    public void setDoiTraMauSacGetAll() {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;
        int sum = 0;
        for (var x : doiTraThongKe.getAll()) {
            if (x.getLiDoDoi().equals("Mau Sac")) {
                sum++;
                tong = instance.format(sum);
            }
        }
        lbl_doiTraMauSac.removeAll();
        if (sum == 0) {
            lbl_doiTraMauSac.setText(sum + "");
        } else {
            lbl_doiTraMauSac.setText(tong + "");
        }
    }

    public void setDoiTraKichCo(String batDau, String ketThuc) {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;

        int sum = 0;
        for (var x : doiTraThongKe.getAllBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            if (x.getLiDoDoi().equals("Kich Co")) {
                sum++;
                tong = instance.format(sum);
            }
        }
        lbl_doiTraKichCo.removeAll();
        if (sum == 0) {
            lbl_doiTraKichCo.setText(sum + "");
        } else {
            lbl_doiTraKichCo.setText(tong + "");
        }
    }

    public void setDoiTraKichCoGetAll() {
        NumberFormat instance = NumberFormat.getInstance();
        String tong = null;
        int sum = 0;
        for (var x : doiTraThongKe.getAll()) {
            if (x.getLiDoDoi().equals("Kich Co")) {
                sum++;
                tong = instance.format(sum);
            }
        }
        lbl_doiTraKichCo.removeAll();
        if (sum == 0) {
            lbl_doiTraKichCo.setText(sum + "");
        } else {
            lbl_doiTraKichCo.setText(tong + "");
        }
    }

    public void setBieuDoDTTheoNam(JPanel input, String batDau) {
        double thang1 = 0;
        double thang2 = 0;
        double thang3 = 0;
        double thang4 = 0;
        double thang5 = 0;
        double thang6 = 0;
        double thang7 = 0;
        double thang8 = 0;
        double thang9 = 0;
        double thang10 = 0;
        double thang11 = 0;
        double thang12 = 0;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-01-01'", "'" + batDau + "-02-01'")) {
            if (x.getTrangThai() == 3) {
                thang1 += x.getTongTien();
                dataset.addValue(thang1, "Doang Thu Năm " + batDau, "Tháng 1");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-02-02'", "'" + batDau + "-03-01'")) {
            if (x.getTrangThai() == 3) {
                thang2 += x.getTongTien();
                dataset.addValue(thang2, "Doang Thu Năm " + batDau, "Tháng 2");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-03-02'", "'" + batDau + "-04-01'")) {
            if (x.getTrangThai() == 3) {
                thang3 += x.getTongTien();
                dataset.addValue(thang3, "Doang Thu Năm " + batDau, "Tháng 3");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-04-02'", "'" + batDau + "-05-01'")) {
            if (x.getTrangThai() == 3) {
                thang4 += x.getTongTien();
                dataset.addValue(thang4, "Doang Thu Năm " + batDau, "Tháng 4");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-05-02'", "'" + batDau + "-06-01'")) {
            if (x.getTrangThai() == 3) {
                thang5 += x.getTongTien();
                dataset.addValue(thang5, "Doang Thu Năm " + batDau, "Tháng 5");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-06-02'", "'" + batDau + "-07-01'")) {
            if (x.getTrangThai() == 3) {
                thang6 += x.getTongTien();
                dataset.addValue(thang6, "Doang Thu Năm " + batDau, "Tháng 6");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-07-02'", "'" + batDau + "-08-01'")) {
            if (x.getTrangThai() == 3) {
                thang7 += x.getTongTien();
                dataset.addValue(thang7, "Doang Thu Năm " + batDau, "Tháng 7");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-08-02'", "'" + batDau + "-09-01'")) {
            if (x.getTrangThai() == 3) {
                thang8 += x.getTongTien();
                dataset.addValue(thang8, "Doang Thu Năm " + batDau, "Tháng 8");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-09-02'", "'" + batDau + "-10-01'")) {
            thang9 += x.getTongTien();
            dataset.addValue(thang9, "Doang Thu Năm " + batDau, "Tháng 9");
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-10-02'", "'" + batDau + "-11-01'")) {
            if (x.getTrangThai() == 3) {
                thang10 += x.getTongTien();
                dataset.addValue(thang10, "Doang Thu Năm " + batDau, "Tháng 10");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-11-02'", "'" + batDau + "-12-01'")) {
            if (x.getTrangThai() == 3) {
                thang11 += x.getTongTien();
                dataset.addValue(thang11, "Doang Thu Năm " + batDau, "Tháng 11");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-12-02'", "'" + batDau + "-12-07'")) {
            if (x.getTrangThai() == 3) {
                thang12 += x.getTongTien();
                dataset.addValue(thang12, "Doang Thu Năm " + batDau, "Tháng 12");
            }
        }

        JFreeChart cha = ChartFactory.createBarChart("Thống Kê Doanh Thu", null, "Doanh Thu", dataset);
        ChartPanel chartPanel = new ChartPanel(cha);
        chartPanel.setPreferredSize(new Dimension(input.getWidth(), 470));

        input.removeAll();
        input.setLayout(new CardLayout());
        input.add(chartPanel);
        input.validate();
        input.repaint();

    }

    public void setBieuDoKHTronTheoNam(JPanel input, String batDau) {
        int nho = 0;
        int tre = 0;
        int trung = 0;
        int gia = 0;

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-01-01'", "'" + batDau + "-12-31'")) {
            if (x.getTrangThai() == 3 && x.getTuoiKH() > 14 && x.getTuoiKH() <= 20) {
                nho++;
                dataset.setValue("Tuổi từ 14-20", nho);
            }
        }
        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-01-01'", "'" + batDau + "-12-31'")) {
            if (x.getTrangThai() == 3 && x.getTuoiKH() > 20 && x.getTuoiKH() <= 40) {
                tre++;
                dataset.setValue("Tuổi từ 20-40", tre);
            }

        }
        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-01-01'", "'" + batDau + "-12-31'")) {
            if (x.getTrangThai() == 3 && x.getTuoiKH() > 40 && x.getTuoiKH() <= 60) {
                trung++;
                dataset.setValue("Tuổi từ 40-60", trung);
            }

        }
        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-01-01'", "'" + batDau + "-12-31'")) {
            if (x.getTrangThai() == 3 && x.getTuoiKH() > 60) {
                gia++;
                dataset.setValue("Tuổi trên 60", gia);
            }
        }

        JFreeChart cha = ChartFactory.createPieChart("Biểu Đồ Thống Kê Tuổi Khách Hàng Năm " + batDau, dataset);
        ChartPanel chartPanel = new ChartPanel(cha);;

        chartPanel.setPreferredSize(new Dimension(input.getWidth(), 5));

        input.removeAll();
        input.setLayout(new CardLayout());
        input.add(chartPanel);
        input.validate();
        input.repaint();
    }

    public void setBieuDoKHTronGetAll(JPanel input) {
        int nho = 0;
        int tre = 0;
        int trung = 0;
        int gia = 0;

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (var x : hoaDonHDServiceThongKe.getAllHoaDon()) {
            if (x.getTrangThai() == 3 && x.getTuoiKH() > 14 && x.getTuoiKH() <= 20) {
                nho++;
                dataset.setValue("Tuổi từ 14-20", nho);
            }
        }
        for (var x : hoaDonHDServiceThongKe.getAllHoaDon()) {
            if (x.getTrangThai() == 3 && x.getTuoiKH() > 20 && x.getTuoiKH() <= 40) {
                tre++;
                dataset.setValue("Tuổi từ 20-40", tre);
            }

        }
        for (var x : hoaDonHDServiceThongKe.getAllHoaDon()) {
            if (x.getTrangThai() == 3 && x.getTuoiKH() > 40 && x.getTuoiKH() <= 60) {
                trung++;
                dataset.setValue("Tuổi từ 40-60", trung);
            }

        }
        for (var x : hoaDonHDServiceThongKe.getAllHoaDon()) {
            if (x.getTrangThai() == 3 && x.getTuoiKH() > 60) {
                gia++;
                dataset.setValue("Tuổi trên 60", gia);
            }
        }

        JFreeChart cha = ChartFactory.createPieChart("Biểu Đồ Thống Kê Tuổi Trung Bình Khách Hàng ", dataset);
        ChartPanel chartPanel = new ChartPanel(cha);;
        chartPanel.setPreferredSize(new Dimension(input.getWidth(), 5));

        input.removeAll();
        input.setLayout(new CardLayout());
        input.add(chartPanel);
        input.validate();
        input.repaint();
    }

    public void setTronDoiTraTheoNam(JPanel input, String batDau, String ketThuc) {
        int chatLuong = 0;
        int mauSac = 0;
        int kichCo = 0;

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (var x : doiTraThongKe.getAllBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            if (x.getLiDoDoi().equals("Chat Luong")) {
                chatLuong++;
                dataset.setValue("Chất lượng", chatLuong);
            }
        }
        for (var x : doiTraThongKe.getAllBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            if (x.getLiDoDoi().equals("Mau Sac")) {
                mauSac++;
                dataset.setValue("Màu Sắc", chatLuong);
            }
        }
        for (var x : doiTraThongKe.getAllBetWeen("'" + batDau + "'", "'" + ketThuc + "'")) {
            if (x.getLiDoDoi().equals("Kich Co")) {
                kichCo++;
                dataset.setValue("Kích Cỡ", chatLuong);
            }
        }

        JFreeChart cha = ChartFactory.createPieChart("Biểu Đồ Thống Kê Sản Phẩm Đổi Trả ", dataset);
        ChartPanel chartPanel = new ChartPanel(cha);;
        chartPanel.setPreferredSize(new Dimension(input.getWidth(), 470));

        input.removeAll();
        input.setLayout(new CardLayout());
        input.add(chartPanel);
        input.validate();
        input.repaint();
    }

    public void setTronDoiTraGetAll(JPanel input) {

        int chatLuong = 0;
        int mauSac = 0;
        int kichCo = 0;

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (var x : doiTraThongKe.getAll()) {
            if (x.getLiDoDoi().equals("Chat Luong")) {
                chatLuong++;
                dataset.setValue("Chất lượng", chatLuong);
            }
        }
        for (var x : doiTraThongKe.getAll()) {
            if (x.getLiDoDoi().equals("Mau Sac")) {
                mauSac++;
                dataset.setValue("Màu Sắc", chatLuong);
            }
        }
        for (var x : doiTraThongKe.getAll()) {
            if (x.getLiDoDoi().equals("Kich Co")) {
                kichCo++;
                dataset.setValue("Kích Cỡ", chatLuong);
            }
        }

        JFreeChart cha = ChartFactory.createPieChart("Biểu Đồ Thống Kê Sản Phẩm Đổi Trả ", dataset);
        ChartPanel chartPanel = new ChartPanel(cha);;
        chartPanel.setPreferredSize(new Dimension(input.getWidth(), 470));

        input.removeAll();
        input.setLayout(new CardLayout());
        input.add(chartPanel);
        input.validate();
        input.repaint();
    }

    public void setBieuDoKHTheoNam(JPanel input, String batDau) {
        double thang1 = 0;
        double thang2 = 0;
        double thang3 = 0;
        double thang4 = 0;
        double thang5 = 0;
        double thang6 = 0;
        double thang7 = 0;
        double thang8 = 0;
        double thang9 = 0;
        double thang10 = 0;
        double thang11 = 0;
        double thang12 = 0;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-01-01'", "'" + batDau + "-02-01'")) {
            if (x.getTrangThai() == 3) {
                thang1++;
                dataset.addValue(thang1, "Lượng Khách Hàng Năm " + batDau, "Tháng 1");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-02-02'", "'" + batDau + "-03-01'")) {
            if (x.getTrangThai() == 3) {
                thang2++;
                dataset.addValue(thang2, "Lượng Khách Hàng Năm " + batDau, "Tháng 2");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-03-02'", "'" + batDau + "-04-01'")) {
            if (x.getTrangThai() == 3) {
                thang3++;
                dataset.addValue(thang3, "Lượng Khách Hàng Năm " + batDau, "Tháng 3");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-04-02'", "'" + batDau + "-05-01'")) {
            if (x.getTrangThai() == 3) {
                thang4++;
                dataset.addValue(thang4, "Lượng Khách Hàng Năm " + batDau, "Tháng 4");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-05-02'", "'" + batDau + "-06-01'")) {
            if (x.getTrangThai() == 3) {
                thang5++;
                dataset.addValue(thang5, "Lượng Khách Hàng Năm " + batDau, "Tháng 5");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-06-02'", "'" + batDau + "-07-01'")) {
            if (x.getTrangThai() == 3) {
                thang6++;
                dataset.addValue(thang6, "Lượng Khách Hàng Năm " + batDau, "Tháng 6");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-07-02'", "'" + batDau + "-08-01'")) {
            if (x.getTrangThai() == 3) {
                thang7++;
                dataset.addValue(thang7, "Lượng Khách Hàng Năm " + batDau, "Tháng 7");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-08-02'", "'" + batDau + "-09-01'")) {
            if (x.getTrangThai() == 3) {
                thang8++;
                dataset.addValue(thang8, "Lượng Khách Hàng Năm " + batDau, "Tháng 8");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-09-02'", "'" + batDau + "-10-01'")) {
            thang9++;
            dataset.addValue(thang9, "Lượng Khách Hàng Năm " + batDau, "Tháng 9");
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-10-02'", "'" + batDau + "-11-01'")) {
            if (x.getTrangThai() == 3) {
                thang10++;
                dataset.addValue(thang10, "Lượng Khách Hàng Năm " + batDau, "Tháng 10");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-11-02'", "'" + batDau + "-12-01'")) {
            if (x.getTrangThai() == 3) {
                thang11++;
                dataset.addValue(thang11, "Lượng Khách Hàng Năm " + batDau, "Tháng 11");
            }
        }

        for (var x : hoaDonHDServiceThongKe.getHoaDonBetWeen("'" + batDau + "-12-02'", "'" + batDau + "-12-31'")) {
            if (x.getTrangThai() == 3) {
                thang12++;
                dataset.addValue(thang12, "Lượng Khách Hàng Năm " + batDau, "Tháng 12");
            }
        }

        JFreeChart cha = ChartFactory.createBarChart("Biểu Đồ Thống Kê Khách Hàng Năm " + batDau, null, "Khách Hàng", dataset);
        ChartPanel chartPanel = new ChartPanel(cha);
        chartPanel.setPreferredSize(new Dimension(input.getWidth(), 470));

        input.removeAll();
        input.setLayout(new CardLayout());
        input.add(chartPanel);
        input.validate();
        input.repaint();

    }

    public boolean checkDL(JTextField input) {
        String p = "0[0-9]{3}";
        if (utility.CheckSo(input.getText())) {

            JOptionPane.showMessageDialog(this, "Năm Không Đúng Kiểu Dữ Liệu");

            input.requestFocus();
            input.setText("");
            return true;
        }

        if (utility.DemChuoi(input.getText()) > 4 || utility.DemChuoi(input.getText()) < 4) {
            JOptionPane.showMessageDialog(this, "Năm Không Đúng Kiểu Dữ Liệu");
            input.requestFocus();
            input.setText("");
            return true;
        }

        if (input.getText().matches(p)) {
            JOptionPane.showMessageDialog(this, "Năm Không Đúng Kiểu Dữ Liệu");
            input.requestFocus();
            input.setText("");
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_doanhThu = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_hoaDon = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbl_sanPhamtk = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbl_khachHang = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        pnl_doanhThu = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txt_namDT = new javax.swing.JTextField();
        btn_thongKeDT = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        pnl_khachHangTron = new javax.swing.JPanel();
        pnl_khachHang = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txt_namKH = new javax.swing.JTextField();
        btn_thongKeKH = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        pnl_doiTra = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbl_doiTraKichCo = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbl_doiTraMauSac = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbl_doiTraChatLuong = new javax.swing.JLabel();
        date_doiTraBD = new com.toedter.calendar.JDateChooser();
        date_doiTraKT = new com.toedter.calendar.JDateChooser();
        btn_thongKeDoiTra = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbl_doiTra = new javax.swing.JLabel();
        btn_reset = new javax.swing.JButton();
        btn_thongKeLBL = new javax.swing.JButton();
        date_batDau = new com.toedter.calendar.JDateChooser();
        date_ketThuc = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Doanh Thu");

        lbl_doanhThu.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_doanhThu.setForeground(new java.awt.Color(204, 0, 0));
        lbl_doanhThu.setText("20.000.000");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("VNĐ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_doanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addGap(82, 82, 82))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_doanhThu)
                    .addComponent(jLabel9))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Hóa đơn");

        lbl_hoaDon.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_hoaDon.setForeground(new java.awt.Color(204, 0, 0));
        lbl_hoaDon.setText("209");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(lbl_hoaDon)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Sản phẩm bán ra");

        lbl_sanPhamtk.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_sanPhamtk.setForeground(new java.awt.Color(204, 0, 0));
        lbl_sanPhamtk.setText("270");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl_sanPhamtk, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lbl_sanPhamtk)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Lượng khách hàng");

        lbl_khachHang.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_khachHang.setForeground(new java.awt.Color(204, 0, 0));
        lbl_khachHang.setText("200");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(lbl_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lbl_khachHang)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Thống Kê");

        javax.swing.GroupLayout pnl_doanhThuLayout = new javax.swing.GroupLayout(pnl_doanhThu);
        pnl_doanhThu.setLayout(pnl_doanhThuLayout);
        pnl_doanhThuLayout.setHorizontalGroup(
            pnl_doanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1120, Short.MAX_VALUE)
        );
        pnl_doanhThuLayout.setVerticalGroup(
            pnl_doanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        jLabel12.setText("Năm");

        btn_thongKeDT.setText("Thống Kê");
        btn_thongKeDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongKeDTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_doanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_namDT, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_thongKeDT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_thongKeDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_namDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnl_doanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jTabbedPane4.addTab("Biểu Đồ Thống Kê Doanh Thu", jPanel6);

        javax.swing.GroupLayout pnl_khachHangTronLayout = new javax.swing.GroupLayout(pnl_khachHangTron);
        pnl_khachHangTron.setLayout(pnl_khachHangTronLayout);
        pnl_khachHangTronLayout.setHorizontalGroup(
            pnl_khachHangTronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 387, Short.MAX_VALUE)
        );
        pnl_khachHangTronLayout.setVerticalGroup(
            pnl_khachHangTronLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnl_khachHangLayout = new javax.swing.GroupLayout(pnl_khachHang);
        pnl_khachHang.setLayout(pnl_khachHangLayout);
        pnl_khachHangLayout.setHorizontalGroup(
            pnl_khachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 738, Short.MAX_VALUE)
        );
        pnl_khachHangLayout.setVerticalGroup(
            pnl_khachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        jLabel11.setText("Năm");

        btn_thongKeKH.setText("Thống Kê");
        btn_thongKeKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongKeKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnl_khachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_namKH, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_thongKeKH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(pnl_khachHangTron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_khachHangTron, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_namKH)
                                .addComponent(btn_thongKeKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane4.addTab("Biểu Đồ Thống Kê Khách Hàng", jPanel7);

        javax.swing.GroupLayout pnl_doiTraLayout = new javax.swing.GroupLayout(pnl_doiTra);
        pnl_doiTra.setLayout(pnl_doiTraLayout);
        pnl_doiTraLayout.setHorizontalGroup(
            pnl_doiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 633, Short.MAX_VALUE)
        );
        pnl_doiTraLayout.setVerticalGroup(
            pnl_doiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 51));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Sản Phẩm Đổi Trả Do Kích Cỡ");

        lbl_doiTraKichCo.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_doiTraKichCo.setForeground(new java.awt.Color(204, 0, 0));
        lbl_doiTraKichCo.setText("200");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(lbl_doiTraKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(32, 32, 32)
                .addComponent(lbl_doiTraKichCo)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 51));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Sản Phẩm Đổi Trả Do Màu Sắc");

        lbl_doiTraMauSac.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_doiTraMauSac.setForeground(new java.awt.Color(204, 0, 0));
        lbl_doiTraMauSac.setText("200");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(lbl_doiTraMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(50, 50, 50)
                .addComponent(lbl_doiTraMauSac)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 51));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Sản Phẩm Đổi Trả Do Chất Lượng");

        lbl_doiTraChatLuong.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_doiTraChatLuong.setForeground(new java.awt.Color(204, 0, 0));
        lbl_doiTraChatLuong.setText("200");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(lbl_doiTraChatLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135))))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(33, 33, 33)
                .addComponent(lbl_doiTraChatLuong)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        date_doiTraBD.setDateFormatString("yyyy-MM-dd\n");

        date_doiTraKT.setDateFormatString("yyyy-MM-dd\n");

        btn_thongKeDoiTra.setText("Thống Kê");
        btn_thongKeDoiTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongKeDoiTraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnl_doiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(date_doiTraBD, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(date_doiTraKT, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_thongKeDoiTra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(date_doiTraBD, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(date_doiTraKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl_doiTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(btn_thongKeDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane4.addTab("Sản Phẩm Đổi Trả", jPanel8);

        jPanel9.setBackground(new java.awt.Color(255, 255, 51));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Sản Phẩm Đổi Trả");

        lbl_doiTra.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_doiTra.setForeground(new java.awt.Color(204, 0, 0));
        lbl_doiTra.setText("200");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(lbl_doiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_doiTra)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        btn_reset.setText("Reset");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        btn_thongKeLBL.setText("Thống Kê");
        btn_thongKeLBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongKeLBLActionPerformed(evt);
            }
        });

        date_batDau.setDateFormatString("yyyy-MM-dd\n");

        date_ketThuc.setDateFormatString("yyyy-MM-dd\n");

        jLabel13.setText("Ngày Bắt Đầu");

        jLabel14.setText("Ngày Kết Thúc");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(434, 434, 434)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTabbedPane4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date_batDau, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date_ketThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btn_thongKeLBL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_reset))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btn_thongKeLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_reset))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(date_batDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(date_ketThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:

        setDoanhThuTheoNgay(date);
        setHoaDonTheoNgay(date);
        setSanPhamTheoNgay(date);
        setKhachHangTheoNgay(date);

        setBieuDoKHTronGetAll(pnl_khachHangTron);
        setBieuDoDTTheoNam(pnl_doanhThu, year + "");
        setBieuDoKHTheoNam(pnl_khachHang, year + "");
        setTronDoiTraGetAll(pnl_doiTra);

        setDoiTraChatLuongGetAll();
        setDoiTraKichCoGetAll();
        setDoiTraMauSacGetAll();

        date_batDau.setDate(new java.util.Date());
        date_ketThuc.setDate(new java.util.Date());

        date_doiTraBD.setDate(new java.util.Date());
        date_doiTraKT.setDate(new java.util.Date());

        txt_namDT.setText(year + "");
        txt_namKH.setText(year + "");
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_thongKeLBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongKeLBLActionPerformed

        try {

            Date bd = new SimpleDateFormat("yyyy-MM-dd").parse(batDau.getText());
            Date kt = new SimpleDateFormat("yyyy-MM-dd").parse(ketThuc.getText());

            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();

            c1.setTime(bd);
            c2.setTime(kt);

            long noDay = ((c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000));
            System.out.println(noDay);
            if (noDay < 0) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc lớn hơn ngày bắt đầu");
            } else {

                setDoanhThuTheoNam(batDau.getText(), ketThuc.getText());
                setKhachHangTheoNam(batDau.getText(), ketThuc.getText());
                setHoaDonTheoNam(batDau.getText(), ketThuc.getText());
                setSanPhamTheoNam(batDau.getText(), ketThuc.getText());
                setDoiTraTheoNam(batDau.getText(), ketThuc.getText());
            }
        } catch (ParseException ex) {
            Logger.getLogger(ViewThongKe.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btn_thongKeLBLActionPerformed

    private void btn_thongKeDoiTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongKeDoiTraActionPerformed
        // TODO add your handling code here:
        setTronDoiTraTheoNam(pnl_doiTra, batDauDoiTra.getText(), ketThucDoiTra.getText());
        setDoiTraChatLuong(batDauDoiTra.getText(), ketThucDoiTra.getText());
        setDoiTraKichCo(batDauDoiTra.getText(), ketThucDoiTra.getText());
        setDoiTraMauSac(batDauDoiTra.getText(), ketThucDoiTra.getText());
    }//GEN-LAST:event_btn_thongKeDoiTraActionPerformed

    private void btn_thongKeKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongKeKHActionPerformed
        // TODO add your handling code here:
        if (checkDL(txt_namKH) == false) {
            setBieuDoKHTheoNam(pnl_khachHang, txt_namKH.getText());
        }
    }//GEN-LAST:event_btn_thongKeKHActionPerformed

    private void btn_thongKeDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongKeDTActionPerformed
        // TODO add your handling code here:
        if (checkDL(txt_namDT) == false) {
            setBieuDoDTTheoNam(pnl_doanhThu, txt_namDT.getText());
        }
    }//GEN-LAST:event_btn_thongKeDTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_reset;
    private javax.swing.JButton btn_thongKeDT;
    private javax.swing.JButton btn_thongKeDoiTra;
    private javax.swing.JButton btn_thongKeKH;
    private javax.swing.JButton btn_thongKeLBL;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser date_batDau;
    private com.toedter.calendar.JDateChooser date_doiTraBD;
    private com.toedter.calendar.JDateChooser date_doiTraKT;
    private com.toedter.calendar.JDateChooser date_ketThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel lbl_doanhThu;
    private javax.swing.JLabel lbl_doiTra;
    private javax.swing.JLabel lbl_doiTraChatLuong;
    private javax.swing.JLabel lbl_doiTraKichCo;
    private javax.swing.JLabel lbl_doiTraMauSac;
    private javax.swing.JLabel lbl_hoaDon;
    private javax.swing.JLabel lbl_khachHang;
    private javax.swing.JLabel lbl_sanPhamtk;
    private javax.swing.JPanel pnl_doanhThu;
    private javax.swing.JPanel pnl_doiTra;
    private javax.swing.JPanel pnl_khachHang;
    private javax.swing.JPanel pnl_khachHangTron;
    private javax.swing.JTextField txt_namDT;
    private javax.swing.JTextField txt_namKH;
    // End of variables declaration//GEN-END:variables
}
