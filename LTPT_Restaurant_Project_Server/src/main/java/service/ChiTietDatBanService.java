package service;

import model.ChiTietDatBan;

import java.rmi.RemoteException;
import java.util.List;

public interface ChiTietDatBanService extends GenericService<ChiTietDatBan, ChiTietDatBan.ChiTietDatBanId> {
    public List<ChiTietDatBan> getList (String maDDB) throws RemoteException;
    public boolean deleteChiTietDatBan (String maDDB);

}
