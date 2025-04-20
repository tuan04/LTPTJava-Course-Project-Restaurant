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
    @Transactional
    public void capNhatBanSauGioKhachDen() {
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now.minusMinutes(60);
        LocalDateTime to = now.minusMinutes(30);

        // Lấy các đơn phù hợp
        String jpql = "SELECT d FROM DonDatBan d " +
                "WHERE d.trangThai = 1 " +
                "AND d.ngayTao = :today " +
                "AND d.gioDat BETWEEN :from AND :to " +
                "AND d.ban.trangThai = 2";

        List<DonDatBan> dsDon = em.createQuery(jpql, DonDatBan.class)
                .setParameter("today", today)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();

        for (DonDatBan ddb : dsDon) {
            ddb.getBan().setTrangThai(0);        // Trả lại bàn
            ddb.setTrangThai(3);                 // Hủy đơn
            ddb.setTienHoanCoc(0);               // Không hoàn cọc
            ddb.setGioHuy(LocalDateTime.now());  // Giờ hủy
        }
    }

    @Transactional
    public void capNhatBanTruocGioKhachDen() {
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime from = now;
        LocalDateTime to = now.plusMinutes(30);

        String jpql = "SELECT DISTINCT d.ban FROM DonDatBan d " +
                "WHERE d.trangThai = 1 " +
                "AND d.gioDat BETWEEN :from AND :to " +
                "AND d.ngayTao = :today";

        List<Ban> dsBan = em.createQuery(jpql, Ban.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .setParameter("today", today)
                .getResultList();

        for (Ban ban : dsBan) {
            ban.setTrangThai(2); // Đặt tình trạng là ĐANG CHUẨN BỊ
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
        if(listddb.isEmpty()){
            System.out.print("nooo");
        }
        for(DonDatBan db : listddb){
            System.out.println(db);
        }
//        String ddb = ddb_dao.donDatBanMoiNhat();
//        System.out.println(ddb);

//        System.out.println(ddb_dao.findById("DB250419001"));



    }

}
