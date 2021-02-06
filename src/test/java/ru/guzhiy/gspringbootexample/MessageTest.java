package ru.guzhiy.gspringbootexample;


import org.junit.jupiter.api.Test;
import ru.guzhiy.gspringbootexample.model.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MessageTest {


    @Test
    public void testEqualsOfBothIdenticalMessages(){
        Message m1 = new Message(1, "qwe");
        Message m2 = new Message(1, "qwe");
        assertEquals(m1, m2);
    }

    @Test
    public void testEqualsOfBothWithDifferentContent(){
        Message m1 = new Message(1, "aaaa");
        Message m2 = new Message(1, "bbbbb");
        assertNotEquals(m1, m2);
    }
}
