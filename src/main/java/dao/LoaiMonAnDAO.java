package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.LoaiMonAn;

import java.util.List;

public class LoaiMonAnDAO extends GenericDao<LoaiMonAn, String>{


    public LoaiMonAnDAO(Class<LoaiMonAn> clazz) {
        super(clazz);
    }
}
