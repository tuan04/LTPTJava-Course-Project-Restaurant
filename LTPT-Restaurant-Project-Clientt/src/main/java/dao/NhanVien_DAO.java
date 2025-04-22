/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.LoaiNhanVien;
import entity.NhanVien;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author THANHTRI
 */
public class NhanVien_DAO {

    private Connection con = null;

    public boolean CapNhatNhanVienChiTiet(NhanVien nv) {
        con = ConnectDB.getConnection();
        PreparedStatement stml = null;
        int n = 0;
        try {
            stml = con.prepareStatement("update NhanVien set hoTenNV=?, CCCD=?, soDienThoai=?, trangThai=?, ngaySinh=?, gioiTinh=?, email=? where maNV=?");
            stml.setString(1, nv.getHoTenNV());
            stml.setString(2, nv.getCCCD());
            stml.setString(3, nv.getSoDienThoai());
            stml.setBoolean(4, nv.isTrangThai());
            if (nv.getNgaySinh() != null) {
                stml.setDate(5, java.sql.Date.valueOf(nv.getNgaySinh()));
            } else {
                stml.setNull(5, java.sql.Types.DATE);
            }
            stml.setBoolean(6, nv.isGioiTinh());
            stml.setString(7, nv.getEmail());
            stml.setString(8, nv.getMaNV());

            n = stml.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    
    public NhanVien dangNhap(String maNV, String matKhau) {
        con = ConnectDB.getConnection();
        NhanVien x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ? AND matKhau = ?");
            ps.setString(1, maNV);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ma = rs.getString("maNV");
                String tenNV = rs.getString("hoTenNV");
                String CCCD = rs.getString("CCCD");
                String email = rs.getString("email");
                String soDienThoai = rs.getString("soDienThoai");
                boolean trangThai = rs.getBoolean("trangThai");
                String maLoaiNhanVien = rs.getString("maLoaiNV");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                x = new NhanVien(ma, tenNV, CCCD, soDienThoai, matKhau, trangThai, new LoaiNhanVien(maLoaiNhanVien), ngaySinh, gioiTinh, email);
            }
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }

    public ArrayList<NhanVien> TimTheoSDT(String sdt) {
        ArrayList<NhanVien> list = new ArrayList<NhanVien>();
        con = ConnectDB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from NhanVien where soDienThoai = ? AND maNV != 'AD001'");
            ps.setString(1, sdt);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String CCCD = rs.getString(3);
                String soDienThoai = rs.getString(4);
                String matKhau = rs.getString(5);
                boolean trangThai = rs.getBoolean(6);
                String maLoaiNhanVien = rs.getString(7);
                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                boolean gioiTinh = rs.getBoolean(9);
                String maXacThuc = rs.getString(10);
                String email = rs.getString(11);
                LoaiNhanVien loai = new LoaiNhanVien(maLoaiNhanVien);

                list.add(new NhanVien(maNV, tenNV, CCCD, soDienThoai, matKhau, trangThai, loai, ngaySinh, gioiTinh, maXacThuc, email));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public NhanVien getNV(String maNV) {
        con = ConnectDB.getConnection();
        NhanVien x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ?");
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ma = rs.getString("maNV");
                String tenNV = rs.getString("hoTenNV");
                String CCCD = rs.getString("CCCD");
                String email = rs.getString("email");
                String soDienThoai = rs.getString("soDienThoai");
                String matKhau = rs.getString("matKhau");
                boolean trangThai = rs.getBoolean("trangThai");
                String maLoaiNhanVien = rs.getString("maLoaiNV");
                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                x = new NhanVien(ma, tenNV, CCCD, soDienThoai, matKhau, trangThai, new LoaiNhanVien(maLoaiNhanVien), ngaySinh, gioiTinh, email);
            }
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }
    
//    public NhanVien getNVADMIN(String maNV) {
//        con = ConnectDB.getConnection();
//        NhanVien x = null;
//        try {
//            PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ? ");
//            ps.setString(1, maNV);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                String ma = rs.getString("maNV");
//                String tenNV = rs.getString("hoTenNV");
//                String CCCD = rs.getString("CCCD");
//                String email = rs.getString("email");
//                String soDienThoai = rs.getString("soDienThoai");
//                String matKhau = rs.getString("matKhau");
//                boolean trangThai = rs.getBoolean("trangThai");
//                String maLoaiNhanVien = rs.getString("maLoaiNV");
//                LocalDate ngaySinh = rs.getDate("ngaySinh").toLocalDate();
//                boolean gioiTinh = rs.getBoolean("gioiTinh");
//                x = new NhanVien(ma, tenNV, CCCD, soDienThoai, matKhau, trangThai, new LoaiNhanVien(maLoaiNhanVien), ngaySinh, gioiTinh, email);
//            }
//            return x;
//        } catch (SQLException e) {
//            System.out.println("dao.NhanVien_DAO.getNVADMIN()");
//            e.printStackTrace();
//        }
//        return x;
//    }

    public ArrayList<NhanVien> getList() {
        ArrayList<NhanVien> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        PreparedStatement ps = null;

        String sql = "SELECT * FROM NhanVien NV \n" +
                     "JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n"
                + "WHERE NV.maLoaiNV != 'LNV4'";
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String CCCD = rs.getString(3);
                String soDienThoai = rs.getString(4);
                String matKhau = rs.getString(5);
                boolean trangThai = rs.getBoolean(6);
                String maLoaiNhanVien = rs.getString(7);
                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                boolean gioiTinh = rs.getBoolean(9);
                String maXacThuc = rs.getString(10);
                String email = rs.getString(11);
                String viTri = rs.getString(13);
                LoaiNhanVien loai = new LoaiNhanVien(maLoaiNhanVien,viTri);

                list.add(new NhanVien(maNV, tenNV, CCCD, soDienThoai, matKhau, trangThai, loai, ngaySinh, gioiTinh, maXacThuc, email));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    //Lọc theo loại
    public ArrayList<NhanVien> getListNVTheoLoai(String maLoaiNV) {
        ArrayList<NhanVien> list = new ArrayList<NhanVien>();
        con = ConnectDB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from NhanVien where maLoaiNV = ?");
            ps.setString(1, maLoaiNV);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String CCCD = rs.getString(3);
                String soDienThoai = rs.getString(4);
                String matKhau = rs.getString(5);
                boolean trangThai = rs.getBoolean(6);
                String maLoaiNhanVien = rs.getString(7);
                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                boolean gioiTinh = rs.getBoolean(9);
                String maXacThuc = rs.getString(10);
                String email = rs.getString(11);
                LoaiNhanVien loai = new LoaiNhanVien(maLoaiNhanVien);

                list.add(new NhanVien(maNV, tenNV, CCCD, soDienThoai, matKhau, trangThai, loai, ngaySinh, gioiTinh, maXacThuc, email));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    //Mã phát sinh
    public String maTuSinh(String loai) {
        String maNV = "";
        String prefix = "NV" + loai;  // "NVLT" cho lễ tân, "NVQL" cho quản lý, "NVTN" cho thu ngân
        con = ConnectDB.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT TOP 1 maNV FROM NhanVien WHERE maNV LIKE ? ORDER BY maNV DESC");
            ps.setString(1, prefix + "%"); // Lọc theo tiền tố loại nhân viên (VD: "NVLT%")
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                maNV = rs.getString("maNV");  // Lấy mã cuối cùng trong loại đã chọn
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Nếu không tìm thấy mã nào, bắt đầu từ "001" cho loại đó
        int soMoi = maNV.isEmpty() ? 1 : Integer.parseInt(maNV.substring(5)) + 1;

        // Tạo mã mới với định dạng 3 chữ số
        if (soMoi < 10) {
            return prefix + "00" + soMoi;
        } else if (soMoi < 100) {
            return prefix + "0" + soMoi;
        } else {
            return prefix + soMoi;
        }
    }
    //Thêm

    public boolean ThemNhanVien(NhanVien e) {
        con = ConnectDB.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try {
            ps = con.prepareStatement("insert into NhanVien (maNV, hoTenNV, CCCD, soDienThoai, matKhau, trangThai, maLoaiNV, ngaySinh, gioiTinh, maXacThuc, email ) values (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, e.getMaNV());
            ps.setString(2, e.getHoTenNV());
            ps.setString(3, e.getCCCD());
            ps.setString(4, e.getSoDienThoai());
            ps.setString(5, e.getMatKhau());
            ps.setBoolean(6, e.isTrangThai());
            ps.setString(7, e.getLoaiNhanVien().getMaLoaiNV());
            if (e.getNgaySinh() != null) {
                ps.setDate(8, java.sql.Date.valueOf(e.getNgaySinh()));
            } else {
                ps.setNull(8, java.sql.Types.DATE);
            }
            ps.setBoolean(9, e.isGioiTinh());
            ps.setString(10, e.getMaXacThuc());
            ps.setString(11, e.getEmail());

            n = ps.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return n > 0;
    }


    //Tìm theo tên
    public ArrayList<NhanVien> TimTheoTenNV(String ten) {
        ArrayList<NhanVien> list = new ArrayList<NhanVien>();
        con = ConnectDB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from NhanVien where hoTenNV COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%')");
            ps.setString(1, ten);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String CCCD = rs.getString(3);
                String soDienThoai = rs.getString(4);
                String matKhau = rs.getString(5);
                boolean trangThai = rs.getBoolean(6);
                String maLoaiNhanVien = rs.getString(7);
                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                boolean gioiTinh = rs.getBoolean(9);
                String maXacThuc = rs.getString(10);
                String email = rs.getString(11);
                LoaiNhanVien loai = new LoaiNhanVien(maLoaiNhanVien);

                list.add(new NhanVien(maNV, tenNV, CCCD, soDienThoai, matKhau, trangThai, loai, ngaySinh, gioiTinh, maXacThuc, email));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    // Cập nhật
    public boolean CapNhatNhanVien(NhanVien nv) {
        con = ConnectDB.getConnection();
        PreparedStatement stml = null;
        int n = 0;
        try {
            stml = con.prepareStatement("update NhanVien set hoTenNV=?, CCCD=?, soDienThoai=?, trangThai=?, ngaySinh=?, gioiTinh=?, email=? where maNV=?");
            stml.setString(1, nv.getHoTenNV());
            stml.setString(2, nv.getCCCD());
            stml.setString(3, nv.getSoDienThoai());
            stml.setBoolean(4, nv.isTrangThai());
            if (nv.getNgaySinh() != null) {
                stml.setDate(5, java.sql.Date.valueOf(nv.getNgaySinh()));
            } else {
                stml.setNull(5, java.sql.Types.DATE);
            }
            stml.setBoolean(6, nv.isGioiTinh());
            stml.setString(7, nv.getEmail());
            stml.setString(8, nv.getMaNV());

            n = stml.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }

    // Lọc trạng thái
    public ArrayList<NhanVien> getListNVTheoTrangThai(String trangthai) {
        ArrayList<NhanVien> list = new ArrayList<NhanVien>();
        con = ConnectDB.getConnection();
        LoaiNhanVien emp = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from NhanVien where trangThai = ?");
            ps.setString(1, trangthai);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNV = rs.getString(1);
                String tenNV = rs.getString(2);
                String CCCD = rs.getString(3);
                String soDienThoai = rs.getString(4);
                String matKhau = rs.getString(5);
                boolean trangThai = rs.getBoolean(6);
                String maLoaiNhanVien = rs.getString(7);
                LocalDate ngaySinh = rs.getDate(8).toLocalDate();
                boolean gioiTinh = rs.getBoolean(9);
                String maXacThuc = rs.getString(10);
                String email = rs.getString(11);
                LoaiNhanVien loai = new LoaiNhanVien(maLoaiNhanVien);

                list.add(new NhanVien(maNV, tenNV, CCCD, soDienThoai, matKhau, trangThai, loai, ngaySinh, gioiTinh, maXacThuc, email));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public boolean checkOTP(String maNV, String email, String otp) {
        con = ConnectDB.getConnection();
        boolean check = false;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ? AND email = ? AND maXacThuc = ? ");
            ps.setString(1, maNV);
            ps.setString(2, email);
            ps.setString(3, otp);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    public boolean updatePassword(String maNV, String pass) {
        con = ConnectDB.getConnection();
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE NhanVien SET matKhau = ? WHERE maNV = ?");
            ps.setString(1, pass);
            ps.setString(2, maNV);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return n > 0;
    }

    public String getOldPass(String maNV) {
        con = ConnectDB.getConnection();
        String pass = "";
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ?");
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pass += rs.getString("matKhau");
            }
            return pass;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pass;
    }

    public boolean checkEmail(String maNV, String email) {
        con = ConnectDB.getConnection();
        boolean check = false;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ? AND email = ?");
            ps.setString(1, maNV);
            ps.setString(2, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            }
            return check;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    public boolean checkMaNV(String maNV) {
        con = ConnectDB.getConnection();
        boolean check = false;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM NhanVien WHERE maNV = ?");
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                check = true;
            }
            return check;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }

    public String generateOTP(int length) {
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }

    public boolean updateOTP(String maNV, String otp) {
        con = ConnectDB.getConnection();
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE NhanVien SET maXacThuc = ? WHERE maNV = ?");
            ps.setString(1, otp);
            ps.setString(2, maNV);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return n > 0;
    }

    public boolean deleteOTP(String maNV) {
        con = ConnectDB.getConnection();
        int n = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE NhanVien SET maXacThuc = NULL WHERE maNV = ?");
            ps.setString(1, maNV);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return n > 0;
    }
    
    public Object[] timMonAnTheoMa(String maNV,String loaiNV,String trangThaiNV) throws SQLException {
        con = ConnectDB.getConnection();
        Object ob[] = null;
        ResultSet rs = null;
        if(loaiNV.trim().length() > 0 && trangThaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0)\n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV\n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE   NV.maNV = ? AND LNV.viTri = ? AND NV.trangThai = ?\n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,maNV);
            ps.setString(2,loaiNV);
            ps.setString(3,trangThaiNV);
            rs = ps.executeQuery();
        }
        else if(loaiNV.trim().length() == 0 && trangThaiNV.trim().length() == 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0)\n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV\n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE   NV.maNV = ? \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,maNV);
            rs = ps.executeQuery();
        }
        else if(loaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0)\n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV\n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE   NV.maNV = ? AND LNV.viTri = ?\n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,maNV);
            ps.setString(2,loaiNV);
            rs = ps.executeQuery();
        }
        else if(trangThaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0)\n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV\n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE   NV.maNV = ? AND  NV.trangThai = ?\n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,maNV);
            ps.setString(2,trangThaiNV);
            rs = ps.executeQuery();
        };

        while (rs.next()){
            String maNV1 = rs.getString(1);
            String tenNV = rs.getString(2);
            String viTri = rs.getString(3);
            String CCCD = rs.getString(4);
            String soDienThoai = rs.getString(5);
            String ngaySinh = rs.getString(6);
            String gioiTinh = rs.getInt(7) == 1 ? "Nam" : "Nữ";
            String trangThai = rs.getInt(8) == 1 ? "Đang hoạt động" : "Ngưng hoạt động";
            float doanhThu = rs.getFloat(9)  +  rs.getFloat(10);
            ob = new Object[]{maNV1,tenNV,viTri,CCCD,soDienThoai,ngaySinh,gioiTinh,doanhThu,trangThai};
        }
        return ob;
    };

