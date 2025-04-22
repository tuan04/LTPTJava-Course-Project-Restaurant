/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.component;

import dao.KhuyenMai_DAO;
import entity.KhuyenMai;
import entity.MonAn;
import gui.swing.table.cell.DeleteLabel;
import gui.swing.table.cell.TableActionCellRender;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Renderer;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Thanh Tuan
 */
public class ItemMonAn extends javax.swing.JPanel {
    private KhuyenMai_DAO km_dao = new KhuyenMai_DAO();
    private MonAn ma = null;
    private JTable orderTable= null;
    private JLabel tongTien = null;
    
    private static Map<String, ImageIcon> imageCache = new HashMap<>();
    /**
     * Creates new form ItemMonAn
     */
    public ItemMonAn(MonAn monAn, JTable orderTable, JLabel tongTien) {
        initComponents();
        this.ma = monAn; 
        this.orderTable = orderTable;
        this.tongTien = tongTien;
        foodLoad();
    }
    
    public void foodLoad(){
        SwingUtilities.invokeLater(() -> {imgLoad(ma.getHinhAnh());});
        foodName.setText(ma.getTenMA());
        foodImg.setToolTipText(ma.getMaMA());
        foodName.setToolTipText(ma.getMaMA());
    }
    
    public void imgLoad(String path){
        // nếu bộ đệm đã có hình rồi thì load luôn
        if(imageCache.containsKey(path)){
            foodImg.setIcon(imageCache.get(path));
        }
        // còn nếu chưa có thì dùng SwingWorker để load ảnh đồng thời lưu luôn ảnh đó vào cache :33
        else{
            new SwingWorker<ImageIcon, Void>() {
                @Override
                protected ImageIcon doInBackground() throws Exception {
                    InputStream input = getClass().getResourceAsStream(path);
                    BufferedImage bufImg = ImageIO.read(input);
                    Image scaledImg = bufImg.getScaledInstance(foodImg.getWidth(), foodImg.getHeight(), Image.SCALE_SMOOTH);
                    return new ImageIcon(scaledImg);
                }

                @Override
                protected void done() {
                    try {
                        ImageIcon icon = get();
                        imageCache.put(path, icon);
                        foodImg.setIcon(icon);
                        foodImg.revalidate();
                        foodImg.repaint();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.execute();
        }
    }
   
    public void addFoodTable(){
        DefaultTableModel df = (DefaultTableModel) orderTable.getModel();
        double giaGiam = tinhGiaGiam(ma.getKhuyenMai(), ma.getGia());
        double thanhTien = tinhThanhTien(giaGiam, 1);
  
        df.addRow(new Object[] {ma.getTenMA(), 1, currencyFormat(ma.getGia()), currencyFormat(giaGiam), currencyFormat(thanhTien), new DeleteLabel().createDeleteLabel(orderTable), ma.getMaMA()});
    }
    
    public void tinhTongTien(){
        double tongTien = 0;
        DefaultTableModel df = (DefaultTableModel) orderTable.getModel();
        for(int i = 0; i < df.getRowCount(); i++){
            tongTien += currencyFormatToDouble((String) df.getValueAt(i, 4)) ;
        }
        this.tongTien.setText(currencyFormat(tongTien));
    }
    
    public double tinhThanhTien(double price, int sl){
        return price * sl;
    }
    
    public double tinhGiaGiam(KhuyenMai km, double price){
        if(km == null){
            return price;
        }
        double soTienGiam = ma.getGia() - (km.getGiamGia()*(10/100.0));
        return soTienGiam;
    }
    
    public String currencyFormat(double price){
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(price);
    }
    
    public double currencyFormatToDouble(String currency){
        String str = currency.replaceAll("[^\\d]", "");
        return Double.parseDouble(str);
    }
    
    public boolean updateOrderTable(){
        boolean flag = false;
        // true là món đã có, false là món chưa có
        DefaultTableModel df = (DefaultTableModel) orderTable.getModel();
        for(int i = 0; i < df.getRowCount(); i++){
            if(ma.getMaMA() == df.getValueAt(i, 6)){
                int sl = (int)df.getValueAt(i, 1);
                double giaGiam = tinhGiaGiam(ma.getKhuyenMai(), ma.getGia());
                df.setValueAt(sl+1, i, 1);
                df.setValueAt(currencyFormat((sl+1)*giaGiam), i, 4);
                flag = true;
            }
        }
        return flag;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        foodImg = new javax.swing.JLabel();
        foodName = new javax.swing.JLabel();

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        foodImg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodImg.setToolTipText("img");
        foodImg.setPreferredSize(new java.awt.Dimension(165, 140));
        foodImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodImgMouseClicked(evt);
            }
        });

        foodName.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        foodName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodName.setText("jLabel6");
        foodName.setToolTipText("name");
        foodName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodNameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(foodImg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(foodName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(foodImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(foodName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void foodImgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodImgMouseClicked
        // TODO add your handling code here:
        if(updateOrderTable()==true){
            
        }
        else{addFoodTable();}
        tinhTongTien();
    }//GEN-LAST:event_foodImgMouseClicked

    private void foodNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodNameMouseClicked
        // TODO add your handling code here:
        if(updateOrderTable()==true){
            
        }
        else{addFoodTable();}
        tinhTongTien();
    }//GEN-LAST:event_foodNameMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel foodImg;
    private javax.swing.JLabel foodName;
    private javax.swing.JPanel jPanel8;
    // End of variables declaration//GEN-END:variables
}
