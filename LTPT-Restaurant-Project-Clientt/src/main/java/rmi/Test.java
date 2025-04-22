/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import model.NhanVien;

/**
 *
 * @author htuan
 */
public class Test {
    public static void main(String[] args) {
        try {
        RMIClientManager manager = RMIClientManager.getInstance();
        NhanVien nv = manager.getNhanVienService().findById("AD001");
        System.out.println(nv);
    }
          catch (Exception e) {
        e.printStackTrace();
    }}
}
