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
public class MonAn {
    private String maMA;
    private String tenMA;
    private String hinhAnh;
    private double gia;
    private boolean trangThai;
    private LoaiMonAn loaiMonAn;
    private KhuyenMai khuyenMai;

    public MonAn() {
    }
    
    public MonAn(String maMA) {
        this.maMA = maMA;
    }

    public MonAn(String maMA, String tenMA, String hinhAnh, double gia, boolean trangThai, LoaiMonAn loaiMonAn, KhuyenMai khuyenMai) {
        this.maMA = maMA;
        this.tenMA = tenMA;
        this.hinhAnh = hinhAnh;
        this.gia = gia;
        this.trangThai = trangThai;
        this.loaiMonAn = loaiMonAn;
        this.khuyenMai = khuyenMai;
    }

    public String getMaMA() {
        return maMA;
    }

    public void setMaMA(String maMA) {
        this.maMA = maMA;
    }

    public String getTenMA() {
        return tenMA;
    }

    public void setTenMA(String tenMA) {
        this.tenMA = tenMA;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LoaiMonAn getLoaiMonAn() {
        return loaiMonAn;
    }

    public void setLoaiMonAn(LoaiMonAn loaiMonAn) {
        this.loaiMonAn = loaiMonAn;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.maMA);
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
        final MonAn other = (MonAn) obj;
        return Objects.equals(this.maMA, other.maMA);
    }

    
    
    
}
