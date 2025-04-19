package dao;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.KhuyenMai;

import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO extends GenericDao<KhuyenMai, String>{
    public KhuyenMaiDAO(Class<KhuyenMai> clazz) {
        super(clazz);
    }
}
