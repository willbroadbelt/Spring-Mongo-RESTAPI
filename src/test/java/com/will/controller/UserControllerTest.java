package com.will.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void registerAndLoginTest() throws Exception{
        MockHttpServletRequestBuilder builderSignup =
                MockMvcRequestBuilders.post("/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(String.format("{\n" +
                                "        \"email\": \"test@email.com\",\n" +
                                "        \"password\": \"RandomPass\"\n" +
                                "    }"));

        this.mvc.perform(builderSignup)
                .andExpect(MockMvcResultMatchers.status().isOk());

        MockHttpServletRequestBuilder builderLogin =
                MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(String.format("{\n" +
                                "        \"email\": \"test@email.com\",\n" +
                                "        \"password\": \"RandomPass\"\n" +
                                "    }"));

        this.mvc.perform(builderLogin)
                .andExpect(MockMvcResultMatchers.status().isOk());


    }

}
