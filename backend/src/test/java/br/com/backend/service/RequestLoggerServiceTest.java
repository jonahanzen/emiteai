package br.com.backend.service;

import br.com.backend.dto.request.RequestAuditLogDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import static org.mockito.Mockito.*;

class RequestLoggerServiceTest {
    @Mock
    private RabbitTemplate rabbitTemplate;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private RequestLoggerService requestLoggerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Manually set the queueName since @Value is not injected in unit tests
        java.lang.reflect.Field field = null;
        try {
            field = requestLoggerService.getClass().getDeclaredField("queueName");
            field.setAccessible(true);
            field.set(requestLoggerService, "request-audit-queue");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testLogRequest() throws Exception {
        String method = "POST";
        String uri = "/api/test";
        String headers = "Content-Type: application/json";
        String body = "{\"key\":\"value\"}";
        RequestAuditLogDTO dto = new RequestAuditLogDTO();
        dto.setMethod(method);
        dto.setUrl(uri);
        dto.setHeaders(headers);
        dto.setBody(body);
        String json = "json-string";
        when(objectMapper.writeValueAsString(any(RequestAuditLogDTO.class))).thenReturn(json);
        requestLoggerService.logRequest(method, uri, headers, body);
        verify(rabbitTemplate, times(1)).convertAndSend(eq("request-audit-queue"), eq(json));
    }

    @Test
    void testLogRequestJsonProcessingException() throws Exception {
        String method = "POST";
        String uri = "/api/test";
        String headers = "Content-Type: application/json";
        String body = "{\"key\":\"value\"}";
        when(objectMapper.writeValueAsString(any(RequestAuditLogDTO.class))).thenThrow(JsonProcessingException.class);
        requestLoggerService.logRequest(method, uri, headers, body);
        verify(rabbitTemplate, never()).convertAndSend(anyString(), anyString());
    }
}
