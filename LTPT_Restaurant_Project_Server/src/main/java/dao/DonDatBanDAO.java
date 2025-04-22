package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import model.Ban;
import model.DonDatBan;
import model.KhachHang;
import model.NhanVien;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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





    public static void main(String[] args) {
        DonDatBanDAO ddb_dao = new DonDatBanDAO(DonDatBan.class);

        System.out.println(LocalDate.parse("19/04/2025", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        List<DonDatBan> listddb = ddb_dao.todayList("19/04/2025", "NVLT002");
        if (listddb.isEmpty()) {
            System.out.print("nooo");
        }
        for (DonDatBan db : listddb) {
            System.out.println(db);
        }
//        String ddb = ddb_dao.donDatBanMoiNhat();
//        System.out.println(ddb);

//        System.out.println(ddb_dao.findById("DB250419001"));
    }
        public boolean capNhatTTDDB(String maDDB) {
          String sql= "UPDATE DonDatBan c Set c.trangThai = 2 WHERE c.maDDB =: maDDB";

           return  em.createQuery(sql).setParameter("maDDB", maDDB).executeUpdate() > 0;
        }




}
