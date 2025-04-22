package service.impl;

import dao.DonDatBanDAO;
import model.DonDatBan;
import service.DonDatBanService;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

public class DonDatBanServiceImpl extends GenericServiceImpl<DonDatBan, String> implements DonDatBanService {
    private DonDatBanDAO donDatBanDAO;

    public DonDatBanServiceImpl(DonDatBanDAO donDatBanDAO) throws RemoteException {
        super(donDatBanDAO);
        this.donDatBanDAO = donDatBanDAO;
    }

    public List<DonDatBan> timKiemCapNhat(String dateStr, String sdt) throws RemoteException{
        return donDatBanDAO.timKiemCapNhat(dateStr, sdt);
    };
    public LocalDateTime checkTimeBan(String maBan, LocalDateTime gioHen) throws RemoteException{
        return donDatBanDAO.checkTimeBan(maBan, gioHen);
    };
    public String donDatBanMoiNhat() throws RemoteException{
        return donDatBanDAO.donDatBanMoiNhat();
    };
    public List<DonDatBan> todayList(String toDay, String maNV) throws RemoteException{
        return donDatBanDAO.todayList(toDay, maNV);
    };
    public void capNhatBanSauGioKhachDen() throws RemoteException{
        donDatBanDAO.capNhatBanSauGioKhachDen();
    };
    public void capNhatBanTruocGioKhachDen() throws RemoteException{
        donDatBanDAO.capNhatBanTruocGioKhachDen();
    };
    public List<DonDatBan> todayListHuy(String toDay, String maNV) throws RemoteException{
        return donDatBanDAO.todayListHuy(toDay, maNV);
    };
    public boolean capNhatTTDDB(String maDDB) {
        return donDatBanDAO.capNhatTTDDB(maDDB);
    }
    }
