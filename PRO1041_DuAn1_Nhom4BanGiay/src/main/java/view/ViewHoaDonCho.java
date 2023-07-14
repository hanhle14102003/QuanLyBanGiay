/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import customModel.DeGiayCustomModel;
import customModel.DongSanPhamCustomModel;
import customModel.HoaDonChiTietCustomModel;
import customModel.HoaDonCustomModel;
import customModel.KhachHangCustomModel;
import customModel.MauSacCustomModel;
import customModel.NhanVienCustomModel;
import customModel.VoucherCustomModel;
import customModelBanHang.GioHangViewModel;
import customModelBanHang.HoaDonViewModel;
import customModelBanHang.SanPhamViewModel;
import domainModel.ChiTietSanPhamHiber;
import domainModel.HoaDon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import service.IDeGiayService;
import service.IDongSPService;
import service.IMauSacService;
import service.impl.BanHangServiceImpl;
import service.impl.DeGiayServiecImpl;
import service.impl.DongSPServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.MauSacServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.VoucherServiceImpl;
import utilities.Utility;

/**
 *
 * @author ADMIN
 */
public class ViewHoaDonCho extends javax.swing.JFrame {

    private DefaultTableModel tblModelHoaDon = new DefaultTableModel();
    private DefaultTableModel tblModelGioHang = new DefaultTableModel();
    private DefaultComboBoxModel cbbModelNV = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbModelKH = new DefaultComboBoxModel();
    private DefaultTableModel tblModelSanPham = new DefaultTableModel();
    private List<HoaDonViewModel> listHoaDons = new ArrayList<>();
    private List<SanPhamViewModel> listSanPhams = new ArrayList<>();
    private List<GioHangViewModel> listGioHangS = new ArrayList<>();
    private List<NhanVienCustomModel> listNV = new ArrayList<>();
    private List<KhachHangCustomModel> listKH = new ArrayList<>();
    private List<HoaDonCustomModel> listFullHD = new ArrayList<>();
    private List<DeGiayCustomModel> ListDG = new ArrayList<>();
    private List<DongSanPhamCustomModel> listDSP = new ArrayList<>();
    private List<MauSacCustomModel> listMS = new ArrayList<>();
    private BanHangServiceImpl bhs = new BanHangServiceImpl();
    private NhanVienServiceImpl nvs = new NhanVienServiceImpl();
    private KhachHangServiceImpl khs = new KhachHangServiceImpl();
    private IDeGiayService deGiayService = new DeGiayServiecImpl();
    private IDongSPService dongSPService = new DongSPServiceImpl();
    private IMauSacService mauSacService = new MauSacServiceImpl();
    private utilities.Utility uti = new Utility();
    private List<VoucherCustomModel> listVoucherCM = new ArrayList<>();
    private VoucherServiceImpl vcs = new VoucherServiceImpl();
    private DefaultComboBoxModel cbbModelVC = new DefaultComboBoxModel();
    DecimalFormat fomat = new DecimalFormat("###,###,###");

