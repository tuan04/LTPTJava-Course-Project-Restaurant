/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author THANHTRI
 */
public class DonDatBan {
    private String maDDB;
    private String hoTenKH;
    private String soDienThoai;
    private int soLuongKH;
    private double tienCoc;
    private int trangThai;
    private LocalDateTime gioHen;
    private double hoanCoc;
    private LocalDateTime gioHuy;
    private NhanVien nhanVien;
    private LocalDate ngayTao;
    private String ghiChu;
    private Ban ban;
    private NhanVien nhanVienHuy;

    public DonDatBan() {
    }
    
    public DonDatBan(String maDDB) {
        this.maDDB = maDDB;
    }
    
    public DonDatBan(String maDDB, String hoTenKH, String soDienThoai, int soLuongKH, double tienCoc, int trangThai, LocalDateTime gioHen, NhanVien nhanVien, LocalDate ngayTao, String ghiChu, Ban ban) {
        this.maDDB = maDDB;
        this.hoTenKH = hoTenKH;
        this.soDienThoai = soDienThoai;
        this.soLuongKH = soLuongKH;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
        this.gioHen = gioHen;
        this.nhanVien = nhanVien;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.ban = ban;
    }
    
    public DonDatBan(String maDDB, String hoTenKH, String soDienThoai, int soLuongKH, double tienCoc, int trangThai, LocalDateTime gioHen, double hoanCoc, LocalDateTime gioHuy, NhanVien nhanVien, LocalDate ngayTao, String ghiChu, Ban ban) {
        this.maDDB = maDDB;
        this.hoTenKH = hoTenKH;
        this.soDienThoai = soDienThoai;
        this.soLuongKH = soLuongKH;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
        this.gioHen = gioHen;
        this.hoanCoc = hoanCoc;
        this.gioHuy = gioHuy;
        this.nhanVien = nhanVien;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.ban = ban;
    }

    public DonDatBan(String maDDB, String hoTenKH, String soDienThoai, int soLuongKH, double tienCoc, int trangThai, LocalDateTime gioHen, double hoanCoc, LocalDateTime gioHuy, NhanVien nhanVien, LocalDate ngayTao, String ghiChu, Ban ban, NhanVien nhanVienHuy) {
        this.maDDB = maDDB;
        this.hoTenKH = hoTenKH;
        this.soDienThoai = soDienThoai;
        this.soLuongKH = soLuongKH;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
        this.gioHen = gioHen;
        this.hoanCoc = hoanCoc;
        this.gioHuy = gioHuy;
        this.nhanVien = nhanVien;
        this.ngayTao = ngayTao;
        this.ghiChu = ghiChu;
        this.ban = ban;
        this.nhanVienHuy = nhanVienHuy;
    }

    public DonDatBan(String maDDB, double tienCoc) {
        this.maDDB = maDDB;
        this.tienCoc = tienCoc;
    }

    public String getMaDDB() {
        return maDDB;
    }

    public void setMaDDB(String maDDB) {
        this.maDDB = maDDB;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getSoLuongKH() {
        return soLuongKH;
    }

    public void setSoLuongKH(int soLuongKH) {
        this.soLuongKH = soLuongKH;
    }

    public double getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(double tienCoc) {
        this.tienCoc = tienCoc;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getGioHen() {
        return gioHen;
    }

    public void setGioHen(LocalDateTime gioHen) {
        this.gioHen = gioHen;
    }

    public double getHoanCoc() {
        return hoanCoc;
    }

    public void setHoanCoc(double hoanCoc) {
        this.hoanCoc = hoanCoc;
    }

    public LocalDateTime getGioHuy() {
        return gioHuy;
    }

    public void setGioHuy(LocalDateTime gioHuy) {
        this.gioHuy = gioHuy;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public NhanVien getNhanVienHuy() {
        return nhanVienHuy;
    }

    public void setNhanVienHuy(NhanVien nhanVienHuy) {
        this.nhanVienHuy = nhanVienHuy;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.maDDB);
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
        final DonDatBan other = (DonDatBan) obj;
        return Objects.equals(this.maDDB, other.maDDB);
    }

    
    
    
}
