package com.zero.mockito.controller;

import com.zero.mockito.service.MockitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mockito")
public class MockitoController {

    MockitoService mockitoService;

    @Autowired
    public MockitoController(MockitoService mockitoService) {
        this.mockitoService = mockitoService;
    }

    @RequestMapping("/hello")
    public Map<String, Object> showHelloWorld(){
        Map<String, Object> map = new HashMap<>();
        map.put("msg", mockitoService.say("zero"));
        return map;
    }

}
