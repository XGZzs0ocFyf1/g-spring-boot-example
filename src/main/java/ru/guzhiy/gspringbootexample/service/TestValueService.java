package ru.guzhiy.gspringbootexample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TestValueService {

    private final static Logger log = LoggerFactory.getLogger(TestValueService.class);

    @Value("${test.my.value}")
    private String value;

    public String getValue(){
        log.info("value = {}", value);
        return value;
    }
}
