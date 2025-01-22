package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.LoaiKhuyenMai;

import java.util.List;

public class LoaiKhuyenMaiDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
            .createEntityManager();

    public boolean updateLoaiKhuyenMai(LoaiKhuyenMai loaikhuyenmai){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(loaikhuyenmai);
            tr.commit();
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }


    public List<LoaiKhuyenMai> getAll(){
        String query = "select loaiKM from LoaiKhuyenMai loaiKM";
        return em.createQuery(query, LoaiKhuyenMai.class).getResultList();
    }

    public LoaiKhuyenMai findByID(String maLoaiKM){
        return em.find(LoaiKhuyenMai.class, maLoaiKM);
    }

}
