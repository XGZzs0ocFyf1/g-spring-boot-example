package ru.guzhiy.gspringbootexample;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValueControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;


    @Test
    public void testValueAndShouldReturnValue1() {
        testRestTemplate.getForObject("http://localhost:" + port + "/api/testValue", String.class).contains("value1");
    }



}
