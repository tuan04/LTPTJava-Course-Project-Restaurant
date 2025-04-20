package rmi;

import dao.ChiTietDatBanDAO;
import dao.DonDatBanDAO;
import model.ChiTietDatBan;
import model.DonDatBan;
import service.ChiTietDatBanService;
import service.DonDatBanService;
import service.impl.ChiTietDatBanServiceImpl;
import service.impl.DonDatBanServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;

public class RMIServer {
    public static void main(String[] args) throws Exception{

        Context context = new InitialContext();
        LocateRegistry.createRegistry(9090);

        DonDatBanDAO ddb_dao = new DonDatBanDAO(DonDatBan.class); //Java Object
        ChiTietDatBanDAO ctdb_dao = new ChiTietDatBanDAO(ChiTietDatBan.class); //Java Object


        DonDatBanService ddbService = new DonDatBanServiceImpl(ddb_dao); //Java Remote Object
        ChiTietDatBanService ctdbService = new ChiTietDatBanServiceImpl(ctdb_dao); //Java Remote Object

        context.bind("rmi://DESKTOP-0LHH81P:9090/donDatBanService", ddbService);
        context.bind("rmi://DESKTOP-0LHH81P:9090/chiTietDatBanService", ctdbService);


        System.out.println("RMI Server is running...");
    }
}
