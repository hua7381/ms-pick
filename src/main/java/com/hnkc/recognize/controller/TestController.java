package com.hnkc.recognize.controller;

import com.hnkc.recognize.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestService service;
    
    @GetMapping("batchTestByDb")
    public void batchTestByDb() {
        service.batchTestByDb();
    }
    
}
