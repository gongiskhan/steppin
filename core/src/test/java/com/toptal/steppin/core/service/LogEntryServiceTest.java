package com.toptal.steppin.core.service;

import com.toptal.steppin.core.AbstractLogEntryTest;
import com.toptal.steppin.core.model.LogEntry;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * Created by ggomes on 11/05/14.
 */
public class LogEntryServiceTest extends AbstractLogEntryTest {

    @Autowired
    private LogEntryService logEntryService;

    @Test
    @Rollback(true)
    public void testPersist() throws Exception{
        try{
            logEntryService.delete(1L);
            logEntryService.delete(2L);
        }catch (Exception ex){}

        logEntryService.persist(logEntry);

        LogEntry lE = logEntryService.fetch(logEntry.getId());
        assertEquals(logEntry.getDate(), lE.getDate());
        assertEquals(logEntry.getDistanceInMeters(), lE.getDistanceInMeters());
        assertEquals(logEntry.getTimeInMilliseconds(), lE.getTimeInMilliseconds());
//        assertEquals(logEntry.getUser(), lE.getUser());
    }

    @Test
    @Rollback(true)
    public void testDelete() throws Exception{
        LogEntry lE = logEntryService.persist(logEntry);
        long id = lE.getId();
        logEntryService.delete(id);
        assertNull(logEntryService.fetch(id));
    }

    @Test
    @Rollback(true)
    public void testFindLogEntriesWithinRange() throws Exception{
        try{
            logEntryService.delete(1L);
            logEntryService.delete(2L);
        }catch (Exception ex){}

        logEntryService.persist(logEntry);

        LogEntry lE = logEntryService.fetch(logEntry.getId());
        assertEquals(logEntry.getDate(), lE.getDate());
        assertEquals(logEntry.getDistanceInMeters(), lE.getDistanceInMeters());
        assertEquals(logEntry.getTimeInMilliseconds(), lE.getTimeInMilliseconds());
//        assertEquals(logEntry.getUser(), lE.getUser());
    }
}
