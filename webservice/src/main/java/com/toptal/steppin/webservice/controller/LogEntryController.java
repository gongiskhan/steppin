package com.toptal.steppin.webservice.controller;

import com.toptal.steppin.core.error.CoreException;
import com.toptal.steppin.core.model.LogEntry;
import com.toptal.steppin.core.service.LogEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.List;

/**
 * User Class.
 * User: ggomes
 */
@RestController
@RequestMapping("/logEntry")
public class LogEntryController {

    @Autowired
    LogEntryService logEntryService;

    @RequestMapping(method = RequestMethod.GET)
    public List<LogEntry> list(){
        return logEntryService.fetchAll();
    }

    @RequestMapping(params = {"id"}, method = RequestMethod.GET)
    public LogEntry get(@RequestParam("id") Long id){
        return logEntryService.fetch(id);
    }

    @RequestMapping(method = {RequestMethod.POST})
    public ResponseEntity<LogEntry> save(@RequestBody LogEntry logEntry) throws CoreException, UnsupportedEncodingException, GeneralSecurityException {
        return new ResponseEntity<>(logEntryService.persist(logEntry), HttpStatus.CREATED);
    }

    @RequestMapping(method = {RequestMethod.PUT})
    public ResponseEntity<LogEntry> update(@RequestBody LogEntry logEntry) throws CoreException, UnsupportedEncodingException, GeneralSecurityException {
        return new ResponseEntity<>(logEntryService.persist(logEntry), HttpStatus.OK);
    }

    @RequestMapping(params = {"userId","from","to"},method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<List<LogEntry>> userLogEntries(@RequestParam("userId")Long userId, @RequestParam("from")Date from, @RequestParam("to")Date to){
        return new ResponseEntity<List<LogEntry>>(logEntryService.fetchLogEntriesWithinRange(userId,from,to), HttpStatus.OK);
    }

    @RequestMapping(params = {"id"}, method = RequestMethod.DELETE)
    public LogEntry delete(@RequestParam("id") Long id){
        return logEntryService.delete(id);
    }
}
