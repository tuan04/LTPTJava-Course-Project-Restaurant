package service.impl;

import dao.GenericDao;
import dao.LoaiMonAnDAO;
import model.LoaiMonAn;
import service.LoaiMonAnService;

import java.rmi.RemoteException;
import java.util.List;

public class LoaiMonAnServiceImpl extends GenericServiceImpl<LoaiMonAn, String> implements LoaiMonAnService {
    private LoaiMonAnDAO loaiMonAnDAO;

    public LoaiMonAnServiceImpl(LoaiMonAnDAO loaiMonAnDAO) throws RemoteException {
        super(loaiMonAnDAO);
        this.loaiMonAnDAO = loaiMonAnDAO;
    }

    @Override
    public List<LoaiMonAn> getListLoaiMonAn() throws RemoteException  {
        return loaiMonAnDAO.getListLoaiMonAn();
    }

    @Override
    public LoaiMonAn timLoaiMonTheoMa(String ma) throws RemoteException {
        return loaiMonAnDAO.timLoaiMonTheoMa(ma);
    }

    @Override
    public LoaiMonAn timLoaiMonTheoTen(String tenLoaiMon) throws RemoteException {
        return loaiMonAnDAO.timLoaiMonTheoTen(tenLoaiMon);
    }
}
