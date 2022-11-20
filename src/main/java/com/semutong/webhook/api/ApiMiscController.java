package com.semutong.webhook.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ApiMiscController {
    private static final Date SERVER_STARTED_TIME = new Date();

    @Data
    @AllArgsConstructor
    public static class HealthCheckResponseDTO {
        private Date serverStartedTime;
        private Date serverRealTime;
    }

    @GetMapping("/")
    public ResponseEntity<HealthCheckResponseDTO> healthCheck() throws Exception {
        return ResponseEntity.ok(new HealthCheckResponseDTO(SERVER_STARTED_TIME, new Date()));
    }
}
