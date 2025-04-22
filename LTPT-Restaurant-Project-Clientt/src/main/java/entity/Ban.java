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
public class Ban {
    private String maBan;
    private int soBan;
    private int tinhTrang;
    private LoaiBan loaiBan;
    private int soGhe;

    public Ban() {
    }
    
    public Ban(String maBan) {
        this.maBan = maBan;
    }

    public Ban(String maBan, int soBan, int tinhTrang, LoaiBan loaiBan, int soGhe) {
        this.maBan = maBan;
        this.soBan = soBan;
        this.tinhTrang = tinhTrang;
        this.loaiBan = loaiBan;
        this.soGhe = soGhe;
    }

    

    public String getMaBan() {
        return maBan;
    }

    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    public int getSoBan() {
        return soBan;
    }

    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public LoaiBan getLoaiBan() {
        return loaiBan;
    }

    public void setLoaiBan(LoaiBan loaiBan) {
        this.loaiBan = loaiBan;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.maBan);
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
        final Ban other = (Ban) obj;
        return Objects.equals(this.maBan, other.maBan);
    }

    
    
}
