package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.LoaiBan;

import java.util.List;

public class LoaiBanDAO extends GenericDao<LoaiBan, String>{

    public LoaiBanDAO(Class<LoaiBan> clazz) {
        super(clazz);
    }
}
