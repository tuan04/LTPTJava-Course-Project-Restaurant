package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.LoaiKhachHang;

import java.util.List;

public class LoaiKhachHangDAO extends GenericDao<LoaiKhachHang, String>{
    public LoaiKhachHangDAO(Class<LoaiKhachHang> clazz) {
        super(clazz);
    }
}
