package gui.main;

import gui.component.Header;
import gui.component.Menu;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.form.Main_PN;
import gui.swing.menu.MenuItem;
import gui.swing.menu.PopupMenu;
import java.awt.Component;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

import model.Ban;
import gui.form.CapNhatHoaDon_PN;
import gui.form.DanhMucBanThuNgan_PN;
import gui.form.KetToanThuNgan_PN;
import gui.form.QuanLyKhachHang_PN;
import gui.form.TaoHoaDon_PN;
import gui.form.TimHoaDon_PN;
import gui.form.TimKhachHang_PN;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.RMIClientManager;
import service.DonDatBanService;
import service.KhachHangService;

public class ThuNgan_DashBoard extends javax.swing.JFrame {

    private MigLayout layout;
    private Header header;
    private JPanel main;
    private Menu menu;
    private static DonDatBanService dao ;
    private static KhachHangService kh_dao ;

    public Header getHeader() {
        return header;
    }

    public ThuNgan_DashBoard() throws Exception {
        this.dao=RMIClientManager.getInstance().getDonDatBanService();
        this.kh_dao=RMIClientManager.getInstance().getKhachHangService();
           
        header = new Header();
        initComponents();
        init();
//        connect();

    }

    public ThuNgan_DashBoard(Header header) throws Exception {
        this.dao=RMIClientManager.getInstance().getDonDatBanService();
        this.kh_dao=RMIClientManager.getInstance().getKhachHangService();
           
        this.header = header;
        initComponents();
        init();
//        connect();
    }

//    private void connect() {
//        ConnectDB.getInstance().connect();
//    }

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);

        header.setLbTab("WELCOME");
        main = new Main_PN();
        menu = new Menu();
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                showPanel(menuIndex, subMenuIndex);
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(ThuNgan_DashBoard.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = ThuNgan_DashBoard.this.getX() + 52;
                int y = ThuNgan_DashBoard.this.getY() + com.getY() + 146;
                popup.setLocation(x, y);
                popup.setSize(150, popup.getHeight());
                popup.setVisible(true);
            }
        });
        menu.initMenuItemThuNgan();
        bg.add(menu, "w 244!, spany 2");    // Span Y 2cell
        bg.add(header, "h 56!, wrap");
        bg.add(main, "w 100%, h 100%");
    }

    public void showTaoHD(Ban ban) {
        bg.remove(main);
        main = new TaoHoaDon_PN(ban, this);
        bg.add(main, "w 100%, h 100%");
        bg.revalidate();
        bg.repaint();
    }

    public void showGoiMon(Ban ban) {
        bg.remove(main);
        main = new CapNhatHoaDon_PN(ban, this);
        bg.add(main, "w 100%, h 100%");
        bg.revalidate();
        bg.repaint();
    }

    public void showPanel(int menuIndex, int subMenuIndex) {
        bg.remove(main);

        if (menuIndex == 0) {
            if (subMenuIndex == 0) {
                main = new DanhMucBanThuNgan_PN(this);
                header.setLbTab("Quản Lý Hóa Đơn / Tạo Hóa Đơn");
            } else if (subMenuIndex == 1) {
                main = new TimHoaDon_PN();
                header.setLbTab("Quản Lý Hóa Đơn / Tìm");
            }
        } // Determine the new panel based on menuIndex and subMenuIndex
        else if (menuIndex == 1) {
            if (subMenuIndex == 0) {
                main = new QuanLyKhachHang_PN();
                header.setLbTab("Quản Lý Khách Hàng / Thêm - Cập Nhật");
            } else if (subMenuIndex == 1) {
                main = new TimKhachHang_PN();
                header.setLbTab("Quản Lý Khách Hàng / Tìm");
            }
        } else if (menuIndex == 2) {
            if (subMenuIndex == -1) {
                main = new KetToanThuNgan_PN(this);
                header.setLbTab("Kết Toán Trong Ngày Thu Ngân");
            }
        }

        // Add the new panel
        bg.add(main, "w 100%, h 100%");
        bg.revalidate();
        bg.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        bg.setBackground(new java.awt.Color(245, 245, 245));
        bg.setOpaque(true);
        bg.setPreferredSize(new java.awt.Dimension(1244, 768));
        bg.setRequestFocusEnabled(false);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1244, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThuNgan_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThuNgan_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThuNgan_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThuNgan_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ThuNgan_DashBoard().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(ThuNgan_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2); // 2 luồng cho 2 nhiệm vụ

       
                scheduler.scheduleAtFixedRate(() -> {
                    try {
                        dao.capNhatBanTruocGioKhachDen();
                    } catch (RemoteException ex) {
                        Logger.getLogger(ThuNgan_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }, 0, 10, TimeUnit.MINUTES);

        
                scheduler.scheduleAtFixedRate(() -> {
                    try {
                        dao.capNhatBanSauGioKhachDen();
                    } catch (RemoteException ex) {
                        Logger.getLogger(ThuNgan_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }, 0, 10, TimeUnit.MINUTES);
                try {
                    kh_dao.updateDiemTL();
                } catch (RemoteException ex) {
                    Logger.getLogger(ThuNgan_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
