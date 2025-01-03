package com.incetutku.accountservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping
    public String sayHello() {
        return "Hello World!";
    }
}
