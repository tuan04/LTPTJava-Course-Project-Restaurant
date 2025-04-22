//package dao;
//
//import model.LoaiBan;
//import model.LoaiKhuyenMai;
//import model.LoaiMonAn;
//import model.MonAn;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LoaiMonAnDAOTest {
//    private LoaiMonAnDAO loaiMonAnDAO = new LoaiMonAnDAO();
//    @Test
//    void getAll() {
//        List<LoaiMonAn> lbList = loaiMonAnDAO.getAll();
//        Assertions.assertNotEquals(null, lbList);
//    }
//
//    @Test
//    void findByID() {
//        LoaiMonAn lb = loaiMonAnDAO.findByID("LM120");
//        Assertions.assertNotEquals(null, lb);
//    }
//    @Test
//    void findByID_nullReturned() {
//        LoaiMonAn lb = loaiMonAnDAO.findByID("123");
//        Assertions.assertEquals(null, lb);
//    }
//}