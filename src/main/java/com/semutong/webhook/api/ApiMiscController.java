package com.semutong.webhook.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Api(tags = {"기타 API"})
@RestController
public class ApiMiscController {
    @Value("${spring.jackson.date-format}")
    private String format;

    private static final Date SERVER_STARTED_TIME = new Date();

    @Data
    @AllArgsConstructor
    public static class HealthCheckResponseDTO {
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
        private Date serverStartedTime;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
        private Date serverRealTime;
    }

    @ApiOperation(notes = "", value = "")
    @GetMapping("/")
    public ResponseEntity<HealthCheckResponseDTO> healthCheck() throws Exception {
        return ResponseEntity.ok(new HealthCheckResponseDTO(SERVER_STARTED_TIME, new Date()));
    }
}
