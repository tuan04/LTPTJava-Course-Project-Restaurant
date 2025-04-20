package service.impl;

import dao.ChiTietDatBanDAO;
import model.ChiTietDatBan;

import java.util.List;

public class ChiTietDatBanServiceImpl {
    private ChiTietDatBanDAO chiTietDatBanDAO;
    public ChiTietDatBanServiceImpl(ChiTietDatBanDAO chiTietDatBanDAO) {
        this.chiTietDatBanDAO = chiTietDatBanDAO;
    }
    public List<ChiTietDatBan> getList (String maDDB){
        return chiTietDatBanDAO.getList(maDDB);
    }
}
