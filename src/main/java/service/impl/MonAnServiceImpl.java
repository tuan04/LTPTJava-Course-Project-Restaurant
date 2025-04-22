package service.impl;

import dao.MonAnDAO;
import model.HoaDon;
import model.MonAn;
import service.HoaDonService;
import service.MonAnService;

import java.rmi.RemoteException;
import java.util.List;

public class MonAnServiceImpl extends GenericServiceImpl<MonAn, String> implements MonAnService {
    private MonAnDAO monAnDAO;

    public MonAnServiceImpl(MonAnDAO monAnDAO) throws RemoteException {
        super(monAnDAO);
        this.monAnDAO = monAnDAO;
    }

    @Override
    public MonAn getMonAn(String maMA) throws RemoteException {
        return monAnDAO.getMonAn(maMA);
    }

    @Override
    public List<MonAn> getMonTheoLoai(String maLoai) throws RemoteException {
        return monAnDAO.getMonTheoLoai(maLoai);
    }

    @Override
    public List<MonAn> getList() throws RemoteException {
        return monAnDAO.getList();
    }

    @Override
    public List<MonAn> timTheoTen(String ten) throws RemoteException {
        return monAnDAO.timTheoTen(ten);
    }

    @Override
    public List<MonAn> getListMonTheoTrangThai(boolean trangThai) throws RemoteException {
        return monAnDAO.getListMonTheoTrangThai(trangThai);
    }

    @Override
    public List<Object[]> getDSMonAn(String tenLoaiMon) throws RemoteException {
        return monAnDAO.getDSMonAn(tenLoaiMon);
    }

    @Override
    public boolean updateMonAnKhuyenMai(String maMA, String maKM) throws RemoteException {
        return monAnDAO.updateMonAnKhuyenMai(maMA, maKM);
    }

    @Override
    public boolean themMonAn(MonAn monAn) throws RemoteException {
        return monAnDAO.themMonAn(monAn);
    }

    @Override
    public boolean capNhatMonAn(MonAn monAn) throws RemoteException {
        return monAnDAO.capNhatMonAn(monAn);
    }

    @Override
    public boolean xoaMonAn(String maMA) throws RemoteException {
        return monAnDAO.xoaMonAn(maMA);
    }

    @Override
    public Object[] timMonAnTheoMa(String maMA, String loaiMon, String trangThaiMon) throws RemoteException {
        return monAnDAO.timMonAnTheoMa(maMA, loaiMon, trangThaiMon);
    }

    @Override
    public List<Object[]> timKiemMonTheoTen(String tenMon, String loaiMon, String trangThaiMon, String sortKey, String sortValue) throws RemoteException {
        return monAnDAO.timKiemMonTheoTen(tenMon, loaiMon, trangThaiMon, sortKey, sortValue);
    }

    @Override
    public List<String> getLoaiMon() throws RemoteException {
        return monAnDAO.getLoaiMon();
    }
}
