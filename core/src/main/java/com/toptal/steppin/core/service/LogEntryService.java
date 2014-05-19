package com.toptal.steppin.core.service;

import com.toptal.steppin.core.model.LogEntry;

import java.util.Date;
import java.util.List;

/**
 * Demo Project documentation not available
 * User: ggomes
 * Date: 08-10-2013
 * Time: 19:03
 * To change this template use File | Settings | File Templates.
 */
public interface LogEntryService {
    LogEntry persist(LogEntry logEntry);

    LogEntry fetch(Long id);

    List<LogEntry> fetchAll();

    LogEntry delete(Long id);

    List<LogEntry> fetchLogEntriesWithinRange(Long userId, Date from, Date to);
}
