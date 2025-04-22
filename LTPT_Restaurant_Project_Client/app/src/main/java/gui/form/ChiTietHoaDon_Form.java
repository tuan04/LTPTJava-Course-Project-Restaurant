/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.form;



import dao.Ban_DAO;
import dao.ChiTietHoaDon_DAO;
import dao.DichVu_DAO;
import dao.DonDatBan_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.KhuyenMai_DAO;
import dao.LoaiBan_DAO;
import dao.LoaiKhachHang_DAO;
import dao.MonAn_DAO;
import dao.NhanVien_DAO;
import model.Ban;
import model.ChiTietHoaDon;
//import model.DichVu;
import model.DonDatBan;
import model.HoaDon;
import model.KhachHang;
import model.KhuyenMai;
import model.LoaiBan;
import model.LoaiKhachHang;
import gui.swing.table.TableCustom;

import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import print.ReportManager;
import print.model.FieldReportHoaDon;
import print.model.ParameterReportHoaDon;


/**
 *
 * @author THANHTRI
 */
public class ChiTietHoaDon_Form extends javax.swing.JFrame {
    private HoaDon hd = null;
    private ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
    private MonAn_DAO monAn_dao = new MonAn_DAO();
    private HoaDon_DAO hd_dao = new HoaDon_DAO();
    private NhanVien_DAO nv_dao = new NhanVien_DAO();
    private Ban_DAO ban_dao = new Ban_DAO();
    private KhuyenMai_DAO km_dao = new KhuyenMai_DAO();
    private LoaiBan_DAO lb_dao = new LoaiBan_DAO();
    private DichVu_DAO dv_dao = new DichVu_DAO();
    private KhachHang_DAO kh_dao = new KhachHang_DAO();
    private LoaiKhachHang_DAO loaiKh_dao = new LoaiKhachHang_DAO();
    private DonDatBan_DAO ddb_dao = new DonDatBan_DAO();
    private ArrayList<ChiTietHoaDon> list = new ArrayList<>();
    /**
     * Creates new form ChiTietHoaDon_Form
     */
    
    public ChiTietHoaDon_Form(String maHD,String soBan, String ngayLap, String tenNV, String tenKH, String maKM, String trangThai, float tongTien, float VAT, float giamGiaKM, float giaDV, float giamGiaTV, float giamGiaNS, float tienCoc, String maLoaiBan, float tongTienTT, ArrayList<Object[]> list,String gioVao,String giaRa) {
        initComponents();
        load(maHD,soBan,ngayLap,tenNV,tenKH,maKM, trangThai,tongTien,VAT,giamGiaKM, giaDV,giamGiaTV,giamGiaNS, tienCoc, maLoaiBan, tongTienTT, list,gioVao,giaRa);
        customTable();
    }
    


