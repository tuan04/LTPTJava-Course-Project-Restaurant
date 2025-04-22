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
public class DichVu {

    private String maDV;
    private int VAT;
    private int PV;
    private int phongVIP;
    private boolean trangThai;

    public DichVu() {
    }
    
    public DichVu(String maDV) {
        this.maDV = maDV;
    }

    public DichVu(String maDV, int VAT, int PV, int phongVIP, boolean trangThai) {
        this.maDV = maDV;
        this.VAT = VAT;
        this.PV = PV;
        this.phongVIP = phongVIP;
        this.trangThai = trangThai;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public int getVAT() {
        return VAT;
    }

    public void setVAT(int VAT) {
        this.VAT = VAT;
    }

    public int getPV() {
        return PV;
    }

    public void setPV(int PV) {
        this.PV = PV;
    }

    public int getPhongVIP() {
        return phongVIP;
    }

    public void setPhongVIP(int phongVIP) {
        this.phongVIP = phongVIP;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.maDV);
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
        final DichVu other = (DichVu) obj;
        return Objects.equals(this.maDV, other.maDV);
    }

}
