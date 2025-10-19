package com.hoang.actorservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoang.actorservice.model.ApiLog;
import com.hoang.actorservice.repository.ApiLogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class ApiLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger("ApiLogger");

    @Autowired
    private ApiLogRepository logRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object logApiCall(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        ApiLog apiLog = new ApiLog();

        String endpoint = null;
        try {
            endpoint = joinPoint.getSignature().toShortString();
            String method = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();

            apiLog.setEndpoint(endpoint);
            apiLog.setMethod(method);
            apiLog.setRequestBody(args != null && args.length > 0 ? objectMapper.writeValueAsString(args) : "");
            apiLog.setTimestamp(LocalDateTime.now());

            Object result = joinPoint.proceed();

            apiLog.setResponseBody(result != null ? objectMapper.writeValueAsString(result) : "");
            if (result instanceof ResponseEntity) {
                apiLog.setStatusCode(((ResponseEntity<?>) result).getStatusCodeValue());
            } else {
                apiLog.setStatusCode(200);
            }
            apiLog.setExecutionTime(System.currentTimeMillis() - startTime);

            logRepository.save(apiLog);

            logger.info("API Call - Endpoint: {}, Method: {}, Request: {}, Response: {}, Status: {}, Time: {}ms",
                    endpoint, method, apiLog.getRequestBody(), apiLog.getResponseBody(),
                    apiLog.getStatusCode(), apiLog.getExecutionTime());

            return result;
        } catch (Throwable t) {
            apiLog.setStatusCode(500);
            apiLog.setResponseBody(t.getMessage());
            apiLog.setExecutionTime(System.currentTimeMillis() - startTime);
            logRepository.save(apiLog);

            logger.error("API Error - Endpoint: {}, Method: {}, Error: {}",
                    endpoint, apiLog.getMethod(), t.getMessage());

            throw t;
        }
    }
}