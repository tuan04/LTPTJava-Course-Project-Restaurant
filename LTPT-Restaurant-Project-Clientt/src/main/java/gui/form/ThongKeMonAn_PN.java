/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.LoaiMonAn_DAO;
import dao.MonAn_DAO;
import entity.LoaiMonAn;
import entity.MonAn;
import gui.model.ModelChart;
import javax.swing.table.DefaultTableModel;
import gui.swing.table.TableCustom;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author THANHTRI
 */
public class ThongKeMonAn_PN extends javax.swing.JPanel {

    private HoaDon_DAO hd_dao;
    private MonAn_DAO ma_dao;
    private LoaiMonAn_DAO lm_dao;
    private ChiTietHoaDon_DAO cthd_dao;
    private DefaultTableModel tableModel;
    private int tongSLMonBan = 0;
    private double tongDT = 0;

    public ThongKeMonAn_PN() {
        initComponents();
        customTable();
        init();
        chart1.addLegend("Số Lượng", Color.decode("#F0852F"));
        chart1.addLegend("Doanh Thu", Color.decode("#33CCFF"));
        txtTongDT.setText(currencyFormat(0));
    }

    private void customTable() {
        TableCustom.apply(table_sp, TableCustom.TableType.MULTI_LINE);
        table.getTableHeader().setFont(new java.awt.Font("Sanserif", java.awt.Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(50, 50, 50));
        table.repaint();
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

    private void init() {
        tableModel = (DefaultTableModel) table.getModel();
        txtDate.setDate(new Date());
        radioBtnNgay.setSelected(true);
        hd_dao = new HoaDon_DAO();
        ma_dao = new MonAn_DAO();
        cthd_dao = new ChiTietHoaDon_DAO();
        lm_dao = new LoaiMonAn_DAO();

        loadNam();
        loadThang();
        loaiLM();
        loadQuy();

        radioBtnNgay.setSelected(true);
        jcbLoaiMon.setSelectedIndex(0);
        jcbQuy.setSelectedIndex(0);
        jcbThang.setSelectedIndex(0);
        if (!Objects.isNull(jcbNam.getItemAt(0))) {
            jcbNam.setSelectedIndex(0);
        }
        txtDate.setDate(new Date());
        tableModel.setRowCount(0);
        txtMonIt.setText("...");
        txtMonNhieu.setText("...");
        txtTongSLBan.setText("0");
        txtTongDT.setText(currencyFormat(0));
        chart1.clear();
        chart1.repaint();
        chartTitle.setText("Báo cáo");
    }

    private void loadNam() {
        ArrayList<Integer> list2 = hd_dao.loadNam();
        if (list2 != null) {
            Collections.sort(list2, Collections.reverseOrder());
            for (int x : list2) {
                jcbNam.addItem(x + "");
            }
        }
    }

    private void loadThang() {
        for (int i = 1; i <= 12; i++) {
            jcbThang.addItem(i + "");
        }
    }

    private void loaiLM() {
        ArrayList<LoaiMonAn> list = lm_dao.getListLoaiMonAn();
        for (LoaiMonAn x : list) {
            jcbLoaiMon.addItem(x.getTenLoaiMA());
        }
        jcbLoaiMon.setSelectedIndex(0);
    }

    private void loadQuy() {
        jcbQuy.addItem("1");
        jcbQuy.addItem("2");
        jcbQuy.addItem("3");
        jcbQuy.addItem("4");
    }

    private void tableMonAn(ArrayList<Object[]> list) {
        if (list != null) {
            for (Object[] x : list) {
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (tableModel.getValueAt(i, 1).equals(x[0].toString())) {
                        int soLuongHT = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
                        double doanhThuHT = currencyFormatToDouble(tableModel.getValueAt(i, 5).toString());
                        tableModel.setValueAt(soLuongHT + Integer.parseInt(x[3].toString()), i, 4);
                        tableModel.setValueAt(currencyFormat(doanhThuHT + Double.parseDouble(x[4].toString())), i, 5);
                        break;
                    }
                }
            }
        }
    }

    private void thongKeNgay() {
        tongSLMonBan = 0;
        tongDT = 0;
        if (txtDate.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày!");
            return;
        }

        String day = new SimpleDateFormat("dd/MM/yyyy").format(txtDate.getDate());
        tableModel.setRowCount(0);
        chartTitle.setText("Báo Cáo Loại Món Ăn " + jcbLoaiMon.getSelectedItem().toString() + " Được Bán Ra Trong Ngày " + day);
        String maLoaiMA = lm_dao.TimLoaiMonTheoTen(jcbLoaiMon.getSelectedItem().toString()).getMaLoaiMA();
        ArrayList<MonAn> dsMon = ma_dao.getMonTheoLoai(maLoaiMA);
        int stt = 1;
        for (MonAn x : dsMon) {
            tableModel.addRow(new Object[]{stt++, x.getMaMA(), x.getTenMA(), currencyFormat(x.getGia()), 0, currencyFormat(0)});
        }
        ArrayList<Object[]> list = hd_dao.thongKeNgayMon(day, maLoaiMA);
        tableMonAn(list);

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tongSLMonBan += Integer.parseInt(tableModel.getValueAt(i, 4).toString());
            tongDT += currencyFormatToDouble(tableModel.getValueAt(i, 5).toString());
        }

        txtTongSLBan.setText(tongSLMonBan + "");
        txtTongDT.setText(currencyFormat(tongDT));
        minAndMax();
        chart();
    }

