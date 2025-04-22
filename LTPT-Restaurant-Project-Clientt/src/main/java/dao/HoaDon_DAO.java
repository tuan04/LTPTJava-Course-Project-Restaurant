/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.DichVu;
import entity.DonDatBan;
import entity.HoaDon;
import entity.KhachHang;
import entity.KhuyenMai;
import entity.NhanVien;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author THANHTRI
 */
public class HoaDon_DAO {

    private Connection con = null;

    public boolean capNhatTongTienHD(String maHD, double tongTien) {
        con = ConnectDB.getConnection();
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("update HoaDon set tongTien = ? where maHD = ?");
            ps.setDouble(1, tongTien);
            ps.setString(2, maHD);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public boolean updateNhanVienHoaDon(String maNV, String maHD) {
        con = ConnectDB.getConnection();
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("update HoaDon set maNV = ? where maHD = ?");
            ps.setString(1, maNV);
            ps.setString(2, maHD);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public HoaDon getHoaDonTheoMa(String mahd) {
        con = ConnectDB.getConnection();
        HoaDon hd = null;
        try {
            PreparedStatement ps = con.prepareStatement("select * from HoaDon where maHD = ?");
            ps.setString(1, mahd);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString(1);
                LocalDate ngayLap = rs.getDate(2).toLocalDate();
                double tongTien = rs.getDouble(3);
                double tongTienTT = rs.getDouble(4);
                boolean trangThai = rs.getBoolean(5);
                String maDV = rs.getString(6);
                String maKH = rs.getString(7);
                String maNV = rs.getString(8);
                String maDDB = rs.getString(9);
                String maKM = rs.getString(10);
                String maBan = rs.getString(11);
                double giamGiaThanhVien = rs.getDouble(12);
                double giamGiaSinhNhat = rs.getDouble(13);
                Timestamp gioVaoTimestamp = rs.getTimestamp(14);
                LocalDateTime gioVao = gioVaoTimestamp != null ? gioVaoTimestamp.toLocalDateTime() : null;
                Timestamp gioRaTimestamp = rs.getTimestamp(15);
                LocalDateTime gioRa = gioRaTimestamp != null ? gioRaTimestamp.toLocalDateTime() : null;
                hd = new HoaDon(maHD, ngayLap, tongTien, tongTienTT, trangThai, new DichVu(maDV), new KhachHang(maKH), new NhanVien(maNV), new DonDatBan(maDDB), new KhuyenMai(maKM), new Ban(maBan), giamGiaThanhVien, giamGiaSinhNhat, gioVao, gioRa);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hd;
    }

    public boolean capNhatHoaDon(String maHoaDon, double tongTienTT, String maKhachHang, String maKhuyenMai, double giamGia, String maBan) throws SQLException {
        Connection con = ConnectDB.getConnection();
        PreparedStatement psHoaDon = null;
        PreparedStatement psBan = null;

        try {
            String sqlUpdateHoaDon = "UPDATE HoaDon SET tongTienTT = ?, trangThai = 1, maKH = ?, maKM = ?, giamGiaThanhVien = ?, gioRa = GETDATE() WHERE maHD = ?";
            psHoaDon = con.prepareStatement(sqlUpdateHoaDon);
            psHoaDon.setDouble(1, tongTienTT);
            psHoaDon.setString(2, maKhachHang);
            psHoaDon.setString(3, maKhuyenMai);
            psHoaDon.setDouble(4, giamGia);
            psHoaDon.setString(5, maHoaDon);
            psHoaDon.executeUpdate();

            // Cập nhật trạng thái bàn
            String sqlUpdateBan = "UPDATE Ban SET tinhTrang = 'ConTrong' WHERE maBan = ?";
            psBan = con.prepareStatement(sqlUpdateBan);
            psBan.setString(1, maBan);
            psBan.executeUpdate();

            // Cam kết giao dịch
            con.commit();
            return true; // Cập nhật thành công

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback nếu có lỗi
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            e.printStackTrace(); // In ra ngoại lệ
            return false; // Cập nhật không thành công
        } finally {
            // Đóng tài nguyên
            try {
                if (psHoaDon != null) {
                    psHoaDon.close();
                }
                if (psBan != null) {
                    psBan.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }

    public HoaDon getHoaDonTheoBanHoatDong(Ban ban) {
        HoaDon hd = null;
        con = ConnectDB.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("select * from HoaDon where trangThai = ? and maBan = ?");
            ps.setBoolean(1, false);
            ps.setString(2, ban.getMaBan());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString(1);
                LocalDate ngayLap = rs.getDate(2).toLocalDate();
                double tongTien = rs.getDouble(3);
                double tongTienTT = rs.getDouble(4);
                boolean trangThai = rs.getBoolean(5);
                String maDV = rs.getString(6);
                String maKH = rs.getString(7);
                String maNV = rs.getString(8);
                String maDDB = rs.getString(9);
                String maKM = rs.getString(10);
                String maBan = rs.getString(11);
                double giamGiaThanhVien = rs.getDouble(12);
                double giamGiaSinhNhat = rs.getDouble(13);
                Timestamp gioVaoTimestamp = rs.getTimestamp(14);
                LocalDateTime gioVao = gioVaoTimestamp != null ? gioVaoTimestamp.toLocalDateTime() : null;
                Timestamp gioRaTimestamp = rs.getTimestamp(15);
                LocalDateTime gioRa = gioRaTimestamp != null ? gioRaTimestamp.toLocalDateTime() : null;
                hd = new HoaDon(maHD, ngayLap, tongTien, tongTienTT, trangThai, new DichVu(maDV), new KhachHang(maKH), new NhanVien(maNV), new DonDatBan(maDDB), new KhuyenMai(maKM), new Ban(maBan), giamGiaThanhVien, giamGiaSinhNhat, gioVao, gioRa);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hd;
    }

    public ArrayList<HoaDon> thongKeQuy(String quarter, String year, String loaiBan) {
        con = ConnectDB.getConnection();
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HoaDon WHERE CAST((MONTH(ngayLap) - 1) / 3 + 1 AS INT) = ? AND YEAR(ngayLap) = ? ";

            if (!loaiBan.equals("Tất cả")) {
                sql += "AND maBan LIKE ?";
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, quarter);
            ps.setString(2, year);
            if (!loaiBan.equals("Tất cả")) {
                ps.setString(3, loaiBan.equals("Tầng 1") ? "T1%" : loaiBan.equals("Tầng 2") ? "T2%" : "VP%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString("maHD");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                double tongTien = rs.getDouble("tongTien");
                double tongTienTT = rs.getDouble("tongTienTT");
                boolean trangThai = rs.getBoolean("trangThai");
                String maDV = rs.getString("maDV");
                String maKH = rs.getString("maKH");
                String maNV = rs.getString("maNV");
                String maDDB = rs.getString("maDDB");
                String maKM = rs.getString("maKM");
                String maBan = rs.getString("maBan");
                double giamGiaThanhVien = rs.getDouble("giamGiaThanhVien");
                double giamGiaSinhNhat = rs.getDouble("giamGiaSinhNhat");
                LocalDateTime gioVao = rs.getTimestamp("gioVao").toLocalDateTime();
                Timestamp timestamp = rs.getTimestamp("gioRa");
                LocalDateTime gioRa = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                list.add(new HoaDon(maHoaDon, ngayLap, tongTien, tongTienTT, trangThai, new DichVu(maDV), new KhachHang(maKH), new NhanVien(maNV), new DonDatBan(maDDB), new KhuyenMai(maKM), new Ban(maBan), giamGiaThanhVien, giamGiaSinhNhat, gioVao, gioRa));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<HoaDon> thongKeNam(String year, String loaiBan) {
        con = ConnectDB.getConnection();
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HoaDon WHERE YEAR(ngayLap) = ? ";

            if (!loaiBan.equals("Tất cả")) {
                sql += "AND maBan LIKE ?";
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, year);
            if (!loaiBan.equals("Tất cả")) {
                ps.setString(2, loaiBan.equals("Tầng 1") ? "T1%" : loaiBan.equals("Tầng 2") ? "T2%" : "VP%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString("maHD");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                double tongTien = rs.getDouble("tongTien");
                double tongTienTT = rs.getDouble("tongTienTT");
                boolean trangThai = rs.getBoolean("trangThai");
                String maDV = rs.getString("maDV");
                String maKH = rs.getString("maKH");
                String maNV = rs.getString("maNV");
                String maDDB = rs.getString("maDDB");
                String maKM = rs.getString("maKM");
                String maBan = rs.getString("maBan");
                double giamGiaThanhVien = rs.getDouble("giamGiaThanhVien");
                double giamGiaSinhNhat = rs.getDouble("giamGiaSinhNhat");
                LocalDateTime gioVao = rs.getTimestamp("gioVao").toLocalDateTime();
                Timestamp timestamp = rs.getTimestamp("gioRa");
                LocalDateTime gioRa = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                list.add(new HoaDon(maHoaDon, ngayLap, tongTien, tongTienTT, trangThai, new DichVu(maDV), new KhachHang(maKH), new NhanVien(maNV), new DonDatBan(maDDB), new KhuyenMai(maKM), new Ban(maBan), giamGiaThanhVien, giamGiaSinhNhat, gioVao, gioRa));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<HoaDon> thongKeThang(String month, String year, String loaiBan) {
        con = ConnectDB.getConnection();
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HoaDon WHERE MONTH(ngayLap) = ? AND YEAR(ngayLap) = ? ";

            if (!loaiBan.equals("Tất cả")) {
                sql += "AND maBan LIKE ?";
            }
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, month);
            ps.setString(2, year);
            if (!loaiBan.equals("Tất cả")) {
                ps.setString(3, loaiBan.equals("Tầng 1") ? "T1%" : loaiBan.equals("Tầng 2") ? "T2%" : "VP%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString("maHD");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                double tongTien = rs.getDouble("tongTien");
                double tongTienTT = rs.getDouble("tongTienTT");
                boolean trangThai = rs.getBoolean("trangThai");
                String maDV = rs.getString("maDV");
                String maKH = rs.getString("maKH");
                String maNV = rs.getString("maNV");
                String maDDB = rs.getString("maDDB");
                String maKM = rs.getString("maKM");
                String maBan = rs.getString("maBan");
                double giamGiaThanhVien = rs.getDouble("giamGiaThanhVien");
                double giamGiaSinhNhat = rs.getDouble("giamGiaSinhNhat");
                LocalDateTime gioVao = rs.getTimestamp("gioVao").toLocalDateTime();
                Timestamp timestamp = rs.getTimestamp("gioRa");
                LocalDateTime gioRa = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                list.add(new HoaDon(maHoaDon, ngayLap, tongTien, tongTienTT, trangThai, new DichVu(maDV), new KhachHang(maKH), new NhanVien(maNV), new DonDatBan(maDDB), new KhuyenMai(maKM), new Ban(maBan), giamGiaThanhVien, giamGiaSinhNhat, gioVao, gioRa));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<HoaDon> thongKeNgay(String day, String loaiBan) {
        con = ConnectDB.getConnection();
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM HoaDon WHERE FORMAT(ngayLap, 'dd/MM/yyyy') = ? ";

            if (!loaiBan.equals("Tất cả")) {
                sql += "AND maBan LIKE ?";
            }
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, day);
            if (!loaiBan.equals("Tất cả")) {
                ps.setString(2, loaiBan.equals("Tầng 1") ? "T1%" : loaiBan.equals("Tầng 2") ? "T2%" : "VP%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString("maHD");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                double tongTien = rs.getDouble("tongTien");
                double tongTienTT = rs.getDouble("tongTienTT");
                boolean trangThai = rs.getBoolean("trangThai");
                String maDV = rs.getString("maDV");
                String maKH = rs.getString("maKH");
                String maNV = rs.getString("maNV");
                String maDDB = rs.getString("maDDB");
                String maKM = rs.getString("maKM");
                String maBan = rs.getString("maBan");
                double giamGiaThanhVien = rs.getDouble("giamGiaThanhVien");
                double giamGiaSinhNhat = rs.getDouble("giamGiaSinhNhat");
                LocalDateTime gioVao = rs.getTimestamp("gioVao").toLocalDateTime();
                Timestamp timestamp = rs.getTimestamp("gioRa");
                LocalDateTime gioRa = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                list.add(new HoaDon(maHoaDon, ngayLap, tongTien, tongTienTT, trangThai, new DichVu(maDV), new KhachHang(maKH), new NhanVien(maNV), new DonDatBan(maDDB), new KhuyenMai(maKM), new Ban(maBan), giamGiaThanhVien, giamGiaSinhNhat, gioVao, gioRa));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<Object[]> thongKeNamMon(String year, String maLoaiMon) {
        con = ConnectDB.getConnection();
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT MA.maMA, MA.tenMA, MA.gia, SUM(CTHD.soLuong) AS soLuongBanRa, SUM(CTHD.thanhTien) AS doanhThu FROM HoaDon HD JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.maHD JOIN MonAn MA ON cthd.maMA = MA.maMA JOIN LoaiMonAn LMA ON MA.maLoaiMA = LMA.maLoaiMA WHERE YEAR(hd.ngayLap) = ? AND LMA.maLoaiMA = ? GROUP BY  MA.maMA, MA.tenMA, MA.gia");
            ps.setString(1, year);
            ps.setString(2, maLoaiMon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString("maMA");
                String tenMA = rs.getString("tenMA");
                double gia = rs.getDouble("gia");
                int soLuongBanRa = rs.getInt("soLuongBanRa");
                double doanhThu = rs.getDouble("doanhThu");
                list.add(new Object[]{maMA, tenMA, gia, soLuongBanRa, doanhThu});
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<Object[]> thongKeQuyMon(String quarter, String year, String maLoaiMon) {
        con = ConnectDB.getConnection();
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT MA.maMA, MA.tenMA, MA.gia, SUM(CTHD.soLuong) AS soLuongBanRa, SUM(CTHD.thanhTien) AS doanhThu FROM HoaDon HD JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.maHD JOIN MonAn MA ON cthd.maMA = MA.maMA JOIN LoaiMonAn LMA ON MA.maLoaiMA = LMA.maLoaiMA WHERE CAST((MONTH(ngayLap) - 1) / 3 + 1 AS INT) = ? AND YEAR(ngayLap) = ? AND LMA.maLoaiMA = ? GROUP BY  MA.maMA, MA.tenMA, MA.gia");
            ps.setString(1, quarter);
            ps.setString(2, year);
            ps.setString(3, maLoaiMon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString("maMA");
                String tenMA = rs.getString("tenMA");
                double gia = rs.getDouble("gia");
                int soLuongBanRa = rs.getInt("soLuongBanRa");
                double doanhThu = rs.getDouble("doanhThu");
                list.add(new Object[]{maMA, tenMA, gia, soLuongBanRa, doanhThu});
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<Object[]> thongKeThangMon(String month, String year, String maLoaiMon) {
        con = ConnectDB.getConnection();
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT MA.maMA, MA.tenMA, MA.gia, SUM(CTHD.soLuong) AS soLuongBanRa, SUM(CTHD.thanhTien) AS doanhThu FROM HoaDon HD JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.maHD JOIN MonAn MA ON cthd.maMA = MA.maMA JOIN LoaiMonAn LMA ON MA.maLoaiMA = LMA.maLoaiMA WHERE MONTH(hd.ngayLap) = ? AND YEAR(hd.ngayLap) = ? AND LMA.maLoaiMA = ? GROUP BY  MA.maMA, MA.tenMA, MA.gia");
            ps.setString(1, month);
            ps.setString(2, year);
            ps.setString(3, maLoaiMon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString("maMA");
                String tenMA = rs.getString("tenMA");
                double gia = rs.getDouble("gia");
                int soLuongBanRa = rs.getInt("soLuongBanRa");
                double doanhThu = rs.getDouble("doanhThu");
                list.add(new Object[]{maMA, tenMA, gia, soLuongBanRa, doanhThu});
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<Object[]> thongKeNgayMon(String day, String maLoaiMon) {
        con = ConnectDB.getConnection();
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT MA.maMA, MA.tenMA, MA.gia, SUM(CTHD.soLuong) AS soLuongBanRa, SUM(CTHD.thanhTien) AS doanhThu FROM HoaDon HD JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.maHD JOIN MonAn MA ON CTHD.maMA = MA.maMA JOIN LoaiMonAn LMA ON MA.maLoaiMA = LMA.maLoaiMA WHERE FORMAT(HD.ngayLap, 'dd/MM/yyyy') = ? AND LMA.maLoaiMA = ? GROUP BY  MA.maMA, MA.tenMA, MA.gia");
            ps.setString(1, day);
            ps.setString(2, maLoaiMon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString("maMA");
                String tenMA = rs.getString("tenMA");
                double gia = rs.getDouble("gia");
                int soLuongBanRa = rs.getInt("soLuongBanRa");
                double doanhThu = rs.getDouble("doanhThu");
                list.add(new Object[]{maMA, tenMA, gia, soLuongBanRa, doanhThu});
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<Integer> loadNam() {
        con = ConnectDB.getConnection();
        ArrayList<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT YEAR(ngayLap) AS Nam FROM HoaDon");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int year = rs.getInt("Nam");
                list.add(year);
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public String hoaDonMoiNhat() {
        con = ConnectDB.getConnection();
        String maHD = null;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select top 1 maHD from HoaDon order by maHD desc");
            while (rs.next()) {
                maHD = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maHD;
    }

    public HoaDon getHD(String maHD) {
        con = ConnectDB.getConnection();
        HoaDon x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM HoaDon WHERE maHD = ?");
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString("maHD");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                double tongTien = rs.getDouble("tongTien");
                double tongTienTT = rs.getDouble("tongTienTT");
                boolean trangThai = rs.getBoolean("trangThai");
                String maDV = rs.getString("maDV");
                String maKH = rs.getString("maKH");
                String maNV = rs.getString("maNV");
                String maDDB = rs.getString("maDDB");
                String maKM = rs.getString("maKM");
                String maBan = rs.getString("maBan");
                double giamGiaThanhVien = rs.getDouble("giamGiaThanhVien");
                double giamGiaSinhNhat = rs.getDouble("giamGiaSinhNhat");
                LocalDateTime gioVao = rs.getTimestamp("gioVao").toLocalDateTime();
                LocalDateTime gioRa = LocalDateTime.now();
                if (rs.getTimestamp("gioRa") != null) {
                    gioRa = rs.getTimestamp("gioRa").toLocalDateTime();
                }
                x = new HoaDon(maHoaDon, ngayLap, tongTien, tongTienTT, trangThai, new DichVu(maDV), new KhachHang(maKH), new NhanVien(maNV), new DonDatBan(maDDB), new KhuyenMai(maKM), new Ban(maBan), giamGiaThanhVien, giamGiaSinhNhat, gioVao, gioRa);
            }
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }

    public ArrayList<HoaDon> todayList(String maNhanVien, String today) {
        con = ConnectDB.getConnection();
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM HoaDon WHERE maNV = ? AND FORMAT(ngayLap, 'dd/MM/yyyy') = ? ");
            ps.setString(1, maNhanVien);
            ps.setString(2, today);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDate ngayLap = rs.getDate("ngayLap").toLocalDate();
                double tongTien = rs.getDouble("tongTien");
                double tongTienTT = rs.getDouble("tongTienTT");
                boolean trangThai = rs.getBoolean("trangThai");
                String maDV = rs.getString("maDV");
                String maKH = rs.getString("maKH");
                String maNV = rs.getString("maNV");
                String maDDB = rs.getString("maDDB");
                String maKM = rs.getString("maKM");
                String maBan = rs.getString("maBan");
                double giamGiaThanhVien = rs.getDouble("giamGiaThanhVien");
                double giamGiaSinhNhat = rs.getDouble("giamGiaSinhNhat");
                LocalDateTime gioVao = rs.getTimestamp("gioVao").toLocalDateTime();
                Timestamp timestamp = rs.getTimestamp("gioRa");
                LocalDateTime gioRa = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                list.add(new HoaDon(maHD, ngayLap, tongTien, tongTienTT, trangThai, new DichVu(maDV), new KhachHang(maKH), new NhanVien(maNV), new DonDatBan(maDDB), new KhuyenMai(maKM), new Ban(maBan), giamGiaThanhVien, giamGiaSinhNhat, gioVao, gioRa));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public boolean createOrder(double tongTien, String maDichVu, String maNhanVien, String maBan) {
        int n = 0;
        con = ConnectDB.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("insert into HoaDon (tongTien, tongTienTT, trangThai, maDV, maKH, maNV, maDDB, maKM, maBan)"
                    + " values (?,?,?,?,?,?,?,?,?)");
            ps.setDouble(1, tongTien);
            ps.setDouble(2, 0);
            ps.setBoolean(3, false);
            ps.setString(4, maDichVu);
            ps.setString(5, null);
            ps.setString(6, maNhanVien);
            ps.setString(7, null);
            ps.setString(8, null);
            ps.setString(9, maBan);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xong
    public HoaDon getHoaDon(String soBan) throws SQLException {
        con = ConnectDB.getConnection();
        String sql = "SELECT HD.maHD,HD.ngayLap,HD.tongTien,HD.tongTienTT,HD.trangThai,HD.maNV,HD.maDDB,HD.maBan,HD.gioVao\n"
                + "FROM Ban B JOIN HoaDon HD ON B.maBan = HD.maBan\n"
                + "WHERE HD.trangThai = 0 AND B.soBan = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, soBan);
        ResultSet rs = ps.executeQuery();
        HoaDon hoaDon = null;
        while (rs.next()) {
            String maHD = rs.getString(1);
            Date ngayLap = rs.getDate(2);
            double tongTien = rs.getDouble(3);
            double tongTienTT = rs.getDouble(4);
            boolean trangThai = rs.getInt(5) == 1 ? true : false;
            String maNhanVien = rs.getString(6);
            String maDonDatBan = rs.getString(7);
            String maBan = rs.getString(8);
            LocalDateTime gioVao = rs.getTimestamp(9).toLocalDateTime();
            NhanVien nhanVien = new NhanVien(maNhanVien);
            DonDatBan donDatBan = new DonDatBan(maDonDatBan);
            Ban ban = new Ban(maBan);

            hoaDon = new HoaDon(maHD, ngayLap.toLocalDate(), tongTien, tongTienTT, trangThai, nhanVien, donDatBan, ban, gioVao);

        }

        return hoaDon;
    }

    // Xong
    public ArrayList<Object[]> getChiTietHoaDon(String maBan) throws SQLException {
        con = ConnectDB.getConnection();
        ArrayList<Object[]> list = new ArrayList<>();
        String sql = "SELECT tenMA,soLuong,gia,giaSauGiam\n"
                + "FROM Ban B JOIN HoaDon HD ON B.maBan = HD.maBan\n"
                + "JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.maHD\n"
                + "JOIN MonAn MA ON CTHD.maMA = MA.maMA\n"
                + "WHERE HD.trangThai = 0 AND B.soBan = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maBan);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String tenMonAn = rs.getString(1);
            int soLuong = rs.getInt(2);
            double gia = rs.getDouble(3);
            double giaSauGiam = rs.getDouble(4);
            Object[] obj = {tenMonAn, soLuong, gia, giaSauGiam};
            list.add(obj);
        }
        return list;
    }

    public boolean taoHoaDon(HoaDon hoaDon) throws SQLException {
        con = ConnectDB.getConnection();
        int n = 0;
        String sql = "INSERT INTO Hoa_Don(tongTien,tongTienTT,trangThai,maDichVu,maNhanVien,maDonDatBan,giamGiaThanhVien,giamGiaNgaySinh)\n"
                + "VALUES (0,0,0,?,?,null,0,0)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, hoaDon.getDichVu().getMaDV());
        ps.setString(2, hoaDon.getNhanVien().getMaNV());

        n = ps.executeUpdate();
        if (n == 0) {
            return false;
        }

        return true;
    }

    ;

    // Xong
    public Object[] getThongTinHoaDon(String maHoaDon) throws SQLException {
        Object[] obj = new Object[8];
        con = ConnectDB.getConnection();
        String sql = "SELECT B.maLB,NV.hoTenNV,B.soBan,HD.tongTien,COALESCE(DDB.tienCoc,0),HD.tongTien*DV.VAT/100.0,HD.tongTien*DV.DV/100.0,FORMAT(gioVao, 'dd/MM/yyyy HH:mm')\n"
                + "FROM HoaDon HD LEFT JOIN ChiTietHoaDon CTHD\n"
                + "ON HD.maHD = CTHD.maHD\n"
                + "JOIN NhanVien NV ON NV.maNV = HD.maNV\n"
                + "LEFT JOIN DonDatBan DDB ON HD.maDDB = DDB.maDDB\n"
                + "JOIN DichVu DV ON HD.maDV = DV.maDV\n"
                + "JOIN Ban B ON HD.maBan = B.maBan\n"
                + "WHERE HD.maHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHoaDon);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            obj[0] = rs.getString(1);
            if (obj[0].toString().equals("LB001")) {
                obj[0] = "Tầng 1";
            } else if (obj[0].toString().equals("LB002")) {
                obj[0] = "Tầng 2";
            } else if (obj[0].toString().equals("LB003")) {
                obj[0] = "VIP";
            }
            obj[1] = rs.getString(2);
            obj[2] = rs.getString(3);
            obj[3] = rs.getDouble(4);
            obj[4] = rs.getDouble(5);
            obj[5] = rs.getDouble(6);
            obj[6] = rs.getDouble(7);
            obj[7] = rs.getString(8);
        }

        return obj;
    }

    // Xong
    public ArrayList<Object[]> getChiTietHoaDon_1(String ma) throws SQLException {
        con = ConnectDB.getConnection();
        ArrayList<Object[]> list = new ArrayList<>();
        String sql = "SELECT MA.tenMA,CTHD.soLuong,MA.gia,CTHD.giaSauGiam,CTHD.thanhTien\n"
                + "FROM HoaDon HD JOIN ChiTietHoaDon CTHD \n"
                + "ON HD.maHD = CTHD.maHD\n"
                + "JOIN MonAn MA ON CTHD.maMA = MA.maMA\n"
                + "WHERE HD.maHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        int i = 1;
        while (rs.next()) {
            String tenMonAn = rs.getString(1);
            int soLuong = rs.getInt(2);
            double gia = rs.getDouble(3);
            double giaSauGiam = rs.getDouble(4);
            double thanhTien = rs.getDouble(5);
            Object[] obj = {i, tenMonAn, soLuong, gia, giaSauGiam, thanhTien};
            list.add(obj);
            i++;
        }
        return list;
    }

    // Xong
    public int checkBanVip(String maHoaDon) throws SQLException {
        con = ConnectDB.getConnection();
        int n = 0;
        String sql = "SELECT DV.phongVIP\n"
                + "FROM HoaDon HD JOIN Ban B ON HD.MaBan = B.MaBan\n"
                + "JOIN DichVu DV ON HD.maDV = DV.maDV\n"
                + "WHERE B.maLB = 'LB003' AND HD.maHD = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHoaDon);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String phongVIP = rs.getString(1);
            if (phongVIP.trim().length() == 0) {
                n = 0;
            } else {
                n = Integer.parseInt(phongVIP);
            }
        }

        return n;
    }

    ;

    //Xong
    public double checkNS(String maHoaDon, String sdt) throws SQLException {
        con = ConnectDB.getConnection();
        double n = 0;
        String sql = "SELECT HD.tongTien*LKH.giamGiamSinhNhat/100.0\n"
                + "FROM HoaDon HD,KhachHang KH\n"
                + "JOIN LoaiKhachHang LKH ON LKH.maLoaiKH = KH.maLoaiKH\n"
                + "WHERE HD.maHD = ? AND KH.soDienThoai = ? AND KH.ngaySinh = GETDATE()";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHoaDon);
        ps.setString(2, sdt);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String giamGiaNS = rs.getString(1);
            if (giamGiaNS.trim().length() == 0) {
                n = 0;
            } else {
                n = Double.parseDouble(giamGiaNS);
            }
        }

        return n;
    }

    ;

    // Xong
    public Object[] getThongTinKH(String maHD, String std) throws SQLException {
        Object[] obj = new Object[3];
        con = ConnectDB.getConnection();

        String sql = "SELECT KH.tenKH,KH.maLoaiKH,HD.tongTien*LKH.giamGiaThanhVien/100.0\n"
                + "FROM HoaDon HD , KhachHang KH\n"
                + "JOIN LoaiKhachHang LKH ON LKH.maLoaiKH = KH.maLoaiKH\n"
                + "WHERE HD.maHD = ? AND KH.soDienThoai = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        ps.setString(2, std);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            obj[0] = rs.getString(1);
            obj[1] = rs.getString(2);
            if (obj[1].toString().equals("LKH1")) {
                obj[1] = "Number";
            } else if (obj[1].toString().equals("LKH2")) {
                obj[1] = "Goal";
            } else if (obj[1].toString().equals("LKH3")) {
                obj[1] = "Palatinum";
            }
            obj[2] = rs.getDouble(3);
        }
        return obj;
    }

    // Xong
    public Object[] getKM(String maHD, String maKM) throws SQLException {
        con = ConnectDB.getConnection();
        Object[] obj = new Object[2];
        String sql = "SELECT KM.giamGia,HD.tongTien*KM.giamGia/100.0\n"
                + "FROM HoaDon HD,KhuyenMai KM\n"
                + "WHERE HD.maHD = ? AND KM.maKM = ?\n"
                + "AND KM.loaiKM = 'HoaDon' AND KM.soLuong >= 1 AND KM.ngayHH >= GETDATE()";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        ps.setString(2, maKM);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            obj[0] = "Giảm giá " + rs.getDouble(1) + "%";;
            obj[1] = rs.getDouble(2);
        }

        return obj;
    }

    // Xong
    public boolean capNhatHoaDon(String maHD, String maKM, String maKH, Double tienTT, Double giamGiaTV, Double giamGiaNS) {
        Connection con = ConnectDB.getConnection();
        int n = 0;
        String sql = "UPDATE HoaDon\n"
                + "SET maKM = ? , maKH = ? , giamGiaThanhVien = ?,\n"
                + "giamGiaSinhNhat = ?, tongTienTT = ?, trangThai = ?, gioRa = GETDATE()\n"
                + "WHERE maHD = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKM);
            ps.setString(2, maKH);
            ps.setDouble(3, giamGiaTV);
            ps.setDouble(4, giamGiaNS);
            ps.setDouble(5, tienTT);
            ps.setInt(6, 1);
            ps.setString(7, maHD);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    public Object timKiemHoaDonTheoMa(String maHoaDon, String ngayDB, String ngayKT) {
        Object ob = new Object[]{};
        con = ConnectDB.getConnection();
        ResultSet rs = null;
        try {
            if (ngayDB.trim().length() > 0 & ngayKT.trim().length() > 0) {
                String sql = "SELECT HD.maHD, ngayLap, tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban ON Ban.maBan = HD.maBan WHERE maHD = ? AND ngayLap BETWEEN ? AND ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, maHoaDon);
                ps.setString(2, ngayDB);
                ps.setString(3, ngayKT);
                rs = ps.executeQuery();
            } else {
                String sql = "SELECT HD.maHD, ngayLap, tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai FROM HoaDon HD  LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban ON Ban.maBan = HD.maBan WHERE maHD = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, maHoaDon);
                rs = ps.executeQuery();
            }

            while (rs.next()) {
                String maHD = rs.getString(1);
                String ngayTao = rs.getString(2);
                String tenKhachHang = rs.getString(3);
                String soDienThoai = rs.getString(4);
                float tongTien = rs.getFloat(5);
                float tongTienTT = rs.getFloat(6);
                int soBan = rs.getInt(7);
                String trangThai = rs.getString(8);
                trangThai = trangThai.equals("1") ? "Đã thanh toán" : "Chưa thanh toán";
                ob = new Object[]{maHD, ngayTao, tenKhachHang, soDienThoai, tongTien, tongTienTT, soBan, trangThai};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ob;
    }

    // Xong
    public ArrayList<Object[]> timKiemHoaDonTheoTenKH(String name, String loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        ResultSet rs = null;
        try {
            if (loaiDon.trim().length() > 0 && sortKey.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')\n"
                        + "AND HD.trangThai = ? AND ngayLap BETWEEN ? AND ? ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, loaiDon);
                ps.setString(3, ngayBD);
                ps.setString(4, ngayKT);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() == 0 && sortKey.trim().length() == 0 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() > 0 && sortKey.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')\n"
                        + "AND HD.trangThai = ? ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, loaiDon);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')\n"
                        + "AND HD.trangThai = ? AND ngayLap BETWEEN ? AND ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, loaiDon);
                ps.setString(3, ngayBD);
                ps.setString(4, ngayKT);
                rs = ps.executeQuery();
            } else if (sortKey.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')\n"
                        + "AND ngayLap BETWEEN ? AND ? ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, ngayBD);
                ps.setString(3, ngayKT);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')\n"
                        + "AND HD.trangThai = ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, loaiDon);
                rs = ps.executeQuery();
            } else if (sortKey.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')\n"
                        + " ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                rs = ps.executeQuery();
            } else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')\n"
                        + "AND ngayLap BETWEEN ? AND ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, ngayBD);
                ps.setString(3, ngayKT);
                rs = ps.executeQuery();
            }

            while (rs.next()) {
                String maHD = rs.getString(1);
                String ngayTao = rs.getString(2);
                String tenKhachHang = rs.getString(3);
                String soDienThoai = rs.getString(4);
                float tongTien = rs.getFloat(5);
                float tongTienTT = rs.getFloat(6);
                int soBan = rs.getInt(7);
                String trangThai = rs.getString(8);
                trangThai = trangThai.equals("1") ? "Đã thanh toán" : "Chưa thanh toán";
                Object ob[] = new Object[]{maHD, ngayTao, tenKhachHang, soDienThoai, tongTien, tongTienTT, soBan, trangThai};
                list.add(ob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Xong
    public ArrayList<Object[]> timKiemHoaDonTheoSTD(String soDienThoai, String loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        ResultSet rs = null;
        try {
            if (loaiDon.trim().length() > 0 && sortKey.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soDienThoai = ?\n"
                        + "AND HD.trangThai = ? AND ngayLap BETWEEN ? AND ? ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soDienThoai);
                ps.setString(2, loaiDon);
                ps.setString(3, ngayBD);
                ps.setString(4, ngayKT);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() == 0 && sortKey.trim().length() == 0 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soDienThoai = ?\n";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soDienThoai);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() > 0 && sortKey.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soDienThoai = ?\n"
                        + "AND HD.trangThai = ? ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soDienThoai);
                ps.setString(2, loaiDon);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soDienThoai = ?\n"
                        + "AND HD.trangThai = ? AND ngayLap BETWEEN ? AND ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soDienThoai);
                ps.setString(2, loaiDon);
                ps.setString(3, ngayBD);
                ps.setString(4, ngayKT);
                rs = ps.executeQuery();
            } else if (sortKey.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soDienThoai = ?\n"
                        + "AND ngayLap BETWEEN ? AND ? ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soDienThoai);
                ps.setString(2, ngayBD);
                ps.setString(3, ngayKT);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soDienThoai = ?\n"
                        + "AND HD.trangThai = ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soDienThoai);
                ps.setString(2, loaiDon);
                rs = ps.executeQuery();
            } else if (sortKey.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soDienThoai = ?\n"
                        + "ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soDienThoai);
                rs = ps.executeQuery();
            } else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soDienThoai = ?\n"
                        + "AND ngayLap BETWEEN ? AND ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soDienThoai);
                ps.setString(2, ngayBD);
                ps.setString(3, ngayKT);
                rs = ps.executeQuery();
            }

            while (rs.next()) {
                String maHD = rs.getString(1);
                String ngayTao = rs.getString(2);
                String tenKhachHang = rs.getString(3);
                String soDienThoai1 = rs.getString(4);
                float tongTien = rs.getFloat(5);
                float tongTienTT = rs.getFloat(6);
                int soBan = rs.getInt(7);
                String trangThai = rs.getString(8);
                trangThai = trangThai.equals("1") ? "Đã thanh toán" : "Chưa thanh toán";
                Object ob[] = new Object[]{maHD, ngayTao, tenKhachHang, soDienThoai1, tongTien, tongTienTT, soBan, trangThai};
                list.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Xong
    public ArrayList<Object[]> timKiemHoaDonTheoSoBan(String soBan, String loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        ResultSet rs = null;
        try {
            if (loaiDon.trim().length() > 0 && sortKey.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soBan = ?\n"
                        + "AND HD.trangThai = ? AND ngayLap BETWEEN ? AND ? ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soBan);
                ps.setString(2, loaiDon);
                ps.setString(3, ngayBD);
                ps.setString(4, ngayKT);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() == 0 && sortKey.trim().length() == 0 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soBan = ?\n";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soBan);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() > 0 && sortKey.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soBan = ?\n"
                        + "AND HD.trangThai = ? ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soBan);
                ps.setString(2, loaiDon);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soBan = ?\n"
                        + "AND HD.trangThai = ? AND ngayLap BETWEEN ? AND ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soBan);
                ps.setString(2, loaiDon);
                ps.setString(3, ngayBD);
                ps.setString(4, ngayKT);
                rs = ps.executeQuery();
            } else if (sortKey.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soBan = ?\n"
                        + " AND ngayLap BETWEEN ? AND ? ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soBan);
                ps.setString(2, ngayBD);
                ps.setString(3, ngayKT);
                rs = ps.executeQuery();
            } else if (loaiDon.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soBan = ?\n"
                        + "AND HD.trangThai = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soBan);
                ps.setString(2, loaiDon);
                rs = ps.executeQuery();
            } else if (sortKey.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soBan = ?\n"
                        + "ORDER BY " + sortKey + " " + sortValue;
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soBan);
                rs = ps.executeQuery();
            } else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
                String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                        + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                        + "JOIN Ban B ON HD.maBan = B.maBan\n"
                        + "WHERE soBan = ?\n"
                        + " AND ngayLap BETWEEN ? AND ? ";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, soBan);
                ps.setString(2, ngayBD);
                ps.setString(3, ngayKT);
                rs = ps.executeQuery();
            }

            while (rs.next()) {
                String maHD = rs.getString(1);
                String ngayTao = rs.getString(2);
                String tenKhachHang = rs.getString(3);
                String soDienThoai = rs.getString(4);
                float tongTien = rs.getFloat(5);
                float tongTienTT = rs.getFloat(6);
                int soBan1 = rs.getInt(7);
                String trangThai = rs.getString(8);
                trangThai = trangThai.equals("1") ? "Đã thanh toán" : "Chưa thanh toán";
                Object ob[] = new Object[]{maHD, ngayTao, tenKhachHang, soDienThoai, tongTien, tongTienTT, soBan1, trangThai};
                list.add(ob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //Xong
    public Object timKiemHD(String maHD)  {
        Object ob = new Object[]{};
        con = ConnectDB.getConnection();
        String sql = "SELECT B.soBan,HD.ngayLap,NV.hoTenNV,KH.tenKH,KM.maKM,HD.trangThai,HD.tongTien,HD.tongTien*(DV.VAT/100.0),HD.tongTien*(KM.giamGia/100.0),\n"
                + "HD.tongTien*(DV.PV/100.0),HD.giamGiaThanhVien,HD.giamGiaSinhNhat,DDB.tienCoc,B.maLB,HD.tongTienTT,FORMAT(gioVao,'dd/MM/yyyy HH:mm') , COALESCE(FORMAT(gioRa,'dd/MM/yyyy HH:mm'),' ')\n"
                + "FROM HoaDon HD LEFT JOIN NhanVien NV ON HD.maNV = NV.maNV\n"
                + "LEFT JOIN  Ban B ON HD.maBan = B.maBan\n"
                + "LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                + "LEFT JOIN  KhuyenMai KM ON HD.maKM = KM.maKM\n"
                + "LEFT JOIN DichVu DV ON HD.maDV = DV.maDV\n"
                + "LEFT JOIN DonDatBan DDB ON HD.maDDB = DDB.maDDB WHERE HD.maHD = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String soBan = rs.getString(1);
            String ngayLap = rs.getString(2);
            String tenNV = rs.getString(3);
            String tenKH = rs.getString(4);
            String maKM = rs.getString(5);
            String trangThai = rs.getString(6);
            float tongTien = rs.getFloat(7);
            float VAT = rs.getFloat(8);
            float giamGiaKM = rs.getFloat(9);
            float giaDV = rs.getFloat(10);
            float giamGiaTV = rs.getFloat(11);
            float giamGiaNS = rs.getFloat(12);
            float tienCoc = rs.getFloat(13);
            String maLoaiBan = rs.getString(14);
            float tongTienTT = rs.getFloat(15);
            String gioVao = rs.getString(16);
            String gioRa = rs.getString(17);
            ob = new Object[]{soBan, ngayLap, tenNV, tenKH, maKM, trangThai, tongTien, VAT, giamGiaKM, giaDV, giamGiaTV, giamGiaNS, tienCoc, maLoaiBan, tongTienTT, gioVao, gioRa};
        };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ob;
    }

    ;

    public boolean kiemTraGiamGiaSN(KhachHang kh){
        con = ConnectDB.getConnection();
        int n = 0;
        try{
            PreparedStatement ps = con.prepareStatement("select count(*) from HoaDon where maKH = ? and ngayLap = FORMAT(GETDATE(),'yyyy-MM-dd')");
            ps.setString(1, kh.getMaKH());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                n = rs.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return n == 0;
    }
    
    // Xong
    public ArrayList<Object[]> timKiemCTHD(String maHD)  {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        String sql = "SELECT tenMA,gia,giaSauGiam,soLuong,thanhTien\n"
                + "FROM ChiTietHoaDon CTHD JOIN MonAn MA \n"
                + "ON CTHD.maMA = MA.maMA \n"
                + "WHERE CTHD.maHD = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maHD);
        ResultSet rs = ps.executeQuery();
        int i = 1;
        while (rs.next()) {
            String tenMonAn = rs.getString(1);
            String gia = rs.getString(2);
            String giaSauGiam = rs.getString(3);
            String soLuong = rs.getString(4);
            String thanhTien = rs.getString(5);
            Object ob[] = new Object[]{i, tenMonAn, gia, giaSauGiam, soLuong, thanhTien};
            list.add(ob);
            i++;
        };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ;

    public ArrayList<Object[]> hoaDonTrongNgay() throws SQLException {
        con = ConnectDB.getConnection();
        ArrayList<Object[]> list = new ArrayList<>();
        String sql = "SELECT maHD,ngayLap,tenKH,soDienThoai,tongTien,tongTienTT,soBan,HD.trangThai\n"
                + "FROM HoaDon HD LEFT JOIN KhachHang KH ON HD.maKH = KH.maKH\n"
                + "LEFT JOIN Ban B ON HD.maBan = B.maBan\n"
                + "WHERE ngayLap = CONVERT(Date,GETDATE())";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String maHD = rs.getString(1);
            String ngayTao = rs.getString(2);
            String tenKhachHang = rs.getString(3);
            String soDienThoai = rs.getString(4);
            float tongTien = rs.getFloat(5);
            float tongTienTT = rs.getFloat(6);
            int soBan = rs.getInt(7);
            String trangThai = rs.getString(8);
            trangThai = trangThai.equals("1") ? "Đã thanh toán" : "Chưa thanh toán";
            Object ob[] = new Object[]{maHD, ngayTao, tenKhachHang, soDienThoai, tongTien, tongTienTT, soBan, trangThai};
            list.add(ob);
        }

        return list;

    }

}
