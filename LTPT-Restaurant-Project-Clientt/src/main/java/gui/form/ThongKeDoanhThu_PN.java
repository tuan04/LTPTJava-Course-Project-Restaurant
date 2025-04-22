/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;

import dao.Ban_DAO;
import dao.DonDatBan_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.LoaiBan_DAO;
import dao.NhanVien_DAO;
import entity.Ban;
import entity.DonDatBan;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.LoaiBan;
import entity.NhanVien;
import gui.model.ModelChart;
import javax.swing.table.DefaultTableModel;
import gui.swing.table.TableCustom;
import gui.swing.table.cell.ActionCell;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Vector;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
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
public class ThongKeDoanhThu_PN extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private DefaultTableModel tableModel2;
    private DefaultTableModel tableModel3;
    private double tongDT = 0;
    private int tongSoHD = 0;
    private int tongSoDDB = 0;
    private int tongSoDDBHuy = 0;
    private double tongTT = 0;
    private double tongSN = 0;
    private double tongTV = 0;
    private double tongTienCoc = 0;
    private double tongTienNhan = 0;
    private double tongTienHoan = 0;
    private HoaDon_DAO hd_dao;
    private DonDatBan_DAO ddb_dao;
    private LoaiBan_DAO lb_dao;
    private Ban_DAO ban_dao;
    private NhanVien_DAO nv_dao;
    private KhachHang_DAO kh_dao;

    public ThongKeDoanhThu_PN() {
        initComponents();
        customTable();
        init();
        chart1.addLegend("Doanh thu", Color.decode("#33CCFF"));
        chart1.addLegend("Số lượng", Color.decode("#F0852F"));
        batSuKienTable();
    }

    private void customTable() {
        TableCustom.apply(table_sp, TableCustom.TableType.MULTI_LINE);
        table.getTableHeader().setFont(new java.awt.Font("Sanserif", java.awt.Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(50, 50, 50));
        table.repaint();

        TableCustom.apply(table_sp2, TableCustom.TableType.MULTI_LINE);
        table2.getTableHeader().setFont(new java.awt.Font("Sanserif", java.awt.Font.BOLD, 12));
        table2.getTableHeader().setBackground(new Color(50, 50, 50));
        table2.repaint();

        TableCustom.apply(table_sp3, TableCustom.TableType.MULTI_LINE);
        table3.getTableHeader().setFont(new java.awt.Font("Sanserif", java.awt.Font.BOLD, 12));
        table3.getTableHeader().setBackground(new Color(50, 50, 50));
        table3.repaint();
    }

    public String currencyFormat(double price) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(price);
    }

    public double currencyFormatToDouble(String currency) {
        String str = currency.replaceAll("[^\\d]", "");
        return Double.parseDouble(str);
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

    public void setCellRender2() {
        table2.getColumnModel().getColumn(9).setCellRenderer(new DefaultTableCellRenderer() {
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

    public void setCellRender3() {
        table3.getColumnModel().getColumn(14).setCellRenderer(new DefaultTableCellRenderer() {
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

    private void batSuKienTable() {
        // Bắt sự kiện nhấp chuột vào bảng
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                if (column == 6 && row >= 0) {
                    String maHD = tableModel.getValueAt(row, 1).toString();
                    suKienXemChiTietHD(maHD);
                }

            }
        });

        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table2.getSelectedRow();
                int column = table2.getSelectedColumn();
                if (column == 9 && row >= 0) {
                    String maDDB = tableModel2.getValueAt(row, 2).toString();
                    suKienXemChiTietDDB(maDDB);
                }

            }
        });

        table3.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table3.getSelectedRow();
                int column = table3.getSelectedColumn();
                if (column == 14 && row >= 0) {
                    String maDDB = table3.getValueAt(row, 2).toString();
                    suKienXemChiTietDDB(maDDB);
                }

            }
        });
    }

    public void suKienXemChiTietHD(String maHD) {
        Object[] ob = (Object[]) hd_dao.timKiemHD(maHD);
        ArrayList<Object[]> list = hd_dao.timKiemCTHD(maHD);

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

    public void suKienXemChiTietDDB(String maDDB) {
        Object[] ob = (Object[]) ddb_dao.timDDB(maDDB);
        ArrayList<Object[]> list = ddb_dao.timChiTietDonDatBan(maDDB);
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

    private void init() {
        setCellRender();
        setCellRender2();
        setCellRender3();
        tableModel = (DefaultTableModel) table.getModel();
        tableModel2 = (DefaultTableModel) table2.getModel();
        tableModel3 = (DefaultTableModel) table3.getModel();
        hd_dao = new HoaDon_DAO();
        ddb_dao = new DonDatBan_DAO();
        lb_dao = new LoaiBan_DAO();
        ban_dao = new Ban_DAO();
        nv_dao = new NhanVien_DAO();
        kh_dao = new KhachHang_DAO();

        loadNam();
        loadThang();
        loadLB();
        loadQuy();

        radioBtnNgay.setSelected(true);
        txtDate.setDate(new Date());
        jcbLoaiBan.setSelectedIndex(0);
        jcbQuy.setSelectedIndex(0);
        jcbThang.setSelectedIndex(0);
        if (!Objects.isNull(jcbNam.getItemAt(0))) {
            jcbNam.setSelectedIndex(0);
        }
        txtTongDoanhThu.setText(currencyFormat(0));
        tableModel.setRowCount(0);
        tableModel2.setRowCount(0);
        tableModel3.setRowCount(0);
        txtTongSoDDB.setText("0");
        txtTongSoDDBHuy.setText("0");
        txtTongSoHD.setText("0");
        txtTongTT.setText("_________");
        txtTongTV.setText("_________");
        txtTongSN.setText("_________");
        txtTongCoc.setText("_________");
        txtTongTienNhan.setText("_________");
        txtTongTienHoan.setText("_________");
        chart1.clear();
        chart1.repaint();
        chartTitle.setText("Báo Cáo");
    }

    private void loadNam() {
        ArrayList<Integer> list = hd_dao.loadNam();
        ArrayList<Integer> list2 = ddb_dao.loadNam();
        ArrayList<Integer> nam = new ArrayList<>();

        if (list != null) {
            for (int x : list) {
                nam.add(x);
            }
        }

        if (list2 != null) {
            for (int x : list2) {
                if (!nam.contains(x)) {
                    nam.add(x);
                }
            }
        }

        if (!nam.isEmpty()) {
            Collections.sort(nam, Collections.reverseOrder());
            for (int x : nam) {
                jcbNam.addItem(x + "");
            }
        }
    }

    private void loadThang() {
        for (int i = 1; i <= 12; i++) {
            jcbThang.addItem(i + "");
        }
    }

    private void loadLB() {
        jcbLoaiBan.addItem("Tất cả");
        ArrayList<LoaiBan> list = lb_dao.getListLoaiBan();
        for (LoaiBan x : list) {
            jcbLoaiBan.addItem(x.getTenLB());
        }
        jcbLoaiBan.setSelectedIndex(0);
    }

    private void loadQuy() {
        jcbQuy.addItem("1");
        jcbQuy.addItem("2");
        jcbQuy.addItem("3");
        jcbQuy.addItem("4");
    }

    private void hoaDonTable(ArrayList<HoaDon> list) {
        tableModel.setRowCount(0);
        if (list != null) {
            int i = 1;
            for (HoaDon x : list) {
                tongTT += x.getTongTienTT();
                tongSN += x.getGiamGiaSinhNhat();
                tongTV += x.getGiamGiaThanhVien();
                tableModel.addRow(new Object[]{i++, x.getMaHD(), currencyFormat(x.getTongTien()), currencyFormat(x.getGiamGiaThanhVien()), currencyFormat(x.getGiamGiaSinhNhat()), currencyFormat(x.getTongTienTT()), new ActionCell().createActionLabel(table, 6, "/gui/icon/icons8-eye-20.png")});
            }
        }
    }

    private void datBanTable(ArrayList<DonDatBan> list, ArrayList<DonDatBan> list2) {
        tableModel2.setRowCount(0);
        if (list != null) {
            int i = 1;
            for (DonDatBan x : list) {
                tongTienCoc += x.getTienCoc();
                Ban ban = ban_dao.getBan(x.getBan().getMaBan());
                LoaiBan lb = lb_dao.getLB(ban.getLoaiBan().getMaLB());
                tableModel2.addRow(new Object[]{i++, x.getNgayTao(), x.getMaDDB(), x.getHoTenKH(), x.getSoDienThoai(), x.getSoLuongKH(), "Bàn " + ban.getSoBan() + " / " + lb.getTenLB(), x.getGioHen(), currencyFormat(x.getTienCoc()), new ActionCell().createActionLabel(table2, 9, "/gui/icon/icons8-eye-20.png")});
            }
        }
        if (list2 != null) {
            int i = 1;
            for (DonDatBan x : list2) {
                tongTienCoc += x.getTienCoc();
                Ban ban = ban_dao.getBan(x.getBan().getMaBan());
                LoaiBan lb = lb_dao.getLB(ban.getLoaiBan().getMaLB());
                tableModel2.addRow(new Object[]{i++, x.getNgayTao(), x.getMaDDB(), x.getHoTenKH(), x.getSoDienThoai(), x.getSoLuongKH(), "Bàn " + ban.getSoBan() + " / " + lb.getTenLB(), x.getGioHen(), currencyFormat(x.getTienCoc()), new ActionCell().createActionLabel(table2, 9, "/gui/icon/icons8-eye-20.png")});
            }
        }
    }

    private void datBanHuyTable(ArrayList<DonDatBan> list) {
        tableModel3.setRowCount(0);
        if (list != null) {
            int i = 1;
            for (DonDatBan x : list) {
                tongTienHoan += x.getHoanCoc();
                tongTienNhan += x.getTienCoc() - x.getHoanCoc();
                Ban ban = ban_dao.getBan(x.getBan().getMaBan());
                LoaiBan lb = lb_dao.getLB(ban.getLoaiBan().getMaLB());
                NhanVien nvTao = nv_dao.getNV(x.getNhanVien().getMaNV());
                NhanVien nvHuy = null;
                if (!Objects.isNull(x.getNhanVienHuy().getMaNV())) {
                    nvHuy = nv_dao.getNV(x.getNhanVienHuy().getMaNV());
                }
                tableModel3.addRow(new Object[]{i++, x.getNgayTao(), x.getMaDDB(), x.getHoTenKH(), x.getSoDienThoai(), x.getSoLuongKH(), "Bàn " + ban.getSoBan() + " / " + lb.getTenLB(), x.getGioHen(), currencyFormat(x.getTienCoc()), x.getGioHuy(), currencyFormat(x.getHoanCoc()), currencyFormat(x.getTienCoc() - x.getHoanCoc()), nvTao.getHoTenNV(), Objects.isNull(nvHuy) ? "" : nvHuy.getHoTenNV(), new ActionCell().createActionLabel(table3, 14, "/gui/icon/icons8-eye-20.png")});
            }
        }
    }

    private void checkValue() {
        if (tableModel.getRowCount() <= 0 && tableModel2.getRowCount() <= 0 && tableModel3.getRowCount() <= 0) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu để hiển thị!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void thongKeNgay() {
        tongDT = 0;
        tongSoHD = 0;
        tongSoDDB = 0;
        tongSoDDBHuy = 0;
        tongTT = 0;
        tongSN = 0;
        tongTV = 0;
        tongTienCoc = 0;
        tongTienNhan = 0;
        tongTienHoan = 0;
        if (txtDate.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày!");
            return;
        }

        String day = new SimpleDateFormat("dd/MM/yyyy").format(txtDate.getDate());

        chartTitle.setText("Báo Cáo Doanh Thu Trong Ngày " + day);

        ArrayList<HoaDon> list = hd_dao.thongKeNgay(day, jcbLoaiBan.getSelectedItem().toString());
        hoaDonTable(list);

        ArrayList<DonDatBan> list2 = ddb_dao.thongKeNgay(day, "1", jcbLoaiBan.getSelectedItem().toString());
        ArrayList<DonDatBan> list4 = ddb_dao.thongKeNgay(day, "2", jcbLoaiBan.getSelectedItem().toString());
        datBanTable(list2, list4);

        ArrayList<DonDatBan> list3 = ddb_dao.thongKeNgay(day, "3", jcbLoaiBan.getSelectedItem().toString());
        datBanHuyTable(list3);

        tongDT = tongTT + tongTienCoc + tongTienNhan;
        tongSoHD = tableModel.getRowCount();
        tongSoDDB = tableModel2.getRowCount();
        tongSoDDBHuy = tableModel3.getRowCount();

        txtTongDoanhThu.setText(currencyFormat(tongDT));
        txtTongSoHD.setText(tongSoHD + "");
        txtTongSoDDB.setText(tongSoDDB + "");
        txtTongSoDDBHuy.setText(tongSoDDBHuy + "");
        txtTongTT.setText(currencyFormat(tongTT));
        txtTongTV.setText(currencyFormat(tongTV));
        txtTongSN.setText(currencyFormat(tongSN));
        txtTongCoc.setText(currencyFormat(tongTienCoc));
        txtTongTienHoan.setText(currencyFormat(tongTienHoan));
        txtTongTienNhan.setText(currencyFormat(tongTienNhan));
        chart();
        checkValue();
    }

    private void thongKeThang() {
        tongDT = 0;
        tongSoHD = 0;
        tongSoDDB = 0;
        tongSoDDBHuy = 0;
        tongTT = 0;
        tongSN = 0;
        tongTV = 0;
        tongTienCoc = 0;
        tongTienNhan = 0;
        tongTienHoan = 0;
        String month = jcbThang.getSelectedItem().toString();
        String year = jcbNam.getSelectedItem().toString();

        chartTitle.setText("Báo Cáo Doanh Thu Trong Tháng " + month + "/" + year);

        ArrayList<HoaDon> list = hd_dao.thongKeThang(month, year, jcbLoaiBan.getSelectedItem().toString());
        hoaDonTable(list);

        ArrayList<DonDatBan> list2 = ddb_dao.thongKeThang(month, year, "1", jcbLoaiBan.getSelectedItem().toString());
        ArrayList<DonDatBan> list4 = ddb_dao.thongKeThang(month, year, "2", jcbLoaiBan.getSelectedItem().toString());
        datBanTable(list2, list4);

        ArrayList<DonDatBan> list3 = ddb_dao.thongKeThang(month, year, "3", jcbLoaiBan.getSelectedItem().toString());
        datBanHuyTable(list3);

        tongDT = tongTT + tongTienCoc + tongTienNhan;
        tongSoHD = tableModel.getRowCount();
        tongSoDDB = tableModel2.getRowCount();
        tongSoDDBHuy = tableModel3.getRowCount();

        txtTongDoanhThu.setText(currencyFormat(tongDT));
        txtTongSoHD.setText(tongSoHD + "");
        txtTongSoDDB.setText(tongSoDDB + "");
        txtTongSoDDBHuy.setText(tongSoDDBHuy + "");
        txtTongTT.setText(currencyFormat(tongTT));
        txtTongTV.setText(currencyFormat(tongTV));
        txtTongSN.setText(currencyFormat(tongSN));
        txtTongCoc.setText(currencyFormat(tongTienCoc));
        txtTongTienHoan.setText(currencyFormat(tongTienHoan));
        txtTongTienNhan.setText(currencyFormat(tongTienNhan));
        chart();
        checkValue();
    }

    private void thongKeNam() {
        tongDT = 0;
        tongSoHD = 0;
        tongSoDDB = 0;
        tongSoDDBHuy = 0;
        tongTT = 0;
        tongSN = 0;
        tongTV = 0;
        tongTienCoc = 0;
        tongTienNhan = 0;
        tongTienHoan = 0;
        String nam = jcbNam.getSelectedItem().toString();

        chartTitle.setText("Báo Cáo Doanh Thu Trong Năm " + nam);

        ArrayList<HoaDon> list = hd_dao.thongKeNam(nam, jcbLoaiBan.getSelectedItem().toString());
        hoaDonTable(list);

        ArrayList<DonDatBan> list2 = ddb_dao.thongKeNam(nam, "1", jcbLoaiBan.getSelectedItem().toString());
        ArrayList<DonDatBan> list4 = ddb_dao.thongKeNam(nam, "2", jcbLoaiBan.getSelectedItem().toString());
        datBanTable(list2, list4);

        ArrayList<DonDatBan> list3 = ddb_dao.thongKeNam(nam, "3", jcbLoaiBan.getSelectedItem().toString());
        datBanHuyTable(list3);

        tongDT = tongTT + tongTienCoc + tongTienNhan;
        tongSoHD = tableModel.getRowCount();
        tongSoDDB = tableModel2.getRowCount();
        tongSoDDBHuy = tableModel3.getRowCount();

        txtTongDoanhThu.setText(currencyFormat(tongDT));
        txtTongSoHD.setText(tongSoHD + "");
        txtTongSoDDB.setText(tongSoDDB + "");
        txtTongSoDDBHuy.setText(tongSoDDBHuy + "");
        txtTongTT.setText(currencyFormat(tongTT));
        txtTongTV.setText(currencyFormat(tongTV));
        txtTongSN.setText(currencyFormat(tongSN));
        txtTongCoc.setText(currencyFormat(tongTienCoc));
        txtTongTienHoan.setText(currencyFormat(tongTienHoan));
        txtTongTienNhan.setText(currencyFormat(tongTienNhan));
        chart();
        checkValue();
    }

    private void thongKeQuy() {
        tongDT = 0;
        tongSoHD = 0;
        tongSoDDB = 0;
        tongSoDDBHuy = 0;
        tongTT = 0;
        tongSN = 0;
        tongTV = 0;
        tongTienCoc = 0;
        tongTienNhan = 0;
        tongTienHoan = 0;
        String quy = jcbQuy.getSelectedItem().toString();
        String nam = jcbNam.getSelectedItem().toString();

        chartTitle.setText("Báo Cáo Doanh Thu Theo Quý " + quy + " Năm " + nam);

        ArrayList<HoaDon> list = hd_dao.thongKeQuy(quy, nam, jcbLoaiBan.getSelectedItem().toString());
        hoaDonTable(list);

        ArrayList<DonDatBan> list2 = ddb_dao.thongKeQuy(quy, nam, "1", jcbLoaiBan.getSelectedItem().toString());
        ArrayList<DonDatBan> list4 = ddb_dao.thongKeQuy(quy, nam, "2", jcbLoaiBan.getSelectedItem().toString());
        datBanTable(list2, list4);

        ArrayList<DonDatBan> list3 = ddb_dao.thongKeQuy(quy, nam, "3", jcbLoaiBan.getSelectedItem().toString());
        datBanHuyTable(list3);

        tongDT = tongTT + tongTienCoc + tongTienNhan;
        tongSoHD = tableModel.getRowCount();
        tongSoDDB = tableModel2.getRowCount();
        tongSoDDBHuy = tableModel3.getRowCount();

        txtTongDoanhThu.setText(currencyFormat(tongDT));
        txtTongSoHD.setText(tongSoHD + "");
        txtTongSoDDB.setText(tongSoDDB + "");
        txtTongSoDDBHuy.setText(tongSoDDBHuy + "");
        txtTongTT.setText(currencyFormat(tongTT));
        txtTongTV.setText(currencyFormat(tongTV));
        txtTongSN.setText(currencyFormat(tongSN));
        txtTongCoc.setText(currencyFormat(tongTienCoc));
        txtTongTienHoan.setText(currencyFormat(tongTienHoan));
        txtTongTienNhan.setText(currencyFormat(tongTienNhan));
        chart();
        checkValue();
    }

    private void chart() {
        chart1.clear();
        chart1.repaint();
        chart1.addData(new ModelChart("Hóa Đơn", new double[]{tongTT, tongSoHD}));
        chart1.addData(new ModelChart("Đơn Đặt Bàn", new double[]{tongTienCoc, tongSoDDB}));
        chart1.addData(new ModelChart("Đơn Đặt Bàn Hủy", new double[]{tongTienNhan, tongSoDDBHuy}));
        chart1.start();
    }

    private void refresh() {
        radioBtnNgay.setSelected(true);
        txtDate.setDate(new Date());
        jcbLoaiBan.setSelectedIndex(0);
        jcbQuy.setSelectedIndex(0);
        jcbThang.setSelectedIndex(0);
        if (!Objects.isNull(jcbNam.getItemAt(0))) {
            jcbNam.setSelectedIndex(0);
        }
        tableModel.setRowCount(0);
        tableModel2.setRowCount(0);
        tableModel3.setRowCount(0);
        txtTongDoanhThu.setText(currencyFormat(0));
        txtTongSoDDB.setText("0");
        txtTongSoDDBHuy.setText("0");
        txtTongSoHD.setText("0");
        txtTongTT.setText("_________");
        txtTongTV.setText("_________");
        txtTongSN.setText("_________");
        txtTongCoc.setText("_________");
        txtTongTienNhan.setText("_________");
        txtTongTienHoan.setText("_________");
        chart1.clear();
        chart1.repaint();
        chartTitle.setText("Báo Cáo");
    }

    private void xuatExcel() {
        if (tableModel.getRowCount() > 0 || tableModel2.getRowCount() > 0 || tableModel3.getRowCount() > 0) {
            JFileChooser jFileChooser = new JFileChooser();
            File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
            jFileChooser.setDialogTitle("Chọn nơi lưu file Excel");

            // Đặt thư mục mặc định là Desktop
            jFileChooser.setCurrentDirectory(desktopDir);
            jFileChooser.setSelectedFile(new File("BaoCaoDoanhThu.xlsx")); // Đặt tên file mặc định

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
                    tongDTValueCell.setCellValue(currencyFormatToDouble(txtTongDoanhThu.getText()) + "");
                    sheet1.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));

                    Row tongSoHDRow = sheet1.createRow(4);
                    Cell tongSoHDCell = tongSoHDRow.createCell(0);
                    tongSoHDCell.setCellValue(jLabel9.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
                    Cell tongSoHDValueCell = tongSoHDRow.createCell(2);
                    tongSoHDValueCell.setCellValue(txtTongSoHD.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(4, 4, 2, 3));

                    Row tongSoDDBRow = sheet1.createRow(5);
                    Cell tongSoDDBCell = tongSoDDBRow.createCell(0);
                    tongSoDDBCell.setCellValue(jLabel10.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(5, 5, 0, 1));
                    Cell tongSoDDBValueCell = tongSoDDBRow.createCell(2);
                    tongSoDDBValueCell.setCellValue(txtTongSoDDB.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(5, 5, 2, 3));

                    Row tongSoDDBHuyRow = sheet1.createRow(6);
                    Cell tongSoDDBHuyCell = tongSoDDBHuyRow.createCell(0);
                    tongSoDDBHuyCell.setCellValue(jLabel12.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(6, 6, 0, 1));
                    Cell tongSoDDBHuyValueCell = tongSoDDBHuyRow.createCell(2);
                    tongSoDDBHuyValueCell.setCellValue(txtTongSoDDBHuy.getText());
                    sheet1.addMergedRegion(new CellRangeAddress(6, 6, 2, 3));

                    // Danh sách các hóa đơn
                    Sheet sheet2 = workbook.createSheet("Danh sách các hóa đơn");

                    Row titleRow2 = sheet2.createRow(1);
                    Cell titleCell2 = titleRow2.createCell(0);
                    titleCell2.setCellValue("Danh Sách Các Hóa Đơn");
                    titleCell2.setCellStyle(titleStyle);

                    sheet2.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));

                    Row tongTienTTRow = sheet2.createRow(3);
                    Cell tongTienTTCell = tongTienTTRow.createCell(0);
                    tongTienTTCell.setCellValue(lblMaxValue.getText());
                    sheet2.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
                    Cell tongTienTTValueCell = tongTienTTRow.createCell(2);
                    tongTienTTValueCell.setCellValue(currencyFormatToDouble(txtTongTT.getText()) + "");
                    sheet2.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));

                    Row tongGGTVRow = sheet2.createRow(4);
                    Cell tongGGTVCell = tongGGTVRow.createCell(0);
                    tongGGTVCell.setCellValue(lblMinValue.getText());
                    sheet2.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
                    Cell tongGGTVValueCell = tongGGTVRow.createCell(2);
                    tongGGTVValueCell.setCellValue(currencyFormatToDouble(txtTongTV.getText()) + "");
                    sheet2.addMergedRegion(new CellRangeAddress(4, 4, 2, 3));

                    Row tongGGSNRow = sheet2.createRow(5);
                    Cell tongGGSNCell = tongGGSNRow.createCell(0);
                    tongGGSNCell.setCellValue(lblMinValue2.getText());
                    sheet2.addMergedRegion(new CellRangeAddress(5, 5, 0, 1));
                    Cell tongGGSNValueCell = tongGGSNRow.createCell(2);
                    tongGGSNValueCell.setCellValue(currencyFormatToDouble(txtTongSN.getText()) + "");
                    sheet2.addMergedRegion(new CellRangeAddress(5, 5, 2, 3));

                    Row tableHeaderRow = sheet2.createRow(7);
                    for (int col = 0; col < tableModel.getColumnCount() - 1; col++) {
                        Cell cell = tableHeaderRow.createCell(col * 2); // Mỗi cột chiếm 2 ô
                        cell.setCellValue(tableModel.getColumnName(col));
                        // Hợp nhất ô cho tiêu đề cột
                        sheet2.addMergedRegion(new CellRangeAddress(7, 7, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho tên cột
                    }

                    for (int row = 0; row < tableModel.getRowCount(); row++) {
                        Row dataRow = sheet2.createRow(row + 8); // Bắt đầu từ hàng 4
                        for (int col = 0; col < tableModel.getColumnCount() - 1; col++) {
                            // Tạo ô đầu tiên cho dữ liệu
                            Cell cell1 = dataRow.createCell(col * 2);
                            Object value;
                            if (col != 0 && col != 1) {
                                value = currencyFormatToDouble(tableModel.getValueAt(row, col).toString());
                                cell1.setCellValue(value.toString());
                            } else {
                                value = tableModel.getValueAt(row, col);
                                cell1.setCellValue(value.toString());
                            }
                            sheet2.addMergedRegion(new CellRangeAddress(row + 8, row + 8, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho dữ liệu
                        }
                    }

                    // Danh sách các đơn đặt bàn Hủy
                    Sheet sheet3 = workbook.createSheet("Danh sách đơn đặt bàn");

                    Row titleRow3 = sheet3.createRow(1);
                    Cell titleCell3 = titleRow3.createCell(0);
                    titleCell3.setCellValue("Danh Sách Các Đơn Đặt Bàn");
                    titleCell3.setCellStyle(titleStyle);

                    sheet3.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));

                    Row tongCocRow = sheet3.createRow(3);
                    Cell tongCocCell = tongCocRow.createCell(0);
                    tongCocCell.setCellValue(lblMaxValue1.getText());
                    sheet3.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
                    Cell tongCocValueCell = tongCocRow.createCell(2);
                    tongCocValueCell.setCellValue(currencyFormatToDouble(txtTongCoc.getText()) + "");
                    sheet3.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));

                    Row tableHeaderRow2 = sheet3.createRow(5);
                    for (int col = 0; col < tableModel2.getColumnCount() - 1; col++) {
                        Cell cell = tableHeaderRow2.createCell(col * 2); // Mỗi cột chiếm 2 ô
                        cell.setCellValue(tableModel2.getColumnName(col));
                        // Hợp nhất ô cho tiêu đề cột
                        sheet3.addMergedRegion(new CellRangeAddress(5, 5, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho tên cột
                    }

                    for (int row = 0; row < tableModel2.getRowCount(); row++) {
                        Row dataRow = sheet3.createRow(row + 6); // Bắt đầu từ hàng 4
                        for (int col = 0; col < tableModel2.getColumnCount() - 1; col++) {
                            // Tạo ô đầu tiên cho dữ liệu
                            Cell cell1 = dataRow.createCell(col * 2);
                            Object value;
                            if (col == 8) {
                                value = currencyFormatToDouble(tableModel2.getValueAt(row, col).toString());
                                cell1.setCellValue(value.toString());
                            } else {
                                value = tableModel2.getValueAt(row, col);
                                cell1.setCellValue(value.toString());
                            }
                            sheet3.addMergedRegion(new CellRangeAddress(row + 6, row + 6, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho dữ liệu
                        }
                    }

                    // Sheet 4
                    Sheet sheet4 = workbook.createSheet("Danh sách đơn đặt bàn hủy");

                    Row titleRow4 = sheet4.createRow(1);
                    Cell titleCell4 = titleRow4.createCell(0);
                    titleCell4.setCellValue("Danh Sách Các Đơn Đặt Bàn Hủy");
                    titleCell4.setCellStyle(titleStyle);

                    sheet4.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));

                    Row tongTNRow = sheet4.createRow(3);
                    Cell tongTNCell = tongTNRow.createCell(0);
                    tongTNCell.setCellValue(lblMaxValue2.getText());
                    sheet4.addMergedRegion(new CellRangeAddress(3, 3, 0, 1));
                    Cell tongTNValueCell = tongTNRow.createCell(2);
                    tongTNValueCell.setCellValue(currencyFormatToDouble(txtTongTienNhan.getText()) + "");
                    sheet4.addMergedRegion(new CellRangeAddress(3, 3, 2, 3));

                    Row tongTHRow = sheet4.createRow(4);
                    Cell tongTHCell = tongTHRow.createCell(0);
                    tongTHCell.setCellValue(lblMinValue3.getText());
                    sheet4.addMergedRegion(new CellRangeAddress(4, 4, 0, 1));
                    Cell tongTHValueCell = tongTHRow.createCell(2);
                    tongTHValueCell.setCellValue(currencyFormatToDouble(txtTongTienHoan.getText()) + "");
                    sheet4.addMergedRegion(new CellRangeAddress(4, 4, 2, 3));

                    Row tableHeaderRow3 = sheet4.createRow(6);
                    for (int col = 0; col < tableModel3.getColumnCount() - 1; col++) {
                        Cell cell = tableHeaderRow3.createCell(col * 2); // Mỗi cột chiếm 2 ô
                        cell.setCellValue(tableModel3.getColumnName(col));
                        // Hợp nhất ô cho tiêu đề cột
                        sheet4.addMergedRegion(new CellRangeAddress(6, 6, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho tên cột
                    }

                    for (int row = 0; row < tableModel3.getRowCount(); row++) {
                        Row dataRow = sheet4.createRow(row + 7); // Bắt đầu từ hàng 4
                        for (int col = 0; col < tableModel3.getColumnCount() - 1; col++) {
                            // Tạo ô đầu tiên cho dữ liệu
                            Cell cell1 = dataRow.createCell(col * 2);
                            Object value;
                            if (col == 8 || col == 10 || col == 11) {
                                value = currencyFormatToDouble(tableModel3.getValueAt(row, col).toString());
                                cell1.setCellValue(value.toString());
                            } else {
                                value = tableModel3.getValueAt(row, col);
                                cell1.setCellValue(value.toString());
                            }
                            sheet4.addMergedRegion(new CellRangeAddress(row + 7, row + 7, col * 2, col * 2 + 1)); // Hợp nhất 2 ô cho dữ liệu
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
        else {
             JOptionPane.showMessageDialog(null, "Không có dữ liệu để xuất Excel!", "Thông báo", JOptionPane.WARNING_MESSAGE);
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
        jPanel3 = new javax.swing.JPanel();
        table_sp = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        lblMaxValue = new javax.swing.JLabel();
        lblMinValue = new javax.swing.JLabel();
        txtTongTT = new javax.swing.JLabel();
        txtTongTV = new javax.swing.JLabel();
        btnExcel = new gui.component.Button();
        lblMinValue2 = new javax.swing.JLabel();
        txtTongSN = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        table_sp2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        lblMaxValue1 = new javax.swing.JLabel();
        txtTongCoc = new javax.swing.JLabel();
        btnExcel1 = new gui.component.Button();
        jPanel10 = new javax.swing.JPanel();
        table_sp3 = new javax.swing.JScrollPane();
        table3 = new javax.swing.JTable();
        lblMaxValue2 = new javax.swing.JLabel();
        txtTongTienNhan = new javax.swing.JLabel();
        lblMinValue3 = new javax.swing.JLabel();
        txtTongTienHoan = new javax.swing.JLabel();
        btnExcel2 = new gui.component.Button();
        tabbedPaneCustom2 = new gui.component.TabbedPaneCustom();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTongDoanhThu = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTongSoHD = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtTongSoDDB = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtTongSoDDBHuy = new javax.swing.JLabel();
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
        jcbLoaiBan = new javax.swing.JComboBox<>();
        txtDate = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(217, 217, 217));

        tabbedPaneCustom1.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(50, 50, 50));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(153, 153, 153));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        chartTitle.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        chartTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chartTitle.setText("Báo Cáo ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chartTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(chartTitle)
                .addGap(18, 18, 18)
                .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        tabbedPaneCustom1.addTab("Tổng quan", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        table.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
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
            table.getColumnModel().getColumn(6).setMinWidth(35);
            table.getColumnModel().getColumn(6).setPreferredWidth(35);
            table.getColumnModel().getColumn(6).setMaxWidth(35);
        }

        lblMaxValue.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMaxValue.setText("Tổng tiền Thanh Toán:");

        lblMinValue.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMinValue.setText("Tổng giảm giá TV:");

        txtTongTT.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTongTT.setText("_________");

        txtTongTV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTongTV.setText("_________");

        btnExcel.setBackground(new java.awt.Color(50, 50, 50));
        btnExcel.setForeground(new java.awt.Color(255, 255, 255));
        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-excel-32.png"))); // NOI18N
        btnExcel.setText("Xuất báo cáo Excel");
        btnExcel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        lblMinValue2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMinValue2.setText("Tổng giảm giá SN:");

        txtTongSN.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTongSN.setText("_________");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblMaxValue)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTT, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMinValue)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTV, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMinValue2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongSN, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(table_sp))
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(table_sp, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMinValue2)
                        .addComponent(txtTongSN))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMaxValue)
                        .addComponent(lblMinValue)
                        .addComponent(txtTongTT)
                        .addComponent(txtTongTV)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3))
        );

        tabbedPaneCustom1.addTab("Chi tiết Hóa đơn", jPanel3);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        table2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ngày tạo", "Mã DDB", "Họ tên KH", "SDT", "Số lượng KH", "Số bàn", "Giờ hẹn", "Tiền cọc", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_sp2.setViewportView(table2);
        if (table2.getColumnModel().getColumnCount() > 0) {
            table2.getColumnModel().getColumn(9).setMinWidth(35);
            table2.getColumnModel().getColumn(9).setPreferredWidth(35);
            table2.getColumnModel().getColumn(9).setMaxWidth(35);
        }

        lblMaxValue1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMaxValue1.setText("Tổng tiền cọc:");

        txtTongCoc.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTongCoc.setText("_________");

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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblMaxValue1)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(table_sp2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(table_sp2, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaxValue1)
                    .addComponent(txtTongCoc)
                    .addComponent(btnExcel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        tabbedPaneCustom1.addTab("Chi tiết Đơn Đặt Bàn", jPanel9);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        table3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        table3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ngày tạo", "Mã DDB", "Họ tên KH", "SDT", "Số lượng KH", "Số bàn", "Giờ hẹn", "Tiền cọc", "Giờ hủy", "Tiền hoàn", "Tiền nhận", "NV tạo", "NV hủy", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_sp3.setViewportView(table3);
        if (table3.getColumnModel().getColumnCount() > 0) {
            table3.getColumnModel().getColumn(14).setMinWidth(35);
            table3.getColumnModel().getColumn(14).setPreferredWidth(35);
            table3.getColumnModel().getColumn(14).setMaxWidth(35);
        }

        lblMaxValue2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMaxValue2.setText("Tổng tiền nhận:");

        txtTongTienNhan.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTongTienNhan.setText("_________");

        lblMinValue3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMinValue3.setText("Tổng tiền hoàn:");

        txtTongTienHoan.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        txtTongTienHoan.setText("_________");

        btnExcel2.setBackground(new java.awt.Color(50, 50, 50));
        btnExcel2.setForeground(new java.awt.Color(255, 255, 255));
        btnExcel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-excel-32.png"))); // NOI18N
        btnExcel2.setText("Xuất báo cáo Excel");
        btnExcel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnExcel2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcel2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(lblMaxValue2)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMinValue3)
                        .addGap(18, 18, 18)
                        .addComponent(txtTongTienHoan, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(table_sp3))
                .addGap(30, 30, 30))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(table_sp3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaxValue2)
                    .addComponent(lblMinValue3)
                    .addComponent(txtTongTienNhan)
                    .addComponent(txtTongTienHoan)
                    .addComponent(btnExcel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        tabbedPaneCustom1.addTab("Chi tiết Đơn Đặt Bàn Hủy", jPanel10);

        tabbedPaneCustom2.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        tabbedPaneCustom2.setSelectedColor(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel6.setText("Tổng Doanh Thu");

        txtTongDoanhThu.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtTongDoanhThu.setText("...");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(txtTongDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel9.setText("Tổng Số Hóa Đơn");

        txtTongSoHD.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtTongSoHD.setText("...");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongSoHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addComponent(txtTongSoHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel10.setText("Tổng Số Đơn Đặt Bàn");

        txtTongSoDDB.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtTongSoDDB.setText("...");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongSoDDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24)
                .addComponent(txtTongSoDDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        jLabel12.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel12.setText("Tổng Số Đơn Đặt Bàn Hủy");

        txtTongSoDDBHuy.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        txtTongSoDDBHuy.setText("...");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTongSoDDBHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(26, 26, 26)
                .addComponent(txtTongSoDDBHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(19, 19, 19))
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
        jLabel14.setText("Loại bàn:");

        jcbLoaiBan.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jcbLoaiBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbLoaiBanActionPerformed(evt);
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
                                    .addComponent(jcbThang, 0, 126, Short.MAX_VALUE)
                                    .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbNam, 0, 111, Short.MAX_VALUE)
                                    .addComponent(jcbLoaiBan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jcbQuy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(1, 1, 1)))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(radioBtnQuy, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(radioBtnNam)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jcbLoaiBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jcbQuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jcbNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 24, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPaneCustom3.addTab("Lọc", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(tabbedPaneCustom3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabbedPaneCustom2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tabbedPaneCustom3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        try {
            xuatExcel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnExcel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcel1ActionPerformed
        try {
            xuatExcel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnExcel1ActionPerformed

    private void btnExcel2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcel2ActionPerformed
        try {
            xuatExcel();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnExcel2ActionPerformed

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

    private void jcbLoaiBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbLoaiBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbLoaiBanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button btnExcel;
    private gui.component.Button btnExcel1;
    private gui.component.Button btnExcel2;
    private gui.component.Button btnRefresh;
    private gui.component.Button btnThongKe;
    private javax.swing.ButtonGroup buttonGroup1;
    private gui.component.Chart chart1;
    private javax.swing.JLabel chartTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JComboBox<String> jcbLoaiBan;
    private javax.swing.JComboBox<String> jcbNam;
    private javax.swing.JComboBox<String> jcbQuy;
    private javax.swing.JComboBox<String> jcbThang;
    private javax.swing.JLabel lblMaxValue;
    private javax.swing.JLabel lblMaxValue1;
    private javax.swing.JLabel lblMaxValue2;
    private javax.swing.JLabel lblMinValue;
    private javax.swing.JLabel lblMinValue2;
    private javax.swing.JLabel lblMinValue3;
    private javax.swing.JRadioButton radioBtnNam;
    private javax.swing.JRadioButton radioBtnNgay;
    private javax.swing.JRadioButton radioBtnQuy;
    private javax.swing.JRadioButton radioBtnThang;
    private gui.component.TabbedPaneCustom tabbedPaneCustom1;
    private gui.component.TabbedPaneCustom tabbedPaneCustom2;
    private gui.component.TabbedPaneCustom tabbedPaneCustom3;
    private javax.swing.JTable table;
    private javax.swing.JTable table2;
    private javax.swing.JTable table3;
    private javax.swing.JScrollPane table_sp;
    private javax.swing.JScrollPane table_sp2;
    private javax.swing.JScrollPane table_sp3;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JLabel txtTongCoc;
    private javax.swing.JLabel txtTongDoanhThu;
    private javax.swing.JLabel txtTongSN;
    private javax.swing.JLabel txtTongSoDDB;
    private javax.swing.JLabel txtTongSoDDBHuy;
    private javax.swing.JLabel txtTongSoHD;
    private javax.swing.JLabel txtTongTT;
    private javax.swing.JLabel txtTongTV;
    private javax.swing.JLabel txtTongTienHoan;
    private javax.swing.JLabel txtTongTienNhan;
    // End of variables declaration//GEN-END:variables
}
