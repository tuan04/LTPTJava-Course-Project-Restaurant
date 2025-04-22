package gui.main;

import model.Ban;
import gui.component.Header;
import gui.component.Menu;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.form.CapNhatDonDatBan_PN;
import gui.form.CapNhatHoaDon_Admin_PN;
import gui.form.DanhMucBanLeTan_PN;
import gui.form.DanhMucBanThuNgan_PN;
import gui.form.DatBan_PN;
import gui.form.KetToanLeTan_PN;
import gui.form.KetToanThuNgan_PN;
import gui.form.Main_PN;
import gui.form.QuanLyKhachHang_PN;
import gui.form.QuanLyKhuyenMai_PN;
import gui.form.QuanLyMonAn_PN;
import gui.form.QuanLyNhanVien_PN;
import gui.form.TaoHoaDon_Admin_PN;
import gui.form.ThongKeDoanhThu_PN;
import gui.form.ThongKeDonDatBan_PN;
import gui.form.ThongKeMonAn_PN;
import gui.form.TimDonDatBan_PN;
import gui.form.TimHoaDon_PN;
import gui.form.TimKhachHang_PN;
import gui.form.TimKhuyenMai_PN;
import gui.form.TimMonAn_PN;
import gui.form.TimNhanVien_PN;
import gui.swing.menu.MenuItem;
import gui.swing.menu.PopupMenu;
import java.awt.Component;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import rmi.RMIClientManager;
import service.DonDatBanService;
import service.KhachHangService;

public class Admin_DashBoard extends javax.swing.JFrame {

    private MigLayout layout;
    private Header header;
    private JPanel main;
    private Menu menu;
    private static DonDatBanService dao ;
    private static KhachHangService kh_dao ;

    public Header getHeader() {
        return header;
    }

    public Admin_DashBoard() throws Exception {
        header = new Header();
        this.dao=RMIClientManager.getInstance().getDonDatBanService();
        this.kh_dao=RMIClientManager.getInstance().getKhachHangService();
        initComponents();
        init();
//        connect();

    }

