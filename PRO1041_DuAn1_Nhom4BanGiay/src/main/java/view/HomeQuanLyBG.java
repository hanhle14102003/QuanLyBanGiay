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
import customModel.ChucVuCustomModel;
import customModel.DeGiayCustomModel;
import customModel.DongSanPhamCustomModel;
import customModel.HDCTCustoModelHD;
import customModel.HoaDonChiTietCustomModel;
import customModel.HoaDonCustomModel;
import customModel.HoaDonCustomModelHD;
import customModel.HoaDonCustomModelHDThongKe;
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
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.text.NumberFormat;
import java.text.ParseException;
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
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import service.IChiTietSanPhamService;
import service.IDeGiayService;
import service.IDoiTraThongKeService;
import service.IDongSPHiberService;
import service.IDongSPService;
import service.IHoaDonHDServiceThongKe;
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
import service.impl.DoiTraThongKeServiceImpl;
import service.impl.DongSPHiberServiceImpl;
import service.impl.DongSPServiceImpl;
import service.impl.HoaDonHDServiceImpl;
import service.impl.HoaDonHDServiceImplThongKe;
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
public class HomeQuanLyBG extends javax.swing.JFrame implements Runnable, ThreadFactory {

    private DefaultTableModel tblmodel = new DefaultTableModel();
    private List<KhachHang> listKH = new ArrayList<>();
    private List<KhachHangCustomModel> listKHCM = new ArrayList<>();
    private KhachHangServiceImpl khs = new KhachHangServiceImpl();
    private DefaultTableModel dtm = new DefaultTableModel();
    private List<NhanVien> listNhanViens = new ArrayList<>();
    private List<NhanVienCustomModel> listNhanVienCustom = new ArrayList<>();
    private NhanVienServiceImpl nhanVienServiceImpl = new NhanVienServiceImpl();
    private List<ChucVuCustomModel> listChucVus = new ArrayList<>();
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
    DecimalFormat fomat = new DecimalFormat("###,###,###");
    private static final long serialVersionUID = 6441489157408381878L;
    private IHoaDonHDServiceThongKe hoaDonHDServiceThongKe = new HoaDonHDServiceImplThongKe();
    private IDoiTraThongKeService doiTraThongKe = new DoiTraThongKeServiceImpl();
    JTextField batDau;
    JTextField ketThuc;
    JTextField batDauDoiTra;
    JTextField ketThucDoiTra;

