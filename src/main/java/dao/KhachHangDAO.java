package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.KhachHang;
import model.LoaiKhachHang;

import java.util.List;

public class KhachHangDAO extends GenericDao<KhachHang, String>{
    public KhachHangDAO(Class<KhachHang> clazz) {
        super(clazz);
    }
}
