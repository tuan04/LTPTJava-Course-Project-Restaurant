//package dao;
//
//import model.KhuyenMai;
//import model.LoaiKhuyenMai;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class KhuyenMaiDAOTest {
//
//    private KhuyenMaiDAO khuyenMaiDAO;
//    private KhuyenMai khuyenMai;
//    private LoaiKhuyenMai loaiKhuyenMai;
//
//    @BeforeEach
//    public void setUp() {
//        khuyenMaiDAO = new KhuyenMaiDAO();
//        loaiKhuyenMai = new LoaiKhuyenMai();
//        loaiKhuyenMai.setMaLoaiKM("LKM1");
//        loaiKhuyenMai.setTenLoaiKM("Món ăn");
//
//        khuyenMai = new KhuyenMai();
//        khuyenMai.setMaKM("KM001");
//        khuyenMai.setTenKM("Khuyến mãi món ăn");
//        khuyenMai.setChietKhau(5.0);
//        khuyenMai.setNgayBD(LocalDateTime.of(2025, 01, 21, 11, 19));
//        khuyenMai.setNgayKT(LocalDateTime.of(2025, 01, 27, 11, 19));
//        khuyenMai.setSoLuong(0);
//        khuyenMai.setLoaiKM(loaiKhuyenMai);
//    }
//
//    @Test
//    public void testCreateKhuyenMai() {
//        boolean result = khuyenMaiDAO.createKhuyenMai(khuyenMai);
//        assertTrue(result, "Khuyến mãi tạo thành công");
//    }
//
//    @Test
//    public void testUpdateKhuyenMai() {
//        khuyenMai.setChietKhau(20.0);
//        boolean result = khuyenMaiDAO.updateKhuyenMai(khuyenMai);
//
//        assertTrue(result, "Khuyến mãi cập nhật thành công");
//        KhuyenMai updatedKhuyenMai = khuyenMaiDAO.getKhuyenMai(khuyenMai.getMaKM());
//        assertEquals(20.0, updatedKhuyenMai.getChietKhau(), "Giảm giá 20%");
//    }
//
//    @Test
//    public void testGetAllKhuyenMai() {
//        List<KhuyenMai> khuyenMais = khuyenMaiDAO.getAllKhuyenMai();
//        assertFalse(khuyenMais.isEmpty(), "Có ít nhất một khuyến mãi");
//    }
//
//    @Test
//    public void testGetKhuyenMai() {
//        KhuyenMai fetchedKhuyenMai = khuyenMaiDAO.getKhuyenMai("KM001");
//        assertNotNull(fetchedKhuyenMai, "Khuyến mãi nên tìm");
//        assertEquals(khuyenMai.getTenKM(), fetchedKhuyenMai.getTenKM(), "Tên khuyến mãi phải phù hợp");
//        assertEquals(khuyenMai.getChietKhau(), fetchedKhuyenMai.getChietKhau(), "Chiết khấu phải phù hợp");
//    }
//}
