/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;

import dao.Ban_DAO;
import dao.ChiTietHoaDon_DAO;
import dao.DichVu_DAO;
import dao.HoaDon_DAO;
import dao.LoaiBan_DAO;
import dao.LoaiMonAn_DAO;
import dao.MonAn_DAO;
import dao.NhanVien_DAO;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.DichVu;
import entity.HoaDon;
import entity.LoaiBan;
import entity.LoaiMonAn;
import entity.MonAn;
import entity.NhanVien;
import gui.component.ItemMonAn;
import gui.main.Admin_DashBoard;
import gui.main.ThuNgan_DashBoard;
import gui.swing.WrapLayout;
import gui.swing.table.ScrollBarCustomUI;
import gui.swing.table.TableCustom;
import gui.swing.table.cell.EventCellInputChange;
import gui.swing.table.cell.SpinnerCellEditor;
import gui.swing.table.cell.SpinnerCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JButton;
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
public class TaoHoaDon_Admin_PN extends javax.swing.JPanel {

    private MonAn_DAO monAn_dao = new MonAn_DAO();
    private DichVu_DAO dv_dao = new DichVu_DAO();
    private HoaDon_DAO hd_dao = new HoaDon_DAO();
    private ChiTietHoaDon_DAO cthd_dao = new ChiTietHoaDon_DAO();
    private Ban_DAO ban_dao = new Ban_DAO();
    private LoaiMonAn_DAO loaiMa_dao = new LoaiMonAn_DAO();
    private LoaiBan_DAO loaiBan_dao = new LoaiBan_DAO();
    private NhanVien_DAO nv_dao = new NhanVien_DAO();
    private Ban ban = null;
    private Admin_DashBoard dashBoard;
    private NhanVien nv = null;

    /**
     * Creates new form TaoHoaDon_PN1
     */
    public TaoHoaDon_Admin_PN(Ban ban, Admin_DashBoard dash) {
        initComponents();
        setWrapLayout();
        customItemPane();
        customTable();
        hideIdColumn();
        setCellRender();
        this.ban = ban;
        this.dashBoard = dash;
        this.nv = nv_dao.getNV(dash.getHeader().getTextMaNV());
        batSuKienTable();
        setGioVao();
        setBanLabel();
        loadLoaiMon();
        DefaultSelectedLoaiMon();
    }

