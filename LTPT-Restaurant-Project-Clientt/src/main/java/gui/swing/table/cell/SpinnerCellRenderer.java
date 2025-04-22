/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing.table.cell;

import gui.component.SoLuongSpinner;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Thanh Tuan
 */
public class SpinnerCellRenderer extends DefaultTableCellRenderer{
    protected SoLuongSpinner spinner;
    
    public SpinnerCellRenderer() {
        spinner = new SoLuongSpinner();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        spinner.setValue(value);
        return spinner;
    }  
}

