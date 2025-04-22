/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package print.model;

/**
 *
 * @author Thanh Tuan
 */
public class FieldReportHoaDon {
    private String count;
    private String name;
    private String qty;
    private String price;
    private String discountPrice;
    private String total;

    public String getCount() {
        return count;
    }

    public void setCount(String stt) {
        this.count = stt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }
    public void getDiscountPrice(String discountPrice){
        this.discountPrice = discountPrice;
    }
    
    public FieldReportHoaDon(String count, String name, String qty, String price, String discountPrice,String total) {
        this.count = count;
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.discountPrice = discountPrice;
        this.total = total;
    }
        
}
