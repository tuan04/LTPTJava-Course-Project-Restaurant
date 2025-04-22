package gui.model;

public class ModelChart {

    private String label;            // Nhãn cho biểu đồ
    private double[] values;         // Giá trị gốc dưới dạng double
    private String[] formattedValues; // Giá trị đã định dạng dưới dạng String

    // Constructor mặc định
    public ModelChart() {
    }

    // Constructor với nhãn và giá trị
    public ModelChart(String label, double[] values) {
        this.label = label;
        this.values = values;
        this.formattedValues = formatValues(values); // Định dạng giá trị ngay khi khởi tạo
    }

    // Getter và setter cho label
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    // Getter và setter cho values
    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
        this.formattedValues = formatValues(values); // Cập nhật giá trị đã định dạng
    }

    // Getter cho formattedValues
    public String[] getFormattedValues() {
        return formattedValues;
    }
    
    public void setFormattedValues(String[] formattedValues) {
        this.formattedValues = formattedValues;
    }

    // Phương thức để tìm giá trị lớn nhất trong mảng values
    public double getMaxValues() {
        double max = 0;
        for (double v : values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }

    // Phương thức để định dạng giá trị
    private String[] formatValues(double[] values) {
        String[] formatted = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            formatted[i] = formatVND(values[i]); // Định dạng theo yêu cầu (VND)
        }
        return formatted;
    }

    // Phương thức để định dạng số thành VND
    private String formatVND(double value) {
        // Định dạng giá trị thành VND (có thể thay đổi theo yêu cầu của bạn)
        return String.format("%,.0f", value);
    }

}
