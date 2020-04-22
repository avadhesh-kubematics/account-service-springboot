package com.service.account.connector;

import com.service.account.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class CustomerConnector {

    private String getCustomerEndpoint;
    private RestTemplate restTemplate;

    public CustomerConnector(@Value("${customer.getEndpoint}") String getCustomerEndpoint, RestTemplate restTemplate) {
        this.getCustomerEndpoint = getCustomerEndpoint;
        this.restTemplate = restTemplate;
    }

    public Customer getCustomer(int customerId) {
        log.info("CustomerConnector : getCustomer : Init..");

        log.debug("CustomerConnector : getCustomer : Invoking GET Endpoint : {} for customerId",
                getCustomerEndpoint, customerId);
        ResponseEntity<Customer> responseEntity = this.restTemplate.getForEntity
                (getCustomerEndpoint, Customer.class, customerId);
        log.debug("CustomerConnector: getCustomer : Response is : {}", responseEntity);

        Customer customer = responseEntity.getBody();
        log.info("CustomerConnector : getCustomer : End..");
        return customer;
    }
}
