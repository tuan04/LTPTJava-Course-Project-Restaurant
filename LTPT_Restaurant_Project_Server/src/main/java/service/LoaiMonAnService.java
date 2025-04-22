package service;

import model.LoaiMonAn;

import java.rmi.RemoteException;
import java.util.List;

public interface LoaiMonAnService extends GenericService<LoaiMonAn, String>{
    public List<LoaiMonAn> getListLoaiMonAn() throws RemoteException;
    public LoaiMonAn timLoaiMonTheoMa(String ma) throws RemoteException;
    public LoaiMonAn timLoaiMonTheoTen(String tenLoaiMon) throws RemoteException;

}
