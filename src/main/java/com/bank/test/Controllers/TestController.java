package com.bank.test.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class TestController {

    @GetMapping
    public String helloTest () {
        return "esta ok";
    }
}
