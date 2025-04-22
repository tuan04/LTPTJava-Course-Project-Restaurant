package service;

import model.Ban;

import java.rmi.RemoteException;
import java.util.List;

public interface BanService extends GenericService<Ban, String>{
    public List<Ban> getListBanTheoLoai(String maLoai) throws RemoteException;
    public long getSoLuongBanTheoLBvTrangThai(String maLoaiBan, String trangThai) throws RemoteException;
    public List<Object[]> getBan(String maLoaiBan, String trangThai) throws RemoteException;

}
