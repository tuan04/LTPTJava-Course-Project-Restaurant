/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.LoaiKhachHang;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author THANHTRI
 */
public class KhachHang_DAO {

    private Connection con = null;
    
    public void updateDiemTL(){
         con = ConnectDB.getConnection();
         try {
            String sql = "UPDATE KhachHang\n" +
                         "SET diemTL = 0\n" +
                         "WHERE FORMAT(ngayTao,'dd/MM') = FORMAT(GETDATE(),'dd/MM')";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public KhachHang getKHSDT(String sdt) {
        con = ConnectDB.getConnection();
        KhachHang kh = null;
        try {
            PreparedStatement ps = con.prepareStatement("select * from KhachHang where soDienThoai = ? and trangThai = 1");
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String email = rs.getString(4);
                int diemTL = rs.getInt(5);
                boolean trangThai = rs.getBoolean(6);
                String maLoai = rs.getString(7);
                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                LocalDate ngayTao = rs.getDate(9).toLocalDate();

                LoaiKhachHang loai = new LoaiKhachHang(maLoai);

                kh = new KhachHang(maKH, tenKH, soDienThoai, email, diemTL, trangThai, loai, ngaySinh, ngayTao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }

    public ArrayList<KhachHang> getList() {
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        PreparedStatement ps = null;
        con = ConnectDB.getConnection();
        String sql = "SELECT * FROM KhachHang KH\n"
                + "JOIN LoaiKhachHang LKH ON KH.maLoaiKH = LKH.maLoaiKH";
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String email = rs.getString(4);
                int diemTL = rs.getInt(5);
                boolean trangThai = rs.getBoolean(6);
                String maLoaiKH = rs.getString(7);

                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                LocalDate ngayTao = rs.getDate(9).toLocalDate();

                LoaiKhachHang loai = new LoaiKhachHang(maLoaiKH, rs.getString("tenLoaiKH"));

                list.add(new KhachHang(maKH, tenKH, soDienThoai, email, diemTL, trangThai, loai, ngaySinh, ngayTao));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    // Lọc theo loại

    public ArrayList<KhachHang> getListKHTheoLoai(String maLoaiKH) {
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        PreparedStatement ps = null;
        con = ConnectDB.getConnection();
        try {
            // Truy vấn có JOIN để lấy tên loại khách hàng từ bảng LoaiKhachHang
            ps = con.prepareStatement(
                    "SELECT KhachHang.*, LoaiKhachHang.tenLoaiKH "
                    + "FROM KhachHang "
                    + "JOIN LoaiKhachHang ON KhachHang.maLoaiKH = LoaiKhachHang.maLoaiKH "
                    + "WHERE KhachHang.maLoaiKH = ?"
            );
            ps.setString(1, maLoaiKH);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String email = rs.getString(4);
                int diemTL = rs.getInt(5);
                boolean trangThai = rs.getBoolean(6);
                String loaiKH = rs.getString(7);
                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                LocalDate ngayTao = rs.getDate(9).toLocalDate();

                // Tạo đối tượng LoaiKhachHang với mã và tên loại
                LoaiKhachHang loai = new LoaiKhachHang(loaiKH, rs.getString("tenLoaiKH"));

                // Thêm đối tượng KhachHang vào danh sách
                list.add(new KhachHang(maKH, tenKH, soDienThoai, email, diemTL, trangThai, loai, ngaySinh, ngayTao));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Mã Phát Sinh
    public String maTuSinh() {
        con = ConnectDB.getConnection();
        String maKH = "";
        try {
            PreparedStatement ps = con.prepareStatement("select top 1 maKH from KhachHang order by maKH desc ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maKH = rs.getString("maKH");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (maKH.isEmpty()) {
            return "KH000001";
        }
        //KH003
        int soMoi = Integer.parseInt(maKH.substring(2)) + 1; //004
        if (soMoi < 10) {
            return "KH00000" + soMoi;//4 
        } else if (soMoi < 100) {
            return "KH0000" + soMoi; //11-99
        } else {
            return "KH000" + soMoi;//999
        }
    }
    //Thêm

    public boolean ThemKhachHang(KhachHang e) {
        con = ConnectDB.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("insert into KhachHang (maKH, tenKH, soDienThoai, email, diemTL, trangThai, maLoaiKH, ngaySinh, ngayTao) values (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, e.getMaKH());
            ps.setString(2, e.getTenKH());
            ps.setString(3, e.getSoDienThoai());
            ps.setString(4, e.getEmail());
            ps.setInt(5, e.getDiemTL());
            ps.setBoolean(6, e.isTrangThai());
            ps.setString(7, e.getLoaiKhachHang().getMaLoaiKH());
            if (e.getNgaySinh() != null) {
                ps.setDate(8, java.sql.Date.valueOf(e.getNgaySinh()));
            } else {
                ps.setNull(8, java.sql.Types.DATE); // Đặt là null nếu không có giá trị
            }
            if (e.getNgayTao() != null) {
                ps.setDate(9, java.sql.Date.valueOf(e.getNgayTao()));
            } else {
                ps.setNull(9, java.sql.Types.DATE); // Đặt là null nếu không có giá trị
            }
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return n > 0;
    }

    //Tìm theo Số điện thoại
    public ArrayList<KhachHang> TimTheoSDT(String sdt) {
        con = ConnectDB.getConnection();
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from KhachHang where soDienThoai COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')");
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String email = rs.getString(4);
                int diemTL = rs.getInt(5);
                boolean trangThai = rs.getBoolean(6);
                String loaiKH = rs.getString(7);
                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                LocalDate ngayTao = rs.getDate(9).toLocalDate();
                LoaiKhachHang loai = new LoaiKhachHang(loaiKH);

                list.add(new KhachHang(maKH, tenKH, soDienThoai, email, diemTL, trangThai, loai, ngaySinh, ngayTao));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    //Tìm Theo Mã
    public KhachHang TimTheoMa(String ma) {
        KhachHang emp = null;
        PreparedStatement ps = null;
        con = ConnectDB.getConnection();
        try {
            ps = con.prepareStatement("SELECT KhachHang.*, LoaiKhachHang.tenLoaiKH "
                    + "FROM KhachHang "
                    + "JOIN LoaiKhachHang ON KhachHang.maLoaiKH = LoaiKhachHang.maLoaiKH "
                    + "WHERE KhachHang.maKH = ?");
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String email = rs.getString(4);
                int diemTL = rs.getInt(5);
                boolean trangThai = rs.getBoolean(6);
                String loaiKH = rs.getString(7);

                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                LocalDate ngayTao = rs.getDate(9).toLocalDate();
                LoaiKhachHang loai = new LoaiKhachHang(loaiKH, rs.getString("tenLoaiKH"));

                emp = new KhachHang(maKH, tenKH, soDienThoai, email, diemTL, trangThai, loai, ngaySinh, ngayTao);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return emp;
    }
    // Cập nhật

    public boolean CapNhatKhachHang(KhachHang kh) {
        con = ConnectDB.getConnection();
        PreparedStatement stml = null;
        int n = 0;
        try {
            stml = con.prepareStatement("update KhachHang set tenKH=?, soDienThoai=?, email=?, trangThai=?, ngaySinh=? where maKH=?");
            stml.setString(1, kh.getTenKH());
            stml.setString(2, kh.getSoDienThoai());
            stml.setString(3, kh.getEmail());
            stml.setBoolean(4, kh.isTrangThai());
            if (kh.getNgaySinh() != null) {
                stml.setDate(5, java.sql.Date.valueOf(kh.getNgaySinh()));
            } else {
                stml.setNull(5, java.sql.Types.DATE);
            }
            stml.setString(6, kh.getMaKH());
            n = stml.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    //
    // Lọc trạng thái
    public ArrayList<KhachHang> getListKHTheoTrangThai(String trangthai) {
        ArrayList<KhachHang> list = new ArrayList<KhachHang>();
        PreparedStatement ps = null;
        con = ConnectDB.getConnection();
        try {
            ps = con.prepareStatement(
                    "SELECT KhachHang.*, LoaiKhachHang.tenLoaiKH "
                    + "FROM KhachHang "
                    + "JOIN LoaiKhachHang ON KhachHang.maLoaiKH = LoaiKhachHang.maLoaiKH "
                    + "WHERE KhachHang.trangThai = ?"
            );
            ps.setString(1, trangthai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                String soDienThoai = rs.getString(3);
                String email = rs.getString(4);
                int diemTL = rs.getInt(5);
                boolean trangThai = rs.getBoolean(6);
                String loaiKH = rs.getString(7);
                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                LocalDate ngayTao = rs.getDate(9).toLocalDate();

                // Tạo đối tượng LoaiKhachHang với mã và tên loại
                LoaiKhachHang loai = new LoaiKhachHang(loaiKH, rs.getString("tenLoaiKH"));

                // Thêm đối tượng KhachHang vào danh sách
                list.add(new KhachHang(maKH, tenKH, soDienThoai, email, diemTL, trangThai, loai, ngaySinh, ngayTao));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public KhachHang getKH(String maKH) {
        con = ConnectDB.getConnection();
        KhachHang x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM KhachHang WHERE maKH = ?");
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String mKH = rs.getString("maKH");
                String tenKH = rs.getString("tenKH");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                int diemTL = rs.getInt("diemTL");
                boolean trangThai = rs.getBoolean("trangThai");
                String maLoaiKH = rs.getString("maLoaiKH");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                x = new KhachHang(mKH, tenKH, soDienThoai, email, diemTL, trangThai, new LoaiKhachHang(maLoaiKH), ngaySinh, ngayTao);
            }
            return x;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return x;
    }

    public KhachHang giamGiaNgaySinh(String date) {
        con = ConnectDB.getConnection();
        KhachHang x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM KhachHang WHERE FORMAT(ngaySinh, 'MM-dd') = ?");
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("tenKH");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                int diemTL = rs.getInt("diemTL");
                boolean trangThai = rs.getBoolean("trangThai");
                String maLoaiKH = rs.getString("maLoaiKH");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                LocalDate ngayTao = rs.getDate("ngayTao").toLocalDate();
                x = new KhachHang(maKH, tenKH, soDienThoai, email, diemTL, trangThai, new LoaiKhachHang(maLoaiKH), ngaySinh, ngayTao);
            }
            return x;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return x;
    }

    public Object[] timKHTheoMa(String maKH, String loaiKH, String trangThaiKH) throws SQLException {
        con = ConnectDB.getConnection();
        Object ob[] = null;
        ResultSet rs = null;
        if (loaiKH.trim().length() > 0 && trangThaiKH.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai\n"
                    + "FROM KhachHang WHERE maKH = ? AND maLoaiKH = ? AND trangThai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            ps.setString(2, loaiKH);
            ps.setString(3, trangThaiKH);
            rs = ps.executeQuery();
        } else if (loaiKH.trim().length() == 0 && trangThaiKH.trim().length() == 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE maKH = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            rs = ps.executeQuery();
        } else if (loaiKH.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai \n"
                    + "FROM KhachHang WHERE maKH = ? AND maLoaiKH = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            ps.setString(2, loaiKH);
            rs = ps.executeQuery();
        } else if (trangThaiKH.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai\n"
                    + "FROM KhachHang WHERE maKH = ?  AND trangThai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            ps.setString(2, trangThaiKH);
            rs = ps.executeQuery();
        };

        while (rs.next()) {
            String maKH1 = rs.getString(1);
            String tenNV = rs.getString(2);
            String soDienThoai = rs.getString(3);
            String ngayTao = rs.getString(4);
            String ngaySinh = rs.getString(5);
            String maLoaiKH = rs.getString(6);
            if (maLoaiKH.equals("LKH1")) {
                maLoaiKH = "Number";
            } else if (maLoaiKH.equals("LKH2")) {
                maLoaiKH = "Gold";
            } else if (maLoaiKH.equals("LKH3")) {
                maLoaiKH = "Platinum";
            }
            int diemTL = rs.getInt(7);
            String trangThai = rs.getInt(8) == 1 ? "Đang hoạt động" : "Ngưng hoạt động";
            ob = new Object[]{maKH1, tenNV, soDienThoai, ngayTao, ngaySinh, maLoaiKH, diemTL, trangThai};
        }
        return ob;
    }

    ;


    // Xong
    public Object[] timKHTheoSDT(String std, String loaiKH, String trangThaiKH) throws SQLException {
        con = ConnectDB.getConnection();
        Object ob[] = null;
        ResultSet rs = null;
        if (loaiKH.trim().length() > 0 && trangThaiKH.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai \n"
                    + "FROM KhachHang WHERE soDienThoai = ? AND maLoaiKH = ? AND trangThai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, std);
            ps.setString(2, loaiKH);
            ps.setString(3, trangThaiKH);
            rs = ps.executeQuery();
        } else if (loaiKH.trim().length() == 0 && trangThaiKH.trim().length() == 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE soDienThoai = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, std);
            rs = ps.executeQuery();
        } else if (loaiKH.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE soDienThoai = ? AND maLoaiKH = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, std);
            ps.setString(2, loaiKH);
            rs = ps.executeQuery();
        } else if (trangThaiKH.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE soDienThoai = ?  AND trangThai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, std);
            ps.setString(2, trangThaiKH);
            rs = ps.executeQuery();
        };

        while (rs.next()) {
            String maKH = rs.getString(1);
            String tenNV = rs.getString(2);
            String soDienThoai = rs.getString(3);
            String ngayTao = rs.getString(4);
            String ngaySinh = rs.getString(5);
            String maLoaiKH = rs.getString(6);
            if (maLoaiKH.equals("LKH1")) {
                maLoaiKH = "Number";
            } else if (maLoaiKH.equals("LKH2")) {
                maLoaiKH = "Gold";
            } else if (maLoaiKH.equals("LKH3")) {
                maLoaiKH = "Platinum";
            }
            int diemTL = rs.getInt(7);
            String trangThai = rs.getInt(8) == 1 ? "Đang hoạt động" : "Ngưng hoạt động";
            ob = new Object[]{maKH, tenNV, soDienThoai, ngayTao, ngaySinh, maLoaiKH, diemTL, trangThai};
        }
        return ob;
    }

    ;

    // Xong
    public ArrayList<Object[]> timKiemKHTheoTen(String tenKH, String loaiKH, String trangThaiKH, String sortKey, String sortValue) throws SQLException {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        ResultSet rs = null;

        if (loaiKH.trim().length() > 0 && trangThaiKH.trim().length() > 0 && sortKey.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND maLoaiKH = ? AND trangThai = ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKH);
            ps.setString(2, loaiKH);
            ps.setString(3, trangThaiKH);
            rs = ps.executeQuery();
        } else if (loaiKH.trim().length() == 0 && trangThaiKH.trim().length() == 0 && sortKey.trim().length() == 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')  ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKH);
            rs = ps.executeQuery();
        } else if (loaiKH.trim().length() > 0 && trangThaiKH.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND maLoaiKH = ? AND trangThai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKH);
            ps.setString(2, loaiKH);
            ps.setString(3, trangThaiKH);
            rs = ps.executeQuery();
        } else if (loaiKH.trim().length() > 0 && sortKey.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND maLoaiKH = ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKH);
            ps.setString(2, loaiKH);
            rs = ps.executeQuery();
        } else if (trangThaiKH.trim().length() > 0 && sortKey.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')  AND trangThai = ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKH);
            ps.setString(2, trangThaiKH);
            rs = ps.executeQuery();
        } else if (loaiKH.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND maLoaiKH = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKH);
            ps.setString(2, loaiKH);
            rs = ps.executeQuery();
        } else if (trangThaiKH.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')  AND trangThai = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKH);
            ps.setString(2, trangThaiKH);
            rs = ps.executeQuery();
        } else if (sortKey.trim().length() > 0) {
            String sql = "SELECT maKH,tenKH,soDienThoai,FORMAT(ngayTao,'dd/MM/yyyy'), FORMAT(ngaySinh,'dd/MM/yyyy'),maLoaiKH,diemTL,trangThai "
                    + "FROM KhachHang WHERE tenKH COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenKH);
            rs = ps.executeQuery();
        }

        while (rs.next()) {
            String maKH = rs.getString(1);
            String tenKH1 = rs.getString(2);
            String soDienThoai = rs.getString(3);
            String ngayTao = rs.getString(4);
            String ngaySinh = rs.getString(5);
            String maLoaiKH = rs.getString(6);
            if (maLoaiKH.equals("LKH1")) {
                maLoaiKH = "Number";
            } else if (maLoaiKH.equals("LKH2")) {
                maLoaiKH = "Gold";
            } else if (maLoaiKH.equals("LKH3")) {
                maLoaiKH = "Platinum";
            }
            int diemTL = rs.getInt(7);
            String trangThai = rs.getInt(8) == 1 ? "Đang hoạt động" : "Ngưng hoạt động";
            Object ob[] = new Object[]{maKH, tenKH1, soDienThoai, ngayTao, ngaySinh, maLoaiKH, diemTL, trangThai};
            list.add(ob);
        }
        return list;
    }

    ;

    // Xong
    public String getMaKH(String std) throws SQLException {
        con = ConnectDB.getConnection();

        String sql = "SELECT maKH "
                + "FROM KhachHang WHERE soDienThoai = ? AND trangThai = 1";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, std);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
;

}
