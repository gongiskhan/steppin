package com.toptal.steppin.core.dao;

import com.toptal.steppin.core.AbstractCoreTest;
import com.toptal.steppin.core.AbstractLogEntryTest;
import com.toptal.steppin.core.model.LogEntry;
import com.toptal.steppin.core.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;


/**
 * UserRepositoryTest Class.
 * User: ggomes
 * Date: 08/05/14
 * Time: 10:56
 */
public class LogEntryRepositoryTest extends AbstractLogEntryTest {

    @Autowired
    LogEntryRepository logEntryRepository;
    @Autowired
    UserRepository userRepository;

    LogEntry logEntry;

    @Before
    public void setUp(){
        User user = new User();
        user.setEmail("bolinhos@gmail.com");
        user.setPassword("non_hash");
        logEntry = new LogEntry();
        logEntry.setDate(new Date());
        logEntry.setDistanceInMeters((long) 33);
//        logEntry.setUser(user);
        List<LogEntry> logEntries = new ArrayList<LogEntry>();
        logEntries.add(logEntry);
        user.setLogEntries(logEntries);
    }

    @Test
    @Rollback(true)
    public void testSave(){

        LogEntry lE = logEntryRepository.save(logEntry);

        assertEquals(logEntry.getDate(), lE.getDate());
        assertEquals(logEntry.getDistanceInMeters(), lE.getDistanceInMeters());
        assertEquals(logEntry.getTimeInMilliseconds(), lE.getTimeInMilliseconds());
//        assertEquals(logEntry.getUser(), lE.getUser());
    }

    @Test
    @Rollback(true)
    public void testUpdate(){

        LogEntry lE = logEntryRepository.save(logEntry);

        lE.setDistanceInMeters(88L);
        logEntryRepository.save(lE);

        LogEntry lDB = logEntryRepository.findOne(lE.getId());

        assertEquals(new Long(88L),lDB.getDistanceInMeters());
    }

    @Test
    @Rollback(true)
    public void testDelete(){
        LogEntry lE = logEntryRepository.save(logEntry);
        long id = lE.getId();
        logEntryRepository.delete(id);
        assertNull(logEntryRepository.findOne(id));
    }

    @Test
    @Rollback(true)
    public void testFindLogEntriesWithinRange() throws Exception{

        LogEntry lE = logEntryRepository.save(logEntry);
        LogEntry logEntry2 = new LogEntry();
        logEntry2.setDate(new Date(new Date().getTime() + 100000000));
        logEntry2.setDistanceInMeters((long) 66);
        LogEntry lE2 = logEntryRepository.save(logEntry2);

        user = new User();
        user.setEmail("bolinhos@gmail.com");
        user.setPassword("non_hash");
        logEntry = new LogEntry();
        logEntry.setDate(new Date());
        logEntry.setDistanceInMeters((long) 33);
        List<LogEntry> logEntries = new ArrayList<LogEntry>();
        logEntries.add(logEntry);
        logEntries.add(logEntry2);
        user.setLogEntries(logEntries);
        userRepository.save(user);

        assertEquals(1, logEntryRepository.findLogEntriesWithinRange(user.getId(), new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()+999999999)).size());

//        assertEquals(logEntry.getUser(), lE.getUser());
    }
}
