/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.component;

import model.Ban;
import model.LoaiBan;
import gui.main.Admin_DashBoard;
import gui.main.ThuNgan_DashBoard;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import rmi.RMIClientManager;
import service.LoaiBanService;

/**
 *
 * @author Thanh Tuan
 */
public class ItemTable_ThuNganDS extends javax.swing.JPanel {

    private Ban ban = null;
    private Object dashBoard;
    private static Map<String, ImageIcon> imageCache = new HashMap<>();
    private LoaiBanService lb_dao ;

    /**
     * Creates new form ItemTable1
     */
    public ItemTable_ThuNganDS(Ban ban, ThuNgan_DashBoard dashBoard) throws Exception {
       this.lb_dao=RMIClientManager.getInstance().getLoaiBanService();
        initComponents();
        this.dashBoard = dashBoard;
        this.ban = ban;
        loadBan();
    }

    public ItemTable_ThuNganDS(Ban ban, Admin_DashBoard dashBoard) throws Exception {
             this.lb_dao=RMIClientManager.getInstance().getLoaiBanService();
        initComponents();
        this.dashBoard = dashBoard;
        this.ban = ban;
        loadBan();
    }

    public void loadBan() throws RemoteException {
        SwingUtilities.invokeLater(() -> {
            imgLoad("/hinhAnh/table.png");
        });
        LoaiBan lb = lb_dao.findById(ban.getLoaiBan().getMaLoaiBan());
        if (ban.getTrangThai()
                == 1) {
            jPanel11.setBackground(Color.GREEN);
        }
        if (ban.getTrangThai() == 2) {
            jPanel11.setBackground(new Color(171, 219, 227));
        }
        tableName.setText("Bàn " + ban.getMaBan() + " / " + lb.getTenLoaiBan());
    }

    public void imgLoad(String path) {
        // nếu bộ đệm đã có hình rồi thì load luôn
        if (imageCache.containsKey(path)) {
            imgTable3.setIcon(imageCache.get(path));
        } // còn nếu chưa có thì dùng SwingWorker để load ảnh đồng thời lưu luôn ảnh đó vào cache :33
        else {
            new SwingWorker<ImageIcon, Void>() {
                @Override
                protected ImageIcon doInBackground() throws Exception {
                    InputStream input = getClass().getResourceAsStream(path);
                    BufferedImage bufImg = ImageIO.read(input);
                    Image scaledImg = bufImg.getScaledInstance(imgTable3.getWidth(), imgTable3.getHeight(), Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImg);
                }

                @Override
                protected void done() {
                    try {
                        ImageIcon icon = get();
                        imageCache.put(path, icon);
                        imgTable3.setIcon(icon);
                        imgTable3.revalidate();
                        imgTable3.repaint();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.execute();
        }
    }

    public void loadTaoHoaDon_PN() {
        if (dashBoard instanceof ThuNgan_DashBoard) {
            if (ban.getTrangThai() == 0) {
                ((ThuNgan_DashBoard) dashBoard).showTaoHD(ban);
            } else if (ban.getTrangThai() == 1) {
                ((ThuNgan_DashBoard) dashBoard).showGoiMon(ban);
            }
        } else {
            if (ban.getTrangThai() == 0) {
                ((Admin_DashBoard) dashBoard).showTaoHD(ban);
            } else if (ban.getTrangThai() == 1) {
                ((Admin_DashBoard) dashBoard).showGoiMon(ban);
            }
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

        jPanel11 = new javax.swing.JPanel();
        imgTable3 = new javax.swing.JLabel();
        tableName = new javax.swing.JLabel();

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        imgTable3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imgTable3.setToolTipText("img");
        imgTable3.setPreferredSize(new java.awt.Dimension(165, 140));
        imgTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imgTable3MouseClicked(evt);
            }
        });

        tableName.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        tableName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tableName.setText("jLabel6");
        tableName.setToolTipText("name");
        tableName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imgTable3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgTable3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tableName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        imgTable3.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imgTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imgTable3MouseClicked
        // TODO add your handling code here:
        loadTaoHoaDon_PN();
    }//GEN-LAST:event_imgTable3MouseClicked

    private void tableNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNameMouseClicked
        // TODO add your handling code here:
        loadTaoHoaDon_PN();
    }//GEN-LAST:event_tableNameMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel imgTable3;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JLabel tableName;
    // End of variables declaration//GEN-END:variables
}
