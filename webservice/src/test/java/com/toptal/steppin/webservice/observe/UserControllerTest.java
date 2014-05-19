package com.toptal.steppin.webservice.observe;

import com.toptal.steppin.core.crypt.Sha256;
import com.toptal.steppin.core.model.LogEntry;
import com.toptal.steppin.core.model.User;
import com.toptal.steppin.core.service.UserService;
import com.toptal.steppin.core.util.JSONUtil;
import com.toptal.steppin.webservice.config.WebConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class UserControllerTest {
    private MockMvc mockMvc;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;
    @Autowired
    JSONUtil jsonUtil;

    User user1;
    User user2;
    Date now;
    String nowFormatted;

    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void setUp() throws Exception{
        mockMvc = webAppContextSetup(ctx).build();
        now = new Date();
        nowFormatted = new SimpleDateFormat("yyyy/MM/dd").format(now);
        user1 = new User();
        user1.setEmail("user1@gmail.com");
        user1.setPassword("non_hash");
        LogEntry logEntry1 = new LogEntry();
        logEntry1.setDate(now);
        logEntry1.setDistanceInMeters(33L);
        List<LogEntry> logEntries1 = new ArrayList<LogEntry>();
        logEntries1.add(logEntry1);
        user1.setLogEntries(logEntries1);
        user2 = new User();
        user2.setEmail("user2@gmail.com");
        user2.setPassword("non_hash");
        LogEntry logEntry2 = new LogEntry();
        logEntry2.setDate(now);
        logEntry2.setDistanceInMeters(66L);
        List<LogEntry> logEntries2 = new ArrayList<LogEntry>();
        logEntries2.add(logEntry2);
        user2.setLogEntries(logEntries2);
        try{
            userService.delete(userService.fetchByEmail("user1@gmail.com").getId());
            userService.delete(userService.fetchByEmail("user2@gmail.com").getId());
        }catch(Exception ex){}
    }

    @Test
    @Rollback(true)
    public void test_list() throws Exception{

        userService.persist(user1);
        userService.persist(user2);

        mockMvc.perform(get("/user"))
        .andExpect(status().is(200))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[0].email", is("user1@gmail.com")))
        .andExpect(jsonPath("$[0].logEntries[0].date", is(nowFormatted)))
        .andExpect(jsonPath("$[0].logEntries[0].distanceInMeters", is(33)))
        .andExpect(jsonPath("$[1].email", is("user2@gmail.com")))
        .andExpect(jsonPath("$[1].logEntries[0].date", is(nowFormatted)))
        .andExpect(jsonPath("$[1].logEntries[0].distanceInMeters", is(66)));
    }

    @Test
    @Rollback(true)
    public void test_save() throws Exception{

        Object u = new Object(){
            public String email = user1.getEmail();
            public String password = user1.getPassword();
        };

        logger.debug(jsonUtil.toJSON(u));

        mockMvc.perform(post("/user").content(jsonUtil.toJSON(u)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email", is(user1.getEmail())))
                .andExpect(jsonPath("$.password", is(new ShaPasswordEncoder().encodePassword(user1.getPassword(),""))));
    }

    @Test
    @Rollback(true)
    public void test_save_duplicate() throws Exception{

        Object u = new Object(){
            public String email = user1.getEmail();
            public String password = user1.getPassword();
        };

        userService.persist(user1);

        mockMvc.perform(post("/user").content(jsonUtil.toJSON(u)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(409));
    }
}
