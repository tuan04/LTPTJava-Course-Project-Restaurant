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
public class HoaDon {

    private String maHD;
    private LocalDate ngayLap;
    private double tongTien;
    private double tongTienTT;
    private boolean trangThai;
    private DichVu dichVu;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private DonDatBan donDatBan;
    private KhuyenMai khuyenMai;
    private Ban ban;
    private double giamGiaThanhVien;
    private double giamGiaSinhNhat;
    private LocalDateTime gioVao;
    private LocalDateTime gioRa;

    public HoaDon() {
        
    }
    
    public HoaDon(String maHD) {
        this.maHD = maHD;
    }
    
    public HoaDon(String maHD, LocalDate ngayLap, double tongTien, double tongTienTT, boolean trangThai, NhanVien nhanVien, DonDatBan donDatBan, Ban ban, LocalDateTime gioVao) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.tongTienTT = tongTienTT;
        this.trangThai = trangThai;
        this.nhanVien = nhanVien;
        this.donDatBan = donDatBan;
        this.ban = ban;
        this.gioVao = gioVao;
    }

    public HoaDon(String maHD, LocalDate ngayLap, double tongTien, double tongTienTT, boolean trangThai, DichVu dichVu, KhachHang khachHang, NhanVien nhanVien, DonDatBan donDatBan, KhuyenMai khuyenMai, Ban ban, double giamGiaThanhVien, double giamGiaSinhNhat, LocalDateTime gioVao, LocalDateTime gioRa) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.tongTienTT = tongTienTT;
        this.trangThai = trangThai;
        this.dichVu = dichVu;
        this.khachHang = khachHang;
        this.nhanVien = nhanVien;
        this.donDatBan = donDatBan;
        this.khuyenMai = khuyenMai;
        this.ban = ban;
        this.giamGiaThanhVien = giamGiaThanhVien;
        this.giamGiaSinhNhat = giamGiaSinhNhat;
        this.gioVao = gioVao;
        this.gioRa = gioRa;
    }

    

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTongTienTT() {
        return tongTienTT;
    }

    public void setTongTienTT(double tongTienTT) {
        this.tongTienTT = tongTienTT;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public DonDatBan getDonDatBan() {
        return donDatBan;
    }

    public void setDonDatBan(DonDatBan donDatBan) {
        this.donDatBan = donDatBan;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public LocalDateTime getGioVao() {
        return gioVao;
    }

    public void setGioVao(LocalDateTime gioVao) {
        this.gioVao = gioVao;
    }

    public LocalDateTime getGioRa() {
        return gioRa;
    }

    public void setGioRa(LocalDateTime gioRa) {
        this.gioRa = gioRa;
    }

    public double getGiamGiaThanhVien() {
        return giamGiaThanhVien;
    }

    public void setGiamGiaThanhVien(double giamGiaThanhVien) {
        this.giamGiaThanhVien = giamGiaThanhVien;
    }

    public double getGiamGiaSinhNhat() {
        return giamGiaSinhNhat;
    }

    public void setGiamGiaSinhNha(double giamGiaSinhNhat) {
        this.giamGiaSinhNhat = giamGiaSinhNhat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.maHD);
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
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHD, other.maHD);
    }

    
}
