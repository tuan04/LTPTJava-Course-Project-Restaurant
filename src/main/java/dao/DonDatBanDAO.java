package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.DonDatBan;

import java.util.List;


public class DonDatBanDAO {

    private static EntityManager em = Persistence.createEntityManagerFactory(
            "mariadb-pu"
    ).createEntityManager();

    public static boolean creat(DonDatBan donDatBan){
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(donDatBan);
            tr.commit();
            return  true;
        }catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public static boolean update(DonDatBan donDatBan){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(donDatBan);
            tr.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }

    public static List<DonDatBan> getAll(){
        String query = "select don from DonDatBan don";
        return em.createQuery(query,DonDatBan.class).getResultList();
    }

    public static DonDatBan findById(String maDon){
        return em.find(DonDatBan.class, maDon);
    }
}
