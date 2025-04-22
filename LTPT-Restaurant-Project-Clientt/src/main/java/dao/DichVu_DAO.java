/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.DichVu;
import java.sql.*;

/**
 *
 * @author THANHTRI
 */
public class DichVu_DAO {
    private Connection con = null;
    
    
    
    public DichVu getDV(String maDV) {
        con = ConnectDB.getConnection();
        DichVu x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM DichVu WHERE maDV = ?");
            ps.setString(1, maDV);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String mDV = rs.getString("maDV");
                int VAT = rs.getInt("VAT");
                int PV = rs.getInt("PV");
                int phongVIP = rs.getInt("phongVIP");
                boolean trangThai = rs.getBoolean("trangThai");
                x = new DichVu(mDV, VAT, PV, phongVIP, trangThai);
            }
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }
    public DichVu getActiveService(){
        DichVu dv = null;
        con = ConnectDB.getConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select *  from DichVu where trangThai = 1");
            while(rs.next()){
                String maDV = rs.getString(1);
                int VAT = rs.getInt(2);
                int DV = rs.getInt(3);
                int phongVip = rs.getInt(4);
                boolean trangThai = rs.getBoolean(5);
                dv = new DichVu(maDV, VAT, DV, phongVip, trangThai);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dv;
    }
}
