package br.com.backend.service;

import br.com.backend.dto.request.RequestAuditLogDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * Service used for logging and saving them in the database asynchronously.
 * This service will be used by the RequestLoggerInterceptor to log requests.
 * It will handle the logic of saving logs to the database or any other storage.
 * 
 * It is done using RabbitMQ to ensure that the logging does not block the main
 * request processing.
 */
@EnableAsync
@Service
public class RequestLoggerService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${rabbitmq.queue:request-audit-queue}")
    private String queueName;

    public RequestLoggerService(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @Async
    public void logRequest(String method, String uri, String headers, String body) {
        RequestAuditLogDTO dto = new RequestAuditLogDTO();
        dto.setMethod(method);
        dto.setUrl(uri);
        dto.setHeaders(headers);
        dto.setBody(body);
        try {
            String json = objectMapper.writeValueAsString(dto);
            rabbitTemplate.convertAndSend(queueName, json);
        } catch (JsonProcessingException e) {
            // Handle serialization error
            e.printStackTrace();
        }
    }

}