    public void load(String maHD,String soBan, String ngayLap, String tenNV, String tenKH, String maKM, String trangThai, float tongTien, float VAT, float giamGiaKM, float giaDV, float giamGiaTV, float giamGiaNS, float tienCoc, String maLoaiBan, float tongTienTT, ArrayList<Object[]> list,String gioVao,String gioRa) {
        this.hd = hd_dao.getHD(maHD);
        this.list = cthd_dao.getList(maHD);
        txtSoBan.setText(soBan);
        txtTenNV.setText(tenNV);
        txtTenKH.setText(tenKH);
        txtTongTien.setText(dinhDangVND(tongTien));
        txtPV.setText(dinhDangVND(giaDV));
        txtSN.setText(dinhDangVND(giamGiaNS));
        txtVIP.setText(dinhDangVND(Double.parseDouble(maLoaiBan)));
        txtTV.setText(dinhDangVND(giamGiaTV));
        txtVAT.setText(dinhDangVND(VAT));
        txtGiamGia.setText(dinhDangVND(giamGiaKM));
        txtTienCoc.setText(dinhDangVND(tienCoc));
        txtMaHD.setText(maHD);

        txtTongTienTT.setText(dinhDangVND(tongTienTT));
        txtGioVao.setText(gioVao);
        txtGioRa.setText(gioRa);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.getDataVector().removeAllElements();
        for (Object[] ob : list) {
            ob[3] = dinhDangVND(Double.parseDouble(ob[3].toString()));
            ob[2] = dinhDangVND(Double.parseDouble(ob[2].toString()));
            ob[5] = dinhDangVND(Double.parseDouble(ob[5].toString()));

            model.addRow(ob);
        }
    }

    
    private void customTable() {
        TableCustom.apply(table_sp, TableCustom.TableType.MULTI_LINE);
        table.getTableHeader().setFont(new Font("Sanserif", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(50, 50, 50));
        table.repaint();
    }

    public String dinhDangVND(double x){
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formatted = decimalFormat.format(x).replace(",", " ");

        return formatted + " VNĐ";
    }

    public int huyDinhDangVND(String s){
        String newString[] = s.split("\\s+");
        String newS = "";
        for (int i = 0; i < newString.length - 1; i++) {
            newS += newString[i];
        }

        return Integer.parseInt(newS);
    }
    
    public String currencyFormat(double price){
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(price);
    }
    
    public double currencyFormatToDouble(String currency){
        String str = currency.replaceAll("[^\\d]", "");
        return Double.parseDouble(str);
    }
    
    public String formatLocalDateTime(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateTime.format(formatter);
    }
    
    public String dateFormat(LocalDate dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateTime.format(formatter);
    }
    
    public String timeFormat(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return dateTime.format(formatter);
    }

    public String banLabel(Ban ban){
        LoaiBan lb = lb_dao.getLoaiBanTheoMa(ban.getLoaiBan().getMaLB());
        return "Bàn " + ban.getSoBan() + " / " + lb.getTenLB();
    }
    
    public void inHoaDon()throws JRException{
        int stt = 0;
        ArrayList<FieldReportHoaDon> fields = new ArrayList<>();
        for(ChiTietHoaDon ct : list){
            stt ++;
            String name = monAn_dao.getMonAnTheoMa(ct.getMonAn().getMaMA()).getTenMA();
            String qty = String.valueOf(ct.getSoLuong());
            String price = currencyFormat(monAn_dao.getMonAnTheoMa(ct.getMonAn().getMaMA()).getGia());
            String discountPrice = currencyFormat(ct.getGiaSauGiam());
            String total = currencyFormat(ct.getThanhTien());

            fields.add(new FieldReportHoaDon(String.valueOf(stt), name, qty, price, discountPrice, total));
        }
        String orderID = hd.getMaHD();
        String table = banLabel(ban_dao.getBan(hd.getBan().getMaBan()));
        String cashier = nv_dao.getNV(hd.getNhanVien().getMaNV()).getHoTenNV();
        String date = dateFormat(hd.getNgayLap());
        String checkInTime = timeFormat(hd.getGioVao());
        String checkOutTime = timeFormat(hd.getGioRa());
        String customer = "";
        String giamGiaThanhVienPer = "0%";
        String giamGiaThanhVien = currencyFormat(0);
        double giamGiaThanhVien1 = 0;
        String giamGiaSNPer = "0%";
        String giamGiaSN = currencyFormat(0);
        double giamGiaSN1 = 0;
        KhachHang kh = kh_dao.getKH(hd.getKhachHang().getMaKH());
        if(kh != null){
            LoaiKhachHang loaiKh = loaiKh_dao.TimLoaiKhachHangTim(kh.getLoaiKhachHang().getMaLoaiKH());
            customer = kh.getTenKH();
            giamGiaThanhVienPer = loaiKh.getGiamGiaThanhVien() + "%";
            giamGiaThanhVien = currencyFormat(hd.getTongTien() * loaiKh.getGiamGiaThanhVien() / 100);
            giamGiaThanhVien1 = hd.getTongTien() * loaiKh.getGiamGiaThanhVien() / 100;
            if(kh.getNgaySinh().equals(LocalDate.now())){
                giamGiaSNPer = loaiKh.getGiamGiaSinhNhat()+ "%";
            }
            giamGiaSN = currencyFormat(hd.getTongTien() * loaiKh.getGiamGiaSinhNhat() / 100);
            giamGiaSN1 = hd.getTongTien() * loaiKh.getGiamGiaSinhNhat() / 100;
        }
        String discountName = "";
        String perDiscount = "";
        String discount = "";
        double discount1 = 0;
        KhuyenMai km = km_dao.getKhuyenMaiTheoMa(hd.getKhuyenMai().getMaKM());
        if(km != null){
            discount1 = hd.getTongTien() * km.getGiamGia() / 100;
            discountName = km.getTenKM();
            perDiscount = km.getGiamGia() + "%";
            discount = currencyFormat(hd.getTongTien() * km.getGiamGia()/100);
        }
        DonDatBan ddb = ddb_dao.getDDB(hd.getDonDatBan().getMaDDB());
        String tienCoc = currencyFormat(0);
        if(ddb != null){
            tienCoc = currencyFormat(ddb.getTienCoc());
        }
        String total = currencyFormat(hd.getTongTien());
        String totalDiscount = currencyFormat(discount1 + hd.getGiamGiaSinhNhat() + hd.getGiamGiaThanhVien());
        String totalPay = currencyFormat(hd.getTongTienTT());
        DichVu dv = dv_dao.getDV(hd.getDichVu().getMaDV());
        String svc = currencyFormat(hd.getTongTien() * dv.getPV()/ 100);
        String vat = currencyFormat(hd.getTongTien() * dv.getVAT() / 100);
        String phongVip = currencyFormat(0);
        Ban ban = ban_dao.getBan(hd.getBan().getMaBan());
        LoaiBan lb = lb_dao.getLoaiBanTheoMa(ban.getLoaiBan().getMaLB());
        if(lb.getTenLB().equals("Phòng VIP")){
            phongVip = currencyFormat(dv.getPhongVIP());
        }
        ParameterReportHoaDon dataprint = new ParameterReportHoaDon(orderID, table, checkInTime,checkOutTime, date, customer, cashier, total, totalDiscount, svc, vat, totalPay, giamGiaThanhVienPer, giamGiaThanhVien, giamGiaSNPer, giamGiaSN, discountName, perDiscount, discount, tienCoc, phongVip,fields);
        ReportManager.getInstance().printReportThanhToanNew(dataprint);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        gradientPanel1 = new gui.component.GradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtGioVao = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtGioRa = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JLabel();
        table_sp = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtVAT = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtPV = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtTV = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtSN = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtTienCoc = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtVIP = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtTongTienTT = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        txtSoBan = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtGiamGia = new javax.swing.JLabel();
        button1 = new gui.component.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout gradientPanel1Layout = new javax.swing.GroupLayout(gradientPanel1);
        gradientPanel1.setLayout(gradientPanel1Layout);
        gradientPanel1Layout.setHorizontalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        gradientPanel1Layout.setVerticalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông Tin Chi Tiết Hóa Đơn");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("Mã hóa đơn:");

        txtMaHD.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMaHD.setText("...");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setText("Giờ vào:");

        txtGioVao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtGioVao.setText("...");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setText("Nhân viên:");

        txtTenNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTenNV.setText("...");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel9.setText("Giờ ra:");

        txtGioRa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtGioRa.setText("...");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel11.setText("Khách hàng:");

        txtTenKH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTenKH.setText("...");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên món", "Giá", "Giá sau giảm", "Số lượng", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_sp.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel13.setText("Tổng tiền:");

        txtTongTien.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTongTien.setText("...");

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel15.setText("Phí VAT:");

        txtVAT.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtVAT.setText("...");

        jLabel17.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel17.setText("Phí phục vụ:");

        txtPV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtPV.setText("...");

        jLabel19.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel19.setText("Thành viên:");

        txtTV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTV.setText("...");

        jLabel21.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel21.setText("Ưu đại SN:");

        txtSN.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtSN.setText("...");

        jLabel23.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel23.setText("Tiền cọc:");

        txtTienCoc.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTienCoc.setText("...");

        jLabel25.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel25.setText("Phí bàn VIP:");

        txtVIP.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtVIP.setText("...");

        jLabel27.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel27.setText("Tổng Tiền Thanh Toán:");

        txtTongTienTT.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        txtTongTienTT.setText("...");

        jLabel29.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel29.setText("Số bàn:");

        txtSoBan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtSoBan.setText("...");

        jLabel31.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel31.setText("Giảm giá:");

        txtGiamGia.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtGiamGia.setText("...");

        button1.setBackground(new java.awt.Color(0, 0, 0));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("In hóa đơn");
        button1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtTenNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMaHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGioVao, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGioRa, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
            .addComponent(table_sp, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTienTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVAT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienCoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtVIP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(gradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaHD)
                    .addComponent(jLabel5)
                    .addComponent(txtGioVao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTenNV)
                    .addComponent(jLabel9)
                    .addComponent(txtGioRa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTenKH)
                    .addComponent(jLabel29)
                    .addComponent(txtSoBan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(table_sp, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtVAT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtPV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtVIP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtTV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtSN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtGiamGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtTienCoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtTongTienTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        try {
            ReportManager.getInstance().complieReport();
        } catch (JRException ex) {
            ex.printStackTrace();
        }

        
        try {
            inHoaDon();
        } catch (JRException ex) {
            Logger.getLogger(ChiTietHoaDon_Form.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button1ActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button button1;
    private gui.component.GradientPanel gradientPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane table_sp;
    private javax.swing.JLabel txtGiamGia;
    private javax.swing.JLabel txtGioRa;
    private javax.swing.JLabel txtGioVao;
    private javax.swing.JLabel txtMaHD;
    private javax.swing.JLabel txtPV;
    private javax.swing.JLabel txtSN;
    private javax.swing.JLabel txtSoBan;
    private javax.swing.JLabel txtTV;
    private javax.swing.JLabel txtTenKH;
    private javax.swing.JLabel txtTenNV;
    private javax.swing.JLabel txtTienCoc;
    private javax.swing.JLabel txtTongTien;
    private javax.swing.JLabel txtTongTienTT;
    private javax.swing.JLabel txtVAT;
    private javax.swing.JLabel txtVIP;
    // End of variables declaration//GEN-END:variables
}
