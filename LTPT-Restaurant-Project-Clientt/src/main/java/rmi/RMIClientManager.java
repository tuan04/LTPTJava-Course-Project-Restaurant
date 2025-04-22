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
        String host = "rmi://DESKTOP-0LHH81P:9090/";
        Context context = new InitialContext();

        donDatBanService = (DonDatBanService) context.lookup(host + "donDatBanService");
        chiTietDatBanService = (ChiTietDatBanService) context.lookup(host + "chiTietDatBanService");
        hoaDonService = (HoaDonService) context.lookup(host + "hoaDonService");
        chiTietHoaDonService = (ChiTietHoaDonService) context.lookup(host + "chiTietHoaDonService");
        banService = (BanService) context.lookup(host + "banService");
        loaiBanService = (LoaiBanService) context.lookup(host + "loaiBanService");
        khachHangService = (KhachHangService) context.lookup(host + "khachHangService");
        loaiKhachHangService = (LoaiKhachHangService) context.lookup(host + "loaiKhachHangService");
        khuyenMaiService = (KhuyenMaiService) context.lookup(host + "khuyenMaiService");
        loaiKhuyenMaiService = (LoaiKhuyenMaiService) context.lookup(host + "loaiKmService");
        monAnService = (MonAnService) context.lookup(host + "monAnService");
        loaiMonAnService = (LoaiMonAnService) context.lookup(host + "loaiMonAnService");
        nhanVienService = (NhanVienService) context.lookup(host + "nhanVienService");
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
