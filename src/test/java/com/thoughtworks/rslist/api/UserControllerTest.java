package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.dto.RsEvent;
import com.thoughtworks.rslist.dto.UserInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void return_all_user() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));
    }

    @Test
    void add_user_when_info_is_complete() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));

        UserInfo userInfo = new UserInfo("xiaowang2", 19, "female", "a@thoughtworks.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    @Test
    void register_user_userName_no_empty() throws Exception {

        UserInfo userInfo = new UserInfo("", 19, "female", "a@thoughtworks.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void register_user_userName_less_than_eight() throws Exception {

        UserInfo userInfo = new UserInfo("111111111111", 19, "female", "a@thoughtworks.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    void register_user_age_less_than_100() throws Exception {

        UserInfo userInfo = new UserInfo("lili", 101, "female", "a@thoughtworks.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_user_age_more_than_18() throws Exception {

        UserInfo userInfo = new UserInfo("lili", 17, "female", "a@thoughtworks.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_user_age_no_empty() throws Exception {

        UserInfo userInfo = new UserInfo("lili", null, "female", "a@thoughtworks.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_user_gender_no_empty() throws Exception {

        UserInfo userInfo = new UserInfo("lili", 20, "", "a@thoughtworks.com", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_user_email_no_empty() throws Exception {

        UserInfo userInfo = new UserInfo("lili", 20, "", "", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_user_email_no_false() throws Exception {

        UserInfo userInfo = new UserInfo("lili", 20, "", "1", "18888888888");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_user_phone_no_empty() throws Exception {

        UserInfo userInfo = new UserInfo("lili", 20, "", "1", "");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_user_phone_no_false() throws Exception {

        UserInfo userInfo = new UserInfo("lili", 20, "", "1", "123");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}