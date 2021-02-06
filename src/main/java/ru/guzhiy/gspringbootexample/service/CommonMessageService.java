package ru.guzhiy.gspringbootexample.service;

import ru.guzhiy.gspringbootexample.model.Message;

public interface CommonMessageService {
    Message saveMessage(Message message);
    Message saveMessageByTextContent(String messageContent);
    Message findMessageById(long id);
}
