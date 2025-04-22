package dao;

import model.ChiTietDatBan;
import model.DonDatBan;
import model.HoaDon;
import model.MonAn;

import java.util.List;

public class ChiTietDatBanDAO extends GenericDao<ChiTietDatBan, ChiTietDatBan.ChiTietDatBanId>{
    public ChiTietDatBanDAO(Class<ChiTietDatBan> clazz) {
        super(clazz);
    }

    public List<ChiTietDatBan> getList (String maDDB){
        String jpql = "SELECT t FROM ChiTietDatBan t WHERE t.donDatBan.id = :maDDB";
        return em.createQuery(jpql, ChiTietDatBan.class).setParameter("maDDB", maDDB).getResultList();
    }
    public boolean deleteChiTietDatBan (String maDDB){
        String sql="DELETE FROM ChiTietDatBan c WHERE c.donDatBan.maDDB =:maDDB";
        return em.createQuery(sql).setParameter("maDDB", maDDB).executeUpdate()>0;

    }
    public static void main(String[] args) {
        ChiTietDatBanDAO ctdb_dao = new ChiTietDatBanDAO(ChiTietDatBan.class);

        List<ChiTietDatBan> list = ctdb_dao.getList("DB241213002");
        for (ChiTietDatBan chiTietDatBan : list) {
            System.out.println(chiTietDatBan);
        }
    }
}
