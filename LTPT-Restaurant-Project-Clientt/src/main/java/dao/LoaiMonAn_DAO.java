/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.LoaiMonAn;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author THANHTRI
 */
public class LoaiMonAn_DAO {

    private Connection con = null;

    public ArrayList<LoaiMonAn> getListLoaiMonAn() {
        con = ConnectDB.getConnection();
        ArrayList<LoaiMonAn> list = new ArrayList<LoaiMonAn>();

        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from LoaiMonAn");
            while (rs.next()) {
                String maLoai = rs.getString(1);
                String tenLoai = rs.getString(2);
                list.add(new LoaiMonAn(maLoai, tenLoai));
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public LoaiMonAn TimLoaiMon(String ma) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        LoaiMonAn emp = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("select * from LoaiMonAn where maLoaiMA = ?");
            ps.setString(1, ma);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiMA = rs.getString(1);
                String tenLoaiMA = rs.getString(2);

                emp = new LoaiMonAn(maLoaiMA, tenLoaiMA);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return emp;
    }

    public LoaiMonAn TimLoaiMonTheoTen(String ten) {
        LoaiMonAn emp = null;
        PreparedStatement ps = null;
        con = ConnectDB.getConnection();
        try {
            ps = con.prepareStatement("select * from LoaiMonAn where tenLoaiMA = ?");
            ps.setString(1, ten);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maLoaiMA = rs.getString(1);
                String tenLoaiMA = rs.getString(2);

                emp = new LoaiMonAn(maLoaiMA, tenLoaiMA);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return emp;
    }

}
