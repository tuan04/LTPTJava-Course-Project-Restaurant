package data;

import jakarta.persistence.*;
import model.*;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    private Faker faker = new Faker();
    private final Random rd = new Random();
    private static EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
            .createEntityManager();

    private static EntityTransaction tr = em.getTransaction();

    //Tạo ngày tạo ngẫu nhiên từ 1-1000 ngày trước
    public LocalDate generateNgayTao(){
        LocalDate today = LocalDate.now();
        int randomDays = rd.nextInt(1000) + 1;
        LocalDate randomDate = today.minusDays(randomDays);
        return randomDate;
    }

    //Tạo số điện thoại ngẫu nhiên theo định dạng Việt Nam
    public String generateSDT(){
        String[] prefixes = {"090", "091", "092", "093", "094", "095", "096", "097", "098", "099", "03", "07", "08"};
        String prefix = prefixes[faker.random().nextInt(prefixes.length)];
        String phoneNumber = prefix + faker.number().digits(7);

        return phoneNumber;
    }

    //Tạo ngày sinh ngẫu nhiên từ 18-100 năm trước
    public LocalDate generateNgaySinh(){
        LocalDate today = LocalDate.now();
        LocalDate minDate = today.minusYears(100); // 100 năm trước
        LocalDate maxDate = today.minusYears(18);  // 18 năm trước
        long daysBetween = ChronoUnit.DAYS.between(minDate, maxDate);
        long randomDays = rd.nextLong(daysBetween + 1); // +1 để bao gồm ngày cuối cùng
        LocalDate randomBirthDate = minDate.plusDays(randomDays);
        return randomBirthDate;
    }

    //Đoạn code phát sinh khách hàng
    //Tạo 3 loại khách hàng
    public void generateLoaiKhachHang() {
        String[] maLoaiKH = {"LKH1", "LKH2", "LKH3"};
        String[] tenLoaiKH = {"Thành viên", "Vàng", "Kim cương"};
        double[] giamGiaTV = {0, 0.10, 0.15};
        double[] giamGiaSN = {0.05, 0.10, 0.15};
        for (int i = 0; i < 3; i++) {
            LoaiKhachHang loaiKhachHang = new LoaiKhachHang();
            tr.begin();
            loaiKhachHang.setMaLoaiKH(maLoaiKH[i]);
            loaiKhachHang.setTenLoaiKH(tenLoaiKH[i]);
            loaiKhachHang.setGiamGiaTV(giamGiaTV[i]);
            loaiKhachHang.setGiamGiaSN(giamGiaSN[i]);
            em.persist(loaiKhachHang);
            tr.commit();
        }

    }

    public KhachHang generateKhachHang(){
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH("KH" + faker.number().digits(6));
        khachHang.setNgaySinh(generateNgaySinh());
        khachHang.setNgayTao(generateNgayTao());
        khachHang.setTrangThai(faker.bool().bool());
        khachHang.setTenKH(faker.name().fullName());
        khachHang.setEmail(khachHang.getTenKH().toLowerCase().replace(" ", "") + "@gmail.com");
        khachHang.setSdt(generateSDT());
        khachHang.setDiemTL(faker.number().numberBetween(0, 700));
        String maLoaiKH = "";
        if(khachHang.getDiemTL() > 199 && khachHang.getDiemTL() < 500) {
            maLoaiKH = "LKH2";
        }else if(khachHang.getDiemTL() >= 500) {
            maLoaiKH = "LKH3";
        }  else{maLoaiKH = "LKH1";}
        khachHang.setLoaiKH(em.find(LoaiKhachHang.class, maLoaiKH));
        return khachHang;
    }
    //Ket thuc doan code phat sinh khach hang

    //Doan code phat sinh ban
    public void generateLoaiBan() {
        String[] maLoaiBan = {"LB1", "LB2"};
        String[] tenLoaiBan = {"Thường", "VIP"};
        for(int i = 0; i < 2; i++) {
            LoaiBan ban = new LoaiBan();
            tr.begin();
            ban.setMaLoaiBan(maLoaiBan[i]);
            ban.setTenLoaiBan(tenLoaiBan[i]);
            em.persist(ban);
            tr.commit();
        }
    }

    public Ban generateBan(){
        Ban ban = new Ban();
        String loaiBan = faker.bool().bool() ? "LB1" : "LB2";
        ban.setMaBan("B" + em.find(LoaiBan.class, loaiBan).getTenLoaiBan().substring(0,0) + faker.number().digits(3));
        ban.setTenBan("Bàn" + ban.getMaBan().substring(1,4));
        ban.setTrangThai("Trống");
        ban.setViTri(faker.bool().bool()? "Tầng 1" : "Tầng 2");
        ban.setLoaiBan(em.find(LoaiBan.class, loaiBan));
        return ban;
    }
    //Ket thuc doan code phat sinh ban
    //Doan code phat sinh nhan vien
    public NhanVien generateNhanVien(){
        String loaiNV = faker.bool().bool() ? "QL" : "NV";
        NhanVien nhanVien = new NhanVien();

        nhanVien.setMaNV(loaiNV + faker.number().digits(6));
        nhanVien.setTenNV(faker.name().fullName());
        nhanVien.setNgaySinh(generateNgaySinh());
        nhanVien.setSdt(generateSDT());
        nhanVien.setEmail(nhanVien.getTenNV().toLowerCase().replace(" ", ""));
        nhanVien.setTrangThai(faker.bool().bool());
        nhanVien.setLoaiNV(loaiNV.equals("QL") ? "Quản lý" : "Nhân viên");
        nhanVien.setGioiTinh(faker.bool().bool());
        nhanVien.setMatKhau(faker.internet().password());

        return nhanVien;
    }
    //Ket thuc doan code phat sinh nhan vien
    //Doan code phat sinh mon an
    public void generateLoaiMonAn(){
        String[] tenLoai = {"Khai vị", "Tráng miệng", "Thịt", "Hải sản", "Mì ý", "Súp"};
        for (int i = 0; i < 6; i++){
            LoaiMonAn loaiMonAn = new LoaiMonAn();
            loaiMonAn.setMaLoaiMon("LM" + faker.number().digits(3));
            loaiMonAn.setTenLoaiMon(tenLoai[i]);
            tr.begin();
            em.persist(loaiMonAn);
            tr.commit();
        }
    }

    public MonAn generateMonAn(List<LoaiMonAn> listlm){
        MonAn monAn = new MonAn();
        LoaiMonAn lm = listlm.get(rd.nextInt(listlm.size()));
        monAn.setMaMonAn("MA" + faker.number().digits(3));
        monAn.setTenMonAn(faker.food().dish());
        monAn.setLoaiMonAn(lm);
        monAn.setGia(150 + (800 - 150) * rd.nextDouble());
        monAn.setTrangThai(faker.bool().bool());
        return monAn;
    }
    //Doan code phat sinh khuyen mai
    public void generateLoaiKHuyenMai(){
        String[] loaiKM = {"Món ăn", "Hóa đơn"};
        for(int i = 0; i < 2; i ++){
            LoaiKhuyenMai loaiKhuyenMai = new LoaiKhuyenMai();
            loaiKhuyenMai.setMaLoaiKM("LKM" + (i+1));
            loaiKhuyenMai.setTenLoaiKM(loaiKM[i]);
            tr.begin();
            em.persist(loaiKhuyenMai);
            tr.commit();
        }
    }
    public KhuyenMai generateKhuyenMai(List<LoaiKhuyenMai> listlkm){
        LoaiKhuyenMai loaiKhuyenMai = listlkm.get(rd.nextInt(listlkm.size()));
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setMaKM("KM" + faker.number().digits(3));
        khuyenMai.setLoaiKM(loaiKhuyenMai);
        khuyenMai.setTenKM("Khuyến mãi" + loaiKhuyenMai.getTenLoaiKM().toLowerCase() + faker.number().digits(5));
        int ptKM = (faker.number().numberBetween(5, 20));
        khuyenMai.setChietKhau((double)ptKM);
        khuyenMai.setNgayBD(LocalDateTime.now());
        khuyenMai.setNgayKT(LocalDateTime.now().plusDays(faker.number().numberBetween(5, 10)));
        return khuyenMai;
    }
    //Ket thuc doan code phat sinh khuyen mai
    public static void main(String[] args) {
        DataGenerator generator = new DataGenerator();

        generator.generateLoaiKhachHang();
        for (int i = 0; i < 20; i++) {
            KhachHang khachHang = generator.generateKhachHang();
            tr.begin();
            em.persist(khachHang);
            tr.commit();
        }

        generator.generateLoaiBan();
        for (int i = 0; i < 20; i++) {
            Ban ban = generator.generateBan();
            tr.begin();
            em.persist(ban);
            tr.commit();
        }

        for (int i = 0; i < 20; i++) {
            NhanVien nhanVien = generator.generateNhanVien();
            tr.begin();
            em.persist(nhanVien);
            tr.commit();
        }

        generator.generateLoaiMonAn();
        Query qr = em.createQuery("FROM LoaiMonAn ");
        List<LoaiMonAn> listlm = qr.getResultList();
        for(int i = 0; i < 30; i++){
            MonAn monAn = generator.generateMonAn(listlm);
            tr.begin();
            em.persist(monAn);
            tr.commit();
        }

        generator.generateLoaiKHuyenMai();
        Query qr2 = em.createQuery("FROM LoaiKhuyenMai ");
        List<LoaiKhuyenMai> listkm = qr2.getResultList();
        for(int i = 0; i < 10; i++){
            KhuyenMai km = generator.generateKhuyenMai(listkm);
            tr.begin();
            em.persist(km);
            tr.commit();
        }

    }
}