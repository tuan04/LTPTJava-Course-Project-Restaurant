package service;

import model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public interface HoaDonService extends GenericService<HoaDon, String> {
    public ArrayList<HoaDon> thongKeNam(String year, String loaiBan);

    public List<HoaDon> thongKeThang(String month, String year, String loaiBan);

    public List<HoaDon> thongKeNgay(String day, String loaiBan);

    public List<Object[]> thongKeNamMon(String year, String maLoaiMon);

    public List<Object[]> thongKeQuyMon(String quarter, String year, String maLoaiMon);

    public List<Object[]> thongKeThangMon(String month, String year, String maLoaiMon);

    public List<Object[]> thongKeNgayMon(String day, String maLoaiMon);

    public List<Integer> loadNam();

    public String hoaDonMoiNhat();

    public HoaDon getHD(String maHD);

    public List<HoaDon> todayList(String maNhanVien, String today);

    public boolean createOrder(HoaDon hd);

    public HoaDon getHoaDon(String soBan);

    public List<Object[]> getChiTietHoaDon(String soBan);

    public boolean taoHoaDon(HoaDon hoaDon);


    public Object[] getThongTinHoaDon(String maHoaDon);

    public List<Object[]> getChiTietHoaDon_1(String maHD);

    public double checkNS(String maHoaDon, String sdt);

    public Object[] getThongTinKH(String maHD, String sdt);

    public Object[] getKM(String maHD, String maKM);

    public boolean capNhatHoaDon(String maHD, String maKM, String maKH, Double tienTT, Double giamGia);

    public Object timKiemHD(String maHD);

    public boolean kiemTraGiamGiaSN(String maKH);

    public List<Object[]> timKiemCTHD(String maHD);

    public List<Object[]> hoaDonTrongNgay();

}
