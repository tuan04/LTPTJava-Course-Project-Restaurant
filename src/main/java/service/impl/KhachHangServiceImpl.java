package service.impl;

import dao.GenericDao;
import dao.KhachHangDAO;
import model.KhachHang;
import service.KhachHangService;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public class KhachHangServiceImpl extends GenericServiceImpl<KhachHang, String> implements KhachHangService {
    private KhachHangDAO khachHangDAO;

    public KhachHangServiceImpl(KhachHangDAO khachHangDAO) throws RemoteException {
        super(khachHangDAO);
        this.khachHangDAO = khachHangDAO;
    }

    @Override
    public boolean updateDiemTL() throws RemoteException {
        return khachHangDAO.updateDiemTL();
    }

    @Override
    public KhachHang getKHSDT(String sdt) throws RemoteException {
        return khachHangDAO.getKHSDT(sdt);
    }

    @Override
    public List<KhachHang> getListKHTheoLoai(String maLoaiKH) throws RemoteException {
        return khachHangDAO.getListKHTheoLoai(maLoaiKH);
    }

    @Override
    public String maTuSinh() throws RemoteException {
        return khachHangDAO.maTuSinh();
    }

    @Override
    public List<KhachHang> timTheoSDT(String sdt) throws RemoteException {
        return khachHangDAO.timTheoSDT(sdt);
    }

    @Override
    public List<KhachHang> getListKHTheoTrangThai(boolean trangthai) throws RemoteException {
        return khachHangDAO.getListKHTheoTrangThai(trangthai);
    }

    @Override
    public KhachHang giamGiaNgaySinh(LocalDate ngayThang) throws RemoteException {
        return khachHangDAO.giamGiaNgaySinh(ngayThang);
    }
}
