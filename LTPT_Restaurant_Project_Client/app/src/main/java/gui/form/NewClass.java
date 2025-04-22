package gui.form;

import java.rmi.Naming;
import model.NhanVien;
import service.NhanVienService;

public class NewClass {
    public static void main(String[] args) {
        try {
            NhanVienService nvService = (NhanVienService) Naming.lookup("rmi://DESKTOP-03JUV5H:9090/nhanvienService");
            NhanVien nv = nvService.findById("AD001");

            if (nv != null) {
                System.out.println("Tên nhân viên: " + nv.getTenNV());
            } else {
                System.out.println("Không tìm thấy nhân viên với mã AD001");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
