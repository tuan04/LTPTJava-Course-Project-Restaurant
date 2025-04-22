/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.KhuyenMai;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author THANHTRI
 */
public class KhuyenMai_DAO {

    private Connection con = null;

    public KhuyenMai getKhuyenMaiTheoMa(String maKM) {
        KhuyenMai km = null;
        con = ConnectDB.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("select * from KhuyenMai where maKM = ?");
            ps.setString(1, maKM);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKm = rs.getString(1);
                String tenKm = rs.getString(2);
                int giamGiam = rs.getInt(3);
                LocalDate ngayHH = rs.getDate(4).toLocalDate();
                LocalDate ngayBD = rs.getDate(5).toLocalDate();
                int soLuong = rs.getInt(6);
                String loaiKM = rs.getString(7);

                km = new KhuyenMai(maKm, tenKm, giamGiam, ngayHH, ngayBD, soLuong, loaiKM);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return km;
    }

    public ArrayList<KhuyenMai> getList() {
        ArrayList<KhuyenMai> list = new ArrayList<KhuyenMai>();
        PreparedStatement ps = null;
        con = ConnectDB.getConnection();
        String sql = "select * from KhuyenMai";
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKM = rs.getString(1);
                String tenKM = rs.getString(2);
                int giamGia = rs.getInt(3);
                LocalDate ngayHH = rs.getDate(4).toLocalDate();
                LocalDate ngayBD = rs.getDate(5).toLocalDate();
                int soLuong = rs.getInt(6);
                String loaiKM = rs.getString(7);

                list.add(new KhuyenMai(maKM, tenKM, giamGia, ngayHH, ngayBD, soLuong, loaiKM));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    // Xong
    public boolean themKhuyenMai(KhuyenMai km) throws SQLException {
        int n = 0;
        con = ConnectDB.getConnection();
        String sql = "insert into KhuyenMai values(?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, km.getMaKM());
        ps.setString(2, km.getTenKM());
        ps.setInt(3, km.getGiamGia());
        ps.setDate(4, Date.valueOf(km.getNgayHH()));
        ps.setDate(5, Date.valueOf(km.getNgayBD()));
        ps.setInt(6, km.getSoLuong());
        ps.setString(7, km.getLoaiKM());
        n = ps.executeUpdate();
        return n > 0;
    }

    //Xong
    public int countKM() throws SQLException {
        con = ConnectDB.getConnection();
        String sql = "select count(*) from KhuyenMai";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    //Lọc Khuyến Mãi
    public ArrayList<KhuyenMai> getListKMTheoLoai(String maLoaiKM) {
        con = ConnectDB.getConnection();
        ArrayList<KhuyenMai> list = new ArrayList<KhuyenMai>();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from KhuyenMai where loaiKM = ?");
            ps.setString(1, maLoaiKM);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKM = rs.getString(1);
                String tenKM = rs.getString(2);
                int giamGia = rs.getInt(3);
                LocalDate ngayHH = rs.getDate(4).toLocalDate();
                LocalDate ngayBD = rs.getDate(5).toLocalDate();
                int soLuong = rs.getInt(6);
                String loaiKM = rs.getString(7);

                list.add(new KhuyenMai(maKM, tenKM, giamGia, ngayHH, ngayBD, soLuong, loaiKM));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    //Tìm theo tên
    public ArrayList<KhuyenMai> TimTheoTen(String ten) {
        con = ConnectDB.getConnection();
        ArrayList<KhuyenMai> list = new ArrayList<KhuyenMai>();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from KhuyenMai where tenKM COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')");
            ps.setString(1, ten);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKM = rs.getString(1);
                String tenKM = rs.getString(2);
                int giamGia = rs.getInt(3);
                LocalDate ngayHH = rs.getDate(4).toLocalDate();
                LocalDate ngayBD = rs.getDate(5).toLocalDate();
                int soLuong = rs.getInt(6);
                String loaiKM = rs.getString(7);

                list.add(new KhuyenMai(maKM, tenKM, giamGia, ngayHH, ngayBD, soLuong, loaiKM));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    // Cập nhật
    public boolean CapNhatKhuyenMai(KhuyenMai km) {
        con = ConnectDB.getConnection();
        PreparedStatement stml = null;
        int n = 0;
        try {
            stml = con.prepareStatement("update KhuyenMai set tenKM=?, giamGia=?, ngayHH=?, ngayBD=?, soLuong=? where maKM=?");
            stml.setString(1, km.getTenKM());
            stml.setInt(2, km.getGiamGia());
//            if (km.getNgayHH() != null) {
//                    stml.setDate(3, new java.sql.Date(km.getNgayHH().getTime()));
//            } else {
//                    stml.setDate(3, null);
//            }
//            if (km.getNgayBD() != null) {
//                    stml.setDate(4, new java.sql.Date(km.getNgayBD().getTime()));
//            } else {
//                    stml.setDate(4, null);
//            }
            if (km.getNgayHH() != null) {
                stml.setDate(3, java.sql.Date.valueOf(km.getNgayHH()));
            } else {
                stml.setNull(3, java.sql.Types.DATE);
            }
            if (km.getNgayBD() != null) {
                stml.setDate(4, java.sql.Date.valueOf(km.getNgayBD()));
            } else {
                stml.setNull(4, java.sql.Types.DATE);
            }
            stml.setInt(5, km.getSoLuong());
            stml.setString(6, km.getMaKM());

            n = stml.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Xong
    public boolean suaKhuyenMai(KhuyenMai km) throws SQLException {
        int n = 0;
        con = ConnectDB.getConnection();
        String sql = "update KhuyenMai set TenKM = ?, GiamGia = ?, NgayHH = ?, NgayBD = ?, SoLuong = ?, LoaiKM = ? where MaKM = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, km.getTenKM());
        ps.setInt(2, km.getGiamGia());
        ps.setDate(3, Date.valueOf(km.getNgayHH()));
        ps.setDate(4, Date.valueOf(km.getNgayBD()));
        ps.setInt(5, km.getSoLuong());
        ps.setString(6, km.getLoaiKM());
        ps.setString(7, km.getMaKM());
        n = ps.executeUpdate();
        if (n == 0) {
            return false;
        }
        return true;
    }

    // Xong
    public Object[] timKhuyenMaiTheoMa(String maKM, String loaiKM, String trangThaiKM) throws SQLException {
        con = ConnectDB.getConnection();
        Object ob[] = null;
        ResultSet rs = null;
        if (loaiKM.trim().length() > 0 && trangThaiKM.trim().length() > 0) {
            System.out.println("1");
            trangThaiKM = trangThaiKM.equals("Còn hạn") ? "ngayHH > GetDate()" : "ngayHH < GetDate()";

            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM\n"
                    + "FROM KhuyenMai KM WHERE maKM = ? AND loaiKM = ? " + trangThaiKM;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKM);
            ps.setString(2, loaiKM);
            rs = ps.executeQuery();
        } else if (loaiKM.trim().length() == 0 && trangThaiKM.trim().length() == 0) {
            System.out.println("2");
            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE maKM = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKM);
            rs = ps.executeQuery();
        } else if (loaiKM.trim().length() > 0) {
            System.out.println("3");
            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE maKM = ? AND loaiKM = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKM);
            ps.setString(2, loaiKM);
            rs = ps.executeQuery();
        } else if (trangThaiKM.trim().length() > 0) {
            System.out.println("4");
            trangThaiKM = trangThaiKM.equals("Còn hạn") ? "ngayHH > GetDate()" : "ngayHH < GetDate()";

            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE maKM = ? AND " + trangThaiKM;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKM);
            rs = ps.executeQuery();
        };

        while (rs.next()) {
            String maKM1 = rs.getString(1);
            String tenKM = rs.getString(2);
            String giamGia = rs.getString(3);
            String soLuong = rs.getString(4);
            String ngayBD = rs.getString(5);
            String ngayHH = rs.getString(6);
            String loaiKM1 = rs.getString(7).equals("HoaDon") == true ? "Hóa Đơn" : "Món Ăn";
            ob = new Object[]{maKM1, tenKM, giamGia, soLuong, ngayBD, ngayHH, loaiKM1};
        }

        return ob;
    }

    ;

    // Xong
    public ArrayList<Object[]> timKiemKhuyenMaiTheoTen(String tenKM, String loaiKM, String trangThaiKM, String sortKey, String sortValue) throws SQLException {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        ResultSet rs = null;

        if (loaiKM.trim().length() > 0 && trangThaiKM.trim().length() > 0 && sortKey.trim().length() > 0) {
            trangThaiKM = trangThaiKM.equals("Còn hạn") ? "ngayHH > GetDate()" : "ngayHH < GetDate()";

            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE tenKM COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND loaiKM = ? AND " + trangThaiKM
                    + " ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKM);
            ps.setString(2, loaiKM);
            rs = ps.executeQuery();
        } else if (loaiKM.trim().length() == 0 && trangThaiKM.trim().length() == 0 && sortKey.trim().length() == 0) {
            System.out.println("2");
            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE tenKM COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKM);
            rs = ps.executeQuery();
        } else if (loaiKM.trim().length() > 0 && trangThaiKM.trim().length() > 0) {
            System.out.println("3");
            trangThaiKM = trangThaiKM.equals("Còn hạn") ? "ngayHH > GetDate()" : "ngayHH < GetDate()";
            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE tenKM COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND loaiKM = ? AND " + trangThaiKM;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKM);
            ps.setString(2, loaiKM);
            rs = ps.executeQuery();
        } else if (loaiKM.trim().length() > 0 && sortKey.trim().length() > 0) {
            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE tenKM COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND loaiKM = ?"
                    + " ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKM);
            ps.setString(2, loaiKM);
            rs = ps.executeQuery();
        } else if (trangThaiKM.trim().length() > 0 && sortKey.trim().length() > 0) {
            trangThaiKM = trangThaiKM.equals("Còn hạn") ? "ngayHH > GetDate()" : "ngayHH < GetDate()";
            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE tenKM COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND " + trangThaiKM
                    + " ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKM);
            rs = ps.executeQuery();
        } else if (loaiKM.trim().length() > 0) {
            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE tenKM COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND loaiKM = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKM);
            ps.setString(2, loaiKM);
            rs = ps.executeQuery();
        } else if (trangThaiKM.trim().length() > 0) {
            trangThaiKM = trangThaiKM.equals("Còn hạn") ? "ngayHH > GetDate()" : "ngayHH < GetDate()";
            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE tenKM COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND " + trangThaiKM;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKM);
            rs = ps.executeQuery();
        } else if (sortKey.trim().length() > 0) {
            System.out.println("7");
            String sql = "SELECT KM.maKM,KM.tenKM,KM.giamGia,KM.soLuong,FORMAT(ngayBD,'dd/MM/yyyy'),FORMAT(ngayHH,'dd/MM/yyyy'),loaiKM"
                    + " FROM KhuyenMai KM WHERE tenKM COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') "
                    + " ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKM);
            rs = ps.executeQuery();
        }

        while (rs.next()) {
            String maKM = rs.getString(1);
            String tenKM1 = rs.getString(2);
            String giamGia = rs.getString(3);
            String soLuong = rs.getString(4);
            String ngayBD = rs.getString(5);
            String ngayHH = rs.getString(6);
           String loaiKM1 = rs.getString(7).equals("HoaDon") == true ? "Hóa Đơn" : "Món Ăn";
            Object ob[] = new Object[]{maKM, tenKM1, giamGia, soLuong, ngayBD, ngayHH, loaiKM1};
            list.add(ob);
        }

        return list;
    }

}
