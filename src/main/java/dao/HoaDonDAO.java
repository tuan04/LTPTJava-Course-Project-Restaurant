package dao;

import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Ban;
import model.HoaDon;
import util.JPAUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class HoaDonDAO extends GenericDao<HoaDon, String> {
    public HoaDonDAO(Class<HoaDon> clazz) {
        super(clazz);
    }


    public  boolean capNhatTongTienHD(String maHD, double tongTien) {
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



    public boolean updateNhanVienHoaDon(String maNV, String maHD) {
        EntityTransaction transaction = em.getTransaction();
        int n = 0;
        try {
            transaction.begin();
            String sql="update HoaDon h set h.nhanVien.maNV =:maNhanVien where h.maHD =:maHD";
            int updatedCount = em.createQuery(sql)
                    .setParameter("maNhanVien",maNV)
                    .setParameter("maHD",maHD).executeUpdate();
                transaction.commit();
                return updatedCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public HoaDon getHoaDonTheoMa(String mahd) {
    return findById(mahd);
    }


    public boolean capNhatHoaDon(String maHoaDon, double tongTienTT, String maKhachHang, String maKhuyenMai, double giamGia, String maBan) throws SQLException {
        EntityTransaction transaction = em.getTransaction();
        int n = 0;
        try {
            transaction.begin();
            String sqlUpdateHoaDon = "UPDATE HoaDon h SET h.tongTienThanhToan =:tongTienTT, h.trangThai = true, h.khachHang.maKH =: maKhachHang, h.khuyenMai.maKM =: maKhuyenMai, h.tongTienGiamGia=:giamGia, h.gioRa = CURRENT_TIMESTAMP WHERE h.maHD =: maHD ";
            em.createQuery(sqlUpdateHoaDon)
                        .setParameter("tongTienTT", tongTienTT)
                        .setParameter("maKhachHang", maKhachHang)
                        .setParameter("maKhuyenMai", maKhuyenMai)
                        .setParameter("giamGia", giamGia)
                        .setParameter("maHD", maHoaDon)
                        .executeUpdate();


            String sqlUpdateBan = "UPDATE Ban b SET b.trangThai = 0 WHERE b.maBan = :maBan";
                    em.createQuery(sqlUpdateBan)
                            .setParameter("maBan", maBan).executeUpdate();
                    transaction.commit();
            return true;

        } catch (Exception e) {
        e.printStackTrace();

            if (transaction.isActive()) {
                transaction.rollback(); // Rollback nếu gặp lỗi
            }
        }
        return false;
    }
    public HoaDon getHoaDonTheoBanHoatDong(String maBan) {


        try {

          String sql=("select h from HoaDon h where h.trangThai = false and h.ban.maBan =: maBan");
            return em.createQuery(sql,HoaDon.class).setParameter("maBan", maBan).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<HoaDon> thongKeQuy(String quarter, String year, String loaiBan) {
        try {
            int quy = Integer.parseInt(quarter);
            int nam = Integer.parseInt(year);

            // Xác định tháng bắt đầu và kết thúc theo quý
            int thangBatDau = (quy - 1) * 3 + 1;
            int thangKetThuc = thangBatDau + 2;

            String jpql = "SELECT h FROM HoaDon h " +
                    "WHERE FUNCTION('MONTH', h.ngayTao) BETWEEN :startMonth AND :endMonth " +
                    "AND FUNCTION('YEAR', h.ngayTao) = :year";

            if (!loaiBan.equals("Tất cả")) {
                jpql += " AND h.ban.maBan LIKE :maBanPattern";
            }

            TypedQuery<HoaDon> query = em.createQuery(jpql, HoaDon.class);
            query.setParameter("startMonth", thangBatDau);
            query.setParameter("endMonth", thangKetThuc);
            query.setParameter("year", nam);

            if (!loaiBan.equals("Tất cả")) {
                String pattern = loaiBan.equals("Tầng 1") ? "T1%" :
                        loaiBan.equals("Tầng 2") ? "T2%" :"T3%";
                query.setParameter("maBanPattern", pattern);
            }

            List<HoaDon> list = query.getResultList();
            return list.isEmpty() ? null : list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDon> thongKeNam(String year, String loaiBan) {
        ArrayList<HoaDon> list = new ArrayList<>();
        try {
            String jpql = "SELECT h FROM HoaDon h WHERE FUNCTION('YEAR', h.ngayTao) = :year";

            if (!loaiBan.equals("Tất cả")) {
                jpql += " AND h.ban.maBan LIKE :maBan";
            }

            TypedQuery<HoaDon> query = em.createQuery(jpql, HoaDon.class);
            query.setParameter("year", Integer.parseInt(year));

            if (!loaiBan.equals("Tất cả")) {
                String pattern = loaiBan.equals("Tầng 1") ? "T1%" :
                        loaiBan.equals("Tầng 2") ? "T2%" : "T3%";
                query.setParameter("maBan", pattern);
            }

            list = new ArrayList<>(query.getResultList());
            return list.isEmpty() ? null : list;

        } catch (Exception e) {
            System.out.println("Lỗi khi thống kê theo năm bằng JPA: " + e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }




    public List<HoaDon> thongKeThang(String month, String year, String loaiBan) {
        List<HoaDon> result;

        try {
            StringBuilder jpql = new StringBuilder("SELECT h FROM HoaDon h WHERE FUNCTION('MONTH', h.ngayTao) = :month AND FUNCTION('YEAR', h.ngayTao) = :year");

            if (!loaiBan.equals("Tất cả")) {
                jpql.append(" AND h.ban.maBan LIKE :maBan");
            }

            TypedQuery<HoaDon> query = em.createQuery(jpql.toString(), HoaDon.class);
            query.setParameter("month", Integer.parseInt(month));
            query.setParameter("year", Integer.parseInt(year));

            if (!loaiBan.equals("Tất cả")) {
                String maBanPattern = switch (loaiBan) {
                    case "Tầng 1" -> "T1%";
                    case "Tầng 2" -> "T2%";
                    default -> "T3%";
                };
                query.setParameter("maBan", maBanPattern);
            }

            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            result = new ArrayList<>();
        }

        return result;
    }




    public List<HoaDon> thongKeNgay(String day, String loaiBan) {
        List<HoaDon> result = new ArrayList<>();
        try {
            // Chuyển đổi chuỗi ngày thành LocalDate
            LocalDate localDate = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // Tạo câu truy vấn JPQL
            StringBuilder jpql = new StringBuilder("SELECT h FROM HoaDon h WHERE h.ngayTao = :ngay");

            // Kiểm tra loại bàn, nếu không phải "Tất cả" thì thêm điều kiện
            if (!loaiBan.equals("Tất cả")) {
                jpql.append(" AND h.ban.maBan LIKE :maBan");
            }

            // Tạo câu truy vấn TypedQuery
            TypedQuery<HoaDon> query = em.createQuery(jpql.toString(), HoaDon.class);
            query.setParameter("ngay", localDate);

            // Nếu loại bàn không phải "Tất cả", thì thêm tham số "maBan"
            if (!loaiBan.equals("Tất cả")) {
                String prefix = switch (loaiBan) {
                    case "Tầng 1" -> "T1%";
                    case "Tầng 2" -> "T2%";
                    default -> "T3%";
                };
                query.setParameter("maBan", prefix);
            }

            // Thực thi truy vấn và lấy kết quả
            result = query.getResultList();
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        } finally {
            // Đảm bảo entity manager được đóng
            if (em.isOpen()) em.close();
        }

        return result.isEmpty() ? null : result;
    }


    public List<Object[]> thongKeNamMon(String year, String maLoaiMon) {
        List<Object[]> result = new ArrayList<>();
        try {
            // Tạo câu truy vấn JPQL
            String jpql = "SELECT MA.maMonAn, MA.tenMonAn, MA.gia, HD.ngayTao, MA.loaiMonAn.maLoaiMon,SUM(CTHD.soLuong) AS soLuongBanRa, SUM(CTHD.thanhTien) AS doanhThu " +
                    "FROM HoaDon HD " +
                    "JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.hoaDon.maHD " +
                    "JOIN MonAn MA ON CTHD.monAn.maMonAn = MA.maMonAn " +
                    "JOIN LoaiMonAn LMA ON MA.loaiMonAn.maLoaiMon = LMA.maLoaiMon " +
                    "WHERE FUNCTION('YEAR', HD.ngayTao) = :year " +
                    "AND LMA.maLoaiMon = :maLoaiMon " +
                    "GROUP BY MA.maMonAn, MA.tenMonAn, MA.gia";


            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("year", Integer.parseInt(year));
            query.setParameter("maLoaiMon", maLoaiMon);

            result = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result.isEmpty() ? null : result;
    }
    public List<Object[]> thongKeQuyMon(String quarter, String year, String maLoaiMon) {
        List<Object[]> list = new ArrayList<>();
        try {
            // JPQL không hỗ trợ YEAR và MONTH, do đó chúng ta sẽ lọc dựa vào tháng
            String jpql = "SELECT MA.maMonAn, MA.tenMonAn, MA.gia ,SUM(CTHD.soLuong) AS soLuongBanRa, SUM(CTHD.thanhTien) AS doanhThu " +
                    "FROM HoaDon HD " +
                    "JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.hoaDon.maHD " +
                    "JOIN MonAn MA ON CTHD.monAn.maMonAn = MA.maMonAn " +
                    "JOIN LoaiMonAn LMA ON MA.loaiMonAn.maLoaiMon = LMA.maLoaiMon " +
                    "WHERE FUNCTION('YEAR', HD.ngayTao) = :year " +
                    "AND LMA.maLoaiMon = :maLoaiMon " +
                    "AND CASE " +
                    "        WHEN FUNCTION('MONTH', HD.ngayTao) BETWEEN 1 AND 3 THEN 1 " +
                    "        WHEN FUNCTION('MONTH', HD.ngayTao) BETWEEN 4 AND 6 THEN 2 " +
                    "        WHEN FUNCTION('MONTH', HD.ngayTao) BETWEEN 7 AND 9 THEN 3 " +
                    "        WHEN FUNCTION('MONTH', HD.ngayTao) BETWEEN 10 AND 12 THEN 4 " +
                    "    END = :quarter " +
                    "GROUP BY MA.maMonAn, MA.tenMonAn, MA.gia";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("year", Integer.parseInt(year));
            query.setParameter("maLoaiMon", maLoaiMon);
            query.setParameter("quarter", Integer.parseInt(quarter));

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list.isEmpty() ? null : list;
    }

    public List<Object[]> thongKeThangMon(String month, String year, String maLoaiMon) {
        List<Object[]> list = new ArrayList<>();
        try {
            String jpql = "SELECT MA.maMonAn, MA.tenMonAn, MA.gia, SUM(CTHD.soLuong) AS soLuongBanRa, SUM(CTHD.thanhTien) AS doanhThu " +
                    "FROM HoaDon HD " +
                    "JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.hoaDon.maHD " +
                    "JOIN MonAn MA ON CTHD.monAn.maMonAn = MA.maMonAn " +
                    "JOIN LoaiMonAn LMA ON MA.loaiMonAn.maLoaiMon = LMA.maLoaiMon " +
                    "WHERE FUNCTION('MONTH', HD.ngayTao) = :month " +
                    "AND FUNCTION('YEAR', HD.ngayTao) = :year " +
                    "AND LMA.maLoaiMon = :maLoaiMon " +
                    "GROUP BY MA.maMonAn, MA.tenMonAn, MA.gia";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("month", Integer.parseInt(month)); // Set the month parameter
            query.setParameter("year", Integer.parseInt(year)); // Set the year parameter
            query.setParameter("maLoaiMon", maLoaiMon); // Set the maLoaiMon parameter

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list.isEmpty() ? null : list;
    }


    public List<Object[]> thongKeNgayMon(String day, String maLoaiMon) {
        List<Object[]> list = new ArrayList<>();
        try {
            // JPQL query using FUNCTION to extract day
            String jpql = "SELECT MA.maMonAn, MA.tenMonAn, MA.gia, SUM(CTHD.soLuong) AS soLuongBanRa, SUM(CTHD.thanhTien) AS doanhThu " +
                    "FROM HoaDon HD " +
                    "JOIN ChiTietHoaDon CTHD ON HD.maHD = CTHD.hoaDon.maHD " +
                    "JOIN MonAn MA ON CTHD.monAn.maMonAn = MA.maMonAn " +
                    "JOIN LoaiMonAn LMA ON MA.loaiMonAn.maLoaiMon = LMA.maLoaiMon " +
                    "WHERE FUNCTION('DATE', HD.ngayTao) = :day " +
                    "AND LMA.maLoaiMon = :maLoaiMon " +
                    "GROUP BY MA.maMonAn, MA.tenMonAn, MA.gia";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("day", java.sql.Date.valueOf(day)); // Convert string to Date
            query.setParameter("maLoaiMon", maLoaiMon);

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list.isEmpty() ? null : list;
    }


    public List<Integer> loadNam() {
        List<Integer> list = new ArrayList<>();
        try {
            String jpql = "SELECT DISTINCT FUNCTION('YEAR', HD.ngayTao) FROM HoaDon HD";

            TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);

            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list.isEmpty() ? null : list;
    }


    public String hoaDonMoiNhat() {
        String maHD = null;
        try {

            String jpql = "SELECT HD.maHD FROM HoaDon HD ORDER BY HD.maHD DESC";

            TypedQuery<String> query = em.createQuery(jpql, String.class);
            query.setMaxResults(1);

            List<String> result = query.getResultList();

            if (!result.isEmpty()) {
                maHD = result.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maHD;
    }
    public HoaDon getHD(String maHD) {
        return  findById(maHD);
    }

    public List<HoaDon> todayList(String maNhanVien, String today) {
        List<HoaDon> list = new ArrayList<>();
        try {
            String jpql = "SELECT HD FROM HoaDon HD " +
                    "WHERE HD.nhanVien.maNV = :maNhanVien " +
                    "AND FUNCTION('DATE', HD.ngayTao) = :today";

            list = em.createQuery(jpql, HoaDon.class)
            .setParameter("maNhanVien", maNhanVien)
            .setParameter("today", LocalDate.parse(today)).getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list.isEmpty() ? null : list;
    }






    public static void main(String[] args) throws SQLException {
        EntityManager em = JPAUtil.getEntityManager();
        HoaDonDAO dao = new HoaDonDAO(HoaDon.class);
//        boolean result = dao.capNhatTongTienHD("HD241212001", 410000);
//        System.out.println("Cập nhật thành công: " + result);
//        System.out.println(dao.updateNhanVienHoaDon("AD001","HD241212001"));
//        System.out.println(dao.getHoaDonTheoMa("HD241212001"));

//        System.out.println("cap nhat:"+dao.capNhatHoaDon("HD241212001",474500,"KH000001",null,0,"T1001"));

//        HoaDon hd=dao.getHoaDonTheoBanHoatDong("T1001");
//        System.out.println(hd);
//        List<HoaDon> list=dao.thongKeQuy("4","2025","Tầng 1");
//        List<HoaDon> list=dao.thongKeNam("2024","Tất cả");
//        List<HoaDon> list=dao.thongKeThang("12","2024","Tầng 3");
//         List<HoaDon> list=dao.thongKeNgay("2024-12-13","Tầng 1");
//        if (list != null && !list.isEmpty()) {
//            list.forEach(System.out::println);
//        } else {
//            System.out.println("Không có hóa đơn nào.");
//        }
//
        List<Object[]> list=dao.thongKeNamMon("2024","LM001");
//        List<Object[]> list=dao.thongKeQuyMon("4","2024","LM001");
//        List<Object[]> list=dao.thongKeThangMon("12","2024","LM001");
//        List<Object[]> list=dao.thongKeNgayMon("2024-12-13","LM001");
//
        if (list != null && !list.isEmpty()) {
            for (Object[] row : list) {
                for (Object obj : row) {
                    System.out.print(obj + " ");  // In từng phần tử của mảng
                }
                System.out.println();  // In xuống dòng sau mỗi dòng kết quả
            }
        } else {
            System.out.println("Không có hóa đơn nào.");
        }
//        List<Integer> list=dao.loadNam();
//                if (list != null && !list.isEmpty()) {
//            list.forEach(System.out::println);
//        } else {
//            System.out.println("Không có hóa đơn nào.");
//        }
//        System.out.println(dao.hoaDonMoiNhat());
//        System.out.println(dao.findById("HD241213005"));
//        List<HoaDon> list=dao.todayList("AD001","2024-12-13");
//                        if (list != null && !list.isEmpty()) {
//        list.forEach(System.out::println);
//        } else {
//            System.out.println("Không có hóa đơn nào.");
//        }
    }

}
