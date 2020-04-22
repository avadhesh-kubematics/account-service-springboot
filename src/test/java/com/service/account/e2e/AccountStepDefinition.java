package com.service.account.e2e;

import com.service.account.helper.CustomResponseEntity;
import com.service.account.helper.SpringIntegration;
import com.service.account.helper.TestContextInterface;
import com.service.account.model.AccountVO;
import com.service.account.model.Customer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.nl.En;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.lang.annotation.Annotation;

import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.CREATED;

public class AccountStepDefinition extends SpringIntegration implements En, TestContextInterface {

    private AccountVO customer = new AccountVO();
    private ResponseEntity<AccountVO> createResponseEntityResponseEntity;
    private ResponseEntity<Customer> getCustomerResponseEntity;
    private CustomResponseEntity customResponseEntity = new CustomResponseEntity();

    @Given("Customer provides a valid customer id {string}")
    public void customer_provides_a_valid_customer_id(String customerId) {
        startService();
        stubCustomerService();
        testContext().set("customerId", customerId);
    }

    @When("The customer makes a call to create an account")
    public void the_customer_makes_a_call_to_create_an_account() {
        try {
            createResponseEntityResponseEntity = restTemplate.postForEntity(DEFAULT_URL + "/create/" + testContext().get("customerId"), null, AccountVO.class);
        } catch (HttpClientErrorException exception) {
            customResponseEntity.setStatusCode(exception.getRawStatusCode());
            customResponseEntity.setResponseMessage(exception.getResponseBodyAsString());
            testContext().setPayload(customResponseEntity);
        }
    }

    @Then("The API should return the account and customer details")
    public void the_API_should_return_the_account_and_customer_details() {
        assertEquals(CREATED.value(), createResponseEntityResponseEntity.getStatusCodeValue());
        AccountVO account = createResponseEntityResponseEntity.getBody();
        assertEquals("Robo", account.getFirstName());
        assertEquals("Cop", account.getSurname());
        assertEquals(valueOf(testContext().get("customerId")), account.getCustomerId());
        assertNotNull(account.getAccountNumber());
        stopService();
        testContext().reset();
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
