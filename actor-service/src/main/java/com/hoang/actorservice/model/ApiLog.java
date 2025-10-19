package com.hoang.actorservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_logs")
@Data
public class ApiLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String endpoint;
    private String method;
    private String requestBody;
    private String responseBody;
    private int statusCode;
    private LocalDateTime timestamp;
    private long executionTime;
}