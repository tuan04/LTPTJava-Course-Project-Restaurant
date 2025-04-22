/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author THANHTRI
 */
public class NhanVien {

    private String maNV;
    private String hoTenNV;
    private String CCCD;
    private String soDienThoai;
    private String matKhau;
    private boolean trangThai;
    private LoaiNhanVien loaiNhanVien;
    private LocalDate ngaySinh;
    private boolean gioiTinh;
    private String maXacThuc;
    private String email;

    public NhanVien() {
    }
    
    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    public NhanVien(String maNV, String hoTenNV, String CCCD, String soDienThoai, String matKhau, boolean trangThai, LoaiNhanVien loaiNhanVien, LocalDate ngaySinh, boolean gioiTinh, String email) {
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.CCCD = CCCD;
        this.soDienThoai = soDienThoai;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.loaiNhanVien = loaiNhanVien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
    }

    public NhanVien(String maNV, String hoTenNV, String CCCD, String soDienThoai, String matKhau, boolean trangThai, LoaiNhanVien loaiNhanVien, LocalDate ngaySinh, boolean gioiTinh, String maXacThuc, String email) {
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.CCCD = CCCD;
        this.soDienThoai = soDienThoai;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.loaiNhanVien = loaiNhanVien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.maXacThuc = maXacThuc;
        this.email = email;
    }
    
    
    public NhanVien(String maNV, String hoTenNV, String CCCD, String soDienThoai, boolean trangThai, LoaiNhanVien loaiNhanVien, LocalDate ngaySinh, boolean gioiTinh, String email) {
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.CCCD = CCCD;
        this.soDienThoai = soDienThoai;
        this.trangThai = trangThai;
        this.loaiNhanVien = loaiNhanVien;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.email = email;
    }
    

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LoaiNhanVien getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMaXacThuc() {
        return maXacThuc;
    }

    public void setMaXacThuc(String maXacThuc) {
        this.maXacThuc = maXacThuc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.maNV);
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
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNV, other.maNV);
    }

    
    
    
}
