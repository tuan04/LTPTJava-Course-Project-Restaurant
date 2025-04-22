package dao;

import connectDB.ConnectDB;
import entity.ChiTietDatBan;
import entity.ChiTietHoaDon;
import entity.DonDatBan;
import entity.MonAn;
import java.sql.*;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author THANHTRI
 */
public class ChiTietDatBan_DAO {
    private Connection con = null;
    
    public boolean delete(String maDDB) {
        int n = 0; 
        con = ConnectDB.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("DELETE FROM ChiTietDatBan WHERE maDDB = ?");
            ps.setString(1, maDDB);
            n = ps.executeUpdate();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return n > 0;
    }
    
    public boolean insert(ChiTietDatBan ctdb){
        int n = 0; 
        con = ConnectDB.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("insert into ChiTietDatBan values (?,?,?,?,?)");
            ps.setString(1, ctdb.getMonAn().getMaMA());
            ps.setString(2, ctdb.getDonDatBan().getMaDDB());
            ps.setInt(3, ctdb.getSoLuong());
            ps.setDouble(4, ctdb.getThanhTien());
            ps.setDouble(5, ctdb.getGiaSauGiam());
            n = ps.executeUpdate();
            
            ps.close();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return n > 0;
    }
    
    public ArrayList<ChiTietDatBan> getList(String maDDB) {
        con = ConnectDB.getConnection();
        ArrayList<ChiTietDatBan> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ChiTietDatBan WHERE maDDB = ?");
            ps.setString(1, maDDB);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maDonDatBan = rs.getString("maDDB");
                String maMonAn = rs.getString("maMA");
                int soLuong = rs.getInt("soLuong");
                double thanhTien = rs.getDouble("thanhTien");
                double giaSauGiam = rs.getDouble("giaSauGiam");
                list.add(new ChiTietDatBan(new MonAn(maMonAn), new DonDatBan(maDonDatBan), soLuong, thanhTien, giaSauGiam));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }
}
