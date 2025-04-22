package gui.component;

import gui.event.EventMenu;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.model.ModelMenu;
import gui.swing.menu.MenuAnimation;
import gui.swing.menu.MenuItem;
import gui.swing.menu.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

public class Menu extends javax.swing.JPanel {

    public boolean isShowMenu() {
        return showMenu;
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    private final MigLayout layout;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;

    public Menu() {
        initComponents();
        setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
        panel.setLayout(layout);
    }

    public void initMenuItemAdmin() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-order-32.png")), "Quản Lý Đặt Bàn", "Đặt Bàn", "Cập Nhật", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-bill-32.png")), "Quản Lý Hóa Đơn", "Gọi món", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-chart-32.png")), "Kết toán", "Lễ Tân", "Thu Ngân"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-restaurant-table-32.png")), "Xem danh mục bàn"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-customers-32.png")), "Quản Lý Khách Hàng", "Thêm - Cập Nhật", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-employees-32.png")), "Quản Lý Nhân Viên", "Thêm - Cập Nhật", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-meal-32.png")), "Quản Lý Món Ăn", "Thêm - Cập Nhật", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-voucher-32.png")), "Quản Lý Khuyến Mãi", "Thêm - Cập Nhật", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-chart-32.png")), "Thống Kê", "Doanh Thu", "Đơn Đặt Bàn", "Món Ăn"));

    }

    public void initMenuItemQuanLy() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-employees-32.png")), "Quản Lý Nhân Viên", "Thêm - Cập Nhật", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-meal-32.png")), "Quản Lý Món Ăn", "Thêm - Cập Nhật", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-voucher-32.png")), "Quản Lý Khuyến Mãi", "Thêm - Cập Nhật", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-chart-32.png")), "Thống Kê", "Doanh Thu", "Đơn Đặt Bàn", "Món Ăn"));
    }

    public void initMenuItemLeTan() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-order-32.png")), "Quản Lý Đặt Bàn", "Đặt Bàn", "Cập Nhật", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-restaurant-table-32.png")), "Xem danh mục bàn"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-chart-32.png")), "Kết toán"));
    }

    public void initMenuItemThuNgan() {
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-bill-32.png")), "Quản Lý Hóa Đơn", "Gọi món", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-customers-32.png")), "Quản Lý Khách Hàng", "Thêm - Cập Nhật", "Tìm"));
        addMenu(new ModelMenu(new ImageIcon(getClass().getResource("/gui/icon/icons8-chart-32.png")), "Kết toán"));
    }

    private void addMenu(ModelMenu menu) {
        panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (isShowMenu()) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    public void hideallMenu() {
        for (Component com : panel.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.isOpen()) {
                new MenuAnimation(layout, com, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        logo1 = new gui.component.Logo();

        setPreferredSize(new java.awt.Dimension(244, 768));

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setOpaque(false);

        panel.setBorder(null);
        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 668, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(logo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); // Ensure the component is properly cleared before custom painting
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#c31432"), 0, getHeight(), Color.decode("#240b36"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.Logo logo1;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
