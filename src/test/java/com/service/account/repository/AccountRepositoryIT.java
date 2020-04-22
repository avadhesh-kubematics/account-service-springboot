package com.service.account.repository;

import com.service.account.model.AccountDAO;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.service.account.helper.TestData.getRequestAccountDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase
@ActiveProfiles("test")
public class AccountRepositoryIT {

    @Autowired
    private AccountRepository accountRepository;
    private AccountDAO accountDAO = getRequestAccountDAO();

    @Test
    public void save_whenAccountDAOIsPassed_shouldCreateAccountAndStoreData() {
        AccountDAO storedAccountDAO = accountRepository.save(accountDAO);

        assertEquals(1000, storedAccountDAO.getCustomerId());
        assertEquals(1111, storedAccountDAO.getAccountNumber());
        assertNotNull(storedAccountDAO.getCreatedAt());
    }
}
