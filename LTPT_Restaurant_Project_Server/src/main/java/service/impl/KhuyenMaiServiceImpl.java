package service.impl;

import dao.GenericDao;
import dao.KhuyenMaiDAO;
import model.KhuyenMai;
import service.KhuyenMaiService;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public class KhuyenMaiServiceImpl extends GenericServiceImpl<KhuyenMai, String> implements KhuyenMaiService {
    private KhuyenMaiDAO khuyenMaiDAO;

    public KhuyenMaiServiceImpl(KhuyenMaiDAO khuyenMaiDAO) throws RemoteException {
        super(khuyenMaiDAO);
        this.khuyenMaiDAO = khuyenMaiDAO;
    }

    @Override
    public List<KhuyenMai> layTatCaKhuyenMai() throws RemoteException {
        return khuyenMaiDAO.layTatCaKhuyenMai();
    }

    @Override
    public List<KhuyenMai> layKhuyenMaiDangApDung() throws RemoteException {
        return khuyenMaiDAO.layKhuyenMaiDangApDung();
    }

    @Override
    public List<KhuyenMai> timKhuyenMaiTheoTen(String ten) throws RemoteException {
        return khuyenMaiDAO.timKhuyenMaiTheoTen(ten);
    }

    @Override
    public KhuyenMai timKhuyenMaiTheoMa(String ma) throws RemoteException {
        return khuyenMaiDAO.timKhuyenMaiTheoMa(ma);
    }

    @Override
    public List<KhuyenMai> locKhuyenMaiTheoThoiGian(LocalDate tuNgay, LocalDate denNgay) throws RemoteException {
        return khuyenMaiDAO.locKhuyenMaiTheoThoiGian(tuNgay, denNgay);
    }
}