    /**
     * Codes điều chỉnh giao diện
     */
    public void setWrapLayout() {
        foodsPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 5, 5));
        loaiMonAnPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 3, 3));
    }

    public void hideIdColumn() {
        TableColumnModel columnModel = orderTable.getColumnModel();
        TableColumn column = columnModel.getColumn(6);
        columnModel.removeColumn(column);
    }

    public void setCellRender() {
        orderTable.getColumnModel().getColumn(1).setCellEditor(new SpinnerCellEditor(new EventCellInputChange() {
            @Override
            public void inputChanged() {
                tinhTongTien();
            }
        }));
        orderTable.getColumnModel().getColumn(1).setCellRenderer(new SpinnerCellRenderer());
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
        try {
            Locale locale = new Locale("vi", "VN");
            NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
            return formatter.parse(currency).doubleValue();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public String formatLocalDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return dateTime.format(formatter);
    }

    /**
     * Code tính toán
     */
    public void tinhTongTien() {
        double tongTien = 0;
        DefaultTableModel df = (DefaultTableModel) orderTable.getModel();
        for (int i = 0; i < df.getRowCount(); i++) {
            tongTien += currencyFormatToDouble((String) df.getValueAt(i, 4));
        }
        tongTienLabel.setText(currencyFormat(tongTien));
    }

    /*
    * Code xử lý
     */

    public void DefaultSelectedLoaiMon() {
        Component[] list = loaiMonAnPanel.getComponents();
        for (Component c : list) {
            JButton but = (JButton) c;
            but.setBackground(Color.WHITE);
        }
        JButton but = (JButton) list[0];
        but.setBackground(Color.ORANGE);
        loadMonTheoLoai(but.getToolTipText());
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

    public void createOrder() {
        String maNV = nv.getMaNV();
        System.out.println(nv.getHoTenNV());
        double tongTien = currencyFormatToDouble(tongTienLabel.getText());
        DichVu dichVu = dv_dao.getActiveService();
        ban_dao.updateTableState(ban.getMaBan(), 1);
        DefaultTableModel df = (DefaultTableModel) orderTable.getModel();

        if (hd_dao.createOrder(tongTien, dichVu.getMaDV(), maNV, ban.getMaBan())) {
            for (int i = 0; i < orderTable.getRowCount(); i++) {
                String maMA = (String) df.getValueAt(i, 6);
                double thanhTien = currencyFormatToDouble((String) df.getValueAt(i, 4));
                int soLuong = (int) df.getValueAt(i, 1);
                double giaSauGiam = currencyFormatToDouble((String) df.getValueAt(i, 3));
                cthd_dao.createOrderDetail(new ChiTietHoaDon(new HoaDon(hd_dao.hoaDonMoiNhat()), new MonAn(maMA), thanhTien, soLuong, giaSauGiam));
            }
            JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
            dashBoard.showPanel(1, 0);
        }
    }

    public void loadMonTheoLoai(String maLoai) {
        ArrayList<MonAn> listMA = monAn_dao.getMonTheoLoai(maLoai);
        foodsPanel.removeAll(); // Xóa tất cả các thành phần
        foodsPanel.revalidate(); // Cập nhật lại bố cục
        foodsPanel.repaint(); // Vẽ lại giao diện
        for (MonAn ma : listMA) {
            foodsPanel.add(new ItemMonAn(ma, orderTable, tongTienLabel));
        }
    }

    public void setGioVao() {
        thoiGianVaoLabel.setText(formatLocalDateTime(LocalDateTime.now()));
    }

    public void setBanLabel() {
        LoaiBan lb = loaiBan_dao.getLoaiBanTheoMa(ban.getLoaiBan().getMaLB());
        tableLable.setText("Bàn " + ban.getSoBan() + " / " + lb.getTenLB());
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        foodsJScrollPane = new javax.swing.JScrollPane();
        foodsPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        loaiMonAnPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        thoiGianVaoLabel = new javax.swing.JLabel();
        tableLable = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tongTienLabel = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        button1 = new gui.component.Button();
        taoHDBtn = new gui.component.Button();
        button2 = new gui.component.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 710));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        jScrollPane3.setPreferredSize(new java.awt.Dimension(566, 120));

        loaiMonAnPanel.setBackground(new java.awt.Color(255, 255, 255));
        loaiMonAnPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loại món ăn\n", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14))); // NOI18N
        loaiMonAnPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        loaiMonAnPanel.setPreferredSize(new java.awt.Dimension(550, 110));

        javax.swing.GroupLayout loaiMonAnPanelLayout = new javax.swing.GroupLayout(loaiMonAnPanel);
        loaiMonAnPanel.setLayout(loaiMonAnPanelLayout);
        loaiMonAnPanelLayout.setHorizontalGroup(
            loaiMonAnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 556, Short.MAX_VALUE)
        );
        loaiMonAnPanelLayout.setVerticalGroup(
            loaiMonAnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(loaiMonAnPanel);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
            .addComponent(foodsJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(foodsJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(423, 710));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel1.setText("Bàn: ");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel2.setText("Thời gian vào:");

        thoiGianVaoLabel.setBackground(new java.awt.Color(255, 255, 255));
        thoiGianVaoLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        tableLable.setBackground(new java.awt.Color(255, 255, 255));
        tableLable.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(87, 87, 87)
                        .addComponent(tableLable, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(thoiGianVaoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tableLable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(thoiGianVaoLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 500));

        orderTable.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
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
        orderTable.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        orderTable.setPreferredSize(new java.awt.Dimension(400, 600));
        orderTable.setRowHeight(40);
        jScrollPane1.setViewportView(orderTable);
        if (orderTable.getColumnModel().getColumnCount() > 0) {
            orderTable.getColumnModel().getColumn(1).setPreferredWidth(20);
            orderTable.getColumnModel().getColumn(5).setPreferredWidth(5);
        }

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel7.setText("Tổng tiền: ");

        tongTienLabel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

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

        button1.setBackground(new java.awt.Color(0, 0, 0));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Xóa trắng");
        button1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(180, 46));
        button1.setRippleColor(new java.awt.Color(255, 255, 255));
        button1.setShadowColor(new java.awt.Color(255, 255, 255));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        taoHDBtn.setBackground(new java.awt.Color(51, 255, 51));
        taoHDBtn.setForeground(new java.awt.Color(0, 0, 0));
        taoHDBtn.setText("Tạo hóa đơn");
        taoHDBtn.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        taoHDBtn.setPreferredSize(new java.awt.Dimension(180, 46));
        taoHDBtn.setRippleColor(new java.awt.Color(255, 255, 255));
        taoHDBtn.setShadowColor(new java.awt.Color(255, 255, 255));
        taoHDBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                taoHDBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addComponent(taoHDBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(taoHDBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                .addContainerGap())
        );

        button2.setBackground(new java.awt.Color(0, 0, 0));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/back.png"))); // NOI18N
        button2.setText("  Quay về sơ đồ bàn");
        button2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        ((DefaultTableModel) orderTable.getModel()).setRowCount(0);
        setGioVao();
        DefaultSelectedLoaiMon();
    }//GEN-LAST:event_button1ActionPerformed

    private void taoHDBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_taoHDBtnActionPerformed
        // TODO add your handling code here:
        createOrder();
    }//GEN-LAST:event_taoHDBtnActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        dashBoard.showPanel(1, 0);
    }//GEN-LAST:event_button2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button button1;
    private gui.component.Button button2;
    private javax.swing.JScrollPane foodsJScrollPane;
    private javax.swing.JPanel foodsPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel loaiMonAnPanel;
    private javax.swing.JTable orderTable;
    private javax.swing.JLabel tableLable;
    private gui.component.Button taoHDBtn;
    private javax.swing.JLabel thoiGianVaoLabel;
    private javax.swing.JLabel tongTienLabel;
    // End of variables declaration//GEN-END:variables
}
