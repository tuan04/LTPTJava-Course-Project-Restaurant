package service.impl;

import dao.GenericDao;
import dao.LoaiKhachHangDAO;
import model.LoaiKhachHang;
import service.LoaiKhachHangService;

import java.rmi.RemoteException;

public class LoaiKhachHangServiceImpl extends GenericServiceImpl<LoaiKhachHang, String> implements LoaiKhachHangService {
    private LoaiKhachHangDAO loaiKhachHangDAO;
    public LoaiKhachHangServiceImpl(LoaiKhachHangDAO loaiKhachHangDAO) throws RemoteException {
        super(loaiKhachHangDAO);
        this.loaiKhachHangDAO = loaiKhachHangDAO;
    }
}
