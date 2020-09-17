package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.dto.RsEvent;
import com.thoughtworks.rslist.dto.UserInfo;
import com.thoughtworks.rslist.entity.RsEventEntity;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.repository.RsEventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RsControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RsEventRepository rsEventRepository;

    @Test
    void should_add_rsEvent_when_user_exists() throws Exception {
        UserEntity user=UserEntity.builder()
                .userName("liming1")
                .gender("male")
                .voteNum(10)
                .phone("15991047255")
                .age(20)
                .email("a@thoughtworks.com")
                .build();
        userRepository.save(user);

        String jsonValue="{\"eventName\":\"热搜事件1\",\"keyword\":\"关键字1\",\"userId\":"+user.getId()+"}";
        mockMvc.perform(post("/rs/event")
                .content(jsonValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        List<RsEventEntity> rsEvents = rsEventRepository.findAll();
        assertEquals(1,rsEvents.size());
        assertEquals("热搜事件1",rsEvents.get(0).getEventName());
        assertEquals(user.getId(),rsEvents.get(0).getUserId());
    }

    @Test
    void should_add_rsEvent_when_user_no_exists() throws Exception {

        String jsonValue="{\"eventName\":\"热搜事件1\",\"keyword\":\"关键字1\",\"userId\":5}";
        mockMvc.perform(post("/rs/event")
                .content(jsonValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));

        List<RsEventEntity> rsEvents = rsEventRepository.findAll();
        assertEquals(0,rsEvents.size());
    }

    @Test
    void should_update_rsEvent_when_user_and_eventName_and_keyword_exists() throws Exception {
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

        String jsonValue="{\"eventName\":\"热搜事件1\",\"keyword\":\"热搜分类1\",\"userId\":"+user.getId()+"}";
        mockMvc.perform(post("/rs/{rsEventId}",rsEvent.getId())
                .content(jsonValue)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        List<RsEventEntity> rsEvents = rsEventRepository.findAll();
        assertEquals(1,rsEvents.size());
        assertEquals("热搜事件1",rsEvents.get(0).getEventName());
        assertEquals("热搜分类1",rsEvents.get(0).getKeyword());
    }
//    @Test
//    void check_re_list() throws Exception {
//        mockMvc.perform(get("/rs/list"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(3)))
//                .andExpect(jsonPath("$[0].eventName",is("第一条事件")))
//                .andExpect(jsonPath("$[0].keyword",is("分类1")))
//                .andExpect(jsonPath("$[1].eventName",is("第二条事件")))
//                .andExpect(jsonPath("$[1].keyword",is("分类2")))
//                .andExpect(jsonPath("$[2].eventName",is("第三条事件")))
//                .andExpect(jsonPath("$[2].keyword",is("分类3")));
//    }
//    @Test
//    void get_one_event_by_url() throws Exception {
//        mockMvc.perform(get("/rs/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.eventName", is("第一条事件")))
//                .andExpect(jsonPath("$.keyword", is("分类1")));
//    }
//
//    @Test
//    void get_event_by_start_to_end_range() throws Exception {
//        mockMvc.perform(get("/rs/list?start=1&end=3"))
//                .andExpect(jsonPath("$[0].eventName", is("第一条事件")))
//                .andExpect(jsonPath("$[0].keyword", is("分类1")))
//                .andExpect(jsonPath("$[1].eventName", is("第二条事件")))
//                .andExpect(jsonPath("$[1].keyword", is("分类2")))
//                .andExpect(jsonPath("$[2].eventName", is("第三条事件")))
//                .andExpect(jsonPath("$[2].keyword", is("分类3")))
//                .andExpect(status().isOk());
//
//
//    }
//    @Test
//    void add_event_have_eventName_and_keyword_userName() throws Exception {
//        mockMvc.perform(get("/rs/list"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(3)));
//
//        RsEvent rsEvent = new RsEvent("第四条事件","分类4",new UserInfo("xiaowang1",22,"male","d@thoughtworks.com","18888888888"));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(rsEvent);
//        mockMvc.perform(post("/rs/event").content(json).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//
//        mockMvc.perform(get("/rs/list"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(4)))
//                .andExpect(jsonPath("$[0].eventName",is("第一条事件")))
//                .andExpect(jsonPath("$[0].keyword",is("分类1")))
//                .andExpect(jsonPath("$[1].eventName",is("第二条事件")))
//                .andExpect(jsonPath("$[1].keyword",is("分类2")))
//                .andExpect(jsonPath("$[2].eventName",is("第三条事件")))
//                .andExpect(jsonPath("$[2].keyword",is("分类3")))
//                .andExpect(jsonPath("$[3].eventName",is("第四条事件")))
//                .andExpect(jsonPath("$[3].keyword",is("分类4")));
//    }
//
//    @Test
//    void add_event_eventName_should_no_empty() throws Exception {
//        RsEvent rsEvent = new RsEvent("","分类4",new UserInfo("xiaowang1",22,"male","d@thoughtworks.com","18888888888"));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(rsEvent);
//        mockMvc.perform(post("/rs/event")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid param")));
//    }
//
//    @Test
//    void add_event_keyword_should_no_empty() throws Exception {
//        RsEvent rsEvent = new RsEvent("第一条事件","",new UserInfo("xiaowang1",22,"male","d@thoughtworks.com","18888888888"));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(rsEvent);
//        mockMvc.perform(post("/rs/event")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void add_event_user_should_no_empty() throws Exception {
//        RsEvent rsEvent = new RsEvent("第一条事件","",null);
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(rsEvent);
//        mockMvc.perform(post("/rs/event")
//                .content(json)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest());
//    }
//
//
//    @Test
//    void del_event_by_index() throws Exception {
//        mockMvc.perform(get("/rs/list"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(3)));
//
//        mockMvc.perform(delete("/rs/1"))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get("/rs/list"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(2)));
//    }
//
//    @Test
//    void update_event_by_eventName_or_keyword() throws Exception {
//        mockMvc.perform(get("/rss/1?eventName=热搜第一条事件&keyword=人气分类"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.eventName", is("热搜第一条事件")))
//                .andExpect(jsonPath("$.keyword", is("人气分类")));
//
//        mockMvc.perform(get("/rss/2?eventName=热搜第二条事件"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.eventName", is("热搜第二条事件")))
//                .andExpect(jsonPath("$.keyword", is("分类2")));
//
//        mockMvc.perform(get("/rss/3?keyword=人气分类3"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.eventName",is("第三条事件")))
//                .andExpect(jsonPath("$.keyword",is("人气分类3")));
//
//    }
//
//    @Test
//    void get_one_event_index_no_index_out_of_bound() throws Exception {
//        mockMvc.perform(get("/rs/4"))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid index")));
//    }
//
//    @Test
//    public void should_throw_rs_list_not_valid_param() throws Exception {
//        mockMvc.perform(get("/rs/list?start=1&end=4"))
//                .andExpect(status().isBadRequest())
//                .andExpect(jsonPath("$.error",is("invalid request param")));
//    }



}