    public ViewHoaDonCho() {
        initComponents();
        String headersss[] = {"Mã HĐ", "Ngày tạo", "Nhân viên tạo", "Khách hàng", "Tình trạng"};
        tblModelHoaDon.setColumnIdentifiers(headersss);
        String headers[] = {"Mã SP", "Tên SP", "Số luọng", "Đon giá",};
        tblModelGioHang.setColumnIdentifiers(headers);
        String headerss[] = {"Mã SP", "Tên SP", "Dòng sản phẩm", "Đế giày", "Mau sắc", "Đơn giá", "Số lượng", "Xuất xứ", "Size"};
        tblModelSanPham.setColumnIdentifiers(headerss);
        tblHoaDon.setModel(tblModelHoaDon);
        tblGioHang.setModel(tblModelGioHang);
        tblSanPham.setModel(tblModelSanPham);
        listSanPhams = bhs.getSanPhamVM();
        listHoaDons = bhs.getHoaDon();
        listFullHD = bhs.getHoaDonFull();
        //Load Combobox
        ListDG = deGiayService.getAll(null);
        listDSP = dongSPService.getAllCustom();
        listMS = mauSacService.getAllCustom();

        AutoCompleteDecorator.decorate(cbbNhanVienBH);
        AutoCompleteDecorator.decorate(cbbSoDienThoai);
        listNV = nvs.getAllCustomByMaNV();
        listNV.forEach((nv) -> {
            cbbNhanVienBH.addItem(nv.getHoTen());
        });
        cbbNhanVienBH.setSelectedItem("Tên nhân viên");

        listKH = khs.getAllCustom();
        listKH.forEach((kh) -> {
            cbbSoDienThoai.addItem(kh.getSdt());
        });
        cbbSoDienThoai.setSelectedItem("(84+)");
        showDataSanPham(listSanPhams);
        loadDataHoaDon(listHoaDons);
        cbbDSPBH.removeAllItems();
        cbbDGBH.removeAllItems();
        cbbMSBH.removeAllItems();
        listDSP.forEach((dsp) -> {
            cbbDSPBH.addItem(dsp.getTen());
        });
        ListDG.forEach((dg) -> {
            cbbDGBH.addItem(dg.getTen());
        });
        listMS.forEach((ms) -> {
            cbbMSBH.addItem(ms.getTen());
        });
        // trang thai taoHoaDon
        if (demTrangThai() > 4) {
            btnTaoHoaDon.setEnabled(false);
        }
        //Enter txt tiền khách đưa --> tiền thừa
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double thanhToan = Double.valueOf(lblThanhToan.getText().replace(",", ""));
                Double tienKhachDua = Double.valueOf(txtTienKhachDua.getText());
                Double tienThua = 0.0;

                if (tienKhachDua == 0 || tienKhachDua == null) {
                    tienThua = 0.0;
                } else {
                    tienThua = tienKhachDua - thanhToan;
                }
                lblTienThua.setText(String.valueOf(fomat.format(tienThua)));
            }
        };
        txtTienKhachDua.addActionListener(action);
        listVoucherCM = vcs.getVoucherTrangThai();
        listVoucherCM.forEach((vc) -> {
            cbbGiaGiam.addItem(String.valueOf(vc.giamGiaTT()));
        });
    }

    private void loadDataHoaDon(List<HoaDonViewModel> listHoaDons) {
//        loaddata hóa đơn
        tblModelHoaDon.setRowCount(0);

        for (HoaDonViewModel listHoaDon : listHoaDons) {

            tblModelHoaDon.addRow(listHoaDon.toRowDataHD());
        }
    }

    private void showDataSanPham(List<SanPhamViewModel> listSanPhams) {
        tblModelSanPham.setRowCount(0);

        for (SanPhamViewModel sp : listSanPhams) {

            tblModelSanPham.addRow(sp.todataRowSanPham());
        }

    }

    private void showDataGioHang(List<GioHangViewModel> listGioHangS) {

        tblModelGioHang.setRowCount(0);
        for (GioHangViewModel gh : listGioHangS) {

            tblModelGioHang.addRow(gh.todataRow());
        }
        // show data giỏ hàng

    }
    // Fill data 

    private void fillDataHD(int index) {
        //fill data hoa don
        HoaDonViewModel hd = listHoaDons.get(index);
        lblMaHD.setText(hd.getMa());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        cbbNhanVienBH.setSelectedItem(hd.getNv());
        cbbSoDienThoai.setSelectedItem(hd.getSdt());
        lblNgayTao.setText(sdf.format(hd.getNgayTao()));

    }

    //Fill data txtTenKhachHang từ cbbSDT
