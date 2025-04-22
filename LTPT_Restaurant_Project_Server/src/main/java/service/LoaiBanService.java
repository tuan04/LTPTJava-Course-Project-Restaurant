package service;

import model.Ban;
import model.LoaiBan;

import java.rmi.RemoteException;
import java.util.List;

public interface LoaiBanService extends GenericService<LoaiBan, String>{
    public List<LoaiBan> timTheoTenGanDung(String tenLoai) throws RemoteException;
}
