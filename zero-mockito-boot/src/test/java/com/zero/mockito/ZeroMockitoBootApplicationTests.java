package com.zero.mockito;

import com.zero.mockito.controller.MockitoController;
import com.zero.mockito.service.MockitoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
class ZeroMockitoBootApplicationTests {
    
    MockMvc mockMvc;

    @Autowired
    MockitoController controller;
    
    @BeforeEach
    public void setup() {
        //init mvn env
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
    
    @MockBean
    private MockitoService mockitoService;
    
    @Test
    public void mock1()  {
        //Mockito.when(mockitoService.say(Mockito.anyString())).thenReturn("harries");
        when(mockitoService.say("hblog")).thenReturn("harries");
        String  str = mockitoService.say("hblog");
        log.info(str);
        Assertions.assertNotNull(str);
    }
    
    @Test
    public void mock2()  {
        when(mockitoService.say("exception")).thenThrow(new RuntimeException("mock throw exception"));
        String  str1 = mockitoService.say("exception");
    }

    @Test
    public void mock3()  {
        mockitoService.say("hblog");
        Mockito.verify(mockitoService, Mockito.times(1)).say(Mockito.eq("hblog")) ;
    }
    
    @Test
    public void mockController() throws Exception {
        // mock service
        when(mockitoService.say("hblog")).thenReturn("harries");

        //mock controller
        ResultActions perform = this.mockMvc.perform(get("/hello"));

        log.info(perform.andReturn().getResponse().getContentAsString());
        //verify responsible
        perform.andExpect(status().isOk())
                // You don't need to write it in escaped json format
                .andExpect(MockMvcResultMatchers.content().json("{msg:\"harries\"}"));
    }

}
