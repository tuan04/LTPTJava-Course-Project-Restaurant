package data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class Runner {
    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("mariadb-pu")
                .createEntityManager();
    }
}
