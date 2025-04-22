/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;


import model.HoaDon;
import model.NhanVien;
import gui.component.Header;
import gui.main.Admin_DashBoard;
import gui.main.ThuNgan_DashBoard;
import gui.model.ModelChart;
import javax.swing.table.DefaultTableModel;
import gui.swing.table.TableCustom;
import gui.swing.table.cell.ActionCell;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.HoaDonService;
import service.NhanVienService;
import rmi.RMIClientManager;

/**
 *
 * @author THANHTRI
 */
public class KetToanThuNgan_PN extends javax.swing.JPanel {

    /**
     * s
     * Creates new form pn_ThongKeDoanhThu
     */
    private DefaultTableModel modelTable;
    private HoaDonService hoaDon_DAO;
    private String maNV;
    private String tenNV;
    private String today;
    private double tongGGTV = 0;
    private double tongGGSN = 0;
    private double tongDT = 0;
    private int tongSLDon = 0;
    private NhanVien nv;
    private NhanVienService nhanVien_dao;
    
    public KetToanThuNgan_PN(Admin_DashBoard dashboard) throws Exception {
        this.hoaDon_DAO=RMIClientManager.getInstance().getHoaDonService();
        this.nhanVien_dao=RMIClientManager.getInstance().getNhanVienService();
        initComponents();
        customTable();
        this.nv = nhanVien_dao.findById(dashboard.getHeader().getTextMaNV());
        init();
        loadTable();
        loadTXT();
        batSuKienTable();
        chart();
    }

    public KetToanThuNgan_PN(ThuNgan_DashBoard dashboard) throws Exception {
        this.hoaDon_DAO=RMIClientManager.getInstance().getHoaDonService();
        this.nhanVien_dao=RMIClientManager.getInstance().getNhanVienService();
        initComponents();
        customTable();
        this.nv = nhanVien_dao.findById(dashboard.getHeader().getTextMaNV());
        init();
        loadTable();
        loadTXT();
        batSuKienTable();
        chart();
    }

