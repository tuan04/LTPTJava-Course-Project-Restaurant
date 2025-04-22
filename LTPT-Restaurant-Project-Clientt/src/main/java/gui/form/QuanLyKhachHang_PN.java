/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;


import model.KhachHang;
import model.LoaiKhachHang;
import gui.swing.table.TableCustom;
import java.awt.Color;
import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import service.KhachHangService;
import service.LoaiKhachHangService;
import rmi.RMIClientManager;

/**
 *
 * @author tamthanh
 */
public class QuanLyKhachHang_PN extends javax.swing.JPanel {

    private KhachHangService dao_kh ;
    private LoaiKhachHangService lkh_dao;
    private DefaultTableModel tbm;

    /**
     * Creates new form QuanLiKhachHang_PN
     */
    public QuanLyKhachHang_PN() throws Exception {
        this.dao_kh=RMIClientManager.getInstance().getKhachHangService();
        this.lkh_dao=RMIClientManager.getInstance().getLoaiKhachHangService();
        
        initComponents();
        customTable();
        cbbtt.addItem("Tất cả");
        cbbtt.addItem("Hoạt động");
        cbbtt.addItem("Dừng hoạt động");
    }

    private void customTable() throws RemoteException {
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        table.getTableHeader().setFont(new Font("Sanserif", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(50, 50, 50));
        table.repaint();
        tbm = new DefaultTableModel(new String[]{"Mã Khách hàng", "Tên Khách hàng", "Số điện thoại", "Email", "Điểm tích luỹ", "Trạng thái", "Loại khách hàng", "Ngày sinh", "Ngày tạo", "Tuỳ chọn"}, 0);
        table.setModel(tbm);
        // Thêm biểu tượng "Xóa" vào cột Xóa
        ImageIcon icon = new ImageIcon(getClass().getResource("/gui/icon/edit.png"));
        table.getColumnModel().getColumn(9).setCellRenderer(new DefaultTableCellRenderer() {
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

            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cell.setHorizontalAlignment(JLabel.CENTER);  // Căn giữa icon trong cột
                return cell;
            }
        });

        duyetListVaoTable();
        duyetLoaiKHVaoComboxBox();

    }

    //
    public void duyetListVaoTable() throws RemoteException {

        List<KhachHang> list =  dao_kh.getAll();
        ImageIcon icon = new ImageIcon(getClass().getResource("/gui/icon/edit.png"));
        JLabel lblIcon = new JLabel(icon);
        for (KhachHang e : list) {
        
        Object[] ob = {
            e.getMaKH(), e.getTenKH(), e.getSdt(),
            e.getEmail(), e.getDiemTL(), e.isTrangThai() ? "Hoạt động" : "Dừng hoạt động",
            e.getLoaiKH().getTenLoaiKH(), e.getNgaySinh(), e.getNgayTao(), lblIcon
        };
        tbm.addRow(ob);
        }
    }
    //

