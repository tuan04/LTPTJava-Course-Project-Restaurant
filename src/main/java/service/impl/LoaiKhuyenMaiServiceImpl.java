package service.impl;

import dao.GenericDao;
import dao.LoaiKhuyenMaiDAO;
import model.LoaiKhuyenMai;
import service.LoaiKhuyenMaiService;

import java.rmi.RemoteException;

public class LoaiKhuyenMaiServiceImpl extends GenericServiceImpl<LoaiKhuyenMai, String> implements LoaiKhuyenMaiService {
    private LoaiKhuyenMaiDAO loaiKhuyenMaiDAO;
    public LoaiKhuyenMaiServiceImpl(LoaiKhuyenMaiDAO loaiKhuyenMaiDAO) throws RemoteException {
        super(loaiKhuyenMaiDAO);
        this.loaiKhuyenMaiDAO = loaiKhuyenMaiDAO;
    }
}
