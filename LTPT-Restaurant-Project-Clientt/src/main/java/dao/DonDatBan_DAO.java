/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.DonDatBan;
import entity.NhanVien;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author THANHTRI
 */
public class DonDatBan_DAO {

    private Connection con = null;

    public ArrayList<DonDatBan> thongKeQuy(String quarter, String year, String tt, String loaiBan) {
        con = ConnectDB.getConnection();
        ArrayList<DonDatBan> list = new ArrayList<>();
        try {
            String sql = "";
            if (tt.equals("3")) {
                sql += "SELECT * FROM DonDatBan WHERE trangThai = ? AND CAST((MONTH(gioHuy) - 1) / 3 + 1 AS INT) = ? AND YEAR(gioHuy) = ? ";
            } else {
                sql += "SELECT * FROM DonDatBan WHERE trangThai = ? AND CAST((MONTH(ngayTao) - 1) / 3 + 1 AS INT) = ? AND YEAR(ngayTao) = ? ";
            }

            if (!loaiBan.equals("Tất cả")) {
                sql += "AND maBan LIKE ?";
            }
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tt);
            ps.setString(2, quarter);
            ps.setString(3, year);
            if (!loaiBan.equals("Tất cả")) {
                ps.setString(4, loaiBan.equals("Tầng 1") ? "T1%" : loaiBan.equals("Tầng 2") ? "T2%" : "VP%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mDDB = rs.getString("maDDB");
                String hoTenKH = rs.getString("hoTenKH");
                String soDienThoai = rs.getString("soDienThoai");
                int soLuongKH = rs.getInt("soLuongKH");
                double tienCoc = rs.getDouble("tienCoc");
                LocalDateTime gioHen = rs.getTimestamp("gioHen").toLocalDateTime();
                double hoanCoc = rs.getDouble("hoanCoc");
                Timestamp timestamp = rs.getTimestamp("gioHuy");
                LocalDateTime gioHuy = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                String mNV = rs.getString("maNV");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String ghiChu = rs.getString("ghiChu");
                String maBan = rs.getString("maBan");
                String maNVHuy = rs.getString("maNVHuy");
                int trangThai = rs.getInt("trangThai");
                list.add(new DonDatBan(mDDB, hoTenKH, soDienThoai, soLuongKH, tienCoc, trangThai, gioHen, hoanCoc, gioHuy, new NhanVien(mNV), ngayTao, ghiChu, new Ban(maBan), new NhanVien(maNVHuy)));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<DonDatBan> thongKeNam(String year, String tt, String loaiBan) {
        con = ConnectDB.getConnection();
        ArrayList<DonDatBan> list = new ArrayList<>();
        try {
            String sql = "";
            if (tt.equals("3")) {
                sql += "SELECT * FROM DonDatBan WHERE trangThai = ? AND YEAR(gioHuy) = ? ";
            } else {
                sql += "SELECT * FROM DonDatBan WHERE trangThai = ? AND YEAR(ngayTao) = ? ";
            }
            if (!loaiBan.equals("Tất cả")) {
                sql += "AND maBan LIKE ?";
            }
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tt);
            ps.setString(2, year);
            if (!loaiBan.equals("Tất cả")) {
                ps.setString(3, loaiBan.equals("Tầng 1") ? "T1%" : loaiBan.equals("Tầng 2") ? "T2%" : "VP%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mDDB = rs.getString("maDDB");
                String hoTenKH = rs.getString("hoTenKH");
                String soDienThoai = rs.getString("soDienThoai");
                int soLuongKH = rs.getInt("soLuongKH");
                double tienCoc = rs.getDouble("tienCoc");
                LocalDateTime gioHen = rs.getTimestamp("gioHen").toLocalDateTime();
                double hoanCoc = rs.getDouble("hoanCoc");
                Timestamp timestamp = rs.getTimestamp("gioHuy");
                LocalDateTime gioHuy = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                String mNV = rs.getString("maNV");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String ghiChu = rs.getString("ghiChu");
                String maBan = rs.getString("maBan");
                String maNVHuy = rs.getString("maNVHuy");
                int trangThai = rs.getInt("trangThai");
                list.add(new DonDatBan(mDDB, hoTenKH, soDienThoai, soLuongKH, tienCoc, trangThai, gioHen, hoanCoc, gioHuy, new NhanVien(mNV), ngayTao, ghiChu, new Ban(maBan), new NhanVien(maNVHuy)));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<DonDatBan> thongKeThang(String month, String year, String tt, String loaiBan) {
        con = ConnectDB.getConnection();
        ArrayList<DonDatBan> list = new ArrayList<>();
        try {
            String sql = "";
            if (tt.equals("3")) {
                sql += "SELECT * FROM DonDatBan WHERE trangThai = ? AND MONTH(gioHuy) = ? AND YEAR(gioHuy) = ? ";
            } else {
                sql += "SELECT * FROM DonDatBan WHERE trangThai = ? AND MONTH(ngayTao) = ? AND YEAR(ngayTao) = ? ";
            }

            if (!loaiBan.equals("Tất cả")) {
                sql += "AND maBan LIKE ?";
            }
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tt);
            ps.setString(2, month);
            ps.setString(3, year);
            if (!loaiBan.equals("Tất cả")) {
                ps.setString(4, loaiBan.equals("Tầng 1") ? "T1%" : loaiBan.equals("Tầng 2") ? "T2%" : "VP%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mDDB = rs.getString("maDDB");
                String hoTenKH = rs.getString("hoTenKH");
                String soDienThoai = rs.getString("soDienThoai");
                int soLuongKH = rs.getInt("soLuongKH");
                double tienCoc = rs.getDouble("tienCoc");
                LocalDateTime gioHen = rs.getTimestamp("gioHen").toLocalDateTime();
                double hoanCoc = rs.getDouble("hoanCoc");
                Timestamp timestamp = rs.getTimestamp("gioHuy");
                LocalDateTime gioHuy = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                String mNV = rs.getString("maNV");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String ghiChu = rs.getString("ghiChu");
                String maBan = rs.getString("maBan");
                String maNVHuy = rs.getString("maNVHuy");
                int trangThai = rs.getInt("trangThai");
                list.add(new DonDatBan(mDDB, hoTenKH, soDienThoai, soLuongKH, tienCoc, trangThai, gioHen, hoanCoc, gioHuy, new NhanVien(mNV), ngayTao, ghiChu, new Ban(maBan), new NhanVien(maNVHuy)));

            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<DonDatBan> thongKeNgay(String day, String tt, String loaiBan) {
        con = ConnectDB.getConnection();
        ArrayList<DonDatBan> list = new ArrayList<>();
        try {
            String sql = "";
            if (tt.equals("3")) {
                sql += "SELECT * FROM DonDatBan WHERE trangThai = ? AND FORMAT(gioHuy, 'dd/MM/yyyy') = ? ";
            } else {
                sql += "SELECT * FROM DonDatBan WHERE trangThai = ? AND FORMAT(ngayTao, 'dd/MM/yyyy') = ? ";
            }

            if (!loaiBan.equals("Tất cả")) {
                sql += "AND maBan LIKE ?";
            }

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tt);
            ps.setString(2, day);
            if (!loaiBan.equals("Tất cả")) {
                ps.setString(3, loaiBan.equals("Tầng 1") ? "T1%" : loaiBan.equals("Tầng 2") ? "T2%" : "VP%");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mDDB = rs.getString("maDDB");
                String hoTenKH = rs.getString("hoTenKH");
                String soDienThoai = rs.getString("soDienThoai");
                int soLuongKH = rs.getInt("soLuongKH");
                double tienCoc = rs.getDouble("tienCoc");
                LocalDateTime gioHen = rs.getTimestamp("gioHen").toLocalDateTime();
                double hoanCoc = rs.getDouble("hoanCoc");
                Timestamp timestamp = rs.getTimestamp("gioHuy");
                LocalDateTime gioHuy = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                String mNV = rs.getString("maNV");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String ghiChu = rs.getString("ghiChu");
                String maBan = rs.getString("maBan");
                String maNVHuy = rs.getString("maNVHuy");
                int trangThai = rs.getInt("trangThai");
                list.add(new DonDatBan(mDDB, hoTenKH, soDienThoai, soLuongKH, tienCoc, trangThai, gioHen, hoanCoc, gioHuy, new NhanVien(mNV), ngayTao, ghiChu, new Ban(maBan), new NhanVien(maNVHuy)));

            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public Object[] getThongTinDonDatBan(String maDDB) throws SQLException {
        Connection con = ConnectDB.getConnection();
        Object[] obj = new Object[6];

        String sql = "SELECT hoTenKH,soDienThoai,FORMAT(gioHen,'dd/MM/yyyy HH:mm'),soBan,tienCoc,DATEDIFF(minute ,GETDATE(), gioHen)\n" +
                "FROM DonDatBan JOIN Ban ON DonDatBan.maBan = Ban.maBan\n" +
                "WHERE maDDB = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maDDB);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            obj[0] = rs.getString(1);
            obj[1] = rs.getString(2);
            obj[2] = rs.getString(3);
            obj[3] = rs.getInt(4);
            obj[4] = rs.getDouble(5);
            obj[5] = rs.getInt(6);
        }

        return obj;
    }


    public ArrayList<Object[]> getChiTietDonDatBan(String maDonDatBan) throws SQLException {
        Connection con = ConnectDB.getConnection();
        ArrayList<Object[]> list = new ArrayList<>();
        String sql = "SELECT tenMA,gia,giaSauGiam,soLuong,thanhTien\n" +
                "FROM DonDatBan DDB JOIN ChiTietDatBan CTDB ON DDB.maDDB = CTDB.maDDB\n" +
                "JOIN MonAn MA ON CTDB.maMA = MA.maMA\n" +
                "WHERE DDB.maDDB = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maDonDatBan);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            Object[] obj = new Object[5];
            obj[0] = rs.getString(1);
            obj[1] = rs.getDouble(2);
            obj[2] = rs.getDouble(3);
            obj[3] = rs.getInt(4);
            obj[4] = rs.getDouble(5);
            list.add(obj);
        }

        return list;
    }


    public ArrayList<Integer> loadNam() {
        con = ConnectDB.getConnection();
        ArrayList<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT YEAR(ngayTao) AS Nam FROM DonDatBan");
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

    public boolean capNhatTTDDB(String maDDB) {
        con = ConnectDB.getConnection();
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE DonDatBan Set trangThai = 2 WHERE maDDB = ?");
            ps.setString(1, maDDB);
            n = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return n > 0;
    }

    public boolean update(String maDDB, String hoTenKH, String soDienThoai, int soLuongKH, double tienCoc, LocalDateTime gioHen, String maNV, String ghiChu, String maBan) {
        con = ConnectDB.getConnection();
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE DonDatBan SET hoTenKH = ?, soDienThoai = ?, soLuongKH = ?, tienCoc = ?, trangThai = ?, gioHen = ?, maNV = ?, ghiChu = ?, maBan = ? WHERE maDDB = ?");
            ps.setString(1, hoTenKH);
            ps.setString(2, soDienThoai);
            ps.setInt(3, soLuongKH);
            ps.setDouble(4, tienCoc);
            ps.setInt(5, 1);
            ps.setTimestamp(6, Timestamp.valueOf(gioHen));
            ps.setString(7, maNV);
            ps.setString(8, ghiChu);
            ps.setString(9, maBan);
            ps.setString(10, maDDB);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return n > 0;
    }

    public ArrayList<DonDatBan> timKiemCapNhat(String date, String sdt) {
        con = ConnectDB.getConnection();
        ArrayList<DonDatBan> list = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder("SELECT * FROM DonDatBan WHERE trangThai = 1 AND FORMAT(gioHen, 'dd/MM/yyyy') = ? ");
            if (sdt != null) {
                sql.append("AND soDienThoai = ?");
            }
            PreparedStatement ps = con.prepareStatement(sql.toString());
            ps.setString(1, date);
            if (sdt != null) {
                ps.setString(2, sdt);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mDDB = rs.getString("maDDB");
                String hoTenKH = rs.getString("hoTenKH");
                String soDienThoai = rs.getString("soDienThoai");
                int soLuongKH = rs.getInt("soLuongKH");
                double tienCoc = rs.getDouble("tienCoc");
                LocalDateTime gioHen = rs.getTimestamp("gioHen").toLocalDateTime();
                String maNV = rs.getString("maNV");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String ghiChu = rs.getString("ghiChu");
                String maBan = rs.getString("maBan");
                int trangThai = rs.getInt("trangThai");
                list.add(new DonDatBan(mDDB, hoTenKH, soDienThoai, soLuongKH, tienCoc, trangThai, gioHen, new NhanVien(maNV), ngayTao, ghiChu, new Ban(maBan)));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public LocalDateTime checkTimeBan(String maBan, LocalDateTime gioHen) {
        con = ConnectDB.getConnection();
        LocalDateTime time = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM DonDatBan WHERE trangThai = 1 AND maBan = ? AND gioHen BETWEEN DATEADD(HOUR, -3, ?) AND DATEADD(HOUR, 3, ?)");
            ps.setString(1, maBan);
            ps.setTimestamp(2, Timestamp.valueOf(gioHen));
            ps.setTimestamp(3, Timestamp.valueOf(gioHen));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                time = rs.getTimestamp("gioHen").toLocalDateTime();
            }
            return time;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return time;
    }

    public String datBanMoiNhat() {
        con = ConnectDB.getConnection();
        String maHD = null;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select top 1 maDDB from DonDatBan order by maDDB desc");
            while (rs.next()) {
                maHD = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maHD;
    }

    public boolean insert(String hoTenKH, String soDienThoai, int soLuongKH, double tienCoc, LocalDateTime gioHen, String maNV, String ghiChu, String maBan) {
        con = ConnectDB.getConnection();
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("INSERT DonDatBan (hoTenKH, soDienThoai, soLuongKH, tienCoc, trangThai, gioHen, maNV, ghiChu, maBan) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, hoTenKH);
            ps.setString(2, soDienThoai);
            ps.setInt(3, soLuongKH);
            ps.setDouble(4, tienCoc);
            ps.setInt(5, 1);
            ps.setTimestamp(6, Timestamp.valueOf(gioHen));
            ps.setString(7, maNV);
            ps.setString(8, ghiChu);
            ps.setString(9, maBan);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return n > 0;
    }

    public DonDatBan getDDBForHD(String maDDB) {
        con = ConnectDB.getConnection();
        DonDatBan x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM DonDatBan WHERE maDDB = ?");
            ps.setString(1, maDDB);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mDDB = rs.getString("maDDB");
                double tienCoc = rs.getDouble("tienCoc");

                x = new DonDatBan(mDDB, tienCoc);
            }
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }

    public DonDatBan getDDB(String maDDB) {
        con = ConnectDB.getConnection();
        DonDatBan x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM DonDatBan WHERE maDDB = ?");
            ps.setString(1, maDDB);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mDDB = rs.getString("maDDB");
                String hoTenKH = rs.getString("hoTenKH");
                String soDienThoai = rs.getString("soDienThoai");
                int soLuongKH = rs.getInt("soLuongKH");
                double tienCoc = rs.getDouble("tienCoc");
                LocalDateTime gioHen = rs.getTimestamp("gioHen").toLocalDateTime();
                double hoanCoc = rs.getDouble("hoanCoc");
//                LocalDateTime gioHuy = rs.getTimestamp("gioHuy").toLocalDateTime();
                Timestamp timestamp = rs.getTimestamp("gioHuy");
                LocalDateTime gioHuy = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                String maNV = rs.getString("maNV");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String ghiChu = rs.getString("ghiChu");
                String maBan = rs.getString("maBan");
                String maNVHuy = rs.getString("maNVHuy");
                int trangThai = rs.getInt("trangThai");
                x = new DonDatBan(mDDB, hoTenKH, soDienThoai, soLuongKH, tienCoc, trangThai, gioHen, hoanCoc, gioHuy, new NhanVien(maNV), ngayTao, ghiChu, new Ban(maBan), new NhanVien(maNVHuy));
            }
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }

    public ArrayList<DonDatBan> todayList(String today, String maNV) {
        con = ConnectDB.getConnection();
        ArrayList<DonDatBan> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM DonDatBan WHERE maNV = ? AND FORMAT(ngayTao, 'dd/MM/yyyy') = ? AND trangThai IN (1, 2)");
            ps.setString(1, maNV);
            ps.setString(2, today);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mDDB = rs.getString("maDDB");
                String hoTenKH = rs.getString("hoTenKH");
                String soDienThoai = rs.getString("soDienThoai");
                int soLuongKH = rs.getInt("soLuongKH");
                double tienCoc = rs.getDouble("tienCoc");
                LocalDateTime gioHen = rs.getTimestamp("gioHen").toLocalDateTime();
                double hoanCoc = rs.getDouble("hoanCoc");
                Timestamp timestamp = rs.getTimestamp("gioHuy");
                LocalDateTime gioHuy = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                String mNV = rs.getString("maNV");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String ghiChu = rs.getString("ghiChu");
                String maBan = rs.getString("maBan");
                String maNVHuy = rs.getString("maNVHuy");
                int trangThai = rs.getInt("trangThai");
                list.add(new DonDatBan(mDDB, hoTenKH, soDienThoai, soLuongKH, tienCoc, trangThai, gioHen, hoanCoc, gioHuy, new NhanVien(mNV), ngayTao, ghiChu, new Ban(maBan), new NhanVien(maNVHuy)));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<DonDatBan> todayListHuy(String today) {
        con = ConnectDB.getConnection();
        ArrayList<DonDatBan> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM DonDatBan WHERE FORMAT(ngayTao, 'dd/MM/yyyy') = ? AND trangThai = 3");
            ps.setString(1, today);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mDDB = rs.getString("maDDB");
                String hoTenKH = rs.getString("hoTenKH");
                String soDienThoai = rs.getString("soDienThoai");
                int soLuongKH = rs.getInt("soLuongKH");
                double tienCoc = rs.getDouble("tienCoc");
                LocalDateTime gioHen = rs.getTimestamp("gioHen").toLocalDateTime();
                double hoanCoc = rs.getDouble("hoanCoc");
                Timestamp timestamp = rs.getTimestamp("gioHuy");
                LocalDateTime gioHuy = (timestamp != null) ? timestamp.toLocalDateTime() : null;
                String mNV = rs.getString("maNV");
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                String ghiChu = rs.getString("ghiChu");
                String maBan = rs.getString("maBan");
                String maNVHuy = rs.getString("maNVHuy");
                int trangThai = rs.getInt("trangThai");
                list.add(new DonDatBan(mDDB, hoTenKH, soDienThoai, soLuongKH, tienCoc, trangThai, gioHen, hoanCoc, gioHuy, new NhanVien(mNV), ngayTao, ghiChu, new Ban(maBan), new NhanVien(maNVHuy)));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public void capNhatBanTruocGioKhachDen() {
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "UPDATE Ban\n"
                    + "SET tinhTrang = 2\n"
                    + "FROM DonDatBan DDB JOIN dbo.Ban B on B.maBan = DDB.maBan\n"
                    + "WHERE CONVERT(DATE,gioHen) = CONVERT(DATE,GETDATE())\n"
                    + "AND trangThai = 1 AND DATEDIFF(minute, GETDATE(), gioHen) BETWEEN 0 AND 30";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void capNhatBanSauGioKhachDen() {
        try {
            Connection con = ConnectDB.getConnection();
            String sql1 = "UPDATE Ban\n"
                    + "SET tinhTrang = 0\n"
                    + "FROM DonDatBan DDB JOIN dbo.Ban B on B.maBan = DDB.maBan\n"
                    + "WHERE CONVERT(DATE, gioHen) = CONVERT(DATE, GETDATE()) AND B.tinhTrang = 2 AND DDB.trangThai = 1\n"
                    + "  AND DATEDIFF(minute, GETDATE(), gioHen) BETWEEN -60 AND -30";

            String sql2 = "UPDATE DonDatBan\n"
                    + "SET trangThai = 3, hoanCoc = 0, gioHuy = GETDATE()\n"
                    + "WHERE CONVERT(DATE, gioHen) = CONVERT(DATE, GETDATE()) AND trangThai = 1\n"
                    + "  AND DATEDIFF(minute, GETDATE(), gioHen) BETWEEN -60 AND -30 ";

            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(sql2);
            ps2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Object timKiemDonDatBanMa(String maDonDatBan, String ngayDB, String ngayKT) throws SQLException {
        Object ob = new Object[]{};
         con = ConnectDB.getConnection();
        ResultSet rs = null;
        if (ngayDB.trim().length() > 0 & ngayKT.trim().length() > 0) {
            String sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE maDDB = ? AND ngayTao BETWEEN ? AND ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maDonDatBan);
            ps.setString(2, ngayDB);
            ps.setString(3, ngayKT);
            rs = ps.executeQuery();
        } else {
            String sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE maDDB = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maDonDatBan);
            rs = ps.executeQuery();
        }

        while (rs.next()) {
            String maDDB = rs.getString(1);
            String hoTenKH = rs.getString(2);
            String tenNV = rs.getString(3);
            String ngayTao = rs.getString(4);
            Double tienCoc = rs.getDouble(5);
            String soDienThoai = rs.getString(6);
            int trangThai = rs.getInt(7);
            String TT = "";
            if (trangThai == 1) {
                TT = "Đang xử lí";
            } else if (trangThai == 2) {
                TT = "Đã nhận bàn";
            } else {
                TT = "Đã hủy";
            }
            int soBan = rs.getInt(8);
            String gioHen = rs.getString(9);
            ob = new Object[]{maDDB, hoTenKH, tenNV, ngayTao, tienCoc, soDienThoai, TT, gioHen, soBan};
        }
        return ob;
    }

    // Xong
    public ArrayList<Object[]> timKiemDonDatBanName(String name, String loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws SQLException {
        ArrayList<Object[]> list = new ArrayList<>();
         con = ConnectDB.getConnection();
        String sql = "";
        ResultSet rs = null;
        if (loaiDon.trim().length() > 0 && sortKey.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE hoTenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND DDB.trangThai = ? AND ngayTao BETWEEN ? AND ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, loaiDon);
            ps.setString(3, ngayBD);
            ps.setString(4, ngayKT);
            rs = ps.executeQuery();
        } else if (loaiDon.trim().length() == 0 && sortKey.trim().length() == 0 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE hoTenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
        } else if (loaiDon.trim().length() > 0 && sortKey.trim().length() > 0) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE hoTenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND DDB.trangThai = ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, loaiDon);
            rs = ps.executeQuery();
        } else if (loaiDon.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE hoTenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND DDB.trangThai = ? AND ngayTao BETWEEN ? AND ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, loaiDon);
            ps.setString(3, ngayBD);
            ps.setString(4, ngayKT);
            rs = ps.executeQuery();
        } else if (sortKey.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE hoTenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')  AND ngayTao BETWEEN ? AND ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, ngayBD);
            ps.setString(3, ngayKT);
            rs = ps.executeQuery();
        } else if (loaiDon.trim().length() > 0) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE hoTenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND DDB.trangThai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, loaiDon);
            rs = ps.executeQuery();
        } else if (sortKey.trim().length() > 0) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE hoTenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')  ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
        } else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE hoTenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')  AND ngayTao BETWEEN ? AND ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, ngayBD);
            ps.setString(3, ngayKT);
            rs = ps.executeQuery();
        }

        while (rs.next()) {
            String maDDB = rs.getString(1);
            String hoTenKH = rs.getString(2);
            String tenNV = rs.getString(3);
            String ngayTao = rs.getString(4);
            Double tienCoc = rs.getDouble(5);
            String soDienThoai = rs.getString(6);
            int trangThai = rs.getInt(7);
            String TT = "";
            if (trangThai == 1) {
                TT = "Đang xử lí";
            } else if (trangThai == 2) {
                TT = "Đã nhận bàn";
            } else {
                TT = "Đã hủy";
            }
            int soBan = rs.getInt(8);
            String gioHen = rs.getString(9);
            Object ob[] = new Object[]{maDDB, hoTenKH, tenNV, ngayTao, tienCoc, soDienThoai, TT, gioHen, soBan};
            list.add(ob);
        }
        return list;
    }

    // Xong
    public ArrayList<Object[]> timKiemDonDatBanPhone(String phone, String loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws SQLException {
        ArrayList<Object[]> list = new ArrayList<>();
         con = ConnectDB.getConnection();
        String sql = "";
        ResultSet rs = null;

        if (loaiDon.trim().length() > 0 && sortKey.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE DDB.soDienThoai = ? AND DDB.trangThai = ? AND ngayTao BETWEEN ? AND ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, loaiDon);
            ps.setString(3, ngayBD);
            ps.setString(4, ngayKT);
            rs = ps.executeQuery();
        } else if (loaiDon.trim().length() == 0 && sortKey.trim().length() == 0 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE DDB.soDienThoai = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phone);
            rs = ps.executeQuery();
        } else if (loaiDon.trim().length() > 0 && sortKey.trim().length() > 0) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE DDB.soDienThoai = ? AND DDB.trangThai = ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, loaiDon);
            rs = ps.executeQuery();
        } else if (loaiDon.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE DDB.soDienThoai = ? AND DDB.trangThai = ? AND ngayTao BETWEEN ? AND ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, loaiDon);
            ps.setString(3, ngayBD);
            ps.setString(4, ngayKT);
            rs = ps.executeQuery();
        } else if (sortKey.trim().length() > 0 && (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0)) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE DDB.soDienThoai = ? AND ngayTao BETWEEN ? AND ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, ngayBD);
            ps.setString(3, ngayKT);
            rs = ps.executeQuery();
        } else if (loaiDon.trim().length() > 0) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE DDB.soDienThoai = ? AND DDB.trangThai = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, loaiDon);
            rs = ps.executeQuery();
        } else if (sortKey.trim().length() > 0) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE DDB.soDienThoai = ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phone);
            rs = ps.executeQuery();
        } else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            sql = "SELECT maDDB,hoTenKH,hoTenNV,FORMAT(ngayTao,'dd/MM/yyyy'),tienCoc,DDB.soDienThoai,DDB.trangThai,B.soBan,FORMAT(gioHen,'dd/MM/yyyy hh:mm')\n"
                    + "FROM DonDatBan DDB JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                    + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                    + "WHERE DDB.soDienThoai = ? AND ngayTao BETWEEN ? AND ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, phone);
            ps.setString(2, ngayBD);
            ps.setString(3, ngayKT);
            rs = ps.executeQuery();
        }

        while (rs.next()) {
            String maDDB = rs.getString(1);
            String hoTenKH = rs.getString(2);
            String tenNV = rs.getString(3);
            String ngayTao = rs.getString(4);
            Double tienCoc = rs.getDouble(5);
            String soDienThoai = rs.getString(6);
            int trangThai = rs.getInt(7);
            String TT = "";
            if (trangThai == 1) {
                TT = "Đang xử lí";
            } else if (trangThai == 2) {
                TT = "Đã nhận bàn";
            } else {
                TT = "Đã hủy";
            }
            int soBan = rs.getInt(8);
            String gioHen = rs.getString(9);
            Object ob[] = new Object[]{maDDB, hoTenKH, tenNV, ngayTao, tienCoc, soDienThoai, TT, gioHen, soBan};
            list.add(ob);
        }
        return list;
    }


    // Xong
    public Object timDDB(String ma) {
        Object ob = new Object[]{};
        con = ConnectDB.getConnection();
        String sql = "SELECT hoTenKH,FORMAT(ngayTao,'dd/MM/yyyy'),FORMAT(gioHen,'dd/MM/yyyy HH:mm'),soLuongKH,soDienThoai,soBan,tienCoc,hoanCoc,FORMAT(gioHuy,'dd/MM/yyyy hh:mm'),DDB.trangThai\n"
                + "FROM DonDatBan DDB JOIN Ban B ON DDB.maBan = B.maBan\n"
                + "WHERE DDB.maDDB = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, ma);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String hoTenKH = rs.getString(1);
            String ngayTao = rs.getString(2);
            String gioHen = rs.getString(3);
            int soLuongKH = rs.getInt(4);
            String soDienThoai = rs.getString(5);
            int soBan = rs.getInt(6);
            float tienCoc = rs.getFloat(7);
            float hoanCoc = rs.getFloat(8);
            String gioHuy = rs.getString(9);
            int trangThai = rs.getInt(10);
            String TT = "";
            if (trangThai == 1) {
                TT = "Đang xử lí";
            } else if (trangThai == 2) {
                TT = "Đã nhận bàn";
            } else {
                TT = "Đã hủy";
            }
            ob = new Object[]{hoTenKH, ngayTao, gioHen, soLuongKH, soDienThoai, soBan, tienCoc, hoanCoc, gioHuy, TT};
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ob;
    }

    ;

    // Xong
    public ArrayList<Object[]> timChiTietDonDatBan(String maDonDatBan)  {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();

        String sql = "SELECT  tenMA,gia,giaSauGiam,soLuong,thanhTien\n"
                + "FROM ChiTietDatBan CTDB JOIN MonAn MA ON CTDB.maMA = MA.maMA\n"
                + "WHERE maDDB = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maDonDatBan);
        ResultSet rs = ps.executeQuery();
        int i = 1;
        while (rs.next()) {
            String tenMonAn = rs.getString(1);
            float gia = rs.getFloat(2);
            float giaSauGiam = rs.getFloat(3);
            int soLuong = rs.getInt(4);
            float thanhTien = rs.getFloat(5);
            Object[] ob = {i, tenMonAn, gia, giaSauGiam, soLuong, thanhTien};
            list.add(ob);
            i++;
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ;

    // Xong
    public boolean huyDonDatBan(String maDDB, double hoanCoc, LocalDateTime gioHuy, String maNV) throws SQLException {
        con = ConnectDB.getConnection();
        int n = 0;
        String sql = "UPDATE DonDatBan\n"
                + "SET trangThai = 3, hoanCoc = ?, gioHuy = ?, maNVHuy = ?\n"
                + "WHERE maDDB = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, hoanCoc);
        ps.setObject(2, gioHuy);
        ps.setString(3, maNV);
        ps.setString(4, maDDB);

        n = ps.executeUpdate();
        return n > 0;
    }

    public ArrayList<Object[]> donDatBanTrongNgay() throws SQLException {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        String sql = "SELECT maDDB, hoTenKH, hoTenNV, ngayTao, tienCoc, DDB.soDienThoai, DDB.trangThai, B.soBan, gioHen AS gioHenDate\n"
                + "FROM DonDatBan DDB\n"
                + "JOIN NhanVien NV ON DDB.maNV = NV.maNV\n"
                + "JOIN Ban B ON DDB.maBan = B.maBan\n"
                + "WHERE CONVERT(DATE, gioHen) = CONVERT(DATE, GETDATE())\n"
                + "ORDER BY  DATEDIFF(HOUR, GETDATE(), gioHen) ASC ";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String maDDB = rs.getString(1);
            String hoTenKH = rs.getString(2);
            String tenNV = rs.getString(3);
            LocalDate ngayTao = rs.getDate(4).toLocalDate();
            Double tienCoc = rs.getDouble(5);
            String soDienThoai = rs.getString(6);
            int trangThai = rs.getInt(7);
            String TT = "";
            if (trangThai == 1) {
                TT = "Đang xử lí";
            } else if (trangThai == 2) {
                TT = "Đã nhận bàn";
            } else {
                TT = "Đã hủy";
            }
            int soBan = rs.getInt(8);
            String gioHen = rs.getString(9);
            Object ob[] = new Object[]{maDDB, hoTenKH, tenNV, ngayTao, tienCoc, soDienThoai, TT, gioHen, soBan};
            list.add(ob);
        }

        return list;
    }

}
