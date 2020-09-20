package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.dto.RsEvent;
import com.thoughtworks.rslist.dto.UserInfo;
import com.thoughtworks.rslist.entity.RsEventEntity;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RsEventRepository rsEventRepository;

    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
    }

//
//    @Test
//    void return_all_user() throws Exception {
//        mockMvc.perform(get("/user/list"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(1)));
//    }
//
//    @Test
//    void add_user_when_info_is_complete() throws Exception {
//        mockMvc.perform(get("/user/list"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(1)));
//
//        UserInfo userInfo = new UserInfo("xiaowang2", 19, "female", "a@thoughtworks.com", "18888888888");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get("/user/list"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(2)));
//    }
//
//    @Test
//    void register_user_userName_no_empty() throws Exception {
//
//        UserInfo userInfo = new UserInfo("", 19, "female", "a@thoughtworks.com", "18888888888");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid user")));
//
//    }
//
//    @Test
//    void register_user_userName_less_than_eight() throws Exception {
//
//        UserInfo userInfo = new UserInfo("111111111111", 19, "female", "a@thoughtworks.com", "18888888888");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid user")));
//
//    }
//
//    @Test
//    void register_user_age_less_than_100() throws Exception {
//
//        UserInfo userInfo = new UserInfo("lili", 101, "female", "a@thoughtworks.com", "18888888888");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid user")));
//    }
//
//    @Test
//    void register_user_age_more_than_18() throws Exception {
//
//        UserInfo userInfo = new UserInfo("lili", 17, "female", "a@thoughtworks.com", "18888888888");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid user")));
//    }
//
//    @Test
//    void register_user_age_no_empty() throws Exception {
//
//        UserInfo userInfo = new UserInfo("lili", null, "female", "a@thoughtworks.com", "18888888888");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid user")));
//    }
//
//    @Test
//    void register_user_gender_no_empty() throws Exception {
//
//        UserInfo userInfo = new UserInfo("lili", 20, "", "a@thoughtworks.com", "18888888888");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid user")));
//    }
//
//    @Test
//    void register_user_email_no_empty() throws Exception {
//
//        UserInfo userInfo = new UserInfo("lili", 20, "", "", "18888888888");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid user")));
//    }
//
//    @Test
//    void register_user_email_no_false() throws Exception {
//
//        UserInfo userInfo = new UserInfo("lili", 20, "", "1", "18888888888");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid user")));
//    }
//
//    @Test
//    void register_user_phone_no_empty() throws Exception {
//
//        UserInfo userInfo = new UserInfo("lili", 20, "", "1", "");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid user")));
//
//    }
//
//    @Test
//    void register_user_phone_no_false() throws Exception {
//
//        UserInfo userInfo = new UserInfo("lili", 20, "", "1", "123");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(userInfo);
//        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid user")));
//
//    }

    @Test
    void should_register_user() throws Exception {

        UserInfo userInfo = new UserInfo("xiaomin", 19, "female", "a@thoughtworks.com", "15991047255");
        String json = objectMapper.writeValueAsString(userInfo);
        mockMvc.perform(post("/user/register").content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<UserEntity> users = userRepository.findAll();
        assertEquals(1,users.size());
        assertEquals("xiaomin",users.get(0).getUserName());
    }

    @Test
    void should_return_all_user_in_mysql() throws Exception {
        UserEntity user=UserEntity.builder()
                .userName("liming1")
                .gender("male")
                .voteNum(10)
                .phone("15991047255")
                .age(20)
                .email("a@thoughtworks.com")
                .build();
        userRepository.save(user);

        mockMvc.perform(get("/user/{id}",user.getId()))
                         .andExpect(status().isOk())
                        .andExpect(jsonPath("$.userName", is("liming1")));
    }

    @Test
    void should_delete_user_by_id() throws Exception {
        UserEntity user=UserEntity.builder()
                .userName("liming1")
                .gender("male")
                .voteNum(10)
                .phone("15991047255")
                .age(20)
                .email("a@thoughtworks.com")
                .build();
        userRepository.save(user);

        mockMvc.perform(delete("/user/{id}",user.getId()))
                .andExpect(status().isNoContent());

        List<UserEntity> users = userRepository.findAll();
        assertEquals(0,users.size());
    }

    @Test
    void should_delete_user_by_id_and_delete_rsEvent() throws Exception {
        UserEntity user=UserEntity.builder()
                .userName("liming1")
                .gender("male")
                .voteNum(10)
                .phone("15991047255")
                .age(20)
                .email("a@thoughtworks.com")
                .build();
        userRepository.save(user);

        RsEventEntity rsEvent = RsEventEntity.builder()
                .eventName("事件1")
                .keyword("分类1")
                .userId(user.getId())
                .build();
        rsEventRepository.save(rsEvent);

        mockMvc.perform(delete("/user/{id}",user.getId()))
                .andExpect(status().isNoContent());

        List<UserEntity> users = userRepository.findAll();
        List<RsEventEntity> rsEvents = rsEventRepository.findAll();
        assertEquals(0,users.size());
        assertEquals(0,rsEvents.size());
    }

}