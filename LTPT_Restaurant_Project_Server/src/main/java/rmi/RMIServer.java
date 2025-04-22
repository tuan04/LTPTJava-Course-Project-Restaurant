package rmi;

import dao.*;
import model.*;
import service.*;
import service.impl.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws Exception{

        Context context = new InitialContext();
        LocateRegistry.createRegistry(9090);

        DonDatBanDAO ddb_dao = new DonDatBanDAO(DonDatBan.class); //Java Object
        ChiTietDatBanDAO ctdb_dao = new ChiTietDatBanDAO(ChiTietDatBan.class); //Java Object
        HoaDonDAO hoaDon_dao = new HoaDonDAO(HoaDon.class);
        ChiTietHoaDonDAO cthd_dao = new ChiTietHoaDonDAO(ChiTietHoaDon.class);
        BanDAO banDao = new BanDAO(Ban.class);
        LoaiBanDAO loaiBanDao = new LoaiBanDAO(LoaiBan.class);
        KhachHangDAO khachHangDao = new KhachHangDAO(KhachHang.class);
        LoaiKhachHangDAO loaiKhachHangDAO = new LoaiKhachHangDAO(LoaiKhachHang.class);
        KhuyenMaiDAO khuyenMaiDao = new KhuyenMaiDAO(KhuyenMai.class);
        LoaiKhuyenMaiDAO loaiKhuyenMaiDAO = new LoaiKhuyenMaiDAO(LoaiKhuyenMai.class);
        MonAnDAO monAnDao = new MonAnDAO(MonAn.class);
        LoaiMonAnDAO loaiMonAnDao = new LoaiMonAnDAO(LoaiMonAn.class);
        NhanVienDAO nhanVienDao = new NhanVienDAO(NhanVien.class);


        DonDatBanService ddbService = new DonDatBanServiceImpl(ddb_dao); //Java Remote Object
        ChiTietDatBanService ctdbService = new ChiTietDatBanServiceImpl(ctdb_dao); //Java Remote Object
        HoaDonService hoaDonService = new HoaDonServiceImpl(hoaDon_dao);
        ChiTietHoaDonService cthdService = new ChiTietHoaDonServiceImpl(cthd_dao);
        BanService banService = new BanServiceImpl(banDao);
        LoaiBanService loaiBanService = new LoaiBanServiceImpl(loaiBanDao);
        KhachHangService khachHangService = new KhachHangServiceImpl(khachHangDao);
        LoaiKhachHangService loaiKhachHangService = new LoaiKhachHangServiceImpl(loaiKhachHangDAO);
        KhuyenMaiService khuyenMaiService = new KhuyenMaiServiceImpl(khuyenMaiDao);
        LoaiKhuyenMaiService loaiKmService = new LoaiKhuyenMaiServiceImpl(loaiKhuyenMaiDAO);
        MonAnService monAnService = new MonAnServiceImpl(monAnDao);
        LoaiMonAnService loaiMonAnService = new LoaiMonAnServiceImpl(loaiMonAnDao);
        NhanVienService nhanVienService = new NhanVienServiceImpl(nhanVienDao  );

        context.bind("rmi://DESKTOP-0LHH81P:9090/donDatBanService", ddbService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/chiTietDatBanService", ctdbService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/hoaDonService", hoaDonService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/chiTietHoaDonService", cthdService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/banService", banService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/loaiBanService", loaiBanService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/khachHangService", khachHangService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/loaiKhachHangService", loaiKhachHangService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/khuyenMaiService", khuyenMaiService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/loaiKmService", loaiKmService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/monAnService", monAnService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/loaiMonAnService", loaiMonAnService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/nhanVienService", nhanVienService);

        System.out.println("RMI Server is running...");
    }
}
