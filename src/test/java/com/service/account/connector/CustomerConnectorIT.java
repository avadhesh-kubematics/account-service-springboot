package com.service.account.connector;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.service.account.helper.WireMockService;
import com.service.account.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static com.service.account.helper.TestData.CUSTOMER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class CustomerConnectorIT implements WireMockService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${customer.getEndpoint}")
    private String getCustomerEndpoint;

    @BeforeEach
    void setUp() {
        startService();
        stubCustomerService();
    }

    @Test
    void getCustomer_whenAValidCustomerIdIsPassed_shouldReturnTheCustomerData() {
        CustomerConnector customerConnector = new CustomerConnector(getCustomerEndpoint, restTemplate);

        Customer customer = customerConnector.getCustomer(1001);

        assertEquals("Robo", customer.getFirstName());
        assertEquals("Cop", customer.getSurname());
        assertEquals(1001, customer.getId());
        stopService();
    }
}