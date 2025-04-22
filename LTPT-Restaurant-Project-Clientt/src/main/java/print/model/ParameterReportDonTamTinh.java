/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package print.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Thanh Tuan
 */
public class ParameterReportDonTamTinh {
    private String table;
    private String date;
    private String cashier;
    private String total;
    private ArrayList<FieldReportDonTamTinh> fields;

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public ArrayList<FieldReportDonTamTinh> getFields() {
        return fields;
    }

    public void setFields(ArrayList<FieldReportDonTamTinh> fields) {
        this.fields = fields;
    }

    public ParameterReportDonTamTinh(String table, String date, String cashier, String total, ArrayList<FieldReportDonTamTinh> fields) {
        this.table = table;
        this.date = date;
        this.cashier = cashier;
        this.total = total;
        this.fields = fields;
    }
}
