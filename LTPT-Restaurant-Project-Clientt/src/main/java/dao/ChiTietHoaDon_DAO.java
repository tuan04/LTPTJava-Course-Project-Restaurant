/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.MonAn;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author THANHTRI
 */
public class ChiTietHoaDon_DAO {
    private Connection con = null;
    
    public boolean deleteCTHD(ChiTietHoaDon cthd){

        int n = 0;

        con = ConnectDB.getConnection();

        try{

            PreparedStatement ps = con.prepareStatement("delete ChiTietHoaDon where maHD = ? and maMA = ?");

            ps.setString(1, cthd.getHoaDon().getMaHD());

            ps.setString(2, cthd.getMonAn().getMaMA());

            n = ps.executeUpdate();

        }catch(SQLException e){

            e.printStackTrace();

        }

        return n > 0;

    }
    
    public boolean updateSoLuongMonOrderDetail(ChiTietHoaDon cthd){
        int n = 0;
        try{
            PreparedStatement ps = con.prepareStatement("update ChiTietHoaDon set soLuong = ? , thanhTien = ? where maHD = ? and maMA = ?");
            ps.setInt(1, cthd.getSoLuong());
            ps.setDouble(2, cthd.getThanhTien());
            ps.setString(3, cthd.getHoaDon().getMaHD());
            ps.setString(4, cthd.getMonAn().getMaMA());
            n = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return n > 0;
    }
    
    public ArrayList<ChiTietHoaDon> getList(String maHD) {
        con = ConnectDB.getConnection();
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ChiTietHoaDon WHERE maHD = ?");
            ps.setString(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maHoaDon = rs.getString("maHD");
                String maMA = rs.getString("maMA");
                double giaSauGiam = rs.getDouble("giaSauGiam");
                int soLuong = rs.getInt("soLuong");
                double thanhTien = rs.getDouble("thanhTien");
                list.add(new ChiTietHoaDon(new HoaDon(maHoaDon), new MonAn(maMA), thanhTien, soLuong, giaSauGiam));
            }
            return list.isEmpty() ? null : list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }
    
    public boolean createOrderDetail(ChiTietHoaDon cthd){
        int n = 0; 
        con = ConnectDB.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("insert into ChiTietHoaDon values (?,?,?,?,?)");
            ps.setString(1, cthd.getHoaDon().getMaHD());
            ps.setString(2, cthd.getMonAn().getMaMA());
            ps.setDouble(3, cthd.getThanhTien());
            ps.setInt(4, cthd.getSoLuong());
            ps.setDouble(5, cthd.getGiaSauGiam());
            n = ps.executeUpdate();
            
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return n > 0;
    }
    
    public ArrayList<ChiTietHoaDon> getOrderDetails(String orderID){
        ArrayList<ChiTietHoaDon> cthd = new ArrayList<ChiTietHoaDon>();
        con = ConnectDB.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from ChiTietHoaDon where maHD = ?");
            ps.setString(1, orderID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String maHD = rs.getString(1);
                String maMA = rs.getString(2);
                double thanhTien = rs.getDouble(3);
                int soLuong = rs.getInt(4);
                double giaSauGiam = rs.getDouble(5);
                
                cthd.add(new ChiTietHoaDon(new HoaDon(maHD), new MonAn(maMA), thanhTien, soLuong, giaSauGiam));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return cthd;
    }

}
