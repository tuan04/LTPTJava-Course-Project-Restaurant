package service.impl;

import dao.ChiTietHoaDonDAO;
import model.ChiTietHoaDon;
import service.ChiTietHoaDonService;

import java.rmi.RemoteException;
import java.util.List;

public class ChiTietHoaDonServiceImpl extends GenericServiceImpl<ChiTietHoaDon, ChiTietHoaDon.ChiTietHoaDonId> implements ChiTietHoaDonService {
    private ChiTietHoaDonDAO chiTietHoaDonDAO;
    public ChiTietHoaDonServiceImpl(ChiTietHoaDonDAO chiTietHoaDonDAO) throws RemoteException {
        super(chiTietHoaDonDAO);
        this.chiTietHoaDonDAO = chiTietHoaDonDAO;
    }

    @Override
    public List<ChiTietHoaDon> getListTheoHoaDon(String maHD) throws RemoteException{
        return chiTietHoaDonDAO.getListTheoHoaDon(maHD);
    }
}
