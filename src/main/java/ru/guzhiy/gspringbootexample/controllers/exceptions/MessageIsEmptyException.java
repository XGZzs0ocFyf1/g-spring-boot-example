package ru.guzhiy.gspringbootexample.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "message is empty (message length = 0 )")
public class MessageIsEmptyException extends Exception {
    public MessageIsEmptyException(String s) {
    }
}
