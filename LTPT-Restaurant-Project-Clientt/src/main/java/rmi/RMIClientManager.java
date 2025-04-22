package rmi;

import service.DonDatBanService;
import service.ChiTietDatBanService;
import service.HoaDonService;
import service.ChiTietHoaDonService;
import service.BanService;
import service.LoaiBanService;
import service.KhachHangService;
import service.LoaiKhachHangService;
import service.KhuyenMaiService;
import service.LoaiKhuyenMaiService;
import service.MonAnService;
import service.LoaiMonAnService;
import service.NhanVienService;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.naming.Context;
import javax.naming.InitialContext;
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
        Registry registry = LocateRegistry.getRegistry("DESKTOP-0LHH81P", 9090);

        donDatBanService = (DonDatBanService) registry.lookup("donDatBanService");
        chiTietDatBanService = (ChiTietDatBanService) registry.lookup("chiTietDatBanService");
        hoaDonService = (HoaDonService) registry.lookup("hoaDonService");
        chiTietHoaDonService = (ChiTietHoaDonService) registry.lookup("chiTietHoaDonService");
        banService = (BanService) registry.lookup("banService");
        loaiBanService = (LoaiBanService) registry.lookup("loaiBanService");
        khachHangService = (KhachHangService) registry.lookup("khachHangService");
        loaiKhachHangService = (LoaiKhachHangService) registry.lookup("loaiKhachHangService");
        khuyenMaiService = (KhuyenMaiService) registry.lookup("khuyenMaiService");
        loaiKhuyenMaiService = (LoaiKhuyenMaiService) registry.lookup("loaiKmService");
        monAnService = (MonAnService) registry.lookup("monAnService");
        loaiMonAnService = (LoaiMonAnService) registry.lookup("loaiMonAnService");
        nhanVienService = (NhanVienService) registry.lookup("nhanVienService");
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
}
