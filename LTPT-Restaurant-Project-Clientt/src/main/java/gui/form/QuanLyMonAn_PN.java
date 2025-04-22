/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;

import dao.MonAn_DAO;
import entity.LoaiMonAn;
import entity.MonAn;
import gui.swing.table.TableCustom;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author tamthanh
 */
public class QuanLyMonAn_PN extends javax.swing.JPanel {
    private MonAn_DAO dao_ma = new MonAn_DAO();
    private DefaultTableModel tbm;
    /**
     * Creates new form QuanLyMonAn_PN
     */
    public QuanLyMonAn_PN() {
        initComponents();
        customTable();
        cbbtt.addItem("Tất cả");
        cbbtt.addItem("Còn món");
        cbbtt.addItem("Hết món");
    }
    private void customTable() {
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        table.getTableHeader().setFont(new Font("Sanserif", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(50, 50, 50));
        table.repaint();
        tbm = new DefaultTableModel(new String[]{"Mã Món ăn", "Tên Món ăn", "Giá", "Trạng thái", "Loại Món", "Mã Khuyến mãi", "Tuỳ chọn"}, 0);
        table.setModel(tbm);
        // Thêm biểu tượng "Xóa" vào cột Xóa
            ImageIcon icon = new ImageIcon(getClass().getResource("/gui/icon/edit.png"));
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
                 @Override
                public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cell.setHorizontalAlignment(JLabel.CENTER);  // Căn giữa icon trong cột
                return cell;
                }
             });
            duyetListVaoTable();
            duyetMonVaoComboxBox();
    }
    //
    public void duyetListVaoTable() {

                    ArrayList<MonAn> list = dao_ma.getList();
                    ImageIcon icon = new ImageIcon(getClass().getResource("/gui/icon/edit.png"));
                    JLabel lblIcon = new JLabel(icon);
                    for (MonAn e : list) {
                    String maKM = e.getKhuyenMai() != null ? e.getKhuyenMai().getMaKM() : ""; 
                    Object[] ob = { 
                        e.getMaMA(), 
                        e.getTenMA(), 
                        e.getGia(), 
                        e.isTrangThai() ? "Còn món" : "Hết món", 
                        e.getLoaiMonAn().getTenLoaiMA(), 
                        maKM,
                        lblIcon
                    }; 
                    tbm.addRow(ob);
                }
        }
    //
    public void duyetMonVaoComboxBox() {
		ArrayList<LoaiMonAn> list = dao_ma.getListLoai();
		for(LoaiMonAn m : list) {
			int flag = -1;
			String loai = m.getTenLoaiMA();
			for(int i= 0; i < cbbmon.getItemCount(); i++) {
				if(loai.equals(cbbmon.getItemAt(i))) {
					flag = 1;
				}
			}
			if(flag == -1) {
				cbbmon.addItem(loai);
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
    public void refreshTable() {
    tbm.setRowCount(0); 
     ArrayList<MonAn> list = dao_ma.getList();
                    ImageIcon icon = new ImageIcon(getClass().getResource("/gui/icon/edit.png"));
                    JLabel lblIcon = new JLabel(icon);
                    for (MonAn e : list) {
                    String maKM = e.getKhuyenMai() != null ? e.getKhuyenMai().getMaKM() : ""; 
                    Object[] ob = { 
                        e.getMaMA(), 
                        e.getTenMA(), 
                        e.getGia(), 
                        e.isTrangThai() ? "Còn món" : "Hết món", 
                        e.getLoaiMonAn().getTenLoaiMA(), 
                        maKM,
                        lblIcon
                    }; 
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
        cbbmon = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        tten = new javax.swing.JTextField();
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
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Mục Món Ăn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 2, 24), new java.awt.Color(51, 51, 51))); // NOI18N

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel4.setText("Lọc loại Món Ăn :");

        cbbmon.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        cbbmon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbbmon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbmonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        jLabel6.setText("Nhập Tên Món Ăn :");

        tten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ttenKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ttenKeyReleased(evt);
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
        jLabel1.setText("Lọc trạng thái Món Ăn");

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
                                .addGap(116, 116, 116)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(cbbmon, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(tten)))
                        .addGap(24, 24, 24))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbmon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(12, Short.MAX_VALUE))
        );

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbmonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbmonActionPerformed
        // TODO add your handling code here:
        // Lấy loại món được chọn từ ComboBox
    String tenLoaiMonDuocChon = (String) cbbmon.getSelectedItem();
    tbm.setRowCount(0); // Xóa dữ liệu cũ trong bảng

    // Kiểm tra nếu người dùng chọn "Tất cả"
    if ("Tất cả".equals(tenLoaiMonDuocChon)) {
        duyetListVaoTable(); // Hiển thị tất cả món ăn
    } else {
        // Lấy mã loại món ăn tương ứng với tên loại món được chọn
        String maLoaiMonDuocChon = null;
        for (LoaiMonAn loaiMonAn : dao_ma.getListLoai()) {
            if (loaiMonAn.getTenLoaiMA().equals(tenLoaiMonDuocChon)) {
                maLoaiMonDuocChon = loaiMonAn.getMaLoaiMA();
                break;
            }
        }

        // Nếu không tìm thấy mã loại món, dừng xử lý
        if (maLoaiMonDuocChon == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy loại món ăn phù hợp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Lấy danh sách món ăn theo mã loại món
        ArrayList<MonAn> danhSachMonTheoLoai = dao_ma.getListMonTheoLoai(maLoaiMonDuocChon);

        // Lặp qua danh sách và thêm từng món ăn vào bảng
        for (MonAn monAn : danhSachMonTheoLoai) {
            // Kiểm tra mã khuyến mãi (nếu không có, gán là "Không có")
            String maKhuyenMai = (monAn.getKhuyenMai() != null) 
                ? monAn.getKhuyenMai().getMaKM() 
                : "";

            // Tạo dữ liệu hàng cho bảng
            Object[] rowData = {
                monAn.getMaMA(),
                monAn.getTenMA(),
                monAn.getGia(),
                monAn.isTrangThai() ? "Còn món" : "Hết món", // Hiển thị trạng thái rõ ràng
                monAn.getLoaiMonAn().getTenLoaiMA(),  // Hiển thị tên loại món
                maKhuyenMai // Hiển thị mã khuyến mãi hoặc "Không có"
            };

            // Thêm hàng vào model của bảng
            tbm.addRow(rowData);
        }
    }
    }//GEN-LAST:event_cbbmonActionPerformed

    private void ttenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttenKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            button2ActionPerformed(null);  // Gọi hàm xử lý cho button2
        }
    }//GEN-LAST:event_ttenKeyPressed

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        ThemMonAn_Form form = new ThemMonAn_Form(this);
            form.setVisible(true);
    }//GEN-LAST:event_themActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
        String ten = tten.getText();

        if (!ten.isEmpty()) {
        tbm.setRowCount(0);
        ArrayList<MonAn> list = dao_ma.TimTheoTenMon(ten);
        if (list != null && !list.isEmpty()) { // Kiểm tra danh sách hợp lệ
            for (MonAn e : list) {
                if (e != null) {
                    String maKM = e.getKhuyenMai() != null ? e.getKhuyenMai().getMaKM() : ""; 
                    Object[] ob = { 
                        e.getMaMA(), 
                        e.getTenMA(), 
                        e.getGia(), 
                        e.isTrangThai() ? "Còn món" : "Hết món", 
                        e.getLoaiMonAn().getTenLoaiMA(), 
                        maKM
                    }; 
                    tbm.addRow(ob);
                }
            }
        } else {
            tbm.setRowCount(0);
            duyetListVaoTable(); // Hàm duyệt danh sách mặc định
            JOptionPane.showMessageDialog(this, "Không tìm thấy Món Ăn", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
        } else {
            tbm.setRowCount(0);
            duyetListVaoTable();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên Món Ăn cần tìm", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_button2ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        tten.setText("");
        tten.requestFocus();
        tbm.setRowCount(0);
	duyetListVaoTable();
        cbbmon.setSelectedItem("Tất cả"); 
        cbbtt.setSelectedItem("Tất cả"); 
    }//GEN-LAST:event_button1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int clickedRow = table.getSelectedRow();
        String makh = String.valueOf(table.getValueAt(clickedRow, 0)) ;  
        ThongTinMonAn_Form form = new ThongTinMonAn_Form(makh, this);
        form.setVisible(true);
    }//GEN-LAST:event_tableMouseClicked

    private void ttenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttenKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ttenKeyReleased

    private void cbbttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbttActionPerformed
        // TODO add your handling code here:
         // Lấy giá trị được chọn trong combobox
    String selectedItem = (String) cbbtt.getSelectedItem();
    tbm.setRowCount(0);  // Xóa tất cả các hàng trong bảng trước khi thêm dữ liệu mới

    if ("Tất cả".equals(selectedItem)) {
        duyetListVaoTable(); // Hiển thị tất cả khách hàng
    } else {
        String trangThai = null;
        if ("Còn món".equals(selectedItem)) {
            trangThai = "1"; // Trạng thái đã đặt
        } else if ("Hết món".equals(selectedItem)) {
            trangThai = "0"; // Trạng thái đã nghỉ
        }
        
        if (trangThai != null) {
            // Gọi phương thức lọc danh sách khách hàng theo trạng thái
            ArrayList<MonAn> filteredList = dao_ma.getListMonTheoTrangThai(trangThai);
            for (MonAn e : filteredList) {
                String maKM = e.getKhuyenMai() != null ? e.getKhuyenMai().getMaKM() : ""; 
                    Object[] ob = { 
                        e.getMaMA(), 
                        e.getTenMA(), 
                        e.getGia(), 
                        e.isTrangThai() ? "Còn món" : "Hết món", 
                        e.getLoaiMonAn().getTenLoaiMA(), 
                        maKM
                    }; 
                tbm.addRow(ob);  // Thêm hàng vào bảng
            }
        }
    }
    }//GEN-LAST:event_cbbttActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Button button1;
    private gui.component.Button button2;
    private javax.swing.JComboBox<String> cbbmon;
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
    private javax.swing.JTextField tten;
    // End of variables declaration//GEN-END:variables
}
