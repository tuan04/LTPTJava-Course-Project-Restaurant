package dao;

import jakarta.persistence.*;
import model.Ban;

import java.util.ArrayList;
import java.util.List;

public class BanDAO extends GenericDao<Ban, String>{

    public BanDAO(Class<Ban> clazz) {
        super(clazz);
    }

    // danh sach ban theo ma loai
    public List<Ban> getListBanTheoLoai(String maLoai) {
        String jpql = "SELECT b FROM Ban b WHERE b.loaiBan.maLoaiBan = :maLoai";
        TypedQuery<Ban> query = em.createQuery(jpql, Ban.class);
        query.setParameter("maLoai", maLoai);
        return query.getResultList();
    }

    // dem so luong ban theo ma loai va trang thai
    public long getSoLuongBanTheoLBvTrangThai(String maLoaiBan, String trangThai) {
        String jpql = "SELECT COUNT(b) FROM Ban b WHERE b.loaiBan.maLoaiBan = :maLoaiBan AND b.trangThai = :trangThai";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("maLoaiBan", maLoaiBan);
        query.setParameter("trangThai", trangThai);
        return query.getSingleResult();
    }


    // lay danh sach ban theo ma loai va trang thai
    public List<Object[]> getBan(String maLoaiBan, String trangThai) {
        List<Object[]> result = new ArrayList<>();
        String jpql;

        if (trangThai != null && !trangThai.trim().isEmpty()) {
            jpql = "SELECT b.tenBan, b.trangThai FROM Ban b WHERE b.loaiBan.maLoaiBan = :maLoaiBan AND b.trangThai = :trangThai";
        } else {
            jpql = "SELECT b.tenBan, b.trangThai FROM Ban b WHERE b.loaiBan.maLoaiBan = :maLoaiBan";
        }

        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("maLoaiBan", maLoaiBan);
        if (trangThai != null && !trangThai.trim().isEmpty()) {
            query.setParameter("trangThai", trangThai);
        }

        List<Object[]> list = query.getResultList();
        for (Object[] row : list) {
            Object[] ban = new Object[2];
            ban[0] = row[0];
            ban[1] = "0".equals(row[1]) ? "0" : "1";
            result.add(ban);
        }
        return result;
    }

//    public static void main(String[] args) {
//        BanDAO banDao = new BanDAO(Ban.class);
//        List<Ban> bans = banDao.getAll();
//        for(Ban ban : bans){
//            System.out.println(ban);
//        }
//    }
}
