package com.hoang.actorservice;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class ApiLoggingConfig {
    @PostConstruct
    public void init() {
        configureLogback();
    }

    private void configureLogback() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        RollingFileAppender<ch.qos.logback.classic.spi.ILoggingEvent> rollingFileAppender = new RollingFileAppender<>();
        rollingFileAppender.setContext(context);
        rollingFileAppender.setFile("logs/api.log");

        SizeAndTimeBasedRollingPolicy<ch.qos.logback.classic.spi.ILoggingEvent> policy = new SizeAndTimeBasedRollingPolicy<>();
        policy.setContext(context);
        policy.setFileNamePattern("logs/api.%d{yyyy-MM-dd}.%i.log");
        policy.setMaxFileSize(FileSize.valueOf("10MB"));
        policy.setMaxHistory(30);
        policy.setTotalSizeCap(FileSize.valueOf("1GB"));
        policy.setParent(rollingFileAppender);
        policy.start();

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n");
        encoder.start();

        rollingFileAppender.setEncoder(encoder);
        rollingFileAppender.setRollingPolicy(policy);
        rollingFileAppender.start();

        ch.qos.logback.classic.Logger logger = context.getLogger("ApiLogger");
        logger.addAppender(rollingFileAppender);
    }
}