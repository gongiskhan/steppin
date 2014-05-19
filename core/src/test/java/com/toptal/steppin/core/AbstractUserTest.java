package com.toptal.steppin.core;

import com.toptal.steppin.core.model.LogEntry;
import com.toptal.steppin.core.model.User;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ggomes on 12/05/14.
 */
public abstract class AbstractUserTest extends AbstractCoreTest{

    protected User user;

    @Before
    public void setUp(){
        user = new User();
        user.setEmail("bolinhos@gmail.com");
        user.setPassword("non_hash");
        LogEntry logEntry = new LogEntry();
        logEntry.setDate(new Date());
        logEntry.setDistanceInMeters((long) 33);
//        logEntry.setUser(user);
        List<LogEntry> logEntries = new ArrayList<LogEntry>();
        logEntries.add(logEntry);
        user.setLogEntries(logEntries);
    }
}
