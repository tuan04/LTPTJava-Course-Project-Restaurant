package rmi;

import service.*;
import java.rmi.Naming;
import model.NhanVien;

public class RMIClientManager {
    private static RMIClientManager instance;

    private DonDatBanService donDatBanService;
    private ChiTietDatBanService chiTietDatBanService;
    private HoaDonService hoaDonService;
    private ChiTietHoaDonService chiTietHoaDonService;
    private BanService banService;
    private LoaiBanService loaiBanService;
    private KhachHangService khachHangService;
    private LoaiKhachHangService loaiKhachHangService;
    private KhuyenMaiService khuyenMaiService;
    private LoaiKhuyenMaiService loaiKhuyenMaiService;
    private MonAnService monAnService;
    private LoaiMonAnService loaiMonAnService;
    private NhanVienService nhanVienService;

    private RMIClientManager() throws Exception {
        String host = "rmi://DESKTOP-03JUV5H:9090/";

        donDatBanService = (DonDatBanService) Naming.lookup(host + "donDatBanService");
        chiTietDatBanService = (ChiTietDatBanService) Naming.lookup(host + "chiTietDatBanService");
        hoaDonService = (HoaDonService) Naming.lookup(host + "hoaDonService");
        chiTietHoaDonService = (ChiTietHoaDonService) Naming.lookup(host + "chiTietHoaDonService");
        banService = (BanService) Naming.lookup(host + "banService");
        loaiBanService = (LoaiBanService) Naming.lookup(host + "loaiBanService");
        khachHangService = (KhachHangService) Naming.lookup(host + "khachHangService");
        loaiKhachHangService = (LoaiKhachHangService) Naming.lookup(host + "loaiKhachHangService");
        khuyenMaiService = (KhuyenMaiService) Naming.lookup(host + "khuyenMaiService");
        loaiKhuyenMaiService = (LoaiKhuyenMaiService) Naming.lookup(host + "loaiKmService");
        monAnService = (MonAnService) Naming.lookup(host + "monAnService");
        loaiMonAnService = (LoaiMonAnService) Naming.lookup(host + "loaiMonAnService");
        nhanVienService = (NhanVienService) Naming.lookup(host + "nhanVienService");
    }

    public static RMIClientManager getInstance() throws Exception {
        if (instance == null) {
            instance = new RMIClientManager();
        }
        return instance;
    }

    public DonDatBanService getDonDatBanService() {
        return donDatBanService;
    }

    public ChiTietDatBanService getChiTietDatBanService() {
        return chiTietDatBanService;
    }

    public HoaDonService getHoaDonService() {
        return hoaDonService;
    }

    public ChiTietHoaDonService getChiTietHoaDonService() {
        return chiTietHoaDonService;
    }

    public BanService getBanService() {
        return banService;
    }

    public LoaiBanService getLoaiBanService() {
        return loaiBanService;
    }

    public KhachHangService getKhachHangService() {
        return khachHangService;
    }

    public LoaiKhachHangService getLoaiKhachHangService() {
        return loaiKhachHangService;
    }

    public KhuyenMaiService getKhuyenMaiService() {
        return khuyenMaiService;
    }

    public LoaiKhuyenMaiService getLoaiKhuyenMaiService() {
        return loaiKhuyenMaiService;
    }

    public MonAnService getMonAnService() {
        return monAnService;
    }

    public LoaiMonAnService getLoaiMonAnService() {
        return loaiMonAnService;
    }

    public NhanVienService getNhanVienService() {
        return nhanVienService;
    }
    public static void main(String[] args) {
        try {
        RMIClientManager manager = RMIClientManager.getInstance();
        NhanVien nv = manager.getNhanVienService().findById("AD001");
        System.out.println(nv);
    }
          catch (Exception e) {
        e.printStackTrace();
    }}
}
