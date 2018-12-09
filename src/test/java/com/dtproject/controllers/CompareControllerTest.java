package com.dtproject.controllers;

import com.dtproject.configuration.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class CompareControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    @Test
    public void uploadPage() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(this.ctx).build();
        mockMvc.perform(get("/upload")).andExpect(status().isOk());
    }

}