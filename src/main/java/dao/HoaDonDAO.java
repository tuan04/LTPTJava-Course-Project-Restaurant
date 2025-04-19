package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.HoaDon;

public class HoaDonDAO extends GenericDao<HoaDon, String>{
    public HoaDonDAO(Class<HoaDon> clazz) {
        super(clazz);
    }
}
