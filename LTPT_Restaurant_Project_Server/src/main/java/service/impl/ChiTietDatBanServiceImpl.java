package service.impl;

import dao.ChiTietDatBanDAO;
import dao.GenericDao;
import model.ChiTietDatBan;
import service.ChiTietDatBanService;

import java.rmi.RemoteException;
import java.util.List;

public class ChiTietDatBanServiceImpl extends GenericServiceImpl<ChiTietDatBan, ChiTietDatBan.ChiTietDatBanId> implements ChiTietDatBanService {
    private ChiTietDatBanDAO chiTietDatBanDAO;

    public ChiTietDatBanServiceImpl(ChiTietDatBanDAO chiTietDatBanDAO) throws RemoteException {
        super(chiTietDatBanDAO);
        this.chiTietDatBanDAO = chiTietDatBanDAO;
    }
    public List<ChiTietDatBan> getList (String maDDB) throws RemoteException{
        return chiTietDatBanDAO.getList(maDDB);
    }
}
