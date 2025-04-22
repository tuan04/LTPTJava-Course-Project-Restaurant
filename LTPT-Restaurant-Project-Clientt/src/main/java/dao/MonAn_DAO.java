/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.KhuyenMai;
import entity.LoaiMonAn;
import entity.MonAn;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author THANHTRI
 */
public class MonAn_DAO {

    private Connection con = null;

    public MonAn getMonAn(String maMA) {
        con = ConnectDB.getConnection();
        MonAn x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM MonAn WHERE maMA = ?");
            ps.setString(1, maMA);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maMon = rs.getString("maMA");
                String tenMA = rs.getString("tenMA");
                String hinhAnh = rs.getString("hinhAnh");
                double gia = rs.getDouble("gia");
                String maLoaiMA = rs.getString("maLoaiMA");
                String maKM = rs.getString("maKM");
                boolean trangThai = rs.getBoolean("trangThai");
                x = new MonAn(maMon, tenMA, hinhAnh, gia, trangThai, new LoaiMonAn(maLoaiMA), new KhuyenMai(maKM));
            }
            return x;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return x;
    }
    
    public ArrayList<MonAn> getMonTheoLoai(String maLoai) {
        ArrayList<MonAn> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("select * from MonAn where maLoaiMA = ?");
            ps.setString(1, maLoai);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString(1);
                String tenMA = rs.getString(2);
                String hinhAnh = rs.getString(3);
                double gia = rs.getDouble(4);
                boolean trangThai = rs.getBoolean(5);
                String maLoaiMA = rs.getString(6);
                String maKM = rs.getString(7);

                list.add(new MonAn(maMA, tenMA, hinhAnh, gia, trangThai, new LoaiMonAn(maLoaiMA), new KhuyenMai(maKM)));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public MonAn getMonAnTheoMa(String maMA) {
        MonAn ma = null;
        con = ConnectDB.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("select * from MonAn where maMA = ?");
            ps.setString(1, maMA);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMa = rs.getString(1);
                String tenMA = rs.getString(2);
                String hinhAnh = rs.getString(3);
                double gia = rs.getDouble(4);
                boolean trangThai = rs.getBoolean(5);
                String maLoai = rs.getString(6);
                String maKM = rs.getString(7);
                ma = new MonAn(maMa, tenMA, hinhAnh, gia, trangThai, new LoaiMonAn(maLoai), new KhuyenMai(maKM));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ma;
    }

    public ArrayList<MonAn> getList() {
        con = ConnectDB.getConnection();
        ArrayList<MonAn> list = new ArrayList<MonAn>();
        PreparedStatement ps = null;
        String sql = "SELECT * FROM MonAn MA\n" +
                     "JOIN LoaiMonAn LMA ON MA.maLoaiMA = LMA.maLoaiMA";
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString(1);
                String tenMA = rs.getString(2);
                String hinhAnh = rs.getString(3);
                float gia = rs.getFloat(4);
                boolean trangThai = rs.getBoolean(5);
                String maLoaiMA = rs.getString(6);
                String maKM = rs.getString(7);
                String tenLoaiMon = rs.getString(9);
                LoaiMonAn loaiMon = new LoaiMonAn(maLoaiMA,tenLoaiMon);
                KhuyenMai km = new KhuyenMai(maKM);
                list.add(new MonAn(maMA, tenMA, hinhAnh, gia, trangThai, loaiMon, km));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    //
    public ArrayList<LoaiMonAn> getListLoai() {
        ArrayList<LoaiMonAn> list = new ArrayList<LoaiMonAn>();
        con = ConnectDB.getConnection();
        PreparedStatement ps = null;

        String sql = "select * from LoaiMonAn";
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiMA = rs.getString(1);
                String tenLoaiMA = rs.getString(2);

                list.add(new LoaiMonAn(maLoaiMA, tenLoaiMA));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    //Mã tự sinh
    public String maTuSinh() {
        String maMA = "";
        try {
            PreparedStatement ps = con.prepareStatement("select top 1 maMA from MonAn order by maMA desc ");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                maMA = rs.getString("maMA");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (maMA.isEmpty()) {
            return "MA001";
        }
        //
        int soMoi = Integer.parseInt(maMA.substring(2)) + 1;
        if (soMoi < 10) {
            return "MA00" + soMoi;//<10 
        } else if (soMoi < 100) {
            return "MA0" + soMoi; //11-99
        } else {
            return "MA" + soMoi;//999
        }
    }

    //Thêm
    public boolean ThemMonAn(MonAn e) {
        PreparedStatement ps = null;
        con = ConnectDB.getConnection();
        int n = 0;
        try {
            ps = con.prepareStatement("insert into MonAn (maMA, tenMA, hinhAnh, gia, trangThai, maLoaiMA, maKM) values (?,?,?,?,?,?,?)");
            ps.setString(1, e.getMaMA());
            ps.setString(2, e.getTenMA());
            ps.setString(3, e.getHinhAnh());
            ps.setDouble(4, e.getGia());

            ps.setBoolean(5, e.isTrangThai());
            ps.setString(6, e.getLoaiMonAn().getMaLoaiMA());
            ps.setString(7, e.getKhuyenMai().getMaKM());
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        return n > 0;
    }
    public ArrayList<MonAn> getListMonTheoLoai(String maLoaiMon) {
        ArrayList<MonAn> list = new ArrayList<MonAn>();
        PreparedStatement ps = null;
        con = ConnectDB.getConnection();
        try {
            ps = con.prepareStatement("SELECT MonAn.*, LoaiMonAn.tenLoaiMA FROM MonAn JOIN LoaiMonAn ON MonAn.maLoaiMA = LoaiMonAn.maLoaiMA WHERE MonAn.maLoaiMA = ?");
            ps.setString(1, maLoaiMon);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString(1);
                String tenMA = rs.getString(2);
                String hinhAnh = rs.getString(3);
                float gia = rs.getFloat(4);
                boolean trangThai = rs.getBoolean(5);
                String maLoaiMA = rs.getString(6);
                String maKM = rs.getString(7);

                LoaiMonAn loaiMon = new LoaiMonAn(maLoaiMA, rs.getString("tenLoaiMA"));

                KhuyenMai km = new KhuyenMai(maKM);

                list.add(new MonAn(maMA, tenMA, hinhAnh, gia, trangThai, loaiMon, km));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    //
    public ArrayList<MonAn> getListMonTheoTrangThai(String trangthai) {
        ArrayList<MonAn> list = new ArrayList<MonAn>();
        con = ConnectDB.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT MonAn.*, LoaiMonAn.tenLoaiMA FROM MonAn JOIN LoaiMonAn ON MonAn.maLoaiMA = LoaiMonAn.maLoaiMA WHERE MonAn.trangThai = ?");
            ps.setString(1, trangthai);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString(1);
                String tenMA = rs.getString(2);
                String hinhAnh = rs.getString(3);
                float gia = rs.getFloat(4);
                boolean trangThai = rs.getBoolean(5);
                String maLoaiMA = rs.getString(6);
                String maKM = rs.getString(7);

                LoaiMonAn loaiMon = new LoaiMonAn(maLoaiMA, rs.getString("tenLoaiMA"));

                KhuyenMai km = new KhuyenMai(maKM);

                list.add(new MonAn(maMA, tenMA, hinhAnh, gia, trangThai, loaiMon, km));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    //Tìm theo tên
    public ArrayList<MonAn> TimTheoTenMon(String ten) {
        ArrayList<MonAn> list = new ArrayList<MonAn>();
        con = ConnectDB.getConnection();
        MonAn emp = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT MonAn.*, LoaiMonAn.tenLoaiMA FROM MonAn JOIN LoaiMonAn ON MonAn.maLoaiMA = LoaiMonAn.maLoaiMA WHERE tenMA COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT('%', ?, '%')");
            ps.setString(1, ten);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString(1);
                String tenMA = rs.getString(2);
                String hinhAnh = rs.getString(3);
                float gia = rs.getFloat(4);
                boolean trangThai = rs.getBoolean(5);
                String maLoaiMA = rs.getString(6);
                String maKM = rs.getString(7);

                LoaiMonAn loaiMon = new LoaiMonAn(maLoaiMA, rs.getString("tenLoaiMA"));

                KhuyenMai km = new KhuyenMai(maKM);

                list.add(new MonAn(maMA, tenMA, hinhAnh, gia, trangThai, loaiMon, km));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    //Tìm Theo Mã

    public MonAn TimTheoMa(String ma) {
        con = ConnectDB.getConnection();
        MonAn emp = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("SELECT MonAn.*, LoaiMonAn.tenLoaiMA FROM MonAn JOIN LoaiMonAn ON MonAn.maLoaiMA = LoaiMonAn.maLoaiMA WHERE MonAn.maMA = ?");
            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maMA = rs.getString(1);
                String tenMA = rs.getString(2);
                String hinhAnh = rs.getString(3);
                float gia = rs.getFloat(4);
                boolean trangThai = rs.getBoolean(5);
                String maLoaiMA = rs.getString(6);
                String maKM = rs.getString(7);

                LoaiMonAn loaiMon = new LoaiMonAn(maLoaiMA, rs.getString("tenLoaiMA"));
                KhuyenMai km = new KhuyenMai(maKM);
                emp = new MonAn(maMA, tenMA, hinhAnh, gia, trangThai, loaiMon, km);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return emp;
    }
    // Cập nhật

    public boolean CapNhatMonAn(MonAn m) {
        ConnectDB.getInstance();
        con = ConnectDB.getConnection();
        PreparedStatement stml = null;
        int n = 0;
        try {
            stml = con.prepareStatement("update MonAn set tenMA=?, hinhAnh=?, gia=?, trangThai=? where maMA=?");
            stml.setString(1, m.getTenMA());
            stml.setString(2, m.getHinhAnh());
            stml.setDouble(3, m.getGia());
            stml.setBoolean(4, m.isTrangThai());
            stml.setString(5, m.getMaMA());

            n = stml.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    // Xong tất cả

    public ArrayList<Object[]> getDSMonAn(String loaiMon) throws SQLException {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        String sql = "SELECT M.maMA,M.tenMA,LM.tenLoaiMA \n"
                + "FROM MonAn M JOIN LoaiMonAn LM ON M.maLoaiMA = LM.maLoaiMA \n"
                + "WHERE LM.tenLoaiMA = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, loaiMon);
        ResultSet rs = ps.executeQuery();
        Object[] ob = new Object[]{};
        while (rs.next()) {
            ob = new Object[4];
            ob[0] = rs.getString(1);
            ob[1] = rs.getString(2);
            ob[2] = rs.getString(3);
            list.add(ob);
        }
        return list;
    }

    //
    public boolean updateMonAn(String maMon, String maKM) throws SQLException {
        con = ConnectDB.getConnection();
        int n = 0;
        String sql = "UPDATE MonAn SET maKM = ? WHERE maMA = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maKM);
        ps.setString(2, maMon);
        n = ps.executeUpdate();
        if (n == 0) {
            return false;
        }
        return true;
    }

    public Object[] timMonAnTheoMa(String maKM,String loaiMon,String trangThaiMon) throws SQLException {
        con = ConnectDB.getConnection();
        Object ob[] = null;
        ResultSet rs = null;
        if(loaiMon.trim().length() > 0 && trangThaiMon.trim().length() > 0){
            String sql = "SELECT M.maMA,M.tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA = LM.maLoaiMA WHERE M.maMA = ? AND LM.tenLoaiMA = ? AND M.trangThai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,maKM);
            ps.setString(2,loaiMon);
            ps.setString(3,trangThaiMon);
            rs = ps.executeQuery();
        }
        else if(loaiMon.trim().length() == 0 && trangThaiMon.trim().length() == 0){
            String sql = "SELECT M.maMA,M.tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA = LM.maLoaiMA WHERE M.maMA = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,maKM);
            rs = ps.executeQuery();
        }
        else if(loaiMon.trim().length() > 0){
            String sql = "SELECT M.maMA,M.tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA = LM.maLoaiMA WHERE M.maMA = ? AND LM.tenLoaiMA = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,maKM);
            ps.setString(2,loaiMon);
            rs = ps.executeQuery();
        }
        else if(trangThaiMon.trim().length() > 0){
            String sql = "SELECT M.maMA,M.tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA = LM.maLoaiMA WHERE M.maMA = ? AND M.trangThai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,maKM);
            ps.setString(2,trangThaiMon);
            rs = ps.executeQuery();
        };

        while (rs.next()){
            String maMonAn = rs.getString(1);
            String tenMonAn = rs.getString(2);
            String gia = rs.getString(3);
            String giamGia = rs.getString(4);
            String loaiMon1 = rs.getString(5);
            String trangThaiMon1 = rs.getInt(6) == 1 ? "Đang hoạt động" : "Ngưng hoạt động";
            ob = new Object[]{maMonAn,tenMonAn,gia,giamGia + " %",loaiMon1,trangThaiMon1};
        }

        return ob;
    };

    public ArrayList<Object[]> timKiemMonTheoTen(String tenMon, String loaiMon, String trangThaiMon, String sortKey, String sortValue) throws SQLException {
        ArrayList<Object[]> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        ResultSet rs = null;

        if(loaiMon.trim().length() > 0 && trangThaiMon.trim().length() > 0 && sortKey.trim().length() > 0) {
            String sql = "SELECT M.maMA,tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA= LM.maLoaiMA WHERE tenMA COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') \n" +
                    "AND LM.tenLoaiMA = ? AND M.trangThai = ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenMon);
            ps.setString(2, loaiMon);
            ps.setString(3, trangThaiMon);
            rs = ps.executeQuery();
        }
        else if(loaiMon.trim().length() == 0 && trangThaiMon.trim().length() == 0 && sortKey.trim().length() == 0){
            String sql = "SELECT M.maMA,tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA= LM.maLoaiMA WHERE tenMA COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenMon);
            rs = ps.executeQuery();
        }
        else if(loaiMon.trim().length() > 0 && trangThaiMon.trim().length() > 0){
            String sql = "SELECT M.maMA,tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA= LM.maLoaiMA WHERE tenMA COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') \n" +
                    "AND LM.tenLoaiMA = ? AND M.trangThai = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenMon);
            ps.setString(2, loaiMon);
            ps.setString(3, trangThaiMon);
            rs = ps.executeQuery();
        }
        else if(loaiMon.trim().length() > 0 && sortKey.trim().length() > 0){
            String sql = "SELECT M.maMA,tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA= LM.maLoaiMA WHERE tenMA COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') \n" +
                    "AND LM.tenLoaiMA = ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenMon);
            ps.setString(2, loaiMon);
            rs = ps.executeQuery();
        }
        else if(trangThaiMon.trim().length() > 0 && sortKey.trim().length() > 0){
            String sql = "SELECT M.maMA,tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA= LM.maLoaiMA WHERE tenMA COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') \n" +
                    "AND M.trangThai = ? ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenMon);
            ps.setString(2, trangThaiMon);
            rs = ps.executeQuery();
        }
        else if(loaiMon.trim().length() > 0){
            String sql = "SELECT M.maMA,tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA= LM.maLoaiMA WHERE tenMA COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') \n" +
                    "AND LM.tenLoaiMA = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenMon);
            ps.setString(2, loaiMon);
            rs = ps.executeQuery();
        }
        else if(trangThaiMon.trim().length() > 0){
            String sql = "SELECT M.maMA,tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA= LM.maLoaiMA WHERE tenMA COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') \n" +
                    " AND M.trangThai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenMon);
            ps.setString(2, trangThaiMon);
            rs = ps.executeQuery();
        }
        else if(sortKey.trim().length() > 0){
            String sql = "SELECT M.maMA,tenMA,gia,COALESCE(giamGia,0),LM.tenLoaiMA,M.trangThai \n" +
                    "FROM MonAn M LEFT JOIN KhuyenMai KM ON M.maKM = KM.maKM \n" +
                    "JOIN LoaiMonAn LM ON M.maLoaiMA= LM.maLoaiMA WHERE tenMA COLLATE SQL_Latin1_General_CP1_CI_AI LIKE CONCAT( '%',?,'%') \n" +
                    "ORDER BY " + sortKey + " " + sortValue;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tenMon);
            rs = ps.executeQuery();
        }



        while (rs.next()){
            String maMonAn = rs.getString(1);
            String tenMonAn = rs.getString(2);
            String gia = rs.getString(3);
            String giamGia = rs.getString(4);
            String loaiMon1 = rs.getString(5);
            String trangThaiMon1 = rs.getInt(6) == 1 ? "Đang hoạt động" : "Ngưng hoạt động";
            Object  ob[] = new Object[]{maMonAn,tenMonAn,gia,giamGia + "%",loaiMon1,trangThaiMon1};
            list.add(ob);
        }
        return list;
    };

    public ArrayList<String> getLoaiMon() throws SQLException {
        ArrayList<String> list = new ArrayList<>();
        con = ConnectDB.getConnection();
        String sql = "SELECT tenLoaiMA FROM LoaiMonAn";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            String loaiMon = rs.getString(1);
            list.add(loaiMon);
        }
        return list;
    };


}
