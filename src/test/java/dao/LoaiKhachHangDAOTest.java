package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.KhachHang;
import model.LoaiKhachHang;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class LoaiKhachHangDAOTest {
    private EntityManager em;
    private EntityTransaction tr;
    private LoaiKhachHangDAO loaiKhachHangDAO;

    @BeforeEach
    public void setUp(){
        em = mock(EntityManager.class);
        tr = mock(EntityTransaction.class);
        loaiKhachHangDAO = new LoaiKhachHangDAO(em);
        when(em.getTransaction()).thenReturn(tr);
    }
    @Test
    public void update() {
        LoaiKhachHang loaiKhachHang = new LoaiKhachHang("LKH4",
                "Bạch kim",
                0.2,
                0.2);
        boolean result = loaiKhachHangDAO.update(loaiKhachHang);

        verify(tr).begin();
        verify(em).merge(loaiKhachHang);
        verify(tr).commit();
        assertTrue(result);
    }
    @Test
    public void testUpdateRollbackOnException() {
        LoaiKhachHang loaiKhachHang = new LoaiKhachHang();
        doThrow(new RuntimeException()).when(em).merge(loaiKhachHang);

        boolean result = loaiKhachHangDAO.update(loaiKhachHang);

        verify(tr).begin();
        verify(tr).rollback();
        assertFalse(result);
    }

    @Test
    public void testGetAll() {

        List<LoaiKhachHang> list = new ArrayList<>();
        list.add(new LoaiKhachHang("LKH1", "Thành viên", 0, 0.05));
        list.add(new LoaiKhachHang("LKH2", "Vàng", 0.1, 0.1));
        list.add(new LoaiKhachHang("LKH3", "Kim cương", 0.15, 0.15));


        TypedQuery<LoaiKhachHang> typedQuery = mock(TypedQuery.class);


        when(em.createQuery("SELECT lkh FROM LoaiKhachHang lkh", LoaiKhachHang.class)).thenReturn(typedQuery);


        when(typedQuery.getResultList()).thenReturn(list);


        List<LoaiKhachHang> resultList = loaiKhachHangDAO.getAll();


        assertEquals(3, resultList.size());
        assertEquals(list, resultList);
        resultList.forEach(System.out::println);
    }

    @Test
    public void testFindById() {
        String maLKH = "LKH1";
        LoaiKhachHang loaiKhachHang1 = new LoaiKhachHang("LKH1", "Thành viên", 0, 0.05);
        LoaiKhachHang loaiKhachHang2 = new LoaiKhachHang("LKH2", "Vàng", 0.1, 0.1);
        LoaiKhachHang loaiKhachHang3 = new LoaiKhachHang("LKH3", "Kim cương", 0.15, 0.15);


        List<LoaiKhachHang> list = new ArrayList<>();
        list.add(loaiKhachHang1);
        list.add(loaiKhachHang2);
        list.add(loaiKhachHang3);


        when(em.find(LoaiKhachHang.class, maLKH)).thenReturn(loaiKhachHang1);


        LoaiKhachHang result = loaiKhachHangDAO.findByID(maLKH);


        assertEquals(loaiKhachHang1, result);
        System.out.println(result);
    }
}