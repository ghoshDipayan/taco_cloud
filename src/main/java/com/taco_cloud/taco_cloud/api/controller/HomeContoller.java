package com.taco_cloud.taco_cloud.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(path="/")
public class HomeContoller {

    @GetMapping
    public String getHome(){
        return "redirect:/api/design/taco";
    }
    @GetMapping("/api")
    public String getApi(){
        return "redirect:/api/design/taco";
    }
}
