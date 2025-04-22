package service;

import model.DonDatBan;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public interface DonDatBanService extends GenericService<DonDatBan, String>{
    public List<DonDatBan> timKiemCapNhat(String dateStr, String sdt) throws RemoteException;
    public LocalDateTime checkTimeBan(String maBan, LocalDateTime gioHen) throws RemoteException;
    public String donDatBanMoiNhat() throws RemoteException;
    public List<DonDatBan> todayList(String toDay, String maNV) throws RemoteException;
    public void capNhatBanSauGioKhachDen() throws RemoteException;
    public void capNhatBanTruocGioKhachDen() throws RemoteException;
    public List<DonDatBan> todayListHuy(String toDay, String maNV) throws RemoteException;
    public boolean capNhatTTDDB(String maDDB) ;
    public List<Object[]> getChiTietDonDatBan(String maDonDatBan) ;
    public Object[] getThongTinDonDatBan(String maDDB) ;
    public boolean huyDonDatBan(String maDDB, double hoanCoc, LocalDateTime gioHuy, String maNV) ;
    public Object[] timDDB(String ma) ;
    public List<Object[]> timChiTietDonDatBan(String maDonDatBan);
    }
