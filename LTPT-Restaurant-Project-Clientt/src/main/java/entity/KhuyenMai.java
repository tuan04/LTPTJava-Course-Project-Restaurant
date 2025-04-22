/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author tamthanh
 */
public class KhuyenMai {
    private String maKM;
    private String tenKM;   
    private int giamGia;
    private LocalDate ngayHH;
    private LocalDate ngayBD;
    private int soLuong;
    private String loaiKM;

    public KhuyenMai() {
    }
    
    public KhuyenMai(String maKM) {
        this.maKM = maKM;
    }

    public KhuyenMai(String maKM, String tenKM, int giamGia, LocalDate ngayHH, LocalDate ngayBD, int soLuong, String loaiKM) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.giamGia = giamGia;
        this.ngayHH = ngayHH;
        this.ngayBD = ngayBD;
        this.soLuong = soLuong;
        this.loaiKM = loaiKM;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public int getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(int giamGia) {
        this.giamGia = giamGia;
    }

    public LocalDate getNgayHH() {
        return ngayHH;
    }

    public void setNgayHH(LocalDate ngayHH) {
        this.ngayHH = ngayHH;
    }

    public LocalDate getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(LocalDate ngayBD) {
        this.ngayBD = ngayBD;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getLoaiKM() {
        return loaiKM;
    }

    public void setLoaiKM(String loaiKM) {
        this.loaiKM = loaiKM;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.maKM);
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
        final KhuyenMai other = (KhuyenMai) obj;
        return Objects.equals(this.maKM, other.maKM);
    }

    
}
