/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;

import gui.swing.WrapLayout;
import dao.Ban_DAO;
import dao.ChiTietDatBan_DAO;
import dao.DonDatBan_DAO;
import dao.LoaiBan_DAO;
import dao.LoaiMonAn_DAO;
import gui.swing.table.TableCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import dao.MonAn_DAO;
import dao.NhanVien_DAO;
import entity.Ban;
import entity.ChiTietDatBan;
import entity.DonDatBan;
import entity.LoaiBan;
import entity.LoaiMonAn;
import entity.MonAn;
import entity.NhanVien;
import gui.component.Header;
import gui.component.ItemMonAnDatBan;
import gui.component.ItemTable;
import gui.main.Admin_DashBoard;
import gui.main.LeTan_DashBoard;
import gui.swing.table.ScrollBarCustomUI;
import gui.swing.table.cell.EventCellInputChange;
import gui.swing.table.cell.SpinnerCellEditor;
import gui.swing.table.cell.SpinnerCellRenderer;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Thanh Tuan
 */
public class DatBan_PN extends javax.swing.JPanel {

    private MonAn_DAO monAn_dao = new MonAn_DAO();
    private Ban_DAO ban_dao = new Ban_DAO();
    private LoaiMonAn_DAO loaiMa_dao = new LoaiMonAn_DAO();
    private LoaiBan_DAO loaiBan_dao = new LoaiBan_DAO();
    private LocalDateTime gioHen;
    private DefaultTableModel tableModel;
    private DonDatBan_DAO donDatBan_DAO;
    private ChiTietDatBan_DAO ctdb_DAO;
    private NhanVien_DAO nv_dao = new NhanVien_DAO();
    private NhanVien nv;
    /**
     * Creates new form TaoHoaDon_PN
     */
    public DatBan_PN(Admin_DashBoard dashBoard) {
        initComponents();
        customTable();
        setWrapLayout();
        hideIdColumn();
        setCellRender();
        batSuKienTable();
        customItemPane();
        loadLoaiMon();
        loadLoaiBan();
        DefaultSelectLoaiBan();
        DefaultSelectedLoaiMon();
        init();
        this.nv = nv_dao.getNV(dashBoard.getHeader().getTextMaNV());
    }
    
    public DatBan_PN(LeTan_DashBoard dashBoard) {
        initComponents();
        customTable();
        setWrapLayout();
        hideIdColumn();
        setCellRender();
        batSuKienTable();
        customItemPane();
        loadLoaiMon();
        loadLoaiBan();
        DefaultSelectLoaiBan();
        DefaultSelectedLoaiMon();
        init();
        this.nv = nv_dao.getNV(dashBoard.getHeader().getTextMaNV());
    }

    /**
     * Codes điều chỉnh giao diện
     */
    private void init() {
        txtDate.setDate(new Date());
        timePicker1.setSelectedTime(new Date());
        tongTienLabel.setText(currencyFormat(100000));
        tableModel = (DefaultTableModel) orderTable.getModel();
        donDatBan_DAO = new DonDatBan_DAO();
        ctdb_DAO = new ChiTietDatBan_DAO();
    }

