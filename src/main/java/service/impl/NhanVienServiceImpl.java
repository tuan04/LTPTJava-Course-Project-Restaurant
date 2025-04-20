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
        return null;
    }

    @Override
    public List<NhanVien> TimTheoSDT(String sdt) throws RemoteException {
        return List.of();
    }

    @Override
    public List<NhanVien> getListNVTheoLoai(String maLoaiNV) throws RemoteException {
        return List.of();
    }

    @Override
    public String maTuSinh() throws RemoteException {
        return "";
    }

    @Override
    public List<NhanVien> TimTheoTenNV(String ten) throws RemoteException {
        return List.of();
    }

    @Override
    public List<NhanVien> getListNVTheoTrangThai(boolean trangthai) throws RemoteException {
        return List.of();
    }

    @Override
    public boolean checkOTP(String maNV, String email, String otp) throws RemoteException {
        return false;
    }

    @Override
    public String getOldPass(String maNV) throws RemoteException {
        return "";
    }

    @Override
    public boolean checkEmail(String maNV, String email) throws RemoteException {
        return false;
    }

    @Override
    public boolean checkMaNV(String maNV) throws RemoteException {
        return false;
    }

    @Override
    public boolean updateOTP(String maNV, String otp) throws RemoteException {
        return false;
    }

    @Override
    public boolean deleteOTP(String maNV) throws RemoteException {
        return false;
    }

    @Override
    public String generateOTP(int length) throws RemoteException {
        return "";
    }
}
