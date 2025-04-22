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
public class LoaiMonAn {

    private String maLoaiMA;
    private String tenLoaiMA;

    public LoaiMonAn() {
    }

    public LoaiMonAn(String maLoaiMA) {
        this.maLoaiMA = maLoaiMA;
    }

    public LoaiMonAn(String maLoaiMA, String tenLoaiMA) {
        this.maLoaiMA = maLoaiMA;
        this.tenLoaiMA = tenLoaiMA;
    }

    public String getMaLoaiMA() {
        return maLoaiMA;
    }

    public void setMaLoaiMA(String maLoaiMA) {
        this.maLoaiMA = maLoaiMA;
    }

    public String getTenLoaiMA() {
        return tenLoaiMA;
    }

    public void setTenLoaiMA(String tenLoaiMA) {
        this.tenLoaiMA = tenLoaiMA;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.maLoaiMA);
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
        final LoaiMonAn other = (LoaiMonAn) obj;
        return Objects.equals(this.maLoaiMA, other.maLoaiMA);
    }

}
