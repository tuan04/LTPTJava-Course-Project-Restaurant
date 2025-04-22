package service;

import model.HoaDon;
import model.MonAn;

import java.rmi.RemoteException;
import java.util.List;

public interface MonAnService extends GenericService<MonAn, String>{
    public MonAn getMonAn(String maMA) throws RemoteException;
    public List<MonAn> getMonTheoLoai(String maLoai) throws RemoteException;
    public List<MonAn> getList() throws RemoteException;
    public List<MonAn> timTheoTen(String ten) throws RemoteException;
    public List<MonAn> getListMonTheoTrangThai(boolean trangThai) throws RemoteException;
    public List<Object[]> getDSMonAn(String tenLoaiMon) throws RemoteException;
    public boolean updateMonAnKhuyenMai(String maMA, String maKM) throws RemoteException;
    public boolean themMonAn(MonAn monAn) throws RemoteException;
    public boolean capNhatMonAn(MonAn monAn) throws RemoteException;
    public boolean xoaMonAn(String maMA) throws RemoteException;
    public Object[] timMonAnTheoMa(String maMA, String loaiMon, String trangThaiMon) throws RemoteException;
    public List<Object[]> timKiemMonTheoTen(String tenMon, String loaiMon, String trangThaiMon, String sortKey, String sortValue) throws RemoteException;
    public List<String> getLoaiMon() throws RemoteException;
}
