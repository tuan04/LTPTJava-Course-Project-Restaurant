package dao;

import model.ChiTietHoaDon;

import java.util.List;

public class ChiTietHoaDonDAO extends GenericDao<ChiTietHoaDon, ChiTietHoaDon.ChiTietHoaDonId> {
    public ChiTietHoaDonDAO(Class<ChiTietHoaDon> clazz) {
        super(clazz);
    }

    public List<ChiTietHoaDon> getListTheoHoaDon(String maHD) {
        String jpql = "SELECT cthd FROM ChiTietHoaDon cthd WHERE cthd.hoaDon.maHD = :maHD";
        return em.createQuery(jpql, ChiTietHoaDon.class).setParameter("maHD", maHD).getResultList();
    }
}

