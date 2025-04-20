package dao;

import jakarta.persistence.*;
import model.KhuyenMai;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO extends GenericDao<KhuyenMai, String>{
    public KhuyenMaiDAO(Class<KhuyenMai> clazz) {
        super(clazz);
    }

    //Lay toan bo khuyen mai
    public List<KhuyenMai> layTatCaKhuyenMai() {
        String jpql = "SELECT k FROM KhuyenMai k";
        TypedQuery<KhuyenMai> query = em.createQuery(jpql, KhuyenMai.class);
        return query.getResultList();
    }

    // Lay danh sach khuyen mai dang ap dung thoi diem hien tai
    public List<KhuyenMai> layKhuyenMaiDangApDung() {
        String jpql = "SELECT k FROM KhuyenMai k WHERE :today BETWEEN k.ngayBD AND k.ngayKT";
        TypedQuery<KhuyenMai> query = em.createQuery(jpql, KhuyenMai.class);
        query.setParameter("today", LocalDate.now());
        return query.getResultList();
    }

    // Tim khuyen mai theo ten
    public List<KhuyenMai> timKhuyenMaiTheoTen(String ten) {
        String jpql = "SELECT k FROM KhuyenMai k WHERE LOWER(k.tenKM) LIKE LOWER(:ten)";
        TypedQuery<KhuyenMai> query = em.createQuery(jpql, KhuyenMai.class);
        query.setParameter("ten", "%" + ten + "%");
        return query.getResultList();
    }

    // Tim khuyen mai theo ma
    public KhuyenMai timKhuyenMaiTheoMa(String ma) {
        String jpql = "SELECT k FROM KhuyenMai k WHERE k.maKM = :ma";
        TypedQuery<KhuyenMai> query = em.createQuery(jpql, KhuyenMai.class);
        query.setParameter("ma", ma);
        List<KhuyenMai> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    // Lọc khuyến mãi theo thời gian bắt đầu và kết thúc
    public List<KhuyenMai> locKhuyenMaiTheoThoiGian(LocalDate tuNgay, LocalDate denNgay) {
        String jpql = "SELECT k FROM KhuyenMai k WHERE k.ngayBD >= :tuNgay AND k.ngayKT <= :denNgay";
        TypedQuery<KhuyenMai> query = em.createQuery(jpql, KhuyenMai.class);
        query.setParameter("tuNgay", tuNgay);
        query.setParameter("denNgay", denNgay);
        return query.getResultList();
    }

}
