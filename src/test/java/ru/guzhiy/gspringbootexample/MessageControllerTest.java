package ru.guzhiy.gspringbootexample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.guzhiy.gspringbootexample.model.Message;
import ru.guzhiy.gspringbootexample.service.CommonMessageService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private String baseUrl = "http://localhost:8081/api/messages";
    private String postUrl = baseUrl + "?text=text1";

    @MockBean
    private CommonMessageService messageService;

    @Test
    public void testPostMessage() throws Exception {

        given(this.messageService.saveMessageByTextContent("text1")).willReturn(new Message(0, "text1"));
        mockMvc.perform(post(postUrl))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\" : 0 }"));
    }

    @Test
    public void testPostMessageWithZeroLength() throws Exception {
        var messageText = "";
        var url = baseUrl + "?text=" + messageText;
        given(this.messageService.saveMessageByTextContent("")).willReturn(new Message(0, ""));
        mockMvc.perform(post(url))
                .andDo(print())
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testGetMessageAndExpectStatusNotFound() throws Exception {
        var getUrl = baseUrl + "?id=10";
        given(this.messageService.findMessageById(10)).willReturn(new Message(-1, "Message not found"));
        mockMvc.perform(get(getUrl))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetMessageAndMockServiceReturnMockResult() throws Exception {
        Message message = new Message(10, "Random text of this message.");
        var url = baseUrl+"?id=" + message.getId();
        given(this.messageService.findMessageById(message.getId())).willReturn(message);
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"text\" : \"" + message.getText() + "\"}"));
    }
}
