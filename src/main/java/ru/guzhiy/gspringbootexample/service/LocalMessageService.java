package ru.guzhiy.gspringbootexample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.guzhiy.gspringbootexample.dao.LocalMessageDao;
import ru.guzhiy.gspringbootexample.model.Message;

import java.util.Objects;


/**
 * local message service from homework task
 *
 */
@Service
@Profile("local")
public class LocalMessageService implements CommonMessageService {

    private final LocalMessageDao localMessageDao;
    private final static Logger log = LoggerFactory.getLogger(LocalMessageService.class);
    public LocalMessageService(LocalMessageDao localMessageDao) {
        this.localMessageDao = localMessageDao;
    }

    @Override
    public Message saveMessage(Message message) {
        var savedMessage = localMessageDao.saveMessage(message);
        log.info("message {} saved", savedMessage);
        return savedMessage;
    }

    @Override
    public Message saveMessageByTextContent(String messageContent) {
        return saveMessage(new Message(0, messageContent));
    }

    @Override
    public Message findMessageById(long id) {
        var retrievedMessage =  localMessageDao.findById(id);
        log.info("Message retrieved from database: {}", retrievedMessage);
        if (Objects.isNull(retrievedMessage))
            retrievedMessage = new Message(-1, "Message not found");
        return retrievedMessage;
    }
}
