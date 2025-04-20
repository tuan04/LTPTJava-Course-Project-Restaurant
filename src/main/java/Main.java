import dao.ChiTietDatBanDAO;
import dao.DonDatBanDAO;
import model.ChiTietDatBan;
import model.DonDatBan;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ChiTietDatBanDAO ddb_dao = new ChiTietDatBanDAO(ChiTietDatBan.class);
        List<ChiTietDatBan> ddb = ddb_dao.getAll();
        for (ChiTietDatBan db : ddb) {
            System.out.println(db);
        }
    }
}