//    private void fillDataKH(int index2) {
//        KhachHangCustomModel kh = listKH.get(index2);
//        txtTenKhachHangBH.setText(kh.getHoTen());
//    }
    // Đếm trạng thái chờ sét btnTaoHoaDon
    public int demTrangThai() {
        int a = 0;
        for (HoaDonViewModel x : listHoaDons) {
            if (x.getTrangThai() == 1) {
                x.getTrangThai();
                a++;
            }
        }
        return a;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaSanPham = new javax.swing.JButton();
        btnCapNhatSP = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTimKiemDSSP = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        cbbDSPBH = new javax.swing.JComboBox<>();
        cbbDGBH = new javax.swing.JComboBox<>();
        cbbMSBH = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnThayDoi = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbbNhanVienBH = new javax.swing.JComboBox<>();
        btnThemKHBH = new javax.swing.JButton();
        btnReloadBH = new javax.swing.JButton();
        cbbSoDienThoai = new javax.swing.JComboBox<>();
        txtTenKhachHangBH = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelTT = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnHuyHoaDon = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        lblMaHD = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lblTienThua = new javax.swing.JLabel();
        btnTaoHoaDon = new javax.swing.JButton();
        cbbGiaGiam = new javax.swing.JComboBox<>();
        lblTenVoucher = new javax.swing.JLabel();
        btnQuayLai = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtHoaDonPDF = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Hóa đơn chờ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Giỏ hàng");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblGioHang);

        btnXoaSanPham.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoaSanPham.setText("Xóa");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        btnCapNhatSP.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCapNhatSP.setText("Cập nhật");
        btnCapNhatSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCapNhatSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnXoaSanPham)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhatSP))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Danh sách sản phẩm");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Tìm kiếm sản phẩm");

        txtTimKiemDSSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemDSSPCaretUpdate(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblSanPham);

        cbbDSPBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDSPBHActionPerformed(evt);
            }
        });

        cbbDGBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDGBHActionPerformed(evt);
            }
        });

        cbbMSBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMSBHActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("Dòng sản phẩm");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("Đế giày");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setText("Màu");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiemDSSP))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbDSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbDGBH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMSBH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTimKiemDSSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDGBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMSBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Nhân viên:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Khách hàng:");

        btnThayDoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThayDoi.setText("Thay đổi");
        btnThayDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Số điện thoại:");

        btnThemKHBH.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThemKHBH.setText("+");
        btnThemKHBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHBHActionPerformed(evt);
            }
        });

        btnReloadBH.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReloadBH.setText("Load");
        btnReloadBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadBHActionPerformed(evt);
            }
        });

        cbbSoDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSoDienThoaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtTenKhachHangBH, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnThemKHBH))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(cbbNhanVienBH, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnReloadBH))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnThayDoi)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbbNhanVienBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReloadBH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemKHBH)
                        .addComponent(txtTenKhachHangBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbbSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThayDoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Mã hóa đơn: ");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Ngày tạo:");

        labelTT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        labelTT.setText("Thành tiền:");

        lblGiamGia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblGiamGia.setText("Giảm giá:");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Thanh toán:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Tiền khách đưa:");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Tiền thừa:");

        btnHuyHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnHuyHoaDon.setText("Hủy hóa đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        btnLamMoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThanhToan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        lblMaHD.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMaHD.setText("HD___");

        lblNgayTao.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNgayTao.setText("dd/mm/yyyy");

        lblThanhTien.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblThanhTien.setForeground(new java.awt.Color(204, 0, 0));
        lblThanhTien.setText("0");

        lblThanhToan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblThanhToan.setForeground(new java.awt.Color(204, 0, 0));
        lblThanhToan.setText("0");

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("VNĐ");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("VNĐ");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setText("VNĐ");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("VNĐ");

        txtTienKhachDua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTienKhachDua.setForeground(new java.awt.Color(204, 0, 0));
        txtTienKhachDua.setText("0");

        lblTienThua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(204, 0, 0));
        lblTienThua.setText("0");

        btnTaoHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        cbbGiaGiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbGiaGiamActionPerformed(evt);
            }
        });

        lblTenVoucher.setText("Voucher");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblGiamGia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                                .addComponent(lblThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblTenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(19, 19, 19)))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnTaoHoaDon))))))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lblMaHD)
                    .addComponent(btnTaoHoaDon))
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblNgayTao))
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTT)
                    .addComponent(lblThanhTien)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addComponent(lblTenVoucher)
                .addGap(4, 4, 4)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiamGia)
                    .addComponent(cbbGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblThanhToan)
                    .addComponent(jLabel25))
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel27)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel26)
                    .addComponent(lblTienThua))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Hóa đơn ");

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtHoaDonPDF.setColumns(20);
        txtHoaDonPDF.setRows(5);
        jScrollPane4.setViewportView(txtHoaDonPDF);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addGap(88, 88, 88))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(5, 5, 5))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnQuayLai)
                        .addGap(20, 20, 20))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel5))
                    .addComponent(btnQuayLai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        //Lấy row table --> Dữ liệu
        GioHangViewModel gh = new GioHangViewModel();
        int rowHD = tblHoaDon.getSelectedRow();
        int row = tblSanPham.getSelectedRow();
        SanPhamViewModel sp = listSanPhams.get(row);
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn thêm sản phẩm");
        } else {
            String soLuong = JOptionPane.showInputDialog("Mời bạn nhập số lượng: ");

            if (soLuong != null) {
                if (!soLuong.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(this, "Nhập đúng định dạng");
                } else if (Integer.valueOf(soLuong) > sp.getSoLuong()) {
                    JOptionPane.showMessageDialog(this, "Số lượng vượt quá -.-");
                } else {
                    // Thêm sản phẩm vào giỏ hàng
                    HoaDonViewModel hd = listHoaDons.get(rowHD);
                    gh.setSoLuong(Integer.valueOf(soLuong));
                    gh.setMaSP(sp.getMaSP());
                    gh.setTenSP(sp.getTenSP());
                    gh.setDonGia(sp.getDonGia());
                    boolean trung = false;
                    for (GioHangViewModel x : listGioHangS) {
                        if (x.getMaSP().contains(sp.getMaSP())) {
                            trung = true;
                        }
                    }
                    if (trung) {
                        JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong giỏ hàng, để thêm số lượng vui lòng chọn chức năng cập nhật");
                    } else {
                        // Thêm sản phẩm vào list giỏ hàng
                        listGioHangS.add(gh);

                        sp.setSoLuong(sp.getSoLuong() - Integer.valueOf(soLuong));
                        showDataSanPham(listSanPhams);

                        String idHD = hd.getId();
                        String idCtsp = sp.getId();
                        int soLuong1 = Integer.valueOf(soLuong);
                        Double donGia = sp.getDonGia();

                        // add giỏ hàng vào HDCT
                        HoaDonChiTietCustomModel hdct = new HoaDonChiTietCustomModel(idHD, idCtsp, soLuong1, donGia);
                        JOptionPane.showMessageDialog(this, bhs.addHDCT(hdct));
                        listGioHangS = bhs.getGioHang(idHD);
                        showDataGioHang(listGioHangS);

                        //Cập nhật số lượng trong bảng Sản phẩm CT
                        ChiTietSanPhamHiber ctsp = new ChiTietSanPhamHiber(sp.getSoLuong());
                        bhs.updateSoLuong(ctsp, idCtsp);

                        //Fill thành tiền, thanh toán, giảm giá
                        double thanhTien = 0;
                        double thanhToan = 0;
                        double giamGia = 0;
                        String cbbVoucher = cbbGiaGiam.getSelectedItem().toString();

                        for (GioHangViewModel gha : listGioHangS) {
                            thanhTien += gha.getSoLuong() * gh.getDonGia();

                        }

                        if (cbbVoucher.equals("5%")) {
                            giamGia = 0.95;
                        } else if (cbbVoucher.equals("10%")) {
                            giamGia = 0.90;
                        } else if (cbbVoucher.equals("15%")) {
                            giamGia = 0.85;
                        } else if (cbbVoucher.equals("20%")) {
                            giamGia = 0.80;
                        } else if (cbbVoucher.equals("25%")) {
                            giamGia = 0.75;
                        } else if (cbbVoucher.equals("30%")) {
                            giamGia = 0.70;
                        } else if (cbbVoucher.equals("35%")) {
                            giamGia = 0.65;
                        } else if (cbbVoucher.equals("40%")) {
                            giamGia = 0.60;
                        } else if (cbbVoucher.equals("45%")) {
                            giamGia = 0.55;
                        } else {
                            giamGia = 0.5;
                        }

                        lblThanhTien.setText(String.valueOf(fomat.format(thanhTien)));
                        lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien * giamGia)));
                    }

                }
            }

        }

    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // Tạo hóa đơn
        if (demTrangThai() > 3) {
            btnTaoHoaDon.setEnabled(false);
        }
        // Dùng cả random + listSize để không bị trùng
        Random random = new Random();
        int x = random.nextInt(10);
        int i = listFullHD.size();
        i++;
        long millis = System.currentTimeMillis();
        String maHD = "HD" + x + i;
        HoaDonViewModel hd = new HoaDonViewModel();
        hd.setKh("E5967694-CF90-4C64-900A-7214F307AB75");
        hd.setNv("1629D824-D841-4BFD-9A6A-263CCCF68FD8");
        hd.setMa(maHD);
        hd.setNgayTao(new Date(millis));
        hd.setTrangThai(1);
        //Lưu hóa đơn tạo vào bảng hóa đơn
        bhs.saveHoaDon(hd);
        //Hóa đơn chờ
        listHoaDons = bhs.getHoaDon();

        //lấy listSize HD dầy đủ
        listFullHD = bhs.getHoaDonFull();
        loadDataHoaDon(listHoaDons);


    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int index = tblHoaDon.getSelectedRow();
        HoaDonViewModel hd = listHoaDons.get(index);
        String idHD = hd.getId();
        listGioHangS = bhs.getGioHang(idHD);
        showDataGioHang(listGioHangS);
