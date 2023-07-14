/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import customModel.VoucherCustomModel;
import domainModel.Voucher;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.impl.VoucherServiceImpl;
import utilities.Utility;

/**
 *
 * @author ADMIN
 */
public class ViewVoucher extends javax.swing.JFrame {

    private DefaultTableModel tblModelVoucher = new DefaultTableModel();
    private List<Voucher> listVoucher = new ArrayList<>();
    private List<VoucherCustomModel> listVoucherCM = new ArrayList<>();
    private VoucherServiceImpl vcs = new VoucherServiceImpl();
    private utilities.Utility uti = new Utility();

    public ViewVoucher() {
        initComponents();
        String headerVC[] = {"Mã Voucher", "Chương trình", "Giảm giá", "Ngày bắt đầu", "Ngày kết thức", "Trạng thái", "Ghi chú"};
        tblVoucher.setModel(tblModelVoucher);
        tblModelVoucher.setColumnIdentifiers(headerVC);
        listVoucherCM = vcs.getAllCustom();
        loadData(listVoucherCM);
    }

    private void loadData(List<VoucherCustomModel> lists) {
        tblModelVoucher.setRowCount(0);
        for (VoucherCustomModel x : lists) {
            tblModelVoucher.addRow(x.toRowData());
        }
    }

    private void fillDataVoucher(int index) {
        VoucherCustomModel v = listVoucherCM.get(index);
        txtMaVoucher.setText(v.getMa());
        txtTenVoucher.setText(v.getTen());
        txtNgayBatDau.setDate(v.getNgayBatDau());
        txtNgayHetHan.setDate(v.getNgayHetHan());

        txtGhiChuVoucher.setText(v.getGhiChu());
        String gg = null;
        if (v.getGiamGia() == 0.95) {
            gg = "5%";
        } else if (v.getGiamGia() == 0.9) {
            gg = "10%";
        } else if (v.getGiamGia() == 0.85) {
            gg = "15%";
        } else if (v.getGiamGia() == 0.8) {
            gg = "20%";
        } else if (v.getGiamGia() == 0.75) {
            gg = "25%";
        } else if (v.getGiamGia() == 0.7) {
            gg = "30%";
        } else if (v.getGiamGia() == 0.65) {
            gg = "35%";
        } else if (v.getGiamGia() == 0.6) {
            gg = "40%";
        } else if (v.getGiamGia() == 0.55) {
            gg = "45%";
        } else {
            gg = "50%";
        }
        cbbMucGiamGia.setSelectedItem(gg);
        String tt = null;

        if (v.getTrangThai()
                == 0) {
            tt = "Đang áp dụng";
        } else {
            tt = "Đã hết hạn";
        }

        cbbTrangThaiVoucher.setSelectedItem(tt);

    }

    public boolean CheckDL() {
        if (uti.CheckRong(txtMaVoucher.getText())) {
            JOptionPane.showMessageDialog(this, "Mã không được để trống");
            txtMaVoucher.requestFocus();
            txtMaVoucher.setText("");
            return true;
        }
//        if (uti.CheckChu(txtMaVoucher.getText())) {
//            JOptionPane.showMessageDialog(this, "Mã không đúng kiểu dữ liệu");
//            txtMaVoucher.requestFocus();
//            txtMaVoucher.setText("");
//            return true;
//        }
        if (uti.DemChuoi(txtTenVoucher.getText()) > 100) {
            JOptionPane.showMessageDialog(this, "Mã không lớn hơn 100 ký tự");
            txtTenVoucher.requestFocus();
            txtTenVoucher.setText("");
            return true;
        }
        if (uti.DemChuoi(txtMaVoucher.getText()) > 20) {
            JOptionPane.showMessageDialog(this, "Mã không lớn hơn 20 ký tự");
            txtMaVoucher.requestFocus();
            txtMaVoucher.setText("");
            return true;
        }

        if (uti.CheckRong(txtTenVoucher.getText())) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống");
            txtTenVoucher.requestFocus();
            txtTenVoucher.setText("");
            return true;
        }
//        if (uti.CheckChu(txtTenVoucher.getText())) {
//            JOptionPane.showMessageDialog(this, "Tên không đúng kiểu dữ liệu");
//            txtTenVoucher.requestFocus();
//            txtTenVoucher.setText("");
//            return true;
//        }

