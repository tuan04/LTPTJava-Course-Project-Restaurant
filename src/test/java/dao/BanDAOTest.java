package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Ban;
import model.LoaiBan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BanDAOTest {
    BanDAO banDAO = new BanDAO();
    @Test
    public void testCreateBan() {
        // Khởi tạo đối tượng Ban với các giá trị hợp lệ
        Ban ban = new Ban();
        ban.setMaBan("B001");
        ban.setTenBan("Ban 1");
        ban.setViTri("Tầng 1");
        ban.setTrangThai("Trống");

        LoaiBan lb = new LoaiBan();
        lb.setMaLoaiBan("LB1");
        lb.setTenLoaiBan("Loại Thường");
        ban.setLoaiBan(lb);

        boolean result = banDAO.createBan(ban);

        Assertions.assertEquals(true, result);
    }
}

