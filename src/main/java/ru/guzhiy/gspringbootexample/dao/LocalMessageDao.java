package ru.guzhiy.gspringbootexample.dao;

import org.springframework.stereotype.Repository;
import ru.guzhiy.gspringbootexample.model.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class LocalMessageDao implements FakeCrudRepository {

    private static Map<Long, Message> messages = new ConcurrentHashMap<>();


    //there is no update logic here
    //update logic located in LocalMessageService.class

    @Override
    public Message saveMessage(Message message) {
        long newMessageId = messages.size();
        message.setId(newMessageId);
        messages.put(newMessageId, message);
        return message;
    }

    @Override
    public Message findById(long id) {
        return messages.get(id);
    }

}
