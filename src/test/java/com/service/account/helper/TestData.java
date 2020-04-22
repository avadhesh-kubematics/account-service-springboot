package com.service.account.helper;

import com.service.account.model.AccountDAO;
import com.service.account.model.AccountVO;
import com.service.account.model.Customer;

import java.util.Date;

import static com.service.account.helper.TestUtils.convertJsonToObject;

public class TestData {

    public static final int CUSTOMER_ID = 1000;

    public static Customer getCustomerData() {
        Customer customer = new Customer();
        customer.setFirstName("Abhishek");
        customer.setSurname("Rajput");
        customer.setId(1000);
        return customer;
    }

    public static AccountVO getAccountVO() {
        AccountVO accountVO = new AccountVO();
        accountVO.setFirstName("Abhishek");
        accountVO.setSurname("Rajput");
        accountVO.setCustomerId(1000);
        accountVO.setAccountNumber(1111);
        return accountVO;
    }

    public static AccountDAO getRequestAccountDAO() {
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.setCustomerId(1000);
        return accountDAO;
    }

    public static AccountDAO getStoredAccountDAO() {
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.setCustomerId(1000);
        accountDAO.setAccountNumber(1111);
        accountDAO.setCreatedAt(new Date());
        return accountDAO;
    }

    public static Customer getCustomerResponse() {
        return convertJsonToObject("__files/customer-service-response.json", Customer.class);
    }

}
