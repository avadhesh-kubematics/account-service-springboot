package com.service.account.service;

import com.service.account.connector.CustomerConnector;
import com.service.account.model.AccountDAO;
import com.service.account.model.AccountVO;
import com.service.account.model.Customer;
import com.service.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.service.account.mapper.AccountsDTOToAccountsVOMapper.MAPPER;

@Slf4j
@Service
public class AccountService {

    private CustomerConnector customerConnector;
    private AccountRepository accountRepository;

    public AccountService(CustomerConnector customerConnector, AccountRepository accountRepository) {
        this.customerConnector = customerConnector;
        this.accountRepository = accountRepository;
    }

    public AccountVO create(int customerId) {
        log.info("AccountService : create : Init..");
        log.debug("AccountService : create : Calling Customer Connector to get customer details for : {}", customerId);
        Customer customer = this.customerConnector.getCustomer(customerId);

        AccountDAO accountDAO = new AccountDAO();
        accountDAO.setCustomerId(customerId);

        log.debug("AccountService : create : Calling Account Repository to create accounts for customerId : {}", customerId);
        AccountDAO storedAccounts = this.accountRepository.save(accountDAO);
        log.debug("AccountService : create : Account created successfully and details are : {}", accountDAO);

        AccountVO accountVO = MAPPER.map(storedAccounts, customer);

        log.debug("AccountService : create : Sending back the Account and customer details :  {}", accountVO);
        log.info("AccountService : create : End..");
        return accountVO;
    }
}
