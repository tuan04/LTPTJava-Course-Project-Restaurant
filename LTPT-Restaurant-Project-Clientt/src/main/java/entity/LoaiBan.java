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
public class LoaiBan {

    private String maLB;
    private String tenLB;

    public LoaiBan() {
    }

    public LoaiBan(String maLB) {
        this.maLB = maLB;
    }

    public LoaiBan(String maLB, String tenLB) {
        this.maLB = maLB;
        this.tenLB = tenLB;
    }

    public String getMaLB() {
        return maLB;
    }

    public void setMaLB(String maLB) {
        this.maLB = maLB;
    }

    public String getTenLB() {
        return tenLB;
    }

    public void setTenLB(String tenLB) {
        this.tenLB = tenLB;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maLB);
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
        final LoaiBan other = (LoaiBan) obj;
        return Objects.equals(this.maLB, other.maLB);
    }

}
