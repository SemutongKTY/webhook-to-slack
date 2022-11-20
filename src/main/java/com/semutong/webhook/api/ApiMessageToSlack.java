package com.semutong.webhook.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ApiMessageToSlack {
    private final ObjectMapper objectMapper;
    @Value("${slack.url}")
    private String TO_SLACK_URL;

    @Data
    public static class ExceptionMessage {
        private String content;
        private String title;
        @JsonProperty("sender")
        private String username;
    }


    @Getter
    public enum ProjectEnum {
        the33refund,
        semutong,
        fincube;
    }


    @PostMapping("/api/webhook/exception/{project}")
    public ResponseEntity<HttpStatus> sendMessage(@RequestBody ExceptionMessage request,
                                                  @PathVariable("project") ProjectEnum project) throws Exception {
        Map<String, Object> message = formatting(request, project);
        String body = objectMapper.writeValueAsString(message);

        // set header
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // set body
        HttpEntity<String> entity = new HttpEntity<String>(body,headers);

        // send
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(TO_SLACK_URL, entity, String.class);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Map<String, Object> formatting(ExceptionMessage request, ProjectEnum project) {
        String username = "[전송자] : " + request.username;
        String text = "[제목] : " + request.getTitle() + "\n" +
                "[본문] : \n" + request.getContent();
        Map<String, Object> message = new HashMap<>();
        message.put("username", username);
        message.put("text", text);
        message.put("channel", project);
        return message;
    }

}
