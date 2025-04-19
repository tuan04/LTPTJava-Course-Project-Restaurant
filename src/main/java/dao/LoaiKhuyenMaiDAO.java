package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.LoaiKhuyenMai;

import java.util.List;

public class LoaiKhuyenMaiDAO extends GenericDao<LoaiKhuyenMai, String>{


    public LoaiKhuyenMaiDAO(Class<LoaiKhuyenMai> clazz) {
        super(clazz);
    }
}
