//package dao;
//
//import model.LoaiKhuyenMai;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class LoaiKhuyenMaiDAOTest {
//    private LoaiKhuyenMaiDAO loaiKhuyenMaiDAO = new LoaiKhuyenMaiDAO();
//
//    @Test
//    void updateLoaiKhuyenMai() {
//        LoaiKhuyenMai loaiKhuyenMai = new LoaiKhuyenMai();
//        loaiKhuyenMai.setMaLoaiKM("LKM1");
//        loaiKhuyenMai.setTenLoaiKM("Món ăn");
//
//        boolean result = loaiKhuyenMaiDAO.updateLoaiKhuyenMai(loaiKhuyenMai);
//        assertTrue(result);
//
//        LoaiKhuyenMai updated = loaiKhuyenMaiDAO.findByID("LKM1");
//        assertNotNull(updated);
//        assertEquals("Món ăn", updated.getTenLoaiKM());
//    }
//
//    @Test
//    void getAllLoaiKhuyenMai() {
//        List<LoaiKhuyenMai> lkmList = loaiKhuyenMaiDAO.getAll();
//        assertNotNull(lkmList);
//        assertTrue(lkmList.size() > 0);
//    }
//
//    @Test
//    void findByID_ex() {
//        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiDAO.findByID("LKM1");
//        assertNotNull(loaiKhuyenMai);
//        assertEquals("LKM1", loaiKhuyenMai.getMaLoaiKM());
//    }
//
//    @Test
//    void findByID_nonEx() {
//        LoaiKhuyenMai loaiKhuyenMai = loaiKhuyenMaiDAO.findByID("LKM3");
//        assertNull(loaiKhuyenMai);
//    }
//}