/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing.table.cell;

import gui.component.SoLuongSpinner;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatter;

/**
 *
 * @author Thanh Tuan
 */
public class SpinnerCellEditor extends DefaultCellEditor {
    private JSpinner input;
    private JTable table;
    private EventCellInputChange event;
    private int row;
    
    public SpinnerCellEditor(EventCellInputChange event) {
        super(new JCheckBox());
        this.event = event;
        input = new SoLuongSpinner();
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) input.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        input.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                inputChange();
                stopCellEditing();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        super.getTableCellEditorComponent(table, value, isSelected, row, column);
        this.table = table;
        this.row = row;
        int qty = Integer.parseInt(value.toString());
        input.setValue(qty);
        return input;
    }

    @Override
    public Object getCellEditorValue() {
        return (int) input.getValue();
    }
    
    public String currencyFormat(double price){
        Locale locale = new Locale("vi", "VN");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.format(price);
    }
    
    public double currencyFormatToDouble(String currency){
        try {
            Locale locale = new Locale("vi", "VN");
            NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        return formatter.parse(currency).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    private void inputChange(){
        int qty = Integer.parseInt(input.getValue().toString());
        DefaultTableModel df = (DefaultTableModel) table.getModel();
        double price = currencyFormatToDouble(table.getValueAt(row, 3).toString());
        df.setValueAt(currencyFormat(qty * price),row , 4);
        df.setValueAt(qty, row, 1);
//        System.out.println(df.getValueAt(row, 1));
        event.inputChanged();
    }
}