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
public class ChiTietDatBan {

    private MonAn monAn;
    private DonDatBan donDatBan;
    private int soLuong;
    private double thanhTien;
    private double giaSauGiam;

    public ChiTietDatBan() {
    }

    public ChiTietDatBan(MonAn monAn, DonDatBan donDatBan, int soLuong, double thanhTien, double giaSauGiam) {
        this.monAn = monAn;
        this.donDatBan = donDatBan;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.giaSauGiam = giaSauGiam;
    }

    

    public MonAn getMonAn() {
        return monAn;
    }

    public void setMonAn(MonAn monAn) {
        this.monAn = monAn;
    }

    public DonDatBan getDonDatBan() {
        return donDatBan;
    }

    public void setDonDatBan(DonDatBan donDatBan) {
        this.donDatBan = donDatBan;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
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
        hash = 83 * hash + Objects.hashCode(this.monAn);
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
        final ChiTietDatBan other = (ChiTietDatBan) obj;
        return Objects.equals(this.monAn, other.monAn);
    }

}
