/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author THANHTRI
 */
public class ChiTietHoaDon {
    private HoaDon hoaDon;
    private MonAn monAn;
    private double thanhTien;
    private int soLuong;
    private double giaSauGiam;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(HoaDon hoaDon, MonAn monAn) {
        this.hoaDon = hoaDon;
        this.monAn = monAn;
    }

    public ChiTietHoaDon(HoaDon hoaDon, MonAn monAn, double thanhTien, int soLuong, double giaSauGiam) {
        this.hoaDon = hoaDon;
        this.monAn = monAn;
        this.thanhTien = thanhTien;
        this.soLuong = soLuong;
        this.giaSauGiam = giaSauGiam;
    }

    

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public MonAn getMonAn() {
        return monAn;
    }

    public void setMonAn(MonAn monAn) {
        this.monAn = monAn;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public double getGiaSauGiam() {
        return giaSauGiam;
    }

    public void setGiaSauGiam(double giaSauGiam) {
        this.giaSauGiam = giaSauGiam;
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.hoaDon);
        hash = 43 * hash + Objects.hashCode(this.monAn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietHoaDon other = (ChiTietHoaDon) obj;
        if (!Objects.equals(this.hoaDon, other.hoaDon)) {
            return false;
        }
        return Objects.equals(this.monAn, other.monAn);
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" + "hoaDon=" + hoaDon + ", monAn=" + monAn + ", thanhTien=" + thanhTien + ", soLuong=" + soLuong + ", giaSauGiam=" + giaSauGiam + '}';
    }

    
    
    
}
