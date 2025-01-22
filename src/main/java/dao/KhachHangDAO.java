package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.KhachHang;
import model.LoaiKhachHang;

import java.util.List;

public class KhachHangDAO {
    private static EntityManager em;

    // Constructor để inject EntityManager
    public KhachHangDAO(EntityManager em) {
        this.em = em;
    }

    public static boolean create(KhachHang khachHang){
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(khachHang);
            tr.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public static boolean update(KhachHang khachHang){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(khachHang);
            tr.commit();
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            if (tr.isActive()){
                tr.rollback();
            }
        }
        return false;
    }

    public static List<KhachHang> getAll(){
        String query = "SELECT kh FROM KhachHang kh";
        TypedQuery<KhachHang> typedQuery = em.createQuery(query, KhachHang.class);
        return typedQuery.getResultList();
    }

    public static KhachHang findById(String maKH){
        return em.find(KhachHang.class, maKH);
    }

}