    public HomeQuanLyBG() {
        initComponents();
        cardLayout = (CardLayout) (pnlcards.getLayout());
        tblKhachHang.setModel(tblmodel);
        String headerKH[] = {"STT", "Mã", "HọTên", "Sđt", "Ngày sinh"};
        tblmodel.setColumnIdentifiers(headerKH);
        showData(listKHCM = khs.getAllCustom());
        setTitle("Hệ thống quản lý bán giày");
        setLocationRelativeTo(null);
        setResizable(false);
        tbHienThi.setModel(dtm);
        String headerNV[] = {"Chức Vụ", "Mã", "Họ Tên", "Tài khoản", "Mật khẩu", "Sdt", "Email", "Giới tính", "Ngày sinh", "Địa chỉ"};
        dtm.setColumnIdentifiers(headerNV);
        cbbChucVu.setModel(dfcbCV);
        listNhanVienCustom = nhanVienServiceImpl.getAllCustom();
        listChucVus = chucVuService.getAllCustom();
        listChucVus.forEach(chucVu -> cbbChucVu.addItem(chucVu.getMa()));
        showDataNV(listNhanVienCustom);
        txt_ngayNhapHang.setText(java.time.LocalDate.now() + "");
        txt_giaBan.requestFocus();
        String headersss[] = {"Mã hóa đơn", "Mã nhân viên", "Tên nhân viên", "Mã khách hàng", "Tên khách hàng", "Tổng tiền", "Tổng sản phẩm", "Trạng Thái"};
        tblModelHoaDonBH.setColumnIdentifiers(headersss);
        String headers[] = {"Mã Sản phẩm", "Tên Sản phẩm", "Số lượng", "Đon giá", "Thành tiền"};
        tblModelHDCTBH.setColumnIdentifiers(headers);
        tblHoaDonBanHang.setModel(tblModelHoaDonBH);
        tblHoaDonChiTietBH.setModel(tblModelHDCTBH);
        listHD = hds.getHoaDon();
        showDataHD(listHD);
        loadCBB();
        loadTable(null);
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
        String headerVC[] = {"Mã Voucher", "Chương trình", "Giảm giá", "Ngày bắt đầu", "Ngày kết thức", "Ghi chú", "Trạng thái"};
        tblVoucher.setModel(tblModelVoucher);
        tblModelVoucher.setColumnIdentifiers(headerVC);
        listVoucherCM = vcs.getAllCustom();
        listVoucherCMTT = vcs.getVoucherTrangThai();
        listVoucherCMTT.forEach((vc) -> {
            cbbGiaGiam.addItem(String.valueOf(vc.giamGiaTT()));
        });
        loadData(listVoucherCM);
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

    public boolean CheckDLVC() {
        if (uti.CheckRong(txtMaVoucher.getText())) {
            JOptionPane.showMessageDialog(this, "Mã không được để trống");
            txtMaVoucher.requestFocus();
            txtMaVoucher.setText("");
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
        if (uti.DemChuoi(txtTenVoucher.getText()) > 100) {
            JOptionPane.showMessageDialog(this, "Tên không lớn hơn 100 ký tự");
            txtTenVoucher.requestFocus();
            txtTenVoucher.setText("");
            return true;
        }

        if (uti.DemChuoi(txtGhiChuVoucher.getText()) > 100) {
            JOptionPane.showMessageDialog(this, "Ghi chú không lớn hơn 100 ký tự");
            txtGhiChuVoucher.requestFocus();
            txtGhiChuVoucher.setText("");
            return true;
        }

        return false;
    }

    public boolean CheckTrungMaVC() {
        if (vcs.CheckTrungMa(txtMaVoucher.getText())) {
            JOptionPane.showMessageDialog(this, "Mã bị trùng, vui lòng nhập mã khác");
            txtMa.requestFocus();
            txtMa.setText("");
            return true;
        }
        return false;
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
//                ex.printStackTrace();
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

    private void fillData(int index) {
        KhachHangCustomModel kh = listKHCM.get(index);
        txtMaKH.setText(kh.getMa());
        txtTenKH.setText(kh.getHoTen());
        txtSdt.setText(kh.getSdt());
        txtNgaySinhKH.setDate(kh.getNgaySinh());

    }

    private void showDataNV(List<NhanVienCustomModel> listNhanVienCustom) {
        dtm.setRowCount(0);
        for (NhanVienCustomModel listNhanViens : listNhanVienCustom) {
            dtm.addRow(listNhanViens.toRowData());
        }
    }

    private void fillDataNV(int index) {
        NhanVienCustomModel kh = listNhanVienCustom.get(index);

        txtMa.setText(kh.getMa());
        txtTen.setText(kh.getHoTen());
        txtTaiKhoan.setText(kh.getTaiKhoan());
        txtMatKhau.setText(kh.getMatKhau());
        txtNgaySinh.setDate(kh.getNgaySinh());
        txtDiaChi.setText(kh.getDiaChi());
        txtSdtNV.setText(kh.getSdt());
        txtEmail.setText(kh.getEmail());

        if (kh.getGioiTinh().equalsIgnoreCase("Nam")) {
            radioNam.setSelected(true);
        } else {
            radioNu.setSelected(true);
        }
        cbbChucVu.setSelectedItem(kh.getTenCV());
        lbTenChucVu.setText(kh.getTenCV());

    }

    public boolean CheckDLNV() {
        if (uti.CheckRong(txtMa.getText())) {
            JOptionPane.showMessageDialog(this, "Mã không được để trống");
            txtMa.requestFocus();
            txtMa.setText("");
            return true;
        }

        if (uti.DemChuoi(txtMa.getText()) > 20) {
            JOptionPane.showMessageDialog(this, "Mã không lớn hơn 20 ký tự");
            txtMa.requestFocus();
            txtMa.setText("");
            return true;
        }
        if (uti.CheckRong(txtTen.getText())) {
            JOptionPane.showMessageDialog(this, "Họ tên không được để trống");
            txtTen.requestFocus();
            txtTen.setText("");
            return true;
        }

        if (uti.DemChuoi(txtTen.getText()) > 100) {
            JOptionPane.showMessageDialog(this, "Họ tên không lớn hơn 100 ký tự");
            txtTen.requestFocus();
            txtTen.setText("");
            return true;
        }
        if (uti.CheckRong(txtTaiKhoan.getText())) {
            JOptionPane.showMessageDialog(this, "Tài khoản không được để trống");
            txtTaiKhoan.requestFocus();
            txtTaiKhoan.setText("");
            return true;
        }

        if (uti.DemChuoi(txtTaiKhoan.getText()) > 100) {
            JOptionPane.showMessageDialog(this, "Tài khoản không lớn hơn 100 ký tự");
            txtTaiKhoan.requestFocus();
            txtTaiKhoan.setText("");
            return true;
        }

        if (uti.CheckRong(txtMatKhau.getText())) {
            JOptionPane.showMessageDialog(this, "Mật khảu không được để trống");
            txtMatKhau.requestFocus();
            txtMatKhau.setText("");
            return true;
        }

        if (uti.DemChuoi(txtMatKhau.getText()) > 25) {
            JOptionPane.showMessageDialog(this, "Mật khảu không lớn hơn 25 ký tự");
            txtMatKhau.requestFocus();
            txtMatKhau.setText("");
            return true;
        }
        if (uti.CheckRong(txtDiaChi.getText())) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
            txtDiaChi.requestFocus();
            txtDiaChi.setText("");
            return true;
        }

        if (uti.DemChuoi(txtDiaChi.getText()) > 100) {
            JOptionPane.showMessageDialog(this, "Địa chỉ không lớn hơn 100 ký tự");
            txtDiaChi.requestFocus();
            txtDiaChi.setText("");
            return true;
        }
        if (uti.CheckRong(txtSdtNV.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
            txtSdt.requestFocus();
            txtSdt.setText("");
            return true;
        }

        if (uti.CheckSdt(txtSdtNV.getText())) {
            JOptionPane.showMessageDialog(this, "SDT không dúng định dạng hoặc không đủ số");
            txtSdtNV.requestFocus();
            return true;
        }
        if (uti.CheckRong(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "Email không được để trống");
            txtEmail.requestFocus();
            txtEmail.setText("");
            return true;
        }

        if (uti.DemChuoi(txtEmail.getText()) > 100) {
            JOptionPane.showMessageDialog(this, "Email không lớn hơn 100 ký tự");
            txtEmail.requestFocus();
            txtEmail.setText("");
            return true;
        }

        return false;
    }

    public boolean CheckTrungMaNV() {
        if (nhanVienServiceImpl.CheckTrungMa(txtMa.getText())) {
            JOptionPane.showMessageDialog(this, "Mã bị trùng, vui lòng nhập mã khác");
            txtMa.requestFocus();
            txtMa.setText("");
            return true;
        }
        return false;
    }
    //vu_ctsp
    private String click;

    public void loadTable(String input) {
        DefaultTableModel mol = (DefaultTableModel) tbl_CTSanPham.getModel();
        mol.setRowCount(0);
        int stt = 1;
        for (var x : chiTietSanPhamService.getAllCustomModel(input)) {
            mol.addRow(new Object[]{
                stt++,
                x.getMaSP(),
                x.getTenSP(),
                x.getTenMauSac(),
                x.getTenDeGiay(),
                x.getTenDongSP(),
                x.getNgayNhapHang(),
                x.getDonGia(),
                x.getSoLuong(),
                x.getKichCo(),
                x.getXuatXu(),
                x.getTrangThai() == 0 ? "Đang Bán" : "Dừng Bán"

            });
        }
    }

    public void loadTableByGia(String batDau, String ketThuc) {
        DefaultTableModel mol = (DefaultTableModel) tbl_CTSanPham.getModel();
        mol.setRowCount(0);
        int stt = 1;
        for (var x : chiTietSanPhamService.getAllBetWeen(batDau, ketThuc)) {
            mol.addRow(new Object[]{
                stt++,
                x.getMaSP(),
                x.getTenSP(),
                x.getTenMauSac(),
                x.getTenDeGiay(),
                x.getTenDongSP(),
                x.getNgayNhapHang(),
                x.getDonGia(),
                x.getSoLuong(),
                x.getKichCo(),
                x.getXuatXu(),
                x.getTrangThai() == 0 ? "Đang Bán" : "Dừng Bán"

            });
        }
    }

    public void loadCBB() {

        cbo_deGiay.removeAllItems();
        for (DeGiayCustomModel x : deGiayService.getAll(null)) {
            cbo_deGiay.addItem(x.getTen());
        }
        AutoCompleteDecorator.decorate(cbo_deGiay);
        //cbo_deGiay.setSelectedIndex(0);

        cbo_dongSanPham.removeAllItems();
        for (var x : dongSPService.getAll()) {
            cbo_dongSanPham.addItem(x.getTen());
        }
        AutoCompleteDecorator.decorate(cbo_dongSanPham);
        //cbo_dongSanPham.setSelectedIndex(0);

        cbo_mauSac.removeAllItems();
        for (var x : mauSacService.getAll()) {
            cbo_mauSac.addItem(x.getTen());
        }
        AutoCompleteDecorator.decorate(cbo_mauSac);
        //cbo_mauSac.setSelectedIndex(0);

        cbo_sanPham.removeAllItems();
        for (var x : sanPhamService.getAll()) {
            cbo_sanPham.addItem(x.getTen());
        }
        AutoCompleteDecorator.decorate(cbo_sanPham);
        //cbo_sanPham.setSelectedIndex(0);

        cbo_trangThai.removeAllItems();
        cbo_trangThai.addItem("Đang Bán");
        cbo_trangThai.addItem("Dừng Bán");
        cbo_trangThai.setSelectedIndex(0);

        cbo_locDeGiay.removeAllItems();
        for (DeGiayCustomModel x : deGiayService.getAll(null)) {
            cbo_locDeGiay.addItem(x.getTen());
        }

        cbo_locDongSP.removeAllItems();
        for (var x : dongSPService.getAll()) {
            cbo_locDongSP.addItem(x.getTen());
        }

        cbo_locMauSac.removeAllItems();
        for (var x : mauSacService.getAll()) {
            cbo_locMauSac.addItem(x.getTen());
        }

        cbo_locGia.removeAllItems();
        cbo_locGia.addItem("100000 - 500000");
        cbo_locGia.addItem("500000 - 1000000");
        cbo_locGia.addItem("1000000 - 3000000");
        cbo_locGia.addItem("3000000 - 5000000");
        cbo_locGia.addItem("5000000 - 7000000");
        cbo_locGia.addItem("7000000 - 10000000");

    }

    public void reloadCBB() {

        cbo_deGiay.removeAllItems();
        for (DeGiayCustomModel x : deGiayService.getAll(null)) {
            cbo_deGiay.addItem(x.getTen());
        }
        AutoCompleteDecorator.decorate(cbo_deGiay);
        //cbo_deGiay.setSelectedIndex(0);

        cbo_dongSanPham.removeAllItems();
        for (var x : dongSPService.getAll()) {
            cbo_dongSanPham.addItem(x.getTen());
        }
        AutoCompleteDecorator.decorate(cbo_dongSanPham);
        //cbo_dongSanPham.setSelectedIndex(0);

        cbo_mauSac.removeAllItems();
        for (var x : mauSacService.getAll()) {
            cbo_mauSac.addItem(x.getTen());
        }
        AutoCompleteDecorator.decorate(cbo_mauSac);
        //cbo_mauSac.setSelectedIndex(0);

        cbo_sanPham.removeAllItems();
        for (var x : sanPhamService.getAll()) {
            cbo_sanPham.addItem(x.getTen());
        }
        AutoCompleteDecorator.decorate(cbo_sanPham);
        //cbo_sanPham.setSelectedIndex(0);

        cbo_locDeGiay.removeAllItems();
        for (DeGiayCustomModel x : deGiayService.getAll(null)) {
            cbo_locDeGiay.addItem(x.getTen());
        }

        cbo_locDongSP.removeAllItems();
        for (var x : dongSPService.getAll()) {
            cbo_locDongSP.addItem(x.getTen());
        }

        cbo_locMauSac.removeAllItems();
        for (var x : mauSacService.getAll()) {
            cbo_locMauSac.addItem(x.getTen());
        }

    }

    public ChiTietSanPhamHiber getForm() {
        return new ChiTietSanPhamHiber(null,
                sanPhamHiberService.getByIndex(cbo_sanPham.getSelectedIndex()),
                dongSPHiberService.getByIndex(cbo_dongSanPham.getSelectedIndex()),
                deGiayService.getDeGiayHiberbyIndex(cbo_deGiay.getSelectedIndex()),
                mauSacHiberService.getByIndex(cbo_mauSac.getSelectedIndex()),
                txt_ngayNhapHang.getText(),
                Double.parseDouble(txt_giaBan.getText()),
                Integer.parseInt(txt_soLuong.getText()),
                txt_xuatXu.getText(),
                txt_kichCo.getText(),
                cbo_trangThai.getSelectedIndex());
    }

    public boolean checkDL() {
        // check giá bán
        if (uti.CheckRong(txt_giaBan.getText())) {
            JOptionPane.showMessageDialog(this, "Giá Bán không được bỏ trống");
            txt_giaBan.requestFocus();
            txt_giaBan.setText("");
            return true;
        }

        if (uti.CheckSoThapPhan(txt_giaBan.getText())) {
            JOptionPane.showMessageDialog(this, "Giá Bán không đúng kiểu dữ liệu");
            txt_giaBan.requestFocus();
            txt_giaBan.setText("");
            return true;
        }

        if (uti.DemChuoi(txt_giaBan.getText()) > 20) {
            JOptionPane.showMessageDialog(this, "Giá Bán không lớn hơn 20 ký tự");
            txt_giaBan.requestFocus();
            txt_giaBan.setText("");
            return true;
        }

        // check số lượng
        if (uti.CheckRong(txt_soLuong.getText())) {
            JOptionPane.showMessageDialog(this, "Số lượng không được bỏ trống");
            txt_soLuong.requestFocus();
            txt_soLuong.setText("");
            return true;
        }

        if (uti.CheckSo(txt_soLuong.getText())) {
            JOptionPane.showMessageDialog(this, "Số lượng không đúng kiểu dữ liệu");
            txt_soLuong.requestFocus();
            txt_soLuong.setText("");
            return true;
        }

        if (uti.DemChuoi(txt_soLuong.getText()) > 10) {
            JOptionPane.showMessageDialog(this, "Số lượng không lớn hơn 10 ký tự");
            txt_soLuong.requestFocus();
            txt_soLuong.setText("");
            return true;
        }

        // check xuất xứ
        if (uti.CheckRong(txt_xuatXu.getText())) {
            JOptionPane.showMessageDialog(this, "Xuất xứ không được bỏ trống");
            txt_xuatXu.requestFocus();
            txt_xuatXu.setText("");
            return true;
        }

        if (uti.CheckChu(txt_xuatXu.getText())) {
            JOptionPane.showMessageDialog(this, "Xuất xứ không đúng kiểu dữ liệu");
            txt_xuatXu.requestFocus();
            txt_xuatXu.setText("");
            return true;
        }

        if (uti.DemChuoi(txt_xuatXu.getText()) > 50) {
            JOptionPane.showMessageDialog(this, "Xuất xứ không lớn hơn 50 ký tự");
            txt_xuatXu.requestFocus();
            txt_xuatXu.setText("");
            return true;
        }

        // check ngày nhập hàng
        if (uti.CheckNgayThang(txt_ngayNhapHang.getText())) {
            JOptionPane.showMessageDialog(this, "Ngày nhập hàng không đúng kiểu dữ liệu");
            txt_ngayNhapHang.requestFocus();
            txt_ngayNhapHang.setText("");
            return true;
        }

        // check kích cỡ
        if (uti.CheckRong(txt_kichCo.getText())) {
            JOptionPane.showMessageDialog(this, "Kích cỡ không được bỏ trống");
            txt_kichCo.requestFocus();
            txt_kichCo.setText("");
            return true;
        }

        if (uti.DemChuoi(txt_kichCo.getText()) > 20) {
            JOptionPane.showMessageDialog(this, "Kích cỡ không lớn hơn 20 ký tự");
            txt_kichCo.requestFocus();
            txt_kichCo.setText("");
            return true;
        }
        return false;
    }

    public boolean CheckDLKH() {
        if (uti.CheckRong(txtMaKH.getText())) {
            JOptionPane.showMessageDialog(this, "Mã không được để trống");
            txtMaKH.requestFocus();
            txtMaKH.setText("");
            return true;
        }
        if (uti.DemChuoi(txtMaKH.getText()) > 20) {
            JOptionPane.showMessageDialog(this, "Mã không lớn hơn 20 ký tự");
            txtMaKH.requestFocus();
            txtMaKH.setText("");
            return true;
        }
        if (uti.CheckRong(txtTenKH.getText())) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống");
            txtTenKH.requestFocus();
            txtTenKH.setText("");
            return true;
        }
        if (uti.DemChuoi(txtTenKH.getText()) > 100) {
            JOptionPane.showMessageDialog(this, "Tên không lớn hơn 100 ký tự");
            txtTenKH.requestFocus();
            txtTenKH.setText("");
            return true;
        }

        if (uti.CheckRong(txtSdt.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được để trống");
            txtSdt.requestFocus();
            txtSdt.setText("");
            return true;
        }

        if (uti.CheckSdt(txtSdt.getText())) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải nhập đúng định đạng");
            txtSdt.requestFocus();
            return true;
        }
        if (uti.DemChuoi(txtSdt.getText()) > 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không lớn hơn 10 ký tự");
            txtSdt.requestFocus();
            return true;
        }

        return false;
    }

    public boolean CheckTrungMaKH() {
        if (khs.CheckTrungMa(txtMaKH.getText())) {
            JOptionPane.showMessageDialog(this, "Mã bị trùng, vui lòng nhập mã khác");
            txtMaKH.requestFocus();
            txtMaKH.setText("");
            return true;
        }
        return false;
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
        lbl_sanPham1.removeAll();
        lbl_sanPham1.setText(sum + "");
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
        lbl_sanPham1.removeAll();
        if (sum == 0) {
            lbl_sanPham1.setText(sum + "");
        } else {
            lbl_sanPham1.setText(tong + "");
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

    public boolean checkDLTK(JTextField input) {
        String p = "0[0-9]{3}";
        if (uti.CheckSo(input.getText())) {

            JOptionPane.showMessageDialog(this, "Năm Không Đúng Kiểu Dữ Liệu");

            input.requestFocus();
            input.setText("");
            return true;
        }

        if (uti.DemChuoi(input.getText()) > 4 || uti.DemChuoi(input.getText()) < 4) {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnKhachhang = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnThongKe = new javax.swing.JButton();
        btnBanHang = new javax.swing.JButton();
        btnDoiTra = new javax.swing.JButton();
        btnHoaDonBanHang = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnKetThuc = new javax.swing.JButton();
        btnVoucher = new javax.swing.JButton();
        pnlcards = new javax.swing.JPanel();
        pnlMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenQuanLy = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        pnlKhachHang = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txtTimKiemKH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtMaKH = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        btnClear = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtNgaySinhKH = new com.toedter.calendar.JDateChooser();
        pnlNhanVien = new javax.swing.JPanel();
        btnUpdateNV = new javax.swing.JButton();
        btnDeleteNV = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnClearNV = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbHienThi = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbbChucVu = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        lbTenChucVu = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbTenCuaHang = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTimKiemNV = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtSdtNV = new javax.swing.JTextField();
        radioNam = new javax.swing.JRadioButton();
        radioNu = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnAddNV = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        btnThemCV = new javax.swing.JButton();
        btnLoadNhanVien = new javax.swing.JButton();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
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
        pnlThongKe = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        date_ketThuc = new com.toedter.calendar.JDateChooser();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        lbl_sanPham1 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        lbl_doiTra = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        lbl_khachHang = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        lbl_doanhThu = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        btn_thongKeLBL = new javax.swing.JButton();
        jLabel64 = new javax.swing.JLabel();
        date_batDau = new com.toedter.calendar.JDateChooser();
        jPanel24 = new javax.swing.JPanel();
        jLabel65 = new javax.swing.JLabel();
        lbl_hoaDon = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel25 = new javax.swing.JPanel();
        pnl_doanhThu = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        txt_namDT = new javax.swing.JTextField();
        btn_thongKeDT = new javax.swing.JButton();
        jPanel26 = new javax.swing.JPanel();
        pnl_khachHangTron = new javax.swing.JPanel();
        pnl_khachHang = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        txt_namKH = new javax.swing.JTextField();
        btn_thongKeKH = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        pnl_doiTra = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        lbl_doiTraKichCo = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        lbl_doiTraMauSac = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        lbl_doiTraChatLuong = new javax.swing.JLabel();
        date_doiTraBD = new com.toedter.calendar.JDateChooser();
        date_doiTraKT = new com.toedter.calendar.JDateChooser();
        btn_thongKeDoiTra = new javax.swing.JButton();
        pnlSanPhamChiTiet = new javax.swing.JPanel();
        lbl_ngayNhapHang = new javax.swing.JLabel();
        lbl_locMauSac = new javax.swing.JLabel();
        txt_giaBan = new javax.swing.JTextField();
        lbl_locDeGiay = new javax.swing.JLabel();
        lbl_giaBan = new javax.swing.JLabel();
        cbo_locDeGiay = new javax.swing.JComboBox<>();
        lbl_soLuong = new javax.swing.JLabel();
        lbl_locDongSP = new javax.swing.JLabel();
        lbl_xuatXu = new javax.swing.JLabel();
        lbl_kichCo = new javax.swing.JLabel();
        cbo_locDongSP = new javax.swing.JComboBox<>();
        lbl_locGiaBan = new javax.swing.JLabel();
        lbl_trangThai = new javax.swing.JLabel();
        cbo_locGia = new javax.swing.JComboBox<>();
        txt_soLuong = new javax.swing.JTextField();
        txt_xuatXu = new javax.swing.JTextField();
        txt_kichCo = new javax.swing.JTextField();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_CTSanPham = new javax.swing.JTable();
        btn_xoa = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        lbl_sanPhamct = new javax.swing.JLabel();
        txt_timKiem = new javax.swing.JTextField();
        cbo_sanPham = new javax.swing.JComboBox<>();
        cbo_trangThai = new javax.swing.JComboBox<>();
        lbl_deGiay = new javax.swing.JLabel();
        cbo_deGiay = new javax.swing.JComboBox<>();
        txt_ngayNhapHang = new javax.swing.JTextField();
        btn_themSP = new javax.swing.JButton();
        cbo_dongSanPham = new javax.swing.JComboBox<>();
        btn_themMS = new javax.swing.JButton();
        cbo_mauSac = new javax.swing.JComboBox<>();
        btn_themDG1 = new javax.swing.JButton();
        lbl_mauSac = new javax.swing.JLabel();
        btn_themDSP1 = new javax.swing.JButton();
        lbl_dongSP = new javax.swing.JLabel();
        cbo_locMauSac = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        btnReloadCTSP = new javax.swing.JButton();
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
        pnlVoucher = new javax.swing.JPanel();
        btnClearVoucher = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayHetHan = new com.toedter.calendar.JDateChooser();
        cbbTrangThaiVoucher = new javax.swing.JComboBox<>();
        jLabel70 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        txtGhiChuVoucher = new javax.swing.JTextArea();
        btnSuaVoucher = new javax.swing.JButton();
        jLabel71 = new javax.swing.JLabel();
        btnThemVoucher = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        txtMaVoucher = new javax.swing.JTextField();
        txtTenVoucher = new javax.swing.JTextField();
        cbbMucGiamGia = new javax.swing.JComboBox<>();
        jPanel23 = new javax.swing.JPanel();
        txtTimKiemVoucher = new javax.swing.JTextField();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        jLabel75 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1474, 736));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(153, 0, 51));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });

        btnKhachhang.setBackground(new java.awt.Color(255, 255, 255));
        btnKhachhang.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnKhachhang.setForeground(new java.awt.Color(0, 153, 102));
        btnKhachhang.setText("Khách hàng");
        btnKhachhang.setBorder(null);
        btnKhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKhachhangMouseEntered(evt);
            }
        });
        btnKhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachhangActionPerformed(evt);
            }
        });

        btnNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        btnNhanVien.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnNhanVien.setForeground(new java.awt.Color(0, 153, 102));
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.setBorder(null);
        btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNhanVienMouseEntered(evt);
            }
        });
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnSanPham.setBackground(new java.awt.Color(255, 255, 255));
        btnSanPham.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnSanPham.setForeground(new java.awt.Color(0, 153, 102));
        btnSanPham.setText("Sản phẩm");
        btnSanPham.setBorder(null);
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseEntered(evt);
            }
        });
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnThongKe.setBackground(new java.awt.Color(255, 255, 255));
        btnThongKe.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(0, 153, 102));
        btnThongKe.setText("Thống Kê");
        btnThongKe.setBorder(null);
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThongKeMouseEntered(evt);
            }
        });
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnBanHang.setBackground(new java.awt.Color(255, 255, 255));
        btnBanHang.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnBanHang.setForeground(new java.awt.Color(0, 153, 102));
        btnBanHang.setText("Bán hàng");
        btnBanHang.setBorder(null);
        btnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBanHangMouseEntered(evt);
            }
        });
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnDoiTra.setBackground(new java.awt.Color(255, 255, 255));
        btnDoiTra.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnDoiTra.setForeground(new java.awt.Color(0, 153, 102));
        btnDoiTra.setText("Đổi trả");
        btnDoiTra.setBorder(null);
        btnDoiTra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDoiTraMouseEntered(evt);
            }
        });
        btnDoiTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiTraActionPerformed(evt);
            }
        });

        btnHoaDonBanHang.setBackground(new java.awt.Color(255, 255, 255));
        btnHoaDonBanHang.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnHoaDonBanHang.setForeground(new java.awt.Color(0, 153, 102));
        btnHoaDonBanHang.setText("Hóa đơn");
        btnHoaDonBanHang.setBorder(null);
        btnHoaDonBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHoaDonBanHangMouseEntered(evt);
            }
        });
        btnHoaDonBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonBanHangActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(255, 255, 255));
        btnLogout.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(0, 153, 102));
        btnLogout.setText("Logout");
        btnLogout.setBorder(null);
        btnLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLogoutMouseEntered(evt);
            }
        });
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblTitle.setBackground(new java.awt.Color(204, 0, 153));
        lblTitle.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("6G SNEAKER");

        btnKetThuc.setBackground(new java.awt.Color(255, 255, 255));
        btnKetThuc.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnKetThuc.setForeground(new java.awt.Color(0, 153, 102));
        btnKetThuc.setText("Thoát");
        btnKetThuc.setBorder(null);
        btnKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKetThucMouseEntered(evt);
            }
        });
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });

        btnVoucher.setBackground(new java.awt.Color(255, 255, 255));
        btnVoucher.setFont(new java.awt.Font("Cambria", 1, 22)); // NOI18N
        btnVoucher.setForeground(new java.awt.Color(0, 153, 102));
        btnVoucher.setText("Voucher");
        btnVoucher.setBorder(null);
        btnVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVoucherMouseEntered(evt);
            }
        });
        btnVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoucherActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHoaDonBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnHoaDonBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel1.add(jPanel2);

        jSplitPane1.setLeftComponent(jPanel1);

        pnlcards.setBackground(new java.awt.Color(51, 51, 51));
        pnlcards.setPreferredSize(new java.awt.Dimension(1130, 724));
        pnlcards.setLayout(new java.awt.CardLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setPreferredSize(new java.awt.Dimension(1148, 724));

        jLabel1.setFont(new java.awt.Font("Serif", 1, 70)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("WELCOME");

        txtTenQuanLy.setFont(new java.awt.Font("Serif", 1, 24)); // NOI18N
        txtTenQuanLy.setForeground(new java.awt.Color(0, 102, 102));

        jLabel17.setFont(new java.awt.Font("Serif", 1, 70)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 0));
        jLabel17.setText("Quản lý");

        jLabel18.setFont(new java.awt.Font("Serif", 1, 70)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 153, 0));
        jLabel18.setText("Bán giày");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Serif", 1, 70)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 153, 0));
        jLabel19.setText("6G Sneaker");

        jLabel20.setFont(new java.awt.Font("Serif", 1, 70)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 153, 0));
        jLabel20.setText("Hệ thống ");

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel20)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(412, 412, 412)
                        .addComponent(txtTenQuanLy, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                .addGap(0, 412, Short.MAX_VALUE)
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(382, 382, 382))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(410, 410, 410))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(txtTenQuanLy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(jLabel20)
                .addGap(52, 52, 52)
                .addComponent(jLabel17)
                .addGap(56, 56, 56)
                .addComponent(jLabel18)
                .addGap(42, 42, 42)
                .addComponent(jLabel19)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pnlcards.add(pnlMain, "cardMain");

        pnlKhachHang.setBackground(new java.awt.Color(255, 255, 255));
        pnlKhachHang.setForeground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiemKH.setText("Tìm kiếm ...");
        txtTimKiemKH.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemKHCaretUpdate(evt);
            }
        });
        txtTimKiemKH.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemKHFocusGained(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("Mã Khách Hàng");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Tên Khách Hàng");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Số điện thoại");

        btnUpdate.setBackground(new java.awt.Color(255, 204, 0));
        btnUpdate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Sửa");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpdateMouseEntered(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(255, 204, 0));
        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm ");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddMouseEntered(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 204, 0));
        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Xóa");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteMouseEntered(evt);
            }
        });
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tblKhachHang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        btnClear.setBackground(new java.awt.Color(255, 204, 0));
        btnClear.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setText("Clear");
        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClearMouseEntered(evt);
            }
        });
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel21.setText("Khách hàng");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel42.setText("Ngày sinh");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(59, 59, 59)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(51, 51, 51)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySinhKH, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 970, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(408, 408, 408)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addGap(81, 81, 81)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgaySinhKH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57)
                .addComponent(txtTimKiemKH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlKhachHangLayout = new javax.swing.GroupLayout(pnlKhachHang);
        pnlKhachHang.setLayout(pnlKhachHangLayout);
        pnlKhachHangLayout.setHorizontalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlKhachHangLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlKhachHangLayout.setVerticalGroup(
            pnlKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlcards.add(pnlKhachHang, "cardKH");

        pnlNhanVien.setBackground(new java.awt.Color(255, 255, 255));

        btnUpdateNV.setBackground(new java.awt.Color(255, 204, 0));
        btnUpdateNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnUpdateNV.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateNV.setText("Update");
        btnUpdateNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnUpdateNVMouseEntered(evt);
            }
        });
        btnUpdateNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateNVActionPerformed(evt);
            }
        });

        btnDeleteNV.setBackground(new java.awt.Color(255, 204, 0));
        btnDeleteNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnDeleteNV.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteNV.setText("Delete");
        btnDeleteNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteNVMouseEntered(evt);
            }
        });
        btnDeleteNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteNVActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel4.setText("Họ Tên");

        btnClearNV.setBackground(new java.awt.Color(255, 204, 0));
        btnClearNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnClearNV.setForeground(new java.awt.Color(255, 255, 255));
        btnClearNV.setText("Clear");
        btnClearNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClearNVMouseEntered(evt);
            }
        });
        btnClearNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearNVActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel6.setText("Giới tính");

        tbHienThi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHienThiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbHienThiMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tbHienThi);

        jLabel7.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel7.setText("Tài khoản");

        jLabel8.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel8.setText("Mật khẩu");

        jLabel12.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel12.setText("Chức Vụ");

        jLabel9.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel9.setText("Ngày sinh");

        cbbChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbbChucVuMouseEntered(evt);
            }
        });
        cbbChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChucVuActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel10.setText("Địa chỉ");

        jLabel11.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel11.setText("Sdt");

        txtMa.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N

        txtTimKiemNV.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        txtTimKiemNV.setText("Tìm kiếm ...");
        txtTimKiemNV.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemNVCaretUpdate(evt);
            }
        });
        txtTimKiemNV.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemNVFocusGained(evt);
            }
        });

        txtTen.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N

        txtTaiKhoan.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N

        txtMatKhau.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N

        txtSdtNV.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N

        buttonGroup1.add(radioNam);
        radioNam.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        radioNam.setSelected(true);
        radioNam.setText("Nam");
        radioNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                radioNamMouseEntered(evt);
            }
        });

        buttonGroup1.add(radioNu);
        radioNu.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        radioNu.setText("Nữ");
        radioNu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                radioNuMouseEntered(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel13.setText("Email");

        txtEmail.setFont(new java.awt.Font("Cambria Math", 1, 12)); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Courier New", 1, 36)); // NOI18N
        jLabel14.setText("NHÂN VIÊN");

        btnAddNV.setBackground(new java.awt.Color(255, 204, 0));
        btnAddNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAddNV.setForeground(new java.awt.Color(255, 255, 255));
        btnAddNV.setText("Add");
        btnAddNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAddNVMouseEntered(evt);
            }
        });
        btnAddNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNVActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Cambria Math", 1, 18)); // NOI18N
        jLabel16.setText("Mã");

        btnThemCV.setBackground(new java.awt.Color(255, 0, 0));
        btnThemCV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnThemCV.setForeground(new java.awt.Color(255, 255, 255));
        btnThemCV.setText("+");
        btnThemCV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemCVMouseEntered(evt);
            }
        });
        btnThemCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCVActionPerformed(evt);
            }
        });

        btnLoadNhanVien.setBackground(new java.awt.Color(255, 0, 51));
        btnLoadNhanVien.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLoadNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        btnLoadNhanVien.setText("Load");
        btnLoadNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLoadNhanVienMouseEntered(evt);
            }
        });
        btnLoadNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadNhanVienActionPerformed(evt);
            }
        });

        txtNgaySinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtNgaySinhMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout pnlNhanVienLayout = new javax.swing.GroupLayout(pnlNhanVien);
        pnlNhanVien.setLayout(pnlNhanVienLayout);
        pnlNhanVienLayout.setHorizontalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                        .addGap(463, 463, 463)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                                .addComponent(jLabel16)
                                                .addGap(99, 99, 99)
                                                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel6))
                                                .addGap(51, 51, 51)
                                                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                                        .addComponent(btnThemCV)
                                                        .addGap(66, 66, 66)
                                                        .addComponent(btnLoadNhanVien))
                                                    .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(cbbChucVu, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlNhanVienLayout.createSequentialGroup()
                                                            .addComponent(radioNam)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(radioNu)))))))
                                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                        .addGap(239, 239, 239)
                                        .addComponent(lbTenCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                        .addGap(62, 62, 62)
                                        .addComponent(btnAddNV, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(70, 70, 70)
                                        .addComponent(btnUpdateNV, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel12)
                                        .addGap(181, 181, 181)
                                        .addComponent(lbTenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(50, 50, 50)
                                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel7))
                                            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel10)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel11)
                                                    .addComponent(jLabel13))
                                                .addGap(4, 4, 4)))
                                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                                .addGap(58, 58, 58)
                                                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(txtSdtNV, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                                    .addComponent(txtDiaChi)
                                                    .addComponent(txtEmail)))
                                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(btnDeleteNV, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(78, 78, 78)
                                        .addComponent(btnClearNV, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(235, Short.MAX_VALUE))
        );
        pnlNhanVienLayout.setVerticalGroup(
            pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jLabel8)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(radioNam)
                                .addComponent(radioNu)))
                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(lbTenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLoadNhanVien))
                            .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel12)
                                                .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtSdtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnThemCV, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(10, 10, 10)
                        .addComponent(lbTenCuaHang, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddNV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdateNV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeleteNV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearNV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtTimKiemNV, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))
                    .addGroup(pnlNhanVienLayout.createSequentialGroup()
                        .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pnlcards.add(pnlNhanVien, "cardNV");

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
                .addContainerGap(653, Short.MAX_VALUE))
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
                .addContainerGap(793, Short.MAX_VALUE))
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

        txtTimKiemDoiTra.setText("Tìm kiếm ...");
        txtTimKiemDoiTra.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemDoiTraCaretUpdate(evt);
            }
        });
        txtTimKiemDoiTra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemDoiTraFocusGained(evt);
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(334, 334, 334))
                            .addGroup(pnlDoiTraLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
            .addGroup(pnlDoiTraLayout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addComponent(jLabel47)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlDoiTraLayout.setVerticalGroup(
            pnlDoiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoiTraLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
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
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlcards.add(pnlDoiTra, "cardDoiTra");

        pnlThongKe.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setForeground(new java.awt.Color(255, 255, 255));

        date_ketThuc.setDateFormatString("yyyy-MM-dd\n");

        jLabel57.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel57.setText("Ngày Bắt Đầu");

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel58.setText("Ngày Kết Thúc");

        jPanel17.setBackground(new java.awt.Color(255, 204, 0));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel59.setText("Sản phẩm bán ra");

        lbl_sanPham1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_sanPham1.setForeground(new java.awt.Color(255, 0, 0));
        lbl_sanPham1.setText("0");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(lbl_sanPham1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel59)
                .addGap(18, 18, 18)
                .addComponent(lbl_sanPham1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 204, 0));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel60.setText("Sản Phẩm Đổi Trả");

        lbl_doiTra.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_doiTra.setForeground(new java.awt.Color(255, 0, 0));
        lbl_doiTra.setText("200");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel60)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(lbl_doiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_doiTra)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 204, 0));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel61.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel61.setText("Lượng khách hàng");

        lbl_khachHang.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_khachHang.setForeground(new java.awt.Color(255, 0, 0));
        lbl_khachHang.setText("200");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel61)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel61)
                .addGap(18, 18, 18)
                .addComponent(lbl_khachHang)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 204, 0));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel62.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel62.setText("Doanh Thu");

        lbl_doanhThu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_doanhThu.setForeground(new java.awt.Color(255, 0, 0));
        lbl_doanhThu.setText("20.000.000");

        jLabel63.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(0, 0, 255));
        jLabel63.setText("VNĐ");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(lbl_doanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel63))
                    .addComponent(jLabel62))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_doanhThu)
                    .addComponent(jLabel63))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_thongKeLBL.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_thongKeLBL.setText("Thống Kê");
        btn_thongKeLBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongKeLBLActionPerformed(evt);
            }
        });

        jLabel64.setFont(new java.awt.Font("Times New Roman", 1, 29)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(0, 102, 0));
        jLabel64.setText("Thống Kê");

        date_batDau.setDateFormatString("yyyy-MM-dd\n");

        jPanel24.setBackground(new java.awt.Color(255, 204, 0));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel65.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel65.setText("Hóa đơn");

        lbl_hoaDon.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_hoaDon.setForeground(new java.awt.Color(255, 0, 0));
        lbl_hoaDon.setText("209");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_hoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel65)
                .addGap(18, 18, 18)
                .addComponent(lbl_hoaDon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        pnl_doanhThu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_doanhThuLayout = new javax.swing.GroupLayout(pnl_doanhThu);
        pnl_doanhThu.setLayout(pnl_doanhThuLayout);
        pnl_doanhThuLayout.setHorizontalGroup(
            pnl_doanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1136, Short.MAX_VALUE)
        );
        pnl_doanhThuLayout.setVerticalGroup(
            pnl_doanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );

        jLabel66.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel66.setText("Năm");

        btn_thongKeDT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_thongKeDT.setText("Thống Kê");
        btn_thongKeDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongKeDTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_doanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_namDT, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_thongKeDT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_thongKeDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_namDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnl_doanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jTabbedPane4.addTab("Biểu Đồ Thống Kê Doanh Thu", jPanel25);

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        pnl_khachHangTron.setBackground(new java.awt.Color(0, 153, 153));

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

        pnl_khachHang.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_khachHangLayout = new javax.swing.GroupLayout(pnl_khachHang);
        pnl_khachHang.setLayout(pnl_khachHangLayout);
        pnl_khachHangLayout.setHorizontalGroup(
            pnl_khachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
        );
        pnl_khachHangLayout.setVerticalGroup(
            pnl_khachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        jLabel81.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel81.setText("Năm");

        btn_thongKeKH.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn_thongKeKH.setText("Thống Kê");
        btn_thongKeKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongKeKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnl_khachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_namKH, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_thongKeKH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(pnl_khachHangTron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnl_khachHangTron, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_namKH)
                                .addComponent(btn_thongKeKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnl_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane4.addTab("Biểu Đồ Thống Kê Khách Hàng", jPanel26);

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        pnl_doiTra.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnl_doiTraLayout = new javax.swing.GroupLayout(pnl_doiTra);
        pnl_doiTra.setLayout(pnl_doiTraLayout);
        pnl_doiTraLayout.setHorizontalGroup(
            pnl_doiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 649, Short.MAX_VALUE)
        );
        pnl_doiTraLayout.setVerticalGroup(
            pnl_doiTraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );

        jPanel28.setBackground(new java.awt.Color(0, 204, 204));

        jLabel82.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel82.setText("Sản Phẩm Đổi Trả Do Kích Cỡ");

        lbl_doiTraKichCo.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_doiTraKichCo.setForeground(new java.awt.Color(204, 0, 0));
        lbl_doiTraKichCo.setText("200");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                        .addComponent(lbl_doiTraKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123))))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel82)
                .addGap(30, 30, 30)
                .addComponent(lbl_doiTraKichCo)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel29.setBackground(new java.awt.Color(0, 204, 204));

        jLabel83.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel83.setText("Sản Phẩm Đổi Trả Do Màu Sắc");

        lbl_doiTraMauSac.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_doiTraMauSac.setForeground(new java.awt.Color(204, 0, 0));
        lbl_doiTraMauSac.setText("200");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                        .addComponent(lbl_doiTraMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116))))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel83)
                .addGap(44, 44, 44)
                .addComponent(lbl_doiTraMauSac)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanel30.setBackground(new java.awt.Color(0, 204, 204));

        jLabel84.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel84.setText("Sản Phẩm Đổi Trả Do Chất Lượng");

        lbl_doiTraChatLuong.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        lbl_doiTraChatLuong.setForeground(new java.awt.Color(204, 0, 0));
        lbl_doiTraChatLuong.setText("200");

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                        .addComponent(lbl_doiTraChatLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel84)
                .addGap(30, 30, 30)
                .addComponent(lbl_doiTraChatLuong)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        date_doiTraBD.setDateFormatString("yyyy-MM-dd\n");

        date_doiTraKT.setDateFormatString("yyyy-MM-dd\n");

        btn_thongKeDoiTra.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_thongKeDoiTra.setText("Thống Kê");
        btn_thongKeDoiTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thongKeDoiTraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnl_doiTra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(date_doiTraBD, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(date_doiTraKT, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(btn_thongKeDoiTra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(date_doiTraBD, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(date_doiTraKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnl_doiTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel27Layout.createSequentialGroup()
                        .addComponent(btn_thongKeDoiTra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane4.addTab("Sản Phẩm Đổi Trả", jPanel27);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date_batDau, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(date_ketThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_thongKeLBL)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(492, 492, 492)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel64)
                .addGap(5, 5, 5)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(date_batDau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(date_ketThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                        .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_thongKeLBL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlThongKeLayout = new javax.swing.GroupLayout(pnlThongKe);
        pnlThongKe.setLayout(pnlThongKeLayout);
        pnlThongKeLayout.setHorizontalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongKeLayout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlThongKeLayout.setVerticalGroup(
            pnlThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlcards.add(pnlThongKe, "cardTK");

        pnlSanPhamChiTiet.setBackground(new java.awt.Color(255, 255, 255));

        lbl_ngayNhapHang.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_ngayNhapHang.setText("Ngày nhập hàng");

        lbl_locMauSac.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_locMauSac.setText("Màu sắc");

        lbl_locDeGiay.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_locDeGiay.setText("Đế giày");

        lbl_giaBan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_giaBan.setText("Giá bán");

        cbo_locDeGiay.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_locDeGiayItemStateChanged(evt);
            }
        });

        lbl_soLuong.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_soLuong.setText("Số lượng");

        lbl_locDongSP.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_locDongSP.setText("Dòng sản phẩm");

        lbl_xuatXu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_xuatXu.setText("Xuất xứ");

        lbl_kichCo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_kichCo.setText("Kích cỡ");

        cbo_locDongSP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_locDongSPItemStateChanged(evt);
            }
        });

        lbl_locGiaBan.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_locGiaBan.setText("Giá");

        lbl_trangThai.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_trangThai.setText("Trạng thái");

        cbo_locGia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_locGiaItemStateChanged(evt);
            }
        });
        cbo_locGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_locGiaActionPerformed(evt);
            }
        });

        btn_them.setBackground(new java.awt.Color(255, 204, 0));
        btn_them.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_them.setForeground(new java.awt.Color(255, 255, 255));
        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.setBackground(new java.awt.Color(255, 204, 0));
        btn_sua.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_sua.setForeground(new java.awt.Color(255, 255, 255));
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        tbl_CTSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Sản Phẩm", "Màu Sắc", "Đế Giày", "Dòng Sản Phẩm", "Ngày Nhập Hàng", "Giá Bán", "Số Lượng", "Kích Cỡ", "Xuất Xứ", "Trạng Thái"
            }
        ));
        tbl_CTSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_CTSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_CTSanPham);

        btn_xoa.setBackground(new java.awt.Color(255, 204, 0));
        btn_xoa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_xoa.setForeground(new java.awt.Color(255, 255, 255));
        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_clear.setBackground(new java.awt.Color(255, 204, 0));
        btn_clear.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btn_clear.setForeground(new java.awt.Color(255, 255, 255));
        btn_clear.setText("Clear");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        lbl_sanPhamct.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_sanPhamct.setText("Sản phẩm");

        txt_timKiem.setText("Tìm Kiếm...");
        txt_timKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_timKiemCaretUpdate(evt);
            }
        });
        txt_timKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_timKiemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_timKiemFocusLost(evt);
            }
        });

        lbl_deGiay.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_deGiay.setText("Đế giày");

        txt_ngayNhapHang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_ngayNhapHangFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_ngayNhapHangFocusLost(evt);
            }
        });

        btn_themSP.setBackground(new java.awt.Color(204, 0, 0));
        btn_themSP.setForeground(new java.awt.Color(255, 255, 255));
        btn_themSP.setText("+");
        btn_themSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themSPActionPerformed(evt);
            }
        });

        btn_themMS.setBackground(new java.awt.Color(204, 0, 0));
        btn_themMS.setForeground(new java.awt.Color(255, 255, 255));
        btn_themMS.setText("+");
        btn_themMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themMSActionPerformed(evt);
            }
        });

        btn_themDG1.setBackground(new java.awt.Color(204, 0, 0));
        btn_themDG1.setForeground(new java.awt.Color(255, 255, 255));
        btn_themDG1.setText("+");
        btn_themDG1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themDG1ActionPerformed(evt);
            }
        });

        lbl_mauSac.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_mauSac.setText("Màu sắc");

        btn_themDSP1.setBackground(new java.awt.Color(204, 0, 0));
        btn_themDSP1.setForeground(new java.awt.Color(255, 255, 255));
        btn_themDSP1.setText("+");
        btn_themDSP1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themDSP1ActionPerformed(evt);
            }
        });

        lbl_dongSP.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl_dongSP.setText("Dòng sản phẩm");

        cbo_locMauSac.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_locMauSacItemStateChanged(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel22.setText("Sản phẩm chi tiết");

        btnReloadCTSP.setBackground(new java.awt.Color(255, 204, 0));
        btnReloadCTSP.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnReloadCTSP.setForeground(new java.awt.Color(255, 255, 255));
        btnReloadCTSP.setText("Reload");
        btnReloadCTSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadCTSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSanPhamChiTietLayout = new javax.swing.GroupLayout(pnlSanPhamChiTiet);
        pnlSanPhamChiTiet.setLayout(pnlSanPhamChiTietLayout);
        pnlSanPhamChiTietLayout.setHorizontalGroup(
            pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamChiTietLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamChiTietLayout.createSequentialGroup()
                                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_sanPhamct, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamChiTietLayout.createSequentialGroup()
                                .addComponent(lbl_giaBan)
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSanPhamChiTietLayout.createSequentialGroup()
                                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbl_xuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_soLuong))
                                .addGap(31, 31, 31)))
                        .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                                .addComponent(cbo_sanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_themSP))
                            .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_xuatXu)
                                    .addComponent(txt_soLuong)
                                    .addComponent(txt_giaBan)
                                    .addComponent(cbo_mauSac, 0, 205, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_themMS)
                                    .addComponent(btnReloadCTSP)
                                    .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_dongSP)
                            .addComponent(lbl_deGiay)
                            .addComponent(lbl_ngayNhapHang)
                            .addComponent(lbl_kichCo)
                            .addComponent(lbl_trangThai))
                        .addGap(30, 30, 30)
                        .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                                .addComponent(cbo_dongSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_themDSP1))
                            .addComponent(txt_ngayNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_kichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                                .addComponent(cbo_deGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_themDG1)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                        .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                                .addGap(479, 479, 479)
                                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(lbl_locDeGiay)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbo_locDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(lbl_locDongSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_locDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_locGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(366, 366, 366))))
            .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                        .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                                .addGap(194, 194, 194)
                                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(lbl_locMauSac)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbo_locMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(650, 650, 650)
                        .addComponent(cbo_locGia, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1097, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                            .addGap(400, 400, 400)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlSanPhamChiTietLayout.setVerticalGroup(
            pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSanPhamChiTietLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel22)
                .addGap(44, 44, 44)
                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_sanPhamct, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_sanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_deGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_deGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_themSP)
                    .addComponent(btn_themDG1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_dongSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_mauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_dongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_themMS)
                    .addComponent(btn_themDSP1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_giaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ngayNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ngayNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReloadCTSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_soLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_kichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_kichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbo_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_xuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_xuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbl_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_locMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_locMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_locDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_locDeGiay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_locDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_locDongSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_locGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_locGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlcards.add(pnlSanPhamChiTiet, "cardCTSP");

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
                .addContainerGap(233, Short.MAX_VALUE))
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

        pnlVoucher.setBackground(new java.awt.Color(255, 255, 255));

        btnClearVoucher.setBackground(new java.awt.Color(255, 0, 51));
        btnClearVoucher.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnClearVoucher.setForeground(new java.awt.Color(255, 255, 255));
        btnClearVoucher.setText("Clear");
        btnClearVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearVoucherActionPerformed(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel67.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel67.setText("Ngày hết hạn");

        jLabel68.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel68.setText("Ngày bắt đầu");

        jLabel69.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel69.setText("Trạng thái");

        cbbTrangThaiVoucher.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbbTrangThaiVoucher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang áp dụng", "Đã hết hạn" }));

        jLabel70.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel70.setText("Ghi chú");

        txtGhiChuVoucher.setColumns(20);
        txtGhiChuVoucher.setRows(5);
        jScrollPane14.setViewportView(txtGhiChuVoucher);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNgayHetHan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbbTrangThaiVoucher, 0, 160, Short.MAX_VALUE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(jLabel70))
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNgayHetHan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel69)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbTrangThaiVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnSuaVoucher.setBackground(new java.awt.Color(255, 0, 51));
        btnSuaVoucher.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSuaVoucher.setForeground(new java.awt.Color(255, 255, 255));
        btnSuaVoucher.setText("Sửa");
        btnSuaVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaVoucherActionPerformed(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel71.setText("Tạo chương trình khuyến mãi");

        btnThemVoucher.setBackground(new java.awt.Color(255, 0, 51));
        btnThemVoucher.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnThemVoucher.setForeground(new java.awt.Color(255, 255, 255));
        btnThemVoucher.setText("Thêm");
        btnThemVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemVoucherActionPerformed(evt);
            }
        });

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel72.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel72.setText("Mã khuyễn mãi");

        jLabel73.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel73.setText("Tên chương trình khuyến mãi ( Áp dụng tất cả sản phẩm )");

        jLabel74.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel74.setText("Mức giá giảm (%)");

        cbbMucGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5%", "10%", "15%", "20%", "30%", "35%", "45%", "50%" }));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtMaVoucher)
                        .addComponent(txtTenVoucher)
                        .addComponent(jLabel73)
                        .addComponent(jLabel74)
                        .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbbMucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbbMucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtTimKiemVoucher.setText("Tìm kiếm ...");
        txtTimKiemVoucher.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemVoucherCaretUpdate(evt);
            }
        });
        txtTimKiemVoucher.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTimKiemVoucherFocusGained(evt);
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
        jScrollPane15.setViewportView(tblVoucher);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(txtTimKiemVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTimKiemVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel75.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel75.setText("Danh sách Vouncher");

        javax.swing.GroupLayout pnlVoucherLayout = new javax.swing.GroupLayout(pnlVoucher);
        pnlVoucher.setLayout(pnlVoucherLayout);
        pnlVoucherLayout.setHorizontalGroup(
            pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVoucherLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlVoucherLayout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlVoucherLayout.createSequentialGroup()
                                .addComponent(btnThemVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addComponent(btnSuaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88)
                                .addComponent(btnClearVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlVoucherLayout.createSequentialGroup()
                .addGroup(pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlVoucherLayout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlVoucherLayout.setVerticalGroup(
            pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlVoucherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel71)
                .addGap(28, 28, 28)
                .addGroup(pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlVoucherLayout.createSequentialGroup()
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlVoucherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnClearVoucher, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel75)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlcards.add(pnlVoucher, "cardVoucher");

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

    private void btnKhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachhangActionPerformed
        cardLayout.show(pnlcards, "cardKH");

    }//GEN-LAST:event_btnKhachhangActionPerformed

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        cardLayout.show(pnlcards, "cardNV");

    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        cardLayout.show(pnlcards, "cardCTSP");

    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        // TODO add your handling code here:
        cardLayout.show(pnlcards, "cardTK");

    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        // TODO add your handling code here:

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


    private void btnUpdateNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateNVActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        int row = tbHienThi.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên muốn sửa");
        } else {
            if (txtNgaySinh.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống");
            } else {
                if (CheckDLNV() == false && CheckTrungMaNV() == false) {

                    var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn cập nhật");
                    if (temp == 0) {
                        NhanVienCustomModel nhanVien = listNhanVienCustom.get(row);
                        String ma = nhanVien.getId();
                        NhanVien nv = new NhanVien();
                        nv.setMa(txtMa.getText());
                        nv.setHoTen(txtTen.getText());
                        nv.setTaiKhoan(txtTaiKhoan.getText());
                        nv.setMatKhau(txtMatKhau.getText());
                        nv.setDiaChi(txtDiaChi.getText());
                        nv.setEmail(txtEmail.getText());
                        nv.setSdt(txtSdtNV.getText());
                        String ngaySinh = sdf.format(txtNgaySinh.getDate());
                        nv.setNgaySinh(Date.valueOf(ngaySinh));
                        boolean gender = radioNam.isSelected();
                        String gioiTinh;
                        if (gender) {
                            gioiTinh = "Nam";
                        } else {
                            gioiTinh = "Nữ";
                        }
                        String maCV = cbbChucVu.getSelectedItem().toString();
                        ChucVu cv = chucVuService.getOne(maCV);
                        nv.setCv(cv);
                        nv.setGioiTinh(gioiTinh);
                        JOptionPane.showMessageDialog(this, nhanVienServiceImpl.update(nv, ma));
                        listNhanVienCustom = nhanVienServiceImpl.getAllCustom();
                        showDataNV(listNhanVienCustom);

                    }
                }
            }

        }

    }//GEN-LAST:event_btnUpdateNVActionPerformed

    private void btnDeleteNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteNVActionPerformed
        // TODO add your handling code here:
        int row = tbHienThi.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên muốn xóa");
        } else {
            if (CheckDLNV() == false) {
                var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa");
                if (temp == 0) {
                    NhanVienCustomModel kh = listNhanVienCustom.get(row);
                    String ma = kh.getId();
                    JOptionPane.showMessageDialog(this, nhanVienServiceImpl.delete(ma));
                    listNhanVienCustom = nhanVienServiceImpl.getAllCustom();
                    showDataNV(listNhanVienCustom);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteNVActionPerformed

    private void btnClearNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearNVActionPerformed
        // TODO add your handling code here:

        txtMa.setText("");
        txtTen.setText("");
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
        txtNgaySinh.setDate(null);
        txtDiaChi.setText("");
        txtSdtNV.setText("");
        txtEmail.setText("");
        radioNam.setSelected(true);
        cbbChucVu.setSelectedIndex(0);
    }//GEN-LAST:event_btnClearNVActionPerformed

    private void tbHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseClicked
        // TODO add your handling code here:
        int row = tbHienThi.getSelectedRow();
        fillDataNV(row);
    }//GEN-LAST:event_tbHienThiMouseClicked

    private void cbbChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChucVuActionPerformed
        // TODO add your handling code here:
        if (cbbChucVu.getSelectedIndex() < 0) {

        } else {
            String maCH = cbbChucVu.getSelectedItem().toString();
            ChucVu cv = (ChucVu) chucVuService.getOne(maCH);
            lbTenChucVu.setText(cv.getTen());
        }

    }//GEN-LAST:event_cbbChucVuActionPerformed

    private void btnAddNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNVActionPerformed
        if (txtNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống");
        } else {
            String ma = txtMa.getText();
            String ten = txtTen.getText();
            String tk = txtTaiKhoan.getText();
            String mk = txtMatKhau.getText();
            String sdt = txtSdtNV.getText();
            String diaChi = txtDiaChi.getText();
            String email = txtEmail.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySinh = sdf.format(txtNgaySinh.getDate());
            boolean gender = radioNam.isSelected();
            String gioiTinh = "";
            if (gender) {
                gioiTinh = "Nam";
            } else {
                gioiTinh = "Nữ";
            }
            String maCV = cbbChucVu.getSelectedItem().toString();
            ChucVu cv = (ChucVu) chucVuService.getOne(maCV);

            if (CheckDLNV() == false && CheckTrungMaNV() == false) {

                var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm mới");
                if (temp == 0) {
                    NhanVien nv = new NhanVien(cv, ma, ten, tk, mk, sdt, email, gioiTinh, Date.valueOf(ngaySinh), diaChi);
                    JOptionPane.showMessageDialog(this, nhanVienServiceImpl.add(nv));
                    listNhanVienCustom = nhanVienServiceImpl.getAllCustom();
                    showDataNV(listNhanVienCustom);
                }
            }
        }
    }//GEN-LAST:event_btnAddNVActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtMaKH.setText("");
        txtTenKH.setText("");
        txtTimKiemKH.setText("");
        txtSdt.setText("");
        txtNgaySinhKH.setDate(null);
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int row = tblKhachHang.getSelectedRow();
        fillData(row);
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String ma = txtMaKH.getText();
        if (CheckDLKH() == false) {
            var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa");
            if (temp == 0) {
                JOptionPane.showMessageDialog(this, khs.delete(ma));
                showData(listKHCM = khs.getAllCustom());
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (txtNgaySinhKH.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống");
        } else {

            String ma = txtMaKH.getText();
            String ten = txtTenKH.getText();
            String sdt = txtSdt.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySinh = sdf.format(txtNgaySinhKH.getDate());

            if (CheckDLKH() == false && CheckTrungMaKH() == false) {
                var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm mới");
                if (temp == 0) {
                    KhachHang kh = new KhachHang(ma, ten, sdt, Date.valueOf(ngaySinh));
                    JOptionPane.showMessageDialog(this, khs.add(kh));
                    showData(listKHCM = khs.getAllCustom());

                }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (txtNgaySinhKH.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày sinh không được để trống");
        } else {
            String ma = txtMaKH.getText();
            String ten = txtTenKH.getText();
            String sdt = txtSdt.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String ngaySinh = sdf.format(txtNgaySinhKH.getDate());
            if (CheckDLKH() == false) {
                var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật");
                if (temp == 0) {

                    KhachHang kh = new KhachHang(ma, ten, sdt, Date.valueOf(ngaySinh));
                    JOptionPane.showMessageDialog(this, khs.update(kh, ma));
                    showData(listKHCM = khs.getAllCustom());

                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnThemCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCVActionPerformed
        ViewChucVu vcv = new ViewChucVu();
        vcv.setVisible(true);


    }//GEN-LAST:event_btnThemCVActionPerformed

    private void txtTimKiemNVCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemNVCaretUpdate
        listNhanVienCustom = nhanVienServiceImpl.SearchNV(txtTimKiemNV.getText());
        showDataNV(listNhanVienCustom);
    }//GEN-LAST:event_txtTimKiemNVCaretUpdate

    private void txtTimKiemKHCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemKHCaretUpdate
        listKHCM = khs.SearchKH(txtTimKiemKH.getText());
        showData(listKHCM);
    }//GEN-LAST:event_txtTimKiemKHCaretUpdate

    private void cbo_locDeGiayItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_locDeGiayItemStateChanged
        // TODO add your handling code here:
        loadTable((String) cbo_locDeGiay.getSelectedItem());
    }//GEN-LAST:event_cbo_locDeGiayItemStateChanged

    private void cbo_locDongSPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_locDongSPItemStateChanged
        // TODO add your handling code here:
        loadTable((String) cbo_locDongSP.getSelectedItem());
    }//GEN-LAST:event_cbo_locDongSPItemStateChanged

    private void cbo_locGiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_locGiaItemStateChanged
        // TODO add your handling code here:
        var batDau = uti.getNam((String) cbo_locGia.getSelectedItem());
        var ketThuc = uti.getThang((String) cbo_locGia.getSelectedItem());
        loadTableByGia(batDau, ketThuc);
    }//GEN-LAST:event_cbo_locGiaItemStateChanged

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        if (checkDL() == false) {
            var temp = JOptionPane.showConfirmDialog(this, "Bạn có chăc muốn thêm mới không ?");
            if (temp == 0) {
                JOptionPane.showMessageDialog(this, chiTietSanPhamService.add(getForm()));
                loadTable(null);
            }
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        if (checkDL() == false) {
            var temp = JOptionPane.showConfirmDialog(this, "Bạn có chăc chắn muốn sửa thông tin không ?");
            if (temp == 0) {
                var row = tbl_CTSanPham.getSelectedRow();
                var chitietSP = getForm();
                chitietSP.setId(chiTietSanPhamService.getIdByIndex(row));
                JOptionPane.showMessageDialog(this, chiTietSanPhamService.update(chitietSP));
                loadTable(null);
            }
        }

    }//GEN-LAST:event_btn_suaActionPerformed

    private void tbl_CTSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_CTSanPhamMouseClicked
        // TODO add your handling code here:
        var row = tbl_CTSanPham.getSelectedRow();
        var temp = chiTietSanPhamService.getById(chiTietSanPhamService.getIdByIndex(row));
        cbo_sanPham.setSelectedItem(temp.getTenSP());
        cbo_mauSac.setSelectedItem(temp.getTenMauSac());
        cbo_deGiay.setSelectedItem(temp.getTenDeGiay());
        cbo_dongSanPham.setSelectedItem(temp.getTenDongSP());
        txt_ngayNhapHang.setText(temp.getNgayNhapHang());
        txt_giaBan.setText(String.valueOf(temp.getDonGia()));
        txt_soLuong.setText(String.valueOf(temp.getSoLuong()));
        txt_kichCo.setText(temp.getKichCo());
        txt_xuatXu.setText(temp.getXuatXu());
        cbo_trangThai.setSelectedIndex(temp.getTrangThai());
    }//GEN-LAST:event_tbl_CTSanPhamMouseClicked

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa thông tin không ?");
        if (temp == 0) {
            var row = tbl_CTSanPham.getSelectedRow();
            var chitietSP = new ChiTietSanPhamHiber();
            chitietSP.setId(chiTietSanPhamService.getIdByIndex(row));
            JOptionPane.showMessageDialog(this, chiTietSanPhamService.delete(chitietSP));
            loadTable(null);
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        // TODO add your handling code here:
        cbo_sanPham.setSelectedIndex(0);
        cbo_mauSac.setSelectedIndex(0);
        cbo_deGiay.setSelectedIndex(0);
        cbo_dongSanPham.setSelectedIndex(0);
        txt_ngayNhapHang.setText(null);
        txt_giaBan.setText(null);
        txt_soLuong.setText(null);
        txt_kichCo.setText(null);
        txt_xuatXu.setText(null);
        cbo_trangThai.setSelectedIndex(0);
    }//GEN-LAST:event_btn_clearActionPerformed

    private void txt_timKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_timKiemCaretUpdate
        // TODO add your handling code here:
        if (txt_timKiem.getText().isEmpty()) {
            loadTable(null);
        }
        loadTable(txt_timKiem.getText());
    }//GEN-LAST:event_txt_timKiemCaretUpdate

    private void txt_timKiemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timKiemFocusGained
        // TODO add your handling code here:
        txt_timKiem.setText("");
    }//GEN-LAST:event_txt_timKiemFocusGained

    private void txt_timKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_timKiemFocusLost
        // TODO add your handling code here:
        txt_timKiem.setText("Tìm Kiếm...");
        loadTable(null);
    }//GEN-LAST:event_txt_timKiemFocusLost

    private void txt_ngayNhapHangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ngayNhapHangFocusGained
        // TODO add your handling code here:
        txt_ngayNhapHang.setText("");
    }//GEN-LAST:event_txt_ngayNhapHangFocusGained

    private void txt_ngayNhapHangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_ngayNhapHangFocusLost
        // TODO add your handling code here:
        txt_ngayNhapHang.setText(java.time.LocalDate.now() + "");
    }//GEN-LAST:event_txt_ngayNhapHangFocusLost

    private void btn_themSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themSPActionPerformed
        // TODO add your handling code here:
        new ViewSanPham().setVisible(true);
    }//GEN-LAST:event_btn_themSPActionPerformed

    private void btn_themMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themMSActionPerformed
        // TODO add your handling code here:
        new ViewMauSac().setVisible(true);
    }//GEN-LAST:event_btn_themMSActionPerformed

    private void btn_themDG1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themDG1ActionPerformed
        // TODO add your handling code here:
        new ViewDeGiay().setVisible(true);
    }//GEN-LAST:event_btn_themDG1ActionPerformed

    private void btn_themDSP1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themDSP1ActionPerformed
        // TODO add your handling code here:
        new ViewDongSp().setVisible(true);
    }//GEN-LAST:event_btn_themDSP1ActionPerformed

    private void cbo_locMauSacItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_locMauSacItemStateChanged
        // TODO add your handling code here:
        loadTable((String) cbo_locMauSac.getSelectedItem());
    }//GEN-LAST:event_cbo_locMauSacItemStateChanged

    private void btnReloadCTSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadCTSPActionPerformed
        loadCBB();
    }//GEN-LAST:event_btnReloadCTSPActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắc chắn muốn thoát");
        if (temp == 0) {
            System.exit(0);
        }

    }//GEN-LAST:event_btnKetThucActionPerformed

    private void btnVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoucherActionPerformed
