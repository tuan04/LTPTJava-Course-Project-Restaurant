package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.LoaiKhachHang;

import java.util.List;

public class LoaiKhachHangDAO {
    private static EntityManager em = Persistence.createEntityManagerFactory(
            "mariadb-pu"
    ).createEntityManager();

    public static boolean update(LoaiKhachHangDAO loaiKhachHangDAO){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(loaiKhachHangDAO);
            tr.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public static List<LoaiKhachHang> getAll(){
        String query = "select lKH from LoaiKhachHang lKH";
        return em.createQuery(query, LoaiKhachHang.class).getResultList();
    }

    public static LoaiKhachHang findByID(String maLoai){
        return em.find(LoaiKhachHang.class, maLoai);
    }


}
