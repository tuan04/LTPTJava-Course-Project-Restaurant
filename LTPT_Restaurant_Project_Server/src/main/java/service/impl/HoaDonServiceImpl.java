package service.impl;

import dao.GenericDao;
import dao.HoaDonDAO;
import model.HoaDon;
import service.HoaDonService;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonServiceImpl extends GenericServiceImpl<HoaDon, String>  implements HoaDonService {

    private HoaDonDAO dao;
    public HoaDonServiceImpl(HoaDonDAO hoadonDAO) throws RemoteException {
        super(hoadonDAO);
        this.dao = hoadonDAO;
    }

    @Override
    public ArrayList<HoaDon> thongKeNam(String year, String loaiBan) throws RemoteException {
        return dao.thongKeNam(year,loaiBan);
    }

    @Override
    public List<HoaDon> thongKeThang(String month, String year, String loaiBan) throws RemoteException {
        return dao.thongKeThang(month,year,loaiBan);
    }

    @Override
    public List<HoaDon> thongKeNgay(String day, String loaiBan) throws RemoteException {
        return dao.thongKeNgay(day,loaiBan);
    }

    @Override
    public List<Object[]> thongKeNamMon(String year, String maLoaiMon) throws RemoteException {
        return dao.thongKeNamMon(year,maLoaiMon);
    }

    @Override
    public List<Object[]> thongKeQuyMon(String quarter, String year, String maLoaiMon) throws RemoteException {
        return dao.thongKeQuyMon(quarter,year,maLoaiMon);
    }

    @Override
    public List<Object[]> thongKeThangMon(String month, String year, String maLoaiMon)throws RemoteException {
        return dao.thongKeThangMon(month,year,maLoaiMon);
    }

    @Override
    public List<Object[]> thongKeNgayMon(String day, String maLoaiMon) throws RemoteException {
        return dao.thongKeNgayMon(day,maLoaiMon);
    }

    @Override
    public List<Integer> loadNam() throws RemoteException {
        return dao.loadNam();
    }

    @Override
    public String hoaDonMoiNhat() throws RemoteException {
        return dao.hoaDonMoiNhat();
    }

    @Override
    public HoaDon getHD(String maHD) throws RemoteException {
        return dao.getHD(maHD);
    }

    @Override
    public List<HoaDon> todayList(String maNhanVien, String today) throws RemoteException {
        return dao.todayList(maNhanVien,today);
    }

    @Override
    public boolean createOrder(HoaDon hd) throws RemoteException {
        return dao.createOrder(hd);
    }

    @Override
    public HoaDon getHoaDon(String soBan) throws RemoteException {
        return dao.getHoaDon(soBan);
    }

    @Override
    public List<Object[]> getChiTietHoaDon(String soBan) throws RemoteException {
        return dao.getChiTietHoaDon(soBan);
    }

    @Override
    public boolean taoHoaDon(HoaDon hoaDon) throws RemoteException {
        return dao.taoHoaDon(hoaDon);
    }

    @Override
    public Object[] getThongTinHoaDon(String maHoaDon) throws RemoteException {
        return dao.getThongTinHoaDon(maHoaDon);
    }

    @Override
    public List<Object[]> getChiTietHoaDon_1(String maHD) throws RemoteException {
        return dao.getChiTietHoaDon_1(maHD);
    }

    @Override
    public double checkNS(String maHoaDon, String sdt) throws RemoteException {
        return dao.checkNS(maHoaDon,sdt);
    }

    @Override
    public Object[] getThongTinKH(String maHD, String sdt) throws RemoteException {
        return dao.getThongTinKH(maHD,sdt);
    }

    @Override
    public Object[] getKM(String maHD, String maKM) throws RemoteException {
        return dao.getKM(maHD,maKM);
    }

    @Override
    public boolean capNhatHoaDon(String maHD, String maKM, String maKH, Double tienTT, Double giamGia) throws RemoteException {
        return dao.capNhatHoaDon(maHD,maKM,maKH,tienTT,giamGia);
    }

    @Override
    public Object timKiemHD(String maHD) throws RemoteException {
        return dao.timKiemHD(maHD);
    }

    @Override
    public boolean kiemTraGiamGiaSN(String maKH) throws RemoteException {
        return dao.kiemTraGiamGiaSN(maKH);
    }

    @Override
    public List<Object[]> timKiemCTHD(String maHD) throws RemoteException {
        return dao.timKiemCTHD(maHD);
    }

    @Override
    public List<Object[]> hoaDonTrongNgay() throws RemoteException {
        return dao.hoaDonTrongNgay();
    }
}
