package service.impl;

import dao.GenericDao;
import dao.HoaDonDAO;
import model.HoaDon;
import service.HoaDonService;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonServiceImpl extends  GenericServiceImpl<HoaDon,String>  implements HoaDonService {
    private HoaDonDAO dao;
    public HoaDonServiceImpl(HoaDonDAO hoadonDAO) throws RemoteException {
        super(hoadonDAO);
        this.dao = hoadonDAO;

    }

    @Override
    public ArrayList<HoaDon> thongKeNam(String year, String loaiBan) {
        return dao.thongKeNam(year,loaiBan);
    }

    @Override
    public List<HoaDon> thongKeThang(String month, String year, String loaiBan) {
        return dao.thongKeThang(month,year,loaiBan);
    }

    @Override
    public List<HoaDon> thongKeNgay(String day, String loaiBan) {
        return dao.thongKeNgay(day,loaiBan);
    }

    @Override
    public List<Object[]> thongKeNamMon(String year, String maLoaiMon) {
        return dao.thongKeNamMon(year,maLoaiMon);
    }

    @Override
    public List<Object[]> thongKeQuyMon(String quarter, String year, String maLoaiMon) {
        return dao.thongKeQuyMon(quarter,year,maLoaiMon);
    }

    @Override
    public List<Object[]> thongKeThangMon(String month, String year, String maLoaiMon) {
        return dao.thongKeThangMon(month,year,maLoaiMon);
    }

    @Override
    public List<Object[]> thongKeNgayMon(String day, String maLoaiMon) {
        return dao.thongKeNgayMon(day,maLoaiMon);
    }

    @Override
    public List<Integer> loadNam() {
        return dao.loadNam();
    }

    @Override
    public String hoaDonMoiNhat() {
        return dao.hoaDonMoiNhat();
    }

    @Override
    public HoaDon getHD(String maHD) {
        return dao.getHD(maHD);
    }

    @Override
    public List<HoaDon> todayList(String maNhanVien, String today) {
        return dao.todayList(maNhanVien,today);
    }

    @Override
    public boolean createOrder(HoaDon hd) {
        return dao.createOrder(hd);
    }

    @Override
    public HoaDon getHoaDon(String soBan) {
        return dao.getHoaDon(soBan);
    }

    @Override
    public List<Object[]> getChiTietHoaDon(String soBan) {
        return dao.getChiTietHoaDon(soBan);
    }

    @Override
    public boolean taoHoaDon(HoaDon hoaDon) {
        return dao.taoHoaDon(hoaDon);
    }

    @Override
    public Object[] getThongTinHoaDon(String maHoaDon) {
        return dao.getThongTinHoaDon(maHoaDon);
    }

    @Override
    public List<Object[]> getChiTietHoaDon_1(String maHD) {
        return dao.getChiTietHoaDon_1(maHD);
    }

    @Override
    public double checkNS(String maHoaDon, String sdt) {
        return dao.checkNS(maHoaDon,sdt);
    }

    @Override
    public Object[] getThongTinKH(String maHD, String sdt) {
        return dao.getThongTinKH(maHD,sdt);
    }

    @Override
    public Object[] getKM(String maHD, String maKM) {
        return dao.getKM(maHD,maKM);
    }

    @Override
    public boolean capNhatHoaDon(String maHD, String maKM, String maKH, Double tienTT, Double giamGia) {
        return dao.capNhatHoaDon(maHD,maKM,maKH,tienTT,giamGia);
    }

    @Override
    public Object timKiemHD(String maHD) {
        return dao.timKiemHD(maHD);
    }

    @Override
    public boolean kiemTraGiamGiaSN(String maKH) {
        return dao.kiemTraGiamGiaSN(maKH);
    }

    @Override
    public List<Object[]> timKiemCTHD(String maHD) {
        return dao.timKiemCTHD(maHD);
    }

    @Override
    public List<Object[]> hoaDonTrongNgay() {
        return dao.hoaDonTrongNgay();
    }
}
