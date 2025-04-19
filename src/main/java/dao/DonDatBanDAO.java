package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.Ban;
import model.DonDatBan;

import java.util.List;


public class DonDatBanDAO extends GenericDao<Ban, String>{
    public DonDatBanDAO(Class<Ban> clazz) {
        super(clazz);
    }
}
