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
public class LoaiKhachHang {

    private String maLoaiKH;
    private String tenLoaiKH;
    private int giamGiaThanhVien;
    private int giamGiaSinhNhat;

    public LoaiKhachHang() {
    }

    public LoaiKhachHang(String maLoaiKH) {
        this.maLoaiKH = maLoaiKH;
    }

    public LoaiKhachHang(String maLoaiKH, String tenLoaiKH, int giamGiaThanhVien, int giamGiaSinhNhat) {
        this.maLoaiKH = maLoaiKH;
        this.tenLoaiKH = tenLoaiKH;
        this.giamGiaThanhVien = giamGiaThanhVien;
        this.giamGiaSinhNhat = giamGiaSinhNhat;
    }

    public LoaiKhachHang(String maLoaiKH, String tenLoaiKH) {
        this.maLoaiKH = maLoaiKH;
        this.tenLoaiKH = tenLoaiKH;
    }

    public String getMaLoaiKH() {
        return maLoaiKH;
    }

    public void setMaLoaiKH(String maLoaiKH) {
        this.maLoaiKH = maLoaiKH;
    }

    public String getTenLoaiKH() {
        return tenLoaiKH;
    }

    public void setTenLoaiKH(String tenLoaiKH) {
        this.tenLoaiKH = tenLoaiKH;
    }

    public int getGiamGiaThanhVien() {
        return giamGiaThanhVien;
    }

    public void setGiamGiaThanhVien(int giamGiaThanhVien) {
        this.giamGiaThanhVien = giamGiaThanhVien;
    }

    public int getGiamGiaSinhNhat() {
        return giamGiaSinhNhat;
    }

    public void setGiamGiamSinhNhat(int giamGiaSinhNhat) {
        this.giamGiaSinhNhat = giamGiaSinhNhat;
    }

    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.maLoaiKH);
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
        final LoaiKhachHang other = (LoaiKhachHang) obj;
        return Objects.equals(this.maLoaiKH, other.maLoaiKH);
    }

}
