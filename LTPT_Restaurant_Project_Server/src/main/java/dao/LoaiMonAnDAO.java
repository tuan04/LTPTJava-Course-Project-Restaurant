package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.LoaiMonAn;

import java.util.List;

public class LoaiMonAnDAO extends GenericDao<LoaiMonAn, String>{
    public LoaiMonAnDAO(Class<LoaiMonAn> clazz) {
        super(clazz);

    }

//     Lấy danh sách tất cả loại món ăn

    public List<LoaiMonAn> getListLoaiMonAn() {
        return getAll();
    }

//     Tìm loại món ăn theo mã

    public LoaiMonAn timLoaiMonTheoMa(String ma) {
        return findById(ma);
    }


//    Tìm loại món ăn theo tên

    public LoaiMonAn timLoaiMonTheoTen(String tenLoaiMon) {
        try {
            TypedQuery<LoaiMonAn> query = em.createQuery(
                    "SELECT l FROM LoaiMonAn l WHERE l.tenLoaiMon = :ten", LoaiMonAn.class
            );
            query.setParameter("ten", tenLoaiMon);
            List<LoaiMonAn> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
