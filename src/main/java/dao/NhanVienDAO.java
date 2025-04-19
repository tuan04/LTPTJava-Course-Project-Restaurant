package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.NhanVien;

import java.util.List;

public class NhanVienDAO extends GenericDao<NhanVien, String>{

    public NhanVienDAO(Class<NhanVien> clazz) {
        super(clazz);
    }
}
