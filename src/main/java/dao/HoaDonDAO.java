package dao;

import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityTransaction;
import model.HoaDon;

public class HoaDonDAO extends GenericDao<HoaDon, String>{
    public HoaDonDAO(Class<HoaDon> clazz) {
        super(clazz);
    }

    public boolean capNhatTongTienHD(String maHD, double tongTien) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            String sql = "UPDATE HoaDon h SET h.tongTien = :tongTien WHERE h.maHD = :maHD";

            int updatedCount = em.createQuery(sql)
                    .setParameter("tongTien", tongTien)
                    .setParameter("maHD", maHD)
                    .executeUpdate();

            transaction.commit();

            return updatedCount > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        HoaDonDAO dao = new HoaDonDAO(HoaDon.class);
        boolean result = dao.capNhatTongTienHD("HD241212001", 410000);
        System.out.println("Cập nhật thành công: " + result);
    }
}

