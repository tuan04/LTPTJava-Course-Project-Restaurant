package service.impl;

import dao.GenericDao;
import dao.NhanVienDAO;
import model.NhanVien;
import service.GenericService;
import service.NhanVienService;

import java.rmi.RemoteException;
import java.util.List;

public class NhanVienServiceImpl extends GenericServiceImpl<NhanVien, String> implements NhanVienService {
    private NhanVienDAO nhanVienDAO;

    public NhanVienServiceImpl(NhanVienDAO nvDao) throws RemoteException {
        super(nvDao);
        this.nhanVienDAO = nvDao;
    }

    @Override
    public NhanVien dangNhap(String maNV, String matKhau) throws RemoteException {
        return nhanVienDAO.dangNhap(maNV, matKhau);
    }

    @Override
    public List<NhanVien> TimTheoSDT(String sdt) throws RemoteException {
        return nhanVienDAO.TimTheoSDT(sdt);
    }

    @Override
    public List<NhanVien> getListNVTheoLoai(String maLoaiNV) throws RemoteException {
        return nhanVienDAO.getListNVTheoLoai(maLoaiNV);
    }

    @Override
    public String maTuSinh() throws RemoteException {
        return nhanVienDAO.maTuSinh();
    }

    @Override
    public List<NhanVien> TimTheoTenNV(String ten) throws RemoteException {
        return nhanVienDAO.TimTheoTenNV(ten);
    }

    @Override
    public List<NhanVien> getListNVTheoTrangThai(boolean trangthai) throws RemoteException {
        return nhanVienDAO.getListNVTheoTrangThai(trangthai);
    }

    @Override
    public boolean checkOTP(String maNV, String email, String otp) throws RemoteException {
        return nhanVienDAO.checkOTP(maNV, email, otp);
    }

    @Override
    public String getOldPass(String maNV) throws RemoteException {
        return nhanVienDAO.getOldPass(maNV);
    }

    @Override
    public boolean checkEmail(String maNV, String email) throws RemoteException {
        return nhanVienDAO.checkEmail(maNV, email);
    }

    @Override
    public boolean checkMaNV(String maNV) throws RemoteException {
        return nhanVienDAO.checkMaNV(maNV);
    }

    @Override
    public boolean updateOTP(String maNV, String otp) throws RemoteException {
        return nhanVienDAO.updateOTP(maNV, otp);
    }

    @Override
    public boolean deleteOTP(String maNV) throws RemoteException {
        return nhanVienDAO.deleteOTP(maNV);
    }

    @Override
    public String generateOTP(int length) throws RemoteException {
        return nhanVienDAO.generateOTP(length);
    }
}