//        if (hd.getTrangThai() == 1) {
//            btnThanhToan.setEnabled(true);
//        } else {
//            btnThanhToan.setEnabled(false);
//        }
//        if (hd.getTrangThai() == 0 || hd.getTrangThai() == 3) {
//            btnHuyHoaDon.setEnabled(false);
//        } else {
//            btnHuyHoaDon.setEnabled(true);
//        }
        double thanhTien = 0;
        double thanhToan = 0;
        double giamGia = 0;
        String cbbVoucher = cbbGiaGiam.getSelectedItem().toString();
        for (GioHangViewModel gh : listGioHangS) {
            thanhTien += gh.getSoLuong() * gh.getDonGia();
        }
        if (cbbVoucher.equals("5%")) {
            giamGia = 0.95;
        } else if (cbbVoucher.equals("10%")) {
            giamGia = 0.90;
        } else if (cbbVoucher.equals("15%")) {
            giamGia = 0.85;
        } else if (cbbVoucher.equals("20%")) {
            giamGia = 0.80;
        } else if (cbbVoucher.equals("25%")) {
            giamGia = 0.75;
        } else if (cbbVoucher.equals("30%")) {
            giamGia = 0.70;
        } else if (cbbVoucher.equals("35%")) {
            giamGia = 0.65;
        } else if (cbbVoucher.equals("40%")) {
            giamGia = 0.60;
        } else if (cbbVoucher.equals("45%")) {
            giamGia = 0.55;
        } else {
            giamGia = 0.5;
        }
        lblThanhTien.setText(String.valueOf(fomat.format(thanhTien)));
        lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien * giamGia)));
        fillDataHD(index);

        txtTienKhachDua.setText("0");
        lblTienThua.setText("0");
        txtHoaDonPDF.setText("");
        btnThanhToan.setEnabled(true);
        btnHuyHoaDon.setEnabled(true);
        btnLamMoi.setEnabled(true);


    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        var tempTT = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn làm mới không ?");
        if (tempTT == 0) {
            txtTienKhachDua.setText("0");
        }
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThemKHBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHBHActionPerformed
        DailogKhachHangBH dkh = new DailogKhachHangBH(this, true);
        dkh.setVisible(true);
    }//GEN-LAST:event_btnThemKHBHActionPerformed

    private void btnThayDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiActionPerformed
        int indexNV = cbbNhanVienBH.getSelectedIndex();
        NhanVienCustomModel nv = listNV.get(indexNV);

        int indexKHSDT = cbbSoDienThoai.getSelectedIndex();
        KhachHangCustomModel khSDT = listKH.get(indexKHSDT);

        String ma = lblMaHD.getText();
        if (ma.equals("HD___")) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn thay đổi");
        } else {
            HoaDonCustomModel hdUpdate2 = new HoaDonCustomModel(khSDT.getId(), nv.getId());
            JOptionPane.showMessageDialog(this, bhs.updateNVKH(hdUpdate2, ma));
            listHoaDons = bhs.getHoaDon();
            loadDataHoaDon(listHoaDons);
        }

    }//GEN-LAST:event_btnThayDoiActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        long millis = System.currentTimeMillis();
        Date ngayThanhToan = new Date(millis);
        String ngayTaoHoaDOn = lblNgayTao.getText();
        String tenKH = txtTenKhachHangBH.getText();
        String sdtKH = cbbSoDienThoai.getSelectedItem().toString();
        String thanhToan = lblThanhToan.getText();
        
        String tienKhachDua = txtTienKhachDua.getText();
        String tienThua = lblTienThua.getText();
        int temp = 3;

        int soLuong = 0;
        for (GioHangViewModel gh : listGioHangS) {
            soLuong += gh.getSoLuong();
        }

        if (lblThanhTien.getText().equals("0.0")) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm trước khi thanh toán");
        } else if (txtTienKhachDua.getText().equals("0") || txtTienKhachDua.getText().matches("\\s+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
            txtTienKhachDua.setText("0");
        } else {
            var tempTT = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thanh toán không ?");
            if (tempTT == 0) {
                String maHd = lblMaHD.getText();
                HoaDonViewModel hd = new HoaDonViewModel();
                hd.setNgayThanhToan(new Date(millis));
                hd.setTongTien(Double.valueOf(thanhToan.replace(",","")));
                hd.setTongSanPham(soLuong);
                hd.setTrangThai(3);

                JOptionPane.showMessageDialog(this, bhs.updateTrangThai(hd, maHd));

                listHoaDons = bhs.getHoaDon();
                loadDataHoaDon(listHoaDons);

                listGioHangS = bhs.getGioHang(maHd);
                showDataGioHang(listGioHangS);
                txtHoaDonPDF.append("\nSHOP 6G SNEAKER\n"
                        + "\n              Hóa Đơn Thanh Toán \n"
                        + "---------------------------------------------------\n"
                        + "Ngày thanh toán:    " + ngayThanhToan + "\n"
                        + "Tên khách hàng:    " + tenKH + "\n"
                        + "Số điện thoại:         " + sdtKH + "\n"
                        + "Thành tiền:             " + thanhToan + "   VNĐ" + "\n"
                        + "Tiền khách đưa:     " + tienKhachDua + "   VNĐ" + "\n"
                        + "Tiền thừa:              " + tienThua + "   VNĐ" + "\n"
                        + "---------------------------------------------------\n"
                        + "              Cảm Ơn Quý Khách\n"
                );
                temp = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không");
                btnThanhToan.setEnabled(false);
                btnHuyHoaDon.setEnabled(false);
                btnLamMoi.setEnabled(false);

            }
        }
        if (temp == 0) {
            try {
                txtHoaDonPDF.print();

            } catch (PrinterException ex) {
                Logger.getLogger(ViewBanHang.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            lblMaHD.setText("Tạo");
            lblThanhTien.setText("0");
            cbbGiaGiam.setSelectedItem("0");
            lblThanhToan.setText("0");
            txtTienKhachDua.setText("0");
            lblTienThua.setText("0");

        }
        if (demTrangThai() < 5) {
            btnTaoHoaDon.setEnabled(true);
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtTimKiemDSSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemDSSPCaretUpdate
        listSanPhams = bhs.SearchSPBH(txtTimKiemDSSP.getText());
        showDataSanPham(listSanPhams);
    }//GEN-LAST:event_txtTimKiemDSSPCaretUpdate

    private void btnReloadBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadBHActionPerformed
        listKH = khs.getAllCustom();
        int itemCount3 = cbbSoDienThoai.getItemCount();
        for (int i = 0; i < itemCount3; i++) {
            cbbSoDienThoai.removeItemAt(0);
        }
        listKH.forEach((kh) -> {
            cbbSoDienThoai.addItem(kh.getSdt());
        });

    }//GEN-LAST:event_btnReloadBHActionPerformed

    private void btnHuyHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyHoaDonActionPerformed
        int index = tblHoaDon.getSelectedRow();
        int sl = listGioHangS.size();

        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn hủy");
        } else {
            var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy hóa đơn không ?");
            if (temp == 0) {
                if (listHoaDons != null) {
                    for (GioHangViewModel ghu : listGioHangS) {
                        int soLuongGH = ghu.getSoLuong();
                        String idCTSP = ghu.getIdCtsp();
                        ChiTietSanPhamHiber ctsp = new ChiTietSanPhamHiber(soLuongGH);
                        bhs.capNhatSoLuong(ctsp, idCTSP);

                    }
                    HoaDonViewModel hdid = listHoaDons.get(index);
                    String idHD = hdid.getId();
                    bhs.deleteHDCT(idHD);
                    bhs.deleteHD(idHD);
                    listSanPhams = bhs.getSanPhamVM();
                    listHoaDons = bhs.getHoaDon();
                    listGioHangS = bhs.getGioHang(idHD);
                    loadDataHoaDon(listHoaDons);
                    showDataSanPham(listSanPhams);
                    showDataGioHang(listGioHangS);
//                    btnThanhToan.setEnabled(false);
                    if (demTrangThai() < 6) {
                        btnTaoHoaDon.setEnabled(true);
                    }
                    lblMaHD.setText("Tạo");
                    lblThanhTien.setText("0");
                    cbbGiaGiam.setSelectedItem("0");
                    lblThanhToan.setText("0");
                    txtTienKhachDua.setText("0");
                    lblTienThua.setText("0");
                }
            }
        }

    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void btnXoaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSanPhamActionPerformed
        int indexHD = tblHoaDon.getSelectedRow();
        int indexGH = tblGioHang.getSelectedRow();
        if (indexGH < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");

        } else {

            GioHangViewModel gh = listGioHangS.get(indexGH);
            HoaDonViewModel hd = listHoaDons.get(indexHD);
            int soLuongGH = gh.getSoLuong();
            String idHD = hd.getId();

            String id = gh.getId();
            String idCTSP = gh.getIdCtsp();
            var tempTT = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa sản phẩm khỏi giỏ hàng không ?");
            if (tempTT == 0) {

                ChiTietSanPhamHiber ctsp = new ChiTietSanPhamHiber(soLuongGH);
                bhs.capNhatSoLuong(ctsp, idCTSP);
                JOptionPane.showMessageDialog(this, bhs.deleteGioHang(id));
                listSanPhams = bhs.getSanPhamVM();
                listGioHangS = bhs.getGioHang(idHD);

                showDataGioHang(listGioHangS);
                showDataSanPham(listSanPhams);
                double thanhTien = 0;
                double thanhToan = 0;
                double giamGia = 0;
                String cbbVoucher = cbbGiaGiam.getSelectedItem().toString();
                for (GioHangViewModel gha : listGioHangS) {
                    thanhTien += gha.getSoLuong() * gha.getDonGia();
                }
                if (cbbVoucher.equals("5%")) {
                    giamGia = 0.95;
                } else if (cbbVoucher.equals("10%")) {
                    giamGia = 0.90;
                } else if (cbbVoucher.equals("15%")) {
                    giamGia = 0.85;
                } else if (cbbVoucher.equals("20%")) {
                    giamGia = 0.80;
                } else if (cbbVoucher.equals("25%")) {
                    giamGia = 0.75;
                } else if (cbbVoucher.equals("30%")) {
                    giamGia = 0.70;
                } else if (cbbVoucher.equals("35%")) {
                    giamGia = 0.65;
                } else if (cbbVoucher.equals("40%")) {
                    giamGia = 0.60;
                } else if (cbbVoucher.equals("45%")) {
                    giamGia = 0.55;
                } else {
                    giamGia = 0.5;
                }
                lblThanhTien.setText(String.valueOf(fomat.format(thanhTien)));
                lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien * giamGia)));

            }
        }

    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnCapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPActionPerformed
        int indexHD = tblHoaDon.getSelectedRow();
        int indexGH = tblGioHang.getSelectedRow();
        if (indexGH < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần cập nhật số lượng");

        } else {
            String soLuongMoi = JOptionPane.showInputDialog("Mời nhập số lượng cần cập nhật: ");
            if (soLuongMoi != null) {
                if (!soLuongMoi.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(this, "Nhập đúng định dạng");
                } else {
                    GioHangViewModel gh = listGioHangS.get(indexGH);
                    HoaDonViewModel hd = listHoaDons.get(indexHD);
                    String idCTSP = gh.getIdCtsp();
                    String id = gh.getId();
                    String idHD = hd.getId();
                    int soLuongCu = gh.getSoLuong();
                    int soLuongCapNhat = 0;
                    if (Integer.valueOf(soLuongMoi) < soLuongCu) {
                        soLuongCapNhat = soLuongCu - Integer.valueOf(soLuongMoi);
                        ChiTietSanPhamHiber ctsp = new ChiTietSanPhamHiber(soLuongCapNhat);
                        bhs.capNhatSoLuong(ctsp, idCTSP);
                    } else {
                        soLuongCapNhat = Integer.valueOf(soLuongMoi) - soLuongCu;
                        ChiTietSanPhamHiber ctsp = new ChiTietSanPhamHiber(soLuongCapNhat);
                        bhs.capNhatSoLuong2(ctsp, idCTSP);
                    }
                    gh.setSoLuong(Integer.valueOf(soLuongMoi));
                    bhs.updateSoLuongHDCT(gh, id);
                    listGioHangS = bhs.getGioHang(idHD);
                    showDataGioHang(listGioHangS);
                    listSanPhams = bhs.getSanPhamVM();
                    showDataSanPham(listSanPhams);
                    double thanhTien = 0;
                    double thanhToan = 0;
                    double giamGia = 0;
                    String cbbVoucher = cbbGiaGiam.getSelectedItem().toString();
                    for (GioHangViewModel gha : listGioHangS) {
                        thanhTien += gha.getSoLuong() * gha.getDonGia();
                    }
                    if (cbbVoucher.equals("5%")) {
                        giamGia = 0.95;
                    } else if (cbbVoucher.equals("10%")) {
                        giamGia = 0.90;
                    } else if (cbbVoucher.equals("15%")) {
                        giamGia = 0.85;
                    } else if (cbbVoucher.equals("20%")) {
                        giamGia = 0.80;
                    } else if (cbbVoucher.equals("25%")) {
                        giamGia = 0.75;
                    } else if (cbbVoucher.equals("30%")) {
                        giamGia = 0.70;
                    } else if (cbbVoucher.equals("35%")) {
                        giamGia = 0.65;
                    } else if (cbbVoucher.equals("40%")) {
                        giamGia = 0.60;
                    } else if (cbbVoucher.equals("45%")) {
                        giamGia = 0.55;
                    } else {
                        giamGia = 0.5;
                    }
                    lblThanhTien.setText(String.valueOf(fomat.format(thanhTien)));
                    lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien * giamGia)));

                }
            }
        }
    }//GEN-LAST:event_btnCapNhatSPActionPerformed

    private void cbbSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSoDienThoaiActionPerformed
        int index = cbbSoDienThoai.getSelectedIndex();
        KhachHangCustomModel kh = listKH.get(index);
        txtTenKhachHangBH.setText(kh.getHoTen());

    }//GEN-LAST:event_cbbSoDienThoaiActionPerformed

    private void cbbDSPBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDSPBHActionPerformed
        listSanPhams = bhs.SearchSPBH(cbbDSPBH.getSelectedItem().toString());
        showDataSanPham(listSanPhams);
    }//GEN-LAST:event_cbbDSPBHActionPerformed

    private void cbbDGBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDGBHActionPerformed
        listSanPhams = bhs.SearchSPBH(cbbDGBH.getSelectedItem().toString());
        showDataSanPham(listSanPhams);
    }//GEN-LAST:event_cbbDGBHActionPerformed

    private void cbbMSBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMSBHActionPerformed
        listSanPhams = bhs.SearchSPBH(cbbMSBH.getSelectedItem().toString());
        showDataSanPham(listSanPhams);
    }//GEN-LAST:event_cbbMSBHActionPerformed

    private void cbbGiaGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGiaGiamActionPerformed
        int index = cbbGiaGiam.getSelectedIndex();
        VoucherCustomModel kh = listVoucherCM.get(index);
        lblTenVoucher.setText(kh.getTen());
        double thanhTien = 0;
        double thanhToan = 0;
        double giamGia = 0;
        String cbbVoucher = cbbGiaGiam.getSelectedItem().toString();
        for (GioHangViewModel gha : listGioHangS) {
            thanhTien += gha.getSoLuong() * gha.getDonGia();
        }
        if (cbbVoucher.equals("5%")) {
            giamGia = 0.95;
        } else if (cbbVoucher.equals("10%")) {
            giamGia = 0.90;
        } else if (cbbVoucher.equals("15%")) {
            giamGia = 0.85;
        } else if (cbbVoucher.equals("20%")) {
            giamGia = 0.80;
        } else if (cbbVoucher.equals("25%")) {
            giamGia = 0.75;
        } else if (cbbVoucher.equals("30%")) {
            giamGia = 0.70;
        } else if (cbbVoucher.equals("35%")) {
            giamGia = 0.65;
        } else if (cbbVoucher.equals("40%")) {
            giamGia = 0.60;
        } else if (cbbVoucher.equals("45%")) {
            giamGia = 0.55;
        } else {
            giamGia = 0.5;
        }
        lblThanhTien.setText((String.valueOf(fomat.format(thanhTien))));
        lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien * giamGia)));
    }//GEN-LAST:event_cbbGiaGiamActionPerformed

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
            java.util.logging.Logger.getLogger(ViewHoaDonCho.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewHoaDonCho.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewHoaDonCho.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewHoaDonCho.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewHoaDonCho().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhatSP;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnReloadBH;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThayDoi;
    private javax.swing.JButton btnThemKHBH;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JComboBox<String> cbbDGBH;
    private javax.swing.JComboBox<String> cbbDSPBH;
    private javax.swing.JComboBox<String> cbbGiaGiam;
    private javax.swing.JComboBox<String> cbbMSBH;
    private javax.swing.JComboBox<String> cbbNhanVienBH;
    private javax.swing.JComboBox<String> cbbSoDienThoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelTT;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblTenVoucher;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtHoaDonPDF;
    private javax.swing.JTextField txtTenKhachHangBH;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiemDSSP;
    // End of variables declaration//GEN-END:variables
}
