package gui.component;

import model.NhanVien;
import gui.form.ThongTinNhanVienChiTiet_Form;
import gui.main.Login;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import rmi.RMIClientManager;
import service.NhanVienService;

public class Header extends javax.swing.JPanel {

    private  NhanVienService nv_dao;
    
    public Header() throws Exception {
        this.nv_dao = RMIClientManager.getInstance().getNhanVienService();
        initComponents();
    }
    
    public void setTextUsername(String text) {
        this.lbUserName.setText(text);
    }

    public void setTextRole(String text) {
        this.lbRole.setText(text);
    }

    public String getTextUsername() {
        return this.lbUserName.getText();
    }

    public String getTextRole() {
        return this.lbRole.getText();
    }

    public void setLbTab(String text) {
        this.lbTab.setText(text);
    }

    public void setTextMaNV(String text) {
        this.lbMaNV.setText(text);
    }

    public String getTextMaNV() {
        return this.lbMaNV.getText();
    }

    //
    private void hienThiFormNhanVien(String maNV) throws RemoteException {
        if (maNV != null && !maNV.trim().isEmpty()) {
            // Lấy thông tin nhân viên từ database
            NhanVien nv = nv_dao.findById(maNV.trim());
            if (nv != null) {
                // Khởi tạo form thông tin nhân viên trực tiếp với mã nhân viên
                ThongTinNhanVienChiTiet_Form form = new ThongTinNhanVienChiTiet_Form(maNV, this);

                // Hiển thị form
                form.setVisible(true);
            } else {
                // Hiển thị thông báo nếu không tìm thấy nhân viên
                JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên!");
            }
        } else {
            // Hiển thị thông báo nếu mã nhân viên không hợp lệ
            JOptionPane.showMessageDialog(null, "Mã nhân viên không hợp lệ!");
        }
    }


@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUserName = new javax.swing.JLabel();
        lbRole = new javax.swing.JLabel();
        lbTab = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        button2 = new gui.swing.menu.Button();
        jLabel1 = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(245, 245, 245));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lbUserName.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbUserName.setText("Lê Phương");
        lbUserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbUserNameMouseClicked(evt);
            }
        });

        lbRole.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbRole.setText("Nhân Viên Lễ Tân");
        lbRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbRoleMouseClicked(evt);
            }
        });

        lbTab.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbTab.setText("Tab");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-logout-32.png"))); // NOI18N
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icon/icons8-menu-32.png"))); // NOI18N

        lbMaNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lbMaNV.setText("NVLT001");
        lbMaNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMaNVMouseClicked(evt);
            }
        });

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbUserName)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbRole)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbMaNV)))
                .addGap(12, 12, 12)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lbTab)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lbUserName)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lbRole, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lbMaNV))
                                                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked

    }//GEN-LAST:event_logoutMouseClicked

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        int ask = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất không?", "Logout", JOptionPane.YES_NO_OPTION);
        if (ask == JOptionPane.YES_OPTION) {
            try {
                new Login().setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(Header.class.getName()).log(Level.SEVERE, null, ex);
            }
            SwingUtilities.getWindowAncestor(this).dispose(); 
        }
    }//GEN-LAST:event_button2ActionPerformed

    private void lbMaNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMaNVMouseClicked
        // TODO add your handling code here:
        String maNV = lbMaNV.getText(); 
        try {
            hienThiFormNhanVien(maNV);
        } catch (RemoteException ex) {
            Logger.getLogger(Header.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_lbMaNVMouseClicked

    private void lbRoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbRoleMouseClicked
        // TODO add your handling code here:
        String maNV = lbMaNV.getText(); 
        try {
            hienThiFormNhanVien(maNV);
        } catch (RemoteException ex) {
            Logger.getLogger(Header.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lbRoleMouseClicked

    private void lbUserNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbUserNameMouseClicked
        // TODO add your handling code here:
        String maNV = lbMaNV.getText(); 
        try {
            hienThiFormNhanVien(maNV);
        } catch (RemoteException ex) {
            Logger.getLogger(Header.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lbUserNameMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.menu.Button button2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbRole;
    private javax.swing.JLabel lbTab;
    private javax.swing.JLabel lbUserName;
    // End of variables declaration//GEN-END:variables
}
