package service;

import model.ChiTietHoaDon;

import java.rmi.RemoteException;
import java.util.List;

public interface ChiTietHoaDonService extends GenericService<ChiTietHoaDon, ChiTietHoaDon.ChiTietHoaDonId>{
    public List<ChiTietHoaDon> getListTheoHoaDon(String maHD) throws RemoteException;
}
