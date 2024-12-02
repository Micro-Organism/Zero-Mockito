package com.zero.mockito.service.impl;

import com.zero.mockito.service.MockitoService;
import org.springframework.stereotype.Service;

@Service
public class MockitoServiceImpl implements MockitoService {

    @Override
    public String say(String name) {
        return "Hello " + name;
    }
}
