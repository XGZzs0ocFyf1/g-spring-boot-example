package ru.guzhiy.gspringbootexample.controllers.dto;

public class MessageIdDto {
    private long id;

    public MessageIdDto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
