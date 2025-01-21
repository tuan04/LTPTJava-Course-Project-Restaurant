package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Ban;

import java.util.ArrayList;
import java.util.List;

public class BanDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
            .createEntityManager();

    public boolean createBan(Ban ban){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(ban);
            tr.commit();
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean updateBan(Ban ban){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(ban);
            tr.commit();
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    public boolean deleteBan(String maBan){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            Ban ban = em.find(Ban.class, maBan);
            em.remove(ban);
            tr.commit();
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public List<Ban> getAllBan(){
        String query = "SELECT b FROM Ban b";
        return em.createQuery(query, Ban.class).getResultList();
    }

    public Ban timTheoMa(String maBan){
        return em.find(Ban.class, maBan);
    }
}
