package service;

import model.KhachHang;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface KhachHangService extends GenericService<KhachHang, String> {
    public boolean updateDiemTL() throws RemoteException;
    public KhachHang getKHSDT(String sdt) throws RemoteException;
    public List<KhachHang> getListKHTheoLoai(String maLoaiKH) throws RemoteException;
    public String maTuSinh() throws RemoteException;
    public List<KhachHang> timTheoSDT(String sdt) throws RemoteException;
    public List<KhachHang> getListKHTheoTrangThai(boolean trangthai) throws RemoteException;
    public KhachHang giamGiaNgaySinh(LocalDate ngayThang) throws RemoteException;

}
