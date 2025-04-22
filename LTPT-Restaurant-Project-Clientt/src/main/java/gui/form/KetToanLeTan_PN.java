/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;

import dao.Ban_DAO;
import dao.DonDatBan_DAO;
import dao.LoaiBan_DAO;
import dao.NhanVien_DAO;
import entity.Ban;
import entity.DonDatBan;
import entity.LoaiBan;
import entity.NhanVien;
import gui.main.Admin_DashBoard;
import gui.main.LeTan_DashBoard;
import gui.model.ModelChart;
import javax.swing.table.DefaultTableModel;
import gui.swing.table.TableCustom;
import gui.swing.table.cell.ActionCell;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author THANHTRI
 */
public class KetToanLeTan_PN extends javax.swing.JPanel {

    /**
     * s
     * Creates new form pn_ThongKeDoanhThu
     */
    private DonDatBan_DAO donDatBan_DAO;
    private Ban_DAO ban_DAO;
    private LoaiBan_DAO loaiBan_DAO;
    private NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
    private String maNV;
    private String tenNV;
    private String today;
    private double tongTienCoc = 0;
    private double tongTienHoan = 0;
    private int tongSLDon = 0;
    private int tongSLDonHuy = 0;
    private double tongDT = 0;
    private NhanVien nv;

    private DefaultTableModel tableModel;
    private DefaultTableModel tableModel2;

    public KetToanLeTan_PN(Admin_DashBoard dashBoard) {
        initComponents();
        this.nv = nhanVien_DAO.getNV(dashBoard.getHeader().getTextMaNV());
        customTable();
        customTable2();

        init();
        loadTable();
        loadTable2();
        loadTXT();
        batSuKienTable();
        chart();

    }
    
    public KetToanLeTan_PN(LeTan_DashBoard dashBoard) {
        initComponents();
        this.nv = nhanVien_DAO.getNV(dashBoard.getHeader().getTextMaNV());
        customTable();
        customTable2();

        init();
        loadTable();
        loadTable2();
        loadTXT();
        batSuKienTable();
        chart();

    }

