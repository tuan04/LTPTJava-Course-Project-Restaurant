package dao;

import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.*;
import model.HoaDon;


import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import model.*;
import util.JPAUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class HoaDonDAO extends GenericDao<HoaDon, String> {
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


    public boolean updateNhanVienHoaDon(String maNV, String maHD) {
        EntityTransaction transaction = em.getTransaction();
        int n = 0;
        try {
            transaction.begin();
            String sql = "update HoaDon h set h.nhanVien.maNV =:maNhanVien where h.maHD =:maHD";
            int updatedCount = em.createQuery(sql)
                    .setParameter("maNhanVien", maNV)
                    .setParameter("maHD", maHD).executeUpdate();
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

            String sql = ("select h from HoaDon h where h.trangThai = false and h.ban.maBan =: maBan");
            return em.createQuery(sql, HoaDon.class).setParameter("maBan", maBan).getSingleResult();

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
                        loaiBan.equals("Tầng 2") ? "T2%" : "T3%";
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
        return findById(maHD);
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

    public boolean createOrder(HoaDon hd) {
        return save(hd);
    }

    public HoaDon getHoaDon(String soBan) {
        try {
            String jpql = "SELECT hd FROM HoaDon hd " +
                    "JOIN Ban  b on hd.ban.maBan =  b.maBan " +
                    "WHERE hd.trangThai = false AND b.maBan = :soBan ";
            TypedQuery<HoaDon> query = em.createQuery(jpql, HoaDon.class);
            query.setParameter("soBan", soBan);
            List<HoaDon> results = query.getResultList();

            return results.isEmpty() ? null : results.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Object[]> getChiTietHoaDon(String soBan) {
        List<Object[]> result = new ArrayList<>();
        try {
            String jpql = "SELECT ma.tenMonAn, ct.soLuong, ma.gia, ct.thanhTien " +
                    "FROM ChiTietHoaDon ct " +
                    "JOIN HoaDon hd on hd.maHD=ct.hoaDon.maHD " +
                    "JOIN Ban b on hd.ban.maBan=b.maBan " +
                    "JOIN MonAn ma on ct.monAn.maMonAn=ma.maMonAn " +
                    "WHERE hd.trangThai = false AND b.maBan = :soBan";
//            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
//            query.setParameter("soBan", soBan);
//            result = query.getResultList();
            return em.createQuery(jpql, Object[].class).setParameter("soBan", soBan).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public boolean taoHoaDon(HoaDon hoaDon)  {

        return save(hoaDon);
    }

    public Object[] getThongTinHoaDon(String maHoaDon) {
        try {
            String jpql = "SELECT " +
                    "b.loaiBan.maLoaiBan, nv.tenNV, b.maBan, hd.tongTien, " +
                    "COALESCE(ddb.tienCoc, 0), " +
                    "hd.gioVao " +
                    "FROM HoaDon hd " +
                    "JOIN NhanVien nv on hd.nhanVien.maNV=nv.maNV " +
                    "LEFT JOIN DonDatBan ddb on hd.donDatBan.maDDB=ddb.maDDB " +
                    "JOIN Ban b on hd.ban.maBan=b.maBan " +
                    "WHERE hd.maHD = :maHoaDon";

            Object[] result = em.createQuery(jpql, Object[].class)
                    .setParameter("maHoaDon", maHoaDon)
                    .getSingleResult();

            // Xử lý chuyển mã loại bàn và định dạng thời gian
            Object[] finalResult = new Object[6];

            // Xử lý tên loại bàn
            String maLoaiBan = (String) result[0];
            switch (maLoaiBan) {
                case "LB001" -> finalResult[0] = "Thường";
                case "LB002" -> finalResult[0] = "VIP";

                default -> finalResult[0] = "Không rõ";
            }

            finalResult[1] = result[1]; // Họ tên NV
            finalResult[2] = result[2]; // Số bàn
            finalResult[3] = result[3]; // Tổng tiền
            finalResult[4] = result[4]; // Tiền cọc


            // Format giờ vào
            LocalDateTime gioVao = (LocalDateTime) result[5];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            finalResult[5] = gioVao.format(formatter);

            return finalResult;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Object[]> getChiTietHoaDon_1(String maHD) {
        try {
            String jpql = """
                        SELECT 
                            ma.tenMonAn, 
                            cthd.soLuong, 
                            ma.gia, 
                            cthd.thanhTien, 
                            cthd.donGia
                        FROM 
                            ChiTietHoaDon cthd
                        JOIN 
                            HoaDon hd  on hd.maHD=cthd.hoaDon.maHD
                        JOIN 
                            MonAn ma on cthd.monAn.maMonAn =ma.maMonAn
                        WHERE 
                            hd.maHD = :maHD
                    """;

            List<Object[]> results = em.createQuery(jpql, Object[].class)
                    .setParameter("maHD", maHD)
                    .getResultList();

            // Thêm chỉ số thứ tự i vào đầu mỗi mảng
            List<Object[]> finalList = new ArrayList<>();
            int i = 1;
            for (Object[] row : results) {
                Object[] withIndex = new Object[6];
                withIndex[0] = i++;                  // STT
                withIndex[1] = row[0];               // tên món ăn
                withIndex[2] = row[1];               // số lượng
                withIndex[3] = row[2];               // giá
                withIndex[4] = row[3];               // giá sau giảm
                withIndex[5] = row[4];               // thành tiền
                finalList.add(withIndex);
            }

            return finalList;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public double checkNS(String maHoaDon, String sdt) {
        try {
            String jpql = """
                        SELECT hd.tongTien * lkh.giamGiaSN / 100.0
                        FROM HoaDon hd
                        JOIN KhachHang kh on hd.khachHang.maKH=kh.maKH
                        JOIN LoaiKhachHang  lkh on lkh.maLoaiKH=kh.loaiKH.maLoaiKH
                        WHERE hd.maHD = :maHD
                          AND kh.sdt = :sdt
                          AND FUNCTION('DAY', kh.ngaySinh) = FUNCTION('DAY', CURRENT_DATE)
                          AND FUNCTION('MONTH', kh.ngaySinh) = FUNCTION('MONTH', CURRENT_DATE)
                    """;


            Double result = em.createQuery(jpql, Double.class)
                    .setParameter("maHD", maHoaDon)
                    .setParameter("sdt", sdt)
                    .getSingleResult();

            return result != null ? result : 0;

        } catch (NoResultException e) {
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Object[] getThongTinKH(String maHD, String sdt) {
        try {
            String jpql = """
                        SELECT kh.tenKH, kh.loaiKH.maLoaiKH, hd.tongTien * lkh.giamGiaTV / 100.0
                        FROM HoaDon hd
                        JOIN KhachHang kh on hd.khachHang.maKH=kh.maKH
                        JOIN LoaiKhachHang lkh on lkh.maLoaiKH=kh.loaiKH.maLoaiKH
                        WHERE hd.maHD = :maHD AND kh.sdt = :sdt
                    """;

            Object[] result = em.createQuery(jpql, Object[].class)
                    .setParameter("maHD", maHD)
                    .setParameter("sdt", sdt)
                    .getSingleResult();

            // Xử lý đổi tên loại khách hàng
            if (result[1] != null) {
                String maLoai = result[1].toString();
                switch (maLoai) {
                    case "LKH1" -> result[1] = "Thành Viên";
                    case "LKH2" -> result[1] = "Vàng";
                    case "LKH3" -> result[1] = "Kim Cương";
                }
            }

            return result;

        } catch (NoResultException e) {
            return new Object[]{};
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[]{};
        }
    }

    public Object[] getKM(String maHD, String maKM) {
        try {
            String jpql = """
                        SELECT km.chietKhau, hd.tongTien * km.chietKhau / 100.0
                        FROM HoaDon hd 
                        join KhuyenMai km on hd.khuyenMai.maKM=km.maKM 
                        join LoaiKhuyenMai lkm on lkm.maLoaiKM=km.loaiKM.maLoaiKM
                        WHERE hd.maHD = :maHD
                          AND km.maKM = :maKM
                          AND lkm.maLoaiKM ='LKM01'
                          AND km.soLuong >= 1
                          AND km.ngayKT >= CURRENT_DATE
                    """;

            Object[] result = em.createQuery(jpql, Object[].class)
                    .setParameter("maHD", maHD)
                    .setParameter("maKM", maKM)
                    .getSingleResult();

            Object[] obj = new Object[2];
            obj[0] = "Giảm giá " + result[0] + "%";
            obj[1] = result[1];

            return obj;

        } catch (NoResultException e) {
            return new Object[]{};
        } catch (Exception e) {
            e.printStackTrace();
            return new Object[]{};
        }
    }

    public boolean capNhatHoaDon(String maHD, String maKM, String maKH, Double tienTT, Double giamGia) {
        try {
            HoaDon hoaDon = findById(maHD);
            if (hoaDon == null) {
                return false;
            }

            // Thiết lập mã khuyến mãi nếu có
            if (maKM != null) {
                KhuyenMai km = em.find(KhuyenMai.class, maKM);
                hoaDon.setKhuyenMai(km);
            } else {
                hoaDon.setKhuyenMai(null);
            }

            if (maKH != null) {
                KhachHang kh = em.find(KhachHang.class, maKH);
                hoaDon.setKhachHang(kh);
            } else {
                hoaDon.setKhachHang(null);
            }

            hoaDon.setTongTienGiamGia(giamGia);
            hoaDon.setTongTienThanhToan(tienTT);
            hoaDon.setTrangThai(true);
            hoaDon.setGioRa(LocalDateTime.now());

            em.getTransaction().begin();
            em.merge(hoaDon);
            em.getTransaction().commit();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public Object timKiemHD(String maHD) {
        Object ob = new Object[]{};

        try {
            String jpql = "SELECT b.maBan, hd.ngayTao, nv.tenNV, kh.tenKH, km.maKM, hd.trangThai, hd.tongTien, "
                    + "hd.tongTien * (km.chietKhau / 100.0), "
                    + "hd.tongTienGiamGia, ddb.tienCoc, b.loaiBan.maLoaiBan, hd.tongTienThanhToan, "
                    + "FUNCTION('FORMAT', hd.gioVao, 'dd/MM/yyyy HH:mm'), "
                    + "COALESCE(FUNCTION('FORMAT', hd.gioRa, 'dd/MM/yyyy HH:mm'), ' ') "
                    + "FROM HoaDon hd "
                    + "LEFT JOIN NhanVien nv on hd.nhanVien.maNV =nv.maNV "
                    + "LEFT JOIN Ban b on hd.ban.maBan=b.maBan "
                    + "LEFT JOIN KhachHang kh on hd.khachHang.maKH=kh.maKH "
                    + "LEFT JOIN KhuyenMai km on hd.khuyenMai.maKM=km.maKM "
                    + "LEFT JOIN DonDatBan ddb on hd.donDatBan.maDDB=ddb.maDDB "
                    + "WHERE hd.maHD = :maHD";

            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setParameter("maHD", maHD);

            List<Object[]> result = query.getResultList();
            if (!result.isEmpty()) {
                ob = result.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        return ob;
    }
    public boolean kiemTraGiamGiaSN(String maKH) {
        try {
            String jpql = "SELECT COUNT(hd) FROM HoaDon hd WHERE hd.khachHang.maKH = :maKH AND FUNCTION('FORMAT', hd.ngayTao, 'yyyy-MM-dd') = FUNCTION('FORMAT', CURRENT_DATE, 'yyyy-MM-dd')";
            Long count = em.createQuery(jpql, Long.class)
                    .setParameter("maKH", maKH)
                    .getSingleResult();
            return count == 0;
        } finally {
            em.close();
        }
    }
    public List<Object[]> timKiemCTHD(String maHD) {
        List<Object[]> result = new ArrayList<>();
        try {
            String jpql = "SELECT ma.tenMonAn, ma.gia, cthd.thanhTien, cthd.soLuong, cthd.donGia "
                    + "FROM ChiTietHoaDon cthd JOIN MonAn ma on cthd.monAn.maMonAn=ma.maMonAn "
                    + "WHERE cthd.hoaDon.maHD = :maHD";

            List<Object[]> rawList = em.createQuery(jpql, Object[].class)
                    .setParameter("maHD", maHD)
                    .getResultList();

            int i = 1;
            for (Object[] row : rawList) {
                Object[] ob = new Object[]{i++, row[0], row[1], row[2], row[3], row[4]};
                result.add(ob);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return result;
    }
    public List<Object[]> hoaDonTrongNgay() {
        List<Object[]> resultList = new ArrayList<>();
        try {
            String jpql = "SELECT hd.maHD, hd.ngayTao, kh.tenKH, kh.sdt, hd.tongTien, hd.tongTienThanhToan, b.maBan, hd.trangThai " +
                    "FROM HoaDon hd LEFT JOIN KhachHang kh on hd.khachHang.maKH=kh.maKH " +
                    "LEFT JOIN Ban b on hd.ban.maBan=b.maBan " +
                    "WHERE hd.ngayTao = CURRENT_DATE";

            List<Object[]> rawList = em.createQuery(jpql, Object[].class).getResultList();

            for (Object[] row : rawList) {
                String trangThai = row[7] != null && row[7].equals(true) ? "Đã thanh toán" : "Chưa thanh toán";
                Object[] formattedRow = new Object[]{
                        row[0],
                        row[1],
                        row[2],
                        row[3],
                        row[4],
                        row[5],
                        row[6],
                        trangThai
                };
                resultList.add(formattedRow);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
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
//        List<Object[]> list=dao.thongKeNamMon("2024","LM001");
//        List<Object[]> list=dao.thongKeQuyMon("4","2024","LM001");
//        List<Object[]> list=dao.thongKeThangMon("12","2024","LM001");
//        List<Object[]> list=dao.thongKeNgayMon("2024-12-13","LM001");
//
//        if (list != null && !list.isEmpty()) {
//            for (Object[] row : list) {
//                for (Object obj : row) {
//                    System.out.print(obj + " ");  // In từng phần tử của mảng
//                }
//                System.out.println();  // In xuống dòng sau mỗi dòng kết quả
//            }
//        } else {
//            System.out.println("Không có hóa đơn nào.");
//        }
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

//        System.out.println(dao.getHoaDon("T1001"));
//        List<Object[]> list=dao.getChiTietHoaDon("T1009");
//        if (list != null && !list.isEmpty()) {
//            for (Object[] row : list) {
//                for (Object obj : row) {
//                    System.out.print(obj + " ");  // In từng phần tử của mảng
//                }
//                System.out.println();  // In xuống dòng sau mỗi dòng kết quả
//            }
//        } else {
//            System.out.println("Không có hóa đơn nào.");
//        }
//        System.out.println(Arrays.toString(dao.getThongTinHoaDon("HD241212001")));
//        List<Object[]> list=dao.getChiTietHoaDon_1("HD241212001");
//        if (list != null && !list.isEmpty()) {
//            for (Object[] row : list) {
//                for (Object obj : row) {
//                    System.out.print(obj + " ");  // In từng phần tử của mảng
//                }
//                System.out.println();  // In xuống dòng sau mỗi dòng kết quả
//            }
//        } else {
//            System.out.println("Không có hóa đơn nào.");
//        }
//        System.out.println(dao.checkNS("HD241212001","0912345678"));
//        System.out.println(Arrays.toString(dao.getThongTinKH("HD241212001","0912345678")));
//        System.out.println(Arrays.toString(dao.getKM("HD241213003","KM008")));
//        System.out.println(dao.capNhatHoaDon("HD241212001", null, "KH000001", 474501.0, 100.0));
//        System.out.println(Arrays.toString((Object[]) dao.timKiemHD("HD241212001")));
//        System.out.println(dao.kiemTraGiamGiaSN("KH000001"));
//        List<Object[]> list = dao.timKiemCTHD("HD241212002");
        List<Object[]> list=dao.hoaDonTrongNgay();
        for (Object[] row : list) {
            System.out.println(Arrays.toString(row));
        }

    }

}

