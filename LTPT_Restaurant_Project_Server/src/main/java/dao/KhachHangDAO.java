package dao;

import jakarta.persistence.*;
import model.KhachHang;
import model.LoaiKhachHang;

import java.time.LocalDate;
import java.util.List;

public class KhachHangDAO extends GenericDao<KhachHang, String>{
    public KhachHangDAO(Class<KhachHang> clazz) {
        super(clazz);
    }
    public boolean updateDiemTL() {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            String jpql = "UPDATE KhachHang kh SET kh.diemTL = 0 " +
                    "WHERE FUNCTION('day', kh.ngayTao) = FUNCTION('day', CURRENT_DATE) " +
                    "AND FUNCTION('month', kh.ngayTao) = FUNCTION('month', CURRENT_DATE)";

            int updatedCount = em.createQuery(jpql).executeUpdate();

            transaction.commit();

            return updatedCount > 0;

        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) transaction.rollback();
            return false;
        }
    }

    public KhachHang getKHSDT(String sdt) {
        String jpql = "SELECT kh FROM KhachHang kh WHERE kh.sdt = :sdt AND kh.trangThai = true";
        return em.createQuery(jpql, KhachHang.class).setParameter("sdt", sdt).getSingleResult();
    }
    //Lọc khách hàng theo loại
    public List<KhachHang> getListKHTheoLoai(String maLoaiKH) {
        String jpql = "SELECT kh FROM KhachHang kh WHERE kh.loaiKH.maLoaiKH = :maLoaiKH ";
        return em.createQuery(jpql, KhachHang.class).setParameter("maLoaiKH", maLoaiKH).getResultList();
    }

    public String maTuSinh() {
        try {
            String jpql = "SELECT kh.maKH FROM KhachHang kh ORDER BY kh.maKH DESC";
            List<String> result = em.createQuery(jpql, String.class)
                    .setMaxResults(1)
                    .getResultList();

            String maKH = result.isEmpty() ? "" : result.get(0);

            if (maKH.isEmpty()) {
                return "KH000001";
            }

            int soMoi = Integer.parseInt(maKH.substring(2)) + 1;

            if (soMoi < 10) {
                return "KH00000" + soMoi;
            } else if (soMoi < 100) {
                return "KH0000" + soMoi;
            } else if (soMoi < 1000) {
                return "KH000" + soMoi;
            } else if (soMoi < 10000) {
                return "KH00" + soMoi;
            } else if (soMoi < 100000) {
                return "KH0" + soMoi;
            } else {
                return "KH" + soMoi;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "KH000001"; // fallback
        }
    }

    //tìm trả về nhiều kết quả, lấy bất cứ khách hàng nào khớp. Ví dụ: 0368
    public List<KhachHang> timTheoSDT(String sdt){
        String jpql = "SELECT kh FROM KhachHang kh WHERE LOWER(kh.sdt) LIKE CONCAT('%', :sdt, '%') ";
        return em.createQuery(jpql, KhachHang.class).setParameter("sdt", sdt).getResultList();
    }

    public List<KhachHang> getListKHTheoTrangThai(boolean trangthai){
        String jpql = "SELECT kh FROM KhachHang kh WHERE kh.trangThai = :trangthai ";
        return em.createQuery(jpql, KhachHang.class).setParameter("trangthai", trangthai).getResultList();
    }

    public KhachHang giamGiaNgaySinh(LocalDate ngayThang) {
        String jpql = "SELECT kh FROM KhachHang kh " +
                "WHERE FUNCTION('MONTH', kh.ngaySinh) = :thang " +
                "AND FUNCTION('DAY', kh.ngaySinh) = :ngay";

        return em.createQuery(jpql, KhachHang.class)
                .setParameter("thang", ngayThang.getMonthValue())
                .setParameter("ngay", ngayThang.getDayOfMonth())
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }



    public static void main(String[] args) {
        KhachHangDAO dao = new KhachHangDAO(KhachHang.class);
        List<KhachHang> list = dao.timTheoSDT("036777");
        for (KhachHang kh : list) {
            System.out.println(kh);
        }
//        System.out.println(dao.maTuSinh());
    }

}