    private void thongKeThang() {
        System.out.println("Thống kê theo tháng");
        tongSLMonBan = 0;
        tongDT = 0;
        String month = jcbThang.getSelectedItem().toString();
        String year = jcbNam.getSelectedItem().toString();
        tableModel.setRowCount(0);
        chartTitle.setText("Báo Cáo Loại Món Ăn " + jcbLoaiMon.getSelectedItem().toString() + " Được Bán Ra Trong Tháng " + month + "/" + year);
        String maLoaiMA = lm_dao.TimLoaiMonTheoTen(jcbLoaiMon.getSelectedItem().toString()).getMaLoaiMA();
        ArrayList<MonAn> dsMon = ma_dao.getMonTheoLoai(maLoaiMA);
        int stt = 1;
        for (MonAn x : dsMon) {
            tableModel.addRow(new Object[]{stt++, x.getMaMA(), x.getTenMA(), currencyFormat(x.getGia()), 0, currencyFormat(0)});
        }

        ArrayList<Object[]> list = hd_dao.thongKeThangMon(month, year, maLoaiMA);
        tableMonAn(list);

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tongSLMonBan += Integer.parseInt(tableModel.getValueAt(i, 4).toString());
            tongDT += currencyFormatToDouble(tableModel.getValueAt(i, 5).toString());
        }

