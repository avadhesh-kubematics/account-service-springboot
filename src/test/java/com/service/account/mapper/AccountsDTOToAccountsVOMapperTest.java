package com.service.account.mapper;

import com.service.account.helper.TestData;
import com.service.account.model.AccountDAO;
import com.service.account.model.AccountVO;
import com.service.account.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountsDTOToAccountsVOMapperTest {

    @Test
    void map_AccountsDTOToAccountsVOMapper() {
        AccountsDTOToAccountsVOMapper mapper = new AccountsDTOToAccountsVOMapperImpl();
        AccountDAO accountDAO = TestData.getStoredAccountDAO();
        Customer customerData = TestData.getCustomerData();

        AccountVO accountVO = mapper.map(accountDAO, customerData);

        assertEquals(accountDAO.getAccountNumber(), accountVO.getAccountNumber());
        assertEquals(customerData.getId(), accountVO.getCustomerId());
        assertEquals(customerData.getFirstName(), accountVO.getFirstName());
        assertEquals(customerData.getSurname(), accountVO.getSurname());
    }
}