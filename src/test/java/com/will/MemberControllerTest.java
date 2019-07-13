package com.will;

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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    /*@Test
    public void getMembers() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/members/all"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Will\",\"type\":\"Free\"},{\"id\":2,\"name\":\"John\",\"type\":\"Free\"},{\"id\":3,\"name\":\"James\",\"type\":\"Premium\"}]"));
    }*/

    @Test
    public void getMember() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/members/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "        \"id\": 1,\n" +
                        "        \"name\": \"Clarice\",\n" +
                        "        \"type\": \"Investigator\"\n" +
                        "    }"));
    }

    @Test
    public void insertAndCheck() throws Exception{
        int id = 5;
        //Insert new member to db
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("/members/insert/"+ id)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(String.format("{\n" +
                        "        \"id\": %d,\n" +
                        "        \"name\": \"Jack Crawford\",\n" +
                        "        \"type\": \"Agent\"\n" +
                        "    }",id));

        this.mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk());


        //Check new member is present in db
        mvc.perform(MockMvcRequestBuilders.get("/members/5"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "        \"id\": 5,\n" +
                        "        \"name\": \"Jack Crawford\",\n" +
                        "        \"type\": \"Agent\"\n" +
                        "    }"));

    }
}
