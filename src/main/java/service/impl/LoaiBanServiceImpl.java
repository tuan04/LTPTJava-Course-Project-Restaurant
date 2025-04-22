package service.impl;

import dao.GenericDao;
import dao.LoaiBanDAO;
import model.LoaiBan;
import service.LoaiBanService;

import java.rmi.RemoteException;
import java.util.List;

public class LoaiBanServiceImpl extends GenericServiceImpl<LoaiBan, String> implements LoaiBanService {
    private LoaiBanDAO loaiBanDAO;
    public LoaiBanServiceImpl(LoaiBanDAO loaiBanDAO) throws RemoteException {
        super(loaiBanDAO);
        this.loaiBanDAO = loaiBanDAO;
    }

    @Override
    public List<LoaiBan> timTheoTenGanDung(String tenLoai) throws RemoteException {
        return loaiBanDAO.timTheoTenGanDung(tenLoai);
    }
}
