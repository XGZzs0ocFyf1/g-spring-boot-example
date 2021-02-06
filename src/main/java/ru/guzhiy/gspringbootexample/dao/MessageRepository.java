package ru.guzhiy.gspringbootexample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.guzhiy.gspringbootexample.model.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {


}
