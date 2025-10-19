package com.hoang.actorservice.controller;

import com.hoang.actorservice.model.ApiLog;
import com.hoang.actorservice.repository.ApiLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogSearchController {
    @Autowired
    private ApiLogRepository logRepository;

    @GetMapping("/search")
    public List<ApiLog> searchLogs(
            @RequestParam(required = false) String endpoint,
            @RequestParam(required = false) String method,
            @RequestParam(required = false) String startTime,
            @RequestParam(required = false) String endTime,
            @RequestParam(required = false) Integer statusCode) {

        if (endpoint != null) {
            return logRepository.findByEndpointContainingIgnoreCase(endpoint);
        }
        if (method != null && startTime != null && endTime != null) {
            LocalDateTime start = LocalDateTime.parse(startTime);
            LocalDateTime end = LocalDateTime.parse(endTime);
            return logRepository.findByMethodAndTimestampBetween(method, start, end);
        }
        if (statusCode != null) {
            return logRepository.findByStatusCode(statusCode);
        }
        return logRepository.findAll();
    }
}