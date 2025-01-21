import dao.KhachHangDAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.KhachHang;
import model.LoaiKhachHang;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import java.time.LocalDate;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class KhachHangServiceTest {
    private EntityManager em;
    private EntityTransaction tr;
    private KhachHangDAO khachHangDAO;



    @BeforeEach
    public void setUp(){
        em = mock(EntityManager.class);
        tr = mock(EntityTransaction.class);
        khachHangDAO = new KhachHangDAO(em);
        when(em.getTransaction()).thenReturn(tr);
    }

    @Test
    public void testCreate() {
        LoaiKhachHang loaiKhachHang = new LoaiKhachHang("LKH1","Thành viên", 0, 0.05);

        // Khởi tạo đối tượng KhachHang
        KhachHang khachHang = new KhachHang(
                "KH200619",
                "Phạm Thanh Tùng",
                "0339864433",
                "ttung2827@gmail.com",
                LocalDate.of(1998, 6, 20),
                LocalDate.now(),
                100,
                true,
                loaiKhachHang
        );


        boolean result = khachHangDAO.create(khachHang);

        verify(tr).begin();
        verify(em).persist(khachHang);
        verify(tr).commit();
        assertTrue(result);
    }

    @Test
    public void testCreateRollbackOnException() {
        LoaiKhachHang loaiKhachHang = new LoaiKhachHang("LKH1","Thành viên", 0, 0.05);

        // Khởi tạo đối tượng KhachHang
        KhachHang khachHang = new KhachHang(
                "KH200619",
                "Phạm Thanh Tùng",
                "0339864433",
                "ttung2827@gmail.com",
                LocalDate.of(1998, 6, 20),
                LocalDate.now(),
                100,
                true,
                loaiKhachHang
        );
        doThrow(new RuntimeException()).when(em).persist(khachHang);

        boolean result = khachHangDAO.create(khachHang);

        verify(tr).begin();
        verify(tr).rollback();
        assertFalse(result);
    }
//
    @Test
    public void testUpdate() {
        LoaiKhachHang loaiKhachHang = new LoaiKhachHang("LKH2","Thành viên", 0.1, 0.1);

        // Khởi tạo đối tượng KhachHang
        KhachHang khachHang = new KhachHang(
                "KH200619",
                "Phạm Thanh Tùng",
                "0339864433",
                "ttung2827@gmail.com",
                LocalDate.of(1998, 6, 20),
                LocalDate.now(),
                300,
                true,
                loaiKhachHang
        );

        boolean result = khachHangDAO.update(khachHang);

        verify(tr).begin();
        verify(em).merge(khachHang);
        verify(tr).commit();
        assertTrue(result);
    }
//
    @Test
    public void testUpdateRollbackOnException() {
        KhachHang khachHang = new KhachHang();
        doThrow(new RuntimeException()).when(em).merge(khachHang);

        boolean result = khachHangDAO.update(khachHang);

        verify(tr).begin();
        verify(tr).rollback();
        assertFalse(result);
    }
//
    @Test
    public void testGetAll() {
        KhachHang khachHang = new KhachHang();
        when(em.createQuery("select kh from KhachHang kh", KhachHang.class)).thenReturn(mock(jakarta.persistence.TypedQuery.class));
        when(em.createQuery("select kh from KhachHang kh", KhachHang.class).getResultList()).thenReturn(Collections.singletonList(khachHang));

        List<KhachHang> result = khachHangDAO.getAll();

        assertEquals(1, result.size());
        assertEquals(khachHang, result.get(0));
        result.forEach(x->System.out.println(x));
    }
//
    @Test
    public void testFindById() {
        String maKH = "KH200619";
        KhachHang khachHang = new KhachHang();
        when(em.find(KhachHang.class, maKH)).thenReturn(khachHang);

        KhachHang result = khachHangDAO.findById(maKH);

        assertEquals(khachHang, result);
        System.out.println(result);
    }
}
