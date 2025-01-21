package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.HoaDon;

public class HoaDonDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
            .createEntityManager();;

    public HoaDon getHoaDon(int maHD) {
        return em.find(HoaDon.class, maHD);
    }
    public boolean createrOrder(HoaDon hd){
        try {
            em.getTransaction().begin();
            em.persist(hd);
            em.getTransaction().commit();
            return true;
        }catch (Exception e){

            em.getTransaction().rollback();
        }
        return false;

    }
    public boolean updateOrder(HoaDon hd){
        try {
            em.getTransaction().begin();
            em.merge(hd);
            em.getTransaction().commit();

            return true;


        }catch (Exception e){

            em.getTransaction().rollback();
        }
        return false;
    }

}
