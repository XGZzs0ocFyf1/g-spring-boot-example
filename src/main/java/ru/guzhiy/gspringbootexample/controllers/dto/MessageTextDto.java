package ru.guzhiy.gspringbootexample.controllers.dto;

public class MessageTextDto {

    private String text;

    public MessageTextDto() {
    }

    public MessageTextDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
