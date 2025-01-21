package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.LoaiBan;

import java.util.List;

public class LoaiBanDAO {
    EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
            .createEntityManager();
    public List<LoaiBan> getAllLoaiBan(){
        String query = "select lb from LoaiBan lb";
        return em.createQuery(query, LoaiBan.class).getResultList();
    }

    public LoaiBan getLoaiBanTheoMa(String maLoaiBan){
        return em.find(LoaiBan.class, maLoaiBan);
    }
}
