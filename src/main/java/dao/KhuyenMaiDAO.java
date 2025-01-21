package dao;

public class KhuyenMaiDAO {

    private EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
            .createEntityManager();
    //
    public boolean createKhuyenMai(KhuyenMai khuyenmai){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(ban);
            tr.commit();
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    //
    public boolean updateKhuyenMai(KhuyenMai khuyenmai){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(ban);
            tr.commit();
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
            tr.rollback();
        }
        return false;
    }
    //
    public List<KhuyenMai> getAllKhuyenMai(){
        String query = "SELECT * FROM KhuyenMai";
        return em.createQuery(query, KhuyenMai.class).getResultList();
    }
    public KhuyenMai getKhuyenMai(int maKM) {
        return em.find(KhuyenMai.class, maKM);
    }

    }
