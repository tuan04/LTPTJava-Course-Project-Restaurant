package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Ban;

import java.util.ArrayList;
import java.util.List;

public class BanDAO extends GenericDao<Ban, String>{

    public BanDAO(Class<Ban> clazz) {
        super(clazz);
    }

    public static void main(String[] args) {
        BanDAO banDao = new BanDAO(Ban.class);
        List<Ban> bans = banDao.getAll();
        for(Ban ban : bans){
            System.out.println(ban);
        }
    }
}
