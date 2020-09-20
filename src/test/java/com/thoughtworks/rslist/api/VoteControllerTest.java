package com.thoughtworks.rslist.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.rslist.dto.VoteDto;
import com.thoughtworks.rslist.entity.EventEntity;
import com.thoughtworks.rslist.entity.UserEntity;
import com.thoughtworks.rslist.entity.VoteEntity;
import com.thoughtworks.rslist.repository.EventRepository;
import com.thoughtworks.rslist.repository.UserRepository;
import com.thoughtworks.rslist.repository.VoteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.dom4j.dom.DOMNodeHelper.setData;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static sun.nio.cs.Surrogate.is;

@SpringBootTest
@AutoConfigureMockMvc
class VoteControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;
    @Autowired
    VoteRepository voteRepository;
    private UserEntity user;
    private EventEntity rsEvent;

    @BeforeEach
    void setUp(){
        setData();
    }

    private void setData() {
        user = UserEntity.builder()
                .name("liming1")
                .gender("male")
                .vote(10)
                .phone("15991047255")
                .age(20)
                .email("a@thoughtworks.com")
                .build();
        userRepository.save(user);

        rsEvent = EventEntity.builder()
                .eventName("事件1")
                .keyWord("分类1")
                .user(user)
                .build();
        eventRepository.save(rsEvent);

        VoteEntity vote = VoteEntity.builder()
                .user(user)
                .rsEvent(rsEvent)
                .voteNum(1)
                .voteTime(LocalDateTime.now())
                .build();
        voteRepository.save(vote);

       vote = VoteEntity.builder()
                .user(user)
                .rsEvent(rsEvent)
                .voteNum(2)
                .voteTime(LocalDateTime.now())
                .build();
        voteRepository.save(vote);

        vote = VoteEntity.builder()
                .user(user)
                .rsEvent(rsEvent)
                .voteNum(3)
                .voteTime(LocalDateTime.now())
                .build();
        voteRepository.save(vote);

    }

    @AfterEach
    void tearDown(){
        voteRepository.deleteAll();
        eventRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    void should_return_votes_between_vote_time() throws Exception {
        String start = "2020-01-01 15:59:59";
        String end = "2020-01-04 16:59:59";
        String voteTime1 = "2020-01-01 11:11:11";
        String voteTime2 = "2020-01-02 11:11:11";
        String voteTime3 = "2020-01-03 11:11:11";

        DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime voteTime11 = LocalDateTime.parse(voteTime1, timeDtf);
        LocalDateTime voteTime22 = LocalDateTime.parse(voteTime2, timeDtf);
        LocalDateTime voteTime33 = LocalDateTime.parse(voteTime3, timeDtf);
        voteRepository.save(new VoteEntity(1, 1, voteTime11, user, rsEvent));
        voteRepository.save(new VoteEntity(2, 1, voteTime22, user, rsEvent));
        voteRepository.save(new VoteEntity(3, 1, voteTime33, user, rsEvent));

        mockMvc.perform(get(String.format("/vote/time?start=%s&end=%s&page=%d", start, end, 0)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(2)));
    }

//    @Test
//    void should_get_votes_by_userId_and_rsEventId() throws Exception {
//        mockMvc.perform(get("/vote")
//                .param("userId",String.valueOf(user.getId()))
//                .param("rsEventId",String.valueOf(rsEvent.getId())))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$",hasSize(8)))
//                .andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[0].userId", is(user.getId())))
//                .andExpect((ResultMatcher) jsonPath("$[0].rsEventId", is(rsEvent.getId())))
//                .andExpect((ResultMatcher) jsonPath("$[0].voteNum", is(1)));
//
//    }
}