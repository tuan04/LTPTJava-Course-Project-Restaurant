package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import model.NhanVien;

import java.util.List;
import java.util.Random;

public class NhanVienDAO extends GenericDao<NhanVien, String> {

    public NhanVienDAO(Class<NhanVien> clazz) {
        super(clazz);
    }

    public NhanVien dangNhap(String maNV, String matKhau) {
        String jpql = "SELECT nv FROM NhanVien nv WHERE maNV = :maNV and matKhau = :matKhau";
        return em.createQuery(jpql, NhanVien.class).setParameter("maNV", maNV).setParameter("matKhau", matKhau).getSingleResult();
    }

    public List<NhanVien> TimTheoSDT(String sdt) {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.sdt= :sdt";
        return em.createQuery(jpql, NhanVien.class).setParameter("sdt", sdt).getResultList();
    }

    public List<NhanVien> getListNVTheoLoai(String maLoaiNV) {
        String spql = "SELECT nv FROM NhanVien nv WHERE nv.loaiNV = :maLoaiNV";
        return em.createQuery(spql, NhanVien.class).setParameter("maLoaiNV", maLoaiNV).getResultList();
    }

    public String maTuSinh() {
        String prefix = "NV"; // VD: NVLT, NVQL...
        String jpql = "SELECT nv.maNV FROM NhanVien nv WHERE nv.maNV LIKE :prefix ORDER BY nv.maNV DESC";

        String maNV = "";
        try {
            maNV = em.createQuery(jpql, String.class)
                    .setParameter("prefix", prefix + "%")
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Không có nhân viên nào thuộc loại này
            maNV = "";
        }

        int soMoi = maNV.isEmpty() ? 1 : Integer.parseInt(maNV.substring(5)) + 1;

        return String.format("%s%03d", prefix, soMoi); // định dạng thành NVXX001, NVXX010...
    }

    public List<NhanVien> TimTheoTenNV(String ten) {
        String jpql = "SELECT nv FROM NhanVien nv WHERE LOWER(nv.tenNV) LIKE :ten";
        return em.createQuery(jpql, NhanVien.class)
                .setParameter("ten", "%" + ten.toLowerCase() + "%")
                .getResultList();
    }

    public List<NhanVien> getListNVTheoTrangThai(boolean trangthai) {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.trangThai = :trangthai";
        return em.createQuery(jpql, NhanVien.class).setParameter("trangthai", trangthai).getResultList();
    }

    public boolean checkOTP(String maNV, String email, String otp) {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNv AND nv.email = :email AND nv.maXacThuc = :otp";
        return em.createQuery(jpql, NhanVien.class).setParameter("maNv", maNV).setParameter("email", email).setParameter("otp", otp).getSingleResult() != null;
    }

    public String getOldPass(String maNV){
        String jpql = "SELECT nv.matKhau FROM NhanVien nv WHERE nv.maNV = :maNv";
        return em.createQuery(jpql, String.class).setParameter("maNv", maNV).getSingleResult();
    }

    public boolean checkEmail(String maNV, String email){
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNv AND nv.email = :email";
        return em.createQuery(jpql, NhanVien.class).setParameter("maNv", maNV).setParameter("email", email).getSingleResult() != null;
    }

    public boolean checkMaNV(String maNV){
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNv";
        return em.createQuery(jpql, NhanVien.class).setParameter("maNv", maNV).getSingleResult() != null;
    }

    public boolean updateOTP(String maNV, String otp){
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            String jpql = "UPDATE NhanVien nv SET nv.maXacThuc = :otp WHERE nv.maNV = :maNV";
            int updated = em.createQuery(jpql)
                    .setParameter("otp", otp)
                    .setParameter("maNV", maNV)
                    .executeUpdate();
            tx.commit();
            return updated > 0;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteOTP(String maNV){
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            String jpql = "UPDATE NhanVien nv SET nv.maXacThuc = NULL WHERE nv.maNV = :maNV";
            int updated = em.createQuery(jpql)
                    .setParameter("maNV", maNV)
                    .executeUpdate();
            tx.commit();
            return updated > 0;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public String generateOTP(int length) {
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }

}
