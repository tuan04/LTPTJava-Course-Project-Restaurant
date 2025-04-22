package service;

import model.HoaDon;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface HoaDonService extends GenericService<HoaDon, String> {
    public ArrayList<HoaDon> thongKeNam(String year, String loaiBan) throws RemoteException;

    public List<HoaDon> thongKeThang(String month, String year, String loaiBan) throws RemoteException;

    public List<HoaDon> thongKeNgay(String day, String loaiBan) throws RemoteException;

    public List<Object[]> thongKeNamMon(String year, String maLoaiMon) throws RemoteException;

    public List<Object[]> thongKeQuyMon(String quarter, String year, String maLoaiMon) throws RemoteException;

    public List<Object[]> thongKeThangMon(String month, String year, String maLoaiMon) throws RemoteException;

    public List<Object[]> thongKeNgayMon(String day, String maLoaiMon) throws RemoteException;

    public List<Integer> loadNam() throws RemoteException;

    public String hoaDonMoiNhat() throws RemoteException;

    public HoaDon getHD(String maHD) throws RemoteException;

    public List<HoaDon> todayList(String maNhanVien, String today) throws RemoteException;

    public boolean createOrder(HoaDon hd) throws RemoteException;

    public HoaDon getHoaDon(String soBan) throws RemoteException;

    public List<Object[]> getChiTietHoaDon(String soBan) throws RemoteException;

    public boolean taoHoaDon(HoaDon hoaDon) throws RemoteException;

    public Object[] getThongTinHoaDon(String maHoaDon) throws RemoteException;

    public List<Object[]> getChiTietHoaDon_1(String maHD) throws RemoteException;

    public double checkNS(String maHoaDon, String sdt) throws RemoteException;

    public Object[] getThongTinKH(String maHD, String sdt) throws RemoteException;

    public Object[] getKM(String maHD, String maKM) throws RemoteException;

    public boolean capNhatHoaDon(String maHD, String maKM, String maKH, Double tienTT, Double giamGia) throws RemoteException;

    public Object timKiemHD(String maHD) throws RemoteException;

    public boolean kiemTraGiamGiaSN(String maKH) throws RemoteException;

    public List<Object[]> timKiemCTHD(String maHD) throws RemoteException;

    public List<Object[]> hoaDonTrongNgay() throws RemoteException;

}
