package ru.guzhiy.gspringbootexample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.guzhiy.gspringbootexample.dao.MessageRepository;
import ru.guzhiy.gspringbootexample.model.Message;


/**
 * simple spring data configured service for connecting to PostgreSQL databse
 * using JpaRepository as dao.
 *
 */
@Profile("extended")
@Service
public class DatabaseMessageService implements CommonMessageService{

    private final MessageRepository messageRepository;
    private final static Logger log = LoggerFactory.getLogger(DatabaseMessageService.class);

    public DatabaseMessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveMessage(Message message) {
        var savedMessage = messageRepository.save(message);
        log.info("message saved: {}", savedMessage);
        return savedMessage;
    }

    @Override
    public Message saveMessageByTextContent(String textContent) {
        log.info("Saving message to database: {}", textContent);
        return saveMessage(new Message(0, textContent));
    }

    @Override
    public Message findMessageById(long id) {
        var message = messageRepository.findById(id).orElse(new Message(-1, "Message not found"));
        if (message.getId() > 0) {
            log.info("Message found: {}", message);
        } else {
            log.info("Message not found.");
        }

        return message;
    }
}
