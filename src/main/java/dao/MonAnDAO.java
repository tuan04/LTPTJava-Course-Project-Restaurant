package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.MonAn;

import java.util.List;

public class MonAnDAO extends GenericDao<MonAn, String>{

    public MonAnDAO(Class<MonAn> clazz) {
        super(clazz);
    }

    public static void main(String[] args) {

    }
}
