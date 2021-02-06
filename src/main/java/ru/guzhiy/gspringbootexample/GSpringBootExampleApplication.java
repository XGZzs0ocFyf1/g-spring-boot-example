package ru.guzhiy.gspringbootexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.guzhiy.gspringbootexample.dao.MessageRepository;
import ru.guzhiy.gspringbootexample.model.Message;


/***
 * Profiles
 *      local - using LocalMessageDao.class as dao layer
 *      test - using instance of ConcurrentMap as dao layer
 *      extended - using PostgreSQL db as storage, and MessageRepository as dao layer
 *
 */
@SpringBootApplication
public class GSpringBootExampleApplication {

    private static Logger logger = LoggerFactory.getLogger(GSpringBootExampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GSpringBootExampleApplication.class, args);
    }

}
