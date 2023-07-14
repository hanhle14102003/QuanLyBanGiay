/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import customModel.DeGiayCustomModel;
import customModel.DongSanPhamCustomModel;
import customModel.HDCTCustoModelHD;
import customModel.HoaDonChiTietCustomModel;
import customModel.HoaDonCustomModel;
import customModel.HoaDonCustomModelHD;
import customModel.KhachHangCustomModel;
import customModel.MauSacCustomModel;
import customModel.NhanVienCustomModel;
import customModel.VoucherCustomModel;
import customModelBanHang.GioHangViewModel;
import customModelBanHang.HoaDonViewModel;
import customModelBanHang.SanPhamViewModel;
import customModelDoiTra.DoiTraCustomModel;
import customModelDoiTra.HDCTDoiTraCustomModel;
import customModelDoiTra.HoaDonDoiTraCustomModel;
import domainModel.ChiTietSanPhamHiber;
import domainModel.ChucVu;
import domainModel.KhachHang;
import domainModel.NhanVien;
import domainModel.Voucher;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import service.IChiTietSanPhamService;
import service.IDeGiayService;
import service.IDongSPHiberService;
import service.IDongSPService;
import service.IKhachHangService;
import service.IMauSacHiberService;
import service.IMauSacService;
import service.INhaCungCapHiberService;
import service.INhaCungCapService;
import service.ISanPhamHiberService;
import service.ISanPhamService;
import service.impl.BanHangServiceImpl;
import service.impl.ChiTietSanPhamServiceImpl;
import service.impl.ChucVuServiceImpl;
import service.impl.DeGiayServiecImpl;
import service.impl.DoiTraServiceImpl;
import service.impl.DongSPHiberServiceImpl;
import service.impl.DongSPServiceImpl;
import service.impl.HoaDonHDServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.MauSacHiberServiecImpl;
import service.impl.MauSacServiceImpl;
import service.impl.NhaCungCapHiberServiceImpl;
import service.impl.NhaCungCapServiceImpl;
import service.impl.NhanVienServiceImpl;
import service.impl.SanPhamHiberServiceImpl;
import service.impl.SanPhamServiceImpl;
import service.impl.VoucherServiceImpl;

import utilities.Utility;

/**
 *
 * @author ADMIN
 */
