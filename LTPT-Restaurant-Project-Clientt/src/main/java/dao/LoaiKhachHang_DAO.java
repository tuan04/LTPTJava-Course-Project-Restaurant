/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.LoaiKhachHang;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author THANHTRI
 */
public class LoaiKhachHang_DAO {
    private Connection con = null;

    public ArrayList<LoaiKhachHang> getListLoai() {
        ArrayList<LoaiKhachHang> list = new ArrayList<LoaiKhachHang>();
        con = ConnectDB.getConnection();
        PreparedStatement ps = null;

        String sql = "select * from LoaiKhachHang";
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiKH = rs.getString(1);
                String tenLoaiKH = rs.getString(2);
                int giamGiaThanhVien = rs.getInt(3);
                int giamGiamSinhNhat = rs.getInt(4);

                list.add(new LoaiKhachHang(maLoaiKH, tenLoaiKH, giamGiaThanhVien, giamGiamSinhNhat));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
    
    public LoaiKhachHang kiemTraLKH(String maLoaiKH) {
        con = ConnectDB.getConnection();
        LoaiKhachHang x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM LoaiKhachHang WHERE maLoaiKH = ?");
            ps.setString(1, maLoaiKH);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String maLoai = rs.getString("maLoaiKH");
                String tenLoaiKH = rs.getString("tenLoaiKH");
                int giamGiaThanhVien = rs.getInt("giamGiaThanhVien");
                int giamGiaSinhNhat = rs.getInt("giamGiaSinhNhat");
                x = new LoaiKhachHang(maLoai, tenLoaiKH, giamGiaThanhVien, giamGiaSinhNhat);
            }
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }
    
    public LoaiKhachHang TimLoaiKhachHangTim(String ma) {
        con = ConnectDB.getConnection();
        LoaiKhachHang emp = null;
        PreparedStatement ps = null;
        try {
                ps = con.prepareStatement("select * from LoaiKhachHang where maLoaiKH = ?");
                ps.setString(1, ma);

                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    String maLoaiKH = rs.getString(1);
                    String tenLoaiKH = rs.getString(2);

                    int giamGiaThanhVien = rs.getInt(3);
                    int giamGiamSinhNhat = rs.getInt(4);


                    emp = new LoaiKhachHang(maLoaiKH, tenLoaiKH, giamGiaThanhVien, giamGiamSinhNhat);
                }
        } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return emp;
    }
}