    // Xong
    public Object[] timMonAnTheoCCCD(String CCCD,String loaiNV,String trangThaiNV) throws SQLException {
        con = ConnectDB.getConnection();
        Object ob[] = null;
        ResultSet rs = null;
        if(loaiNV.trim().length() > 0 && trangThaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV \n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.CCCD = ? AND LNV.viTri = ? AND NV.trangThai = ?\n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,CCCD);
            ps.setString(2,loaiNV);
            ps.setString(3,trangThaiNV);
            rs = ps.executeQuery();
        }
        else if(loaiNV.trim().length() == 0 && trangThaiNV.trim().length() == 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV \n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.CCCD = ? \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,CCCD);
            rs = ps.executeQuery();
        }
        else if(loaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV \n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.CCCD = ? AND LNV.viTri = ? \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,CCCD);
            ps.setString(2,loaiNV);
            rs = ps.executeQuery();
        }
        else if(trangThaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV \n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.CCCD = ?  AND NV.trangThai = ?\n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,CCCD);
            ps.setString(2,trangThaiNV);
            rs = ps.executeQuery();
        };

        while (rs.next()){
            String maNV = rs.getString(1);
            String tenNV = rs.getString(2);
            String viTri = rs.getString(3);
            String CCCD1 = rs.getString(4);
            String soDienThoai = rs.getString(5);
            String ngaySinh = rs.getString(6);
            String gioiTinh = rs.getInt(7) == 1 ? "Nam" : "Nữ";
            String trangThai = rs.getInt(8) == 1 ? "Đang hoạt động" : "Ngưng hoạt động";
            float doanhThu = rs.getFloat(9)  +  rs.getFloat(10);
            ob = new Object[]{maNV,tenNV,viTri,CCCD1,soDienThoai,ngaySinh,gioiTinh,doanhThu,trangThai};
        }
        return ob;
    };

    // Xong
    public Object[] timMonAnTheoSDT(String std,String loaiNV,String trangThaiNV) throws SQLException {
        con = ConnectDB.getConnection();
        Object ob[] = null;
        ResultSet rs = null;
        if(loaiNV.trim().length() > 0 && trangThaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV \n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.soDienThoai = ? AND LNV.viTri = ? AND NV.trangThai = ?\n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,std);
            ps.setString(2,loaiNV);
            ps.setString(3,trangThaiNV);
            rs = ps.executeQuery();
        }
        else if(loaiNV.trim().length() == 0 && trangThaiNV.trim().length() == 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV \n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.soDienThoai = ? \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,std);
            rs = ps.executeQuery();
        }
        else if(loaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV \n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.soDienThoai = ? AND LNV.viTri = ? \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,std);
            ps.setString(2,loaiNV);
            rs = ps.executeQuery();
        }
        else if(trangThaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV \n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.soDienThoai = ? AND NV.trangThai = ?\n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,std);
            ps.setString(2,trangThaiNV);
            rs = ps.executeQuery();
        };

        while (rs.next()){
            String maNV = rs.getString(1);
            String tenNV = rs.getString(2);
            String viTri = rs.getString(3);
            String CCCD1 = rs.getString(4);
            String soDienThoai = rs.getString(5);
            String ngaySinh = rs.getString(6);
            String gioiTinh = rs.getInt(7) == 1 ? "Nam" : "Nữ";
            String trangThai = rs.getInt(8) == 1 ? "Đang hoạt động" : "Ngưng hoạt động";
            float doanhThu = rs.getFloat(9)  +  rs.getFloat(10);
            ob = new Object[]{maNV,tenNV,viTri,CCCD1,soDienThoai,ngaySinh,gioiTinh,doanhThu,trangThai};
        }
        return ob;
    };

    // Xong
    public ArrayList<Object[]> timKiemMonAnTheoTen(String tenNV, String loaiNV, String trangThaiNV, String sortKey, String sortValue) throws SQLException {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        ResultSet rs = null;

        if(loaiNV.trim().length() > 0 && trangThaiNV.trim().length() > 0 && sortKey.trim().length() > 0) {
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.hoTenNV COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND LNV.viTri = ? AND NV.trangThai = ? \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai ORDER BY  " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tenNV);
            ps.setString(2,loaiNV);
            ps.setString(3,trangThaiNV);
            rs = ps.executeQuery();
        }
        else if(loaiNV.trim().length() == 0 && trangThaiNV.trim().length() == 0 && sortKey.trim().length() == 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.hoTenNV COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tenNV);
            rs = ps.executeQuery();
        }
        else if(loaiNV.trim().length() > 0 && trangThaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.hoTenNV COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND LNV.viTri = ? AND NV.trangThai = ? \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tenNV);
            ps.setString(2,loaiNV);
            ps.setString(3,trangThaiNV);
            rs = ps.executeQuery();
        }
        else if(loaiNV.trim().length() > 0 && sortKey.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.hoTenNV COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND LNV.viTri = ? \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai ORDER BY  " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tenNV);
            ps.setString(2,loaiNV);
            rs = ps.executeQuery();
        }
        else if(trangThaiNV.trim().length() > 0 && sortKey.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.tenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) " +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV " +
                    "LEFT JOIN Hoa_Don HD ON NV.maNV = HD.maNhanVien " +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNhanVien = NV.maNV WHERE NV.tenNV COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND NV.trangThai = ? " +
                    "GROUP BY HD.maNhanVien,DDB.maNhanVien,NV.maNV,NV.tenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai ORDER BY  " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tenNV);
            ps.setString(2,trangThaiNV);
            rs = ps.executeQuery();
        }
        else if(loaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.hoTenNV COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND LNV.viTri = ?  \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tenNV);
            ps.setString(2,loaiNV);
            rs = ps.executeQuery();
        }
        else if(trangThaiNV.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.hoTenNV COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') AND NV.trangThai = ? \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tenNV);
            ps.setString(2,trangThaiNV);
            rs = ps.executeQuery();
        }
        else if(sortKey.trim().length() > 0){
            String sql = "SELECT NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,FORMAT(NV.ngaySinh , 'dd/MM/yyyy'),NV.gioiTinh,NV.trangThai,COALESCE(SUM(HD.tongTien),0),COALESCE(SUM(DDB.tienCoc),0) \n" +
                    "FROM NhanVien NV JOIN LoaiNhanVien LNV ON NV.maLoaiNV = LNV.maLoaiNV \n" +
                    "LEFT JOIN HoaDon HD ON NV.maNV = HD.maNV\n" +
                    "LEFT JOIN DonDatBan DDB ON DDB.maNV = NV.maNV WHERE NV.hoTenNV COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') \n" +
                    "GROUP BY HD.maNV,DDB.maNV,NV.maNV,NV.hoTenNV,LNV.viTri,NV.CCCD,NV.soDienThoai,NV.ngaySinh,NV.gioiTinh,NV.trangThai ORDER BY  " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,tenNV);
            rs = ps.executeQuery();
        }



        while (rs.next()){
            String maNV = rs.getString(1);
            String tenNV1 = rs.getString(2);
            String viTri = rs.getString(3);
            String CCCD1 = rs.getString(4);
            String soDienThoai = rs.getString(5);
            String ngaySinh = rs.getString(6);
            String gioiTinh = rs.getInt(7) == 1 ? "Nam" : "Nữ";
            String trangThai = rs.getInt(8) == 1 ? "Đang hoạt động" : "Ngưng hoạt động";
            float doanhThu = rs.getFloat(9)  +  rs.getFloat(10);
            Object  ob[] = new Object[]{maNV,tenNV1,viTri,CCCD1,soDienThoai,ngaySinh,gioiTinh,doanhThu,trangThai};
            list.add(ob);
        }
        return list;
    };


}