    public void setWrapLayout() {
        foodsPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 5, 5));
        tablesPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 5, 5));
        loaiMonAnPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 3, 3));
        loaiBanPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 3, 3));
    }

    public void hideIdColumn() {
        TableColumnModel columnModel = orderTable.getColumnModel();
        TableColumn column = columnModel.getColumn(6);
        columnModel.removeColumn(column);
    }

    public void setCellRender() {
        orderTable.getColumnModel().getColumn(1).setCellRenderer(new SpinnerCellRenderer());
        orderTable.getColumnModel().getColumn(1).setCellEditor(new SpinnerCellEditor(new EventCellInputChange() {
            @Override
            public void inputChanged() {
                tinhTongTien();
            }
        }));
        orderTable.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
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

    private void customItemPane() {
        tablesJScrollPane.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
        tablesJScrollPane.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
        foodsJScrollPane.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
        foodsJScrollPane.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
    }

    private void customTable() {
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        orderTable.getTableHeader().setFont(new Font("Sanserif", Font.BOLD, 12));
        orderTable.getTableHeader().setBackground(new Color(50, 50, 50));
        orderTable.repaint();
    }

    /**
     * Codes format tiền tệ & ngày giờ
     */
    public String currencyFormat(double price) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(price);
    }

    public double currencyFormatToDouble(String currency) {
        String str = currency.replaceAll("[^\\d]", "");
        return Double.parseDouble(str);
    }

    public String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Code tính toán
     */
    public void tinhTongTien() {
        double tongTien = 100000;
        DefaultTableModel df = (DefaultTableModel) orderTable.getModel();
        for (int i = 0; i < df.getRowCount(); i++) {
            tongTien += currencyFormatToDouble((String) df.getValueAt(i, 4)) * 0.3;
        }
        tongTienLabel.setText(currencyFormat(tongTien));
    }

    /*
    * Code xử lý
     */
    public void DefaultSelectedLoaiMon() {
        Component[] list = loaiMonAnPanel.getComponents();
        JButton but = (JButton) list[0];
        loadMonTheoLoai(but.getToolTipText());
    }

    public void DefaultSelectLoaiBan() {
        Component[] list = loaiBanPanel.getComponents();
        JButton but = (JButton) list[0];
        loadBanTheoLoai(but.getToolTipText());
    }

    public void loadLoaiMon() {
        ArrayList<LoaiMonAn> list = loaiMa_dao.getListLoaiMonAn();
        for (LoaiMonAn loai : list) {
            JButton but = new JButton();
            but.setBackground(Color.WHITE);
            but.setText(loai.getTenLoaiMA());
            but.setToolTipText(loai.getMaLoaiMA());
            but.setBorder(null);
            but.setBorderPainted(false);
            but.setFocusPainted(false);
            but.setPreferredSize(new Dimension(100, 40));
            addEventToLoaiMonButton(but);
            loaiMonAnPanel.add(but);
        }
    }

    public void loadLoaiBan() {
        ArrayList<LoaiBan> list = loaiBan_dao.getListLoaiBan();
        for (LoaiBan loai : list) {
            JButton but = new JButton();
            but.setBackground(Color.WHITE);
            but.setText(loai.getTenLB());
            but.setToolTipText(loai.getMaLB());
            but.setBorder(null);
            but.setBorderPainted(false);
            but.setFocusPainted(false);
            but.setPreferredSize(new Dimension(100, 40));
            addEventToLoaiBanButton(but);
            loaiBanPanel.add(but);
        }
    }

    public void addEventToLoaiMonButton(JButton button) {
        button.addActionListener(e -> {
            Component[] list = loaiMonAnPanel.getComponents();
            button.setBackground(Color.ORANGE);
            for (Component c : list) {
                JButton but = (JButton) c;
                if (but != button) {
                    but.setBackground(Color.WHITE);
                }
            }
            button.setBackground(Color.ORANGE);
            loadMonTheoLoai(button.getToolTipText());
        });
    }

    public void addEventToLoaiBanButton(JButton button) {
        button.addActionListener(e -> {
            Component[] list = loaiBanPanel.getComponents();
            button.setBackground(Color.ORANGE);
            for (Component c : list) {
                JButton but = (JButton) c;
                if (but != button) {
                    but.setBackground(Color.WHITE);
                }
            }
            button.setBackground(Color.ORANGE);
            loadBanTheoLoai(button.getToolTipText());
        });
    }

    public void loadMonTheoLoai(String maLoai) {
        ArrayList<MonAn> listMA = monAn_dao.getMonTheoLoai(maLoai);
        foodsPanel.removeAll(); // Xóa tất cả các thành phần
        foodsPanel.revalidate(); // Cập nhật lại bố cục
        foodsPanel.repaint(); // Vẽ lại giao diện
        for (MonAn ma : listMA) {
            foodsPanel.add(new ItemMonAnDatBan(ma, orderTable, tongTienLabel));
        }
    }

    public void loadBanTheoLoai(String maLoai) {
        ArrayList<Ban> listMA = ban_dao.getListBanTheoLoai(maLoai);
        tablesPanel.removeAll(); // Xóa tất cả các thành phần
        tablesPanel.revalidate(); // Cập nhật lại bố cục
        tablesPanel.repaint(); // Vẽ lại giao diện
        for (Ban b : listMA) {
            tablesPanel.add(new ItemTable(tableLable, b));
        }
    }

    public void batSuKienTable() {
        // Bắt sự kiện nhấp chuột vào bảng
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = orderTable.getSelectedRow();
                int column = orderTable.getSelectedColumn();
                DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
                // Kiểm tra xem người dùng có nhấp vào cột chứa icon không
                if (column == 5 && row >= 0) {
                    int ask = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa món ăn này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
                    if (ask == JOptionPane.YES_OPTION) {
                        model.removeRow(row); // Xóa hàng trong model
                        tinhTongTien(); // Cập nhật lại tổng tiền sau khi xóa
                        orderTable.setEnabled(true);
                    }
                }

            }
        });
    }

    private boolean valid() {
        if (txtKH.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không được rỗng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtSLKH.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Số lượng khách hàng không được rỗng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không được rỗng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (!txtSDT.getText().matches("^(0[3|5|7|8|9])[0-9]{8}$")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại khách hàng không hợp lý!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    private boolean checkSLKHTrenBan() {
        if (tableLable.getToolTipText() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        Ban ban = ban_dao.getBan(tableLable.getToolTipText());
        int slKH = Integer.parseInt(txtSLKH.getText());

        if (ban == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn bàn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (slKH <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng khách hàng không hợp lệ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (ban.getSoGhe() < slKH) {
            JOptionPane.showMessageDialog(null, "Bàn này không đủ ghế cho khách hàng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean checkTime() {
        // Tính thời gian hiện tại cộng thêm 4 tiếng
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime thoiGianDatToiThieu = currentTime.plusHours(4);

        // Giờ đặt 
        String ngayDat = new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate());
        String gioDat = txtTime.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        gioHen = LocalDateTime.parse(ngayDat + " " + gioDat, formatter);

        // Check giờ đặt phải sau 8 tiếng
        if (gioHen.isBefore(thoiGianDatToiThieu)) {
            JOptionPane.showMessageDialog(null, "Giờ hẹn phải sau 4 tiếng so với thời gian hiện tại", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        } 
        
        LocalDate maxNH = LocalDate.now().plusDays(30);
        LocalDate ngayHen = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(txtDate.getDate()));
        if (ngayHen.isBefore(LocalDate.now()) || ngayHen.isAfter(maxNH)) {
            JOptionPane.showMessageDialog(null, "Ngày hẹn chỉ được đặt trong vòng 30 ngày kể từ ngày hiện tại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        LocalTime start = LocalTime.of(11, 0);
        LocalTime end = LocalTime.of(21, 0);
        LocalTime gH = LocalTime.parse(txtTime.getText()); 
        if (gH.isBefore(start) || gH.isAfter(end)) {
            JOptionPane.showMessageDialog(null, "Giờ hẹn chỉ được đặt từ 11h - 21h!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean checkTable() {
        LocalDateTime time = donDatBan_DAO.checkTimeBan(tableLable.getToolTipText(), gioHen);
        if (time != null) {
            JOptionPane.showMessageDialog(null, "Chọn thời gian trước hoặc sau 3 tiếng vì đã có đơn đặt bàn khác vào lúc " + time.format(DateTimeFormatter.ofPattern("HH:mm:ss")), "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void taoDDB() {
        String tenKH = txtKH.getText();
        String sdt = txtSDT.getText();
        int sLKH = Integer.parseInt(txtSLKH.getText());
        String ghiChu = txtGhiChu.getText();
        String maBan = tableLable.getToolTipText();
        String maNV = nv.getMaNV();
        double tienCoc = currencyFormatToDouble(tongTienLabel.getText());

        if (donDatBan_DAO.insert(tenKH, sdt, sLKH, tienCoc, gioHen, maNV, ghiChu, maBan)) {
            if (tableModel.getRowCount() > 0) {
                for (int i = 0; i < orderTable.getRowCount(); i++) {
                    String maMA = (String) tableModel.getValueAt(i, 6);
                    double thanhTien = currencyFormatToDouble((String) tableModel.getValueAt(i, 4));
                    int soLuong = (int) tableModel.getValueAt(i, 1);
                    double giaSauGiam = currencyFormatToDouble((String) tableModel.getValueAt(i, 3));
                    ctdb_DAO.insert(new ChiTietDatBan(new MonAn(maMA), new DonDatBan(donDatBan_DAO.datBanMoiNhat()), soLuong, thanhTien, giaSauGiam));
                }
            }
            JOptionPane.showMessageDialog(this, "Tạo đơn đặt bàn thành công");
            refresh();
        }
        else {
            JOptionPane.showMessageDialog(this, "Tạo đơn đặt bàn không thành công");
        }
    }
    
    private void refresh() {
        txtKH.setText("");
        txtSLKH.setText("");
        txtSDT.setText("");
        txtGhiChu.setText("");
        tableLable.setText("");
        tableLable.setToolTipText("");
        tableModel.setRowCount(0);
        txtDate.setDate(new Date());
        timePicker1.setSelectedTime(new Date());
        tongTienLabel.setText(currencyFormat(100000));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        timePicker1 = new com.raven.swing.TimePicker();
        jPanel1 = new javax.swing.JPanel();
        tabbedPaneCustom1 = new gui.component.TabbedPaneCustom();
        jPanel3 = new javax.swing.JPanel();
        tablesJScrollPane = new javax.swing.JScrollPane();
        tablesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        loaiBanPanel = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        foodsJScrollPane = new javax.swing.JScrollPane();
        foodsPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        loaiMonAnPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKH = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSLKH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtTime = new javax.swing.JTextField();
        button3 = new gui.component.Button();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        tableLable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tongTienLabel = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        button2 = new gui.component.Button();
        button1 = new gui.component.Button();

        timePicker1.set24hourMode(true);
        timePicker1.setDisplayText(txtTime);
        timePicker1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(550, 710));

        tabbedPaneCustom1.setForeground(new java.awt.Color(255, 255, 255));
        tabbedPaneCustom1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        tabbedPaneCustom1.setPreferredSize(new java.awt.Dimension(571, 710));
        tabbedPaneCustom1.setSelectedColor(new java.awt.Color(51, 51, 51));
        tabbedPaneCustom1.setUnselectedColor(new java.awt.Color(153, 153, 153));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tablesPanel.setPreferredSize(new java.awt.Dimension(581, 600));

        javax.swing.GroupLayout tablesPanelLayout = new javax.swing.GroupLayout(tablesPanel);
        tablesPanel.setLayout(tablesPanelLayout);
        tablesPanelLayout.setHorizontalGroup(
            tablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );
        tablesPanelLayout.setVerticalGroup(
            tablesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        tablesJScrollPane.setViewportView(tablesPanel);

        jScrollPane2.setPreferredSize(new java.awt.Dimension(566, 124));

        loaiBanPanel.setBackground(new java.awt.Color(255, 255, 255));
        loaiBanPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại bàn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N
        loaiBanPanel.setPreferredSize(new java.awt.Dimension(564, 120));

        javax.swing.GroupLayout loaiBanPanelLayout = new javax.swing.GroupLayout(loaiBanPanel);
        loaiBanPanel.setLayout(loaiBanPanelLayout);
        loaiBanPanelLayout.setHorizontalGroup(
            loaiBanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );
        loaiBanPanelLayout.setVerticalGroup(
            loaiBanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(loaiBanPanel);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tablesJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tablesJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabbedPaneCustom1.addTab("Bàn", jPanel3);

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        foodsJScrollPane.setPreferredSize(new java.awt.Dimension(583, 550));

        foodsPanel.setPreferredSize(new java.awt.Dimension(581, 600));

        javax.swing.GroupLayout foodsPanelLayout = new javax.swing.GroupLayout(foodsPanel);
        foodsPanel.setLayout(foodsPanelLayout);
        foodsPanelLayout.setHorizontalGroup(
            foodsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );
        foodsPanelLayout.setVerticalGroup(
            foodsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        foodsJScrollPane.setViewportView(foodsPanel);

        jScrollPane3.setPreferredSize(new java.awt.Dimension(566, 124));

        loaiMonAnPanel.setBackground(new java.awt.Color(255, 255, 255));
        loaiMonAnPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại món ăn\n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N
        loaiMonAnPanel.setPreferredSize(new java.awt.Dimension(560, 120));

        javax.swing.GroupLayout loaiMonAnPanelLayout = new javax.swing.GroupLayout(loaiMonAnPanel);
        loaiMonAnPanel.setLayout(loaiMonAnPanelLayout);
        loaiMonAnPanelLayout.setHorizontalGroup(
            loaiMonAnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );
        loaiMonAnPanelLayout.setVerticalGroup(
            loaiMonAnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 108, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(loaiMonAnPanel);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foodsJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(foodsJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE))
        );

        tabbedPaneCustom1.addTab("Món ăn", jPanel32);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tabbedPaneCustom1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(423, 710));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel1.setText("Tên Khách Hàng:");

        txtKH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKHActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel2.setText("Số lượng KH:");

        txtSLKH.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel5.setText("Số điện thoại:");

        txtSDT.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel6.setText("Giờ hẹn:");

        txtDate.setDateFormatString("dd/MM/yyyy");
        txtDate.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtDate.setMinimumSize(new java.awt.Dimension(82, 30));
        txtDate.setPreferredSize(new java.awt.Dimension(103, 25));

        txtTime.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimeActionPerformed(evt);
            }
        });

        button3.setBackground(new java.awt.Color(50, 50, 50));
        button3.setForeground(new java.awt.Color(255, 255, 255));
        button3.setText("Chọn giờ");
        button3.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel8.setText("Ghi chú:");

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu);

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel4.setText("Số bàn:");

        tableLable.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(1, 1, 1))
                                    .addComponent(txtKH)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(txtSLKH, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tableLable, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtSDT))
                                        .addGap(3, 3, 3)))
                                .addGap(17, 17, 17))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jScrollPane4)
                                .addGap(18, 18, 18))))
                    .addComponent(jLabel5)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8)
                        .addComponent(jLabel6)))
                .addGap(13, 13, 13))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableLable, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtSLKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(jLabel8))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(button3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 500));

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Món", "SL", "Giá gốc", "Giá sau giảm", "Thành tiền", "Xóa", "id"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderTable.setPreferredSize(new java.awt.Dimension(400, 510));
        jScrollPane1.setViewportView(orderTable);
        if (orderTable.getColumnModel().getColumnCount() > 0) {
            orderTable.getColumnModel().getColumn(1).setPreferredWidth(15);
            orderTable.getColumnModel().getColumn(5).setPreferredWidth(5);
            orderTable.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel7.setText("Tổng tiền: ");

        tongTienLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        tongTienLabel.setForeground(new java.awt.Color(255, 51, 51));
        tongTienLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tongTienLabel.setText(".");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(206, 206, 206)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tongTienLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tongTienLabel))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        button2.setBackground(new java.awt.Color(50, 50, 50));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-refresh-32.png"))); // NOI18N
        button2.setText("Reset");
        button2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(50, 50, 50));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-order-32.png"))); // NOI18N
        button1.setText("Đặt Bàn");
        button1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, Short.MAX_VALUE)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKHActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed

    private void txtTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimeActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        int x = button3.getLocationOnScreen().x - 150;
        int y = button3.getLocationOnScreen().y + button3.getHeight();

        timePicker1.showPopup(this, x - this.getLocationOnScreen().x, y - this.getLocationOnScreen().y);
    }//GEN-LAST:event_button3ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        refresh();
    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        if (valid() && checkSLKHTrenBan() && checkTime() && checkTable()) {
            int ask = JOptionPane.showConfirmDialog(null, "Bạn có muốn tạo đơn đặt bàn này không?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (ask == JOptionPane.YES_OPTION) {
                taoDDB();
            }
        }
    }//GEN-LAST:event_button1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button button1;
    private gui.component.Button button2;
    private gui.component.Button button3;
    private javax.swing.JScrollPane foodsJScrollPane;
    private javax.swing.JPanel foodsPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPanel loaiBanPanel;
    private javax.swing.JPanel loaiMonAnPanel;
    private javax.swing.JTable orderTable;
    private gui.component.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JLabel tableLable;
    private javax.swing.JScrollPane tablesJScrollPane;
    private javax.swing.JPanel tablesPanel;
    private com.raven.swing.TimePicker timePicker1;
    private javax.swing.JLabel tongTienLabel;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSLKH;
    private javax.swing.JTextField txtTime;
    // End of variables declaration//GEN-END:variables
}
