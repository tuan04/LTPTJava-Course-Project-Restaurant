/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.LoaiNhanVien;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author THANHTRI
 */
public class LoaiNhanVien_DAO {

    private Connection con = null;

    public ArrayList<LoaiNhanVien> getListLoai() {
        ArrayList<LoaiNhanVien> list = new ArrayList<LoaiNhanVien>();
        PreparedStatement ps = null;
        con = ConnectDB.getConnection();
        String sql = "select * from LoaiNhanVien WHERE maLoaiNV != 'LNV4'";
        try {
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiNV = rs.getString(1);
                String viTri = rs.getString(2);
                list.add(new LoaiNhanVien(maLoaiNV, viTri));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public LoaiNhanVien TimLoaiNhanVien(String ma) {
        ConnectDB.getInstance();
        con = ConnectDB.getConnection();
        LoaiNhanVien emp = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from LoaiNhanVien where maLoaiNV = ? AND maLoaiNV != 'LNV4'");
            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiNV = rs.getString(1);
                String viTri = rs.getString(2);

                emp = new LoaiNhanVien(maLoaiNV, viTri);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return emp;
    }

    public LoaiNhanVien TimLoaiNhanVienTheoTen(String ten) {
        ConnectDB.getInstance();
        con = ConnectDB.getConnection();
        LoaiNhanVien emp = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from LoaiNhanVien where viTri = ?");
            ps.setString(1, ten);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiNV = rs.getString(1);
                String viTri = rs.getString(2);

                emp = new LoaiNhanVien(maLoaiNV, viTri);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return emp;
    }

}
