package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.LoaiMonAn;

import java.util.List;

public class LoaiMonAnDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
            .createEntityManager();

    public boolean updateLoaiMonAn(LoaiMonAn loaimonan){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(loaimonan);
            tr.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    //
    public static List<LoaiMonAn> getAll(){
        String query = "select * from LoaiMonAn;
        return em.createQuery(query, LoaiMonAn.class).getResultList();
    }

    public static LoaiMonAn findByID(String maLoaiMon){
        return em.find(LoaiMonAn.class, maLoaiMon;
    }


}
