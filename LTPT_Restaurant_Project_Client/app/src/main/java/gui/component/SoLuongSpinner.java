package gui.component;

import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.text.DefaultFormatter;

/**
 *
 * @author Thanh Tuan
 */
public class SoLuongSpinner extends JSpinner {
//    protected DefaultTableModel model;
//    protected JTable table;
    public SoLuongSpinner(){
        setUI(new BasicSpinnerUI());
//        this.model = model;
//        this.table = table;
        SpinnerModel spm = new SpinnerNumberModel(1, 1, 100, 1);
        this.setModel(spm);
        JSpinner.NumberEditor editor = (JSpinner.NumberEditor) this.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) editor.getTextField().getFormatter();
        formatter.setCommitsOnValidEdit(true);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        
//        this.addChangeListener((ChangeEvent e) ->{
//            updateOrderTable();
//        });
    }
//    public String currencyFormat(double price){
//        Locale locale = new Locale("vi", "VN");
//        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
//        return formatter.format(price);
//    }
//    
//    public double currencyFormatToDouble(String currency){
//        try {
//            Locale locale = new Locale("vi", "VN");
//            NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
//        return formatter.parse(currency).doubleValue();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
//    public void updateOrderTable(){
//        int row = table.getEditingRow();
//        if(row != -1){
//            double gia = currencyFormatToDouble((String)model.getValueAt(row, 3));
//            double thanhTien = ((int) this.getValue()) * gia;
//            model.setValueAt(currencyFormat(thanhTien), row, 4);
//            model.setValueAt(this.getValue(), row, 1);
//        }
//    }
}
