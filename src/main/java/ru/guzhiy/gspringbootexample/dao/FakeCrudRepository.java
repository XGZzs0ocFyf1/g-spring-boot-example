package ru.guzhiy.gspringbootexample.dao;

import ru.guzhiy.gspringbootexample.model.Message;

public interface FakeCrudRepository {

    Message saveMessage(Message message);
    Message findById(long id);


}