        if (uti.CheckRong(String.valueOf(txtNgayBatDau.getDate()))) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được để trống");
            txtNgayBatDau.requestFocus();
            txtNgayBatDau.setDate(null);
            return true;
        }
        if (uti.CheckRong(String.valueOf(txtNgayHetHan.getDate()))) {
            JOptionPane.showMessageDialog(this, "Ngày hết hạn không được để trống");
            txtNgayHetHan.requestFocus();
            txtNgayHetHan.setDate(null);
            return true;
        }

//        if (uti.CheckRong(txtGhiChuVoucher.getText())) {
//            JOptionPane.showMessageDialog(this, "Ghi chú không được để trống");
//            txtGhiChuVoucher.requestFocus();
//            txtGhiChuVoucher.setText("");
//            return true;
//        }
//        if (uti.CheckChu(txtGhiChuVoucher.getText())) {
//            JOptionPane.showMessageDialog(this, "Ghi chú không đúng kiểu dữ liệu");
//            txtGhiChuVoucher.requestFocus();
//            txtGhiChuVoucher.setText("");
//            return true;
//        }
        if (uti.DemChuoi(txtGhiChuVoucher.getText()) > 100) {
            JOptionPane.showMessageDialog(this, "Ghi chú không lớn hơn 100 ký tự");
            txtGhiChuVoucher.requestFocus();
            txtGhiChuVoucher.setText("");
            return true;
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaVoucher = new javax.swing.JTextField();
        txtTenVoucher = new javax.swing.JTextField();
        cbbMucGiamGia = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiemVoucher = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayHetHan = new com.toedter.calendar.JDateChooser();
        cbbTrangThaiVoucher = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChuVoucher = new javax.swing.JTextArea();
        btnThemVoucher = new javax.swing.JButton();
        btnSuaVoucher = new javax.swing.JButton();
        btnClearVoucher = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Mã khuyễn mãi");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Tên chương trình khuyến mãi ( Áp dụng tất cả sản phẩm )");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Mức giá giảm (%)");

        cbbMucGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5%", "10%", "15%", "20%", "25%", "30%", "35%", "40%", "45%", "50%" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtMaVoucher)
                        .addComponent(txtTenVoucher)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbbMucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbMucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Danh sách Vouncher");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTimKiemVoucher.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemVoucherCaretUpdate(evt);
            }
        });

        tblVoucher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
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
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVoucher);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtTimKiemVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel3.setText("Tạo chương trình khuyến mãi");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel6.setText("Ngày hết hạn");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel7.setText("Ngày bắt đầu");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel8.setText("Trạng thái");

        cbbTrangThaiVoucher.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbbTrangThaiVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang áp dụng", "Đã hết hạn" }));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel9.setText("Ghi chú");

        txtGhiChuVoucher.setColumns(20);
        txtGhiChuVoucher.setRows(5);
        jScrollPane2.setViewportView(txtGhiChuVoucher);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayHetHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbTrangThaiVoucher, 0, 160, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbTrangThaiVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnThemVoucher.setBackground(new java.awt.Color(255, 0, 51));
        btnThemVoucher.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThemVoucher.setForeground(new java.awt.Color(255, 255, 255));
        btnThemVoucher.setText("Thêm");
        btnThemVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVoucherActionPerformed(evt);
            }
        });

        btnSuaVoucher.setBackground(new java.awt.Color(255, 0, 51));
        btnSuaVoucher.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSuaVoucher.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaVoucher.setText("Sửa");
        btnSuaVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaVoucherActionPerformed(evt);
            }
        });

        btnClearVoucher.setBackground(new java.awt.Color(255, 0, 51));
        btnClearVoucher.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnClearVoucher.setForeground(new java.awt.Color(255, 255, 255));
        btnClearVoucher.setText("Clear");
        btnClearVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearVoucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnThemVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                        .addComponent(btnSuaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(43, 43, 43)
                                        .addComponent(btnClearVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClearVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVoucherActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ma = txtMaVoucher.getText();
        String ten = txtTenVoucher.getText();
        String ngayBD = sdf.format(txtNgayBatDau.getDate());
        String ngayHH = sdf.format(txtNgayHetHan.getDate());
        int trangThai = 0;
        if (cbbTrangThaiVoucher.getSelectedItem().equals("Đang áp dụng")) {
            trangThai = 0;
        } else {
            trangThai = 1;
        }
        Double mucGiam = 0.0;
        String gg = cbbMucGiamGia.getSelectedItem().toString();
        if (gg == "5%") {
            mucGiam = 0.95;
        } else if (gg == "10%") {
            mucGiam = 0.9;
        } else if (gg == "15%") {
            mucGiam = 0.85;
        } else if (gg == "20%") {
            mucGiam = 0.8;
        } else if (gg == "25%") {
            mucGiam = 0.75;
        } else if (gg == "30%") {
            mucGiam = 0.7;
        } else if (gg == "35%") {
            mucGiam = 0.65;
        } else if (gg == "40%") {
            mucGiam = 0.6;
        } else if (gg == "45%") {
            mucGiam = 0.55;
        } else if (gg == "50%") {
            mucGiam = 0.50;
        }

        String ghiChu = txtGhiChuVoucher.getText();

        boolean trung = false;
        for (VoucherCustomModel x : listVoucherCM) {
            if (x.getMa().contains(ma)) {
                trung = true;
            }
        }
        if (CheckDL() == false) {
            if (trung) {
                JOptionPane.showMessageDialog(this, "Mã không được trùng, vui lòng nhập lại");
            } else {
                var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm mới");
                if (temp == 0) {
                    Voucher v = new Voucher(ma, ten, mucGiam, Date.valueOf(ngayBD), Date.valueOf(ngayHH), ghiChu, trangThai);
                    JOptionPane.showMessageDialog(this, vcs.add(v));
                    listVoucherCM = vcs.getAllCustom();
                    loadData(listVoucherCM);
                }
            }
        }
    }

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {

    }//GEN-LAST:event_btnThemVoucherActionPerformed

    private void btnSuaVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaVoucherActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ma = txtMaVoucher.getText();
        String ten = txtTenVoucher.getText();
        String ngayBD = sdf.format(txtNgayBatDau.getDate());
        String ngayHH = sdf.format(txtNgayHetHan.getDate());
        int trangThai = 0;
        if (cbbTrangThaiVoucher.getSelectedItem().equals("Đang áp dụng")) {
            trangThai = 0;
        } else {
            trangThai = 1;
        }
        Double mucGiam = 0.0;
        String gg = cbbMucGiamGia.getSelectedItem().toString();
        if (gg == "5%") {
            mucGiam = 0.95;
        } else if (gg == "10%") {
            mucGiam = 0.9;
        } else if (gg == "15%") {
            mucGiam = 0.85;
        } else if (gg == "20%") {
            mucGiam = 0.8;
        } else if (gg == "25%") {
            mucGiam = 0.75;
        } else if (gg == "30%") {
            mucGiam = 0.7;
        } else if (gg == "35%") {
            mucGiam = 0.65;
        } else if (gg == "40%") {
            mucGiam = 0.6;
        } else if (gg == "45%") {
            mucGiam = 0.55;
        } else if (gg == "50%") {
            mucGiam = 0.50;
        }

        String ghiChu = txtGhiChuVoucher.getText();

        if (CheckDL() == false) {

            var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa");
            if (temp == 0) {
                Voucher v = new Voucher(ma, ten, mucGiam, Date.valueOf(ngayBD), Date.valueOf(ngayHH), ghiChu, trangThai);
                JOptionPane.showMessageDialog(this, vcs.update(v, ma));
                listVoucherCM = vcs.getAllCustom();
                loadData(listVoucherCM);

            }
        }
    }//GEN-LAST:event_btnSuaVoucherActionPerformed

    private void btnClearVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearVoucherActionPerformed
        txtGhiChuVoucher.setText("");
        txtMaVoucher.setText("");
        txtNgayBatDau.setDate(null);
        txtNgayHetHan.setDate(null);
        txtTimKiemVoucher.setText("");
        txtTenVoucher.setText("");
    }//GEN-LAST:event_btnClearVoucherActionPerformed

    private void txtTimKiemVoucherCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemVoucherCaretUpdate
        loadData(vcs.Search(txtTimKiemVoucher.getText()));
    }//GEN-LAST:event_txtTimKiemVoucherCaretUpdate

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        int index = tblVoucher.getSelectedRow();
        fillDataVoucher(index);
    }//GEN-LAST:event_tblVoucherMouseClicked

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
            java.util.logging.Logger.getLogger(ViewVoucher.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewVoucher.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewVoucher.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewVoucher.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewVoucher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClearVoucher;
    private javax.swing.JButton btnSuaVoucher;
    private javax.swing.JButton btnThemVoucher;
    private javax.swing.JComboBox<String> cbbMucGiamGia;
    private javax.swing.JComboBox<String> cbbTrangThaiVoucher;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTextArea txtGhiChuVoucher;
    private javax.swing.JTextField txtMaVoucher;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayHetHan;
    private javax.swing.JTextField txtTenVoucher;
    private javax.swing.JTextField txtTimKiemVoucher;
    // End of variables declaration//GEN-END:variables
}
