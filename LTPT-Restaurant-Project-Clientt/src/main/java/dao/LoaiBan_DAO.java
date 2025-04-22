/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connectDB.ConnectDB;
import entity.LoaiBan;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author THANHTRI
 */
public class LoaiBan_DAO {

    private Connection con = null;

    public LoaiBan getLB(String maLB) {
        con = ConnectDB.getConnection();
        LoaiBan x = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM LoaiBan WHERE maLB = ?");
            ps.setString(1, maLB);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String mLB = rs.getString("maLB");
                String tenLB = rs.getString("tenLB");
                x = new LoaiBan(mLB, tenLB);
            }
            return x;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return x;
    }
    
    public LoaiBan getLoaiBanTheoMa(String maLB){
        LoaiBan lb = null;
        con = ConnectDB.getConnection();
        try{
            PreparedStatement ps = con.prepareStatement("select * from LoaiBan where maLB = ?");
            ps.setString(1, maLB);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String malb = rs.getString(1);
                String tenLb = rs.getString(2);
                lb = new LoaiBan(malb, tenLb);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return lb;
    }
    
    public ArrayList<LoaiBan> getListLoaiBan(){
        ArrayList<LoaiBan> list = new ArrayList<LoaiBan>();
        con = ConnectDB.getConnection();
        try{
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from LoaiBan");
            while(rs.next()){
                String maLB = rs.getString(1);
                String tenLB = rs.getString(2);
                list.add(new LoaiBan(maLB, tenLB));
            }
            rs.close();
            s.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return list;
    }

}
