package dao;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import model.Ban;
import model.DonDatBan;
import model.KhachHang;
import model.NhanVien;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DonDatBanDAO extends GenericDao<DonDatBan, String>{
    public DonDatBanDAO(Class<DonDatBan> clazz) {
        super(clazz);
    }

    public List<DonDatBan> timKiemCapNhat(String dateStr, String sdt){
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        String jpql = "SELECT d FROM DonDatBan d WHERE d.trangThai = 1 AND d.gioDat BETWEEN :start AND :end";

        if (sdt != null && !sdt.isEmpty()) {
            jpql += " AND d.khachHang.sdt = :sdt";
        }

        return em.createQuery(jpql, DonDatBan.class).setParameter("start", startOfDay)
        .setParameter("end", endOfDay).setParameter("sdt", sdt).getResultList();

    }


    public LocalDateTime checkTimeBan(String maBan, LocalDateTime gioHen) {
        LocalDateTime gioTru3 = gioHen.minusHours(3);
        LocalDateTime gioCong3 = gioHen.plusHours(3);

        String jpql = "SELECT d FROM DonDatBan d " +
                "WHERE d.trangThai = 1 " +
                "AND d.ban.maBan = :maBan " +
                "AND d.gioDat BETWEEN :start AND :end";

        TypedQuery<DonDatBan> query = em.createQuery(jpql, DonDatBan.class);
        query.setParameter("maBan", maBan);
        query.setParameter("start", gioTru3);
        query.setParameter("end", gioCong3);

        return query.getResultStream()
                .findFirst()
                .map(DonDatBan::getGioDat)
                .orElse(null);
    }

    //
    public String donDatBanMoiNhat(){
        String jpql = "SELECT d.maDDB FROM DonDatBan d ORDER BY d.maDDB DESC";
        List<String> result = em.createQuery(jpql, String.class)
                .setMaxResults(1)
                .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    //Lấy danh sách các đơn đặt bàn được tạo trong ngày chỉ định, của nhaan viên
    public List<DonDatBan> todayList(String toDay, String maNV){
        LocalDate ngay = LocalDate.parse(toDay, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String jpql = "SELECT d FROM DonDatBan d WHERE d.nhanVien.maNV = :maNV "
                + "AND d.ngayTao = :ngayTao "
                + "AND d.trangThai IN (1, 2)";
        return em.createQuery(jpql, DonDatBan.class)
                .setParameter("maNV", maNV)
                .setParameter("ngayTao", ngay)
                .getResultList();
    }

    public boolean capNhatBanTruocGioKhachDen() {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();

            LocalDateTime now = LocalDateTime.now();
            LocalDate today = now.toLocalDate();
            LocalDateTime fromTime = now;
            LocalDateTime toTime = now.plusMinutes(30);

            String jpql = """
            SELECT ddb FROM DonDatBan ddb
            WHERE ddb.trangThai = 1
              AND FUNCTION('DATE', ddb.gioDat) = :today
              AND ddb.gioDat BETWEEN :fromTime AND :toTime
        """;

            List<DonDatBan> list = em.createQuery(jpql, DonDatBan.class)
                    .setParameter("today", today)
                    .setParameter("fromTime", fromTime)
                    .setParameter("toTime", toTime)
                    .getResultList();

            for (DonDatBan ddb : list) {
                Ban ban = ddb.getBan();
                if (ban != null) {
                    ban.setTrangThai(2); // Cập nhật tình trạng
                    em.merge(ban);
                }
            }

            tr.commit();
            return true;
        } catch (Exception ex) {
            if (tr.isActive()) {
                tr.rollback();
            }
            throw new RuntimeException("Lỗi cập nhật bàn trước giờ khách đến", ex);
        }
    }


    public boolean capNhatBanSauGioKhachDen() {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime fromTime = now.minusMinutes(60);
            LocalDateTime toTime = now.minusMinutes(30);

            LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
            LocalDateTime endOfDay = LocalDate.now().atTime(23, 59, 59);

            String jpql = """
            SELECT ddb FROM DonDatBan ddb
            WHERE ddb.trangThai = 1
              AND ddb.gioDat BETWEEN :fromTime AND :toTime
              AND ddb.gioDat BETWEEN :startOfDay AND :endOfDay
        """;

            List<DonDatBan> danhSach = em.createQuery(jpql, DonDatBan.class)
                    .setParameter("fromTime", fromTime)
                    .setParameter("toTime", toTime)
                    .setParameter("startOfDay", startOfDay)
                    .setParameter("endOfDay", endOfDay)
                    .getResultList();

            for (DonDatBan ddb : danhSach) {
                Ban ban = ddb.getBan();
                if (ban != null && ban.getTrangThai() == 2) {
                    ban.setTrangThai(0);
                    em.merge(ban);
                }

                ddb.setTrangThai(3);
                ddb.setTienHoanCoc(0);
                ddb.setGioHuy(LocalDateTime.now());
                em.merge(ddb);
            }

            tr.commit();
            return true;
        } catch (Exception ex) {
            if (tr.isActive()) tr.rollback();
            throw new RuntimeException("Lỗi cập nhật bàn sau giờ khách đến", ex);
        }
    }





    //Lay danh sách cac đơn đặt bàn hủy trong ngày chỉ dinh
    public List<DonDatBan> todayListHuy(String toDay, String maNV){
        LocalDate ngay = LocalDate.parse(toDay, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String jpql = "SELECT d FROM DonDatBan d WHERE d.nhanVien.maNV = :maNV "
                + "AND d.ngayTao = :ngayTao "
                + "AND d.trangThai = 3";
        return em.createQuery(jpql, DonDatBan.class)
                .setParameter("maNV", maNV)
                .setParameter("ngayTao", ngay)
                .getResultList();
    }

    public List<Object[]> getChiTietDonDatBan(String maDonDatBan) {
        String jpql = "SELECT ma.tenMonAn, ma.gia, ctdb.donGia, ctdb.soLuong, ctdb.thanhTien " +
                "FROM ChiTietDatBan ctdb " +
                "JOIN ctdb.donDatBan ddb " +
                "JOIN ctdb.monAn ma " +
                "LEFT JOIN ma.khuyenMai km " +
                "WHERE ddb.maDDB = :maDDB";

        return em.createQuery(jpql, Object[].class)
                .setParameter("maDDB", maDonDatBan)
                .getResultList();
    }



    public Object[] getThongTinDonDatBan(String maDDB) {
        String sql = "SELECT MA.tenMonAn,CTDB.monAn.gia ,CTDB.donGia,CTDB.soLuong,CTDB.thanhTien " +
                "FROM DonDatBan DDB JOIN ChiTietDatBan CTDB ON DDB.maDDB = CTDB.donDatBan.maDDB " +
                " JOIN MonAn MA ON CTDB.monAn.maMonAn = MA.maMonAn " +
                " WHERE DDB.maDDB = :maDDB";

        List<Object[]> result = em.createQuery(sql, Object[].class)
                .setParameter("maDDB", maDDB)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }
    public boolean huyDonDatBan(String maDDB, double hoanCoc, LocalDateTime gioHuy, String maNV) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            String sql = "UPDATE DonDatBan d "
                    + "SET d.trangThai = 3, d.tienHoanCoc = :hoanCoc, d.gioHuy = :gioHuy, d.nhanVien.maNV = :maNV "
                    + "WHERE d.maDDB = :maDDB";

            int updated = em.createQuery(sql)
                    .setParameter("maDDB", maDDB)
                    .setParameter("hoanCoc", hoanCoc)
                    .setParameter("gioHuy", gioHuy)
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

    public Object[] timDDB(String ma) {
        try {
            String jpql = "SELECT d.khachHang.tenKH, " +
                    "       FUNCTION('DATE_FORMAT', d.ngayTao, '%d/%m/%Y'), " +
                    "       FUNCTION('DATE_FORMAT', d.gioDat, '%d/%m/%Y %H:%i'), " +
                    "       d.soLuongKH, d.khachHang.sdt, b.maBan, d.tienCoc, d.tienHoanCoc, " +
                    "       FUNCTION('DATE_FORMAT', d.gioHuy, '%d/%m/%Y %H:%i'), d.trangThai " +
                    "FROM DonDatBan d JOIN d.ban b WHERE d.maDDB = :ma";

            Object[] result = (Object[]) em.createQuery(jpql)
                    .setParameter("ma", ma)
                    .getSingleResult();

            String TT;
            int trangThai = (int) result[9];
            if (trangThai == 1) {
                TT = "Đang xử lí";
            } else if (trangThai == 2) {
                TT = "Đã nhận bàn";
            } else {
                TT = "Đã hủy";
            }

            return new Object[]{
                    result[0], // hoTenKH
                    result[1], // ngayTao
                    result[2], // gioHen
                    result[3], // soLuongKH
                    result[4], // soDienThoai
                    result[5], // soBan
                    result[6], // tienCoc
                    result[7], // hoanCoc
                    result[8], // gioHuy
                    TT         // trạng thái chuyển thành chuỗi
            };

        } catch (NoResultException e) {
            return null; // hoặc return new Object[] {};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Object[]> timChiTietDonDatBan(String maDonDatBan) {
        List<Object[]> list = new ArrayList<>();

        String jpql = "SELECT ma.tenMonAn, ma.gia, ct.donGia, ct.soLuong, ct.thanhTien " +
                "FROM ChiTietDatBan ct JOIN ct.monAn ma " +
                "WHERE ct.donDatBan.maDDB = :maDDB";

        try {
            List<Object[]> results = em.createQuery(jpql, Object[].class)
                    .setParameter("maDDB", maDonDatBan)
                    .getResultList();
            int i = 1;
            for (Object[] row : results) {
                Object[] ob = new Object[]{
                        i++,           // STT
                        row[0],        // tenMA
                        row[1],        // gia
                        row[2],        // giaSauGiam
                        row[3],        // soLuong
                        row[4]         // thanhTien
                };
                list.add(ob);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }



    public static void main(String[] args) {
        DonDatBanDAO ddb_dao = new DonDatBanDAO(DonDatBan.class);
        List<Object[]> list=ddb_dao.timChiTietDonDatBan("DB241213002");
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
//        Object[] list=ddb_dao.getThongTinDonDatBan("DB241213002");
//        System.out.println(Arrays.toString((Object[]) ddb_dao.timDDB("DB241213001")));

//        System.out.println(ddb_dao.huyDonDatBan("DB241213001",100000,LocalDateTime.now(),"AD001"));

//        System.out.println(LocalDate.parse("19/04/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//
//        List<DonDatBan> listddb = ddb_dao.todayList("19/04/2025", "NVLT002");
//        if (listddb.isEmpty()) {
//            System.out.print("nooo");
//        }
//        for (DonDatBan db : listddb) {
//            System.out.println(db);
//        }
//        String ddb = ddb_dao.donDatBanMoiNhat();
//        System.out.println(ddb);

//        System.out.println(ddb_dao.findById("DB250419001"));
    }
        public boolean capNhatTTDDB(String maDDB) {
          String sql= "UPDATE DonDatBan c Set c.trangThai = 2 WHERE c.maDDB =: maDDB";

           return  em.createQuery(sql).setParameter("maDDB", maDDB).executeUpdate() > 0;
        }





}
