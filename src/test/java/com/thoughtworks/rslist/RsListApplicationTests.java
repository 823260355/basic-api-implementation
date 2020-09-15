package com.thoughtworks.rslist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RsListApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void check_re_list() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/rs/list"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200,status);
        assertEquals("[第一条事件, 第二条事件, 第三条事件]",response.getContentAsString());
//        mockMvc.perform(get("/rs/list"))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) content().string("[第一条事件, 第二条事件, 第三条事件]"));

    }
    @Test
    void get_one_event_by_url() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/rs/1"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200,status);
        assertEquals("第一条事件",response.getContentAsString());
    }

    @Test
    void get_event_by_start_to_end_range() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/rs/list?start=1&end=3"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200,status);
        assertEquals("[第一条事件, 第二条事件, 第三条事件]",response.getContentAsString());
    }

    @Test
    void add_event_have_eventName_and_keyword() throws Exception {
        MvcResult mvcResult1 = mockMvc.perform(get("/rs/list"))
                .andReturn();
        MockHttpServletResponse response1 = mvcResult1.getResponse();
        int status1 = response1.getStatus();
        assertEquals(200,status1);
        assertEquals("[第一条事件, 第二条事件, 第三条事件]",response1.getContentAsString());

        MvcResult mvcResult2 = mockMvc.perform(post("/rs/event").content("第四条事件"))
                .andReturn();
        MockHttpServletResponse response2 = mvcResult2.getResponse();
        int status2 = response2.getStatus();
        assertEquals(200,status2);

        MvcResult mvcResult3 = mockMvc.perform(get("/rs/list"))
                .andReturn();
        MockHttpServletResponse response3 = mvcResult3.getResponse();
        int status3 = response3.getStatus();
        assertEquals(200,status3);
        assertEquals("[第一条事件, 第二条事件, 第三条事件, 第四条事件]",response3.getContentAsString());
    }



}
