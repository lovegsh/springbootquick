package com.gsh.springbootquick.controller;

import com.gsh.springbootquick.SpringbootQuickApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * @author GSH
 * @create 2023/1/24 17:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SpringbootQuickApplication.class})
@AutoConfigureMockMvc
public class UserControllerTest {
    @Resource
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @DisplayName("文本测试用例")
    public void testList() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("id查询测试用例")
    public void getById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/testJSON")
                .header("token", "123")
                .param("code", "200"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("access_token").value("123"))
                .andReturn();
    }

//    @Test
//    @WithMockUser(username="waylau", password="123456", roles={"USER"})  // mock 了一个用户
//    public void testListWithUser() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
//                .andExpect(status().isOk());
//    }
}