//        ViewVoucher vvc = new ViewVoucher();
//        vvc.setVisible(true);
        cardLayout.show(pnlcards, "cardVoucher");
    }//GEN-LAST:event_btnVoucherActionPerformed

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

    private void cbo_locGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_locGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_locGiaActionPerformed

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

    private void btnThemVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemVoucherActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ma = txtMaVoucher.getText();
        String ten = txtTenVoucher.getText();
        if (txtNgayBatDau.getDate() == null || txtNgayHetHan.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày không để trống");
        } else {
            java.util.Date bd = (java.util.Date) txtNgayBatDau.getDate();
            java.util.Date kt = (java.util.Date) txtNgayHetHan.getDate();
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(bd);
            c2.setTime(kt);

            long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
            if (noDay < 0) {
                JOptionPane.showConfirmDialog(this, "Ngày hết hạn phải lớn hơn ngày bắt đầu");
            } else {
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

                if (CheckDLVC() == false && CheckTrungMaVC() == false) {
                    
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
    }//GEN-LAST:event_btnThemVoucherActionPerformed

    private void btnSuaVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaVoucherActionPerformed

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ma = txtMaVoucher.getText();
        String ten = txtTenVoucher.getText();
        if (txtNgayBatDau.getDate() == null || txtNgayHetHan.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày không để trống");
        } else {
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

            if (CheckDLVC() == false) {

                var temp = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn sửa");
                if (temp == 0) {

                    Voucher v = new Voucher(ma, ten, mucGiam, Date.valueOf(ngayBD), Date.valueOf(ngayHH), ghiChu, trangThai);
                    JOptionPane.showMessageDialog(this, vcs.update(v, ma));
                    listVoucherCM = vcs.getAllCustom();
                    loadData(listVoucherCM);

                }
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

    private void txtGhiChuCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtGhiChuCaretUpdate
        if (txtGhiChu.getText().isEmpty()) {
            btnDoiSanPham.setEnabled(false);
        } else {
            btnDoiSanPham.setEnabled(true);
        }
    }//GEN-LAST:event_txtGhiChuCaretUpdate

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DailogDoiTra dld = new DailogDoiTra(this, true);
        dld.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        if (dcBatDau.getDate() == null || dcKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu và ngày kết thúc không được để trống");
        } else {

            java.util.Date bd = (java.util.Date) dcBatDau.getDate();
            java.util.Date kt = (java.util.Date) dcKetThuc.getDate();

            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(bd);
            c2.setTime(kt);
            var temp = 0;
            long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);

            if (noDay < 0) {
                JOptionPane.showConfirmDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu");
            } else {

                String batDau = sdf.format(dcBatDau.getDate());
                String ketThuc = sdf.format(dcKetThuc.getDate());
                listHDDT = dts.getHoaDonDoiTraBetween(batDau, ketThuc);
                showDataHDDoiTra(listHDDT);
            }
        }
    }//GEN-LAST:event_btnLocActionPerformed

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

    private void btn_thongKeLBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongKeLBLActionPerformed

        try {

            java.util.Date bd = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(batDau.getText());
            java.util.Date kt = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(ketThuc.getText());

            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();

            c1.setTime(bd);
            c2.setTime(kt);

            long noDay = ((c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000));

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
            Logger.getLogger(ViewThongKe.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btn_thongKeLBLActionPerformed

    private void btn_thongKeDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongKeDTActionPerformed
        // TODO add your handling code here:
        if (checkDLTK(txt_namDT) == false) {
            setBieuDoDTTheoNam(pnl_doanhThu, txt_namDT.getText());
        }
    }//GEN-LAST:event_btn_thongKeDTActionPerformed

    private void btn_thongKeKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongKeKHActionPerformed
        // TODO add your handling code here:
        if (checkDLTK(txt_namKH) == false) {
            setBieuDoKHTheoNam(pnl_khachHang, txt_namKH.getText());
        }
    }//GEN-LAST:event_btn_thongKeKHActionPerformed

    private void btn_thongKeDoiTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thongKeDoiTraActionPerformed
        // TODO add your handling code here:
        setTronDoiTraTheoNam(pnl_doiTra, batDauDoiTra.getText(), ketThucDoiTra.getText());
        setDoiTraChatLuong(batDauDoiTra.getText(), ketThucDoiTra.getText());
        setDoiTraKichCo(batDauDoiTra.getText(), ketThucDoiTra.getText());
        setDoiTraMauSac(batDauDoiTra.getText(), ketThucDoiTra.getText());
    }//GEN-LAST:event_btn_thongKeDoiTraActionPerformed

    private void btnLoadNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadNhanVienActionPerformed
        listChucVus = chucVuService.getAllCustom();
        int itemCount3 = cbbChucVu.getItemCount();
        for (int i = 0; i < itemCount3; i++) {
            cbbChucVu.removeItemAt(0);
        }
        listChucVus.forEach(chucVu -> cbbChucVu.addItem(chucVu.getMa()));
        cbbChucVu.setSelectedIndex(0);


    }//GEN-LAST:event_btnLoadNhanVienActionPerformed

    private void txtTimKiemKHFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemKHFocusGained
        txtTimKiemKH.setText("");
    }//GEN-LAST:event_txtTimKiemKHFocusGained

    private void btnAddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseEntered
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnAddMouseEntered

    private void btnUpdateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseEntered
        // TODO add your handling code here:
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnUpdateMouseEntered

    private void btnDeleteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseEntered
        // TODO add your handling code here:
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnDeleteMouseEntered

    private void btnClearMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearMouseEntered
        // TODO add your handling code here:
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnClearMouseEntered

    private void tblKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseEntered
        // TODO add your handling code here:
        tblKhachHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tblKhachHangMouseEntered

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

    private void btnThemCVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemCVMouseEntered
        btnThemCV.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnThemCVMouseEntered

    private void btnLoadNhanVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadNhanVienMouseEntered
        // TODO add your handling code here:
        btnLoadNhanVien.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnLoadNhanVienMouseEntered

    private void txtNgaySinhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgaySinhMouseEntered
        // TODO add your handling code here:
        txtNgaySinh.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_txtNgaySinhMouseEntered

    private void btnAddNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddNVMouseEntered
        // TODO add your handling code here:
        btnAddNV.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnAddNVMouseEntered

    private void btnUpdateNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateNVMouseEntered
        // TODO add your handling code here:
        btnUpdateNV.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnUpdateNVMouseEntered

    private void btnDeleteNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteNVMouseEntered
        // TODO add your handling code here:
        btnDeleteNV.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnDeleteNVMouseEntered

    private void btnClearNVMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClearNVMouseEntered
        // TODO add your handling code here:
        btnClearNV.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnClearNVMouseEntered

    private void tbHienThiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHienThiMouseEntered
        // TODO add your handling code here:
        tbHienThi.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_tbHienThiMouseEntered

    private void txtTimKiemNVFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemNVFocusGained
        txtTimKiemNV.setText("");
    }//GEN-LAST:event_txtTimKiemNVFocusGained

    private void txtTimKiemDSSPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemDSSPFocusGained
        txtTimKiemDSSP.setText("");
    }//GEN-LAST:event_txtTimKiemDSSPFocusGained

    private void txtTimKiemDoiTraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemDoiTraFocusGained
        txtTimKiemDoiTra.setText("");
    }//GEN-LAST:event_txtTimKiemDoiTraFocusGained

    private void txtTimKiemVoucherFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemVoucherFocusGained
        // TODO add your handling code here:
        txtTimKiemVoucher.setText("");
    }//GEN-LAST:event_txtTimKiemVoucherFocusGained

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        cardLayout.show(pnlcards, "cardMain");
    }//GEN-LAST:event_jPanel2MouseClicked

    private void btnKhachhangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachhangMouseEntered
        btnKhachhang.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnKhachhangMouseEntered

    private void btnBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseEntered
        // TODO add your handling code here:
        btnBanHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnBanHangMouseEntered

    private void btnNhanVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMouseEntered
        // TODO add your handling code here:
        btnNhanVien.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnNhanVienMouseEntered

    private void btnDoiTraMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiTraMouseEntered
        // TODO add your handling code here:
        btnDoiTra.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnDoiTraMouseEntered

    private void btnHoaDonBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonBanHangMouseEntered
        // TODO add your handling code here:
        btnHoaDonBanHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnHoaDonBanHangMouseEntered

    private void btnSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseEntered
        // TODO add your handling code here:
        btnSanPham.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnSanPhamMouseEntered

    private void btnThongKeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseEntered
        // TODO add your handling code here:
        btnThongKe.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnThongKeMouseEntered

    private void btnVoucherMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVoucherMouseEntered
        // TODO add your handling code here:
        btnVoucher.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnVoucherMouseEntered

    private void btnKetThucMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKetThucMouseEntered
        // TODO add your handling code here:
        btnKetThuc.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnKetThucMouseEntered

    private void btnLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLogoutMouseEntered
        // TODO add your handling code here:
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btnLogoutMouseEntered

    private void radioNuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioNuMouseEntered
        radioNu.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_radioNuMouseEntered

    private void radioNamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioNamMouseEntered
        radioNam.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_radioNamMouseEntered

    private void cbbChucVuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbChucVuMouseEntered
        cbbChucVu.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_cbbChucVuMouseEntered

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
            java.util.logging.Logger.getLogger(HomeQuanLyBG.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeQuanLyBG.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeQuanLyBG.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeQuanLyBG.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeQuanLyBG().setVisible(true);
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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddNV;
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnCapNhatSP;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearNV;
    private javax.swing.JButton btnClearVoucher;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteNV;
    private javax.swing.JButton btnDoiSanPham;
    private javax.swing.JButton btnDoiTra;
    private javax.swing.JButton btnHoaDonBanHang;
    private javax.swing.JButton btnHuyHoaDon;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JButton btnKhachhang;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLoadNhanVien;
    private javax.swing.JButton btnLoadVoucher;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnLocHoaDon;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnReloadBH;
    private javax.swing.JButton btnReloadCTSP;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnSuaVoucher;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThayDoi;
    private javax.swing.JButton btnThemCV;
    private javax.swing.JButton btnThemKHBH;
    private javax.swing.JButton btnThemVoucher;
    private javax.swing.JButton btnThongKe;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateNV;
    private javax.swing.JButton btnVoucher;
    private javax.swing.JButton btnXoaSanPham;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_themDG1;
    private javax.swing.JButton btn_themDSP1;
    private javax.swing.JButton btn_themMS;
    private javax.swing.JButton btn_themSP;
    private javax.swing.JButton btn_thongKeDT;
    private javax.swing.JButton btn_thongKeDoiTra;
    private javax.swing.JButton btn_thongKeKH;
    private javax.swing.JButton btn_thongKeLBL;
    private javax.swing.JButton btn_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChucVu;
    private javax.swing.JComboBox<String> cbbDGBH;
    private javax.swing.JComboBox<String> cbbDSPBH;
    private javax.swing.JComboBox<String> cbbGiaGiam;
    private javax.swing.JComboBox<String> cbbLiDoDoi;
    private javax.swing.JComboBox<String> cbbLocTrangThaiHD;
    private javax.swing.JComboBox<String> cbbMSBH;
    private javax.swing.JComboBox<String> cbbMucGiamGia;
    private javax.swing.JComboBox<String> cbbNhanVienBH;
    private javax.swing.JComboBox<String> cbbSoDienThoai;
    private javax.swing.JComboBox<String> cbbTrangThaiVoucher;
    private javax.swing.JComboBox<String> cbo_deGiay;
    private javax.swing.JComboBox<String> cbo_dongSanPham;
    private javax.swing.JComboBox<String> cbo_locDeGiay;
    private javax.swing.JComboBox<String> cbo_locDongSP;
    private javax.swing.JComboBox<String> cbo_locGia;
    private javax.swing.JComboBox<String> cbo_locMauSac;
    private javax.swing.JComboBox<String> cbo_mauSac;
    private javax.swing.JComboBox<String> cbo_sanPham;
    private javax.swing.JComboBox<String> cbo_trangThai;
    private com.toedter.calendar.JDateChooser date_batDau;
    private com.toedter.calendar.JDateChooser date_doiTraBD;
    private com.toedter.calendar.JDateChooser date_doiTraKT;
    private com.toedter.calendar.JDateChooser date_ketThuc;
    private com.toedter.calendar.JDateChooser dcBatDau;
    private com.toedter.calendar.JDateChooser dcBatDauHD;
    private com.toedter.calendar.JDateChooser dcKetThuc;
    private com.toedter.calendar.JDateChooser dcKetThucHD;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel labelTT;
    private javax.swing.JLabel lbTenChucVu;
    private javax.swing.JLabel lbTenCuaHang;
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
    private javax.swing.JLabel lbl_deGiay;
    private javax.swing.JLabel lbl_doanhThu;
    private javax.swing.JLabel lbl_doiTra;
    private javax.swing.JLabel lbl_doiTraChatLuong;
    private javax.swing.JLabel lbl_doiTraKichCo;
    private javax.swing.JLabel lbl_doiTraMauSac;
    private javax.swing.JLabel lbl_dongSP;
    private javax.swing.JLabel lbl_giaBan;
    private javax.swing.JLabel lbl_hoaDon;
    private javax.swing.JLabel lbl_khachHang;
    private javax.swing.JLabel lbl_kichCo;
    private javax.swing.JLabel lbl_locDeGiay;
    private javax.swing.JLabel lbl_locDongSP;
    private javax.swing.JLabel lbl_locGiaBan;
    private javax.swing.JLabel lbl_locMauSac;
    private javax.swing.JLabel lbl_mauSac;
    private javax.swing.JLabel lbl_ngayNhapHang;
    private javax.swing.JLabel lbl_sanPham1;
    private javax.swing.JLabel lbl_sanPhamct;
    private javax.swing.JLabel lbl_soLuong;
    private javax.swing.JLabel lbl_trangThai;
    private javax.swing.JLabel lbl_xuatXu;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlDoiTra;
    private javax.swing.JPanel pnlHoaDon;
    private javax.swing.JPanel pnlKhachHang;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlNhanVien;
    private javax.swing.JPanel pnlSanPhamChiTiet;
    private javax.swing.JPanel pnlThongKe;
    private javax.swing.JPanel pnlVoucher;
    private javax.swing.JPanel pnlWebcam;
    private javax.swing.JPanel pnl_doanhThu;
    private javax.swing.JPanel pnl_doiTra;
    private javax.swing.JPanel pnl_khachHang;
    private javax.swing.JPanel pnl_khachHangTron;
    private javax.swing.JPanel pnlcards;
    private javax.swing.JRadioButton radioNam;
    private javax.swing.JRadioButton radioNu;
    private javax.swing.JTable tbHienThi;
    private javax.swing.JTable tblDoiTra;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHDCTDoiTra;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonBanHang;
    private javax.swing.JTable tblHoaDonChiTietBH;
    private javax.swing.JTable tblHoaDonDoiTra;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTable tbl_CTSanPham;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextArea txtGhiChuVoucher;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaVoucher;
    private javax.swing.JTextField txtMatKhau;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayHetHan;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private com.toedter.calendar.JDateChooser txtNgaySinhKH;
    private javax.swing.JTextField txtSdt;
    private javax.swing.JTextField txtSdtNV;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTenKhachHangBH;
    private javax.swing.JLabel txtTenQuanLy;
    private javax.swing.JTextField txtTenVoucher;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiemDSSP;
    private javax.swing.JTextField txtTimKiemDoiTra;
    private javax.swing.JTextField txtTimKiemHoaDon;
    private javax.swing.JTextField txtTimKiemKH;
    private javax.swing.JTextField txtTimKiemNV;
    private javax.swing.JTextField txtTimKiemVoucher;
    private javax.swing.JTextField txt_giaBan;
    private javax.swing.JTextField txt_kichCo;
    private javax.swing.JTextField txt_namDT;
    private javax.swing.JTextField txt_namKH;
    private javax.swing.JTextField txt_ngayNhapHang;
    private javax.swing.JTextField txt_soLuong;
    private javax.swing.JTextField txt_timKiem;
    private javax.swing.JTextField txt_xuatXu;
    // End of variables declaration//GEN-END:variables
}
