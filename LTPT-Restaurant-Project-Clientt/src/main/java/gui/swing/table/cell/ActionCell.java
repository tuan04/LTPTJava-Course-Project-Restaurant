/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing.table.cell;

import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author THANHTRI
 */
public class ActionCell {

    public JLabel createActionLabel(JTable table, int tableColumn, String img) {
        ImageIcon icon = new ImageIcon(getClass().getResource(img));
        JLabel lblIcon = new JLabel(icon);

        table.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int column = table.columnAtPoint(evt.getPoint());

                // Kiểm tra xem chuột có đang ở trong cột chứa icon không
                if (column == tableColumn && row >= 0) {
                    // Thay đổi con trỏ chuột thành con trỏ tay
                    table.setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else {
                    // Đặt lại con trỏ chuột
                    table.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            }
        });
        return lblIcon;
    }

}
