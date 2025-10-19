package com.hoang.actorservice.repository;

import com.hoang.actorservice.model.ApiLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ApiLogRepository extends JpaRepository<ApiLog, Long> {
    List<ApiLog> findByEndpointContainingIgnoreCase(String endpoint);
    List<ApiLog> findByMethodAndTimestampBetween(String method, LocalDateTime start, LocalDateTime end);
    List<ApiLog> findByStatusCode(int statusCode);
}