    public void setCellRender() {
        table.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer() {
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

    public void setCellRender2() {
        table2.getColumnModel().getColumn(11).setCellRenderer(new DefaultTableCellRenderer() {
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

    private void customTable2() {
        TableCustom.apply(table_sp2, TableCustom.TableType.MULTI_LINE);
        table2.getTableHeader().setFont(new Font("Sanserif", Font.BOLD, 12));
        table2.getTableHeader().setBackground(new Color(50, 50, 50));
        table2.repaint();
    }

    private void init() {
        setCellRender();
        setCellRender2();
        donDatBan_DAO = new DonDatBan_DAO();
        ban_DAO = new Ban_DAO();
        loaiBan_DAO = new LoaiBan_DAO();
        maNV = nv.getMaNV();
        tenNV = nv.getHoTenNV();
        today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE.ofPattern("dd/MM/yyyy"));
        tableModel = (DefaultTableModel) table.getModel();
        tableModel2 = (DefaultTableModel) table2.getModel();

    }

    private void loadTable() {
        ArrayList<DonDatBan> list = donDatBan_DAO.todayList(today, maNV);
        int i = 1;
        if (list != null) {
            for (DonDatBan x : list) {
                Ban ban = ban_DAO.getBan(x.getBan().getMaBan());
                LoaiBan lb = loaiBan_DAO.getLB(ban.getLoaiBan().getMaLB());
                tableModel.addRow(new Object[]{i++, x.getMaDDB(), x.getHoTenKH(), x.getSoDienThoai(), x.getSoLuongKH(), "Bàn " + ban.getSoBan() + " - " + lb.getTenLB(), x.getGioHen().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), currencyFormat(x.getTienCoc()), new ActionCell().createActionLabel(table, 8, "/gui/icon/icons8-eye-20.png")});
            }
        }
        tongSLDon = tableModel.getRowCount();
    }

    private void loadTable2() {
        ArrayList<DonDatBan> list = donDatBan_DAO.todayListHuy(today);
        int i = 1;
        if (list != null) {
            for (DonDatBan x : list) {
                Ban ban = ban_DAO.getBan(x.getBan().getMaBan());
                LoaiBan lb = loaiBan_DAO.getLB(ban.getLoaiBan().getMaLB());
                tableModel2.addRow(new Object[]{i++, x.getMaDDB(), x.getHoTenKH(), x.getSoDienThoai(), x.getSoLuongKH(), "Bàn " + ban.getSoBan() + " - " + lb.getTenLB(), x.getGioHen().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), currencyFormat(x.getTienCoc()), x.getGioHuy().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")), currencyFormat(x.getHoanCoc()), nhanVien_DAO.getNV(x.getNhanVien().getMaNV()).getHoTenNV(), new ActionCell().createActionLabel(table2, 11, "/gui/icon/icons8-eye-20.png")});
            }
        }
        tongSLDonHuy = tableModel2.getRowCount();
    }

    private void loadTXT() {
        double tienHoanKhacNgay = 0;
        double tienNhanCungNgay = 0;

        if (tableModel.getColumnCount() > 0) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                tongTienCoc += currencyFormatToDouble(tableModel.getValueAt(i, 7).toString());
            }
        }

        if (tableModel2.getRowCount() > 0) {
            for (int i = 0; i < tableModel2.getRowCount(); i++) {
                tongTienHoan += currencyFormatToDouble(tableModel2.getValueAt(i, 9).toString());
                String ngayHen = tableModel2.getValueAt(i, 6).toString().substring(0, 10);
                String ngayHuy = tableModel2.getValueAt(i, 8).toString().substring(0, 10);
                if (ngayHen.equals(ngayHuy)) {
                    tienNhanCungNgay += currencyFormatToDouble(tableModel2.getValueAt(i, 7).toString()) - currencyFormatToDouble(tableModel2.getValueAt(i, 9).toString());
                } else {
                    tienHoanKhacNgay += currencyFormatToDouble(tableModel2.getValueAt(i, 9).toString());
                }
            }
        }

        tongDT = tongTienCoc - tienHoanKhacNgay + tienNhanCungNgay;

        txtDate.setText(today);
        txtMaNV.setText(maNV);
        txtTenNV.setText(tenNV);
        txtTongDT.setText(currencyFormat(tongDT));
        txtTongSoDon.setText(String.valueOf(tongSLDon));
        txtTongSoDonHuy.setText(String.valueOf(tongSLDonHuy));
        txtTongTienHoan.setText(currencyFormat(tongTienHoan));
        txtTongTienCoc.setText(currencyFormat(tongTienCoc));
    }

    private void batSuKienTable() {
        // Bắt sự kiện nhấp chuột vào bảng
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                if (column == 8 && row >= 0) {
                    String maHD = table.getValueAt(row, 1).toString();
                    suKienXemChiTiet(maHD);
                }

            }
        });

        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table2.getSelectedRow();
                int column = table2.getSelectedColumn();
                if (column == 11 && row >= 0) {
                    String maHD = table2.getValueAt(row, 1).toString();
                    suKienXemChiTiet(maHD);
                }

            }
        });
    }

    public void suKienXemChiTiet(String maDDB) {
        Object[] ob = (Object[]) donDatBan_DAO.timDDB(maDDB);
        ArrayList<Object[]> list = donDatBan_DAO.timChiTietDonDatBan(maDDB);
        //ob = new Object[]{hoTenKH, ngayTao, gioHen, soLuongKH, soDienThoai, loaiBan, tienCoc,hoanCoc,gioHuy,trangThai};
        //String hoTenKH, String ngayDat, String gioHen, String soLuongKH, String soDienThoai, ArrayList<Object[]> list,int tienCoc,String trangThai
        String hoTenKH = ob[0].toString();
        String ngayDat = ob[1].toString();
        String gioHen = ob[2].toString();
        String soLuongKH = ob[3].toString();
        String soDienThoai = ob[4].toString();
        float tienCoc = Float.parseFloat(ob[6].toString());
        if (ob[7] == null) {
            ob[7] = "";
        }
        String hoanCoc = ob[7].toString();
        if (ob[8] == null) {
            ob[8] = "";
        }
        String gioHuy = ob[8].toString();
        String trangThai = ob[9].toString();
        if (trangThai.equals("1") == true) {
            trangThai = "Đang xử lí";
        } else if (trangThai.equals("3") == true) {
            trangThai = "Đã hủy";
        } else if (trangThai.equals("2") == true) {
            trangThai = "Thành công";
        }
        ChiTietDatBan_Form form = new ChiTietDatBan_Form(hoTenKH, ngayDat, gioHen, soLuongKH, soDienThoai, list, tienCoc, trangThai, hoanCoc, gioHuy);
        form.setVisible(true);
    }

    private void chart() {
        chart1.addLegend("Kết ca trong ngày " + today, Color.decode("#33CCFF"));
        chart1.addData(new ModelChart("Tổng Doanh Thu", new double[]{tongDT}));
        chart1.addData(new ModelChart("Tổng Số Đơn", new double[]{tongSLDon}));
        chart1.addData(new ModelChart("Tổng Tiền Cọc", new double[]{tongTienCoc}));
        chart1.addData(new ModelChart("Tổng Số Đơn Hủy", new double[]{tongSLDonHuy}));
        chart1.addData(new ModelChart("Tổng Tiền Hoàn", new double[]{tongTienHoan}));
        chart1.start();
    }

    private void xuatExcel() {
        if (tableModel.getRowCount() > 0 || tableModel2.getRowCount() > 0) {
            String now = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();

            // Khởi tạo JFileChooser
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("Chọn nơi lưu file Excel");

            // Đặt thư mục mặc định là Desktop
            jFileChooser.setCurrentDirectory(desktopDir);

            jFileChooser.setSelectedFile(new File("KetToanLeTan-" + now + ".xlsx")); // Đặt tên file mặc định

            // Hiện thị hộp thoại lưu file
            int userSelection = jFileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                System.out.println("Đường dẫn file: " + file.getAbsolutePath());

                try (Workbook workbook = new XSSFWorkbook()) {
                    Sheet sheet1 = workbook.createSheet("Thông tin tổng quát");

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

                    Row tongCocRow = sheet1.createRow(6);
                    Cell tongCocCell = tongCocRow.createCell(0);
                    tongCocCell.setCellValue(jLabel11.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));
                    Cell tongCocValueCell = tongCocRow.createCell(2);
                    tongCocValueCell.setCellValue(currencyFormatToDouble(txtTongDT.getText()) + "");
                    sheet1.addMergedRegion(new CellRangeAddress(6, 6, 2, 3));

                    Row tongSoDonRow = sheet1.createRow(7);
                    Cell tongSoDonCell = tongSoDonRow.createCell(0);
                    tongSoDonCell.setCellValue(jLabel13.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(7, 7, 0, 1));
                    Cell tongSoDonValueCell = tongSoDonRow.createCell(2);
                    tongSoDonValueCell.setCellValue(txtTongSoDon.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(7, 7, 2, 3));

                    Row tongSoDonHuyRow = sheet1.createRow(8);
                    Cell tongSoDonHuyCell = tongSoDonHuyRow.createCell(0);
                    tongSoDonHuyCell.setCellValue(jLabel5.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(8, 8, 0, 1));
                    Cell tongSoDonHuyValueCell = tongSoDonHuyRow.createCell(2);
                    tongSoDonHuyValueCell.setCellValue(txtTongSoDonHuy.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(8, 8, 2, 3));

                    Row tongTienHoanHuyRow = sheet1.createRow(9);
                    Cell tongTienHoanCell = tongTienHoanHuyRow.createCell(0);
                    tongTienHoanCell.setCellValue(jLabel7.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(9, 9, 0, 1));
                    Cell tongTienHoanValueCell = tongTienHoanHuyRow.createCell(2);
                    tongTienHoanValueCell.setCellValue(currencyFormatToDouble(txtTongTienHoan.getText()) + "");
                    sheet1.addMergedRegion(new CellRangeAddress(9, 9, 2, 3));

                    // Danh sách các đơn đặt bàn
                    Row titleRow2 = sheet1.createRow(11);
                    Cell titleCell2 = titleRow2.createCell(0);
                    titleCell2.setCellValue("Danh Sách Các Đơn Đặt Bàn Được Thực Hiện");
                    titleCell2.setCellStyle(titleStyle);

                    sheet1.addMergedRegion(new CellRangeAddress(11, 11, 0, 8));

                    Row tableHeaderRow = sheet1.createRow(13);
                    for (int col = 0; col < tableModel.getColumnCount() - 1; col++) {
                        Cell cell = tableHeaderRow.createCell(col * 2); // Mỗi cột chiếm 2 ô
                        cell.setCellValue(tableModel.getColumnName(col));
                        // Hợp nhất ô cho tiêu đề cột
                        sheet1.addMergedRegion(new CellRangeAddress(13, 13, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho tên cột
                    }

                    for (int row = 0; row < tableModel.getRowCount(); row++) {
                        Row dataRow = sheet1.createRow(row + 14); // Bắt đầu từ hàng 13
                        for (int col = 0; col < tableModel.getColumnCount() - 1; col++) {
                            Cell cell1 = dataRow.createCell(col * 2);
                            Object value;
                            if (col == 7) {
                                value = currencyFormatToDouble(tableModel.getValueAt(row, col).toString());
                                cell1.setCellValue(value.toString());
                                cell1.setCellStyle(numberStyle);
                            } else {
                                value = tableModel.getValueAt(row, col);
                                cell1.setCellValue(value.toString());
                            }
                            sheet1.addMergedRegion(new CellRangeAddress(row + 14, row + 14, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho dữ liệu
                        }
                    }

                    // Danh sách các đơn đặt bàn Hủy
                    Row titleRow3 = sheet1.createRow(tableModel.getRowCount() + 16);
                    Cell titleCell3 = titleRow3.createCell(0);
                    titleCell3.setCellValue("Danh Sách Các Đơn Đặt Bàn Được Hủy");
                    titleCell3.setCellStyle(titleStyle);

                    sheet1.addMergedRegion(new CellRangeAddress(tableModel.getRowCount() + 16, tableModel.getRowCount() + 16, 0, 8));

                    Row tableHeaderRow2 = sheet1.createRow(tableModel.getRowCount() + 18);
                    for (int col = 0; col < tableModel2.getColumnCount() - 1; col++) {
                        Cell cell = tableHeaderRow2.createCell(col * 2); // Mỗi cột chiếm 2 ô
                        cell.setCellValue(tableModel2.getColumnName(col));
                        // Hợp nhất ô cho tiêu đề cột
                        sheet1.addMergedRegion(new CellRangeAddress(tableModel.getRowCount() + 18, tableModel.getRowCount() + 18, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho tên cột
                    }

                    for (int row = 0; row < tableModel2.getRowCount(); row++) {
                        Row dataRow = sheet1.createRow(tableModel.getRowCount() + row + 19); // Bắt đầu từ hàng 4
                        for (int col = 0; col < tableModel2.getColumnCount() - 1; col++) {
                            // Tạo ô đầu tiên cho dữ liệu
                            Cell cell1 = dataRow.createCell(col * 2);
                            Object value;
                            if (col == 7 || col == 9) {
                                value = currencyFormatToDouble(tableModel2.getValueAt(row, col).toString());
                                cell1.setCellValue(value.toString());
                                cell1.setCellStyle(numberStyle);
                            } else {
                                value = tableModel2.getValueAt(row, col);
                                cell1.setCellValue(value.toString());
                            }
                            sheet1.addMergedRegion(new CellRangeAddress(tableModel.getRowCount() + row + 19, tableModel.getRowCount() + row + 19, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho dữ liệu
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

        tabbedPaneCustom2 = new gui.component.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        table_sp = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        button1 = new gui.component.Button();
        jPanel4 = new javax.swing.JPanel();
        table_sp2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        button5 = new gui.component.Button();
        tabbedPaneCustom1 = new gui.component.TabbedPaneCustom();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDate = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTongSoDonHuy = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTongTienHoan = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTongDT = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtTongSoDon = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JLabel();
        txtTongTienCoc = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tabbedPaneCustom5 = new gui.component.TabbedPaneCustom();
        jPanel6 = new javax.swing.JPanel();
        chart1 = new gui.component.Chart();

        setBackground(new java.awt.Color(217, 217, 217));

        tabbedPaneCustom2.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tabbedPaneCustom2.setSelectedColor(new java.awt.Color(51, 51, 51));
        tabbedPaneCustom2.setUnselectedColor(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã DDB", "Họ tên KH", "SDT", "Số lượng KH", "Số bàn", "Giờ hẹn", "Tiền cọc", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_sp.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(10);
            table.getColumnModel().getColumn(8).setPreferredWidth(15);
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
                        .addGap(369, 750, Short.MAX_VALUE)
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(table_sp))
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(table_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPaneCustom2.addTab("Danh sách đơn đặt bàn đã thực hiện", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã DDB", "Họ tên KH", "SDT", "Số lượng KH", "Số bàn", "Giờ hẹn", "Tiền cọc", "Giờ hủy", "Tiền hoàn", "NV tạo", ""
            }
        ));
        table_sp2.setViewportView(table2);
        if (table2.getColumnModel().getColumnCount() > 0) {
            table2.getColumnModel().getColumn(0).setPreferredWidth(10);
            table2.getColumnModel().getColumn(11).setPreferredWidth(15);
        }

        button5.setBackground(new java.awt.Color(51, 51, 51));
        button5.setForeground(new java.awt.Color(255, 255, 255));
        button5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-excel-32.png"))); // NOI18N
        button5.setText("Xuất báo cáo Excel");
        button5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(369, 750, Short.MAX_VALUE)
                        .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(table_sp2))
                .addGap(30, 30, 30))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(table_sp2, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addComponent(button5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPaneCustom2.addTab("Danh sách đơn đặt bàn đã hủy", jPanel4);

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
        jLabel5.setText("Tổng số đơn hủy:");

        txtTongSoDonHuy.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTongSoDonHuy.setText("...");

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setText("Tổng tiền hoàn:");

        txtTongTienHoan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTongTienHoan.setText("...");

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel9.setText("Tên nhân viên:");

        txtTenNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTenNV.setText("...");

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel11.setText("Tổng doanh thu:");

        txtTongDT.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTongDT.setText("...");

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel13.setText("Tổng số đơn thực hiện:");

        txtTongSoDon.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTongSoDon.setText("...");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel3.setText("Mã nhân viên:");

        txtMaNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtMaNV.setText("...");

        txtTongTienCoc.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTongTienCoc.setText("...");

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setText("Tổng tiền cọc:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTongDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTongSoDon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTongSoDonHuy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtTenNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtTongTienHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
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
                    .addComponent(txtTongDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongSoDon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTongSoDonHuy))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTongTienHoan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTongTienCoc))
                .addGap(9, 9, 9))
        );

        tabbedPaneCustom1.addTab("Thông tin chi tiết", jPanel1);

        tabbedPaneCustom5.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tabbedPaneCustom5.setSelectedColor(new java.awt.Color(51, 51, 51));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
        );

        tabbedPaneCustom5.addTab("Biểu đồ", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tabbedPaneCustom5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tabbedPaneCustom5, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(tabbedPaneCustom2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button5ActionPerformed
        try {
            xuatExcel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_button5ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        try {
            xuatExcel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button button1;
    private gui.component.Button button5;
    private gui.component.Chart chart1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private gui.component.TabbedPaneCustom tabbedPaneCustom1;
    private gui.component.TabbedPaneCustom tabbedPaneCustom2;
    private gui.component.TabbedPaneCustom tabbedPaneCustom5;
    private javax.swing.JTable table;
    private javax.swing.JTable table2;
    private javax.swing.JScrollPane table_sp;
    private javax.swing.JScrollPane table_sp2;
    private javax.swing.JLabel txtDate;
    private javax.swing.JLabel txtMaNV;
    private javax.swing.JLabel txtTenNV;
    private javax.swing.JLabel txtTongDT;
    private javax.swing.JLabel txtTongSoDon;
    private javax.swing.JLabel txtTongSoDonHuy;
    private javax.swing.JLabel txtTongTienCoc;
    private javax.swing.JLabel txtTongTienHoan;
    // End of variables declaration//GEN-END:variables
}
