/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.Ban;
import entity.LoaiBan;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author THANHTRI
 */
public class Ban_DAO {
    private Connection con = null;
    
    public int getSoLuongBanTheoLBvTrangThai(String maLB, int trangThai){
        int n = 0;
        
        try{
            PreparedStatement ps = con.prepareStatement("select count(*) from Ban where maLB = ? and tinhTrang = ?");
            ps.setString(1, maLB);
            ps.setInt(2, trangThai);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                n = rs.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return n;
    }
    
    public Ban getBan(String maBan) {
        con = ConnectDB.getConnection();
        Ban x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM Ban WHERE maBan = ?");
            ps.setString(1, maBan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mB = rs.getString("maBan");
                int soBan = rs.getInt("soBan");
                int tinhTrang = rs.getInt("tinhTrang");
                String maLB = rs.getString("maLB");
                int soGhe = rs.getInt("soGhe");
                x = new Ban(mB, soBan, tinhTrang, new LoaiBan(maLB), soGhe);
            }
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }
    
     public boolean updateTableState(String maBan, int tinhTrang){
        int n = 0;
        con = ConnectDB.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("update Ban set tinhTrang = ? where maBan = ?");
            ps.setString(2, maBan);
            ps.setInt(1, tinhTrang);
            n = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    
    public ArrayList<Ban> getListBanTheoLoai(String maLoai){
        ArrayList<Ban> list = new ArrayList<Ban>();
        con = ConnectDB.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from Ban where maLB = ?");
            ps.setString(1, maLoai);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String maBan = rs.getString(1);
                int soBang = rs.getInt(2);
                int tinhTrang = rs.getInt(3);
                String maLB = rs.getString(4);
                int soGhe = rs.getInt(5);
                list.add(new Ban(maBan, soBang, tinhTrang, new LoaiBan(maLB), soGhe));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return list;
    }
    
    public ArrayList<Object[]> getBan(String loaiBan, String trangThai) throws SQLException {
        ArrayList<Object[]> listBan = new ArrayList<>();
        con = ConnectDB.getConnection();
        ResultSet rs = null;
        if(trangThai.trim().length() > 0 && loaiBan.trim().length() > 0){
            String sql = "SELECT soBan,tinhTrang FROM Ban WHERE maLB = ? AND tinhTrang = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, loaiBan);
            ps.setString(2, trangThai);
            rs = ps.executeQuery();
        }
        else{
            String sql = "SELECT soBan,tinhTrang FROM Ban WHERE maLB = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, loaiBan);
            rs = ps.executeQuery();
        }
        while(rs.next()){
            Object ban[] = new Object[2];
            ban[0] = rs.getString(1);
            ban[1] = rs.getString(2).equals("0") ? "Còn trống" : "Hoạt động";
            listBan.add(ban);
        }

        return listBan;
    };


}
