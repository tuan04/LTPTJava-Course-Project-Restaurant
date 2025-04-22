/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.form;

import dao.Ban_DAO;
import dao.DonDatBan_DAO;
import dao.KhachHang_DAO;
import dao.LoaiBan_DAO;
import model.Ban;
import model.LoaiBan;
import gui.component.ItemTable_LeTan;
import gui.component.ItemTable_ThuNganDS;
import gui.main.Admin_DashBoard;
import gui.main.ThuNgan_DashBoard;
import gui.swing.WrapLayout;
import gui.swing.table.ScrollBarCustomUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author Thanh Tuan
 */
public class DanhMucBanThuNgan_PN extends javax.swing.JPanel {

    private Ban_DAO ban_dao = new Ban_DAO();
    private LoaiBan_DAO loaiBan_dao = new LoaiBan_DAO();
    private Object dashBoard;
    private String maLoai = null;
    private static DonDatBan_DAO dao = new DonDatBan_DAO();
//
    /**
     * Creates new form DanhSachBanThuNgan_PN
     */

    public DanhMucBanThuNgan_PN(Object dashBoard) {
        initComponents();
        this.dashBoard = dashBoard;
        setWrapLayout();
        customItemPane();
        loadLoaiBan();
        DefaultSelectLoaiBan();
        dao.capNhatBanTruocGioKhachDen();
        dao.capNhatBanSauGioKhachDen();
    }

    private void customItemPane() {
        jScrollPane1.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
        jScrollPane1.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
        jScrollPane2.getVerticalScrollBar().setUI(new ScrollBarCustomUI());
        jScrollPane2.getHorizontalScrollBar().setUI(new ScrollBarCustomUI());
    }

