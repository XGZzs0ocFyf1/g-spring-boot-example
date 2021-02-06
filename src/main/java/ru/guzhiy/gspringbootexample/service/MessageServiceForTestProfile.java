package ru.guzhiy.gspringbootexample.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.guzhiy.gspringbootexample.model.LoggingEntity;
import ru.guzhiy.gspringbootexample.model.Message;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * another message service which handle local ConcurrentMap as dao layer.
 * Also this service logs handled request and response to filepath as
 * JSON objects via Gson library.
 *
 */
@Profile("test")
@Service
public class MessageServiceForTestProfile implements CommonMessageService {
    @Value("${filepath}")
    private String filepath;

    private static final Logger log = LoggerFactory.getLogger(MessageServiceForTestProfile.class);
    private final static Map<Long, Message> messages = new ConcurrentHashMap<>();

    @Override
    public Message saveMessage(Message message) {
        long id = (long) messages.size();
        messages.put(id, message);
        log.info("message {} saved", new LoggingEntity(message));
//            log.info("message {} saved", message);
        saveToFileInGson(new LoggingEntity(message, "save"), filepath);
        message.setId(id);
        return message;
    }

    @Override
    public Message saveMessageByTextContent(String messageContent) {
        return saveMessage(new Message(1, messageContent));
    }

    @Override
    public Message findMessageById(long id) {
        var currentMessage = messages.get(id);
        if (currentMessage != null) {
            log.info("loaded id = {}, text = {}", id, currentMessage.getText());
            saveToFileInGson( new LoggingEntity(currentMessage, "findById"), filepath);
            return currentMessage;
        }else{
            log.info("There is no message with id {} in database.", id);
            saveToFileInGson(new LoggingEntity(new Message(-1, "Message not found"), "findById"),
                    filepath);
            return new Message(-1, "Message not found");
        }
    }


    private void saveToFileInGson(LoggingEntity message, String filepath){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try(FileWriter fileWriter = new FileWriter(filepath, true);
        ) {
            gson.toJson(message, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
