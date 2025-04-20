package service;

import model.NhanVien;

import java.rmi.RemoteException;
import java.util.List;

public interface NhanVienService extends GenericService<NhanVien, String>{
    public NhanVien dangNhap(String maNV, String matKhau) throws RemoteException;
    public List<NhanVien> TimTheoSDT(String sdt) throws RemoteException;
    public List<NhanVien> getListNVTheoLoai(String maLoaiNV) throws RemoteException;
    public String maTuSinh() throws RemoteException;
    public List<NhanVien> TimTheoTenNV(String ten) throws RemoteException;
    public List<NhanVien> getListNVTheoTrangThai(boolean trangthai) throws RemoteException;
    public boolean checkOTP(String maNV, String email, String otp) throws RemoteException;
    public String getOldPass(String maNV) throws RemoteException;
    public boolean checkEmail(String maNV, String email) throws RemoteException;
    public boolean checkMaNV(String maNV) throws RemoteException;
    public boolean updateOTP(String maNV, String otp) throws RemoteException;
    public boolean deleteOTP(String maNV) throws RemoteException;
    public String generateOTP(int length) throws RemoteException;
}
