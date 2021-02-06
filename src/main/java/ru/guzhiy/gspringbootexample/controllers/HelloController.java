package ru.guzhiy.gspringbootexample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/greeting")
public class HelloController {


    @GetMapping("/hello")
    public String getGreeting(/*@RequestParam(value = "name", required = false) String name*/) {
        // return "Hello  !" + (name.equals("") ? "brazza" : name);
        return "Hello  brazza!";
    }

}
