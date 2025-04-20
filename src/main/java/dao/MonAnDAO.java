package dao;

import jakarta.persistence.*;
import model.KhuyenMai;
import model.MonAn;

import java.util.List;

public class MonAnDAO extends GenericDao<MonAn, String>{

    public MonAnDAO(Class<MonAn> clazz) {
        super(clazz);
    }

    // Lấy món ăn theo mã
    public MonAn getMonAn(String maMA) {
        try {
            TypedQuery<MonAn> query = em.createQuery(
                    "SELECT m FROM MonAn m WHERE m.maMonAn = :ma", MonAn.class);
            query.setParameter("ma", maMA);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    // Lấy danh sách món ăn theo mã loại món
    public List<MonAn> getMonTheoLoai(String maLoai) {
        return em.createQuery(
                        "SELECT m FROM MonAn m WHERE m.loaiMonAn.maLoaiMon = :maLoai", MonAn.class)
                .setParameter("maLoai", maLoai)
                .getResultList();
    }

    // Lấy tất cả món ăn
    public List<MonAn> getList() {
        return em.createQuery(
                        "SELECT m FROM MonAn m JOIN FETCH m.loaiMonAn", MonAn.class)
                .getResultList();
    }

    // Tìm món ăn theo tên
    public List<MonAn> timTheoTen(String ten) {
        return em.createQuery(
                        "SELECT m FROM MonAn m WHERE LOWER(m.tenMonAn) LIKE LOWER(CONCAT('%', :ten, '%'))",
                        MonAn.class)
                .setParameter("ten", ten)
                .getResultList();
    }

    // Tìm món ăn theo trạng thái
    public List<MonAn> getListMonTheoTrangThai(boolean trangThai) {
        return em.createQuery(
                        "SELECT m FROM MonAn m WHERE m.trangThai = :tt", MonAn.class)
                .setParameter("tt", trangThai)
                .getResultList();
    }

    // Lấy danh sách món ăn theo tên loại món
    public List<Object[]> getDSMonAn(String tenLoaiMon) {
        return em.createQuery(
                        "SELECT m.maMonAn, m.tenMonAn, m.loaiMonAn.tenLoaiMon " +
                                "FROM MonAn m WHERE m.loaiMonAn.tenLoaiMon = :tenLoai", Object[].class)
                .setParameter("tenLoai", tenLoaiMon)
                .getResultList();
    }

    // Cập nhật khuyến mãi cho món ăn
    public boolean updateMonAnKhuyenMai(String maMA, String maKM) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            MonAn m = em.find(MonAn.class, maMA);
            KhuyenMai km = em.find(KhuyenMai.class, maKM);
            if (m != null && km != null) {
                m.setKhuyenMai(km);
                em.merge(m);
                tx.commit();
                return true;
            }
            tx.rollback();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    // Thêm một món ăn
    public boolean themMonAn(MonAn monAn) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(monAn);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật thông tin món ăn
    public boolean capNhatMonAn(MonAn monAn) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(monAn);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }

    // Xóa món ăn theo mã
    public boolean xoaMonAn(String maMA) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            MonAn monAn = em.find(MonAn.class, maMA);
            if (monAn != null) {
                em.remove(monAn);
                tx.commit();
                return true;
            }
            tx.rollback();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
    //Tim mon an theo ma
    public Object[] timMonAnTheoMa(String maMA, String loaiMon, String trangThaiMon) {
        String jpql = "SELECT m.maMonAn, m.tenMonAn, m.gia, " +
                "COALESCE(km.giamGia, 0), lm.tenLoaiMon, m.trangThai " +
                "FROM MonAn m LEFT JOIN m.khuyenMai km JOIN m.loaiMonAn lm " +
                "WHERE m.maMonAn = :maMA";

        if (loaiMon != null && !loaiMon.trim().isEmpty()) {
            jpql += " AND lm.tenLoaiMon = :tenLoai";
        }

        if (trangThaiMon != null && !trangThaiMon.trim().isEmpty()) {
            boolean tt = trangThaiMon.equalsIgnoreCase("1");
            jpql += " AND m.trangThai = :trangThai";
        }

        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        query.setParameter("maMA", maMA);
        if (loaiMon != null && !loaiMon.trim().isEmpty()) {
            query.setParameter("tenLoai", loaiMon);
        }
        if (trangThaiMon != null && !trangThaiMon.trim().isEmpty()) {
            boolean tt = trangThaiMon.equalsIgnoreCase("1");
            query.setParameter("trangThai", tt);
        }

        List<Object[]> result = query.getResultList();
        if (!result.isEmpty()) {
            Object[] ob = result.get(0);
            ob[5] = ((Boolean) ob[5]) ? "1" : "0";
            ob[3] = ob[3] + " %";
            return ob;
        }
        return null;
    }

    //tim mon theo ten
    public List<Object[]> timKiemMonTheoTen(String tenMon, String loaiMon, String trangThaiMon, String sortKey, String sortValue) {
        StringBuilder jpql = new StringBuilder(
                "SELECT m.maMonAn, m.tenMonAn, m.gia, COALESCE(km.giamGia, 0), lm.tenLoaiMon, m.trangThai " +
                        "FROM MonAn m LEFT JOIN m.khuyenMai km JOIN m.loaiMonAn lm " +
                        "WHERE LOWER(m.tenMonAn) LIKE LOWER(CONCAT('%', :tenMon, '%'))"
        );

        if (loaiMon != null && !loaiMon.trim().isEmpty()) {
            jpql.append(" AND lm.tenLoaiMon = :tenLoai");
        }
        if (trangThaiMon != null && !trangThaiMon.trim().isEmpty()) {
            jpql.append(" AND m.trangThai = :trangThai");
        }
        if (sortKey != null && !sortKey.trim().isEmpty()) {
            List<String> allowedSortKeys = List.of("m.tenMonAn", "m.gia", "m.maMonAn");
            if (allowedSortKeys.contains("m." + sortKey)) {
                jpql.append(" ORDER BY m.").append(sortKey).append(" ").append(sortValue.equalsIgnoreCase("desc") ? "DESC" : "ASC");
            }
        }

        TypedQuery<Object[]> query = em.createQuery(jpql.toString(), Object[].class);
        query.setParameter("tenMon", tenMon);

        if (loaiMon != null && !loaiMon.trim().isEmpty()) {
            query.setParameter("tenLoai", loaiMon);
        }
        if (trangThaiMon != null && !trangThaiMon.trim().isEmpty()) {
            boolean tt = trangThaiMon.equalsIgnoreCase("1");
            query.setParameter("trangThai", tt);
        }

        List<Object[]> result = query.getResultList();

        for (Object[] ob : result) {
            ob[5] = ((Boolean) ob[5]) ? "1" : "0";
            ob[3] = ob[3] + "%";
        }

        return result;
    }

    //lay danh sach ten loai
    public List<String> getLoaiMon() {
        return em.createQuery("SELECT l.tenLoaiMon FROM LoaiMonAn l", String.class)
                .getResultList();
    }



}
