package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.MonAn;

import java.util.ArrayList;
import java.util.List;

public class MonAnDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
            .createEntityManager();;

    public boolean createMonAn(MonAn monan){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(monan);
            tr.commit();
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public boolean updateMonAn(MonAn monan){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(monan);
            tr.commit();
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }


    public List<MonAn> getAllMonAn(){
        String query = "SELECT b FROM MonAn b";
        return em.createQuery(query, MonAn.class).getResultList();
    }

    public MonAn FindByID(String maMonAn){
        return em.find(MonAn.class, maMonAn);
    }
}
