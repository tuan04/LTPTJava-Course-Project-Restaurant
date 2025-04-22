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
public class LoaiNhanVien {
    private String maLoaiNV;
    private String viTri;
 

    public LoaiNhanVien() {
    }
    
    public LoaiNhanVien(String maLoaiNV) {
        this.maLoaiNV = maLoaiNV;
    }

    public LoaiNhanVien(String maLoaiNV, String viTri) {
        this.maLoaiNV = maLoaiNV;
        this.viTri = viTri;
    }

    

    public String getMaLoaiNV() {
        return maLoaiNV;
    }

    public void setMaLoaiNV(String maLoaiNV) {
        this.maLoaiNV = maLoaiNV;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maLoaiNV);
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
        final LoaiNhanVien other = (LoaiNhanVien) obj;
        return Objects.equals(this.maLoaiNV, other.maLoaiNV);
    }

    @Override
    public String toString() {
        return "LoaiNhanVien{" + "maLoaiNV=" + maLoaiNV + ", viTri=" + viTri + '}';
    }

    
    
}
