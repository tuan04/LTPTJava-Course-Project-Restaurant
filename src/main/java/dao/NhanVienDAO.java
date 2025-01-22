package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.NhanVien;

import java.util.List;

public class NhanVienDAO {
    private EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
            .createEntityManager();
    public boolean createNhanVien(NhanVien nhanvien) {
        try {
            em.getTransaction().begin();
            em.persist(nhanvien);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return false;


    }
    public boolean updateNhanVien(NhanVien nhanvien) {
        try {
            em.getTransaction().begin();
            em.merge(nhanvien);
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return false;
    }
    public List<NhanVien> getAllNhanVien() {
        String query = "Select nv from NhanVien nv";
        return em.createQuery(query, NhanVien.class).getResultList();
    }
    public NhanVien getNhanVien(String maNV) {
        return em.find(NhanVien.class, maNV);
    }
}