    public void setWrapLayout() {
        loaiBanPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10));
        banPanel.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10));
    }

    /*
    code xử lý
     */
    public void loadLoaiBan() {
        ArrayList<LoaiBan> list = loaiBan_dao.getListLoaiBan();
        maLoai = list.get(0).getMaLB();
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

    public void addEventToLoaiBanButton(JButton button) {
        button.addActionListener(e -> {
            Component[] list = loaiBanPanel.getComponents();
            maLoai = button.getToolTipText();
            for (Component c : list) {
                JButton but = (JButton) c;
                if (but != button) {
                    but.setBackground(Color.WHITE);
                }
            }
            button.setBackground(Color.ORANGE);
            setSoLuongChoRadioBtn(button.getToolTipText());
            tatCaBtn.setSelected(true);
            loadBanTheoLoaiVaTrangThai(maLoai, 12);
        });
    }

    public void DefaultSelectLoaiBan() {
        Component[] list = loaiBanPanel.getComponents();
        JButton but = (JButton) list[0];
        but.setBackground(Color.ORANGE);
        tatCaBtn.setSelected(true);
        setSoLuongChoRadioBtn(but.getToolTipText());
        loadBanTheoLoaiVaTrangThai(but.getToolTipText(), 12);
    }

    public void setSoLuongChoRadioBtn(String maLoai) {
        tatCaBtn.setText("");
        conTrongBtn.setText("");
        dangPhucVuBtn.setText("");
        doiKhachBtn.setText("");
        ArrayList<Ban> listMA = ban_dao.getListBanTheoLoai(maLoai);
        int tatCa = 0;
        for (Ban b : listMA) {
            tatCa++;
        }
        int conTrong = ban_dao.getSoLuongBanTheoLBvTrangThai(maLoai, 0);
        int phucVu = ban_dao.getSoLuongBanTheoLBvTrangThai(maLoai, 1);
        int choKhach = ban_dao.getSoLuongBanTheoLBvTrangThai(maLoai, 2);
        tatCaBtn.setText("Tất cả" + " (" + tatCa + ")");
        conTrongBtn.setText("Còn trống" + " (" + conTrong + ")");
        dangPhucVuBtn.setText("Đang phục vụ" + " (" + phucVu + ")");
        doiKhachBtn.setText("Đợi khách" + " (" + choKhach + ")");
    }

    public void loadBanTheoLoaiVaTrangThai(String maLoai, int trangThai) {
        ArrayList<Ban> listBan = ban_dao.getListBanTheoLoai(maLoai);
        banPanel.removeAll(); // Xóa tất cả các thành phần
        banPanel.revalidate(); // Cập nhật lại bố cục
        banPanel.repaint(); // Vẽ lại giao diện
        for (Ban b : listBan) {
            if(dashBoard instanceof ThuNgan_DashBoard){
                if (trangThai == 12) {
                    ItemTable_ThuNganDS ban = new ItemTable_ThuNganDS(b,(ThuNgan_DashBoard) dashBoard);
                    banPanel.add(ban);
                } else if (trangThai == 0 && b.getTinhTrang() == 0) {
                    ItemTable_ThuNganDS ban = new ItemTable_ThuNganDS(b, (ThuNgan_DashBoard) dashBoard);
                    banPanel.add(ban);
                } else if (trangThai == 1 && b.getTinhTrang() == 1) {
                    ItemTable_ThuNganDS ban = new ItemTable_ThuNganDS(b, (ThuNgan_DashBoard) dashBoard);
                    banPanel.add(ban);
                } else if (trangThai == 2 && b.getTinhTrang() == 2) {
                    ItemTable_ThuNganDS ban = new ItemTable_ThuNganDS(b, (ThuNgan_DashBoard) dashBoard);
                    banPanel.add(ban);
                }
            }
            else{
                if (trangThai == 12) {
                    ItemTable_ThuNganDS ban = new ItemTable_ThuNganDS(b,(Admin_DashBoard) dashBoard);
                    banPanel.add(ban);
                } else if (trangThai == 0 && b.getTinhTrang() == 0) {
                    ItemTable_ThuNganDS ban = new ItemTable_ThuNganDS(b, (Admin_DashBoard) dashBoard);
                    banPanel.add(ban);
                } else if (trangThai == 1 && b.getTinhTrang() == 1) {
                    ItemTable_ThuNganDS ban = new ItemTable_ThuNganDS(b, (Admin_DashBoard) dashBoard);
                    banPanel.add(ban);
                } else if (trangThai == 2 && b.getTinhTrang() == 2) {
                    ItemTable_ThuNganDS ban = new ItemTable_ThuNganDS(b, (Admin_DashBoard) dashBoard);
                    banPanel.add(ban);
                }
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

        trangThaiBanBtnGrp = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        loaiBanPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        tatCaBtn = new javax.swing.JRadioButton();
        conTrongBtn = new javax.swing.JRadioButton();
        dangPhucVuBtn = new javax.swing.JRadioButton();
        doiKhachBtn = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        banPanel = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(650, 110));

        loaiBanPanel.setBackground(new java.awt.Color(255, 255, 255));
        loaiBanPanel.setPreferredSize(new java.awt.Dimension(616, 60));

        javax.swing.GroupLayout loaiBanPanelLayout = new javax.swing.GroupLayout(loaiBanPanel);
        loaiBanPanel.setLayout(loaiBanPanelLayout);
        loaiBanPanelLayout.setHorizontalGroup(
            loaiBanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );
        loaiBanPanelLayout.setVerticalGroup(
            loaiBanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(loaiBanPanel);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        trangThaiBanBtnGrp.add(tatCaBtn);
        tatCaBtn.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tatCaBtn.setText("Tất cả");
        tatCaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tatCaBtnActionPerformed(evt);
            }
        });

        trangThaiBanBtnGrp.add(conTrongBtn);
        conTrongBtn.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        conTrongBtn.setText("Còn trống    ");
        conTrongBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conTrongBtnActionPerformed(evt);
            }
        });

        trangThaiBanBtnGrp.add(dangPhucVuBtn);
        dangPhucVuBtn.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        dangPhucVuBtn.setText("Đang phục vụ");
        dangPhucVuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangPhucVuBtnActionPerformed(evt);
            }
        });

        trangThaiBanBtnGrp.add(doiKhachBtn);
        doiKhachBtn.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        doiKhachBtn.setText("Đợi khách");
        doiKhachBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doiKhachBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tatCaBtn)
                .addGap(26, 26, 26)
                .addComponent(conTrongBtn)
                .addGap(26, 26, 26)
                .addComponent(dangPhucVuBtn)
                .addGap(26, 26, 26)
                .addComponent(doiKhachBtn)
                .addContainerGap(203, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tatCaBtn)
                    .addComponent(conTrongBtn)
                    .addComponent(dangPhucVuBtn)
                    .addComponent(doiKhachBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(332, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane2.setPreferredSize(new java.awt.Dimension(988, 588));

        banPanel.setPreferredSize(new java.awt.Dimension(970, 650));

        javax.swing.GroupLayout banPanelLayout = new javax.swing.GroupLayout(banPanel);
        banPanel.setLayout(banPanelLayout);
        banPanelLayout.setHorizontalGroup(
            banPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 970, Short.MAX_VALUE)
        );
        banPanelLayout.setVerticalGroup(
            banPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(banPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tatCaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tatCaBtnActionPerformed
        // TODO add your handling code here:
        loadBanTheoLoaiVaTrangThai(maLoai, 12);
    }//GEN-LAST:event_tatCaBtnActionPerformed

    private void conTrongBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conTrongBtnActionPerformed
        // TODO add your handling code here:
        loadBanTheoLoaiVaTrangThai(maLoai, 0);
    }//GEN-LAST:event_conTrongBtnActionPerformed

    private void dangPhucVuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangPhucVuBtnActionPerformed
        // TODO add your handling code here:
        loadBanTheoLoaiVaTrangThai(maLoai, 1);
    }//GEN-LAST:event_dangPhucVuBtnActionPerformed

    private void doiKhachBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doiKhachBtnActionPerformed
        // TODO add your handling code here:
        loadBanTheoLoaiVaTrangThai(maLoai, 2);
    }//GEN-LAST:event_doiKhachBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel banPanel;
    private javax.swing.JRadioButton conTrongBtn;
    private javax.swing.JRadioButton dangPhucVuBtn;
    private javax.swing.JRadioButton doiKhachBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel loaiBanPanel;
    private javax.swing.JRadioButton tatCaBtn;
    private javax.swing.ButtonGroup trangThaiBanBtnGrp;
    // End of variables declaration//GEN-END:variables
}
