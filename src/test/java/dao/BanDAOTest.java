package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.Ban;
import model.LoaiBan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BanDAOTest {
    BanDAO banDAO = new BanDAO();

    @Test
    void testCreateBan_success() {
        Ban ban = new Ban();
        ban.setMaBan("B002");
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
    @Test
    void testCreateBan_emptyObject() {
        Ban ban = new Ban();

        boolean result = banDAO.createBan(ban);

        Assertions.assertEquals(false, result);
    }

    @Test
    void testCreateBan_nullAgr(){
        boolean result = banDAO.createBan(null);

        Assertions.assertEquals(false, result);
    }

    @Test
    void updateBan() {
        Ban ban = banDAO.timTheoMa("B002");
        ban.setTrangThai("Hoạt động");
        boolean result = banDAO.updateBan(ban);

        Assertions.assertEquals(true, result);
    }


    @Test
    void deleteBan() {
        boolean result = banDAO.deleteBan("B002");
        Assertions.assertEquals(true, result);
    }

    @Test
    void getAllBan() {
        List<Ban> banList = banDAO.getAllBan();
        Assertions.assertNotEquals(null, banList);
    }

    @Test
    void timTheoMaTest() {
        Ban ban = banDAO.timTheoMa("B002");
        Assertions.assertNotEquals(null, ban);
    }

    @Test
    void timTheoMaTest_nullReturned(){
        Ban ban = banDAO.timTheoMa("12314");
        Assertions.assertEquals(null, ban);
    }
}

