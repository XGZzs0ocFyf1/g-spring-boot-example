package ru.guzhiy.gspringbootexample.model;


import java.time.LocalDateTime;

public class LoggingEntity {



    private LocalDateTime createdAt = LocalDateTime.now();
    private Message message;
    private String actionType;

    public LoggingEntity() {
    }

    public LoggingEntity(Message message) {
        this.message = message;
    }

    public LoggingEntity(Message message, String actionType) {
        this.message = message;
        this.actionType = actionType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {

        return "LoggingEntity{" +
                "createdAt=" +createdAt+
                ", message=" + message +
                '}';
    }
}
