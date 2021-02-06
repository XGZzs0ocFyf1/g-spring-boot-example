package ru.guzhiy.gspringbootexample.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.guzhiy.gspringbootexample.service.TestValueService;

@RestController
@RequestMapping("api/testValue")
public class TestValueController {

    private final TestValueService testValueService;

    public TestValueController(TestValueService testValueService) {
        this.testValueService = testValueService;
    }

    @GetMapping("")
    public String getSettings(){
        return testValueService.getValue();
    }
}
