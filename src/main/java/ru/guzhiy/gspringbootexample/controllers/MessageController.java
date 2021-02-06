package ru.guzhiy.gspringbootexample.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.guzhiy.gspringbootexample.controllers.dto.MessageIdDto;
import ru.guzhiy.gspringbootexample.controllers.dto.MessageTextDto;
import ru.guzhiy.gspringbootexample.service.CommonMessageService;


/**
 * message controller
 * getSettings method is moved to TestValueController.class
 */
@RestController
@RequestMapping("api/messages")
public class MessageController {

    private final CommonMessageService databaseMessageService;


    public MessageController(CommonMessageService databaseMessageService) {
        this.databaseMessageService = databaseMessageService;

    }

    @ResponseBody
    @PostMapping("")
    public ResponseEntity<MessageIdDto> saveMessage(@RequestParam(name = "text", required = false) String text) {
        if (text.length() == 0) return ResponseEntity.badRequest().build();
        var savedMessage = databaseMessageService.saveMessageByTextContent(text);
        var messageIdDto = new MessageIdDto(savedMessage.getId());
        return ResponseEntity.ok(messageIdDto);
    }

    @ResponseBody
    @GetMapping("")
    public ResponseEntity<MessageTextDto> getMessage(@RequestParam(name = "id", required = false) long id) {
        var messageFromDataBase = databaseMessageService.findMessageById(id);
        //todo: need validation here, instead of message with negative index
        if (messageFromDataBase.getId() < 0)
            return ResponseEntity.notFound().build();
        var messageTextDto = new MessageTextDto(messageFromDataBase.getText());
        return ResponseEntity.ok(messageTextDto);
    }





    /*
    fixme look here. this case will work with mvc +template engine
    //due to class javadoc this case with throwing exception,
    //annotated by @ResponceStatuse is note suitable for REST controllers
    @ResponseBody
    @PostMapping("")
    public Message postMessage(@RequestParam(name = "message", required = false) String message) {
        if (message.length() == 0)
            try {
                throw new MessageIsEmptyException("message length == 0");
            } catch (MessageIsEmptyException e) {
                e.printStackTrace();
            }
        return messageService.saveMessageByTextContent(message);
    }*/
}
