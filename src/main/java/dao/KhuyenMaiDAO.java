package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.KhuyenMai;

import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
            .createEntityManager();
    //
    public boolean createKhuyenMai(KhuyenMai khuyenmai){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(khuyenmai);
            tr.commit();
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    //
    public boolean updateKhuyenMai(KhuyenMai khuyenmai){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(khuyenmai);
            tr.commit();
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    //
    public List<KhuyenMai> getAllKhuyenMai(){
        String query = "SELECT km FROM KhuyenMai km";
        return em.createQuery(query, KhuyenMai.class).getResultList();
    }
    public KhuyenMai getKhuyenMai(int maKM) {
        return em.find(KhuyenMai.class, maKM);
    }

    }
