//package dao;
//
//import model.LoaiMonAn;
//import model.MonAn;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MonAnDAOTest {
//    private MonAnDAO monAnDAO = new MonAnDAO();
//
//    @Test
//    void createMonAn() {
//        MonAn monAn = new MonAn();
//        monAn.setMaMonAn("MA012");
//        monAn.setTenMonAn("Gan ngỗng siêu cấp");
//        LoaiMonAn lm = new LoaiMonAn();
//        lm.setMaLoaiMon("LM120");
//        lm.setTenLoaiMon("Hải sản");
//
//        boolean result = monAnDAO.createMonAn(monAn);
//        assertEquals(true, result);
//
//    }
//
//    @Test
//    void createMonAn_nullObject(){
//        boolean result = monAnDAO.createMonAn(null);
//        Assertions.assertEquals(false, result);
//    }
//
//    @Test
//    void updateMonAn() {
//        MonAn monAn = monAnDAO.findByID("MA012");
//        monAn.setGia(120);
//        boolean result = monAnDAO.updateMonAn(monAn);
//        Assertions.assertEquals(true, result);
//    }
//    @Test
//    void deleteMonAn() {
//        boolean result = monAnDAO.deleteMonAn("MA 012");
//        Assertions.assertEquals(true, result);
//    }
//    @Test
//    void getAllMonAn() {
//        List<MonAn> listMA = monAnDAO.getAllMonAn();
//        assertNotEquals(listMA, null);
//    }
//
//    @Test
//    void findByID() {
//        MonAn monAn = monAnDAO.findByID("MA096");
//        assertNotEquals(null, monAn);
//    }
//
//    @Test
//    void findByID_incorrect(){
//        MonAn monAn = monAnDAO.findByID("123");
//        assertEquals(null, monAn);
//    }
//}