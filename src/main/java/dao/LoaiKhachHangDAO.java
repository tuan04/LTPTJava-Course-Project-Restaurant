package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.LoaiKhachHang;

import java.util.List;

public class LoaiKhachHangDAO {
    private static EntityManager em;

    // Constructor để inject EntityManager
    public LoaiKhachHangDAO(EntityManager em) {
        this.em = em;
    }

    public static boolean update(LoaiKhachHang loaiKhachHang){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(loaiKhachHang);
            tr.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public static List<LoaiKhachHang> getAll(){
        String query = "SELECT lkh FROM LoaiKhachHang lkh";
        TypedQuery<LoaiKhachHang> typedQuery = em.createQuery(query, LoaiKhachHang.class);
        return typedQuery.getResultList();
    }

    public static LoaiKhachHang findByID(String maLoai){
        return em.find(LoaiKhachHang.class, maLoai);
    }


}
