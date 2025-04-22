//package dao;
//
//import model.Ban;
//import model.LoaiBan;
//import model.LoaiKhachHang;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LoaiBanDAOTest {
//    private LoaiBanDAO loaiBanDAO = new LoaiBanDAO();
//
//    @Test
//    void getAllLoaiBan() {
//        List<LoaiBan> lbList = loaiBanDAO.getAllLoaiBan();
//        Assertions.assertNotEquals(null, lbList);
//    }
//
//    @Test
//    void getLoaiBanTheoMa() {
//        LoaiBan lb = loaiBanDAO.getLoaiBanTheoMa("LB1");
//        Assertions.assertNotEquals(null, lb);
//    }
//    @Test
//    void getLoaiBanTheoMa_nullReturned() {
//        LoaiBan lb = loaiBanDAO.getLoaiBanTheoMa("LB3");
//        Assertions.assertEquals(null, lb);
//    }
//}