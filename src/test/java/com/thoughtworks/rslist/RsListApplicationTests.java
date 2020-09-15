package com.thoughtworks.rslist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
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
        MvcResult mvcResult = mockMvc.perform(get("/rs/event?start=1&end=3"))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        int status = response.getStatus();
        assertEquals(200,status);
        assertEquals("[第一条事件, 第二条事件, 第三条事件]",response.getContentAsString());
    }

}
