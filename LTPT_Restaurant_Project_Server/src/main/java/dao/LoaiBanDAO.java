package dao;

import jakarta.persistence.*;
import model.LoaiBan;

import java.util.List;

public class LoaiBanDAO extends GenericDao<LoaiBan, String>{

    public LoaiBanDAO(Class<LoaiBan> clazz) {
        super(clazz);
    }

    public LoaiBan timTheoMa(String maLB) {
        String jpql = "SELECT lb FROM LoaiBan lb WHERE lb.maLoaiBan = :ma";
        TypedQuery<LoaiBan> query = em.createQuery(jpql, LoaiBan.class);
        query.setParameter("ma", maLB);
        List<LoaiBan> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public List<LoaiBan> layTatCaLoaiBan() {
        String jpql = "SELECT lb FROM LoaiBan lb";
        return em.createQuery(jpql, LoaiBan.class).getResultList();
    }


    public List<LoaiBan> timTheoTenGanDung(String tenLoai) {
        String jpql = "SELECT lb FROM LoaiBan lb WHERE LOWER(lb.tenLoaiBan) LIKE LOWER(:ten)";
        return em.createQuery(jpql, LoaiBan.class)
                .setParameter("ten", "%" + tenLoai + "%")
                .getResultList();
    }


    public boolean kiemTraTonTai(String maLoaiBan) {
        String jpql = "SELECT COUNT(lb) FROM LoaiBan lb WHERE lb.maLoaiBan = :ma";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("ma", maLoaiBan)
                .getSingleResult();
        return count > 0;
    }
}
