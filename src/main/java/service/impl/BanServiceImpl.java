package service.impl;

import dao.BanDAO;
import dao.GenericDao;
import model.Ban;
import service.BanService;

import java.rmi.RemoteException;
import java.util.List;

public class BanServiceImpl extends GenericServiceImpl<Ban, String> implements BanService {
    private BanDAO banDAO;

    public BanServiceImpl(BanDAO banDAO) throws RemoteException {
        super(banDAO);
        this.banDAO = banDAO;
    }

    @Override
    public List<Ban> getListBanTheoLoai(String maLoai) throws RemoteException{
        return banDAO.getListBanTheoLoai(maLoai);
    }

    @Override
    public long getSoLuongBanTheoLBvTrangThai(String maLoaiBan, String trangThai) throws RemoteException{
        return banDAO.getSoLuongBanTheoLBvTrangThai(maLoaiBan, trangThai);
    }

    @Override
    public List<Object[]> getBan(String maLoaiBan, String trangThai) throws RemoteException{
        return banDAO.getBan(maLoaiBan, trangThai);
    }
}
