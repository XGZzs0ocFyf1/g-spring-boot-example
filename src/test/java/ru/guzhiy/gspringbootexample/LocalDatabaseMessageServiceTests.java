package ru.guzhiy.gspringbootexample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.guzhiy.gspringbootexample.model.Message;
import ru.guzhiy.gspringbootexample.service.OldLocalMessageService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalDatabaseMessageServiceTests {

    private OldLocalMessageService testInstance =  new OldLocalMessageService();

    @BeforeEach
    public void refreshTestInstance(){
        testInstance  = new OldLocalMessageService();
        testInstance.saveMessage(new Message(1, "first message"));
        testInstance.saveMessage(new Message(2, "second message"));
        testInstance.saveMessage(new Message(3, "third message"));
    }

    @Test
    public void savingThreeMessageAndExpectThreeInsideTest(){
      assertEquals(3, testInstance.getMessages().size());
    }

    @Test
    public void saveThreeEntitiesAndUpdateFirstTest(){
        testInstance.saveMessage(new Message(1, "another content"));
        assertTrue(testInstance.getMessages().contains(new Message(1, "another content")));
    }




}
