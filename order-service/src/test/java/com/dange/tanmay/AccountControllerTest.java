/*
package com.dange.tanmay;

import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AccountService.class)
@WebAppConfiguration
@IntegrationTest({"server.port:0",
        "spring.datasource.url:jdbc:h2:mem:two-phase-cororididator;DB_CLOSE_ON_EXIT=FALSE"})
public class AccountControllerTest {
    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = port;
    }


    @Test
    public void testPrepareValidAmount() throws Exception {
        given().param("txnId", 1)
                .param("accountName", "tanmay")
                .param("amount", 200)
                .get("/prepare")
                .then()
                .body(is("True"));
    }


    @Test
    public void testPrepareGreaterAmount() throws Exception {
        given().param("txnId", 1)
                .param("accountName", "tanmay")
                .param("amount", 20000)
                .get("/prepare")
                .then()
                .body(is("False"));
    }


    @Test
    public void testCommit() throws Exception {
        given().param("txnId", 1)
                .get("/commit")
                .then()
                .body(is("True"));
    }


     @Test
    public void testPrepareValidAmountForRollback() throws Exception {
        given().param("txnId", 2)
                .param("accountName", "tanmay")
                .param("amount", 300)
                .get("/prepare")
                .then()
                .body(is("True"));
    }

    @Test
    public void testRollback() throws Exception {
        given().param("txnId", 2)
                .get("/rollback")
                .then()
                .body(is("True"));
    }

}*/
