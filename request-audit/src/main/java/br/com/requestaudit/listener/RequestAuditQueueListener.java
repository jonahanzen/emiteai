package br.com.requestaudit.listener;

import br.com.requestaudit.service.RequestAuditLogService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RequestAuditQueueListener {

    private final RequestAuditLogService requestAuditLogService;

    public RequestAuditQueueListener(RequestAuditLogService requestAuditLogService) {
        this.requestAuditLogService = requestAuditLogService;
    }

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String message) {
        requestAuditLogService.saveLog(message);
    }
}
