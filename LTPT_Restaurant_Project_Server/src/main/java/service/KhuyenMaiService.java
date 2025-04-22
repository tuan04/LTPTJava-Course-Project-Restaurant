package service;

import model.KhuyenMai;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface KhuyenMaiService extends GenericService<KhuyenMai, String>{
    public List<KhuyenMai> layTatCaKhuyenMai() throws RemoteException;
    public List<KhuyenMai> layKhuyenMaiDangApDung() throws RemoteException;
    public List<KhuyenMai> timKhuyenMaiTheoTen(String ten) throws RemoteException;
    public KhuyenMai timKhuyenMaiTheoMa(String ma) throws RemoteException;
    public List<KhuyenMai> locKhuyenMaiTheoThoiGian(LocalDate tuNgay, LocalDate denNgay) throws RemoteException;
}
