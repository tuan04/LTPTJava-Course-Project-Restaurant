/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing.table.cell;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thanh Tuan
 */
public class DeleteLabel{
    public JLabel createDeleteLabel(JTable hdTb){
        ImageIcon icon = new ImageIcon(getClass().getResource("/gui/icon/delete.png"));
        JLabel lblIcon = new JLabel(icon);
        
        hdTb.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                int row = hdTb.rowAtPoint(evt.getPoint());
                int column = hdTb.columnAtPoint(evt.getPoint());

                // Kiểm tra xem chuột có đang ở trong cột chứa icon không
                if (column == 5 && row >= 0) {
                    // Thay đổi con trỏ chuột thành con trỏ tay
                    hdTb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    // Đặt lại con trỏ chuột
                    hdTb.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        return lblIcon;
    }
}