public class HomeNhanVien extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private DefaultTableModel tblmodel = new DefaultTableModel();
    private List<KhachHang> listKH = new ArrayList<>();
    private List<KhachHangCustomModel> listKHCM = new ArrayList<>();
    private KhachHangServiceImpl khs = new KhachHangServiceImpl();
    private DefaultTableModel dtm = new DefaultTableModel();
    private List<NhanVien> listNhanViens = new ArrayList<>();
    private List<NhanVienCustomModel> listNhanVienCustom = new ArrayList<>();
    private NhanVienServiceImpl nhanVienServiceImpl = new NhanVienServiceImpl();
    private List<ChucVu> listChucVus = new ArrayList<>();
    private DefaultComboBoxModel dfcbCV = new DefaultComboBoxModel();
    private IDeGiayService deGiayService = new DeGiayServiecImpl();
    private IDongSPService dongSPService = new DongSPServiceImpl();
    private IKhachHangService khachHangService = new KhachHangServiceImpl();
    private IMauSacService mauSacService = new MauSacServiceImpl();
    private INhaCungCapService nhaCungCapService = new NhaCungCapServiceImpl();
    private ISanPhamService sanPhamService = new SanPhamServiceImpl();
    private IChiTietSanPhamService chiTietSanPhamService = new ChiTietSanPhamServiceImpl();
    private IDongSPHiberService dongSPHiberService = new DongSPHiberServiceImpl();
    private IMauSacHiberService mauSacHiberService = new MauSacHiberServiecImpl();
    private INhaCungCapHiberService nhaCungCapHiberService = new NhaCungCapHiberServiceImpl();
    private ISanPhamHiberService sanPhamHiberService = new SanPhamHiberServiceImpl();
    private ChucVuServiceImpl chucVuService = new ChucVuServiceImpl();
    private DefaultTableModel tblModelHoaDonBH = new DefaultTableModel();
    private DefaultTableModel tblModelHDCTBH = new DefaultTableModel();
    private List<HoaDonCustomModelHD> listHD = new ArrayList<>();
    private List<HDCTCustoModelHD> listHDCT = new ArrayList<>();
    private HoaDonHDServiceImpl hds = new HoaDonHDServiceImpl();
    private DefaultTableModel tblModelHoaDon = new DefaultTableModel();
    private DefaultTableModel tblModelGioHang = new DefaultTableModel();
    private DefaultComboBoxModel cbbModelNV = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbModelKH = new DefaultComboBoxModel();
    private DefaultTableModel tblModelSanPham = new DefaultTableModel();
    private DefaultTableModel tblModelVoucher = new DefaultTableModel();
    private List<HoaDonViewModel> listHoaDons = new ArrayList<>();
    private List<SanPhamViewModel> listSanPhams = new ArrayList<>();
    private List<SanPhamViewModel> listSoLuong = new ArrayList<>();
    private List<GioHangViewModel> listGioHangS = new ArrayList<>();
    private List<NhanVienCustomModel> listNV = new ArrayList<>();
    private List<HoaDonCustomModel> listFullHD = new ArrayList<>();
    private List<DeGiayCustomModel> ListDG = new ArrayList<>();
    private List<DongSanPhamCustomModel> listDSP = new ArrayList<>();
    private List<MauSacCustomModel> listMS = new ArrayList<>();
    private BanHangServiceImpl bhs = new BanHangServiceImpl();
    private NhanVienServiceImpl nvs = new NhanVienServiceImpl();
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private Executor executor = Executors.newSingleThreadExecutor(this);
    private DefaultTableModel tblModelHDDT = new DefaultTableModel();
    private DefaultTableModel tblModelHDCTDT = new DefaultTableModel();
    private DefaultTableModel tblModelDoiTra = new DefaultTableModel();
    private List<HoaDonDoiTraCustomModel> listHDDT = new ArrayList<>();
    private List<HDCTDoiTraCustomModel> listHDCTDT = new ArrayList<>();
    private List<DoiTraCustomModel> listDoiTra = new ArrayList<>();
    private DoiTraServiceImpl dts = new DoiTraServiceImpl();
    private final JPopupMenu ppMenu = new JPopupMenu();
    private JMenuItem mnItem = null;
    String soLuongDTBH = "";
    private utilities.Utility uti = new Utility();
    private CardLayout cardLayout;
    private List<Voucher> listVoucher = new ArrayList<>();
    private List<VoucherCustomModel> listVoucherCM = new ArrayList<>();
    private List<VoucherCustomModel> listVoucherCMTT = new ArrayList<>();
    private VoucherServiceImpl vcs = new VoucherServiceImpl();
    private static final long serialVersionUID = 6441489157408381878L;
    DecimalFormat fomat = new DecimalFormat("###,###,###");

    public HomeNhanVien() {
        initComponents();
        cardLayout = (CardLayout) (pnlcards.getLayout());

        showData(listKHCM = khs.getAllCustom());
        setTitle("Hệ thống quản lý bán giày");
        setLocationRelativeTo(null);
        setResizable(false);

        listNhanVienCustom = nhanVienServiceImpl.getAllCustom();

        String headersss[] = {"Mã hóa đơn", "Mã nhân viên", "Tên nhân viên", "Mã khách hàng", "Tên khách hàng", "Tổng tiền", "Tổng sản phẩm", "Trạng Thái"};
        tblModelHoaDonBH.setColumnIdentifiers(headersss);
        String headers[] = {"Mã Sản phẩm", "Tên Sản phẩm", "Số lượng", "Đon giá", "Thành tiền"};
        tblModelHDCTBH.setColumnIdentifiers(headers);
        tblHoaDonBanHang.setModel(tblModelHoaDonBH);
        tblHoaDonChiTietBH.setModel(tblModelHDCTBH);
        listHD = hds.getHoaDon();
        showDataHD(listHD);
        initWedcam();
        String headerHDBH[] = {"Mã HĐ", "Ngày tạo", "Nhân viên tạo", "Khách hàng", "Tình trạng"};
        tblModelHoaDon.setColumnIdentifiers(headerHDBH);
        String headerHDCTBH[] = {"Mã SP", "Tên SP", "Số luọng", "Đon giá",};
        tblModelGioHang.setColumnIdentifiers(headerHDCTBH);
        String headerSPBH[] = {"Mã SP", "Tên SP", "Dòng sản phẩm", "Đế giày", "Mau sắc", "Đơn giá", "Số lượng", "Xuất xứ", "Size"};
        tblModelSanPham.setColumnIdentifiers(headerSPBH);
        tblHoaDon.setModel(tblModelHoaDon);
        tblGioHang.setModel(tblModelGioHang);
        tblSanPham.setModel(tblModelSanPham);
        listSanPhams = bhs.getSanPhamVM();
        listHoaDons = bhs.getHoaDon();
        listFullHD = bhs.getHoaDonFull();

        AutoCompleteDecorator.decorate(cbbNhanVienBH);
        AutoCompleteDecorator.decorate(cbbSoDienThoai);
        listNV = nvs.getAllCustomByMaNV();
        listNV.forEach((nv) -> {
            cbbNhanVienBH.addItem(nv.getHoTen());
        });
        cbbNhanVienBH.setSelectedItem("Tên nhân viên");

        listKHCM = khs.getAllCustom();
        listKHCM.forEach((kh) -> {
            cbbSoDienThoai.addItem(kh.getSdt());
        });
        cbbSoDienThoai.setSelectedItem("(84+)");
        showDataSanPham(listSanPhams);
        loadDataHoaDon(listHoaDons);

        // trang thai taoHoaDon
        if (demTrangThai() > 4) {
            btnTaoHoaDon.setEnabled(false);
        }
        //Enter txt tiền khách đưa --> tiền thừa
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double thanhToan = Double.valueOf(lblThanhToan.getText().replace(",", ""));
                String tienKhachDua = txtTienKhachDua.getText().replace(",", "");
                Double tienThua = 0.0;
                if (tienKhachDua == "0" || tienKhachDua.isEmpty()) {
                    tienThua = 0.0;
                } else {
                    tienThua = Double.valueOf(tienKhachDua) - thanhToan;
                }
                lblTienThua.setText(String.valueOf(fomat.format(tienThua)));
            }
        };
        txtTienKhachDua.addActionListener(action);
        tblHoaDonDoiTra.setModel(tblModelHDDT);
        tblHDCTDoiTra.setModel(tblModelHDCTDT);
        tblDoiTra.setModel(tblModelDoiTra);
        if (txtGhiChu.getText().isEmpty()) {
            btnDoiSanPham.setEnabled(false);
        } else {
            btnDoiSanPham.setEnabled(true);
        }
        String headerHDDT[] = {"Mã HĐ", "Tên nhân viên", "Tên khách hàng", "Sđt", "Ngày thanh toán", "Hạn đổi"};
        tblModelHDDT.setColumnIdentifiers(headerHDDT);
        String headersHDCTDT[] = {"Mã SP", "Tên SP", "Dòng SP", "Đế giầy", "Màu sắc", "Size", "Số luọng"};
        tblModelHDCTDT.setColumnIdentifiers(headersHDCTDT);
        String headerDT[] = {"Mã HĐ", "Tên khách hàng", "Sản phẩm đổi", "Số lượng"};
        tblModelDoiTra.setColumnIdentifiers(headerDT);
        listHDDT = dts.getHoaDonDoiTra();
        showDataHDDoiTra(listHDDT);

        listVoucherCM = vcs.getAllCustom();
        listVoucherCMTT = vcs.getVoucherTrangThai();
        listVoucherCMTT.forEach((vc) -> {
            cbbGiaGiam.addItem(String.valueOf(vc.giamGiaTT()));
        });

    }

    private void showDataHDDoiTra(List<HoaDonDoiTraCustomModel> lists) {
        tblModelHDDT.setRowCount(0);
        for (HoaDonDoiTraCustomModel x : lists) {
            tblModelHDDT.addRow(x.toRowData());
        }
    }

    private void showDataHDCTDoiTra(List<HDCTDoiTraCustomModel> listss) {
        tblModelHDCTDT.setRowCount(0);
        for (HDCTDoiTraCustomModel x : listss) {
            tblModelHDCTDT.addRow(x.toRowData());
        }
    }

    private void showDataDoiTra(List<DoiTraCustomModel> listsss) {
        tblModelDoiTra.setRowCount(0);
        for (DoiTraCustomModel x : listsss) {
            tblModelDoiTra.addRow(x.toRowData());
        }
    }

    private void initWedcam() {
        java.awt.Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);
        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        pnlWebcam.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 170));
        executor.execute(this);
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
//                Logger.getLogger(HomeNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
            Result result = null;
            BufferedImage image = null;
            if (webcam.isOpen()) {
                if ((image = webcam.getImage()) == null) {
                    continue;
                }
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bb = new BinaryBitmap(new HybridBinarizer(source));

            try {
                result = new MultiFormatReader().decode(bb);
            } catch (NotFoundException ex) {
                //No result
            }

            if (result != null) {
                JOptionPane.showMessageDialog(this, "Sản phẩm: " + result.getText());
                listSanPhams = bhs.SearchSPBH(result.getText());
                showDataSanPham(listSanPhams);

            }
        } while (true);
    }

    //Load Table
    private void loadDataHoaDon(List<HoaDonViewModel> listHoaDons) {
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

    }
    // Fill data 

    private void fillDataHD(int index) {
        HoaDonViewModel hd = listHoaDons.get(index);
        lblMaHD.setText(hd.getMa());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        cbbNhanVienBH.setSelectedItem(hd.getNv());
        cbbSoDienThoai.setSelectedItem(hd.getSdt());
        lblNgayTao.setText(sdf.format(hd.getNgayTao()));

    }

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

    private void showDataHD(List<HoaDonCustomModelHD> lists) {
        tblModelHoaDonBH.setRowCount(0);
        for (HoaDonCustomModelHD x : lists) {
            tblModelHoaDonBH.addRow(x.toRowData());
        }
    }

    private void showDataHDCT(List<HDCTCustoModelHD> listss) {
        tblModelHDCTBH.setRowCount(0);
        for (HDCTCustoModelHD x : listss) {
            tblModelHDCTBH.addRow(x.toRowData());
        }
    }

    private void showData(List<KhachHangCustomModel> lists) {
        tblmodel.setRowCount(0);
        int i = 1;
        for (KhachHangCustomModel kh : lists) {
            Object[] data = new Object[]{
                i++, kh.getMa(), kh.getHoTen(), kh.getSdt(), kh.getNgaySinh()
            };
            tblmodel.addRow(data);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnBanHang = new javax.swing.JButton();
        btnDoiTra = new javax.swing.JButton();
        btnHoaDonBanHang = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnKetThuc = new javax.swing.JButton();
        pnlcards = new javax.swing.JPanel();
        pnlMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenQuanLy = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        pnlDoiTra = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        btnDoiSanPham = new javax.swing.JButton();
        lblTenKHDoiTra = new javax.swing.JLabel();
        lblMaHDDoiTra = new javax.swing.JLabel();
        lblNgaDoiHangDT = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel52 = new javax.swing.JLabel();
        cbbLiDoDoi = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel53 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtTimKiemDoiTra = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblHoaDonDoiTra = new javax.swing.JTable();
        btnLoc = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        dcBatDau = new com.toedter.calendar.JDateChooser();
        jLabel78 = new javax.swing.JLabel();
        dcKetThuc = new com.toedter.calendar.JDateChooser();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblDoiTra = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tblHDCTDoiTra = new javax.swing.JTable();
        pnlHoaDon = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonChiTietBH = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtTimKiemHoaDon = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        cbbLocTrangThaiHD = new javax.swing.JComboBox<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHoaDonBanHang = new javax.swing.JTable();
        dcKetThucHD = new com.toedter.calendar.JDateChooser();
        jLabel79 = new javax.swing.JLabel();
        dcBatDauHD = new com.toedter.calendar.JDateChooser();
        jLabel80 = new javax.swing.JLabel();
        btnLocHoaDon = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        pnlBanHang = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        btnXoaSanPham = new javax.swing.JButton();
        btnCapNhatSP = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        txtTimKiemDSSP = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        cbbDSPBH = new javax.swing.JComboBox<>();
        cbbDGBH = new javax.swing.JComboBox<>();
        cbbMSBH = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        btnThayDoi = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        cbbNhanVienBH = new javax.swing.JComboBox<>();
        btnThemKHBH = new javax.swing.JButton();
        btnReloadBH = new javax.swing.JButton();
        cbbSoDienThoai = new javax.swing.JComboBox<>();
        txtTenKhachHangBH = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        labelTT = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        btnHuyHoaDon = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        lblMaHD = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        lblThanhToan = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtTienKhachDua = new javax.swing.JTextField();
        lblTienThua = new javax.swing.JLabel();
        btnTaoHoaDon = new javax.swing.JButton();
        cbbGiaGiam = new javax.swing.JComboBox<>();
        lblTenVoucher = new javax.swing.JLabel();
        btnLoadVoucher = new javax.swing.JButton();
        pnlWebcam = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(153, 0, 51));

        btnBanHang.setBackground(new java.awt.Color(255, 255, 255));
        btnBanHang.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(46, 128, 99));
        btnBanHang.setText("Bán hàng");
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnDoiTra.setBackground(new java.awt.Color(255, 255, 255));
        btnDoiTra.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        btnDoiTra.setForeground(new java.awt.Color(46, 128, 99));
        btnDoiTra.setText("Đổi trả");
        btnDoiTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiTraActionPerformed(evt);
            }
        });

        btnHoaDonBanHang.setBackground(new java.awt.Color(255, 255, 255));
        btnHoaDonBanHang.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        btnHoaDonBanHang.setForeground(new java.awt.Color(46, 128, 99));
        btnHoaDonBanHang.setText("Hóa đơn");
        btnHoaDonBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonBanHangActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(46, 128, 99));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblTitle.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("6G SNEAKER");

        btnKetThuc.setBackground(new java.awt.Color(255, 255, 255));
        btnKetThuc.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        btnKetThuc.setForeground(new java.awt.Color(46, 128, 99));
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHoaDonBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(104, 104, 104)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHoaDonBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(268, 268, 268)
                .addComponent(btnKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel1.add(jPanel2);

        jSplitPane1.setLeftComponent(jPanel1);

        pnlcards.setBackground(new java.awt.Color(51, 51, 51));
        pnlcards.setPreferredSize(new java.awt.Dimension(1171, 724));
        pnlcards.setLayout(new java.awt.CardLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setPreferredSize(new java.awt.Dimension(1148, 724));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 70)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("WELCOME");

        txtTenQuanLy.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        txtTenQuanLy.setForeground(new java.awt.Color(0, 102, 102));

        jLabel17.setFont(new java.awt.Font("Serif", 1, 55)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 0));
        jLabel17.setText("Quản lý");

        jLabel18.setFont(new java.awt.Font("Serif", 1, 55)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 0));
        jLabel18.setText("Bán giày");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Serif", 1, 55)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 0));
        jLabel19.setText("6G Sneaker");

        jLabel20.setFont(new java.awt.Font("Serif", 1, 55)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 0));
        jLabel20.setText("Hệ thống ");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addGap(412, 412, 412)
                            .addComponent(txtTenQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addGap(122, 122, 122)
                            .addComponent(jLabel20))
                        .addGroup(pnlMainLayout.createSequentialGroup()
                            .addGap(351, 351, 351)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(479, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(382, 382, 382))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(164, 164, 164))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(txtTenQuanLy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(59, 59, 59)
                .addComponent(jLabel20)
                .addGap(75, 75, 75)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(52, 52, 52)
                .addComponent(jLabel19)
                .addGap(92, 92, 92))
        );

        pnlcards.add(pnlMain, "card8");

        pnlDoiTra.setBackground(new java.awt.Color(255, 255, 255));

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel47.setText("Quản lý đổi sản phẩm");

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel48.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel48.setText("Tên khách hàng:");

        jLabel49.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel49.setText("Mã hóa đơn");

        jLabel50.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel50.setText("Ngày đổi hàng:");

        btnDoiSanPham.setBackground(new java.awt.Color(255, 204, 0));
        btnDoiSanPham.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnDoiSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiSanPham.setText("Đổi sản phẩm");
        btnDoiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiSanPhamActionPerformed(evt);
            }
        });

        lblTenKHDoiTra.setText("_____");

        lblMaHDDoiTra.setText("_____");

        lblNgaDoiHangDT.setText("_____");

        jLabel51.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel51.setText("Ghi chú:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        txtGhiChu.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtGhiChuCaretUpdate(evt);
            }
        });
        jScrollPane10.setViewportView(txtGhiChu);

        jLabel52.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel52.setText("Lí do đổi hàng:");

        cbbLiDoDoi.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbbLiDoDoi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kich Co", "Mau Sac", "Chat Luong" }));

        jButton1.setBackground(new java.awt.Color(153, 0, 0));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Hóa đơn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(133, 133, 133))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbLiDoDoi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaHDDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblTenKHDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNgaDoiHangDT, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btnDoiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(lblTenKHDoiTra))
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(lblMaHDDoiTra))
                .addGap(27, 27, 27)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(lblNgaDoiHangDT))
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(cbbLiDoDoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addComponent(jLabel51)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDoiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel53.setText("Hoàn trả");

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTimKiemDoiTra.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemDoiTraCaretUpdate(evt);
            }
        });

        tblHoaDonDoiTra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblHoaDonDoiTra.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDonDoiTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonDoiTraMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblHoaDonDoiTra);

        btnLoc.setBackground(new java.awt.Color(255, 153, 0));
        btnLoc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLoc.setForeground(new java.awt.Color(255, 255, 255));
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        jLabel77.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel77.setText("Từ ngày");

        jLabel78.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel78.setText("Đến ngày");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(txtTimKiemDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTimKiemDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnLoc)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dcBatDau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dcKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblDoiTra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblDoiTra.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane12.setViewportView(tblDoiTra);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel54.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel54.setText("Hóa đơn");

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel55.setText("Hóa đơn đổi hàng");

        jLabel56.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel56.setText("Hóa đơn chi tiết");

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblHDCTDoiTra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblHDCTDoiTra.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHDCTDoiTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHDCTDoiTraMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tblHDCTDoiTra);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDoiTraLayout = new javax.swing.GroupLayout(pnlDoiTra);
        pnlDoiTra.setLayout(pnlDoiTraLayout);
        pnlDoiTraLayout.setHorizontalGroup(
            pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoiTraLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoiTraLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel47)
                        .addGap(380, 380, 380))
                    .addGroup(pnlDoiTraLayout.createSequentialGroup()
                        .addGroup(pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54))
                        .addGap(18, 18, 18)
                        .addGroup(pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDoiTraLayout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pnlDoiTraLayout.createSequentialGroup()
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(pnlDoiTraLayout.createSequentialGroup()
                        .addGroup(pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDoiTraLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(334, 334, 334))
                            .addGroup(pnlDoiTraLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        pnlDoiTraLayout.setVerticalGroup(
            pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoiTraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addGap(12, 12, 12)
                .addGroup(pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54)
                    .addComponent(jLabel53))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel56)
                    .addGroup(pnlDoiTraLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlcards.add(pnlDoiTra, "cardDoiTra");

        pnlHoaDon.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblHoaDonChiTietBH.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblHoaDonChiTietBH);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel23.setText("Tìm kiếm hóa đơn");

        txtTimKiemHoaDon.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemHoaDonCaretUpdate(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel24.setText("Trang thái");

        cbbLocTrangThaiHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbbLocTrangThaiHD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chờ thanh toán" }));
        cbbLocTrangThaiHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLocTrangThaiHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbLocTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(cbbLocTrangThaiHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblHoaDonBanHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDonBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonBanHangMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblHoaDonBanHang);

        jLabel79.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel79.setText("Đến ngày");

        jLabel80.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel80.setText("Từ ngày");

        btnLocHoaDon.setBackground(new java.awt.Color(255, 153, 0));
        btnLocHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLocHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnLocHoaDon.setText("Lọc");
        btnLocHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane5)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(dcBatDauHD, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(151, 151, 151))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(dcKetThucHD, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLocHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80)
                            .addComponent(dcBatDauHD, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79)
                            .addComponent(dcKetThucHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLocHoaDon))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel25.setText("Hóa đơn");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel26.setText("Hóa đơn chi tiết");

        jLabel76.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 153, 0));
        jLabel76.setText("Hóa Đơn");

        javax.swing.GroupLayout pnlHoaDonLayout = new javax.swing.GroupLayout(pnlHoaDon);
        pnlHoaDon.setLayout(pnlHoaDonLayout);
        pnlHoaDonLayout.setHorizontalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHoaDonLayout.createSequentialGroup()
                .addGroup(pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlHoaDonLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlHoaDonLayout.createSequentialGroup()
                        .addGap(476, 476, 476)
                        .addComponent(jLabel76)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        pnlHoaDonLayout.setVerticalGroup(
            pnlHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHoaDonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addGap(40, 40, 40)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pnlcards.add(pnlHoaDon, "cardHoaDon");

        pnlBanHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlBanHang.setPreferredSize(new java.awt.Dimension(830, 700));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        jScrollPane6.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(11, 42, 61));
        jLabel27.setText("Hóa đơn chờ");

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(11, 42, 61));
        jLabel28.setText("Giỏ hàng");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
        jScrollPane8.setViewportView(tblGioHang);

        btnXoaSanPham.setBackground(new java.awt.Color(255, 204, 0));
        btnXoaSanPham.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnXoaSanPham.setForeground(new java.awt.Color(255, 255, 255));
        btnXoaSanPham.setText("Xóa");
        btnXoaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSanPhamActionPerformed(evt);
            }
        });

        btnCapNhatSP.setBackground(new java.awt.Color(255, 204, 0));
        btnCapNhatSP.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnCapNhatSP.setForeground(new java.awt.Color(255, 255, 255));
        btnCapNhatSP.setText("Cập nhật");
        btnCapNhatSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCapNhatSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnXoaSanPham)
                        .addGap(18, 18, 18)
                        .addComponent(btnCapNhatSP))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(11, 42, 61));
        jLabel29.setText("Danh sách sản phẩm");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setText("Tìm kiếm sản phẩm");

        txtTimKiemDSSP.setText("Tìm kiếm ...");
        txtTimKiemDSSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemDSSPCaretUpdate(evt);
            }
        });
        txtTimKiemDSSP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemDSSPFocusGained(evt);
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
        jScrollPane9.setViewportView(tblSanPham);

        cbbDSPBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbDSPBHMouseClicked(evt);
            }
        });
        cbbDSPBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDSPBHActionPerformed(evt);
            }
        });

        cbbDGBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbDGBHMouseClicked(evt);
            }
        });
        cbbDGBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbDGBHActionPerformed(evt);
            }
        });

        cbbMSBH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbMSBHMouseClicked(evt);
            }
        });
        cbbMSBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMSBHActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel31.setText("Dòng sản phẩm");

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel32.setText("Đế giày");

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setText("Màu");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane9))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(txtTimKiemDSSP))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbDSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbDGBH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMSBH, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtTimKiemDSSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbDSPBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbDGBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbMSBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel34.setText("Nhân viên:");

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setText("Khách hàng:");

        btnThayDoi.setBackground(new java.awt.Color(255, 204, 0));
        btnThayDoi.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnThayDoi.setForeground(new java.awt.Color(255, 255, 255));
        btnThayDoi.setText("Thay đổi");
        btnThayDoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThayDoiActionPerformed(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel36.setText("Số điện thoại:");

        btnThemKHBH.setBackground(new java.awt.Color(255, 0, 51));
        btnThemKHBH.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThemKHBH.setForeground(new java.awt.Color(255, 255, 255));
        btnThemKHBH.setText("+");
        btnThemKHBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHBHActionPerformed(evt);
            }
        });

        btnReloadBH.setBackground(new java.awt.Color(255, 0, 51));
        btnReloadBH.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnReloadBH.setForeground(new java.awt.Color(255, 255, 255));
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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(txtTenKhachHangBH, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnThemKHBH))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(cbbNhanVienBH, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnReloadBH))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbbSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(btnThayDoi)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(cbbNhanVienBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReloadBH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThemKHBH)
                        .addComponent(txtTenKhachHangBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(cbbSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThayDoi)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel37.setText("Mã hóa đơn: ");

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel38.setText("Ngày tạo:");

        labelTT.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        labelTT.setText("Thành tiền:");

        lblGiamGia.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        lblGiamGia.setText("Giảm giá:");

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel39.setText("Thanh toán:");

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel40.setText("Tiền khách đưa:");

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        jLabel41.setText("Tiền thừa:");

        btnHuyHoaDon.setBackground(new java.awt.Color(255, 204, 0));
        btnHuyHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnHuyHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyHoaDon.setText("Hủy hóa đơn");
        btnHuyHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyHoaDonActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(255, 204, 0));
        btnLamMoi.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        btnLamMoi.setForeground(new java.awt.Color(255, 255, 255));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(255, 204, 0));
        btnThanhToan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel43.setText("VNĐ");

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel44.setText("VNĐ");

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel45.setText("VNĐ");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel46.setText("VNĐ");

        txtTienKhachDua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtTienKhachDua.setForeground(new java.awt.Color(204, 0, 0));
        txtTienKhachDua.setText("0");

        lblTienThua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTienThua.setForeground(new java.awt.Color(204, 0, 0));
        lblTienThua.setText("0");

        btnTaoHoaDon.setBackground(new java.awt.Color(255, 204, 0));
        btnTaoHoaDon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnTaoHoaDon.setForeground(new java.awt.Color(255, 255, 255));
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

        lblTenVoucher.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTenVoucher.setText("Không có mã giảm giá");

        btnLoadVoucher.setBackground(new java.awt.Color(255, 0, 51));
        btnLoadVoucher.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLoadVoucher.setForeground(new java.awt.Color(255, 255, 255));
        btnLoadVoucher.setText("Load");
        btnLoadVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadVoucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(labelTT)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel9Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnTaoHoaDon))))))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel40)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(lblTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                            .addComponent(lblGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                    .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                    .addComponent(lblThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel9Layout.createSequentialGroup()
                                    .addComponent(cbbGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLoadVoucher)))))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(lblMaHD)
                    .addComponent(btnTaoHoaDon))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(lblNgayTao))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTT)
                    .addComponent(lblThanhTien)
                    .addComponent(jLabel43))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblTenVoucher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoadVoucher)))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblGiamGia)))
                .addGap(9, 9, 9)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lblThanhToan)
                    .addComponent(jLabel44))
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jLabel46)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel45)
                    .addComponent(lblTienThua))
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuyHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        pnlWebcam.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout pnlBanHangLayout = new javax.swing.GroupLayout(pnlBanHang);
        pnlBanHang.setLayout(pnlBanHangLayout);
        pnlBanHangLayout.setHorizontalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addGap(511, 511, 511)
                .addComponent(pnlWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(696, Short.MAX_VALUE))
            .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBanHangLayout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBanHangLayout.createSequentialGroup()
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(421, 421, 421))
                        .addGroup(pnlBanHangLayout.createSequentialGroup()
                            .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlBanHangLayout.createSequentialGroup()
                                    .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlBanHangLayout.createSequentialGroup()
                                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(243, 243, 243)))
                                    .addGap(5, 5, 5))
                                .addGroup(pnlBanHangLayout.createSequentialGroup()
                                    .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel29)
                                        .addGroup(pnlBanHangLayout.createSequentialGroup()
                                            .addGap(9, 9, 9)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(82, 82, 82)))))
        );
        pnlBanHangLayout.setVerticalGroup(
            pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBanHangLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(pnlWebcam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(736, Short.MAX_VALUE))
            .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBanHangLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel27)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(pnlBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlBanHangLayout.createSequentialGroup()
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)
                            .addComponent(jLabel28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel29)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlcards.add(pnlBanHang, "cardBH");

        jSplitPane1.setRightComponent(pnlcards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1474, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        // TODO add your handling code here:
//        ViewBanHang vbh = new ViewBanHang();
//        vbh.setVisible(true);
        cardLayout.show(pnlcards, "cardBH");

    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnDoiTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiTraActionPerformed
        // TODO add your handling code here:

        cardLayout.show(pnlcards, "cardDoiTra");

    }//GEN-LAST:event_btnDoiTraActionPerformed

    private void btnHoaDonBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonBanHangActionPerformed
        cardLayout.show(pnlcards, "cardHoaDon");

    }//GEN-LAST:event_btnHoaDonBanHangActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        ViewLogin l = new ViewLogin();
        var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắc chắn muốn đăng xuất");
        if (temp == 0) {
            webcam.close();
            l.setVisible(true);
            this.dispose();

        }

    }//GEN-LAST:event_btnLogoutActionPerformed


    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắc chắn muốn thoát");
        if (temp == 0) {
            System.exit(0);
        }

    }//GEN-LAST:event_btnKetThucActionPerformed

    private void btnDoiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiSanPhamActionPerformed
        int indexHDCT = tblHDCTDoiTra.getSelectedRow();
        int indexDT = tblDoiTra.getSelectedRow();

        DoiTraCustomModel dt = new DoiTraCustomModel();
        int indexHD = tblHoaDonDoiTra.getSelectedRow();
        if (indexHD < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn đổi sản phẩm");
        } else if (indexHDCT < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phảm muốn đổi");
        } else {
            JOptionPane.showMessageDialog(this, "Đổi sản phẩm thành công");
            listDoiTra.clear();
            showDataDoiTra(listDoiTra);
            lblMaHDDoiTra.setText("_____");
            lblNgaDoiHangDT.setText("_____");
            lblTenKHDoiTra.setText("_____");
            txtGhiChu.setText("");
            cbbLiDoDoi.setSelectedIndex(0);
        }
    }//GEN-LAST:event_btnDoiSanPhamActionPerformed

    private void txtGhiChuCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGhiChuCaretUpdate
        if (txtGhiChu.getText().isEmpty()) {
            btnDoiSanPham.setEnabled(false);
        } else {
            btnDoiSanPham.setEnabled(true);
        }
    }//GEN-LAST:event_txtGhiChuCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DailogDoiTra dld = new DailogDoiTra(this, true);
        dld.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimKiemDoiTraCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemDoiTraCaretUpdate
        listHDDT = dts.SearchHDDT(txtTimKiemDoiTra.getText());
        showDataHDDoiTra(listHDDT);
    }//GEN-LAST:event_txtTimKiemDoiTraCaretUpdate

    private void tblHoaDonDoiTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonDoiTraMouseClicked
        int index = tblHoaDonDoiTra.getSelectedRow();
        HoaDonDoiTraCustomModel hd = listHDDT.get(index);

        long millis = System.currentTimeMillis();
        Timestamp t = new Timestamp(millis);
        Date ngayTT = hd.getNgayThanhToan();
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(ngayTT);
        c2.setTime(t);
        var temp = 0;
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        if (noDay > 3) {
            temp = JOptionPane.showConfirmDialog(this, "Hóa đơn đã quá hạn đổi, bạn vẫn muốn tiếp tục đổi ?");
        }
        if (temp == 0) {
            String idHD = hd.getId();
            listHDCTDT = dts.getHDCTDoiTra(idHD);
            showDataHDCTDoiTra(listHDCTDT);
            lblTenKHDoiTra.setText(hd.getTenKH());
            lblMaHDDoiTra.setText(hd.getMaHD());
            lblNgaDoiHangDT.setText(String.valueOf(new Date(millis)));
        }
    }//GEN-LAST:event_tblHoaDonDoiTraMouseClicked

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (dcBatDau.getDate() == null || dcKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu và ngày kết thúc không được để trống");
        } else {
            java.util.Date Date = new java.util.Date();
            java.util.Date bd = (java.util.Date) dcBatDau.getDate();
            java.util.Date kt = (java.util.Date) dcKetThuc.getDate();

            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(bd);
            c2.setTime(kt);
            var temp = 0;
            long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);

            if (noDay < 0) {
                temp = JOptionPane.showConfirmDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu");
            } else {

                String batDau = sdf.format(dcBatDau.getDate());
                String ketThuc = sdf.format(dcKetThuc.getDate());
                listHDDT = dts.getHoaDonDoiTraBetween(batDau, ketThuc);
                showDataHDDoiTra(listHDDT);
            }
        }
    }//GEN-LAST:event_btnLocActionPerformed

    private void tblHDCTDoiTraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHDCTDoiTraMouseClicked
        int index = tblHDCTDoiTra.getSelectedRow();
        HDCTDoiTraCustomModel hdct = listHDCTDT.get(index);
        DoiTraCustomModel dt = new DoiTraCustomModel();
        int indexHD = tblHoaDonDoiTra.getSelectedRow();
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        if (indexHD < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn đổi sản phẩm");
        } else {
            boolean trung = false;
            for (DoiTraCustomModel x : listDoiTra) {
                if (x.getSanPhamDoi().equals(hdct.getTenSP())) {
                    trung = true;
                }
            }

            if (trung) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong hóa đơn đổi hàng, để cập nhật vui lòng xóa đi chọn lại");
            } else {

                soLuongDTBH = JOptionPane.showInputDialog("Mời nhập số lượng: ");
                if (soLuongDTBH != null) {
                    if (!soLuongDTBH.matches("[0-9]+")) {
                        JOptionPane.showMessageDialog(this, "Nhập đúng định dạng");
                    } else if (Integer.valueOf(soLuongDTBH) > hdct.getSoLuong()) {
                        JOptionPane.showMessageDialog(this, "Số lượng vượt quá");
                    } else if (txtGhiChu.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng điền ghi chú");
                    } else {
                        var chon = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn đổi sản phẩm");

                        if (chon == 0) {
                            HoaDonDoiTraCustomModel hd = listHDDT.get(indexHD);
                            dt.setMaHD(lblMaHDDoiTra.getText());
                            dt.setTenKH(lblTenKHDoiTra.getText());
                            dt.setSanPhamDoi(hdct.getTenSP());
                            dt.setSoLuongDoi(Integer.valueOf(soLuongDTBH));
                            listDoiTra.add(dt);
                            showDataDoiTra(listDoiTra);

                            HoaDonDoiTraCustomModel hdc = listHDDT.get(indexHD);
                            String idCTSP = hdct.getIdCTSP();
                            String idHD = hdc.getId();
                            String idKH = hdc.getIdKH();

                            String liDoDoi = cbbLiDoDoi.getSelectedItem().toString();
                            String ghiChu = txtGhiChu.getText();
                            HoaDonDoiTraCustomModel hdadd = new HoaDonDoiTraCustomModel(idCTSP, idHD, idKH, date, Integer.valueOf(soLuongDTBH), liDoDoi, ghiChu);
                            HDCTDoiTraCustomModel hdctadd = new HDCTDoiTraCustomModel(Integer.valueOf(soLuongDTBH));
                            dts.doiTra(hdadd);
                            dts.capNhatSoLuong(hdctadd, idCTSP);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tblHDCTDoiTraMouseClicked

    private void txtTimKiemHoaDonCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonCaretUpdate
        listHD = hds.SearchHD(txtTimKiemHoaDon.getText());
        showDataHD(listHD);
    }//GEN-LAST:event_txtTimKiemHoaDonCaretUpdate

    private void cbbLocTrangThaiHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLocTrangThaiHDActionPerformed
        String trangThai = cbbLocTrangThaiHD.getSelectedItem().toString();
        String tt = "";
        if (trangThai.equals("Đã thanh toán")) {
            tt = "3";
        } else {
            tt = "1";
        }
        listHD = hds.SearchCBB(tt);
        showDataHD(listHD);
    }//GEN-LAST:event_cbbLocTrangThaiHDActionPerformed

    private void tblHoaDonBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonBanHangMouseClicked
        int index = tblHoaDonBanHang.getSelectedRow();
        HoaDonCustomModelHD hd = listHD.get(index);
        listHDCT = hds.getHDCT(hd.getId());
        showDataHDCT(listHDCT);
    }//GEN-LAST:event_tblHoaDonBanHangMouseClicked

    private void btnLocHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocHoaDonActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (dcBatDauHD.getDate() == null || dcKetThucHD.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu và ngày kết thúc không được để trống");
        } else {
            java.util.Date Date = new java.util.Date();
            java.util.Date bd = (java.util.Date) dcBatDauHD.getDate();
            java.util.Date kt = (java.util.Date) dcKetThucHD.getDate();

            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(bd);
            c2.setTime(kt);
            var temp = 0;
            long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);

            if (noDay < 0) {
                temp = JOptionPane.showConfirmDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu");
            } else {

                String batDau = sdf.format(dcBatDauHD.getDate());
                String ketThuc = sdf.format(dcKetThucHD.getDate());
                listHD = hds.getHoaDonBetween(batDau, ketThuc);
                showDataHD(listHD);
            }
        }
    }//GEN-LAST:event_btnLocHoaDonActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int index = tblHoaDon.getSelectedRow();
        HoaDonViewModel hd = listHoaDons.get(index);
        String idHD = hd.getId();
        listGioHangS = bhs.getGioHang(idHD);
        showDataGioHang(listGioHangS);
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
        } else if (cbbVoucher.equals("50%")) {
            giamGia = 0.5;
        } else {
            giamGia = 1;
        }
        lblThanhTien.setText(String.valueOf(fomat.format(thanhTien)));
        lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien * giamGia)));
        fillDataHD(index);

        txtTienKhachDua.setText("0");
        lblTienThua.setText("0");
        btnThanhToan.setEnabled(true);
        btnHuyHoaDon.setEnabled(true);
        btnLamMoi.setEnabled(true);
    }//GEN-LAST:event_tblHoaDonMouseClicked

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
                } else if (cbbVoucher.equals("50%")) {
                    giamGia = 0.5;
                } else {
                    giamGia = 1;
                }
                lblThanhTien.setText(String.valueOf(fomat.format(thanhTien)));
                lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien * giamGia)));

            }
        }
    }//GEN-LAST:event_btnXoaSanPhamActionPerformed

    private void btnCapNhatSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatSPActionPerformed
        int indexHD = tblHoaDon.getSelectedRow();
        int indexGH = tblGioHang.getSelectedRow();
        SanPhamViewModel sp = new SanPhamViewModel();

        if (indexGH < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần cập nhật số lượng");

        } else {
            GioHangViewModel ghsl = listGioHangS.get(indexGH);
            String idSL = ghsl.getIdCtsp();
            int sl = bhs.laySoLuong(idSL);
            String soLuongMoi = JOptionPane.showInputDialog("Mời nhập số lượng cần cập nhật: ");
            if (soLuongMoi != null) {
                if (!soLuongMoi.matches("[0-9]+")) {
                    JOptionPane.showMessageDialog(this, "Nhập đúng định dạng");
                } else if (Integer.valueOf(soLuongMoi) > sl) {
                    JOptionPane.showMessageDialog(this, "Số lượng vượt quá");
                } else if (Integer.valueOf(soLuongMoi) == 0) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn chức năng xóa");
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
                    } else if (cbbVoucher.equals("50%")) {
                        giamGia = 0.5;
                    } else {
                        giamGia = 1;
                    }
                    lblThanhTien.setText(String.valueOf(fomat.format(thanhTien)));
                    lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien * giamGia)));

                }
            }
        }
    }//GEN-LAST:event_btnCapNhatSPActionPerformed

    private void txtTimKiemDSSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemDSSPCaretUpdate
        listSanPhams = bhs.SearchSPBH(txtTimKiemDSSP.getText());
        showDataSanPham(listSanPhams);
    }//GEN-LAST:event_txtTimKiemDSSPCaretUpdate

    private void txtTimKiemDSSPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemDSSPFocusGained
        txtTimKiemDSSP.setText("");
    }//GEN-LAST:event_txtTimKiemDSSPFocusGained

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        //Lấy row table --> Dữ liệu
        String tenNV = cbbNhanVienBH.getSelectedItem().toString();
        String tenKH = cbbSoDienThoai.getSelectedItem().toString();
        GioHangViewModel gh = new GioHangViewModel();
        int rowHD = tblHoaDon.getSelectedRow();
        int row = tblSanPham.getSelectedRow();
        SanPhamViewModel sp = listSanPhams.get(row);
        if (rowHD < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn muốn thêm sản phẩm");
        } else {
            String soLuong = JOptionPane.showInputDialog("Mời bạn nhập số lượng: ");
            if (tenNV.contains("Tên nhân viên") || tenKH.contains("(84+)")) {
                JOptionPane.showMessageDialog(this, "Vui lòng thay đổi tên nhân viên hoặc khách hàng");
            } else {
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
                            } else if (cbbVoucher.equals("50%")) {
                                giamGia = 0.5;
                            } else {
                                giamGia = 1;
                            }
                            lblThanhTien.setText(String.valueOf(fomat.format(thanhTien)));
                            lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien * giamGia)));

                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void cbbDSPBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbDSPBHMouseClicked
        cbbDSPBH.removeAllItems();
        listDSP = dongSPService.getAllCustom();
        listDSP.forEach((dsp) -> {
            cbbDSPBH.addItem(dsp.getTen());
        });
    }//GEN-LAST:event_cbbDSPBHMouseClicked

    private void cbbDSPBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDSPBHActionPerformed
        if (cbbDSPBH.getSelectedIndex() < 0) {
        } else {
            List<SanPhamViewModel> listNew = bhs.SearchSPBH(cbbDSPBH.getSelectedItem().toString());
            showDataSanPham(listNew);
        }
    }//GEN-LAST:event_cbbDSPBHActionPerformed

    private void cbbDGBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbDGBHMouseClicked
        cbbDGBH.removeAllItems();
        ListDG = deGiayService.getAll(null);
        ListDG.forEach((dg) -> {
            cbbDGBH.addItem(dg.getTen());
        });
    }//GEN-LAST:event_cbbDGBHMouseClicked

    private void cbbDGBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbDGBHActionPerformed
        if (cbbDGBH.getSelectedIndex() < 0) {
        } else {
            List<SanPhamViewModel> listNew = bhs.SearchSPBH(cbbDGBH.getSelectedItem().toString());
            showDataSanPham(listNew);
        }
    }//GEN-LAST:event_cbbDGBHActionPerformed

    private void cbbMSBHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbMSBHMouseClicked
        cbbMSBH.removeAllItems();
        listMS = mauSacService.getAllCustom();
        listMS.forEach((ms) -> {
            cbbMSBH.addItem(ms.getTen());
        });
    }//GEN-LAST:event_cbbMSBHMouseClicked

    private void cbbMSBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMSBHActionPerformed
        if (cbbMSBH.getSelectedIndex() < 0) {
        } else {
            List<SanPhamViewModel> listNew = bhs.SearchSPBH(cbbMSBH.getSelectedItem().toString());
            showDataSanPham(listNew);
        }
    }//GEN-LAST:event_cbbMSBHActionPerformed

    private void btnThayDoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThayDoiActionPerformed

        int indexNV = cbbNhanVienBH.getSelectedIndex();
        NhanVienCustomModel nv = listNV.get(indexNV);

        int indexKHSDT = cbbSoDienThoai.getSelectedIndex();
        KhachHangCustomModel khSDT = listKHCM.get(indexKHSDT);

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

    private void btnThemKHBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHBHActionPerformed
        DailogKhachHangBH dkh = new DailogKhachHangBH(this, true);
        dkh.setVisible(true);
    }//GEN-LAST:event_btnThemKHBHActionPerformed

    private void btnReloadBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadBHActionPerformed

        listKHCM = khs.getAllCustom();
        int itemCount3 = cbbSoDienThoai.getItemCount();
        for (int i = 0; i < itemCount3; i++) {
            cbbSoDienThoai.removeItemAt(0);
        }
        listKHCM.forEach((kh) -> {
            cbbSoDienThoai.addItem(kh.getSdt());
        });
        cbbSoDienThoai.setSelectedIndex(0);
    }//GEN-LAST:event_btnReloadBHActionPerformed

    private void cbbSoDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSoDienThoaiActionPerformed
        int index = cbbSoDienThoai.getSelectedIndex();
        KhachHangCustomModel kh = listKHCM.get(index);
        txtTenKhachHangBH.setText(kh.getHoTen());
    }//GEN-LAST:event_cbbSoDienThoaiActionPerformed

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
                    if (demTrangThai() < 6) {
                        btnTaoHoaDon.setEnabled(true);
                    }
                    lblMaHD.setText("Tạo");
                    lblThanhTien.setText("0");

                    lblThanhToan.setText("0");
                    txtTienKhachDua.setText("0");
                    lblTienThua.setText("0");
                }
            }
        }
    }//GEN-LAST:event_btnHuyHoaDonActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        var tempTT = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn làm mới không ?");
        if (tempTT == 0) {
            txtTienKhachDua.setText("0");
        }
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        long millis = System.currentTimeMillis();
        Date ngayThanhToan = new Date(millis);
        String ngayTaoHoaDOn = lblNgayTao.getText();
        String tenKH = txtTenKhachHangBH.getText();
        String sdtKH = cbbSoDienThoai.getSelectedItem().toString();
        String thanhToan = lblThanhToan.getText();
        String tienKhachDua = txtTienKhachDua.getText();
        String tienThua = lblTienThua.getText();
        String path = "";
        String tenNV = cbbNhanVienBH.getSelectedItem().toString();
        String maHD = lblMaHD.getText();
        String thanhTien = lblThanhTien.getText();
        String giamGia = cbbGiaGiam.getSelectedItem().toString();

        int temp = 3;

        int soLuong = 0;
        for (GioHangViewModel gh : listGioHangS) {
            soLuong += gh.getSoLuong();
        }

        if (lblThanhTien.getText().equals("0")) {
            JOptionPane.showMessageDialog(this, "Vui lòng thêm sản phẩm trước khi thanh toán");
        } else if (txtTienKhachDua.getText().equals("0") || txtTienKhachDua.getText().matches("\\s+")) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
            txtTienKhachDua.setText("0");
        } else if (Double.valueOf(tienKhachDua.replace(",", "")) < Double.valueOf(thanhToan.replace(",", ""))) {
            JOptionPane.showMessageDialog(this, "Thiếu tiền, vui lòng nhập lại");
        } else {
            var tempTT = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thanh toán không ?");
            if (tempTT == 0) {
                String maHd = lblMaHD.getText();
                HoaDonViewModel hd = new HoaDonViewModel();
                int indexVC = cbbGiaGiam.getSelectedIndex();
                VoucherCustomModel vc = listVoucherCMTT.get(indexVC);
                hd.setIdVoucher(vc.getId());
                hd.setNgayThanhToan(new Date(millis));
                hd.setTongTien(Double.valueOf(thanhToan.replace(",", "")));
                hd.setTongSanPham(soLuong);
                hd.setTrangThai(3);

                JOptionPane.showMessageDialog(this, bhs.updateTrangThai(hd, maHd));

                listHoaDons = bhs.getHoaDon();
                loadDataHoaDon(listHoaDons);

                temp = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không");
                btnThanhToan.setEnabled(false);
                btnHuyHoaDon.setEnabled(false);
                btnLamMoi.setEnabled(false);

            }
        }
        if (temp == 0) {
            JFileChooser j = new JFileChooser();
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int x = j.showSaveDialog(this);
            if (x == JFileChooser.APPROVE_OPTION) {
                path = j.getSelectedFile().getPath();
            }

            Document doc = new Document();

            try {
                PdfWriter pw = PdfWriter.getInstance(doc, new FileOutputStream(path + "\\" + maHD + ".pdf"));
                    doc.open();
                    doc.add(new Paragraph("                                                              SHOP GIÀY 6G SNEAKER\n"));
                    doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                    doc.add(new Paragraph("Ngày thanh toán:          " + ngayThanhToan.toString()));
                    doc.add(new Paragraph("Tên nhân viên:              " + tenNV));
                    doc.add(new Paragraph("Tên khách hàng:           " + tenKH));
                    doc.add(new Paragraph("So dien thoai:               " + sdtKH));
                    doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                    PdfPTable tbl = new PdfPTable(5);
                    tbl.setWidthPercentage(100);
                    tbl.setSpacingBefore(11f);
                    tbl.setSpacingAfter(11f);

                    float[] col = {2f, 2f, 2f, 2f, 2f};
                    tbl.setWidths(col);

                    tbl.addCell("Ma San Pham");
                    tbl.addCell("Ten San Pham");
                    tbl.addCell("So Luong");
                    tbl.addCell("Don Gia");
                    tbl.addCell("Tong Tien");

                    for (int i = 0; i < tblGioHang.getRowCount(); i++) {
                        String ma = tblGioHang.getValueAt(i, 0).toString();
                        String ten = tblGioHang.getValueAt(i, 1).toString();
                        String sl = tblGioHang.getValueAt(i, 2).toString();
                        String dg = tblGioHang.getValueAt(i, 3).toString();
                        Double tt = Double.valueOf(dg) * Integer.valueOf(sl);

                        tbl.addCell(ma);
                        tbl.addCell(ten);
                        tbl.addCell(sl);
                        tbl.addCell(fomat.format(Double.valueOf((dg))) + " VND");
                        tbl.addCell(fomat.format(Double.valueOf((tt))) + " VND");
                    }
                    doc.add(tbl);
                    doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                    doc.add(new Paragraph("Thanh tien: " + "                                                                                                           " + (thanhTien) + " VND"));
                    doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                    doc.add(new Paragraph("Giam gia: " + "                                                                                                             " + (giamGia)));
                    doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                    doc.add(new Paragraph("Thanh toán: " + "                                                                                                          " + (thanhToan) + " VND"));
                    doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                    doc.add(new Paragraph("Tien khach dua: " + "                                                                                                    " + fomat.format(Double.valueOf(tienKhachDua)) + " VND"));
                    doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                    doc.add(new Paragraph("Tien thua: " + "                                                                                                              " + (tienThua) + " VND"));
                    doc.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
                    doc.add(new Paragraph("****************************************************************************************************************"));
                    doc.add(new Paragraph("                                                           THANK YOU COME AGAIN"));
                    doc.add(new Paragraph("****************************************************************************************************************"));
                    doc.add(new Paragraph("                                                           SOFTWARE BY NHOM4_IT17320"));
                    doc.add(new Paragraph("                                                           CONTACT: g6sneaker@it17320.com"));
                    doc.close();
                    pw.close();
                    listGioHangS = bhs.getGioHang(maHD);
                    showDataGioHang(listGioHangS);
                    lblMaHD.setText("Tạo");
                    lblThanhTien.setText("0");

                    lblThanhToan.setText("0");
                    txtTienKhachDua.setText("0");
                    lblTienThua.setText("0");

                } catch (DocumentException ex) {
                    Logger.getLogger(ViewBanHang.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(HomeNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (demTrangThai() < 5) {
                btnTaoHoaDon.setEnabled(true);
            }
    }//GEN-LAST:event_btnThanhToanActionPerformed

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

    private void cbbGiaGiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGiaGiamActionPerformed
        int index = cbbGiaGiam.getSelectedIndex();
        if (index < 0) {

        } else {
            VoucherCustomModel kh = listVoucherCMTT.get(index);
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
            } else if (cbbVoucher.equals("50%")) {
                giamGia = 0.5;
            } else {
                giamGia = 1;
            }

            lblThanhTien.setText(String.valueOf(fomat.format(thanhTien)));
            lblThanhToan.setText(String.valueOf(fomat.format(thanhToan = thanhTien * giamGia)));
        }
    }//GEN-LAST:event_cbbGiaGiamActionPerformed

    private void btnLoadVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadVoucherActionPerformed
        listVoucherCMTT = vcs.getVoucherTrangThai();
        int itemCount3 = cbbGiaGiam.getItemCount();
        for (int i = 0; i < itemCount3; i++) {
            cbbGiaGiam.removeItemAt(0);
        }

        listVoucherCMTT.forEach((kh) -> {
            cbbGiaGiam.addItem(kh.giamGiaTT());
        });
        cbbGiaGiam.setSelectedIndex(0);

    }//GEN-LAST:event_btnLoadVoucherActionPerformed

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
            java.util.logging.Logger.getLogger(HomeNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeNhanVien().setVisible(true);
            }
        });
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, "My Thread");
        t.setDaemon(true);
        return t;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnCapNhatSP;
    private javax.swing.JButton btnDoiSanPham;
    private javax.swing.JButton btnDoiTra;
    private javax.swing.JButton btnHoaDonBanHang;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLoadVoucher;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnLocHoaDon;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnReloadBH;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThayDoi;
    private javax.swing.JButton btnThemKHBH;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbDGBH;
    private javax.swing.JComboBox<String> cbbDSPBH;
    private javax.swing.JComboBox<String> cbbGiaGiam;
    private javax.swing.JComboBox<String> cbbLiDoDoi;
    private javax.swing.JComboBox<String> cbbLocTrangThaiHD;
    private javax.swing.JComboBox<String> cbbMSBH;
    private javax.swing.JComboBox<String> cbbNhanVienBH;
    private javax.swing.JComboBox<String> cbbSoDienThoai;
    private com.toedter.calendar.JDateChooser dcBatDau;
    private com.toedter.calendar.JDateChooser dcBatDauHD;
    private com.toedter.calendar.JDateChooser dcKetThuc;
    private com.toedter.calendar.JDateChooser dcKetThucHD;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labelTT;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMaHDDoiTra;
    private javax.swing.JLabel lblNgaDoiHangDT;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblTenKHDoiTra;
    private javax.swing.JLabel lblTenVoucher;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblThanhToan;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlDoiTra;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlWebcam;
    private javax.swing.JPanel pnlcards;
    private javax.swing.JTable tblDoiTra;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHDCTDoiTra;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonBanHang;
    private javax.swing.JTable tblHoaDonChiTietBH;
    private javax.swing.JTable tblHoaDonDoiTra;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtTenKhachHangBH;
    private javax.swing.JLabel txtTenQuanLy;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiemDSSP;
    private javax.swing.JTextField txtTimKiemDoiTra;
    private javax.swing.JTextField txtTimKiemHoaDon;
    // End of variables declaration//GEN-END:variables
}