    public void duyetLoaiKHVaoComboxBox() throws RemoteException {
        List<LoaiKhachHang> list = lkh_dao.getAll();
        for (LoaiKhachHang kh : list) {
            int flag = -1;
            String loai = kh.getTenLoaiKH();
            for (int i = 0; i < cbbkh.getItemCount(); i++) {
                if (loai.equals(cbbkh.getItemAt(i))) {
                    flag = 1;
                }
            }
            if (flag == -1) {
                cbbkh.addItem(loai);
            }
            flag = 1;
        }
    }
    //

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tbm;
    }

    //
    public void refreshTable() throws RemoteException {
        tbm.setRowCount(0);
        List<KhachHang> list = dao_kh.getAll();
        ImageIcon icon = new ImageIcon(getClass().getResource("/gui/icon/edit.png"));
        JLabel lblIcon = new JLabel(icon);
       
        for (KhachHang e : list) {
            System.out.println(e.getNgayTao());
           
            Object[] ob = { e.getMaKH(), e.getTenKH(), e.getSdt(), e.getEmail(), e.getDiemTL(), e.isTrangThai() ? "Hoạt động" : "Dừng hoạt động", e.getLoaiKH().getTenLoaiKH(), e.getNgaySinh(), e.getNgayTao(), lblIcon};
            tbm.addRow(ob);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbbkh = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        tsdt = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        them = new gui.component.Button();
        button2 = new gui.component.Button();
        button1 = new gui.component.Button();
        jLabel1 = new javax.swing.JLabel();
        cbbtt = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Mục Khách Hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 2, 24), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel4.setText("Lọc loại Khách Hàng :");

        cbbkh.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        cbbkh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbbkh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbkhActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setText("Nhập Số Điện Thoại :");

        tsdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tsdtKeyPressed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tuỳ Chọn Chức Năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 2, 16))); // NOI18N

        them.setBackground(new java.awt.Color(50, 50, 50));
        them.setForeground(new java.awt.Color(255, 255, 255));
        them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-add-32.png"))); // NOI18N
        them.setText("Thêm");
        them.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });

        button2.setBackground(new java.awt.Color(50, 50, 50));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-search-32.png"))); // NOI18N
        button2.setText("Tìm");
        button2.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        button1.setBackground(new java.awt.Color(50, 50, 50));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-refresh-32.png"))); // NOI18N
        button1.setText("Làm mới");
        button1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
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
                .addContainerGap()
                .addComponent(them, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(them, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel1.setText("Lọc trạng thái Khách Hàng");

        cbbtt.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        cbbtt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbttActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(781, 781, 781)
                        .addComponent(jSeparator1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbtt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(81, 81, 81)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cbbkh, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel6)
                                .addGap(26, 26, 26)
                                .addComponent(tsdt)))
                        .addGap(24, 24, 24))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbtt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.setRowHeight(30);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        tsdt.setText("");
        tsdt.requestFocus();
        tbm.setRowCount(0);
        try {
            duyetListVaoTable();
        } catch (RemoteException ex) {
            Logger.getLogger(QuanLyKhachHang_PN.class.getName()).log(Level.SEVERE, null, ex);
        }
        cbbkh.setSelectedItem("Tất cả");
        cbbtt.setSelectedItem("Tất cả");
    }//GEN-LAST:event_button1ActionPerformed

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        ThemKhachHang_Form form = new ThemKhachHang_Form(this);
        form.setVisible(true);
    }//GEN-LAST:event_themActionPerformed

    private void cbbkhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbkhActionPerformed
        // TODO add your handling code here:
        String tenLoaiKH = (String) cbbkh.getSelectedItem();
        tbm.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        // Kiểm tra nếu người dùng chọn "Tất cả"
        if ("Tất cả".equals(tenLoaiKH)) {
            try {
                duyetListVaoTable();
            } catch (RemoteException ex) {
                Logger.getLogger(QuanLyKhachHang_PN.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

            try {
                
                String maLoaiKHDuocChon = null;
                try {
                    for (LoaiKhachHang loaiKhachHang : lkh_dao.getAll()) {
                        if (loaiKhachHang.getTenLoaiKH().equals(tenLoaiKH)) {
                            maLoaiKHDuocChon = loaiKhachHang.getMaLoaiKH();
                            break;
                        }
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(QuanLyKhachHang_PN.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (maLoaiKHDuocChon == null) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy loại Khách hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                List<KhachHang> danhSachKHTheoLoai = dao_kh.getListKHTheoLoai(maLoaiKHDuocChon);
                
                for (KhachHang e : danhSachKHTheoLoai) {
                    LoaiKhachHang lkh = lkh_dao.findById(e.getLoaiKH().getMaLoaiKH());
                    Object[] ob = { e.getMaKH(), e.getTenKH(), e.getSdt(), e.getEmail(), e.getDiemTL(), e.isTrangThai() ? "Hoạt động" : "Dừng hoạt động", lkh.getTenLoaiKH(), e.getNgaySinh(), e.getNgayTao()};
                    tbm.addRow(ob);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(QuanLyKhachHang_PN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_cbbkhActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        String sdt = tsdt.getText();

        if (!sdt.isEmpty()) {
            try {
                tbm.setRowCount(0);
                List<KhachHang> list = dao_kh.timTheoSDT(sdt);
                if (list != null && !list.isEmpty()) { // Kiểm tra danh sách hợp lệ
                    
                    for (KhachHang kh : list) {
                        if (kh != null) {
                            LoaiKhachHang lkh = lkh_dao.findById(kh.getLoaiKH().getMaLoaiKH());
                            Object[] ob = {
                                
                                kh.getMaKH(),
                                kh.getTenKH(),
                                kh.getSdt(),
                                kh.getEmail(),
                                kh.getDiemTL(),
                                kh.isTrangThai() ? "Hoạt động" : "Dừng hoạt động",
                                lkh.getTenLoaiKH(),
                                kh.getNgaySinh(),
                                kh.getNgayTao()
                            };
                            tbm.addRow(ob);
                        }
                    }
                } else {
                    tbm.setRowCount(0);
                    duyetListVaoTable();
                    JOptionPane.showMessageDialog(this, "Không tìm thấy Khách Hàng", "Thông báo", JOptionPane.ERROR_MESSAGE);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(QuanLyKhachHang_PN.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            tbm.setRowCount(0);
            try {
                duyetListVaoTable();
            } catch (RemoteException ex) {
                Logger.getLogger(QuanLyKhachHang_PN.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại Khách Hàng cần tìm", "Thông báo", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_button2ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int clickedRow = table.getSelectedRow();
        String makh = String.valueOf(table.getValueAt(clickedRow, 0));
        ThongTinKhachHang_Form form = new ThongTinKhachHang_Form(makh, this);
        form.setVisible(true);
    }//GEN-LAST:event_tableMouseClicked

    private void tsdtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tsdtKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            button2ActionPerformed(null);  // Gọi hàm xử lý cho button2
        }
    }//GEN-LAST:event_tsdtKeyPressed

    private void cbbttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbttActionPerformed
        // TODO add your handling code here:
        // Lấy giá trị được chọn trong combobox
        String selectedItem = (String) cbbtt.getSelectedItem();
        tbm.setRowCount(0);  // Xóa tất cả các hàng trong bảng trước khi thêm dữ liệu mới

        if ("Tất cả".equals(selectedItem)) {
            try {
                duyetListVaoTable(); // Hiển thị tất cả khách hàng
            } catch (RemoteException ex) {
                Logger.getLogger(QuanLyKhachHang_PN.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Boolean trangThai=null ;
            if ("Hoạt động".equals(selectedItem)) {
                trangThai =true; // Trạng thái đã đặt
            } else if ("Dừng hoạt động".equals(selectedItem)) {
                trangThai = true; // Trạng thái đã nghỉ
            }

            if (trangThai != null) {
                try {
                    // Gọi phương thức lọc danh sách khách hàng theo trạng thái
                    List<KhachHang> filteredList = dao_kh.getListKHTheoTrangThai(trangThai);
                    
                    for (KhachHang e : filteredList) {
                        LoaiKhachHang lkh = lkh_dao.findById(e.getLoaiKH().getMaLoaiKH());
                        Object[] ob = {
                            
                            e.getMaKH(),
                            e.getTenKH(),
                            e.getSdt(),
                            e.getEmail(),
                            e.getDiemTL(),
                            e.isTrangThai() ? "Hoạt động" : "Dừng hoạt động",
                            lkh.getTenLoaiKH(),
                            e.getNgaySinh(),
                            e.getNgayTao()
                        };
                        tbm.addRow(ob);  // Thêm hàng vào bảng
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(QuanLyKhachHang_PN.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_cbbttActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button button1;
    private gui.component.Button button2;
    private javax.swing.JComboBox<String> cbbkh;
    private javax.swing.JComboBox<String> cbbtt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable table;
    private gui.component.Button them;
    private javax.swing.JTextField tsdt;
    // End of variables declaration//GEN-END:variables
}
