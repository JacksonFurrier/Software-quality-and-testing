package com.elte.issuetracker.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class MainController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }
}