    public Admin_DashBoard(Header header) throws Exception, Exception {
        this.header = header;
         this.dao=RMIClientManager.getInstance().getDonDatBanService();
        this.kh_dao=RMIClientManager.getInstance().getKhachHangService();
       
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
                PopupMenu popup = new PopupMenu(Admin_DashBoard.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Admin_DashBoard.this.getX() + 52;
                int y = Admin_DashBoard.this.getY() + com.getY() + 146;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItemAdmin();
        bg.add(menu, "w 244!, spany 2");    // Span Y 2cell
        bg.add(header, "h 56!, wrap");
        bg.add(main, "w 100%, h 100%");
    }

    public void showTaoHD(Ban ban) {
        bg.remove(main);
        main = new TaoHoaDon_Admin_PN(ban, this);
        bg.add(main, "w 100%, h 100%");
        bg.revalidate();
        bg.repaint();
    }

    public void showGoiMon(Ban ban) {
        bg.remove(main);
        main = new CapNhatHoaDon_Admin_PN(ban, this);
        bg.add(main, "w 100%, h 100%");
        bg.revalidate();
        bg.repaint();
    }

    public void showPanel(int menuIndex, int subMenuIndex) {
        bg.remove(main);
        // Determine the new panel based on menuIndex and subMenuIndex
        if (menuIndex == 0) {
            if (subMenuIndex == 0) {
                main = new DatBan_PN(this);
                header.setLbTab("Quản Lý Đặt Bàn / Đặt Bàn");
            } else if (subMenuIndex == 1) {
                main = new CapNhatDonDatBan_PN(this);
                header.setLbTab("Quản Lý Đặt Bàn / Cập Nhật Đơn Đặt Bàn Chờ (Chưa xảy ra)");
            } else if (subMenuIndex == 2) {
                main = new TimDonDatBan_PN();
                header.setLbTab("Quản Lý Đặt Bàn / Tìm Đơn Đặt Bàn");
            }
        } else if (menuIndex == 1) {
            if (subMenuIndex == 0) {
                main = new DanhMucBanThuNgan_PN(this);
                header.setLbTab("Quản Lý Hóa Đơn / Tạo Hóa Đơn");
            } else if (subMenuIndex == 1) {
                main = new TimHoaDon_PN();
                header.setLbTab("Quản Lý Hóa Đơn / Tìm");
            }
        } else if (menuIndex == 2) {
            if (subMenuIndex == 0) {
                main = new KetToanLeTan_PN(this);
                header.setLbTab("Kết Toán Trong Ngày Lễ Tân");
            } else if (subMenuIndex == 1) {
                main = new KetToanThuNgan_PN(this);
                header.setLbTab("Kết Toán Trong Ngày Thu Ngân");
            }
        } else if (menuIndex == 3) {
            if (subMenuIndex == -1) {
                main = new DanhMucBanLeTan_PN(this);
                header.setLbTab("Xem Danh Mục Bàn");
            }
        } else if (menuIndex == 4) {
            if (subMenuIndex == 0) {
                main = new QuanLyKhachHang_PN();
                header.setLbTab("Quản Lý Khách Hàng / Thêm - Cập Nhật");
            } else if (subMenuIndex == 1) {
                main = new TimKhachHang_PN();
                header.setLbTab("Quản Lý Khách Hàng / Tìm");
            }
        } else if (menuIndex == 5) {
            if (subMenuIndex == 0) {
                main = new QuanLyNhanVien_PN();
                header.setLbTab("Quản Lý Nhân Viên / Thêm - Cập Nhật");
            } else if (subMenuIndex == 1) {
                main = new TimNhanVien_PN();
                header.setLbTab("Quản Lý Nhân Viên / Tìm");
            }
        } else if (menuIndex == 6) {
            if (subMenuIndex == 0) {
                main = new QuanLyMonAn_PN();
                header.setLbTab("Quản Lý Món Ăn / Thêm - Cập Nhật");
            } else if (subMenuIndex == 1) {
                main = new TimMonAn_PN();
                header.setLbTab("Quản Lý Món Ăn / Tìm");
            }
        } else if (menuIndex == 7) {
            if (subMenuIndex == 0) {
                main = new QuanLyKhuyenMai_PN();
                header.setLbTab("Quản Lý Khuyến Mãi / Thêm - Cập Nhật");
            } else if (subMenuIndex == 1) {
                main = new TimKhuyenMai_PN();
                header.setLbTab("Quản Lý Khuyến Mãi / Tìm");
            }
        } else if (menuIndex == 8) {
            if (subMenuIndex == 0) {
                main = new ThongKeDoanhThu_PN();
                header.setLbTab("Thống Kê Doanh Thu");
            } else if (subMenuIndex == 1) {
                main = new ThongKeDonDatBan_PN();
                header.setLbTab("Thống Kê Đơn Đặt Bàn");
            } else if (subMenuIndex == 2) {
                main = new ThongKeMonAn_PN();
                header.setLbTab("Thống Kê Món Ăn");
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
            java.util.logging.Logger.getLogger(Admin_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_DashBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                    new Admin_DashBoard().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Admin_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
                ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2); // 2 luồng cho 2 nhiệm vụ

                // Lên lịch để thực hiện nhiệm vụ đầu tiên mỗi 10 phút
                scheduler.scheduleAtFixedRate(() -> {
                    try {
                        dao.capNhatBanTruocGioKhachDen();
                    } catch (RemoteException ex) {
                        Logger.getLogger(Admin_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }, 0, 10, TimeUnit.MINUTES);

                // Lên lịch để thực hiện nhiệm vụ thứ hai mỗi 10 phút
                scheduler.scheduleAtFixedRate(() -> {
                    try {
                        dao.capNhatBanSauGioKhachDen();
                    } catch (RemoteException ex) {
                        Logger.getLogger(Admin_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }, 0, 10, TimeUnit.MINUTES);
                try {
                    kh_dao.updateDiemTL();
                } catch (RemoteException ex) {
                    Logger.getLogger(Admin_DashBoard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
