//package dao;
//
//import model.NhanVien;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityTransaction;
//import model.Ban;
//import model.LoaiBan;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//import java.util.List;
//
//public class NhanVienDAOTest {
//
//    private NhanVienDAO n = new NhanVienDAO();
//    @Test
//    public void createNhanVien() {
//        NhanVien nh = new NhanVien();
//        nh.setMaNV("NV001");
//        nh.setTenNV("Nguyen Van B");
//        nh.setGioiTinh(true);
//        nh.setSdt("0123456789");
//        nh.setEmail("nva@gmail.com");
//        nh.setNgaySinh(LocalDate.of(1990, 1, 1));
//        nh.setMatKhau("123456");
//        nh.setTrangThai(true);
//        nh.setLoaiNV("QL");
//        n.createNhanVien(nh);
//        boolean result = n.createNhanVien(nh);
//        assertTrue(result, "Tạo nhân viên thành công");
//
//    }
//    @Test
//    void createNhanVienEmty() {
//        NhanVien nh = new NhanVien();
//        boolean result = n.createNhanVien(nh);
//        assertTrue(result, "tạo nhân viên không thành công");
//    }
//    @Test
//    public void testGetAllNhanVien() {
//        List<NhanVien> list = n.getAllNhanVien();
//        assertNotNull(list, "List of NhanVien should not be null");
//        assertFalse(list.isEmpty(), "List of NhanVien should not be empty");
//    }
//    @Test
//    public void testUpdateNhanVien() {
//        NhanVien nh = n.getNhanVien("NV001");
//        assertNotNull(nh, "NhanVien với mã NV001 should exist");
//        nh.setTenNV("Nguyen Van B");
//        boolean result = n.updateNhanVien(nh);
//        assertTrue(result, "Update NhanVien should return true");
//
//        NhanVien updatedNhanVien = n.getNhanVien("NV001");
//        assertEquals("Nguyen Van B", updatedNhanVien.getTenNV(), "Updated name should be Nguyen Van B");
//    }
//    @Test
//    public void testGetNhanVien() {
//        NhanVien nh = n.getNhanVien("NV001");
//        assertNotNull(nh, "NhanVien with ID NV001 should exist");
//        assertEquals("NV001", nh.getMaNV(), "NhanVien ID should match");
//    }
//
//
//}
//
//