    private String currencyFormat(double price) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(price);
    }

    private double currencyFormatToDouble(String currency) {
        String str = currency.replaceAll("[^\\d]", "");
        return Double.parseDouble(str);
    }

    private void customTable() {
        TableCustom.apply(table_sp, TableCustom.TableType.MULTI_LINE);
        table.getTableHeader().setFont(new Font("Sanserif", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(50, 50, 50));
        table.repaint();
    }

    public void setCellRender() {
        table.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                if (value instanceof JLabel) {
                    JLabel lbl = (JLabel) value;
                    setIcon(lbl.getIcon());  // Thiết lập icon từ JLabel
                    setText("");  // Bỏ text để chỉ hiển thị icon
                } else {
                    super.setValue(value);  // Nếu không phải JLabel, hiển thị bình thường
                }
            }
        });
    }

    private void init() {
        setCellRender();
        maNV = nv.getMaNV();
        tenNV = nv.getTenNV();
        today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        modelTable = (DefaultTableModel) table.getModel();
    }

    private void loadTable() throws RemoteException {
      List<HoaDon> list = hoaDon_DAO.todayList(maNV, today);
        int i = 1;
        if (list != null) {
            for (HoaDon x : list) {
                modelTable.addRow(new Object[]{i, x.getMaHD(), currencyFormat(x.getTongTien()), currencyFormat(x.getTongTienGiamGia()), currencyFormat(x.getTongTienGiamGia()), currencyFormat(x.getTongTienThanhToan()), new ActionCell().createActionLabel(table, 6, "/gui/icon/icons8-eye-20.png")});
                ++i;
            }
        }
        tongSLDon = modelTable.getRowCount();
    }

    private void loadTXT() {
        if (modelTable.getRowCount() > 0) {
            for (int i = 0; i < modelTable.getRowCount(); i++) {
                tongGGTV += currencyFormatToDouble(modelTable.getValueAt(i, 3).toString());
                tongGGSN += currencyFormatToDouble(modelTable.getValueAt(i, 4).toString());
                tongDT += currencyFormatToDouble(modelTable.getValueAt(i, 5).toString());
            }
        }
        txtDate.setText(today);
        txtMaNV.setText(maNV);
        txtTenNV.setText(tenNV);
        txtTongDoanhThu.setText(currencyFormat(tongDT));
        txtTongGGTV.setText(currencyFormat(tongGGTV));
        txtTongGGSN.setText(currencyFormat(tongGGSN));
        txtTongHD.setText(String.valueOf(tongSLDon));
    }

    public final void batSuKienTable() {
        // Bắt sự kiện nhấp chuột vào bảng
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                if (column == 6 && row >= 0) {
                    String maHD = table.getValueAt(row, 1).toString();
                    try {
                        suKienXemChiTiet(maHD);
                    } catch (RemoteException ex) {
                        Logger.getLogger(KetToanThuNgan_PN.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
    }

    public void suKienXemChiTiet(String maHD) throws RemoteException {
        Object[] ob = (Object[]) hoaDon_DAO.timKiemHD(maHD);
        ArrayList<Object[]> list = (ArrayList<Object[]>) hoaDon_DAO.timKiemCTHD(maHD);

        String soBan = ob[0].toString();
        String ngayLap = ob[1].toString();
        String tenNV = "";
        if (ob[2] != null) {
            tenNV = ob[2].toString();
        }
        String tenKH = ob[3] == null ? "" : ob[3].toString();
        String maKM = ob[4] == null ? "" : ob[4].toString();
        String trangThai = ob[5].toString() == "0" ? "Chưa thanh toán" : "Đã thanh toán";
        float tongTien = Float.parseFloat(ob[6].toString());
        float VAT = Float.parseFloat(ob[7].toString());
        float giamGiaKM = ob[8] == null ? 0 : Float.parseFloat(ob[8].toString());
        float giaDV = Float.parseFloat(ob[9].toString());
        float giamGiaTV = Float.parseFloat(ob[10].toString());
        float giamGiaNS = Float.parseFloat(ob[11].toString());
        float tienCoc = ob[12] == null ? 0 : Float.parseFloat(ob[12].toString());
        String maLoaiBan = ob[13].toString();
        if (maLoaiBan.equals("LB001") == true || maLoaiBan.equals("LB002") == true) {
            maLoaiBan = "0";
        } else {
            maLoaiBan = "100000";
        }
        float tongTienTT = Float.parseFloat(ob[14].toString());
        String gioVao = ob[15].toString();
        String gioRa = ob[16].toString();
        ChiTietHoaDon_Form form = new ChiTietHoaDon_Form(maHD, soBan, ngayLap, tenNV, tenKH, maKM, trangThai, tongTien, VAT, giamGiaKM, giaDV, giamGiaTV, giamGiaNS, tienCoc, maLoaiBan, tongTienTT, list, gioVao, gioRa);
        form.setVisible(true);
    }

    private void chart() {
        chart1.addLegend("Kết ca trong ngày " + today, Color.decode("#33CCFF"));
        chart1.addData(new ModelChart("Tổng Số Đơn", new double[]{tongSLDon}));
        chart1.addData(new ModelChart("Tổng Giảm Giá TV", new double[]{tongGGTV}));
        chart1.addData(new ModelChart("Tổng Giảm Giá SN", new double[]{tongGGSN}));
        chart1.addData(new ModelChart("Tổng Doanh Thu", new double[]{tongDT}));
        chart1.start();
    }

    private void xuatExcel() {
        if (modelTable.getRowCount() > 0) {
            String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();

            // Khởi tạo JFileChooser
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Chọn nơi lưu file Excel");

            // Đặt thư mục mặc định là Desktop
            jFileChooser.setCurrentDirectory(desktopDir);

            jFileChooser.setSelectedFile(new File("KetToanThuNgan-" + now + ".xlsx")); // Đặt tên file mặc định

            // Hiện thị hộp thoại lưu file
            int userSelection = jFileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                System.out.println("Đường dẫn file: " + file.getAbsolutePath());

                try (Workbook workbook = new XSSFWorkbook()) {
                    Sheet sheet1 = workbook.createSheet("Thông tin");

                    // Tạo dòng tiêu đề
                    Row titleRow = sheet1.createRow(1);
                    Cell titleCell = titleRow.createCell(0);
                    titleCell.setCellValue("Báo Cáo Kết Toán Của Lễ Tân");

                    // Định dạng cho tiêu đề (in đậm và căn giữa)
                    CellStyle titleStyle = workbook.createCellStyle();
                    org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
                    titleFont.setBold(true);
                    titleFont.setFontHeightInPoints((short) 14);
                    titleStyle.setFont(titleFont);
                    titleStyle.setAlignment(HorizontalAlignment.CENTER);
                    titleCell.setCellStyle(titleStyle);

                    CellStyle numberStyle = workbook.createCellStyle();
                    DataFormat format = workbook.createDataFormat();
                    numberStyle.setDataFormat(format.getFormat("#,##0"));

                    // Hợp nhất ô cho tiêu đề
                    sheet1.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));

                    Row dateRow = sheet1.createRow(3);
                    Cell dateCell = dateRow.createCell(0);
                    dateCell.setCellValue(jLabel1.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
                    Cell dateValueCell = dateRow.createCell(2);
                    dateValueCell.setCellValue(txtDate.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));

                    Row maNVRow = sheet1.createRow(4);
                    Cell maNVCell = maNVRow.createCell(0);
                    maNVCell.setCellValue(jLabel3.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
                    Cell maNVValueCell = maNVRow.createCell(2);
                    maNVValueCell.setCellValue(txtMaNV.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(4, 4, 2, 3));

                    Row tenNVRow = sheet1.createRow(5);
                    Cell tenNVCell = tenNVRow.createCell(0);
                    tenNVCell.setCellValue(jLabel9.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(5, 5, 0, 1));
                    Cell tenNVValueCell = tenNVRow.createCell(2);
                    tenNVValueCell.setCellValue(txtTenNV.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(5, 5, 2, 3));

                    
                    Row tongDTRow = sheet1.createRow(6);
                    Cell tongDTCell = tongDTRow.createCell(0);
                    tongDTCell.setCellValue(jLabel11.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));
                    Cell tongDTValueCell = tongDTRow.createCell(2);
                    tongDTValueCell.setCellValue(currencyFormatToDouble(txtTongDoanhThu.getText()) + "");
                    sheet1.addMergedRegion(new CellRangeAddress(6, 6, 2, 3));

                    Row tongGGTVRow = sheet1.createRow(7);
                    Cell tongGGTVCell = tongGGTVRow.createCell(0);
                    tongGGTVCell.setCellValue(jLabel13.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(7, 7, 0, 1));
                    Cell tongGGTVValueCell = tongGGTVRow.createCell(2);
                    tongGGTVValueCell.setCellValue(currencyFormatToDouble(txtTongGGTV.getText()) + "");
                    sheet1.addMergedRegion(new CellRangeAddress(7, 7, 2, 3));

                    Row tongGGSNRow = sheet1.createRow(8);
                    Cell tongGGSNRowCell = tongGGSNRow.createCell(0);
                    tongGGSNRowCell.setCellValue(jLabel5.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(8, 8, 0, 1));
                    Cell tongGGSNRowValueCell = tongGGSNRow.createCell(2);
                    tongGGSNRowValueCell.setCellValue(currencyFormatToDouble(txtTongGGSN.getText()) + "");
                    sheet1.addMergedRegion(new CellRangeAddress(8, 8, 2, 3));

                    
                    Row tongSoHDRow = sheet1.createRow(9);
                    Cell tongSoHDCell = tongSoHDRow.createCell(0);
                    tongSoHDCell.setCellValue(jLabel7.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(9, 9, 0, 1));
                    Cell tongSoHDValueCell = tongSoHDRow.createCell(2);
                    tongSoHDValueCell.setCellValue(txtTongHD.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(9, 9, 2, 3));

                    // Danh sách các hóa đơn
                    Row titleRow2 = sheet1.createRow(11);
                    Cell titleCell2 = titleRow2.createCell(0);
                    titleCell2.setCellValue("Danh Sách Các Hóa Đơn");
                    titleCell2.setCellStyle(titleStyle);

                    sheet1.addMergedRegion(new CellRangeAddress(11, 11, 0, 8));

                    Row tableHeaderRow = sheet1.createRow(13);
                    for (int col = 0; col < modelTable.getColumnCount() - 1; col++) {
                        Cell cell = tableHeaderRow.createCell(col * 2); // Mỗi cột chiếm 2 ô
                        cell.setCellValue(modelTable.getColumnName(col));
                        // Hợp nhất ô cho tiêu đề cột
                        sheet1.addMergedRegion(new CellRangeAddress(13, 13, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho tên cột
                    }

                    for (int row = 0; row < modelTable.getRowCount(); row++) {
                        Row dataRow = sheet1.createRow(row + 14); // Bắt đầu từ hàng 4
                        for (int col = 0; col < modelTable.getColumnCount() - 1; col++) {
                            // Tạo ô đầu tiên cho dữ liệu
                            Cell cell1 = dataRow.createCell(col * 2);
                            Object value;
                            if (col != 0 && col != 1) {
                                value = currencyFormatToDouble(modelTable.getValueAt(row, col).toString());
                                cell1.setCellValue(value.toString());
                                cell1.setCellStyle(numberStyle);
                            } else {
                                value = modelTable.getValueAt(row, col);
                                cell1.setCellValue(value.toString());
                            }
                            sheet1.addMergedRegion(new CellRangeAddress(row + 14, row + 14, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho dữ liệu
                        }
                    }

                    // Ghi dữ liệu ra file
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        workbook.write(fos);
                        JOptionPane.showMessageDialog(null, "Xuất file thành công: " + file.getAbsolutePath());
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi xuất file: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Đã hủy lưu file", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu để xuất file Excel", "Thông báo", JOptionPane.WARNING_MESSAGE);
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

        tabbedPaneCustom1 = new gui.component.TabbedPaneCustom();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTongGGSN = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTongHD = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTongGGTV = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JLabel();
        tabbedPaneCustom2 = new gui.component.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        table_sp = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        button1 = new gui.component.Button();
        tabbedPaneCustom3 = new gui.component.TabbedPaneCustom();
        jPanel3 = new javax.swing.JPanel();
        chart1 = new gui.component.Chart();

        setBackground(new java.awt.Color(217, 217, 217));

        tabbedPaneCustom1.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tabbedPaneCustom1.setPreferredSize(new java.awt.Dimension(500, 304));
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Ngày:");

        txtDate.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtDate.setText("...");

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setText("Tổng giảm giá SN:");

        txtTongGGSN.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTongGGSN.setText("...");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setText("Tổng số hóa đơn:");

        txtTongHD.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTongHD.setText("...");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel9.setText("Tên nhân viên:");

        txtTenNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTenNV.setText("...");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel11.setText("Tổng doanh thu:");

        txtTongDoanhThu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTongDoanhThu.setText("...");

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel13.setText("Tổng giảm giá TV:");

        txtTongGGTV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTongGGTV.setText("...");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên:");

        txtMaNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMaNV.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(24, 24, 24)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTongGGTV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTongGGSN, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)
                            .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTenNV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTongDoanhThu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongGGTV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTongGGSN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongHD))
                .addGap(40, 40, 40))
        );

        tabbedPaneCustom1.addTab("Thông tin ", jPanel1);

        tabbedPaneCustom2.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tabbedPaneCustom2.setSelectedColor(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã HD", "Tổng tiền", "Giảm giá Thành Viên", "Giảm giá Sinh Nhật", "Tổng tiền Thanh Toán", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_sp.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(10);
            table.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        button1.setBackground(new java.awt.Color(51, 51, 51));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-excel-32.png"))); // NOI18N
        button1.setText("Xuất báo cáo Excel");
        button1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(table_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(table_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        tabbedPaneCustom2.addTab("Danh sách các hóa đơn đã thực hiện", jPanel2);

        tabbedPaneCustom3.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tabbedPaneCustom3.setSelectedColor(new java.awt.Color(51, 51, 51));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );

        tabbedPaneCustom3.addTab("Biểu đồ", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tabbedPaneCustom3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabbedPaneCustom3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(tabbedPaneCustom2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        try {
            xuatExcel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button button1;
    private gui.component.Chart chart1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private gui.component.TabbedPaneCustom tabbedPaneCustom1;
    private gui.component.TabbedPaneCustom tabbedPaneCustom2;
    private gui.component.TabbedPaneCustom tabbedPaneCustom3;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane table_sp;
    private javax.swing.JLabel txtDate;
    private javax.swing.JLabel txtMaNV;
    private javax.swing.JLabel txtTenNV;
    private javax.swing.JLabel txtTongDoanhThu;
    private javax.swing.JLabel txtTongGGSN;
    private javax.swing.JLabel txtTongGGTV;
    private javax.swing.JLabel txtTongHD;
    // End of variables declaration//GEN-END:variables
}
