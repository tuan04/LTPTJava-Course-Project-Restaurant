/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package print;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import print.model.ParameterReportDonTamTinh;
import print.model.ParameterReportHoaDon;

/**
 *
 * @author Thanh Tuan
 */
public class ReportManager {
    private static ReportManager instance;
    
    private JasperReport reportHoaDon;
    private JasperReport reportTamTinh;
    
    public static ReportManager getInstance(){
        if(instance == null){
            instance = new ReportManager();
        }
        return instance;
    }
    private ReportManager(){
        
    }
    public void complieReport() throws JRException{
        reportHoaDon = JasperCompileManager.compileReport(getClass().getResourceAsStream("/print/HoaDonThanhToan.jrxml"));
        reportTamTinh = JasperCompileManager.compileReport(getClass().getResourceAsStream("/print/HoaDonTamTinh.jrxml"));
    }
    
    public void printReportTamTinh(ParameterReportDonTamTinh data) throws JRException{
        Map para = new HashMap();
        para.put("table", data.getTable());
        para.put("thuNgan", data.getCashier());
        para.put("date", data.getDate());
        para.put("total", data.getTotal());
        para.put("cashier", data.getCashier());

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data.getFields());
        JasperPrint print = JasperFillManager.fillReport(reportTamTinh, para, dataSource);
        view(print);
    }
    
    public void printReportThanhToanNew(ParameterReportHoaDon data) throws JRException{
        Map para = new HashMap();
        para.put("orderID", data.getOrderID());
        para.put("table", data.getTable());
        para.put("cashier", data.getCashier());
        para.put("date", data.getDate());
        para.put("checkInTime", data.getCheckInTime());
        para.put("checkOutTime", data.getCheckOutTime());
        para.put("cust", data.getCust());
        para.put("total", data.getTotal());
        para.put("svc", data.getSvc());
        para.put("vat", data.getVat());
        para.put("totalPay", data.getTotalPay());
        para.put("totalDiscount", data.getTotalDiscount());
        para.put("discountName", data.getDiscountName());
        para.put("perDiscount", data.getPerDiscount());
        para.put("discount", data.getDiscount());
        para.put("giamGiaTVPer", data.getGiamGiaTVPer());
        para.put("giamGiaTV", data.getGiamGiaTV());
        para.put("giamGiaSNPer", data.getGiamGiaSNPer());
        para.put("giamGiaSN", data.getGiamGiaSN());
        para.put("tienCoc", data.getTienCoc());
        para.put("phongVip", data.getPhongVip());
        JasperPrint print = JasperFillManager.fillReport(reportHoaDon, para, new JRBeanCollectionDataSource(data.getFields()));
        view(print);
        
    }
    
    public void view (JasperPrint print) throws JRException{
        JasperViewer.viewReport(print, false);
    }
}
