package com.toptal.steppin.core.service.impl;

import com.toptal.steppin.core.dao.LogEntryRepository;
import com.toptal.steppin.core.dao.UserRepository;
import com.toptal.steppin.core.error.CoreException;
import com.toptal.steppin.core.error.ExceptionFactory;
import com.toptal.steppin.core.model.LogEntry;
import com.toptal.steppin.core.model.User;
import com.toptal.steppin.core.service.LogEntryService;
import com.toptal.steppin.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.List;

/**
 * Demo Project documentation not available
 * User: ggomes
 */
@Component
public class LogEntryServiceImpl implements LogEntryService {

    @Autowired
    LogEntryRepository logEntryRepository;
    @Autowired
    ExceptionFactory exceptionFactory;


    @Override
    public LogEntry persist(LogEntry logEntry) {
        return logEntryRepository.save(logEntry);
    }

    @Override
    public LogEntry fetch(Long id) {
        return logEntryRepository.findOne(id);
    }

    @Override
    public List<LogEntry> fetchAll() {
        return (List<LogEntry>) logEntryRepository.findAll();
    }

    @Override
    public LogEntry delete(Long id) {
        LogEntry logEntry = logEntryRepository.findOne(id);
        logEntryRepository.delete(logEntry);
        return logEntry;
    }
}
