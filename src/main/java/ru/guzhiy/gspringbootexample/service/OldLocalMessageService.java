package ru.guzhiy.gspringbootexample.service;

import ru.guzhiy.gspringbootexample.model.Message;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


//todo: case without storing in map
//todo: next time read task carefully
/*@Profile("local")
@Service*/
public class OldLocalMessageService implements CommonMessageService {

    private List<Message> messages = new CopyOnWriteArrayList<>();

    public List<Message> getMessages(){
        return messages;
    }

    @Override
    public Message saveMessage(Message message) {

        //update logic
        var messageFoundedById = messages.stream()
                .filter(m -> m.getId() == message.getId())
                .findFirst()
                .orElse(null);
        //if message exists we update it content and save then
        if (messageFoundedById != null){
            messageFoundedById.setText(message.getText());
            var idConvertedToInt = (int)messageFoundedById.getId();
            messages.set(idConvertedToInt, messageFoundedById);
            return messageFoundedById;
        }else{
            var messageId = messages.size();
            message.setId(messageId);
            messages.add(message);
        }
        return message;


    }

    @Override
    public Message saveMessageByTextContent(String messageContent) {
        return saveMessage(new Message(0, messageContent));
    }

    @Override
    public Message findMessageById(long id) {
        return messages.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }


}