        txtTongSLBan.setText(tongSLMonBan + "");
        txtTongDT.setText(currencyFormat(tongDT));
        minAndMax();
        chart();
    }

    private void thongKeNam() {
        tongSLMonBan = 0;
        tongDT = 0;
        String year = jcbNam.getSelectedItem().toString();
        tableModel.setRowCount(0);
        chartTitle.setText("Báo Cáo Loại Món Ăn " + jcbLoaiMon.getSelectedItem().toString() + " Được Bán Ra Trong Năm " + year);
        String maLoaiMA = lm_dao.TimLoaiMonTheoTen(jcbLoaiMon.getSelectedItem().toString()).getMaLoaiMA();
        ArrayList<MonAn> dsMon = ma_dao.getMonTheoLoai(maLoaiMA);
        int stt = 1;
        for (MonAn x : dsMon) {
            tableModel.addRow(new Object[]{stt++, x.getMaMA(), x.getTenMA(), currencyFormat(x.getGia()), 0, currencyFormat(0)});
        }

        ArrayList<Object[]> list = hd_dao.thongKeNamMon(year, maLoaiMA);
        tableMonAn(list);

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tongSLMonBan += Integer.parseInt(tableModel.getValueAt(i, 4).toString());
            tongDT += currencyFormatToDouble(tableModel.getValueAt(i, 5).toString());
        }

        txtTongSLBan.setText(tongSLMonBan + "");
        txtTongDT.setText(currencyFormat(tongDT));
        minAndMax();
        chart();
    }

    private void thongKeQuy() {
        tongSLMonBan = 0;
        tongDT = 0;
        String quarter = jcbQuy.getSelectedItem().toString();
        String year = jcbNam.getSelectedItem().toString();
        tableModel.setRowCount(0);
        chartTitle.setText("Báo Cáo Loại Món Ăn " + jcbLoaiMon.getSelectedItem().toString() + " Được Bán Ra Trong Quý " + quarter + "Năm " + year);
        String maLoaiMA = lm_dao.TimLoaiMonTheoTen(jcbLoaiMon.getSelectedItem().toString()).getMaLoaiMA();
        ArrayList<MonAn> dsMon = ma_dao.getMonTheoLoai(maLoaiMA);
        int stt = 1;
        for (MonAn x : dsMon) {
            tableModel.addRow(new Object[]{stt++, x.getMaMA(), x.getTenMA(), currencyFormat(x.getGia()), 0, currencyFormat(0)});
        }

        ArrayList<Object[]> list = hd_dao.thongKeQuyMon(quarter, year, maLoaiMA);
        tableMonAn(list);

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            tongSLMonBan += Integer.parseInt(tableModel.getValueAt(i, 4).toString());
            tongDT += currencyFormatToDouble(tableModel.getValueAt(i, 5).toString());
        }

        txtTongSLBan.setText(tongSLMonBan + "");
        txtTongDT.setText(currencyFormat(tongDT));
        minAndMax();
        chart();
    }

    private void chart() {
        chart1.clear();
        chart1.repaint();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            chart1.addData(new ModelChart(tableModel.getValueAt(i, 2).toString(), new double[]{Integer.parseInt(tableModel.getValueAt(i, 4).toString()), currencyFormatToDouble(tableModel.getValueAt(i, 5).toString())}));
        }
        chart1.start();
    }

    private void minAndMax() {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int minRow = 0;
        int maxRow = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (Integer.parseInt(tableModel.getValueAt(i, 4).toString()) > max) {
                max = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
                maxRow = i;
            }

        }

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (Integer.parseInt(tableModel.getValueAt(i, 4).toString()) < min) {
                min = Integer.parseInt(tableModel.getValueAt(i, 4).toString());
                minRow = i;
            }

        }

        if (tableModel.getRowCount() > 0) {
            txtMonNhieu.setText(tableModel.getValueAt(maxRow, 2).toString() + " (" + tableModel.getValueAt(maxRow, 4).toString() + ")");
            txtMonIt.setText(tableModel.getValueAt(minRow, 2).toString() + " (" + tableModel.getValueAt(minRow, 4).toString() + ")");
        }

    }

    private void refresh() {
        radioBtnNgay.setSelected(true);
        jcbLoaiMon.setSelectedIndex(0);
        jcbQuy.setSelectedIndex(0);
        jcbThang.setSelectedIndex(0);
        jcbNam.setSelectedIndex(0);
        txtDate.setDate(new Date());
        tableModel.setRowCount(0);
        txtMonIt.setText("...");
        txtMonNhieu.setText("...");
        txtTongSLBan.setText("0");
        txtTongDT.setText(currencyFormat(0));
        chart1.clear();
        chart1.repaint();
        chartTitle.setText("Báo cáo");
    }

    private void xuatExcel() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        jFileChooser.setSelectedFile(new File("BaoCaoMonAn.xlsx")); // Đặt tên file mặc định

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
                titleCell.setCellValue(chartTitle.getText());

                // Định dạng cho tiêu đề (in đậm và căn giữa)
                CellStyle titleStyle = workbook.createCellStyle();
                org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
                titleFont.setBold(true);
                titleFont.setFontHeightInPoints((short) 14);
                titleStyle.setFont(titleFont);
                titleStyle.setAlignment(HorizontalAlignment.CENTER);
                titleCell.setCellStyle(titleStyle);

                // Hợp nhất ô cho tiêu đề
                sheet1.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));

                Row tongDTRow = sheet1.createRow(3);
                Cell tongDTCell = tongDTRow.createCell(0);
                tongDTCell.setCellValue(jLabel6.getText());
                sheet1.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
                Cell tongDTValueCell = tongDTRow.createCell(2);
                tongDTValueCell.setCellValue(txtMonNhieu.getText());
                sheet1.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));

                Row tongSoDDBTCRow = sheet1.createRow(4);
                Cell tongSoDDBTCCell = tongSoDDBTCRow.createCell(0);
                tongSoDDBTCCell.setCellValue(jLabel9.getText());
                sheet1.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
                Cell tongSoDDBTCValueCell = tongSoDDBTCRow.createCell(2);
                tongSoDDBTCValueCell.setCellValue(txtMonIt.getText());
                sheet1.addMergedRegion(new CellRangeAddress(4, 4, 2, 3));

                Row tongSoDDBTHRow = sheet1.createRow(5);
                Cell tongSoDDBTHCell = tongSoDDBTHRow.createCell(0);
                tongSoDDBTHCell.setCellValue(jLabel10.getText());
                sheet1.addMergedRegion(new CellRangeAddress(5, 5, 0, 1));
                Cell tongSoDDBTHValueCell = tongSoDDBTHRow.createCell(2);
                tongSoDDBTHValueCell.setCellValue(txtTongSLBan.getText());
                sheet1.addMergedRegion(new CellRangeAddress(5, 5, 2, 3));

                // Danh sách các món ăn
                Row titleRow2 = sheet1.createRow(7);
                Cell titleCell2 = titleRow2.createCell(0);
                titleCell2.setCellValue("Danh Sách Các Món Ăn");
                titleCell2.setCellStyle(titleStyle);

                sheet1.addMergedRegion(new CellRangeAddress(7, 7, 0, 8));

                Row tableHeaderRow = sheet1.createRow(9);
                for (int col = 0; col < tableModel.getColumnCount() - 1; col++) {
                    Cell cell = tableHeaderRow.createCell(col * 2); // Mỗi cột chiếm 2 ô
                    cell.setCellValue(tableModel.getColumnName(col));
                    // Hợp nhất ô cho tiêu đề cột
                    sheet1.addMergedRegion(new CellRangeAddress(9, 9, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho tên cột
                }

                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    Row dataRow = sheet1.createRow(row + 10); // Bắt đầu từ hàng 4
                    for (int col = 0; col < tableModel.getColumnCount() - 1; col++) {
                        // Tạo ô đầu tiên cho dữ liệu
                        Cell cell1 = dataRow.createCell(col * 2);
                        Object value = tableModel.getValueAt(row, col);
                        if (value != null) {
                            cell1.setCellValue(value.toString()); // Gán giá trị cho ô đầu tiên
                        }
                        sheet1.addMergedRegion(new CellRangeAddress(row + 10, row + 10, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho dữ liệu
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
    }

    private boolean valid() {
        if (txtDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now())) {
            JOptionPane.showMessageDialog(null, "Ngày chọn phải trước hoặc bằng ngày hiện tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
        return true;
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
        tabbedPaneCustom1 = new gui.component.TabbedPaneCustom();
        jPanel2 = new javax.swing.JPanel();
        chartTitle = new javax.swing.JLabel();
        chart1 = new gui.component.Chart();
        jPanel9 = new javax.swing.JPanel();
        btnExcel1 = new gui.component.Button();
        table_sp = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        tabbedPaneCustom2 = new gui.component.TabbedPaneCustom();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtMonNhieu = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtMonIt = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTongSLBan = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTongDT = new javax.swing.JLabel();
        tabbedPaneCustom3 = new gui.component.TabbedPaneCustom();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jcbThang = new javax.swing.JComboBox<>();
        radioBtnNgay = new javax.swing.JRadioButton();
        radioBtnThang = new javax.swing.JRadioButton();
        radioBtnQuy = new javax.swing.JRadioButton();
        radioBtnNam = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        btnThongKe = new gui.component.Button();
        btnRefresh = new gui.component.Button();
        jLabel3 = new javax.swing.JLabel();
        jcbQuy = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jcbNam = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jcbLoaiMon = new javax.swing.JComboBox<>();
        txtDate = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(217, 217, 217));

        tabbedPaneCustom1.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(50, 50, 50));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        chartTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        chartTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chartTitle.setText("Báo Cáo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(454, Short.MAX_VALUE)
                .addComponent(chartTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap(453, Short.MAX_VALUE))
            .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(chartTitle)
                .addGap(18, 18, 18)
                .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        tabbedPaneCustom1.addTab("Tổng quan", jPanel2);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        btnExcel1.setBackground(new java.awt.Color(50, 50, 50));
        btnExcel1.setForeground(new java.awt.Color(255, 255, 255));
        btnExcel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-excel-32.png"))); // NOI18N
        btnExcel1.setText("Xuất báo cáo Excel");
        btnExcel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnExcel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcel1ActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã món ăn", "Tên món ăn", "Giá", "Số lượng bán ra", "Doanh thu"
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(table_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 1004, Short.MAX_VALUE)
                    .addComponent(btnExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(table_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        tabbedPaneCustom1.addTab("Chi tiết món ăn", jPanel9);

        tabbedPaneCustom2.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tabbedPaneCustom2.setSelectedColor(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setText("Món Ăn Bán Nhiều Nhất");

        txtMonNhieu.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtMonNhieu.setText("...");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMonNhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(txtMonNhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("Món Ăn Bán Ít Nhất");

        txtMonIt.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtMonIt.setText("...");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMonIt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(txtMonIt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Tống Số Lượng Bán Ra");

        txtTongSLBan.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtTongSLBan.setText("...");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongSLBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(txtTongSLBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel11.setText("Tổng Doanh Thu");

        txtTongDT.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtTongDT.setText("...");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(txtTongDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
        );

        tabbedPaneCustom2.addTab("Tổng quan", jPanel1);

        tabbedPaneCustom3.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tabbedPaneCustom3.setSelectedColor(new java.awt.Color(51, 51, 51));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Loại thời gian:");

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel5.setText("Chọn tháng:");

        jcbThang.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jcbThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbThangActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioBtnNgay);
        radioBtnNgay.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        radioBtnNgay.setText("Theo ngày");

        buttonGroup1.add(radioBtnThang);
        radioBtnThang.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        radioBtnThang.setText("Theo tháng");

        buttonGroup1.add(radioBtnQuy);
        radioBtnQuy.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        radioBtnQuy.setText("Theo quý");

        buttonGroup1.add(radioBtnNam);
        radioBtnNam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        radioBtnNam.setText("Theo năm");

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("Chọn ngày:");

        btnThongKe.setBackground(new java.awt.Color(51, 51, 51));
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-bar-chart-32.png"))); // NOI18N
        btnThongKe.setText("Thống Kê");
        btnThongKe.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        btnRefresh.setBackground(new java.awt.Color(51, 51, 51));
        btnRefresh.setForeground(new java.awt.Color(255, 255, 255));
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-refresh-32.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel3.setText("Chọn quý:");

        jcbQuy.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jcbQuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbQuyActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel4.setText("Chọn năm:");

        jcbNam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jcbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNamActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel14.setText("Loại món:");

        jcbLoaiMon.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jcbLoaiMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbLoaiMonActionPerformed(evt);
            }
        });

        txtDate.setDateFormatString("dd/MM/yyyy");
        txtDate.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addComponent(radioBtnNgay)
                                .addGap(86, 86, 86)
                                .addComponent(radioBtnThang, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbThang, 0, 109, Short.MAX_VALUE)
                                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel4))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbNam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jcbLoaiMon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jcbQuy, 0, 93, Short.MAX_VALUE))
                                .addGap(1, 1, 1)))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(radioBtnQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(radioBtnNam, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel2))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radioBtnNgay)
                        .addComponent(radioBtnThang)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioBtnQuy)
                    .addComponent(radioBtnNam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jcbLoaiMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jcbQuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jcbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        tabbedPaneCustom3.addTab("Lọc", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(tabbedPaneCustom3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPaneCustom2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabbedPaneCustom3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jcbThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbThangActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        if (radioBtnNgay.isSelected() && valid()) {
            thongKeNgay();
        } else if (radioBtnThang.isSelected()) {
            thongKeThang();
        } else if (radioBtnNam.isSelected()) {
            thongKeNam();
        } else if (radioBtnQuy.isSelected()) {
            thongKeQuy();
        }
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refresh();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void jcbQuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbQuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbQuyActionPerformed

    private void jcbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbNamActionPerformed

    private void jcbLoaiMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbLoaiMonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbLoaiMonActionPerformed

    private void btnExcel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcel1ActionPerformed
        try {
            xuatExcel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnExcel1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button btnExcel1;
    private gui.component.Button btnRefresh;
    private gui.component.Button btnThongKe;
    private javax.swing.ButtonGroup buttonGroup1;
    private gui.component.Chart chart1;
    private javax.swing.JLabel chartTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JComboBox<String> jcbLoaiMon;
    private javax.swing.JComboBox<String> jcbNam;
    private javax.swing.JComboBox<String> jcbQuy;
    private javax.swing.JComboBox<String> jcbThang;
    private javax.swing.JRadioButton radioBtnNam;
    private javax.swing.JRadioButton radioBtnNgay;
    private javax.swing.JRadioButton radioBtnQuy;
    private javax.swing.JRadioButton radioBtnThang;
    private gui.component.TabbedPaneCustom tabbedPaneCustom1;
    private gui.component.TabbedPaneCustom tabbedPaneCustom2;
    private gui.component.TabbedPaneCustom tabbedPaneCustom3;
    private javax.swing.JTable table;
    private javax.swing.JScrollPane table_sp;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JLabel txtMonIt;
    private javax.swing.JLabel txtMonNhieu;
    private javax.swing.JLabel txtTongDT;
    private javax.swing.JLabel txtTongSLBan;
    // End of variables declaration//GEN-END:variables
}
