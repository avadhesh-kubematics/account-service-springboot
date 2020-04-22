package com.service.account.service;

import com.service.account.connector.CustomerConnector;
import com.service.account.helper.TestData;
import com.service.account.model.AccountVO;
import com.service.account.model.Customer;
import com.service.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.service.account.helper.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

//
//import com.service.account.mapper.CustomerVOToCustomerMapper;
//import com.service.account.mapper.CustomerVOToCustomerMapperImpl;
//import com.service.account.model.Customer;
//import com.service.account.model.AccountVO;
//import com.service.account.repository.CustomerRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.web.client.HttpClientErrorException;
//
//import static com.service.account.helper.TestData.getCustomerData;
//import static com.service.account.helper.TestData.getCustomerVOData;
//import static java.util.Optional.ofNullable;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//


class AccountServiceTest {

    private AccountService accountService;
    private CustomerConnector mockCustomerConnector;
    private AccountRepository mockAccountRepository;

    @BeforeEach
    void setUp() {
        mockCustomerConnector = mock(CustomerConnector.class);
        mockAccountRepository = mock(AccountRepository.class);
        accountService = new AccountService(mockCustomerConnector, mockAccountRepository);
    }

    @Test
    void create_whenAValidCustomerIdIsPassed_shouldCallTheConnectorWithTheSameCustomerId() {
        accountService.create(CUSTOMER_ID);

        verify(mockCustomerConnector, times(1)).getCustomer(CUSTOMER_ID);
    }

    @Test
    void create_whenAValidCustomerIdIsPassed_shouldCallTheCustomerRepositoryWithTheCustomerDetails() {
        when(mockCustomerConnector.getCustomer(CUSTOMER_ID)).thenReturn(getCustomerData());

        accountService.create(CUSTOMER_ID);

        verify(mockAccountRepository, times(1)).save(getRequestAccountDAO());
    }

    @Test
    void create_whenAValidCustomerIdIsPassed_shouldCreateAAccountAndReturnTheAccountDetails() {
        Customer customerData = getCustomerData();
        when(mockCustomerConnector.getCustomer(CUSTOMER_ID)).thenReturn(customerData);
        when(mockAccountRepository.save(TestData.getRequestAccountDAO())).thenReturn(getStoredAccountDAO());

        AccountVO accountVO = accountService.create(CUSTOMER_ID);

        assertEquals(customerData.getId(), accountVO.getCustomerId());
        assertEquals(getStoredAccountDAO().getAccountNumber(), accountVO.getAccountNumber());
        assertEquals(customerData.getFirstName(), accountVO.getFirstName());
        assertEquals(customerData.getSurname(), accountVO.getSurname());
    }

    //
//    private CustomerRepository mockCustomerRepository = mock(CustomerRepository.class);
//    private AccountService accountService;
//    private CustomerVOToCustomerMapper mockCustomerVOToCustomerMapper;
//    private AccountVO accountVOData = getCustomerVOData();
//    private Customer customerData = getCustomerData();
//    private int CUSTOMER_ID = 1000;
//
//    @BeforeEach
//    void setUp() {
//        mockCustomerVOToCustomerMapper = mock(CustomerVOToCustomerMapperImpl.class);
//        accountService = new AccountService(mockCustomerVOToCustomerMapper, mockCustomerRepository);
//    }
//
//    @Test
//    void create_whenPassedCustomerRequestData_shouldCallTheRepositoryWithConvertedDAO() {
//        when(mockCustomerVOToCustomerMapper.map(accountVOData)).thenReturn(customerData);
//
//        accountService.create(accountVOData);
//
//        verify(mockCustomerRepository, times(1)).save(customerData);
//    }
//
//
//    @Test
//    void create_whenPassedCustomerRequestData_shouldReturnCustomerDataFromRepository() {
//        Customer expectedCustomer = new Customer();
//        when(mockCustomerVOToCustomerMapper.map(accountVOData)).thenReturn(customerData);
//        when(mockCustomerRepository.save(customerData)).thenReturn(expectedCustomer);
//
//        Customer actual = accountService.create(accountVOData);
//
//        assertEquals(expectedCustomer, actual);
//    }
//
//    @Test
//    void getCustomer_whenCustomerIdPassed_shouldCallTheRepositoryWithSameCustomerId() {
//        when(mockCustomerRepository.findById(CUSTOMER_ID)).thenReturn(ofNullable(customerData));
//
//        accountService.getCustomer(CUSTOMER_ID);
//
//        verify(mockCustomerRepository, times(1)).findById(CUSTOMER_ID);
//    }
//
//    @Test
//    void getCustomer_whenCustomerIdPassed_shouldCallTheRepositoryAndReturnCustomerData() {
//        when(mockCustomerRepository.findById(CUSTOMER_ID)).thenReturn(ofNullable(customerData));
//
//        Customer actualCustomer = accountService.getCustomer(CUSTOMER_ID);
//
//        assertEquals(customerData, actualCustomer);
//    }
//
//    @Test
//    void getCustomer_whenANonExistingCustomerIsPassed_shouldThrowError404() {
//        when(mockCustomerRepository.findById(CUSTOMER_ID)).thenReturn(java.util.Optional.empty());
//
//        assertThrows(HttpClientErrorException.class, () -> {
//            accountService.getCustomer(CUSTOMER_ID);
//        });
//    }
}