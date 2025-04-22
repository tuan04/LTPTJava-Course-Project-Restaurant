/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package print.model;

import java.util.ArrayList;
/**
 *
 * @author Thanh Tuan
 */
public class ParameterReportHoaDon {
    private String orderID;
    private String table;
    private String checkInTime;
    private String checkOutTime;
    private String date;
    private String cust;
    private String cashier;
    private String total;
    private String totalDiscount;
    private String svc;
    private String vat;
    private String totalPay;
    private String giamGiaTVPer;
    private String giamGiaTV;
    private String giamGiaSNPer;
    private String giamGiaSN;
    private String discountName;
    private String perDiscount;
    private String discount;
    private String tienCoc;
    private String phongVip;
    private ArrayList<FieldReportHoaDon> fields;

    public String getOrderID() {
        return orderID;
    }

    public void setPhongVip(String phongVip){
        this.phongVip = phongVip;
    }
    
    public String getPhongVip(){
        return phongVip;
    }
    
    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCust() {
        return cust;
    }

    public void setCust(String cust) {
        this.cust = cust;
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

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getSvc() {
        return svc;
    }

    public void setSvc(String svc) {
        this.svc = svc;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(String totalPay) {
        this.totalPay = totalPay;
    }

    public String getGiamGiaTVPer() {
        return giamGiaTVPer;
    }

    public void setGiamGiaTVPer(String giamGiaTVPer) {
        this.giamGiaTVPer = giamGiaTVPer;
    }

    public String getGiamGiaTV() {
        return giamGiaTV;
    }

    public void setGiamGiaTV(String giamGiaTV) {
        this.giamGiaTV = giamGiaTV;
    }

    public String getGiamGiaSNPer() {
        return giamGiaSNPer;
    }

    public void setGiamGiaSNPer(String giamGiaSNPer) {
        this.giamGiaSNPer = giamGiaSNPer;
    }

    public String getGiamGiaSN() {
        return giamGiaSN;
    }

    public void setGiamGiaSN(String giamGiaSN) {
        this.giamGiaSN = giamGiaSN;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public String getPerDiscount() {
        return perDiscount;
    }

    public void setPerDiscount(String perDiscount) {
        this.perDiscount = perDiscount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(String tienCoc) {
        this.tienCoc = tienCoc;
    }

    public ArrayList<FieldReportHoaDon> getFields() {
        return fields;
    }

    public void setFields1(ArrayList<FieldReportHoaDon> fields) {
        this.fields = fields;
    }

    public ParameterReportHoaDon(String orderID, String table, String checkInTime, String checkOutTime, String date, String cust, String cashier, String total, String totalDiscount, String svc, String vat, String totalPay, String giamGiaTVPer, String giamGiaTV, String giamGiaSNPer, String giamGiaSN, String discountName, String perDiscount, String discount, String tienCoc, String phongVip, ArrayList<FieldReportHoaDon> fields) {
        this.orderID = orderID;
        this.table = table;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.date = date;
        this.cust = cust;
        this.cashier = cashier;
        this.total = total;
        this.totalDiscount = totalDiscount;
        this.svc = svc;
        this.vat = vat;
        this.totalPay = totalPay;
        this.giamGiaTVPer = giamGiaTVPer;
        this.giamGiaTV = giamGiaTV;
        this.giamGiaSNPer = giamGiaSNPer;
        this.giamGiaSN = giamGiaSN;
        this.discountName = discountName;
        this.perDiscount = perDiscount;
        this.discount = discount;
        this.tienCoc = tienCoc;
        this.phongVip = phongVip;
        this.fields = fields;
    }
}
