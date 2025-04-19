//package dao;
//
//
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityTransaction;
//import jakarta.persistence.TypedQuery;
//import model.KhachHang;
//import model.LoaiKhachHang;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class KhachHangDAOTest {
//    private EntityManager em;
//    private EntityTransaction tr;
//    private KhachHangDAO khachHangDAO;
//
//
//
////    @BeforeEach
////    public void setUp(){
////        em = mock(EntityManager.class);
////        tr = mock(EntityTransaction.class);
////        khachHangDAO = new KhachHangDAO(em);
////        when(em.getTransaction()).thenReturn(tr);
////    }
//
//    @Test
//    public void testCreate() {
//        LoaiKhachHang loaiKhachHang = new LoaiKhachHang("LKH1",
//                "Thành viên",
//                0,
//                0.05);
//
//        // Khởi tạo đối tượng KhachHang
//        KhachHang khachHang = new KhachHang(
//                "KH200619",
//                "Phạm Thanh Tùng",
//                "0339864433",
//                "ttung2827@gmail.com",
//                LocalDate.of(1998, 6, 20),
//                LocalDate.now(),
//                100,
//                true,
//                loaiKhachHang
//        );
//
//
//        boolean result = khachHangDAO.create(khachHang);
//
//        verify(tr).begin();
//        verify(em).persist(khachHang);
//        verify(tr).commit();
//        assertTrue(result);
//    }
//
//    @Test
//    public void testCreateRollbackOnException() {
//        LoaiKhachHang loaiKhachHang = new LoaiKhachHang("LKH1","Thành viên", 0, 0.05);
//
//        // Khởi tạo đối tượng KhachHang
//        KhachHang khachHang = new KhachHang(
//                "KH200619",
//                "Phạm Thanh Tùng",
//                "0339864433",
//                "ttung2827@gmail.com",
//                LocalDate.of(1998, 6, 20),
//                LocalDate.now(),
//                100,
//                true,
//                loaiKhachHang
//        );
//        doThrow(new RuntimeException()).when(em).persist(khachHang);
//
//        boolean result = khachHangDAO.create(khachHang);
//
//        verify(tr).begin();
//        verify(tr).rollback();
//        assertFalse(result);
//    }
//    //
//    @Test
//    public void testUpdate() {
//        LoaiKhachHang loaiKhachHang = new LoaiKhachHang("LKH2","Thành viên", 0.1, 0.1);
//
//        // Khởi tạo đối tượng KhachHang
//        KhachHang khachHang = new KhachHang(
//                "KH200619",
//                "Phạm Thanh Tùng",
//                "0339864433",
//                "ttung2827@gmail.com",
//                LocalDate.of(1998, 6, 20),
//                LocalDate.now(),
//                300,
//                true,
//                loaiKhachHang
//        );
//
//        boolean result = khachHangDAO.update(khachHang);
//
//        verify(tr).begin();
//        verify(em).merge(khachHang);
//        verify(tr).commit();
//        assertTrue(result);
//    }
//    //
//    @Test
//    public void testUpdateRollbackOnException() {
//        KhachHang khachHang = new KhachHang();
//        doThrow(new RuntimeException()).when(em).merge(khachHang);
//
//        boolean result = khachHangDAO.update(khachHang);
//
//        verify(tr).begin();
//        verify(tr).rollback();
//        assertFalse(result);
//    }
//    //
//    @Test
//    public void testGetAll() {
//        LoaiKhachHang loaiKhachHang = new LoaiKhachHang("LKH1", "Thành viên", 0, 0.05);
//
//        KhachHang khachHang = new KhachHang(
//                "KH200619",
//                "Phạm Thanh Tùng",
//                "0339864433",
//                "ttung2827@gmail.com",
//                LocalDate.of(1998, 6, 20),
//                LocalDate.now(),
//                100,
//                true,
//                loaiKhachHang
//        );
//        KhachHang khachHang1 = new KhachHang(
//                "KH200621",
//                "Phạm Thanh Vy",
//                "0339864433",
//                "ttung2827@gmail.com",
//                LocalDate.of(1998, 6, 20),
//                LocalDate.now(),
//                200,
//                true,
//                loaiKhachHang
//        );
//        KhachHang khachHang2 = new KhachHang(
//                "KH200620",
//                "Phạm Thanh Hùng",
//                "0339864433",
//                "ttung2827@gmail.com",
//                LocalDate.of(1998, 6, 20),
//                LocalDate.now(),
//                300,
//                true,
//                loaiKhachHang
//        );
//
//        List<KhachHang> list = new ArrayList<>();
//        list.add(khachHang);
//        list.add(khachHang1);
//        list.add(khachHang2);
//
//        // Mock the TypedQuery
//        TypedQuery<KhachHang> typedQuery = mock(TypedQuery.class);
//
//        // Set up the EntityManager to return the mocked TypedQuery
//        when(em.createQuery("SELECT kh FROM KhachHang kh", KhachHang.class)).thenReturn(typedQuery);
//
//        // Set up the TypedQuery to return the list
//        when(typedQuery.getResultList()).thenReturn(list);
//
//        // Call the method under test
//        List<KhachHang> result = khachHangDAO.getAll();
//
//        // Assertions
//        assertEquals(3, result.size());
//        assertEquals(list, result); // Compare the entire list
//        result.forEach(x -> System.out.println(x));
//    }
//    //
//    @Test
//    public void testFindById() {
//        String maKH = "KH200619";
//        LoaiKhachHang loaiKhachHang = new LoaiKhachHang("LKH1", "Thành viên", 0, 0.05);
//
//        KhachHang khachHang = new KhachHang(
//                "KH200619",
//                "Phạm Thanh Tùng",
//                "0339864433",
//                "ttung2827@gmail.com",
//                LocalDate.of(1998, 6, 20),
//                LocalDate.now(),
//                100,
//                true,
//                loaiKhachHang
//        );
//        KhachHang khachHang1 = new KhachHang(
//                "KH200621",
//                "Phạm Thanh Vy",
//                "0339864433",
//                "ttung2827@gmail.com",
//                LocalDate.of(1998, 6, 20),
//                LocalDate.now(),
//                200,
//                true,
//                loaiKhachHang
//        );
//        KhachHang khachHang2 = new KhachHang(
//                "KH200620",
//                "Phạm Thanh Hùng",
//                "0339864433",
//                "ttung2827@gmail.com",
//                LocalDate.of(1998, 6, 20),
//                LocalDate.now(),
//                300,
//                true,
//                loaiKhachHang
//        );
//
//        List<KhachHang> list = new ArrayList<>();
//        list.add(khachHang);
//        list.add(khachHang1);
//        list.add(khachHang2);
//
//        when(em.find(KhachHang.class, maKH)).thenReturn(khachHang);
//
//        KhachHang result = khachHangDAO.findById(maKH);
//
//        assertEquals(khachHang, result);
//        System.out.println(result);
//    }
//}